package Levenshtein;

import TimerJava.Timer;

public class Main {

    public static void main(String[] args){
        String a = "amigo";
        String b = "amigo";
        Timer timer = new Timer();
        int result = Levenshtein.Levenshtein(a, b);
        timer.Stop();
        System.out.println(result);
        System.out.println("Length of A: " + a.length() + "\tLength of B: " + b.length());
        System.out.println(timer);
        System.out.println(Levenshtein.LevenshteinNormalized(a, b));
        System.out.println(Levenshtein.PrintTable(a, b));
    }
}
