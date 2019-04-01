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
            //FullTextRetrieval.searchIndex("content", "alumnos");

            //FullTextRetrieval.searchIndexAdvanced("content", "\"ver documento\"~0");
            //FullTextRetrieval.searchIndexAdvanced("content", "\"ver documento\"~1");
            //FullTextRetrieval.searchIndexAdvanced("content", "analia ana~4");
            //FullTextRetrieval.searchIndexAdvanced("content", "ana analia~8");
            FullTextRetrieval.searchIndexAdvanced("content", "joe~0.9");
            FullTextRetrieval.searchIndexAdvanced("content", "joe~0.6");
        } catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
