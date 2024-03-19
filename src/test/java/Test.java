import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Test {

    public static void main(String[] args) throws IOException {
        ObjectNode node = new ObjectMapper().readValue(new File("/tmp/scratch.json"), ObjectNode.class);
        BigDecimal pesoTotalGeral = BigDecimal.ZERO;
        BigDecimal pesoTotalNavioALZ = BigDecimal.ZERO;
        Set<String> navios = new HashSet<>();
        for(JsonNode jsonNode:node.get("view_api_batelada_b103")) {
            String terminal = jsonNode.get("armazem").asText();
            String navio = jsonNode.get("navio").asText();
            navios.add(navio);
            BigDecimal pesoBruto = BigDecimal.valueOf(jsonNode.get("peso_bruto").asDouble());
            BigDecimal pesoResidual = BigDecimal.valueOf(jsonNode.get("peso_residual").asDouble());
            BigDecimal peso = pesoBruto.subtract(pesoResidual);
            pesoTotalGeral = pesoTotalGeral.add(peso);
            if ("ALZ".equals(terminal)&&"ORIENT PEONY".equals(navio)){
                pesoTotalNavioALZ = pesoTotalNavioALZ.add(peso);
            }
        }
        System.out.println("Peso FULL: "+pesoTotalGeral);
        System.out.println("Peso ALZ: "+pesoTotalNavioALZ);
    }

}
