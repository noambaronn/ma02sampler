package workspace.sampler.Transform;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TransformData {
    public List<Map<String, String>> MakeChangesToData(List<Map<String, String>> data) throws IOException ;
}
