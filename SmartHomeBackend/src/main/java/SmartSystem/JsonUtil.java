package SmartSystem;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String serialize(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T deserialize(String json, Class<T> clazz) throws Exception {
        return objectMapper.readValue(json, clazz);
    }
}