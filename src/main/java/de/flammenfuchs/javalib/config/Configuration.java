package de.flammenfuchs.javalib.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Configuration {

    private final String directory;
    private final Gson gson;

    private final Map<Class<?>, ConfigurationContainer<?>> containers = new HashMap<>();

    public Configuration(String directory, Gson gson) {
        this.directory = directory;
        this.gson = gson != null ? gson :
        new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

        File directoryFile = new File(this.directory);
        if (directoryFile.exists() && !directoryFile.isDirectory()) {
            throw new IllegalStateException("directory is a file!");
        }
        directoryFile.mkdirs();
    }

    public Configuration(String name) {
        this(name, null);
    }


    public <T> ConfigurationContainer<T> getContainer(Class<T> cfgClass) {
        return this.getContainer(cfgClass, null);
    }

    public <T> ConfigurationContainer<T> getContainer(Class<T> cfgClass, String savedFileName) {
        if (!this.containers.containsKey(cfgClass)) {
            String fileName = savedFileName != null ? savedFileName
                    : cfgClass.getSimpleName().toLowerCase() + ".json";
            ConfigurationContainer<T> configurationContainer
                    = new ConfigurationContainer<>(this.directory, fileName, gson, cfgClass);
            this.containers.put(cfgClass, configurationContainer);
        }
        return (ConfigurationContainer<T>) this.containers.get(cfgClass);
    }

    public <T> T get(Class<T> t) {
        return get(t, null);
    }

    public <T> T get(Class<T> t, String saveFileName) {
        return getContainer(t, saveFileName).getT();
    }
}
