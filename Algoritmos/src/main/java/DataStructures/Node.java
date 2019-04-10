package DataStructures;

public class Node<T extends Comparable<? super T>>{
    private Node<T> next;
    private T value;

    public Node(){

    }

    public Node(T value, Node<T> next){
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}