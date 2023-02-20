package libreria.persistencia;

import javax.persistence.*;

public class DAO<T> {

    protected final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("JPA_ej1PU");
    protected EntityManager em = EMF.createEntityManager();

    protected void conectar() throws Exception {
        if (!em.isOpen()) {
            em = EMF.createEntityManager();
        }
    }

    protected void desconectar() throws Exception {
        if (em.isOpen()) {
            em.close();
        }
    }

    protected void guardar(T objeto) throws Exception {
        conectar();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        desconectar();
    }

    protected void editar(T objeto) throws Exception {
        conectar();
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
        desconectar();
    }

    protected void eliminar(T objeto) throws Exception {
        try {
            conectar();
            em.getTransaction().begin();
        if (!em.contains(objeto)) {
            objeto = em.merge(objeto);
        }
            em.remove(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
    }
}
