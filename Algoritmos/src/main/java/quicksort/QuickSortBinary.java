package quicksort;

import binarytree.BinaryTree;
import binarytree.Node;

import java.util.ArrayList;
import java.util.List;

public class QuickSortBinary {

    public static <T extends Comparable<? super T>> T[] QuickSort(T[] arr){
        BinaryTree bt = new BinaryTree();
        Node<T> node = new Node<T>();

        bt.setHead(QuickSortRecursive(arr, node));
        T[] finishedArr = Finish(arr, bt);
        return finishedArr;
    }

    //El problema esta en que no tiene longitud el array cuando lo separa
    private static <T extends Comparable<? super T>> Node<T> QuickSortRecursive(T[] arr, Node<T> node){
        if (arr.length == 1){
            Node<T> lastNode = new Node<T>(node);
            lastNode.setValue(arr[0]);
            return lastNode;
        } else if (arr.length == 0){
            return null;
        } else {
            int pivot = GetPivot(arr);
            node.setValue(arr[pivot]);

            T pivotValue = arr[pivot];
            List<T> right = new ArrayList<>();
            List<T> left = new ArrayList<>();
            int compareValue;
            for (int i = 0; i < arr.length; i++){
                compareValue = pivotValue.compareTo(arr[i]);
                //  pivotValue > arr[i]
                if (compareValue > 0){
                    left.add(arr[i]);
                } else if (compareValue < 0){
                    right.add(arr[i]);
                }
            }
            @SuppressWarnings("unchecked")
            T[] rightArr = (T[])new Comparable[right.size()];
            @SuppressWarnings("unchecked")
            T[] leftArr = (T[])new Comparable[left.size()];
            rightArr = right.toArray(rightArr);
            leftArr = left.toArray(leftArr);

            node.setLeft(QuickSortRecursive(leftArr, node));
            node.setRight(QuickSortRecursive(rightArr, node));
            return node;
        }
    }

    private static <T extends Comparable<? super T>> int GetPivot(T[] arr){
        return arr.length - 1;
    }

    private static <T extends Comparable<? super T>> T[] Finish(T[] arr, BinaryTree bt){
        Node<T> current = bt.getHead();
        int counter = 0;
        boolean isLeft = true;

        //Move to the leftmost terminator node
        while(current.getLeft() != null){
            current = current.getLeft();
        }

        while(arr.length != counter){
            if (current.getHead() == null){
                isLeft = false;
                if (counter == 0){
                    arr[counter++] = current.getValue();
                    current = current.getRight();
                }
            } else if (counter == 0){
                arr[counter++] = current.getValue();
                if (isLeft)
                    current = current.getHead().getRight();
                else
                    current = current.getHead().getLeft();
            } else if (counter == arr.length - 2){
                arr[counter++] = current.getValue();
                current = current.getHead().getRight();
            } else if (counter == arr.length - 1){
                arr[counter++] = current.getValue();
            } else if (isLeft){
                if (current.getHead().getRight() == current){
                    arr[counter++] = current.getValue();
                    current = current.getHead().getRight();
                }
            } else if (!isLeft){
                if (current.getHead().getLeft() == current){
                    arr[counter++] = current.getValue();
                    current = current.getHead().getLeft();
                }
            }
        }
        return arr;
    }
}
