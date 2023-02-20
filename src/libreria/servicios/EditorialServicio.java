package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDAO;

public class EditorialServicio {

    public final EditorialDAO DAO = new EditorialDAO();
    public Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void menu() throws Exception {
        int opcion;

        mostrarMenu();
        opcion = leeControlRango(1, 5);

        while (opcion != 5) {
            switch (opcion) {
                case 1:
                    crearEditorial();
                    break;
                case 2:
                    listarEditoriales();
                    break;
                case 3:
                    buscarEditorial();
                    break;
                case 4:
                    eliminarEditorial();
                    break;
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
        System.out.println("MENÚ EDITORIAL. Ingrese una opción:");
        System.out.println("----------------------------");
        System.out.println("1- Agregar editorial");
        System.out.println("2- Listar editoriales");
        System.out.println("3- Buscar editorial por ID");
        System.out.println("4- Eliminar editorial");
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

    public void crearEditorial() throws Exception {
        try {
            Editorial editorial;

            System.out.println("Ingrese nombre de la editorial");
            editorial = new Editorial(leer.next());

            DAO.guardar(editorial);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarEditoriales() {
        try {
            List<Editorial> editoriales = DAO.listarTodos();
            for (Editorial editorial : editoriales) {
                System.out.println(editorial.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarEditorial() {
        try {
            System.out.println("Ingrese ID de la editorial");
            Editorial editorial = DAO.buscarPorId(leer.nextInt());
            if (editorial == null) {
                System.out.println("No se encontró una editorial con ese ID");
            } else {
                System.out.println(editorial.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminarEditorial() {
        try {
            Editorial editorial;
            System.out.println("Ingrese ID de la editorial a eliminar de la siguiente lista: ");
            listarEditoriales();
            editorial = DAO.buscarPorId(leer.nextInt());
            if (editorial == null) {
                System.out.println("No se encontró la editorial");
            } else {
                DAO.eliminar(editorial.getId());
                if (DAO.buscarPorId(editorial.getId()) == null) {
                    System.out.println("Editorial eliminada con éxito");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
