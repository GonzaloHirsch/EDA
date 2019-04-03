package arrays;

public class Move {

    /**
     * Move all the items to the right one position
     * @param arr array with data
     * @param startPosition start position of items to be moved
     * @return the array with the new positions
     */
    public static int[] shiftItemsRight(int[] arr, int startPosition){
        for (int i = arr.length - 2; startPosition <= i; i--){
            arr[i + 1] = arr[i];
        }
        return arr;
    }

    /**
     * Move all the items to the left one position
     * @param arr array with data
     * @param startPosition start position of items to be moved
     * @return the array with the new positions
     */
    public static int[] shiftItemsLeft(int[] arr, int startPosition){
        for (int i = startPosition + 1; i < arr.length; i++){
            arr[i - 1] = arr[i];
        }
        return arr;
    }
}
