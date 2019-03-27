package Ejercicio_2;

public class Test {

    public static void main(String[] args){

        Timer timer = new Timer();

        for (long i = 0; i < 30000000; i++){
            String a = "fgddfg";
            String b = "vdfvdf";
            if(a.equals(b))
                i += 1;
            System.out.println(i);
        }

        timer.Stop();
        System.out.println(timer);
    }
}
