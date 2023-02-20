package libreria.persistencia;

import java.util.List;
import libreria.entidades.Autor;

public class AutorDAO extends DAO<Autor> {
    
    @Override
    public void guardar(Autor autor) throws Exception {
        super.guardar(autor);
    }
    
    public void eliminar(Integer id) throws Exception {
        Autor autor = buscarPorId(id);
        super.eliminar(autor);
    }
    
    public Autor buscarPorId(Integer id) throws Exception {
        conectar();
        Autor autor = em.find(Autor.class, id);
        desconectar();
        return autor;
    }
    
    public List<Autor> buscarPorNombre(String name) throws Exception {
        conectar();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a "
                + "WHERE a.nombre "
                + "LIKE '%" + name + "%'").getResultList();
        desconectar();
        return autores;
    }
    
    public List<Autor> listarTodos()throws Exception {
        conectar();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a").getResultList();
        desconectar();
        return autores;
    }
}
