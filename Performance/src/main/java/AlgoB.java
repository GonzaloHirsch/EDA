import java.util.Arrays;

public class AlgoB {
    public static int max (int[] array) {
        if (array == null || array.length == 0)         //Hace 2 comparaciones aca
            throw new RuntimeException("Empty array");

        Arrays.sort(array);  // ordena ascendentemente  //Segun oracle hace n * log(n) comparaciones

        return array[array.length - 1];
    }

    //El T(AlgoB) = 2 + N * log(N)
}
