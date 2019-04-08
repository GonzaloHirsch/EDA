package lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedLinkedListWithHeader <T extends Comparable<? super T>> implements Iterable<T>{

    public static void main(String[] args) {
        SortedLinkedListWithHeader<Integer> lista = new SortedLinkedListWithHeader<>();
        lista.add(50);     lista.add(30);     lista.add(40);     lista.add(10);
        lista.add(20);     lista.add(60);     lista.add(70);     lista.add(80);

        for (Integer integer : lista)
            System.out.println(integer);

        System.out.println(String.format( "max = %s", lista.getMax()) );
        System.out.println(String.format( "min = %s", lista.getMin()) );
        System.out.println(String.format( "size = %s", lista.size()) );

        for (Iterator<Integer> x = lista.iterator(); x.hasNext();) {
            Integer auxi= x.next();
            if (auxi.equals(80) || auxi.equals(10) || auxi.equals(40)) {
                x.remove();
                System.out.println(String.format("deleting %s", auxi));
            } else {
                System.out.println(auxi);
            }
        }

        System.out.println("..");

        for (Integer integer : lista)
            System.out.println(integer);
    }

    private Header header;

    SortedLinkedListWithHeader(){
        this.header = new Header();
    }

    public Node getMin(){
        return this.header.min;
    }

    public Node getMax(){
        return this.header.max;
    }

    public boolean remove(T item){
        Node prev = this.header.min;
        Node curr = this.header.min;

        while(curr != null && curr.value.compareTo(item) < 0 ){
            prev = curr;
            curr = curr.next;
        }

        if (curr != null && curr.value.compareTo(item) == 0){
            if (curr == prev){
                this.header.min = curr.next;
                this.header.max = curr.next;
            } else {
                prev.next = curr.next;
            }
            this.header.size--;
            return true;
        } else if (curr == null){
            this.header.max = prev.next;
            this.header.size--;
            return true;
        }

        return false;
    }

    public void add(T item){
        Node prev = this.header.min;
        Node curr = this.header.min;

        while(curr != null && curr.value.compareTo(item) < 0 ){
            prev = curr;
            curr = curr.next;
        }

        if (curr != null && curr.value.compareTo(item) == 0){
            System.err.println(String.format("Insertion failed. %s repeated", item));
            return;
        }

        if (curr == prev){
            this.header.min = new Node(item, curr);
            if (this.header.size == 0) {
                this.header.max = new Node(item, curr);
            }
        } else if (curr == null){
            this.header.max = new Node(item, curr);
            prev.next = new Node(item, curr);
        } else {
            prev.next = new Node(item, curr);
        }
        this.header.size++;
    }

    public int size(){
        return this.header.size;
    }

    public boolean isEmpty(){
        return this.header.size <= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new SortedLinkedListWithHeaderIteratorWithRemove();
    }

    private class SortedLinkedListWithHeaderIterator implements Iterator<T> {

        private Node current;

        private SortedLinkedListWithHeaderIterator() {
            current = header.min;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node aux = current;
            current = current.next;
            return aux.value;
        }
    }

    private class SortedLinkedListWithHeaderIteratorWithRemove implements Iterator<T> {

        private Node current;
        private Node previous;
        private Node next;
        private boolean canRemove = false;

        @Override
        public void remove() {
            if (!canRemove)
                throw new UnsupportedOperationException();

            if (previous == null)
                header.min = next;
            else
                previous.next = next;
            canRemove = false;
        }

        private SortedLinkedListWithHeaderIteratorWithRemove() {
            next = header.min;
            current = null;
            previous = null;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            canRemove = true;
            Node aux = next;
            previous = current;
            current = next;
            next = next.next;
            return aux.value;
        }
    }

    private class Header {
        private Node min;
        private Node max;
        private int size;

        Header() {
            this.size = 0;
            this.min = null;
            this.max = null;
        }
    }

    private class Node {

        private T value;
        private Node next;

        Node(T theValue, Node theNext) {
            value = theValue;
            next = theNext;
        }

    }
}
