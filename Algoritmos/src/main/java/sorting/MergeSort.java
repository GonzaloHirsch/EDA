package sorting;

import utils.Utils;

public class MergeSort {

    public static <T extends Comparable<? super T>> void sort(T[] arr){
        if (arr.length > 1){
            sortRecursive(arr, 0, arr.length - 1);
        }
    }

    private static <T extends Comparable<? super T>> void sortRecursive(T[] arr, int left, int right){
        int comparison;
        if (right - left <= 1){
            comparison = arr[left].compareTo(arr[right]);
            //arr[left] > arr[right]
            if (comparison > 0)
                Utils.swap(arr, left, right);
        } else {
            sortRecursive(arr, left, right / 2);
            sortRecursive(arr, (right / 2) + 1, right);
            merge(arr, left, (right - left), (right - left) + 1, right);
        }
    }

    private static <T extends Comparable<? super T>> void merge(T[] arr, int leftA, int rightA, int leftB, int rightB){
        int i = leftA, j = leftB, comparison;
        int position = leftA;
        while( i <= rightA && j <= rightB){
            comparison = arr[i].compareTo(arr[j]);
            if (comparison < 0){
                Utils.swap(arr, i++, position++);
            } else {
                Utils.swap(arr, j++, position++);
            }
        }
    }
}
