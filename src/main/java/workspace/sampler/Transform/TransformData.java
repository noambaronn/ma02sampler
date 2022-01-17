package workspace.sampler.Transform;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TransformData {
    public List<Map<?,?>> MakeChangesToData(List<Map<?,?>> data) throws IOException;
}
