package dao;

import entities.Evento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class EventoDao {
    private static final String PERSISTENCE_UNIT_NAME = "postgres";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private EntityManager em;

    public EventoDao() {
        this.em = getEntityManager();
    }

    private EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }
        return em;
    }

    public void save(Evento evento) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(evento);
        em.getTransaction().commit();
    }

    public Evento findById(Long id) {
        return em.find(Evento.class, id);
    }

    public List<Evento> findAll() {
        return em.createQuery("SELECT e FROM Evento e", Evento.class).getResultList();
    }

    public void update(Evento evento) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(evento);
        em.getTransaction().commit();
    }

    public void delete(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Evento evento = em.find(Evento.class, id);
        if (evento != null) {
            em.remove(evento);
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
