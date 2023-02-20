package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Cliente;
import libreria.persistencia.ClienteDAO;

public class ClienteServicio {

    public final ClienteDAO DAO = new ClienteDAO();
    public final Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void menu() throws Exception {
        int opcion;

        mostrarMenu();
        opcion = leeControlRango(1, 5);

        while (opcion != 5) {
            switch (opcion) {
                case 1:
                    crearCliente();
                    break;
                case 2:
                    buscarPorDocumento();
                    break;
                case 3:
                    listarClientes();
                    break;
                case 4:
                    eliminarCliente();
            }
            System.out.print("Ingrese enter para continuar");
            leer.nextLine();
            leer.nextLine();

            mostrarMenu();
            opcion = leeControlRango(1, 5);
        }
    }

    public void mostrarMenu() {
        System.out.println("----------------------------");
        System.out.println("MENU CLIENTE. Ingrese una opción:");
        System.out.println("----------------------------");
        System.out.println("1- Registrar cliente");
        System.out.println("2- Buscar por documento");
        System.out.println("3- Listar clientes");
        System.out.println("4- Eliminar cliente");
        System.out.println("5- Menú principal");
    }

    public int leeControlRango(int li, int ls) {
        int num = 0;
        do {
            if (num != 0) {
                System.out.println("Error. ingrese nuevamente");
            }
            num = leer.nextInt();
        } while (num < li || num > ls);
        return num;
    }

    public void crearCliente() {
        try {
            Cliente cliente = new Cliente(), validacion;
            do {
                System.out.println("Ingrese su número de documento");
                cliente.setDocumento(leer.nextLong());
                validacion = DAO.buscarPorDocumento(cliente.getDocumento());
            } while (cliente.getDocumento() < 0 || validacion != null);

            do {
                System.out.println("Ingrese su nombre");
                cliente.setNombre(leer.next());
            } while (cliente.getNombre().isEmpty());

            do {
                System.out.println("Ingrese su apellido");
                cliente.setApellido(leer.next());
            } while (cliente.getNombre().isEmpty());

            do {
                System.out.println("Ingrese su teléfono (10 dígitos)");
                cliente.setTelefono(leer.next());
            } while (cliente.getTelefono().length() != 10);

            DAO.guardar(cliente);
            if (DAO.buscarPorId(cliente.getId()) != null) {
                System.out.println("Cliente guardado con éxito");
            } else {
                System.out.println("No se pudo guardar el cliente :(");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("No se pudo guardar el cliente :(");
        }
    }

    public void buscarPorDocumento() {
        try {
            Cliente cliente;
            System.out.println("Ingrese DNI del cliente a encontrar");
            cliente = DAO.buscarPorDocumento(leer.nextLong());
            System.out.println(cliente.toString());
        } catch (Exception e) {
        }
    }

    public void listarClientes() throws Exception {
        try {
            List<Cliente> clientes = DAO.listarTodos();

            for (Cliente cliente : clientes) {
                System.out.println(cliente.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminarCliente() {
        try {
            List<Cliente> clientes = DAO.listarTodos();
            if (!clientes.isEmpty()) {
                System.out.println("Ingrese ID del usuario a eliminar de la siguiente lista");
                for (Cliente aux : clientes) {
                    System.out.println(aux.toString());
                }
                Cliente cliente = DAO.buscarPorId(leer.nextInt());
                if (cliente == null) {
                    System.out.println("No se encontró el cliente");
                } else {
                    DAO.eliminar(cliente.getId());
                    if (DAO.buscarPorId(cliente.getId()) == null) {
                        System.out.println("Cliente eliminado con éxito");
                    }
                }
            } else {
                System.out.println("No se encontraron clientes");
            }
        } catch (Exception e) {
        }
    }
}
