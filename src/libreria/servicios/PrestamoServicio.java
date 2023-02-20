package libreria.servicios;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import libreria.entidades.Cliente;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.persistencia.PrestamoDAO;

public class PrestamoServicio {

    public final PrestamoDAO DAO = new PrestamoDAO();
    public Scanner leer = new Scanner(System.in).useDelimiter("\n");
    LibroServicio ls = new LibroServicio();
    ClienteServicio cs = new ClienteServicio();

    public void menu() {
        int opcion;

        mostrarMenu();
        opcion = leeControlRango(1, 4);

        while (opcion != 4) {
            switch (opcion) {
                case 1:
                    crearPrestamo();
                    break;
                case 2:
                    devolverLibro();
                    break;
                case 3:
                    listarPrestamos();
                    break;
            }
            System.out.print("Ingrese enter para continuar");
            leer.nextLine();
            leer.nextLine();
            
            mostrarMenu();
            opcion = leeControlRango(1, 4);
        }
    }

    public void mostrarMenu() {
        System.out.println("MENU PRÉSTAMO. Ingrese una opción:");
        System.out.println("----------------------------");
        System.out.println("1- Pedir préstamos");
        System.out.println("2- Finalizar préstamo");
        System.out.println("3- Listar préstamos");
        System.out.println("4- Menú principal");
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

    public void crearPrestamo() {
        try {
            int dias;
            Date fecha = new Date(), fechaDevolucion;
            Prestamo prestamo = new Prestamo();
            Libro libro;
            Cliente cliente;

            prestamo.setFechaPrestamo(fecha);

            do {
                System.out.println("Ingrese cuantos días quiere alquilar");
                dias = leer.nextInt();
            } while (dias < 1);

            fechaDevolucion = new Date(fecha.getYear(), fecha.getMonth(), (fecha.getDate() + dias));
            prestamo.setFechaDevolucion(fechaDevolucion);

            do {
                System.out.println("Ingrese ID del libro de la siguiente lista");
                ls.listarLibros();
                libro = ls.DAO.buscarPorId(leer.nextLong());
                prestamo.setLibro(libro);
            } while (libro == null);

            do {
                System.out.println("Ingrese ID del cliente de la siguiente lista: ");
                cs.listarClientes();
                cliente = cs.DAO.buscarPorId(leer.nextInt());
                prestamo.setCliente(cliente);
            } while (cliente == null);

            DAO.guardar(prestamo);
            if (DAO.buscarPorId(prestamo.getId()) != null) {
                System.out.println("Préstamo exitoso");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void devolverLibro() {
        try {
            List<Prestamo> prestamos = DAO.listarTodos();
            Prestamo prestamo;
            if (!prestamos.isEmpty()) {
                System.out.println("Ingrese ID del prestamo a finalizar según la siguiente lista");
                for (Prestamo aux : prestamos) {
                    System.out.println(aux.toString());
                }
                prestamo = DAO.buscarPorId(leer.nextInt());
                DAO.eliminar(prestamo.getId());
                if (DAO.buscarPorId(prestamo.getId()) == null) {
                    System.out.println("Préstamo finalizado con éxito");
                } else {
                    System.out.println("Error al devolver");
                }
            } else {
                System.out.println("No hay prestamos existentes");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarPrestamos() {
        try {
            List<Prestamo> prestamos = DAO.listarTodos();
            for (Prestamo prestamo : prestamos) {
                System.out.println(prestamo.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
