public class AlgoA {
    public static int max(int[] array) {
        if (array == null || array.length == 0)             //Aca hace 2 comparaciones en el peor de los casos
            throw new RuntimeException("Empty array");

        int candidate = array[0];
        for (int rec = 1; rec < array.length - 1; rec++)    //Aca hace 1 comparacion y 1 suma
            if ( candidate < array[rec] )                   //Aca hace 1 comparacion
                candidate = array[rec];

        return candidate;
    }

    //El T(AlgoA) = 2 + 3 * N
}
