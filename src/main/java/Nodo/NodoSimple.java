package Nodo; // O el paquete que prefieras

/**
 * Representa un nodo para una lista simplemente enlazada (circular o no).
 * Contiene el dato y una referencia al nodo siguiente.
 *
 * @param <E> El tipo del dato almacenado en el nodo.
 */
public class NodoSimple<E> {

    private E dato;               // El dato almacenado.
    private NodoSimple<E> siguiente; // Referencia al nodo siguiente.

    /**
     * Constructor para crear un nodo con un dato específico.
     * El enlace siguiente se inicializa a null.
     *
     * @param dato El dato a almacenar en el nodo.
     * @pre El dato proporcionado es del tipo E.
     * @post Se crea una instancia de NodoSimple con el dato y 'siguiente' en null.
     */
    public NodoSimple(E dato) {
        this.dato = dato;
        this.siguiente = null; // Se establecerá la circularidad en la clase LESC
    }

    /**
     * Obtiene el dato almacenado en el nodo.
     * @return El dato del nodo.
     * @pre El nodo ha sido inicializado.
     * @post Devuelve el valor del atributo 'dato'.
     */
    public E getDato() {
        return dato;
    }

    /**
     * Establece o actualiza el dato del nodo.
     * @param dato El nuevo dato para el nodo.
     * @pre El nodo ha sido inicializado.
     * @post El atributo 'dato' se actualiza.
     */
    public void setDato(E dato) {
        this.dato = dato;
    }

    /**
     * Obtiene la referencia al nodo siguiente.
     * @return El nodo siguiente, o null si es el último (antes de enlazar circularmente).
     * @pre El nodo ha sido inicializado.
     * @post Devuelve la referencia al nodo siguiente.
     */
    public NodoSimple<E> getSiguiente() {
        return siguiente;
    }

    /**
     * Establece la referencia al nodo siguiente.
     * @param siguiente El nodo que será el siguiente.
     * @pre El nodo ha sido inicializado.
     * @post El puntero 'siguiente' se actualiza.
     */
    public void setSiguiente(NodoSimple<E> siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * Devuelve la representación en String del dato del nodo.
     * @return String que representa el dato, o "null" si el dato es null.
     * @pre El nodo ha sido inicializado.
     * @post Devuelve la representación textual del dato.
     */
    @Override
    public String toString() {
        return dato != null ? dato.toString() : "null";
    }
}
