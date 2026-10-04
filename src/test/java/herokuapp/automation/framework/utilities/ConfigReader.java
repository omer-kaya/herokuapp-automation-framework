package herokuapp.automation.framework.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static final Properties properties = new Properties();

    static {
        logger.info("config.properties dosyası classpath üzerinden okunuyor...");

        try (InputStream inputStream = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (inputStream == null) {
                logger.error("config.properties classpath'te bulunamadı!");
                throw new RuntimeException("Kritik hata: config.properties dosyası eksik!");
            }

            try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                properties.load(reader);
            }

            logger.info("config.properties yüklendi. Tarayıcı: {} | baseUrl: {}",
                    properties.getProperty("browser"), properties.getProperty("baseUrl"));

        } catch (IOException e) {
            logger.error("config.properties okunurken IO hatası: {}", e.getMessage());
            throw new RuntimeException("config.properties okunamadı!", e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Aranan property bulunamadı: {}", key);
            return null;
        }
        return value.trim();
    }

    public static int getIntProperty(String key) {
        String value = getProperty(key);
        if (value == null) {
            throw new RuntimeException("'" + key + "' anahtarı config.properties içinde yok!");
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new RuntimeException("'" + key + "' sayıya çevrilemedi, değer: " + value, e);
        }
    }

    public static boolean getBooleanProperty(String key) {
        String value = getProperty(key);
        return value != null && Boolean.parseBoolean(value);
    }
}