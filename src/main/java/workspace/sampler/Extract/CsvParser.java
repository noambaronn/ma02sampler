package workspace.sampler.Extract;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CsvParser implements ReadFromInput{
    @Override
    //reads the csv file and return the data as a list<Map<String, String>>
    public List<Map<String, String>> readObjectsFromInputFile(File file) throws IOException {
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<String, String>> mappingIterator = csvMapper.reader(Map.class).with(csvSchema).readValues(file);
        return mappingIterator.readAll();
    }
}
