package binarytree;

public class BinaryTree {

    private Node head;
    private Node current;

    public BinaryTree(){
        this.head = null;
        this.current = null;
    }

    public BinaryTree(Node node){
        this.head = node;
        this.current = null;
    }

    public Node getCurrent() {
        return current;
    }

    public void setCurrent(Node current) {
        this.current = current;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }
}


