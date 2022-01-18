package workspace.sampler.Load;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface WriteToOutput {
    public void writeInOutput(List<Map<String, String>> data, int numberOfLines, String objectType) throws IOException, ParserConfigurationException, TransformerException;
}
