package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ConfigManager {
    private static final String CONFIG_FILE = "/config.json";
    private static final Map<String, String> config;

    static {
        try (InputStream inputStream = ConfigManager.class.getResourceAsStream(CONFIG_FILE)) {
            if (inputStream == null) {
                throw new RuntimeException("Configuration file not found: " + CONFIG_FILE);
            }
            ObjectMapper mapper = new ObjectMapper();
            config = mapper.readValue(inputStream, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public static String getBaseUrl() {
        return config.get("baseUrl");
    }
}
