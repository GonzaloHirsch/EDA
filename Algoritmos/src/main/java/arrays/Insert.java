package arrays;

import java.util.Arrays;

public class Insert {

    public static int[] insert(int[] arr, int item){
        int position;
        if (arr.length == 0) {
            position = -1;
        }
        else{
            //Search for the element in the array to see if it's repeated
            position = Search.search(arr, item, 0, arr.length - 1);
        }
        //If the item does not exist, it
        if (position == -1){
            if (arr.length == 0){
                position = 0;
            } else {
                position = Search.determineNewItemPosition(arr, item, 0, arr.length - 1);
            }
            arr = Arrays.copyOf(arr, arr.length + 1);
            arr = Move.shiftItemsRight(arr, position);
            arr[position] = item;
            return arr;
        } else {
            return arr;
        }
    }

    public static <T extends Comparable<? super T>> T[] insert(T[] arr, T item){
        int position;
        if (arr.length == 0) {
            position = -1;
        }
        else{
            //Search for the element in the array to see if it's repeated
            position = Search.search(arr, item, 0, arr.length - 1);
        }
        //If the item does not exist, it
        if (position == -1){
            if (arr.length == 0){
                position = 0;
            } else {
                position = Search.determineNewItemPosition(arr, item, 0, arr.length - 1);
            }
            arr = Arrays.copyOf(arr, arr.length + 1);
            arr = Move.shiftItemsRight(arr, position);
            arr[position] = item;
            return arr;
        } else {
            return arr;
        }
    }
}
