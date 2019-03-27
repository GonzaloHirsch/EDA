package DocumentSearch;

public class Main {

    public static void main(String[] args){
        try{
            FullTextRetrieval.createIndex();
            FullTextRetrieval.searchIndexPath("doc1.txt");
        } catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
