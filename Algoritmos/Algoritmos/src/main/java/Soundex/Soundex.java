package Soundex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Soundex {

    private static Map<Character, Character> phoneticWeights;

    public static String Soundex(String in){
        //Fill the map with the values
        FillMapping();

        //Trim all excess spaces
        in = in.trim().replaceAll(" +", "").toUpperCase();

        //Convert it to char array to be able to iterate and access specific positions
        char[] input = in.toCharArray();

        //Create an output array and fill it with 0
        char[] out = new char[4];
        Arrays.fill(out, '0');

        //Verify the string isn't empty
        if (in.equals(""))
            return new String(out);
        else
            out[0] = input[0];

        //Set the count
        int count = 1;
        //Get the first element phonetic value
        char last = phoneticWeights.get(out[0]);
        char current;

        for (int i = 1; i < input.length && count < 4; i++){
            current = phoneticWeights.get(input[i]);
            if (current != '0' && current != last){
                out[count] = current;
                count++;
            }
            last = current;
        }
        return new String(out);
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
