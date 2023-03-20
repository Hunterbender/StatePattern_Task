package com.example._06_fassbender_state_task.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyManager {
    private String filename;
    private static PropertyManager instance;
    private final Properties properties = new Properties();

    public static PropertyManager getInstance() {
        return instance == null ? instance = new PropertyManager() : instance;
    }

    private PropertyManager() {
        this.filename = "dbtest.properties";
    }

    public void setFilename(String filename) {
        this.filename = filename;
        try {
            this.fillProperties();
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillProperties() throws URISyntaxException, IOException {
        URI uri = PropertyManager.class.getResource(this.filename).toURI();
        String str_path = Paths.get(uri).toString();
        Path path = Paths.get(str_path);

        try (FileReader fileReader = new FileReader(path.toString())) {
            this.properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readProperty(String key, String defaultValue) {
        return this.properties.getProperty(key, defaultValue);
    }
}
