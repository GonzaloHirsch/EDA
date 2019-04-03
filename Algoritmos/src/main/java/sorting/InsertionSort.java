package sorting;

public class InsertionSort {

    /**
     * Sorts the array with the natural order del tipo
     * @param arr array with the data
     * @param <T> generic type
     * @return sorted array
     */
    public static <T extends Comparable<? super T>> T[] sort(T[] arr){
        int comparison;
        for (int i = 1; i < arr.length; i++){
            comparison = arr[i].compareTo(arr[i - 1]);
            //arr[i] < arr[i - 1]
            //Make the swapping
            if (comparison < 0){
                shiftAndReplace(arr, arr[i], i);
            }
        }
        return arr;
    }

    /**
     * Shifts all the items to the right if they are greater than the item
     * @param arr array with the data
     * @param item item to insert
     * @param rightLimit right limit of the window
     * @param <T> generic type
     * @return array with the swapped positions
     */
    private static <T extends Comparable<? super T>> T[] shiftAndReplace(T[] arr, T item, int rightLimit){
        int i;
        //Iterate the array to make all swaps
        for (i = rightLimit - 1; i >= 0 && item.compareTo(arr[i]) < 0; i--){
            arr[i + 1] = arr[i];
        }
        //Insert the item in it's position
        arr[i + 1] = item;
        return arr;
    }
}
