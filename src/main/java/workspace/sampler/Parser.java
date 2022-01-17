package workspace.sampler;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Parser {


    public static void main(String[] args) throws Exception {
        File input = new File("src\\main\\resources\\MadaReports.csv");

        // List<Map<?, ?>> data = readObjectsFromCsv(input);
        List<Map<?, ?>> data = readObjectsFromCsv(input);
        //JsonOutput json = new JsonOutput();
       // json.writeAsJson(data, 500);
       // SplitLargeFile split = new SplitLargeFile(data, 500);
       // split.splitFiles();
       // String jsonString = listmap_to_json_string(data);
       // System.out.println(jsonString);
        //ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.writerWithDefaultPrettyPrinter().
             //   writeValueAsString(jsonString);
        //JSONParser parser = new JSONParser();
//        //writeAsJson(data, output);
    }

    public static String listmap_to_json_string(List<Map<?, ?>> list) {
        JSONArray json_arr = new JSONArray();
        for (Map<?, ?> map : list) {
            JSONObject json_obj = new JSONObject();
            for (Map.Entry<?,?> entry : map.entrySet()) {
                String key = String.valueOf(entry.getKey());
                Object value = entry.getValue();
                try {
                    json_obj.put(key, value);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            json_arr.add(json_obj);
        }
        return json_arr.toString();
    }

    public static List<Map<?, ?>> readObjectsFromCsv(File file) throws IOException {
        try {
            CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper csvMapper = new CsvMapper();
            MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader(Map.class).with(csvSchema).readValues(file);
            return mappingIterator.readAll();
        } catch (Exception e) {
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
