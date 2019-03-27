import TimerJava.Timer;

public class Performance {

    public static final int N = 10;

    public static void main(String[] args) {
        // generate array
        int[] myArray = new int[N];

        for (int rec = N; rec > 0; rec--)
            myArray[N - rec] = rec;

        // performance testing
        Timer t1 = new Timer();
        int rta1 = AlgoA.max(myArray);
        t1.Stop();

        //Timer t2 = new Timer();
        //int rta2 = AlgoB.max(myArray);
        //t2.Stop();

        System.out.println(String.format("max Algo A %d. Delay %d (ms)", rta1, t1.GetMillis()));
        //System.out.println(String.format("max Algo B %d. Delay %d (ms)", rta2, t2.GetMillis()));
        System.out.println(String.format("max Algo A %d. Delay %d (nanos)", rta1, t1.GetNanos()));
        //System.out.println(String.format("max Algo B %d. Delay %d (nanos)", rta2, t2.GetNanos()));
    }

    //Results
    /*
        N = 10 -> A = 0 - B = 0
        N = 100 -> A = 0 - B = 0
        N = 1 000 -> A = 0 - B = 0
        N = 100 000 000 -> A = 47 - B = 171
        N = 200 000 000 -> A = 78 - B = 406
        Con N = 400 000 000 me quede sin heap en el AlgoA y en el AlgoB
     */
}
