package libreria.persistencia;

import java.util.List;
import libreria.entidades.Prestamo;

public class PrestamoDAO extends DAO<Prestamo> {
    @Override
    public void guardar(Prestamo prestamo) throws Exception {
        super.guardar(prestamo);
    }
    
    public void eliminar(Integer id) throws Exception {
        Prestamo prestamo = buscarPorId(id);
        super.eliminar(prestamo);
    }
    
    public Prestamo buscarPorId(Integer id) throws Exception {
        conectar();
        Prestamo prestamo = em.find(Prestamo.class, id);
        desconectar();
        return prestamo;
    }

    public List<Prestamo> listarTodos()throws Exception {
        conectar();
        List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p").getResultList();
        desconectar();
        return prestamos;
    }
}
