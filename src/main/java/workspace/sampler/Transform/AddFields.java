package workspace.sampler.Transform;

import health_care_provider.HealthCareInfoProvider;
import health_care_provider.errors.InvalidIdException;
import health_care_provider.models.PersonInsured;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddFields implements TransformData{
    /**
     * Here i transform the data by adding 3 more fields -
     * joinDate
     * healthCareId
     * healthCareName
     * I add them only to the maps where the original data maps has an invalid idNum.
     * Only the maps in data that the IdNum of them is valid, will be transformed(add
     * the new fields) and i will put them in the new List<Map<String,String>> dataAfterAddedFileds
     * which i return.
     * @param data - the primary data that i parsed from the input file
     * @return - a list of maps which includes the data after the transformed that i
     * have benn asked to do.
     * @throws IOException
     */
    @Override
    public List<Map<String, String>> MakeChangesToData(List<Map<String, String>> data) throws IOException {
        List<Map<String,String>> dataAfterAddedFileds = new ArrayList<>();
        HealthCareInfoProvider healthCareInfoProvider = new HealthCareInfoProvider();
        for (Map<String, String> map : data) {
            try{
               PersonInsured person = healthCareInfoProvider.fetchInfo(Integer.parseInt(map.get("IDNum")), Integer.parseInt(map.get("IDType")));
               String joinDate = String.valueOf(person.getJoinDate());
               String healthCareIdealCareId = String.valueOf(person.getHealthCareId());
               String healthCareName = person.getHealthCareName();
                map.put("joinDate",joinDate);
                map.put("healthCareId", healthCareIdealCareId);
                map.put("healthCareName", healthCareName);
                dataAfterAddedFileds.add(map);
            } catch (InvalidIdException e) {
                //e.printStackTrace();
                //do not add the map to my transformed data
            }
        }
        return dataAfterAddedFileds;
    }
}
