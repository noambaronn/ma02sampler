package workspace.sampler.Load;

import workspace.sampler.bootStrap.Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Data
public class splitLargeFile {
    private List<Map<?, ?>> data;
    private  int numberOfLines;

    public splitLargeFile(List<Map<?, ?>> data, int numberOfLines) {
        this.data = data;
        this.numberOfLines = numberOfLines;
    }

    public void splitFiles() throws IOException {
        int i = 0;
        int files = this.data.size() / this.numberOfLines +
                this.data.size() % this.numberOfLines;
        int lines = this.data.size();
        int currentLines;
        for (int j = 1; j <= files; j++) {
            FileWriter newFilesNames = new FileWriter("C:\\Users\\נועם\\TheFrequentSampler\\src\\main\\resources" +
                    "\\FileNumber" + j + ".csv");
            BufferedWriter out = new BufferedWriter(newFilesNames);
            if(lines >= this.numberOfLines){
                currentLines = this.numberOfLines;
            }
            else{
                currentLines = lines;
            }
            for (i = i + 1; i <= (j*currentLines); i++){
                if(i != (j*lines)){
                    out.write(this.data.get(i).toString());
                    out.newLine();
                }
            }
            lines = lines - this.numberOfLines;
            out.close();
        }
    }
}
