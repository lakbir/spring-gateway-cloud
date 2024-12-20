import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Test2 {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String getIdRcuFromEcosystem(String jsonString, String ecosystem) {
        try {
            // Analyse de la chaîne JSON
            JsonNode rootNode = mapper.readTree(jsonString);
            JsonNode lsIdrcuNode = rootNode.get("ls_idrcu");

            // Vérifie si le nœud ls_idrcu existe et est un tableau
            if (lsIdrcuNode != null && lsIdrcuNode.isArray()) {
                for (JsonNode node : lsIdrcuNode) {
                    if (ecosystem.equals(node.get("ecosystem").asText())) {
                        return node.get("idrcu").asText();
                    }
                }
            } else {
                System.err.println("Le nœud ls_idrcu est nul ou n'est pas un tableau");
            }
        } catch (JsonProcessingException e) {
            System.err.println("Erreur de récupération des idrcus : " + e.getMessage());
        }

        return null;
    }

    public static String getAttributeFromJSON(Map<String, Object> attributes,
                                              String nomIdDansAttribut,
                                              String valeurIdDansAttribut,
                                              String nomAttribut,
                                              String nomAttributARecuperer) {
        if (attributes.get(nomAttribut) != null) {
            try {
                String jsonString = (String) attributes.get(nomAttribut);
                // Analyse de la chaîne JSON
                JsonNode rootNode = mapper.readTree(jsonString);
                JsonNode lsIdrcuNode = rootNode.get(nomAttribut);

                // Vérifie si le nœud ls_idrcu existe et est un tableau
                if (lsIdrcuNode != null && lsIdrcuNode.isArray()) {
                    for (JsonNode node : lsIdrcuNode) {
                        if (valeurIdDansAttribut.equals(node.get(nomIdDansAttribut).asText())) {
                            System.err.println("ID RCU final : " + node.get(nomAttributARecuperer).asText());
                            return node.get(nomAttributARecuperer).asText();
                        }
                    }
                } else {
                    System.err.println("Le nœud " + nomAttribut + " est nul ou n'est pas un tableau");
                }
            } catch (JsonProcessingException e) {
                System.err.println("Erreur de récupération des idrcus : " + e.getMessage());
            }
        } else {
            System.err.println("L'attribut " + nomAttribut + " n'existe pas dans la carte");
        }
        return null;
    }

    public static void main(String[] args) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("ls_idrcu", "[{\"ecosystem\":\"01\",\"idrcu\":\"ae71730a-03bf-48d8-8359-c5753a72d14e\"},{\"ecosystem\":\"05\",\"idrcu\":\"3e0f8370-13c7-4e95-aa67-0b9e53e253bc\"}]");

        String testJson = attributes.get("ls_idrcu").toString();

        String idrcu = getIdRcuFromEcosystem(testJson, "05");
        System.out.println("ID RCU pour l'écosystème 05 : " + idrcu);

        String idRcu = getAttributeFromJSON(attributes, "ecosystem", "01", "ls_idrcu", "idrcu");
        System.out.println("ID RCU pour l'écosystème 01 : " + idRcu);
    }
}