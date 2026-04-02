package com.template;

public interface PriorityQueue<E extends Comparable<E>> {
    void add(E elemento);
    E remove();
    boolean isEmpty();
    int size();
    E peek();
}