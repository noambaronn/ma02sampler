package workspace.sampler.Load;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface WriteToOutput {
    public void writeAsJson(List<Map<?, ?>> data, int numberOfLines) throws IOException;
}
