package StringSearch;

public class Main {

    public static void main(String[] args){
        String a = "abrabraabra";
        String b = "abra";
        System.out.println("Result: " + StringSearch.IndexOf(a.toCharArray(), b.toCharArray()));
        System.out.println("Result: " + StringSearch.FindAll(b.toCharArray(), a.toCharArray()));
    }
}
