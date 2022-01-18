package workspace.sampler.bootStrap;

import workspace.sampler.Extract.ReadFromInput;
import workspace.sampler.Load.WriteToOutput;
import workspace.sampler.Transform.TransformData;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
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

    public BootStrap(HashMap<String, ReadFromInput> extract, HashMap<String, TransformData> transform, HashMap<String, WriteToOutput> load) {
        this.extract = extract;
        this.transform = transform;
        this.load = load;
    }

    private List<Map<String,String>> extractTheInformation(File file, String typeOfFile) throws IOException {
        return extract.get(typeOfFile).readObjectsFromInputFile(file);
    }
    private List<Map<String, String>> changeData(List<Map<String, String>> data, String type) throws IOException {
       return transform.get(type).MakeChangesToData(data);
    }

    private void loadTransformedData(String typeOfFile, List<Map<String, String>> transD,
                                     int numberOfLines, String objectType) throws IOException,
            ParserConfigurationException, TransformerException {
        load.get(typeOfFile).writeInOutput(transD,numberOfLines, objectType);
    }

    /**
     * this function does the whole program flow
     * @param inputFileType - the name of the input file
     * @param file - the input file itself
     * @param typeOfTransform - the name of the class that implements transform interface
     * @param outputFileType -the name of the class that implements load interface
     * @param numberOfLines - how many lines is the max amount to write in one file (50,000)
     * @param objectType
     * @throws IOException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public void createTheETL(String inputFileType,File file, String typeOfTransform,
                             String outputFileType, int numberOfLines, String objectType) throws IOException, TransformerException, ParserConfigurationException {
        List<Map<String, String>> primaryData = extractTheInformation(file, inputFileType);
        List<Map<String, String>> transformedData = changeData(primaryData, typeOfTransform);
        loadTransformedData(outputFileType, transformedData, numberOfLines, objectType);
    }
}
