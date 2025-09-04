package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties props = new Properties();

    static {
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("testdata/config.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
