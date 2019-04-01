package DocumentSearch;

public class Main {

    public static void main(String[] args){
        try{
            FullTextRetrieval.createIndex();
            //FullTextRetrieval.searchIndexPath("c:\\Repository\\EDA\\data\\docs\\doc1.txt");

            //It does NOT work
            //FullTextRetrieval.searchIndex("owner", "Gonzalo");
            //FullTextRetrieval.searchIndex("owner", "Gonzalo Hirsch");
            //FullTextRetrieval.searchIndex("owner", "gonzalo hirsch");

            //It works
            //FullTextRetrieval.searchIndex("owner", "gonzalo");

            FullTextRetrieval.searchIndex("content", "alumnos");
        } catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
