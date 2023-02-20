package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.persistencia.AutorDAO;

public class AutorServicio {

    public final AutorDAO DAO = new AutorDAO();
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void menu() throws Exception {
        int opcion = 0;

        mostrarMenu();
        opcion = leeControlRango(1, 4);

        while (opcion != 4) {
            switch (opcion) {
                case 1:
                    crearAutor();
                    break;
                case 2:
                    busquedaPorNombre();
                    break;
                case 3:
                    listarAutores();
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
        System.out.println("----------------------------");
        System.out.println("MENÚ AUTOR. Ingrese una opción:");
        System.out.println("----------------------------");
        System.out.println("1- Agregar autor");
        System.out.println("2- Buscar por nombre");
        System.out.println("3- Listar autores");
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

    public void crearAutor() throws Exception {
        try {
            Autor autor;

            do {
                System.out.println("Ingrese nombre del autor");
                autor = new Autor(leer.next());
            } while (autor.getNombre().isEmpty());

            DAO.guardar(autor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void busquedaPorNombre() throws Exception {
        System.out.println("Ingrese el nombre de un autor");
        String nombre = leer.next();

        List<Autor> autores = DAO.buscarPorNombre(nombre);

        for (Autor autor : autores) {
            System.out.println(autor.toString());
        }
    }

    public void listarAutores() throws Exception {
        List<Autor> autores = DAO.listarTodos();

        System.out.println("Los autores son los siguientes:");
        for (Autor autor : autores) {
            System.out.println(autor.toString());
        }
    }

}
