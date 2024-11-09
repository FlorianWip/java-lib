package de.flammenfuchs.javalib.config.v2;

import java.nio.file.Path;

public interface FileHandler {

    /**
     * Create a new JsonFileHandler in the current directory
     *
     * @return the created JsonFileHandler
     */
    static JsonFileHandler createJsonFileHandler() {
        return new JsonFileHandler(".");
    }

    /**
     * Load a configuration file from the given path
     *
     * @param path the path to the file
     * @param clazz the class of the configuration
     * @return the loaded configuration
     * @param <T> the type of the configuration
     */
    <T> T loadFile(String path, Class<T> clazz);

    /**
     * Load a configuration file from the given path
     *
     * @param path the path to the file
     * @param clazz the class of the configuration
     * @return the loaded configuration
     * @param <T> the type of the configuration
     */
    <T> T loadFile(Path path, Class<T> clazz);

    /**
     * Reset a configuration file to its default values
     *
     * @param path the path to the file
     * @param clazz the class of the configuration
     * @param <T> the type of the configuration
     */
    <T> void resetFile(String path, Class<T> clazz);

    /**
     * Save a configuration file to the given path
     *
     * @param path the path to the file
     * @param object the configuration object
     * @param <T> the type of the configuration
     */
    <T> void saveFile(String path, T object);

    /**
     * Save a configuration file to the given path
     *
     * @param path the path to the file
     * @param object the configuration object
     * @param <T> the type of the configuration
     */
    <T> void saveFile(Path path, T object);

}
