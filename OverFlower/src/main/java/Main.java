public class Main {

    public static void main(String[] args){
        GenerateHeapOverflow();
        //GenerateStackOverflow();
    }

    public static void GenerateHeapOverflow(){
        int N = 500000000;
        long[] overflowArray = new long[N];
    }

    public static void GenerateStackOverflow(){
        GenerateStackOverflow();
    }
}
