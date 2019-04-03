package index;

import java.util.Arrays;

public class Index {

    private int[] indexedData;
    private int dim;

    public Index(int[] unsortedData){
        if (unsortedData == null)
            throw new RuntimeException("Create a data collectio");

        Arrays.sort(unsortedData);
        this.indexedData = unsortedData;
        this.dim = this.indexedData.length;
    }

    /**
     * Searches the indexed array for the key item, using the binary search algorithm
     * @param key item to search
     * @return position of the item if present in the array, -1 if not
     */
    public int search(int key){
        return arrays.Search.search(this.indexedData, key, 0, dim - 1);
    }

    public void insert(int key){
        this.indexedData = arrays.Insert.insert(indexedData, key);
        if (this.indexedData.length != dim)
            this.dim++;
    }

    public void printArray(){
        for (int i = 0; i < indexedData.length; i++){
            System.out.print(indexedData[i] + "\t");
        }
        System.out.print("\n");
    }

    public boolean delete(int key){
        this.indexedData = arrays.Delete.delete(indexedData, key);
        if (this.indexedData.length != dim){
            this.dim--;
            return true;
        }
        return false;
    }
}