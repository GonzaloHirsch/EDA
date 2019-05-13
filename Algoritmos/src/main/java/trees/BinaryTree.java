package trees;

public class BinaryTree<T extends Comparable<? super T>> {

    private Node<T> root;

    public BinaryTree(){
    }

    public BinaryTree(Node<T> root){
        this.root = root;
    }

    private class Node<T>{
        private T value;
        private Node<T> left;
        private Node<T> right;

        public Node(){
        }

        public Node(T value){
            this.value = value;
        }

        public Node(T value, Node<T> left, Node<T> right){
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
