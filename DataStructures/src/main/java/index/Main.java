package index;

public class Main {

    public static void main(String[] args){
        int[] items = {10,40,30,60,80,100};
        Index index = new Index(items);
        //System.out.println(index.search(0));
        index.insert(20);
        System.out.println(index.search(20));
        index.printArray();
        index.insert(50);
        System.out.println(index.search(50));
        index.printArray();
        System.out.println(index.delete(80));
        System.out.println(index.search(80));
        index.printArray();
    }
}
