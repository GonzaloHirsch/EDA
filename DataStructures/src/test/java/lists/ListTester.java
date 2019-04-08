package lists;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListTester {

    @BeforeEach
    void setUp() {
        System.out.println("Test Started");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Finished");
    }

    //---------------------------------------EXCEPTIONS----------------------------------------------------



    //---------------------------------------NOT EXCEPTIONS-------------------------------------------------

    public <T extends Comparable<? super T>> void AssertAddMethod(SortedLinkedList<T> list, T item){
        list.add(item);
    }

    //-----------------------------------TESTS-----------------------------------------------------

    @Test
    void AssertAdd(){

    }
}
