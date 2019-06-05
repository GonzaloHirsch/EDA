package graphs;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import graphs.helper.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanCSV {
    public static void main(String[] args) throws IOException {

        String fileName = "C:\\Repository\\EDA\\data\\data\\users.tsv";

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



        Map<String,String> mapping = new HashMap<>();
        mapping.put("#id", "id");
        mapping.put("gender", "gender");
        mapping.put("age", "age");
        mapping.put("country", "country");
        mapping.put("registered", "registered");

        HeaderColumnNameTranslateMappingStrategy<User> strategy =
                new HeaderColumnNameTranslateMappingStrategy<>();

        strategy.setType(User.class);
        strategy.setColumnMapping(mapping);

        CsvToBean<User> csv = new CsvToBean<>();

        List<User> list = csv.parse(strategy, csvReader);
        for (User u : list) {
            System.out.println(u);
        }

        csvReader.close();
    }



}