package workspace.sampler.Load;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface WriteToOutput {
    public void writeInOutput(List<Map<?, ?>> data, int numberOfLines, String objectType) throws IOException;
}
