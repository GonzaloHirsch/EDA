package lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedLinkedList<T extends Comparable<? super T>> implements Iterable<T> {

    public static void main(String[] args) {
        SortedLinkedList<String> l = new SortedLinkedList<>();
        System.out.println(l.isEmpty());
        l.add("hola");
        System.out.println(l.remove("hola"));

        for (String s : l) {
            System.out.println(s);
        }

        System.out.println(l.isEmpty());
        l.add("tal");
        System.out.println(l.isEmpty());
        l.add("a");
        System.out.println(l.isEmpty());
        l.add("veo");
        System.out.println(l.isEmpty());
        l.add("beo");
        System.out.println(l.isEmpty());
        l.add("tito");
        System.out.println(l.isEmpty());

        System.out.println(l.isEmpty());
        l.add("hola");

        for (String s : l) {
            System.out.println(s);
        }

        System.out.println("\n");

        System.out.println(l.size());
        System.out.println(l.remove("rrr"));
        System.out.println(l.size());
        System.out.println(l.remove("a"));
        System.out.println(l.size());
        System.out.println(l.remove("beo"));
        System.out.println(l.size());
        for (String s : l) {
            System.out.println(s);
        }

        System.out.println("\n");
        for (String s : l) {
            // bla bla bla
            System.out.println(s);
        }
    }

    private Node header;

    public boolean isEmpty() {
        return header == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new SortedLinkedListIterator();
    }

    public boolean remove(T element){
        Node prev = header;
        Node rec = header;

        while(rec != null && rec.value.compareTo(element) < 0) {
            // go on
            prev = rec;
            rec = rec.next;
        }

        if(rec != null && rec.value.compareTo(element) == 0) {
            if (prev == rec) {
                header = rec.next;
            } else {
                prev.next = rec.next;
            }
            return true;
        }
        return false;
    }

    public int size(){
        Node rec = header;
        int count = 0;

        while(rec != null) {
            rec = rec.next;
            count++;
        }

        return count;
    }

    public void add(T element) {
        Node prev = header;
        Node rec = header;

        while(rec != null && rec.value.compareTo(element) < 0) {
            // go on
            prev = rec;
            rec = rec.next;
        }

        // repeated?
        if(rec != null && rec.value.compareTo(element) == 0) {
            System.err.println(String.format("Insertion failed. %s repeated", element));
            return;
        }

        // does the header change?
        if (prev == rec) {
            header = new Node(element, rec);
        } else {
            prev.next = new Node(element, rec);
        }
    }

    private class SortedLinkedListIterator implements Iterator<T> {

        private Node current;

        private SortedLinkedListIterator() {
            current = header;
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

    private final class Node {

        private T value;
        private Node next;

        // acepta nulls...
        Node(T theValue, Node theNext) {
            value = theValue;
            next = theNext;
        }

    }
}

