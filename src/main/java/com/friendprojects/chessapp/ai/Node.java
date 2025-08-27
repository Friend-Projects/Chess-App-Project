package com.friendprojects.chessapp.ai;

public class Node<T> {

    private final T item;
    private final double cost;

    public Node(T item, double cost) {
        this.item = item;
        this.cost = cost;
    }

    public T getItem() {
        return this.item;
    }

    public double getCost() {
        return this.cost;
    }
}
