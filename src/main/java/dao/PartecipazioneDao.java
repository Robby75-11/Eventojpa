package dao;
import entities.Partecipazione;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class PartecipazioneDao {
    private static final String PERSISTENCE_UNIT_NAME = "postgres";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private EntityManager em;

    public PartecipazioneDao() {
        this.em = getEntityManager();
    }

    private EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }
        return em;
    }

    public void save(Partecipazione partecipazione) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(partecipazione);
        em.getTransaction().commit();
    }

    public Partecipazione findById(Long id) {
        return em.find(Partecipazione.class, id);
    }

    public List<Partecipazione> findAll() {
        return em.createQuery("SELECT p FROM Partecipazione p", Partecipazione.class).getResultList();
    }

    public void update(Partecipazione partecipazione) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(partecipazione);
        em.getTransaction().commit();
    }

    public void delete(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Partecipazione partecipazione = em.find(Partecipazione.class, id);
        if (partecipazione != null) {
            em.remove(partecipazione);
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
