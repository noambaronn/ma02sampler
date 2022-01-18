package workspace.sampler.Extract;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ReadFromInput {
    public List<Map<String, String>> readObjectsFromInputFile(File file) throws IOException;
}
