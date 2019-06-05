package graphs;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DumpCSV {
    public static void main(String[] args) throws IOException {

        String fileName = "C:\\Repository\\EDA\\data\\data\\artists.tsv";

        Path myPath = Paths.get(fileName);

        CSVParser parser = new CSVParserBuilder().
                withSeparator('\t').
                // withIgnoreQuotations(true).
                        build();

        CSVReader csvReader = new CSVReaderBuilder(Files.newBufferedReader(myPath,
                StandardCharsets.UTF_8))
                .withSkipLines(0)
                .withCSVParser(parser)
                .build();


        String[] line;
        while ((line = csvReader.readNext()) != null) {
            System.out.println("[name= " + line[0] + "]");
        }
    }
}