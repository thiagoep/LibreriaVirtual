package libreria;

import java.util.Scanner;
import libreria.servicios.AutorServicio;
import libreria.servicios.ClienteServicio;
import libreria.servicios.EditorialServicio;
import libreria.servicios.LibroServicio;
import libreria.servicios.PrestamoServicio;

public class Principal {

    public static void main(String[] args) throws Exception {
        int opcion = 0;
        LibroServicio ls = new LibroServicio();
        AutorServicio as = new AutorServicio();
        EditorialServicio es = new EditorialServicio();
        ClienteServicio cs = new ClienteServicio();
        PrestamoServicio ps = new PrestamoServicio();
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        do {
            mostrarMenu();
            opcion = leer.nextInt();
        } while (opcion < 1 || opcion > 6);

        while (opcion != 6) {
            switch (opcion) {
                case 1:
                    as.menu();
                    break;
                case 2:
                    cs.menu();
                    break;
                case 3:
                    es.menu();
                    break;
                case 4:
                    ls.menu();
                    break;
                case 5:
                    ps.menu();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
            }

            do {
                mostrarMenu();
                opcion = leer.nextInt();
            } while (opcion < 1 || opcion > 6);
        }
    }

    public static void mostrarMenu() {
        System.out.println("---------------------");
        System.out.println("MENÚ PRINCIPAL. Ingrese una opción");
        System.out.println("1- Menú Autor");
        System.out.println("2- Menú Cliente");
        System.out.println("3- Menú Editorial");
        System.out.println("4- Menú Libro");
        System.out.println("5- Menú Préstamo");
        System.out.println("6- Salir");
    }

}
