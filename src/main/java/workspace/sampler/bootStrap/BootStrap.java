package workspace.sampler.bootStrap;

import workspace.sampler.Extract.ReadFromInput;
import workspace.sampler.Load.WriteToOutput;
import workspace.sampler.Transform.TransformData;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class BootStrap {
    private HashMap<String, ReadFromInput> extract;
    private HashMap<String, TransformData> transform;
        private HashMap<String, WriteToOutput> load;

    public BootStrap(HashMap<String, ReadFromInput> extract, HashMap<String, WriteToOutput> load) {
        this.extract = extract;
        this.load = load;
    }

    private List<Map<?,?>> extractTheInformation(File file, String typeOfFile) throws IOException {
        return extract.get(typeOfFile).readObjectsFromInputFile(file);
    }
    private List<Map<?,?>> changeData(List<Map<?,?>> data, String type) throws IOException {
       return transform.get(type).MakeChangesToData(data);
    }

    private void loadTransformedData(String typeOfFile, List<Map<?,?>> transD,
                                     int numberOfLines, String objectType) throws IOException {
        load.get(typeOfFile).writeInOutput(transD,numberOfLines, objectType);
    }

    public void createTheETL(String inputFileType,File file, String typeOfTransform,
                             String outputFileType, int numberOfLines, String objectType) throws IOException {
        List<Map<?,?>> primaryData = extractTheInformation(file, inputFileType);
       // List<Map<?,?>> transformedData = changeData(primaryData, typeOfTransform);
        loadTransformedData(outputFileType, primaryData, numberOfLines, String objectType);
    }
}
