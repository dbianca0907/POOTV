import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.ObjectWriter;
import input.data.InputData;

import java.io.File;
import java.io.IOException;


public final class Main {
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        InputData inputData = objectMapper.readValue(inputFile, InputData.class);
        ArrayNode output = objectMapper.createArrayNode();
        Platform platform = new Platform();
        platform.main(inputData, output);

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(outputFile, output);
    }
}
