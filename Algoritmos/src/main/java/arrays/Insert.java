package arrays;

import java.util.Arrays;

public class Insert {

    public static int[] insert(int[] arr, int item){
        //Search for the element in the array to see if it's repeated
        int position = Search.search(arr, item, 0, arr.length - 1);
        //If the item does not exist, it
        if (position == -1){
            position = Search.determineNewItemPosition(arr, item, 0, arr.length - 1);
            arr = Arrays.copyOf(arr, arr.length + 1);
            arr = Move.shiftItemsRight(arr, position);
            arr[position] = item;
            return arr;
        } else {
            return arr;
        }
    }
}
