package workspace.sampler.Load;

import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonOutput implements WriteToOutput{
    @Override
    public void writeInOutput(List<Map<?, ?>> data, int numberOfLines, String objectType) throws IOException {
        SplitLargeFile split = new SplitLargeFile (data, numberOfLines);
        JSONArray jsonArray = split.listToJsonArray(objectType);
        split.splitIntoSmallerFiles(jsonArray);
    }
}
