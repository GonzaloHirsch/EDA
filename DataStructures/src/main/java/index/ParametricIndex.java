package index;

import java.util.Arrays;

public class ParametricIndex<T extends Comparable<? super T>>{

    private T[] indexedData;
    private int dim;

    public ParametricIndex(T[] data){
        if (data == null)
            throw new RuntimeException("Create a data collection");

        sorting.MergeSort.sort(data);
        this.indexedData = data;
        this.dim = indexedData.length;
    }

    /**
     * Searches the indexed array for the key item, using the binary search algorithm
     * @param key item to search
     * @return position of the item if present in the array, -1 if not
     */
    public int search(T key){
        if (dim == 0)
            return -1;
        return arrays.Search.search(this.indexedData, key, 0, dim - 1);
    }

    /**
     * Inserts the key into the array.
     * If the item already exists, it does not insert it
     * @param key item to be inserted
     */
    public void insert(T key){
        this.indexedData = arrays.Insert.insert(indexedData, key);
        if (this.indexedData.length != dim)
            this.dim++;
    }

    /**
     * Prints the array to the standard output
     */
    public void printArray(){
        for (int i = 0; i < indexedData.length; i++){
            System.out.print(indexedData[i] + "\t");
        }
        System.out.print("\n");
    }

    /**
     * Getter for the array
     * @return array with the data
     */
    public T[] getArray(){
        return this.indexedData;
    }

    /**
     * Deletes the key from the data.
     * If the key is not present, it does not delete it.
     * @param key item to delete
     * @return boolean representing if the item was deleted successfully
     */
    public boolean delete(T key){
        this.indexedData = arrays.Delete.delete(indexedData, key);
        if (this.indexedData.length != dim){
            this.dim--;
            return true;
        }
        return false;
    }

}
