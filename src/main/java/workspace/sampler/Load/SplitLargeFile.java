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
public class SplitLargeFile {
    private List<Map<?, ?>> data;
    private int numberOfLines;

    public SplitLargeFile(List<Map<?, ?>> data, int numberOfLines) {
        this.data = data;
        this.numberOfLines = numberOfLines;
    }

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

    public void splitIntoSmallerJsonFiles(JSONArray jsonArray) throws IOException {
        int i=0;
        int files = this.data.size() / this.numberOfLines +
                this.data.size() % this.numberOfLines;
        int lines = this.data.size();
        int currentLines;
        for (int j = 1; j <= files; j++) {
            FileWriter newFilesNames = new FileWriter("C:\\Users\\נועם\\TheFrequentSampler\\src\\main\\resources" +
                    "\\FileNumber" + j + ".json");
            BufferedWriter out = new BufferedWriter(newFilesNames);
            if (lines >= this.numberOfLines) {
                currentLines = this.numberOfLines;
            } else {
                currentLines = lines;
            }
            out.write("[");
            for (i = 1; i <= currentLines; i++) {
                out.write(jsonArray.get(i).toString());
                if(i != currentLines){
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

