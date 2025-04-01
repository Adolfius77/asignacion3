package Implementaciones; 

import Nodo.NodoSimple; 
import java.util.NoSuchElementException;

/**
 * Lista Enlazada Simple Circular (LESC).
 * @param <E> Tipo de elementos.
 */
public class ListaEnlazadaSimpleCircular<E> {

   
    public NodoSimple<E> ultimo;
    private int contador;      

    /** Crea una LESC vacía. */
    public ListaEnlazadaSimpleCircular() {
        this.ultimo = null;
        this.contador = 0;
    }

    /**
     * Verifica si la lista esta vacia.
     * @return true si esta vacia.
     */
    public boolean isEmpty() {
        return contador == 0;
    }

    /**
     * Devuelve el numero de elementos.
     * @return Tamano de la lista.
     */
    public int size() {
        return contador;
    }

    /**
     * Anade un elemento al principio.
     * @param dato Elemento a anadir.
     */
    public void addFirst(E dato) {
        NodoSimple<E> nuevoNodo = new NodoSimple<>(dato);
        if (isEmpty()) {
            ultimo = nuevoNodo;
            ultimo.setSiguiente(ultimo); // Apunta a sí mismo
        } else {
            nuevoNodo.setSiguiente(ultimo.getSiguiente()); // Nuevo apunta a la antigua cabeza
            ultimo.setSiguiente(nuevoNodo); // Ultimo apunta al nuevo (que es la nueva cabeza)
        }
        contador++;
    }

    /**
     * Anade un elemento al final.
     * @param dato Elemento a anadir.
     */
    public void addLast(E dato) {
        NodoSimple<E> nuevoNodo = new NodoSimple<>(dato);
        if (isEmpty()) {
            ultimo = nuevoNodo;
            ultimo.setSiguiente(ultimo); // Apunta a sí mismo
        } else {
            nuevoNodo.setSiguiente(ultimo.getSiguiente()); // Nuevo apunta a la cabeza
            ultimo.setSiguiente(nuevoNodo); // Antiguo último apunta al nuevo
            ultimo = nuevoNodo; // El nuevo es ahora el último
        }
        contador++;
    }

    /**
     * Elimina y devuelve el primer elemento.
     * @return El primer elemento.
     * @throws NoSuchElementException Si la lista esta vacia.
     */
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("La lista está vacía.");
        }
        NodoSimple<E> cabeza = ultimo.getSiguiente(); // Nodo a eliminar
        E datoEliminado = cabeza.getDato();

        if (ultimo == cabeza) { // Es el único nodo
            ultimo = null;
        } else {
            ultimo.setSiguiente(cabeza.getSiguiente()); // Ultimo salta la cabeza
        }
        contador--;
        return datoEliminado;
    }

    /**
     * Elimina y devuelve el ultimo elemento. (O(n))
     * @return El ultimo elemento.
     * @throws NoSuchElementException Si la lista esta vacia.
     */
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("La lista está vacía.");
        }
        E datoEliminado = ultimo.getDato();

        if (contador == 1) { 
            ultimo = null;
        } else {
            // Encontrar el penúltimo nodo
            NodoSimple<E> actual = ultimo.getSiguiente(); 
            for(int i = 0; i < contador - 2; i++) { 
                 actual = actual.getSiguiente();
            }
            // actual es ahora el penultimo
            actual.setSiguiente(ultimo.getSiguiente());
            ultimo = actual;
        }
        contador--;
        return datoEliminado;
    }

    /**
     * Comprueba si la lista contiene el elemento.
     * @param dato Elemento a buscar.
     * @return true si el elemento existe.
     */
    public boolean contains(E dato) {
        if (isEmpty()) {
            return false;
        }
        NodoSimple<E> actual = ultimo.getSiguiente(); 
        for (int i = 0; i < contador; i++) {
            if (dato == null) {
                if (actual.getDato() == null) return true;
            } else {
                if (dato.equals(actual.getDato())) return true;
            }
            actual = actual.getSiguiente();
        }
        return false; // No encontrado
    }

    /**
     * Obtiene el elemento en el indice dado.
     * @param index Indice (base 0).
     * @return El elemento en esa posicion.
     * @throws IndexOutOfBoundsException Si el indice es invalido.
     */
    public E getElement(int index) {
        if (index < 0 || index >= contador) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index + ", Tamaño: " + contador);
        }
        

        NodoSimple<E> actual = ultimo.getSiguiente(); 
        for (int i = 0; i < index; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    /** Elimina todos los elementos de la lista. */
    public void clear() {
        ultimo = null;
        contador = 0;
    }

    /**
     * Devuelve la representacion String de la lista.
     * @return Cadena con formato [elem1, elem2, ...].
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        NodoSimple<E> actual = ultimo.getSiguiente();
        for (int i = 0; i < contador; i++) {
            sb.append(actual.getDato() == null ? "null" : actual.getDato().toString());
            if (i < contador - 1) {
                 sb.append(", "); // Asegurarse de añadir la coma y el espacio
            }
            actual = actual.getSiguiente();
        }
        sb.append("]");
        return sb.toString();
    }
}