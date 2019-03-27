package Levenshtein;

public class Levenshtein {

    private static int[][] table;

    private static void FillTable(int strA, int strB){
        //Row * Col
        table = new int[strA][strB];
        //Fill the table
        for (int i = 0; i < strA; i++){
            table[i][0] = i;
        }
        for (int j = 0; j < strB; j++){
            table[0][j] = j;
        }
    }

    public static int Levenshtein(String strA, String strB){
        //Add the dummy character to be able to fill the table correctly
        strA = "*" + strA;
        strB = "*" + strB;

        //Filling the table
        FillTable(strA.length(), strB.length());

        for (int column = 1; column < strB.length(); column++){
            for (int row = 1; row < strA.length(); row++){
                table[row][column] = Min(table[row - 1][column] + 1, table[row][column - 1] + 1, table[row - 1][column - 1] + (strA.charAt(row) == strB.charAt(column) ? 0 : 1));
            }
        }

        //The "last" element of the table is the result
        return table[strA.length() - 1][strB.length() - 1];
    }

    public static double LevenshteinNormalized(String strA, String strB){
        double max = Integer.max(strA.length(), strB.length());
        return (1 - (Levenshtein(strA, strB)) / max);
    }

    public static String PrintTable(String strA, String strB){
        StringBuilder result = new StringBuilder();
        for (int column = 0; column < strB.length() + 1; column++){
            for (int row = 0; row < strA.length() + 1; row++){
                result.append(table[row][column] + "\t");
            }
            result.append("\n");
        }
        return result.toString();
    }

    private static int Min(int a, int b, int c){
        return Integer.min(c, Integer.min(a, b));
    }
}
