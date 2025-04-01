package Main;

import Implementaciones.ListaEnlazadaSimpleCircular;
import java.util.NoSuchElementException;

public class prueba {

    public static void main(String[] args) {
        System.out.println("Probando Lista Doblemente Enlazada Circular de Strings:");
        ListaEnlazadaSimpleCircular<String> miListaDobleCircular = new ListaEnlazadaSimpleCircular<>();

        System.out.println("Lista vacia? " + miListaDobleCircular.isEmpty());
        System.out.println("Tamano inicial: " + miListaDobleCircular.size());

        miListaDobleCircular.addLast("Mundo");
        miListaDobleCircular.addFirst("Hola");
        miListaDobleCircular.addLast("!");
        miListaDobleCircular.addFirst("Inicio");

        System.out.println("Lista actual: " + miListaDobleCircular.toString());
        System.out.println("Lista vacia? " + miListaDobleCircular.isEmpty());
        System.out.println("Tamano: " + miListaDobleCircular.size());
        if (!miListaDobleCircular.isEmpty()) {

            System.out.println("Elemento 0 (Inicial): " + miListaDobleCircular.getElement(0));
            System.out.println("Elemento " + (miListaDobleCircular.size() - 1) + " (Ultimo): " + miListaDobleCircular.getElement(miListaDobleCircular.size() - 1));
        }

        System.out.println("Contiene 'Mundo'? " + miListaDobleCircular.contains("Mundo"));
        System.out.println("Contiene 'Adios'? " + miListaDobleCircular.contains("Adios"));

        System.out.println("Elemento en indice 0: " + miListaDobleCircular.getElement(0));
        System.out.println("Elemento en indice 3: " + miListaDobleCircular.getElement(3));
        System.out.println("Elemento en indice 1 (desde inicio): " + miListaDobleCircular.getElement(1));
        System.out.println("Elemento en indice 2 (desde fin): " + miListaDobleCircular.getElement(2));

        System.out.println("\n--- Operaciones de Eliminacion ---");

        System.out.println("Eliminando primero: " + miListaDobleCircular.removeFirst());
        System.out.println("Lista ahora: " + miListaDobleCircular.toString());
        System.out.println("Tamano: " + miListaDobleCircular.size());
        if (!miListaDobleCircular.isEmpty()) {
            System.out.println("Nuevo Inicial: " + miListaDobleCircular.getElement(0));
            System.out.println("Ultimo: " + miListaDobleCircular.getElement(miListaDobleCircular.size() - 1));
        }

        System.out.println("Eliminando ultimo: " + miListaDobleCircular.removeLast());
        System.out.println("Lista ahora: " + miListaDobleCircular.toString());
        System.out.println("Tamano: " + miListaDobleCircular.size());
        if (!miListaDobleCircular.isEmpty()) {
            System.out.println("Inicial: " + miListaDobleCircular.getElement(0));
            System.out.println("Nuevo Ultimo: " + miListaDobleCircular.getElement(miListaDobleCircular.size() - 1));
        }

        System.out.println("\n--- Operaciones Adicionales ---");

        miListaDobleCircular.addLast("Fin");
        System.out.println("Lista despues de addLast(Fin): " + miListaDobleCircular.toString());

        System.out.println("\n--- Pruebas de Limpieza y Errores ---");
        miListaDobleCircular.clear();
        System.out.println("Lista despues de clear: " + miListaDobleCircular.toString());
        System.out.println("Lista vacia? " + miListaDobleCircular.isEmpty());
        System.out.println("Tamano final: " + miListaDobleCircular.size());

        try {
            System.out.println("Intentando removeFirst en lista vacia (error esperado):");
            miListaDobleCircular.removeFirst();
        } catch (NoSuchElementException e) {
            System.out.println("Excepcion capturada: " + e.getMessage());
        }

        try {
            System.out.println("Intentando getElement(0) en lista vacia (error esperado):");
            miListaDobleCircular.getElement(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Excepcion capturada: " + e.getMessage());
        }
    }
}
