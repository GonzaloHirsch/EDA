package Soundex;

public class Main {

    public static void main(String[] args){
        String input = "Gonzalez";
        String output = Soundex.Soundex(input);
        System.out.println(output);
    }
}
