package TimerJava;

public class Test {

    public static void main(String[] args){

        Timer timer = new Timer();

        System.out.println("Timer Started");

        try{
            Thread.sleep(6234);
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }

        timer.Stop();

        System.out.println("Timer Finished");
        System.out.println(timer);
    }
}
