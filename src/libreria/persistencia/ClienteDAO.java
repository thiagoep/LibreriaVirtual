package libreria.persistencia;

import java.util.List;
import libreria.entidades.Cliente;

public class ClienteDAO extends DAO<Cliente> {
    @Override
    public void guardar(Cliente cliente) throws Exception {
        super.guardar(cliente);
    }
    
    public void eliminar(Integer id) throws Exception {
        Cliente cliente = buscarPorId(id);
        super.eliminar(cliente);
    }
    
    public Cliente buscarPorId(Integer id) throws Exception {
        conectar();
        Cliente cliente = em.find(Cliente.class, id);
        desconectar();
        return cliente;
    }
    
    public Cliente buscarPorDocumento(Long documento) throws Exception {
        try {
            conectar();
            Cliente cliente = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.documento = " + documento).getSingleResult();
            desconectar();
            return cliente;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            desconectar();
            return null;
        }
    }
    
    public void eliminar(Long documento) {
        try {
            conectar();
            Cliente cliente = buscarPorDocumento(documento);
            super.eliminar(cliente);
        } catch (Exception e) {
        }
    }
    
    public List<Cliente> listarTodos()throws Exception {
        conectar();
        List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c").getResultList();
        desconectar();
        return clientes;
    }
}
