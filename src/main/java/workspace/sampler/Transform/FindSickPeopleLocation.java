package workspace.sampler.Transform;

import workspace.sampler.Extract.CsvParser;
import workspace.sampler.Load.JsonOutput;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindSickPeopleLocation implements CrucifixionData {
    @Override
    public List<Map<String, String>> CrucifixionTwoLists(List<Map<String, String>> data1, List<Map<String, String>> data2) {
        List<Map<String, String>> dataAfterCrucifixion = new ArrayList<>();
        int count = 0;
        for (Map<String, String> map : data1) {
            if (map.get("ResultTestCorona").equals("1")) {
                for (Map<String, String> mapData2 : data2) {
                    if(map.get("IDNum").equals(mapData2.get("IDNum"))){
                        String firstName = mapData2.get("FirstName");
                        String lastName = mapData2.get("LastName");
                        String fullName = firstName + " " + lastName;
                        String city = mapData2.get("City");
                        String street = mapData2.get("Street");
                        String buildingNumber = mapData2.get("BuildingNumber");
                        Address addressOfSickPerson = new Address(city, street, buildingNumber);
                        Map<String, String> sickPersonsAddress = new HashMap<>();
                        sickPersonsAddress.put(fullName, addressOfSickPerson.toString());
                        dataAfterCrucifixion.add(count, sickPersonsAddress);
                    }
                }
            }
        }
        return dataAfterCrucifixion;
    }

    public static void main(String[] args) throws IOException {
        FindSickPeopleLocation findSickPeopleLocation = new FindSickPeopleLocation();
        File inputFile = new File("src\\main\\resources\\MadaReports.csv");
        File secondInputFile = new File("src\\main\\resources\\LabTests.csv");
        CsvParser csvParser = new CsvParser();
        List<Map<String, String>> mada = csvParser.readObjectsFromInputFile(inputFile);
        CsvParser csvParser2 = new CsvParser();
        List<Map<String, String>> labTest = csvParser2.readObjectsFromInputFile(secondInputFile);
        List<Map<String, String>> data =findSickPeopleLocation.CrucifixionTwoLists(labTest, mada);
        JsonOutput jsonOutput = new JsonOutput();
        jsonOutput.writeInOutput(data,340,"SickPersonsLoc");
    }
}
