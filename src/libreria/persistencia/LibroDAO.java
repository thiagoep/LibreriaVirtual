package libreria.persistencia;

import java.util.List;
import libreria.entidades.Libro;

public class LibroDAO extends DAO<Libro> {

    @Override
    public void guardar(Libro libro) throws Exception {
        super.guardar(libro);
    }

    public void eliminar(Long id) throws Exception {
        Libro libro = buscarPorId(id);
        super.eliminar(libro);
    }

    public Libro buscarPorId(Long id) throws Exception {
        try {
            conectar();
            Libro libro = em.find(Libro.class, id);
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            desconectar();
        }
    }

    public Libro buscarPorTitulo(String nombre) {
        try {
            conectar();
            Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE '%" + nombre + "%'")
                    .getSingleResult();
            desconectar();
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Libro> buscarPorAutor(String autor) throws Exception {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.autor.nombre LIKE '%" + autor + "%'")
                .getResultList();
        desconectar();
        return libros;
    }

    public List<Libro> buscarPorEditorial(String editorial) throws Exception {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.Editorial.nombre LIKE '%" + editorial + "%'")
                .getResultList();
        desconectar();
        return libros;
    }

    public List<Libro> listarTodos() throws Exception {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l").getResultList();
        desconectar();

        return libros;
    }
}
