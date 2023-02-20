package libreria.servicios;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import libreria.entidades.Libro;
import libreria.persistencia.LibroDAO;

public class LibroServicio {

    public final LibroDAO DAO = new LibroDAO();
    private final Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private AutorServicio as = new AutorServicio();
    private EditorialServicio es = new EditorialServicio();

    public void menu() throws Exception {
        int opcion;

        mostrarMenu();
        opcion = leeControlRango(1, 8);

        while (opcion != 8) {
            switch (opcion) {
                case 1:
                    crearLibro();
                    break;
                case 2:
                    busquedaPorIsbn();
                    break;
                case 3:
                    busquedaPorTitulo();
                    break;
                case 4:
                    busquedaPorAutor();
                    break;
                case 5:
                    busquedaPorEditorial();
                    break;
                case 6:
                    listarLibros();
                    break;
                case 7:
                    eliminarLibro();
                    break;
                case 8:
                    System.out.println("Regresando...");
                    break;
            }
            System.out.print("Ingrese enter para continuar");
            leer.nextLine();
            leer.nextLine();
            
            mostrarMenu();
            opcion = leeControlRango(1, 8);
        }
    }

    public void mostrarMenu() {
        System.out.println("----------------------------");
        System.out.println("MENÚ LIBRO. Ingrese una opción:");
        System.out.println("----------------------------");
        System.out.println("1- Ingresar libro");
        System.out.println("2- Buscar libro (ISBN)");
        System.out.println("3- Buscar libro (Título)");
        System.out.println("4- Buscar libro (Autor)");
        System.out.println("5- Buscar libro (Editorial)");
        System.out.println("6- Listar libros");
        System.out.println("7- Eliminar libro");
        System.out.println("8- Menú principal");
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

    public void crearLibro() throws Exception {
        try {
            Libro libro = new Libro();
            Libro validacion;

            do {
                System.out.println("Ingrese título del libro");
                libro.setTitulo(leer.next());
                validacion = DAO.buscarPorTitulo(libro.getTitulo());
            } while (libro.getTitulo().isEmpty() || validacion != null);

            do {
                System.out.println("Ingrese año del libro");
                libro.setAnio(leer.nextInt());
            } while (libro.getAnio() < 0 || libro.getAnio() > new Date().getYear() + 1900);

            do {
                System.out.println("Ingrese ejemplares totales");
                libro.setEjemplares(leer.nextInt());
            } while (libro.getEjemplares() < 0);

            do {
                System.out.println("Ingrese ID del autor de la siguiente lista");
                as.listarAutores();
                libro.setAutor(as.DAO.buscarPorId(leer.nextInt()));
            } while (libro.getAutor() == null);

            do {
                System.out.println("Ingrese ID de la editorial de la siguiente lista");
                es.listarEditoriales();
                libro.setEditorial(es.DAO.buscarPorId(leer.nextInt()));
            } while (libro.getEditorial() == null);

            DAO.guardar(libro);
            System.out.println("Libro agregado con éxito.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void busquedaPorIsbn() throws Exception {
        try {
            System.out.println("Ingrese isbn del libro");
            Long isbn = leer.nextLong();

            Libro libro = DAO.buscarPorId(isbn);

            if (libro == null) {
                System.out.println("No se encontró ningún libro");
            } else {
                System.out.println(libro.toString());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void busquedaPorTitulo() throws Exception {
        try {
            System.out.println("Ingrese el titulo del libro a buscar");
            String titulo = leer.next();

            Libro libro = DAO.buscarPorTitulo(titulo);

            if (libro != null) {
                System.out.println(libro.toString());
            } else {
                System.out.println("No hay libros con ese título :(");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void busquedaPorAutor() throws Exception {
        try {
            System.out.println("Ingrese el autor");
            String nombreAutor = leer.next();

            List<Libro> libros = DAO.buscarPorAutor(nombreAutor);

            if (!libros.isEmpty()) {
                System.out.println("Libros encontrados: ");
                for (Libro libro : libros) {
                    System.out.println(libro.toString());
                }
            } else {
                System.out.println("No se encontraron libros de dicho autor :(");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void busquedaPorEditorial() throws Exception {
        try {
            System.out.println("Ingrese la editorial");
            String editorial = leer.next();

            List<Libro> libros = DAO.buscarPorEditorial(editorial);

            if (!libros.isEmpty()) {
                System.out.println("\nLibros encontrados: ");
                for (Libro libro : libros) {
                    System.out.println(libro.toString());
                }
            } else {
                System.out.println("No se encontraron libros de dicha editorial :(");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminarLibro() {
        try {
            List<Libro> libros = DAO.listarTodos();
            if (!libros.isEmpty()) {
                System.out.println("Ingrese ID del libro a eliminar de la siguiente lista");
                for (Libro aux : libros) {
                    System.out.println(aux.toString());
                }
                Libro libro = DAO.buscarPorId(leer.nextLong());
                if (libro == null) {
                    System.out.println("No se encontró el libro");
                } else {
                    DAO.eliminar(libro.getIsbn());
                }
            } else {
                System.out.println("No se encontraron libros");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarLibros() throws Exception {
        List<Libro> libros = DAO.listarTodos();

        for (Libro libro : libros) {
            System.out.println(libro.toString());
        }
    }

}
