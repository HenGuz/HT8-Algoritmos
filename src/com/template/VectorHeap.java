package com.template;

import java.util.Vector;

/**
 * Clase que implementa una cola con prioridad que está dentro de un vector.
 * @param <E> Tipo de elemento guardado, que debe poder ser comparables entre sí.
 * @author Henry Guzmán
 * @version 2.0
 * @since 2026-04-02
 */

public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E> {

	/**
	 * Estructura interna del almacenamiento de los datos.
	 */
    private Vector<E> heap;

    /**
     * Constructor principal de la clase.
     */
    public VectorHeap() {
        heap = new Vector<>();
    }

    /**
     * Constructor que usa un vector inicial y lo convierte en una cola con prioridad.
     * @param v Vector inicial a usar como base de datos.
     */
    public VectorHeap(Vector<E> v) {
        heap = new Vector<>(v);
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }

    /**
     * Método para subir un elemento a la cola con prioridad, usado luego para insertar un elemento en la última posición del vector.
     * @param indice Posición del elemento que se debe subir.
     */
    protected void percolateUp(int indice) {
        while (indice > 0) {
            int padre = (indice - 1) / 2;
            if (heap.get(indice).compareTo(heap.get(padre)) >= 0) break;
            swap(indice, padre);
            indice = padre;
        }
    }

    /**
     * Método para bajar un elemento en la cola con prioridad, usado luego de eliminar la raíz del vector y colocar el último elemento en la mayor altura posible.
     * @param indice Posición del elemento que se debe bajar.
     */
    protected void percolateDown(int indice) {
        int hijo;
        while ((hijo = 2 * indice + 1) < heap.size()) {
            if (hijo + 1 < heap.size() && heap.get(hijo + 1).compareTo(heap.get(hijo)) < 0) {
                hijo++;
            }
            if (heap.get(indice).compareTo(heap.get(hijo)) <= 0) break;
            swap(indice, hijo);
            indice = hijo;
        }
    }

    /**
     * Método para intercambiar dos elementos dentro del vector.
     * @param i Índice del primer elemento a intercambiar.
     * @param j Índice del segundo elemento a intercambiar.
     */
    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
	 * Método para añadir un elemento nuevo a la cola con prioridad.
	 * @param elemento Elemento a agregar a la cola.
	 */
    @Override
    public void add(E elemento) {
        heap.add(elemento);
        percolateUp(heap.size() - 1);
    }

    /**
     * Método para eliminar el elemento de mayor prioridad en la cola.
     * @return Elemento de mayor prioridad en la cola.
     * @throws Error que indica que la cola actualmente esta vacía (se necesita al menos un elemento para ejecutar el método).
     */
    @Override
    public E remove() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola de prioridad está vacía");
        }
        E min = heap.get(0);
        E last = heap.remove(heap.size() - 1);
        if (!isEmpty()) {
            heap.set(0, last);
            percolateDown(0);
        }
        return min;
    }

    /**
     * Método que verifica si la cola con prioridad está vacía.
     * @return Devuelve un valor booleano que indica si la cola esta vacía (True si lo esta).
     */
    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Método para devolver la cantidad de elementos en la cola con prioridad.
     * @return Número de elementos dentro de la cola con prioridad.
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Método para devolver el elemento de mayor prioridad en la cola sin eliminarlo.
     * @return Elemento de mayor prioridad en la cola.
     */
    @Override
    public E peek() {
        if (isEmpty()) return null;
        return heap.get(0);
    }
}