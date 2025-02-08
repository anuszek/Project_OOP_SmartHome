package SmartSystem;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String serialize(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T deserialize(String json, Class<T> clazz) throws Exception {
        return objectMapper.readValue(json, clazz);
    }

    public static String modifyJson(String json) {
        try {
           String newJson = removeParameters(json, "deviceType");
              return newJson;
        } catch (Exception e) {
            return null;
        }
    }
    public static String removeParameters(String json, String... keysToRemove) throws Exception {
        // Deserialize JSON string into a Map
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        // Remove the specified keys
        for (String key : keysToRemove) {
            map.remove(key);
        }

        // Serialize the Map back into a JSON string
        return objectMapper.writeValueAsString(map);
    }
    public static String extractDeviceType(String json) {
        try {
            Map<String, Object> map = objectMapper.readValue(json, Map.class);
            return (String) map.get("deviceType");
        } catch (Exception e) {
            return null;
        }
    }
    public static String extractDeviceID(String json) {
        try {
            Map<String, Object> map = objectMapper.readValue(json, Map.class);
            return (String) map.get("deviceID");
        } catch (Exception e) {
            return null;
        }
    }
}