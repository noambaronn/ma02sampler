package workspace.sampler.Extract;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ReadFromInput {
    public List<Map<?, ?>> readObjectsFromCsv(File file) throws IOException;
}
