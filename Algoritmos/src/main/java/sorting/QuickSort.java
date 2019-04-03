package sorting;

import utils.Utils;

public class QuickSort {

    public static <T extends Comparable<? super T>> void sort(T[] arr){
        if (arr.length > 1)
            sortRecursive(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<? super T>> void sortRecursive(T[] arr, int left, int right){
        if (right - left > 0){
            int newPivot = sepparateValues(arr, left, right);
            sortRecursive(arr, left, newPivot - 1);
            sortRecursive(arr, newPivot + 1, right);
        }
    }

    private static <T extends Comparable<? super T>> int sepparateValues(T[] arr, int left, int right){
        int i = left, j = right, comparison;
        boolean isLeft = false;
        T pivotValue = arr[left];
        Utils.swap(arr, left, right);
        while (i <= j){
            //Using j
            if (!isLeft){
                comparison = arr[j].compareTo(pivotValue);
                if (comparison < 0){
                    Utils.swap(arr, i, j);
                    isLeft = !isLeft;
                }
                else
                    j--;
            }
            //Using i
            else {
                comparison = arr[i].compareTo(pivotValue);
                if (comparison > 0){
                    Utils.swap(arr, i, j);
                    isLeft = !isLeft;
                }
                else
                    i++;
            }
        }
        //Insert the pivot between segments
        Utils.swap(arr, i, right);
        return i;
    }

}
