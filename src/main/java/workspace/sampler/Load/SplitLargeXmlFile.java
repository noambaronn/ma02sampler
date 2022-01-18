package workspace.sampler.Load;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SplitLargeXmlFile {
    private List<Map<String, String>> data;
    private int numberOfLines;

    public SplitLargeXmlFile(List<Map<String, String>> data, int numberOfLines) throws TransformerException {
        this.data = data;
        this.numberOfLines = numberOfLines;
    }

    /**
     * i create a nodeList which has all the data in list<map<String,String>.
     * every time i create a new one which includes as many nodes as this.numberOfLines(50000)
     * @param currentLines
     * @param rootObject
     * @param keyObject
     * @param count
     * @param numberOfIterations
     * @return NodeList
     * @throws ParserConfigurationException
     */
    private NodeList listToXmkNodeList(int currentLines, String rootObject, String keyObject, int count, int numberOfIterations)
            throws ParserConfigurationException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        // root element
        Element root = document.createElement(rootObject);
        document.appendChild(root);
        for (int i=count; i<currentLines*numberOfIterations; i++) {
            // employee element
            Map<String,String> map = this.data.get(i);
            Element mainObjectChild = document.createElement(keyObject);
            root.appendChild(mainObjectChild);
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                // set an attribute to staff element
                String key = String.valueOf(entry.getKey());
                Object value = entry.getValue();
                Attr attr = document.createAttribute(key);
                attr.setValue((String) value);
                mainObjectChild.setAttributeNode(attr);
            }
        }
       NodeList nodeList = root.getChildNodes();
        return nodeList;
    }

    /**
     * this function splits the data into multiple files - each file has
     *     as many lines as this.numberOfLines (In our case i will put 50,000)
     *     i use the Dom library to write to the xml files
     * @param rootObject
     * @param keyObject
     * @throws IOException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public void splitXmlToSmallFiles(String rootObject, String keyObject) throws IOException,
            TransformerException, ParserConfigurationException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        int i = 0;
        int files = this.data.size() / this.numberOfLines +
                this.data.size() % this.numberOfLines;
        int lines = this.data.size();
        int currentLines=0;
        int count=0;
        int numberOfIterations= 1;
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
       for (int j = 1; j <= files; j++) {
            // create the xml files
           StreamResult streamResult = new StreamResult(new File("C:\\Users\\נועם\\TheFrequentSampler\\src\\main\\resources" +
                   "\\XmlFileNumber" + j+ ".xml"));
            if (lines >= this.numberOfLines) {
                currentLines = this.numberOfLines;
            } else {
                currentLines = lines;
            }
            //here i create the nodesList , there are as many nodesLits as the files created
            NodeList nodeList = listToXmkNodeList(currentLines,rootObject, keyObject,count, numberOfIterations);
            count += this.numberOfLines;
            numberOfIterations++;
            DOMSource domSource = new DOMSource((Node) nodeList);
           //transform the DOM Object to an XML File
           transformer.transform(domSource, streamResult);
            lines = lines - this.numberOfLines;
        }
    }
}
