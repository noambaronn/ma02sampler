package workspace.sampler.bootStrap;

import workspace.sampler.Extract.CsvParser;
import workspace.sampler.Extract.ReadFromInput;
import workspace.sampler.Load.JsonOutput;
import workspace.sampler.Load.WriteToOutput;
import workspace.sampler.Load.XmlOutput;
import workspace.sampler.Transform.AddFields;
import workspace.sampler.Transform.TransformData;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * This is the only place where I inject constants into my project.
 * It is the tool that operates everything.
 * I create the object at the highest level and thus control all the changes.
 */
public class Validation {
    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException {
        HashMap<String, ReadFromInput> extract = new HashMap<>();
        HashMap<String, WriteToOutput> load =new HashMap<>();
        HashMap<String, TransformData> transform =new HashMap<>();
        CsvParser csvParser =new CsvParser();
        extract.put("csv", csvParser);
        AddFields addFields = new AddFields();
        transform.put("addF", addFields);
        File inputFile = new File("src\\main\\resources\\MadaReports.csv");
        File secondInputFile = new File("src\\main\\resources\\LabTests.csv");
        JsonOutput jsonOutput = new JsonOutput();
        load.put("json", jsonOutput);
        XmlOutput xmlOutput = new XmlOutput();
        load.put("xml", xmlOutput);
        BootStrap bootStrap = new BootStrap(extract,transform, load);
        String objectType = "Mada report";
        bootStrap.createTheETL("csv", inputFile, "addF", "json",
                500, objectType);
    }
}
