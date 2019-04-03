package arrays;

public class Search {

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

    public static <T extends Comparable<? super T>> int search(T[] arr, T key, int left, int right){
        int mid = (right + left) / 2;
        int comparison = arr[mid].compareTo(key);
        if (comparison == 0)
            return mid;
        if(left >= right)
            return -1;
        if (comparison < 0){
            return search(arr, key, mid + 1, right);
        } else {
            return search(arr, key, left, mid - 1);
        }
    }

    /**
     * Recursive binary search algorith to determine the position a new item would have in the array
     * @param arr array containing data
     * @param key new item
     * @param left left limit of the array window(start in 0)
     * @param right right limit of the array window(start in length - 1)
     * @return the position of the key in the array
     */
    public static int determineNewItemPosition(int[] arr, int key, int left, int right){
        int mid = (right + left) / 2;
        if(left >= right){
            if (arr[mid] > key){
                return mid;
            } else {
                return mid + 1;
            }
        }
        if (key > arr[mid]){
            return determineNewItemPosition(arr, key, mid + 1, right);
        } else {
            return determineNewItemPosition(arr, key, left, mid - 1);
        }
    }

    public static  <T extends Comparable<? super T>> int determineNewItemPosition(T[] arr, T key, int left, int right){
        int mid = (right + left) / 2;
        int comparison = arr[mid].compareTo(key);
        if(left >= right){
            if (comparison > 0){
                return mid;
            } else {
                return mid + 1;
            }
        }
        if (comparison < 0){
            return determineNewItemPosition(arr, key, mid + 1, right);
        } else {
            return determineNewItemPosition(arr, key, left, mid - 1);
        }
    }
}
