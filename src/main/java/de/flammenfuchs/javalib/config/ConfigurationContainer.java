package de.flammenfuchs.javalib.config;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class ConfigurationContainer<T> {

    private final String directoryName;
    private final String fileName;
    private final Gson gson;

    private final Class<T> configClass;

    private File file;

    @Getter
    private T t;

    public ConfigurationContainer(String directoryName, String fileName, Gson gson, Class<T> configClass) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        this.gson = gson;
        this.configClass = configClass;
        this.file = new File(this.directoryName, this.fileName);

        loadFile();
    }

    @SneakyThrows
    private void loadFile() {
        if (!this.file.exists()) {
            loadDefault();
        } else {
            this.t = gson.fromJson(new FileReader(file, StandardCharsets.UTF_8), configClass);
        }
    }

    @SneakyThrows
    public void loadDefault() {
        try {
            this.t = this.configClass.getConstructor().newInstance();
        } catch (NoSuchMethodException ex) {
            throw new IllegalArgumentException("Config needs an empty constructor");
        }
        save();
    }

    @SneakyThrows
    public void save() {
        Writer writer = new FileWriter(file, StandardCharsets.UTF_8);
        this.gson.toJson(this.t, writer);
        writer.flush();
        writer.close();
    }
}
