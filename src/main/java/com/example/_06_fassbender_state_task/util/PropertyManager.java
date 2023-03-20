package com.example._06_fassbender_state_task.util;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyManager {
    private String filename = "";
    private static PropertyManager instance;
    private final Properties properties = new Properties();
    private PropertyManager() {
        filename = "db.properties";
    }

    public static PropertyManager getInstance(){
        return instance == null ? instance = new PropertyManager() : instance;
    }

    public String getFilename(){
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
        try{
            this.fillProperties();
        }catch (URISyntaxException e){
            throw new RuntimeException(e);
        }
    }

    private void fillProperties() throws URISyntaxException{
        URI uri = PropertyManager.class.getResource(filename).toURI();
        String str_path = Paths.get(uri).toString();
        Path path = Paths.get(str_path);
        try (FileReader reader = new FileReader(path.toString())){
            this.properties.load(reader);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String readProperty(String key, String defaultValue){
        return this.properties.getProperty(key, defaultValue);
    }
}
