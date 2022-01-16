package workspace.sampler.bootStrap;

import jdk.jfr.DataAmount;
import workspace.sampler.Extract.ReadFromInput;
import workspace.sampler.Load.WriteToOutput;
import workspace.sampler.Transform.TransformData;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class bootStrap {
    private HashMap<String, ReadFromInput> extract;
    private HashMap<String, TransformData> transform;
    private HashMap<String, WriteToOutput> load;

}
