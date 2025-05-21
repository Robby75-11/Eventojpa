package dao;
import entities.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;


public class PersonaDao {
    private static final String PERSISTENCE_UNIT_NAME = "postgres";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private EntityManager em;

    public PersonaDao() {
        this.em = getEntityManager();
    }

    private EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }
        return em;
    }

    public void save(Persona persona) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(persona);
        em.getTransaction().commit();
    }

    public Persona findById(Long id) {
        return em.find(Persona.class, id);
    }

    public List<Persona> findAll() {
        return em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
    }

    public void update(Persona persona) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(persona);
        em.getTransaction().commit();
    }

    public void delete(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Persona persona = em.find(Persona.class, id);
        if (persona != null) {
            em.remove(persona);
        }
        em.getTransaction().commit();
    }

    public void closeEntityManager() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

}
