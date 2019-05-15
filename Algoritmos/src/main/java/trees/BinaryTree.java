package trees;

public class BinaryTree<T extends Comparable<? super T>> implements BSTreeInterface<T>{

    /**
     * Root node of the tree
     */
    private Node root;

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
            return true;
        else if (comparison > 0)
            return containsRec(node.getLeft(), elem);
        else
            return containsRec(node.getRight(), elem);
    }

    private NodeTreeInterface<T> adjacent(NodeTreeInterface<T> node){
        if (node.getRight() != null)
            return adjacent(node.getRight());
        return node;
    }

    private NodeTreeInterface<T> deleteRec(Node node, T elem){
        if ()
        if (node == null)
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
    }
}
