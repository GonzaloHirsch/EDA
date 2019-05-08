package hash;

import java.io.*;
import java.util.function.Function;

public class Test {

    public static void main(String[] args) throws IOException {

        /*
        Hash<String, Integer> hash = new Hash<>

        Hash<String, Integer> newHash = new Hash<>(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                long aux = 0;
                int sum = 0;
                for(int i = 0; i < s.length(); i++)
                    aux = 31 * aux + (int)s.charAt(i);
                while(aux != 0){
                    sum += (aux % 1000);
                    aux = aux/(1000);
                }
                return sum;
            }
        });

        int countA = 0;
        int countB = 0;
        BufferedReader br = new BufferedReader(new FileReader("C:\\Repository\\EDA\\data\\data\\amazon-categories30.txt"));
        try {
            String line = br.readLine();
            String[] items;

            while (line != null) {
                items = line.split("[#]");
                System.out.println("A - " + countA);
                System.out.println("B - " + countB);
                //hash.insert(items[0], Integer.valueOf(items[2]));
                countA++;
                newHash.insert(items[0], Integer.valueOf(items[2]));
                countB++;
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("A - " + countA);
            System.out.println("B - " + countB);
            e.printStackTrace();
        } finally {
            br.close();
        }
        hash.dump();

        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        newHash.dump();
        */
        OpenHash<Integer, String> hash = new OpenHash<>(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer;
            }
        });

        hash.insert(3, "Dick");
        hash.insert(23, "Joe");
        hash.insert(4, "Sue");
        hash.insert(15, "Meg");
        hash.delete(23);
        hash.delete(15);
        hash.insert(4, "Susan");
        hash.insert(43, "Paul");

        hash.dump();
    }
}
