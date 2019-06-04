package trees;

public class Test {

    public static void main(String[] args){
        /*
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
        */

        BTree<Integer> st = new BTree<>(2);
        Integer[] nums = {0, 8, 109, 220, 222, 241, 149, 107, 75, 248, 254, 140, 16, 66, 74, 21, 211, 47, 80, 242};
        for (int rec = 0; rec < nums.length; rec++){
            st.add(nums[rec]);
            System.out.println(st.toString());
            System.out.println("....");
        }
        System.out.println("........................................................................................");
        st.remove(66);
        System.out.println(st.toString());
    }
}

