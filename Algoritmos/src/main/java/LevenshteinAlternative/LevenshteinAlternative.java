package LevenshteinAlternative;

import org.apache.commons.text.similarity.LevenshteinDetailedDistance;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.apache.commons.text.similarity.LevenshteinResults;

public class LevenshteinAlternative {

    public static int Levenshtein(String a, String b){
        return new LevenshteinDistance().apply(a, b);
    }

    public static void LevenshteinDetails(String a, String b){
        LevenshteinResults results = new LevenshteinDetailedDistance().apply(a,b);
        System.out.println("Distance: " + results.getDistance());
        System.out.println("Deletes: " + results.getDeleteCount());
        System.out.println("Inserts: " + results.getInsertCount());
        System.out.println("Substitutes: " + results.getSubstituteCount());
    }
}
