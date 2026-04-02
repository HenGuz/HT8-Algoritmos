package com.template;

import java.util.Vector;

/**
 * Implementación de una cola de prioridad usando un heap almacenado en un Vector.
 * @param <E> Tipo de elementos, debe ser Comparable.
 */
public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    private Vector<E> heap; // Almacena el heap

    public VectorHeap() {
        heap = new Vector<>();
    }

    /**
     * Constructor que recibe un Vector inicial y lo convierte en heap.
     * @param v Vector inicial.
     */
    public VectorHeap(Vector<E> v) {
        heap = new Vector<>(v);
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }

    /**
     * Subir un elemento en el heap.
     * @param indice Posición del elemento.
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
     * Bajar un elemento en el heap.
     * @param indice Posición del elemento.
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
     * Intercambia dos elementos en el heap.
     */
    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    @Override
    public void add(E elemento) {
        heap.add(elemento);
        percolateUp(heap.size() - 1);
    }

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

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return heap.get(0);
    }
}