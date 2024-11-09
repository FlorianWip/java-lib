package de.flammenfuchs.javalib.config.v2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class JsonFileHandler implements FileHandler {

    private final String basePath;

    private final Gson gson;

    public JsonFileHandler(String basePath, Gson gson) {
        this.basePath = basePath;
        this.gson = gson;
    }

    public JsonFileHandler(String basePath) {
        this(basePath, new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create());
    }

    @Override
    public <T> T loadFile(String path, Class<T> clazz) {
        return loadFile(Path.of(basePath, path), clazz);
    }

    @Override
    @SneakyThrows
    public <T> T loadFile(Path path, Class<T> clazz) {
        if (!path.toFile().exists()) {
            resetFile(path.toString(), clazz);
        }
        return gson.fromJson(new FileReader(path.toFile(), StandardCharsets.UTF_8), clazz);
    }

    @Override
    public <T> void resetFile(String path, Class<T> clazz) {
        T instance = instantiate(clazz);
        saveFile(path, instance);
    }

    @Override
    public <T> void saveFile(String path, T object) {
        saveFile(Path.of(basePath, path), object);
    }

    @Override
    public <T> void saveFile(Path path, T object) {
        try (var writer = new FileWriter(path.toFile())) {
            gson.toJson(object, writer);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T instantiate(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Could not instantiate class " + clazz.getName(), e);
        }
    }
}
