package workspace.sampler.bootStrap;

import workspace.sampler.Extract.CsvParser;
import workspace.sampler.Extract.ReadFromInput;
import workspace.sampler.Load.JsonOutput;
import workspace.sampler.Load.WriteToOutput;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Validation {
    public static void main(String[] args) throws IOException {
        HashMap<String, ReadFromInput> extract = new HashMap<>();
        HashMap<String, WriteToOutput> load =new HashMap<>();
        CsvParser csvParser =new CsvParser();
        extract.put("csv", csvParser);
        File inputFile = new File("src\\main\\resources\\MadaReports.csv");
        JsonOutput jsonOutput = new JsonOutput();
        load.put("json", jsonOutput);
        BootStrap bootStrap = new BootStrap(extract, load);
        String objectType = "Mada report";
        bootStrap.createTheETL("csv", inputFile, "dontknow", "json",
                500, objectType);
    }
}
