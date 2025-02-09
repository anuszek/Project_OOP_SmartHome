package SmartSystem;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.logging.Logger;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = Logger.getLogger(JsonUtil.class.getName());

    public static String serialize(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T deserialize(String json, Class<T> clazz) throws Exception {
        return objectMapper.readValue(json, clazz);
    }

    public static String modifyJson(String json) {
        try {
            logger.info("Original JSON: " + json);
            return removeParameters(json, "deviceType");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String removeParameters(String json, String key) throws Exception {
        // Deserialize JSON safely
        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});

        // Remove deviceType key safely
        map.remove(key);

        // Serialize back to valid JSON
        return objectMapper.writeValueAsString(map);
    }

    public static String extractDeviceType(String json) {
        try {
            Map<String, Object> map = objectMapper.readValue(json, Map.class);
            return (String) map.get("deviceType");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String extractDeviceID(String json) {
        try {
            Map<String, Object> map = objectMapper.readValue(json, Map.class);
            return (String) map.get("deviceID");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}