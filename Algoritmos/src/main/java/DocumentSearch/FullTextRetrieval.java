package DocumentSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Properties;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class FullTextRetrieval {

    private static String defaultDir = "c:/data";

    private static String getDatabaseDir() throws FileNotFoundException, IOException {
        Properties properties = new Properties();

        URL resource = FullTextRetrieval.class.getResource("database.conf");

        if (resource == null) { // no existe el directorio
            new File("c:/data").mkdir();
            properties.put("databaseDir", defaultDir);
        }
        else
            properties.load(new FileReader(new File(resource.getFile())));

        return properties.getOrDefault("databaseDir", defaultDir).toString();
    }

    public static void createIndex() throws IOException 	{
        // donde estï¿½ el ï¿½ndice?
        String databaseDir = getDatabaseDir();

        Directory indexDir = FSDirectory.open( Paths.get(databaseDir + "/index/"));

        // generar indice de todos los archivos. Podria poner un filtro. Ej: solo los txt y pdf
        IndexWriterConfig indexConf = new IndexWriterConfig(new StandardAnalyzer());
        // optional
        indexConf.setOpenMode(OpenMode.CREATE);  // other options are available

        final IndexWriter index = new IndexWriter(indexDir, indexConf);

        // agrego los documentos
        Path docsDir = Paths.get(databaseDir + "/docs/");
        FileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                Document aDoc = new Document();
                aDoc.add(new TextField("owner", "leticia gomez", Field.Store.YES));

                aDoc.add(new StringField("path", file.toAbsolutePath().toString(),
                        Field.Store.YES));

                InputStream theFile = Files.newInputStream(file);
                aDoc.add(new TextField("content",  new BufferedReader(
                        new InputStreamReader(theFile, StandardCharsets.UTF_8))));


                index.addDocument(aDoc);
                return FileVisitResult.CONTINUE;
            }
        };

        Files.walkFileTree(docsDir, fv);
        index.close();  // o bien index.close();
    }

    public static void searchIndexPath(String query) throws FileNotFoundException, IOException 	{

        // donde está el índice?
        String databaseDir = getDatabaseDir();

        IndexReader indexReader = DirectoryReader.open(
                FSDirectory.open(Paths.get(databaseDir + "/index/")));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);


        Term t = new Term("path", query);
        TermQuery termQuery = new TermQuery(t);
        TopDocs topDocs = indexSearcher.search(termQuery,10);
        System.out.println("Number of hits: " + topDocs.totalHits);
        ScoreDoc[] resultSet = topDocs.scoreDocs;
        for(ScoreDoc scoredoc: resultSet){

            // mostrar resultados
            Document doc = indexSearcher.doc(scoredoc.doc);
            System.out.println("owner: "+doc.getField("owner").stringValue());
            System.out.println("path: "+ doc.getField("path").stringValue());
        }

    }

}
