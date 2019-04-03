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

        Integer[] dataI = new Integer[]{};
        ParametricIndex<Integer> myIndex = new ParametricIndex<>(dataI);
        int searchKey = 50;
        int pos = myIndex.search(searchKey);
        System.out.println(String.format("%d en pos %d", searchKey, pos));
        myIndex.insert(searchKey);
        pos = myIndex.search(searchKey);
        System.out.println(String.format("%d en pos %d", searchKey, pos));
        myIndex.printArray();

        String[] dataS = new String[]{"hola", "34", "chau"};
        ParametricIndex<String> myIndexS = new ParametricIndex<>(dataS);
        String stringKey = "al";
        pos = myIndexS.search(stringKey);
        System.out.println(String.format("%s en pos %d", stringKey, pos));
        myIndexS.insert(stringKey);
        pos = myIndexS.search(stringKey);
        System.out.println(String.format("%s en pos %d", stringKey, pos));
        myIndexS.printArray();
    }
}
