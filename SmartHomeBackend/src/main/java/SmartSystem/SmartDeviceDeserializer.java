package SmartSystem;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.logging.Logger;

public class SmartDeviceDeserializer extends JsonDeserializer<SmartDevice> {
    private static final Logger logger = Logger.getLogger(SmartDeviceDeserializer.class.getName());

    @Override
    public SmartDevice deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode node = mapper.readTree(jp);
        JsonNode deviceTypeNode = node.get("deviceType");

        if (deviceTypeNode == null) {
            logger.warning("Missing deviceType field in JSON data: " + node.toString());
            return null; // or throw an exception if you prefer
        }

        String deviceType = deviceTypeNode.asText();

        return switch (deviceType) {
            case "Blinds" -> mapper.treeToValue(node, Blinds.class);
            case "Fridge" -> mapper.treeToValue(node, Fridge.class);
            case "HeatingSystem" -> mapper.treeToValue(node, HeatingSystem.class);
            case "Oven" -> mapper.treeToValue(node, Oven.class);
            case "Lights" -> mapper.treeToValue(node, Lights.class);
            case "Locks" -> mapper.treeToValue(node, Locks.class);
            case "Rumba" -> mapper.treeToValue(node, Rumba.class);
            case "SoundSystem" -> mapper.treeToValue(node, SoundSystem.class);
            default -> throw new IllegalArgumentException("Unknown device type: " + deviceType);
        };
    }
}