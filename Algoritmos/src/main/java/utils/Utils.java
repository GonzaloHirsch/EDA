package utils;

public class Utils {

    public static <T extends Comparable<? super T>> void swap(T[] arr, int left, int right){
        T aux = arr[left];
        arr[left] = arr[right];
        arr[right] = aux;
    }
}
