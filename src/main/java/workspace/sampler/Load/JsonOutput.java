package workspace.sampler.Load;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonOutput implements WriteToOutput{
    @Override
    public void writeAsJson(List<Map<?, ?>> data, int numberOfLines) throws IOException {
        SplitLargeFile split = new SplitLargeFile (data, numberOfLines);
        split.splitFiles();
    }
}
