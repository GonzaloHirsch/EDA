package Soundex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Soundex {

    private static Map<Character, Character> phoneticWeights;

    /**
     * El algoritmo recibe un string y devuelve un string de 4 caracteres con el codigo
     * El algoritmo resulta ser de orden n
     * En terminos de espacio extra
     * @param input
     * @return
     */
    public static String Soundex(String input){
        //Fill the map with the values
        FillMapping();

        //Trim all excess spaces and remove non-letter characters
        input = input.trim().replaceAll(" +", "").replaceAll("[^a-zA-Z]", "").toUpperCase();

        //Convert it to char array to be able to iterate and access specific positions
        char[] IN = input.toCharArray();

        //Create an output array and fill it with 0
        char[] OUT = new char[4];
        Arrays.fill(OUT, '0');

        //Verify the string isn't empty
        if (input.equals(""))
            return new String(OUT);
        else
            OUT[0] = IN[0];

        //Set the count
        int count = 1;

        //Get the first element phonetic value
        char last = phoneticWeights.get(IN[0]);                 //Como es un hashmap, el acceso es de orden 1
        char current;

        for (int i = 1; i < IN.length && count < 4; i++){       //Aca hace dos comparaciones n veces -> 2n
            current = phoneticWeights.get(IN[i]);
            if (current != '0' && current != last)              //Aca hace dos comparaciones n veces -> 2n
                OUT[count++] = current;
            last = current;
        }
        return new String(OUT);
    }

    private static void FillMapping(){
        phoneticWeights = new HashMap<>();
        phoneticWeights.put('A', '0');
        phoneticWeights.put('E', '0');
        phoneticWeights.put('I', '0');
        phoneticWeights.put('O', '0');
        phoneticWeights.put('U', '0');
        phoneticWeights.put('Y', '0');
        phoneticWeights.put('W', '0');
        phoneticWeights.put('H', '0');

        phoneticWeights.put('B', '1');
        phoneticWeights.put('F', '1');
        phoneticWeights.put('P', '1');
        phoneticWeights.put('V', '1');

        phoneticWeights.put('C', '2');
        phoneticWeights.put('G', '2');
        phoneticWeights.put('J', '2');
        phoneticWeights.put('K', '2');
        phoneticWeights.put('Q', '2');
        phoneticWeights.put('S', '2');
        phoneticWeights.put('X', '2');
        phoneticWeights.put('Z', '2');

        phoneticWeights.put('D', '3');
        phoneticWeights.put('T', '3');

        phoneticWeights.put('L', '4');

        phoneticWeights.put('M', '5');
        phoneticWeights.put('N', '5');

        phoneticWeights.put('R', '6');
    }

}
