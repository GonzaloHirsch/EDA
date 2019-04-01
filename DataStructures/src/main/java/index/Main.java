package index;

public class Main {

    public static void main(String[] args){
        int[] items = {10,40,30,60,80,100};
        Index index = new Index(items);
        System.out.println(index.search(0));
    }
}
