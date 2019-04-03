package arrays;

import java.util.Arrays;

public class Delete {

    public static int[] delete(int[] arr, int item){
        //Search for the element in the array to see if it's repeated
        int position = Search.search(arr, item, 0, arr.length - 1);
        //If the item does not exist, it
        if (position != -1){
            arr = Move.shiftItemsLeft(arr, position);
            arr = Arrays.copyOf(arr, arr.length - 1);
            return arr;
        } else {
            return arr;
        }
    }

    public static <T extends Comparable<? super T>> T[] delete(T[] arr, T item){
        //Search for the element in the array to see if it's repeated
        int position = Search.search(arr, item, 0, arr.length - 1);
        //If the item does not exist, it
        if (position != -1){
            arr = Move.shiftItemsLeft(arr, position);
            arr = Arrays.copyOf(arr, arr.length - 1);
            return arr;
        } else {
            return arr;
        }
    }

}
