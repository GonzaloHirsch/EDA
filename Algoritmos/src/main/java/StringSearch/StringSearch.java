package StringSearch;

import java.util.ArrayList;
import java.util.List;

public class StringSearch {

    public static int IndexOf(char[] source, char[] target){
        int j;

        //Check if any of the strings is empty and throw an exception
        if (source.length == 0 || target.length == 0)
            throw new RuntimeException("Both arrays have to have at least one character");

        /*&& ( source.length - i) > target.length*/ //This can optimize if the target can be longer than the source
        for (int i = 0; i < source.length; i++){
            for (j = i; j < source.length &&  j - i < target.length && source[j] == target[j - i]; j++);
            if (j - i == target.length)
                return i;
        }
        return -1;
    }

    private static int[] nextComputation(char[] query) {
        int[] next = new int[query.length];
        next[0] = 0;     // Always. There's no proper border.
        int border = 0;  // Length of the current border
        for (int rec = 1; rec < query.length; rec++) {
            while ((border > 0) && (query[border] != query[rec]))
                border = next[border - 1];     // Improving previous computation
            if (query[border] == query[rec])
                border++;
            // else border = 0;  // redundant
            next[rec] = border;
        }
        return next;
    }

    /**
     * Finds all the positions of the ocurrences of query in target
     * @param query string to search for in target
     * @param target target string to search in
     * @return positions of query in target
     */
    public static List<Integer> FindAll(char[] query, char[] target) {
        List<Integer> positions = new ArrayList<>();

        if (query == null || query.length == 0)
            throw new RuntimeException("Bad query string");
        if (target == null || target.length == 0)
            throw new RuntimeException("Bad target string");

        int[] next = nextComputation(query);
        int rec = 0;
        int pquery = 0;
        while (rec < target.length) {
            if (target[rec] == query[pquery]) {
                rec++;
                pquery++;
            }
            if (pquery == query.length) {  // Found!!!
                positions.add(rec - pquery);
                rec++;
                pquery = next[pquery - 1];
            } else // mismatch?
                if (rec < target.length && target[rec] != query[pquery]) {
                    // no machea los i-1
                    if (pquery != 0)
                        pquery = next[pquery - 1];
                    else
                        rec++;
                }
        }
        return positions;
    }
}
