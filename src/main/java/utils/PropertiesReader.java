package utils;

import exceptions.PropertyReadingException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private static Properties properties;

    private static Properties readProperties() {

        FileReader reader;
        try {
            reader = new FileReader("configuration.properties");
        } catch (FileNotFoundException e) {
            throw new PropertyReadingException("Current fail is not available, please check your project path!", e.getCause());
        }

        Properties props = new Properties();

        try {
            props.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;

    }

    public static Properties getProperties() {
        if (properties == null) {
            properties = readProperties();
        }
        return properties;
    }
}
