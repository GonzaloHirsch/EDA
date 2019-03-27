package SoundexAlternative;

import org.apache.commons.codec.language.Soundex;

public class SoundexAlternative {

    public static String Soundex(String s){
        return new Soundex().soundex(s);
    }

    public static double SoundexDistance(String a, String b){
        try{
            return new Soundex().difference(a,b) / (double)4;
        } catch (Exception ex){
            return 0;
        }
    }
}
