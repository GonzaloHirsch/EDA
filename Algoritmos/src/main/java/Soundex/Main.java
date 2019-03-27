package Soundex;

import TimerJava.Timer;

public class Main {

    public static void main(String[] args){
        String input = "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii";
        Timer timer = new Timer();
        String output = Soundex.Soundex(input);
        timer.Stop();
        System.out.println("Input string: " + input + " Output string: " + output + " Time: " + timer);
    }
}
