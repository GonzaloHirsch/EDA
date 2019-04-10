package DataStructures;

import java.util.Iterator;

public class LinkedList<T extends Comparable<? super T>> implements Iterable<T> {
    private Node<T> first;

    public LinkedList(){

    }

    public LinkedList(Node<T> first){
        this.first = first;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    public void setFirst(Node<T> first) {
        this.first = first;
    }

    public Node<T> getFirst() {
        return first;
    }

    private class LinkedListIterator implements Iterator<T>{
        private Node<T> curr = first;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public T next() {
            T value = curr.getValue();
            curr = curr.getNext();
            return value;
        }
    }
}