package com.assignment.filereaders;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.*;

import static org.testng.Assert.*;

public class PropertyReader {

    private PropertyReader() {
    }

    private static Set<String> pathSet = new TreeSet<>();

    public static void setProperty(String propertyPath) {
        if (!pathSet.contains(propertyPath)) {
            pathSet.add(propertyPath);
            File propertyFile = FileUtils.getFile(propertyPath);
            if (propertyFile.exists()) {
                setSystemProperty(propertyFile);
            } else
                fail("Please set the path of property File correctly");
        } else {
            //TODO Log
        }
    }

    private static void setSystemProperty (File propertyFile) {
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(propertyFile.toPath())) {
            properties.load(inputStream);
            for (String key : properties.stringPropertyNames()) {
                if (System.getProperty(key) == null || System.getProperty(key).isEmpty()) {
                    System.setProperty(key, properties.getProperty(key));
                }
            }
        } catch (Exception e) {
            //TODO Log
            e.printStackTrace();
        }
    }

    public static String getProperty (Enum property) {
        return Objects.isNull(System.getProperty(property.name())) ? "" : System.getProperty(property.name());
    }
}
