package com.ionutciuta.datastructures;

public class Node<T> {
    private final T value;
    private boolean explored;
    private int dist;

    public Node(T value) {
        this.value = value;
        this.dist = 0;
        this.explored = false;
    }

    public T getValue() {
        return value;
    }

    public boolean isExplored() {
        return explored;
    }

    public int getDist() {
        return dist;
    }

    public void explored() {
        this.explored = true;
    }

    public void incDist() {
        this.dist = this.dist + 1;
    }
}
