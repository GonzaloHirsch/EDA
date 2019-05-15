package parsing;

import trees.BinaryTree;

public class Test {

    public static void main(String[] args){
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.insert(50);
        tree.insert(60);
        tree.insert(80);
        tree.insert(20);
        tree.insert(70);
        tree.insert(40);
        tree.insert(44);
        tree.insert(10);
        tree.insert(40);

        System.out.println("In order");
        tree.inOrder();
        System.out.println("Pre order");
        tree.preOrder();
        System.out.println("Post order");
        tree.postOrder();

        System.out.println("Height");
        System.out.println(tree.getHeight());

        ExpTree exp = new ExpTree("( ( ( 5 * 5 ) - ( 3 + 5 ) ) * 4 )");
        System.out.println("In order: " + exp.inOrder());
        System.out.println("Pre order: " + exp.preOrder());
        System.out.println("Post order: " + exp.postOrder());
        System.out.println("Value: " + exp.evaluate());
    }
}
