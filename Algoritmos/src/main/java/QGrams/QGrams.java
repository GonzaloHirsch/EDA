package QGrams;

import java.util.HashMap;
import java.util.Map;

public class QGrams {
    private static final String EXTRA_STRING = "*";

    public static double QGrams(String strA, String strB, int grams){
        strA = GenerateGramString(strA, grams).toUpperCase();
        strB = GenerateGramString(strB, grams).toUpperCase();

        Map<String, Integer> AGrams = GetGrams(strA, grams);
        Map<String, Integer> BGrams = GetGrams(strB, grams);

        int countsA = FindGrams(AGrams, BGrams);
        int countsB = FindGrams(BGrams, AGrams);

        return Similarity(countsA, countsB, SumAll(AGrams), SumAll(BGrams));
    }

    private static int SumAll(Map<String, Integer> list){
        int count = 0;
        for (Integer v : list.values()){
            count += v;
        }
        return count;
    }

    private static int FindGrams(Map<String, Integer> listA, Map<String, Integer> listB){
        int count = 0;
        for (String str : listA.keySet()) {
            if (listB.get(str) != null)
                count += listB.get(str);
        }
        return count;
    }

    private static double Similarity(int APositions, int BPositions, int ALength, int BLength){
        return ((APositions + BPositions)) / (double)(ALength + BLength);
    }

    private static Map<String, Integer> GetGrams(String str, int grams){
        Map<String, Integer> strs = new HashMap<>();
        String aux;
        for(int i = 0; i + grams <= str.length(); i++){
            aux = str.substring(i, i + grams);
            strs.put(aux, strs.get(aux) == null ? 1 : strs.get(aux) + 1);
        }
        return strs;
    }

    public static void PrintTokens(String str, int grams){
        str = GenerateGramString(str, grams).toUpperCase();
        Map<String, Integer> allGrams = GetGrams(str, grams);
        for(String s : allGrams.keySet()){
            System.out.println(s);
        }
    }

    private static String GenerateGramString(String str, int grams){
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < grams - 1; i++){
            strBuilder.append(EXTRA_STRING);
        }
        strBuilder.append(str);
        for (int i = 0; i < grams - 1; i++){
            strBuilder.append(EXTRA_STRING);
        }
        return strBuilder.toString();
    }

}
