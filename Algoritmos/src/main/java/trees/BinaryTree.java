package trees;

import java.util.*;

public class BinaryTree<T extends Comparable<? super T>> implements BSTreeInterface<T>, Iterable<T>{

    /**
     * Root node of the tree
     */
    private Node root;

    private ITERATOR_TYPE type = ITERATOR_TYPE.LEVELS;

    ///CONSTRUCTORS---------------------------------------------

    /**
     * Empty constructor for easy class instances
     */
    public BinaryTree(){
    }

    /**
     * Constructor with a node
     * @param root of the tree
     */
    public BinaryTree(Node root){
        this.root = root;
    }

    ///GETTERS----------------------------------------------------

    @Override
    public Node getRoot(){
        return this.root;
    }

    ///PROPERTIES-------------------------------------------------

    public int getHeight(){
        return getHeightRec(root);
    }

    private int getHeightRec(Node node){
        if (node == null)
            return -1;
        else
            return Integer.max(getHeightRec((Node)node.getLeft()) + 1, getHeightRec((Node)node.getRight()) + 1);
    }

    ///BALANCING--------------------------------------------------

    public boolean testAVL(){
        return testAVLRec(root);
    }

    private boolean testAVLRec(Node node){
        int left;
        int right;
        if (node == null)
            return true;
        left = getHeightRec((Node)node.getLeft());
        right = getHeightRec((Node)node.getRight());
        int dif = Math.abs(left - right);
        if (dif > 1)
            return false;
        else
            return testAVLRec((Node)node.getLeft()) && testAVLRec((Node)node.getRight());
    }

    ///PRINTING---------------------------------------------------

    @Override
    public void inOrder(){
        inOrderRec(root);
        System.out.print("\n");
    }

    private void inOrderRec(Node node){
        if (node.getLeft() != null)
            inOrderRec((Node)node.getLeft());
        System.out.print(node.getData());
        System.out.print(" ");
        if (node.getRight() != null)
            inOrderRec((Node)node.getRight());
    }

    @Override
    public void postOrder(){
        postOrderRec(root);
        System.out.print("\n");
    }

    private void postOrderRec(Node node){
        if (node.getLeft() != null)
            postOrderRec((Node)node.getLeft());
        if (node.getRight() != null)
            postOrderRec((Node)node.getRight());
        System.out.print(node.getData());
        System.out.print(" ");
    }

    @Override
    public void preOrder(){
        preOrderRec(root);
        System.out.print("\n");
    }

    private void preOrderRec(Node node){
        System.out.print(node.getData());
        System.out.print(" ");
        if (node.getLeft() != null)
            preOrderRec((Node)node.getLeft());
        if (node.getRight() != null)
            preOrderRec((Node)node.getRight());
    }

    ///INSERTION----------------------------------------------------

    /**
     * Public method to insert an element in the tree
     * @param elem to be inserted
     */
    @Override
    public void insert(T elem){
        if (root == null)
            root = new Node(elem);
        else
            insertRec(root, elem);
    }

    /**
     * Recursive insertion method
     * Used by the public insertion method to insert an element
     * @param node that is being iterated
     * @param elem to be inserted
     */
    private void insertRec(Node node, T elem){
        if (node.getData().compareTo(elem) >= 0)
            if (node.getLeft() == null)
                node.left = new Node(elem);
            else
                insertRec((Node)node.getLeft(), elem);
        else
            if (node.getRight() == null)
                node.right = new Node(elem);
            else
                insertRec((Node)node.getRight(), elem);
    }

    ///SEARCH--------------------------------------------

    /**
     * Public method to check if the tree contains an element
     * @param elem to be searched
     * @return a boolean representing if the element is contained
     */
    public NodeTreeInterface containsNode(T elem){
        if (root == null)
            return null;
        else
            return containsNodeRec(root, elem);
    }

    /**
     * Recursive search method
     * Called by the public contains method
     * @param node being iterated
     * @param elem to be searched
     * @return a boolean representing if the element is contained
     */
    private NodeTreeInterface containsNodeRec(NodeTreeInterface<T> node, T elem){
        //Store the value of the comparison
        int comparison = node.getData().compareTo(elem);
        if (node == null)
            return null;
        else if (comparison == 0)
            return node;
        else if (comparison > 0)
            return containsNodeRec(node.getLeft(), elem);
        else
            return containsNodeRec(node.getRight(), elem);
    }

    /**
     * Public method to check if the tree contains an element
     * @param elem to be searched
     * @return a boolean representing if the element is contained
     */
    @Override
    public boolean contains(T elem){
        if (root == null)
            return false;
        else
            return containsRec(root, elem);
    }

    /**
     * Recursive search method
     * Called by the public contains method
     * @param node being iterated
     * @param elem to be searched
     * @return a boolean representing if the element is contained
     */
    private boolean containsRec(NodeTreeInterface<T> node, T elem){
        //Store the value of the comparison
        int comparison = node.getData().compareTo(elem);
        if (node == null)
            return false;
        else if (comparison == 0)
            return true;
        else if (comparison > 0)
            return containsRec(node.getLeft(), elem);
        else
            return containsRec(node.getRight(), elem);
    }

    ///PRINTING------------------------------------------------------

    @Override
    public void printByLevels(){
        Deque<NodeTreeInterface<T>> level = new ArrayDeque<>();
        NodeTreeInterface<T> actual;
        if (root != null){
            level.push(root);
            while(level.size() > 0){
                actual = level.pop();
                System.out.print(actual.getData() + " ");
                if (actual.getLeft() != null)
                    level.add(actual.getLeft());
                if (actual.getRight() != null)
                    level.add(actual.getRight());
            }
        }
        System.out.println();
    }

    //Alternative method
    /*
    List<NodeTreeInterface<T>> level = new ArrayList<>();
        List<NodeTreeInterface<T>> aux = new ArrayList<>();
        if (root != null){
            level.add(root);
            while(level.size() != 0){
                for (NodeTreeInterface<T> node: level) {
                    System.out.println(node.getData());
                    if (node.getLeft() != null)
                        aux.add(node.getLeft());
                    if (node.getRight() != null)
                        aux.add(node.getRight());
                }
                level.clear();
                level = aux;
                aux = new ArrayList<>();
            }
        }
     */

    ///DELETION------------------------------------------------------

    @Override
    public void delete(T elem) {
        if (contains(elem))
            nodeDelete(root, elem);
    }

    private NodeTreeInterface<T> nodeDelete(NodeTreeInterface<T> node, T elem){
        //Store the value of the comparison
        int comparison = node.getData().compareTo(elem);
        if (comparison == 0){
            if (node.getLeft() == null && node.getRight() == null)
                return null;
            if (node.getRight() == null)
                return node.getLeft();
            if (node.getLeft() == null)
                return node.getRight();
            NodeTreeInterface<T> replacement = adjacent(node.getLeft());
            node.setData(replacement.getData());
            node.setLeft(replacement.getLeft());
        }
        /*
            return true;
        else if (comparison > 0)
            return containsRec(node.getLeft(), elem);
        else
            return containsRec(node.getRight(), elem);

         */
        return null;
    }

    private NodeTreeInterface<T> adjacent(NodeTreeInterface<T> node){
        if (node.getRight() != null)
            return adjacent(node.getRight());
        return node;
    }

    //ITERATORS--------------------------------------------------------------

    public enum ITERATOR_TYPE{
        PREORDER,
        POSTORDER,
        INORDER,
        LEVELS
    }

    public void setIteratorType(ITERATOR_TYPE type){
        this.type = type;
    }

    @Override
    public Iterator<T> iterator() {
        switch (this.type){
            case PREORDER:
                return new BSTIteratorPreOrder();
            case INORDER:
                return new BSTIteratorInOrder();
            case POSTORDER:
                return new BSTIteratorPostOrder();
            default:
                return new BSTIterator();
        }
    }

    private class BSTIterator implements Iterator<T>{

        private Deque<NodeTreeInterface<T>> nodes;

        public BSTIterator(){
            nodes = new ArrayDeque<>();
            if (root != null)
                nodes.add(root);
        }

        @Override
        public boolean hasNext() {
            return nodes.peekFirst() != null;
        }

        @Override
        public T next() {
            NodeTreeInterface<T> node = nodes.pop();
            if (node.getLeft() != null)
                nodes.add(node.getLeft());
            if (node.getRight() != null)
                nodes.add(node.getRight());
            return node.getData();
        }
    }

    private class BSTIteratorInOrder implements Iterator<T>{

        private Deque<T> nodes;

        public BSTIteratorInOrder(){
            nodes = new ArrayDeque<>();
            createDeque(root);
        }

        private void createDeque(Node node){
            if (node.getLeft() != null)
                createDeque((Node)node.getLeft());
            nodes.add(node.getData());
            if (node.getRight() != null)
                createDeque((Node)node.getRight());
        }

        @Override
        public boolean hasNext() {
            return nodes.isEmpty();
        }

        @Override
        public T next() {
            return nodes.pop();
        }
    }

    private class BSTIteratorPreOrder implements Iterator<T>{

        private Deque<T> nodes;

        public BSTIteratorPreOrder(){
            nodes = new ArrayDeque<>();
            createDeque(root);
        }

        private void createDeque(Node node){
            nodes.add(node.getData());
            if (node.getLeft() != null)
                createDeque((Node)node.getLeft());
            if (node.getRight() != null)
                createDeque((Node)node.getRight());
        }

        @Override
        public boolean hasNext() {
            return nodes.isEmpty();
        }

        @Override
        public T next() {
            return nodes.pop();
        }
    }

    private class BSTIteratorPostOrder implements Iterator<T>{

        private Deque<T> nodes;

        public BSTIteratorPostOrder(){
            nodes = new ArrayDeque<>();
            createDeque(root);
        }

        private void createDeque(Node node){
            if (node.getLeft() != null)
                createDeque((Node)node.getLeft());
            if (node.getRight() != null)
                createDeque((Node)node.getRight());
            nodes.add(node.getData());
        }

        @Override
        public boolean hasNext() {
            return nodes.isEmpty();
        }

        @Override
        public T next() {
            return nodes.pop();
        }
    }

    private class Node implements NodeTreeInterface<T>{
        private T value;
        private Node left;
        private Node right;

        public Node(){
        }

        public Node(T value){
            this.value = value;
        }

        public Node(T value, Node left, Node right){
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public T getData() {
            return value;
        }

        @Override
        public NodeTreeInterface<T> getLeft() {
            return left;
        }

        @Override
        public NodeTreeInterface<T> getRight() {
            return right;
        }

        @Override
        public void setData(T elem) {
            this.value = elem;
        }

        @Override
        public void setLeft(NodeTreeInterface<T> node) {
            this.left = (Node)node;
        }

        @Override
        public void setRight(NodeTreeInterface<T> node) {
            this.right = (Node)node;
        }
    }
}
