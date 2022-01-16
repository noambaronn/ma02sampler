package workspace.sampler;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import workspace.sampler.Load.splitLargeFile;


public class Parser {


        public static void main(String[] args) throws Exception {
            File input = new File("src\\main\\resources\\MadaReports.csv");

           // List<Map<?, ?>> data = readObjectsFromCsv(input);
            List<Map<?, ?>> data = readObjectsFromCsv(input);
            System.out.println(data.get(0).toString());
            splitLargeFile split = new splitLargeFile(data, 500);
            split.splitFiles();
            //writeAsJson(data, output);
        }

        public static List<Map<?, ?>> readObjectsFromCsv(File file) throws IOException {
            try {
                CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
                CsvMapper csvMapper = new CsvMapper();
                MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader(Map.class).with(csvSchema).readValues(file);
                return mappingIterator.readAll();
            }
            catch (Exception e){
                e.printStackTrace();
                throw e;
            }
        }
    /*public static List<Map<String, String>> read(File file) throws JsonProcessingException, IOException {
        List<Map<String, String>> response = new LinkedList<Map<String, String>>();
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        MappingIterator<Map<String, String>> iterator = mapper.reader(Map.class)
                .with(schema)
                .readValues(file);
        while (iterator.hasNext()) {
            response.add(iterator.next());
        }
        return response;
    }*/

        public static void writeAsJson(List<Map<?, ?>> data, File file) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, data);
        }
}
