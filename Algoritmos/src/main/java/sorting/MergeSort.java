package sorting;

import utils.Utils;

import java.util.Arrays;

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
            sortRecursive(arr, left, (right + left) / 2);
            sortRecursive(arr, ((right + left) / 2) + 1, right);
            merge(arr, left, (right + left) / 2, ((right + left) / 2) + 1, right);
        }
    }

    private static <T extends Comparable<? super T>> void merge(T[] arr, int leftA, int rightA, int leftB, int rightB){
        int i = leftA, j = leftB, comparison;
        int position = leftA;
        T[] aux = Arrays.copyOf(arr, arr.length);
        while( i <= rightA && j <= rightB){
            comparison = aux[i].compareTo(aux[j]);
            if (comparison < 0){
                arr[position++] = aux[i++];
                //Utils.swap(arr, i++, position++);
            } else {
                arr[position++] = aux[j++];
                //Utils.swap(arr, j++, position++);
            }
        }
        if (i > rightA)
            arr[position] = aux[j];
        else if (j > rightB)
            arr[position] = aux[i];
    }
}
