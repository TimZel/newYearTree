package org.example;

class Node <T> {
    private int value; // значение узла
    private Node leftChild; // Левый узел-потомок
    private Node rightChild; // Правый узел-потомок

    public int getValue() {
        return this.value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public void setLeftChild(final Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return this.rightChild;
    }
    public void setRightChild(final Node rightChild) {
        this.rightChild = rightChild;
    }

}