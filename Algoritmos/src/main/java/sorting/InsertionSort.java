package sorting;

public class InsertionSort {

    public static <T extends Comparable<? super T>> T[] Sort(T[] arr){
        int comparison;
        for (int i = 1; i < arr.length; i++){
            comparison = arr[i].compareTo(arr[i - 1]);
            //arr[i] < arr[i - 1]
            //Make the swapping
            if (comparison < 0){
                shiftItemsRight(arr, arr[i], i);
            }
        }
        return arr;
    }

    private static <T extends Comparable<? super T>> T[] shiftItemsRight(T[] arr, T item, int rightLimit){
        int i;
        for (i = rightLimit - 1; i >= 0 && item.compareTo(arr[i]) < 0; i--){
            arr[i + 1] = arr[i];
        }
        arr[i + 1] = item;
        return arr;
    }
}
