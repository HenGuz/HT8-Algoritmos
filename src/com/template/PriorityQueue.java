package com.template;

/**
 * Interfaz para definir las operaciones básica de una cola con prioridad.
 * @para <E> Tipo de elementos almacenados en la cola con prioridad, deben ser comparables.
 * @author Henry Guzmán
 * @version 2.0
 * @since 2026-04-02
 */

public interface PriorityQueue<E extends Comparable<E>> {
	/**
	 * Método para añadir un elemento nuevo a la cola con prioridad.
	 * @param elemento Elemento a agregar a la cola.
	 */
    void add(E elemento);
    
    /**
     * Método para eliminar el elemento de mayor prioridad en la cola.
     * @return Elemento de mayor prioridad en la cola.
     */
    E remove();
    
    /**
     * Método que verifica si la cola con prioridad está vacía.
     * @return Devuelve un valor booleano que indica si la cola esta vacía (True si lo esta).
     */
    boolean isEmpty();
    
    /**
     * Método para devolver la cantidad de elementos en la cola con prioridad.
     * @return Número de elementos dentro de la cola con prioridad.
     */
    int size();
    
    /**
     * Método para devolver el elemento de mayor prioridad en la cola sin eliminarlo.
     * @return Elemento de mayor prioridad en la cola.
     */
    E peek();
}