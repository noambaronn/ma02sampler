package workspace.sampler.Load;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import workspace.sampler.bootStrap.Data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Data
public class SplitLargeJsonFile {
    private List<Map<String, String>> data;
    private int numberOfLines;

    public SplitLargeJsonFile(List<Map<String, String>> data, int numberOfLines) {
        this.data = data;
        this.numberOfLines = numberOfLines;
    }

    //converts the list<map<>> to json array
    public JSONArray listToJsonArray(String keyObject) {
        JSONArray jsonArray = new JSONArray();
        for (Map<?, ?> map : this.data) {
            JSONObject jsonObjectDetails = new JSONObject();
            JSONObject jsonObject = new JSONObject();
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                String key = String.valueOf(entry.getKey());
                Object value = entry.getValue();
                try {
                    jsonObjectDetails.put(key, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            jsonObject.put(keyObject, jsonObjectDetails);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /*this function splits the data into multiple files - each file has
    as many lines as this.numberOfLines (In our case i will put 50,000)*/
    public void splitIntoSmallerJsonFiles(JSONArray jsonArray) throws IOException {
        int i=0;
        int files = this.data.size() / this.numberOfLines +
                this.data.size() % this.numberOfLines;
        int lines = this.data.size();
        int currentLines;
        for (int j = 1; j <= files; j++) {
            FileWriter newFilesNames = new FileWriter("C:\\Users\\נועם\\TheFrequentSampler\\src\\main\\resources" +
                    "\\JsonFileNumber" + j + ".json");
            BufferedWriter out = new BufferedWriter(newFilesNames);
            if (this.numberOfLines > jsonArray.size()) {
                for (i = 1; i <= jsonArray.size(); i++) {
                    out.write(jsonArray.get(i).toString());
                    if (i != jsonArray.size()) {
                        out.write(",");
                        out.newLine();
                    }
                }
                out.write("]");
                out.close();
            }
            else{
                if (lines >= this.numberOfLines) {
                    currentLines = this.numberOfLines;
                } else {
                    currentLines = lines;
                }
                out.write("[");
                if (this.numberOfLines > jsonArray.size()) {

                }
                for (i = 1; i <= currentLines; i++) {
                    out.write(jsonArray.get(i).toString());
                    if (i != currentLines) {
                        out.write(",");
                        out.newLine();
                    }
                }
                lines = lines - this.numberOfLines;
                out.write("]");
                out.close();
            }
        }
    }
}

