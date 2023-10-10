package de.flammenfuchs.javalib.config;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * Configuration Object which contains all necessary objects belonging to this config
 *
 * @param <T> type of the contained config
 */
public class ConfigurationContainer<T> {

    private final String directoryName;
    private final String fileName;
    private final Gson gson;

    private final Class<T> configClass;

    private File file;

    @Getter
    private T t;

    /**
     * Initialize a {@link ConfigurationContainer} and load its belonging file
     *
     * @param directoryName path to directory where the config is located
     * @param fileName name of the config file
     * @param gson instance of {@link Gson} to instantiate the config object
     * @param configClass class of the config
     */
    public ConfigurationContainer(String directoryName, String fileName, Gson gson, Class<T> configClass) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        this.gson = gson;
        this.configClass = configClass;
        this.file = new File(this.directoryName, this.fileName);

        loadFile();
    }

    /**
     * Load the config file or load defaults if file is not existing
     */
    @SneakyThrows
    private void loadFile() {
        if (!this.file.exists()) {
            loadDefault();
        } else {
            this.t = gson.fromJson(new FileReader(file, StandardCharsets.UTF_8), configClass);
        }
    }

    /**
     * Load the config default values and save them into the file
     */
    @SneakyThrows
    public void loadDefault() {
        try {
            this.t = this.configClass.getConstructor().newInstance();
        } catch (NoSuchMethodException ex) {
            throw new IllegalArgumentException("Config needs an empty constructor");
        }
        save();
    }

    /**
     * Save current config object to file
     */
    @SneakyThrows
    public void save() {
        Writer writer = new FileWriter(file, StandardCharsets.UTF_8);
        this.gson.toJson(this.t, writer);
        writer.flush();
        writer.close();
    }
}
