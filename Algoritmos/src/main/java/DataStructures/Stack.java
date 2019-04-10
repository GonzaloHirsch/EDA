package DataStructures;

import java.util.Iterator;

public class Stack <T extends Comparable<? super T>> {

    LinkedList<T> stack = new LinkedList<>(null);

    public Stack(){

    }

    public void push(T item){
        stack.setFirst(new Node<>(item, stack.getFirst()));
    }

    public T pop(){
        if (isEmpty())
            throw new UnsupportedOperationException("Stack is empty");
        T value = stack.getFirst().getValue();
        stack.setFirst(stack.getFirst().getNext());
        return value;
    }

    public boolean isEmpty(){
        return stack.getFirst() == null;
    }

    public T peek(){
        return stack.getFirst().getValue();
    }
}
