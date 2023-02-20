package libreria.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import libreria.entidades.Canal;
import libreria.persistencia.exceptions.NonexistentEntityException;

public class CanalJpaController implements Serializable {

    public CanalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Canal canal) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(canal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Canal canal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            canal = em.merge(canal);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = canal.getId();
                if (findCanal(id) == null) {
                    throw new NonexistentEntityException("The canal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Canal canal;
            try {
                canal = em.getReference(Canal.class, id);
                canal.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The canal with id " + id + " no longer exists.", enfe);
            }
            em.remove(canal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Canal> findCanalEntities() {
        return findCanalEntities(true, -1, -1);
    }

    public List<Canal> findCanalEntities(int maxResults, int firstResult) {
        return findCanalEntities(false, maxResults, firstResult);
    }

    private List<Canal> findCanalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Canal.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Canal findCanal(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Canal.class, id);
        } finally {
            em.close();
        }
    }

    public int getCanalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Canal> rt = cq.from(Canal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
