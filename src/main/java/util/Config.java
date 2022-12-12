package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {

    private static final Config INSTANCE = new Config();
    private static final Logger LOGGER = Logger.getLogger(Config.class.getName());
    private final Properties props = new Properties();

    private Config() {
        loadSettings();
    }

    private void loadSettings() {
        String path = getClass().getResource("/config/config.properties").getPath();

        try(FileInputStream fis = new FileInputStream(path)) {
            props.load(fis);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Unable to retrieve config settings.", ex);
            throw new MovieException("Unable to retrieve config settings.");
        }
    }

    public String getSetting(String key) {
        return props.getProperty(key);
    }

    public static Config getInstance() {
        return INSTANCE;
    }
}
