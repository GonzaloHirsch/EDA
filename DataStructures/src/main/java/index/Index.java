package index;

import search.ArraySearch;

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
        return ArraySearch.search(this.indexedData, key, 0, dim - 1);
    }
}