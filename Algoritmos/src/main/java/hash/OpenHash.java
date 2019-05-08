package hash;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Function;

public class OpenHash<K,V> {

    /**
     * Initial size of the array.
     * Used to increment size by chunks.
     */
    private final int INITIAL_SIZE = 5;

    /**
     * Node array of fixed size
     */
    @SuppressWarnings("unchecked")
    private LinkedList<Node<K,V>>[] table = new LinkedList[INITIAL_SIZE];

    /**
     * Function to be used when hashing
     */
    private Function<? super K, Integer> prehash;

    public OpenHash(Function<? super K, Integer> mappingFn)
    {
        prehash = mappingFn;
    }

    /**
     * Get the hash value of the key to able to use it in lookup
     * @param key key of the pair
     * @return hashcode of the given key
     */
    private int hash(K key)
    {
        if (key == null)
            throw new RuntimeException("No key provided");

        //Apply the hashing function and use the modulus of the length of the array
        return this.prehash.apply(key) % table.length;
    }

    /**
     * Getter for the given key
     * @param key key of the pair
     * @return value mapped to the key
     */
    public V getValue(K key)
    {
        Node<K, V> entry = get(key);
        if (entry == null)
            return null;

        return entry.value;
    }

    private Node<K,V> get(K key)
    {
        if (table[hash(key)] == null)
            return null;
        else{
            Iterator<Node<K,V>> iter = table[hash(key)].iterator();
            Node<K,V> actual = null;
            while(iter.hasNext()){
                actual = iter.next();
                if (actual.key == key)
                    return actual;
            }
            return null;
        }
    }

    // insert = update
    public void insert(K key, V value)
    {
        int index = hash(key);
        int position = -1;

        if (table[index] == null){
            table[index] = new LinkedList<>();
            table[index].add(new Node<>(key, value));
        } else {
            Iterator<Node<K,V>> iter = table[index].iterator();
            Node<K,V> actual = null;
            while(iter.hasNext() && position == -1){
                actual = iter.next();
                if (actual.key == key){
                    position = table[index].indexOf(actual);
                }
            }
            if (position != -1){
                table[index].get(position).value = value;
            } else
                table[index].add(new Node<>(key, value));
        }
    }

    public void delete(K key)
    {
        int index = hash(key);
        int position = -1;

        if (table[index] != null){
            Iterator<Node<K,V>> iter = table[index].iterator();
            Node<K,V> actual = null;
            while(iter.hasNext() && position == -1){
                actual = iter.next();
                if (actual.key == key){
                    position = table[index].indexOf(actual);
                }
            }
            if (position != -1){
                table[index].remove(position);
                if (table[index].size() == 0)
                    table[index] = null;
            }
        }
    }

    public void dump()
    {
        for(int rec = 0; rec < table.length; rec++)
            if (table[rec] == null)
                System.out.println(String.format("slot %d is empty", rec));
            else{
                Iterator<Node<K,V>> iter = table[rec].iterator();
                Node<K,V> actual = null;
                while(iter.hasNext()){
                    actual = iter.next();
                    System.out.println(String.format("slot %d contains %s", rec, actual.value));
                }
            }
    }

    static class Node<K,V>
    {
        final K key;
        V value;

        Node(K theKey, V theValue)
        {
            key= theKey;
            value= theValue;
        }

        @Override
        public String toString()
        {
            return String.format("key=%s, value=%s", key, value );
        }
    }
}
