import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Test {

    public static void main(String args[]) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("ls_idrcu", "{ \"idRci\": \"1336615572\", \"ls_idrcu\": [" +
                "{\"ecosystem\": \"01\", \"idrcu\": \"34168867-d694-4db0-9ac0-0875b3e1e08d\"}," +
                "{\"ecosystem\": \"05\", \"idrcu\": \"22c2eaf4-8e56-4b16-8d1e-9ba706172fd3\"}" +
                "]}");

        String testJson = (String) attributes.get("ls_idrcu");
        try {
            String idRcuFT = null;
        JsonNode rootNode = mapper.readTree(testJson);
        JsonNode lsIdrcuNode = rootNode.get("ls_idrcu");
            List<DataIdRcuEcoDTO> dataList = mapper.convertValue(lsIdrcuNode, new TypeReference<List<DataIdRcuEcoDTO>>() {});
            for (DataIdRcuEcoDTO dataIdRcuEcoDTO : dataList) {
                System.out.println(dataIdRcuEcoDTO.getEcosystem()+" : "+dataIdRcuEcoDTO.getIdrcu());
            }

            if(!(dataList == null || dataList.isEmpty())){
                Optional<DataIdRcuEcoDTO> dataIdRcuEcoDTO = dataList.stream().
                        filter(data -> "01".equals(data.getEcosystem())).findFirst();
                if(dataIdRcuEcoDTO.isPresent()){
                    idRcuFT = dataIdRcuEcoDTO.get().getIdrcu();
                }
            }
            System.out.println("idRcuFT : "+idRcuFT);
        } catch (JsonProcessingException e) {
            System.out.println("Erreur de récupération des idrcus : " + e.getMessage());
        }
    }
}