package binarytree;

public class Node<T extends Comparable<? super T>> {
    private Node head;
    private Node left;
    private Node right;
    private T value;

    public Node(){
        this.head = null;
        this.left = null;
        this.right = null;
        this.value = null;
    }

    public Node(Node head){
        this.head = head;
        this.left = null;
        this.right = null;
        this.value = null;
    }

    public Node(Node head, T value){
        this.head = head;
        this.left = null;
        this.right = null;
        this.value = value;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}