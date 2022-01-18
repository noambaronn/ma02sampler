package workspace.sampler.Load;

import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonOutput implements WriteToOutput{
    @Override
    //this function writes to the json files but only 50000 lines to each file
    public void writeInOutput(List<Map<String, String>> data, int numberOfLines, String objectType) throws IOException {
        SplitLargeJsonFile split = new SplitLargeJsonFile(data, numberOfLines);
        JSONArray jsonArray = split.listToJsonArray(objectType);
        split.splitIntoSmallerJsonFiles(jsonArray);
    }
}
