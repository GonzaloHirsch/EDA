package trees;

public class Test {

    public static void main(String[] args){
        BinaryTree<Integer> myTree = new BinaryTree<>();
        myTree.insert(60);
        myTree.insert(50);
        myTree.insert(80);
        myTree.insert(30);
        //myTree.insert(55);
        myTree.insert(70);
        myTree.insert(40);
        myTree.insert(20);

        System.out.println(myTree.testAVL() ? "YES" : "NO");
    }
}
