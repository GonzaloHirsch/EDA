package hash;

import java.util.function.Function;

public class Hash<K, V>
{
    /**
     * Initial size of the array.
     * Used to increment size by chunks.
     */
    private final int INITIAL_SIZE = 30;

    /**
     * Percentage of the array filled
     */
    private final double LIMIT = 0.75;

    /**
     * Amount of keys used
     */
    private int usedKeys = 0;

    /**
     * Node array of fixed size
     */
    @SuppressWarnings("unchecked")
    private Slot<K,V>[] LookUp= new Slot[INITIAL_SIZE];

    /**
     * Function to be used when hashing
     */
    private Function<? super K, Integer> prehash;

    public Hash(Function<? super K, Integer> mappingFn)
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
        return this.prehash.apply(key) % LookUp.length;
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
        return LookUp[hash(key)].node;
    }

    // insert = update
    public void insert(K key, V value)
    {
        int index = hash(key);
        int nextIndex = -1;
        boolean found = false;

        while(!found) {
            if (LookUp[index].status == Slot.Status.NOT_DELETED && LookUp[index].node.key == key) {
                found = true;
                throw new RuntimeException("Key value collision");
            }
            if (LookUp[index].status == Slot.Status.LOGICAL_DELETE || LookUp[index].status == Slot.Status.EMPTY) {
                found = true;
                nextIndex = index;
            }
        }
        
        //Verify if there is a key collision
        if (get(key) != null)
            throw new RuntimeException("Key collision");

        LookUp[hash(key)] = new Slot<>(new Node<>(key,  value));
        //Increment the amount of keys used
        usedKeys++;

        //Verify if the available space is low
        if (((double)this.usedKeys / this.LookUp.length) >= this.LIMIT){
            this.rehash();
        }
    }

    @SuppressWarnings("unchecked")
    private void rehash(){
        //Store the original array
        Slot<K,V>[] aux = this.LookUp;
        //Create a new copy
        this.LookUp = new Slot[aux.length + INITIAL_SIZE];
        //Clear the used keys
        this.usedKeys = 0;
        for(int i = 0; i < aux.length; i++){
            if (aux[i].status != Slot.Status.EMPTY || aux[i].status != Slot.Status.LOGICAL_DELETE || aux[i].status != Slot.Status.PHYSICAL_DELETE)
                this.insert(aux[i].node.key, aux[i].node.value);
        }
    }

    public void delete(K key)
    {
        int index = hash(key);
        boolean found = false;

        while(!found){
            if (LookUp[index].status == Slot.Status.NOT_DELETED && LookUp[index].node.key == key){
                int next = (index + 1) % LookUp.length;
                if ((LookUp[next].status == Slot.Status.NOT_DELETED || LookUp[next].status == Slot.Status.LOGICAL_DELETE))
                    LookUp[index].status = Slot.Status.LOGICAL_DELETE;
                else {
                    LookUp[index].status = Slot.Status.EMPTY;
                    LookUp[index].node = null;
                }
                found = true;
            } else {
                index++;
                index = index % LookUp.length;
            }
        }
    }

    public void dump()
    {
        for(int rec= 0; rec < LookUp.length; rec++)
            if (LookUp[rec] == null)
                System.out.println(String.format("slot %d is empty", rec));
            else
                System.out.println(String.format("slot %d contains %s", rec, LookUp[rec]));
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

    private static class Slot<K,V>{

        private enum Status{
            EMPTY,
            NOT_DELETED,
            LOGICAL_DELETE
        }

        private Status status = Status.EMPTY;
        private Node<K,V> node;

        public Slot(Node<K,V> node){
            this.node = node;
            this.status = Status.NOT_DELETED;
        }
    }

}