package trees;

public interface BSTreeInterface<T extends Comparable<? super T>> {

    void insert(T myData);

    void delete(T myData);

    boolean contains(T myData);

    void preOrder();

    void postOrder();

    void inOrder();

    NodeTreeInterface<T> getRoot();

}