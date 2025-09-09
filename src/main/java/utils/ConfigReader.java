package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    public static void loadConfig() {
        if (prop == null) {
            prop = new Properties();
            try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (input == null) {
                    throw new RuntimeException("❌ config.properties not found in resources folder");
                }
                prop.load(input);
            } catch (IOException e) {
                throw new RuntimeException("❌ Failed to load config.properties", e);
            }
        }
    }

    public static String getProperty(String key) {
        if (prop == null) loadConfig();
        return prop.getProperty(key);
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }
}
