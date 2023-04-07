package ru.academits.kozhevnikov.tree;

class Node<T extends Comparable<T>> {
    private final T data;
    private Node<T> left;
    private Node<T> right;

    public Node(T value) {
        this.data = value;
    }

    public T getData() {
        return data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }
}
