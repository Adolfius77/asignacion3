package Implementaciones; // O el paquete que prefieras

import Nodo.NodoSimple;

import java.util.NoSuchElementException;

/**
 * Implementación de una Lista Enlazada Simple Circular (LESC).
 * Los elementos se enlazan unidireccionalmente y el último nodo apunta al primero.
 * Se mantiene una referencia al último nodo para operaciones eficientes.
 *
 * @param <E> Tipo de elementos que contendrá la lista.
 */
public class ListaEnlazadaSimpleCircular<E> {

    public NodoSimple<E> ultimo; // Referencia al último nodo. Desde él se accede al primero (ultimo.getSiguiente())
    private int contador;       // Número de elementos en la lista.

    /**
     * Constructor para crear una LESC vacía.
     * @pre No se requieren condiciones previas.
     * @post Se crea una instancia de LESC vacía ('ultimo' es null, 'contador' es 0).
     */
    public ListaEnlazadaSimpleCircular() {
        this.ultimo = null;
        this.contador = 0;
    }

    /**
     * Verifica si la lista no contiene elementos.
     * @return true si la lista está vacía, false en caso contrario.
     * @pre La lista ha sido inicializada.
     * @post Devuelve un booleano indicando si la lista está vacía, sin modificarla.
     */
    public boolean isEmpty() {
        return contador == 0;
        // Alternativamente: return ultimo == null;
    }

    /**
     * Devuelve el número de elementos en la lista.
     * @return El tamaño actual de la lista.
     * @pre La lista ha sido inicializada.
     * @post Devuelve el valor del contador sin modificar la lista.
     */
    public int size() {
        return contador;
    }

    /**
     * Añade un elemento al principio de la lista.
     * @param dato El elemento a añadir.
     * @pre La lista ha sido inicializada. 'dato' es del tipo E.
     * @post El 'dato' se añade como nuevo primer elemento. El tamaño ('contador')
     * se incrementa. Si estaba vacía, 'ultimo' apunta al nuevo nodo y este
     * se apunta a sí mismo. Si no, se ajustan los punteros 'siguiente' del
     * 'ultimo' nodo y del nuevo nodo.
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
     * Añade un elemento al final de la lista.
     * @param dato El elemento a añadir.
     * @pre La lista ha sido inicializada. 'dato' es del tipo E.
     * @post El 'dato' se añade como nuevo último elemento. El tamaño ('contador')
     * se incrementa. Si estaba vacía, 'ultimo' apunta al nuevo nodo y este
     * se apunta a sí mismo. Si no, se ajustan los punteros 'siguiente' del
     * antiguo 'ultimo' y del nuevo nodo, y se actualiza 'ultimo'.
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
     * Elimina y devuelve el primer elemento de la lista.
     * @return El primer elemento eliminado.
     * @throws NoSuchElementException si la lista está vacía.
     * @pre La lista ha sido inicializada.
     * @post Si no está vacía, se elimina el primer nodo (apuntado por ultimo.getSiguiente()).
     * El tamaño ('contador') decrece. Se devuelve el dato eliminado.
     * Si solo había un elemento, 'ultimo' se vuelve null. Si había más,
     * 'ultimo' apunta su 'siguiente' al segundo nodo original.
     * Si está vacía, lanza NoSuchElementException.
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
     * Elimina y devuelve el último elemento de la lista.
     * Requiere recorrer la lista para encontrar el penúltimo nodo (O(n)).
     * @return El último elemento eliminado.
     * @throws NoSuchElementException si la lista está vacía.
     * @pre La lista ha sido inicializada.
     * @post Si no está vacía, se elimina el último nodo ('ultimo'). El tamaño ('contador')
     * decrece. Se devuelve el dato eliminado. Si solo había un elemento, 'ultimo'
     * se vuelve null. Si había más, se localiza el penúltimo nodo, se actualiza
     * 'ultimo' a este penúltimo y se ajusta su puntero 'siguiente' para que
     * apunte a la cabeza. Si está vacía, lanza NoSuchElementException.
     */
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("La lista está vacía.");
        }
        E datoEliminado = ultimo.getDato();

        if (contador == 1) { // O if (ultimo.getSiguiente() == ultimo)
            ultimo = null;
        } else {
            // Encontrar el penúltimo nodo
            NodoSimple<E> penultimo = ultimo; // Empezar búsqueda desde uno antes conceptualmente
            // Iteramos contador-1 veces desde la cabeza para llegar al penúltimo
            NodoSimple<E> actual = ultimo.getSiguiente(); // Cabeza
            for(int i = 0; i < contador - 2; i++) { // Llegar al nodo ANTES del último
                 actual = actual.getSiguiente();
            }
            penultimo = actual; // Este es el penúltimo
            penultimo.setSiguiente(ultimo.getSiguiente()); // Penúltimo apunta a la cabeza
            ultimo = penultimo; // Actualizar último
        }
        contador--;
        return datoEliminado;
    }

    /**
     * Comprueba si la lista contiene el elemento especificado.
     * Usa el método equals() para la comparación.
     * @param dato El elemento a buscar.
     * @return true si el elemento está en la lista, false en caso contrario.
     * @pre La lista ha sido inicializada.
     * @post Devuelve un booleano indicando la presencia del elemento, sin modificar la lista.
     * Rendimiento O(n).
     */
    public boolean contains(E dato) {
        if (isEmpty()) {
            return false;
        }
        NodoSimple<E> actual = ultimo.getSiguiente(); // Empezar en la cabeza
        for (int i = 0; i < contador; i++) {
            // Manejo cuidadoso de nulls
            if (dato == null) {
                if (actual.getDato() == null) {
                    return true;
                }
            } else {
                if (dato.equals(actual.getDato())) {
                    return true;
                }
            }
            actual = actual.getSiguiente();
        }
        return false; // No encontrado después de recorrer
    }

    /**
     * Obtiene el elemento en la posición especificada (índice).
     * @param index Índice del elemento a obtener (base 0).
     * @return El elemento en la posición dada.
     * @throws IndexOutOfBoundsException si el índice es inválido (index < 0 || index >= size()).
     * @pre La lista ha sido inicializada. El índice es un entero.
     * @post Devuelve el elemento en la posición 'index' sin modificar la lista.
     * Lanza IndexOutOfBoundsException si el índice no es válido. Rendimiento O(n).
     */
    public E getElement(int index) {
        if (index < 0 || index >= contador) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index + ", Tamaño: " + contador);
        }
        if (isEmpty()) {
             // Esta condición es redundante debido a la validación anterior si index >= 0
             // pero se mantiene por claridad si se pensara en índices negativos.
             throw new IndexOutOfBoundsException("Índice " + index + " inválido para lista vacía.");
        }

        NodoSimple<E> actual = ultimo.getSiguiente(); // Empezar en la cabeza
        for (int i = 0; i < index; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    /**
     * Elimina todos los elementos de la lista, dejándola vacía.
     * @pre La lista ha sido inicializada.
     * @post La lista queda vacía. 'ultimo' es null, 'contador' es 0.
     * Los nodos anteriores quedan disponibles para el recolector de basura.
     */
    public void clear() {
        ultimo = null;
        contador = 0;
        // No es estrictamente necesario romper todos los enlaces en Java
        // gracias al recolector de basura, pero establecer ultimo a null es clave.
    }

    /**
     * Devuelve una representación en String de la lista circular.
     * Muestra los elementos desde la cabeza hasta el último.
     * Formato: [elemento1, elemento2, ..., elementoN]
     * @return La representación textual de la lista.
     * @pre La lista ha sido inicializada.
     * @post Devuelve una cadena representando la lista sin modificarla. O(n).
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        NodoSimple<E> actual = ultimo.getSiguiente(); // Empezar en la cabeza
        for (int i = 0; i < contador; i++) {
            sb.append(actual.getDato() == null ? "null" : actual.getDato().toString());
            if (i < contador - 1) { // Añadir coma excepto después del último
                sb.append(", ");
            }
            actual = actual.getSiguiente();
        }
        sb.append("]");
        return sb.toString();
    }

     // --- Ejemplo de uso (Método main) ---
    
    }
