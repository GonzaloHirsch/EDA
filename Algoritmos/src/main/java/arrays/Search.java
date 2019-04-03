package arrays;

public class ArraySearch {

    /**
     * Recursive binary search algorithm. It searches for the key item in the array
     * @param arr array containing the data
     * @param key item to be searched
     * @param left left limit of the array window(start in 0)
     * @param right right limit of the array window(start in length - 1)
     * @return the position of the key in the array, -1 if not
     */
    public static int search(int[] arr, int key, int left, int right){
        int mid = (right + left) / 2;
        if (arr[mid] == key)
            return mid;
        if(left >= right)
            return -1;
        if (key > arr[mid]){
            return search(arr, key, mid + 1, right);
        } else {
            return search(arr, key, left, mid - 1);
        }
    }
}
