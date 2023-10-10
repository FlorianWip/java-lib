package de.flammenfuchs.javalib.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Managing class for configs
 */
@Getter
public class Configuration {

    private final String directory;
    private final Gson gson;

    private final Map<Class<?>, ConfigurationContainer<?>> containers = new HashMap<>();

    /**
     * Initialize a Configuration
     *
     * @param directory Path to directory where the configs are
     * @param gson Used {@link Gson} instance to instantiate config objects, if null it creates it own instance
     */
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



    /**
     * Initialize a Configuration without an {@link Gson} instance
     *
     * @param directory Path to directory where the configs are
     */
    public Configuration(String directory) {
        this(directory, null);
    }

    /**
     * Get or create a {@link ConfigurationContainer} with savedFileName = null.
     *
     * @param cfgClass Class of the represented Config
     * @return the {@link ConfigurationContainer}
     * @param <T> Type of the represented Config
     */
    public <T> ConfigurationContainer<T> getContainer(Class<T> cfgClass) {
        return this.getContainer(cfgClass, null);
    }

    /**
     * Get or create q {@link ConfigurationContainer}
     * The name of the config file will be the simple className with the suffix ".json" if savedFileName is null
     *
     * @param cfgClass Class of the represented Config
     * @param savedFileName name of the config file
     * @return the {@link ConfigurationContainer}
     * @param <T> Type of the represented Config
     */
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

    /**
     * Get the object instance representing the config with saveFileName = null
     *
     * @param t Class of the config
     * @return instance of the config
     * @param <T> type of the config
     */
    public <T> T get(Class<T> t) {
        return get(t, null);
    }

    /**
     * Get the object instance representing
     * The name of the config file will be the simple className with the suffix ".json" if savedFileName is null
     *
     * @param t Class of the config
     * @param saveFileName name of the config file
     * @return instance of the config
     * @param <T> type of the config
     */
    public <T> T get(Class<T> t, String saveFileName) {
        return getContainer(t, saveFileName).getT();
    }
}
