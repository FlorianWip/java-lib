package de.flammenfuchs.javalib.io;

import java.io.File;
import java.util.function.Consumer;

/**
 * Util to set up directories for your application
 */
public class FileUtil {

    /**
     * Create a directory if not existing. The creation will work recursively
     *
     * @param path path to the directory
     * @param onCreate a {@link Consumer} with the directory file. It will be called if the directory was created
     * @param onExist a {@link Consumer} with the directory file. It will be called if the directory already exist
     * @param onFail  a {@link Consumer} with an {@link Exception}. It will be called if the creation or loading fails
     */
    public static void generateDirectory(String path, Consumer<File> onCreate, Consumer<File> onExist,
                                         Consumer<Exception> onFail) {
        try {
            File directory = new File(path);
            if (directory.mkdirs()) {
                if (onCreate != null) {
                    onCreate.accept(directory);
                } else {
                    if (!directory.isDirectory()) {
                        throw new IllegalArgumentException("Path points to a file");
                    }
                    if (!directory.exists()) {
                        throw new Exception("Directory does not create");
                    }
                    onExist.accept(directory);
                }
            }
        } catch (Exception ex) {
            if (onFail != null) {
                onFail.accept(ex);
            } else {
                ex.printStackTrace();
            }
        }
    }


    /**
     * Create a directory if not existing. The creation will work recursively
     *
     * @param path path to the directory
     * @param onCreate a {@link Consumer} with the directory file. It will be called if the directory was created
     * @param onExist a {@link Consumer} with the directory file. It will be called if the directory already exist
     */
    public static void generateDirectory(String path, Consumer<File> onCreate, Consumer<File> onExist) {
        generateDirectory(path, onCreate, onExist, null);
    }


    /**
     * Create a directory if not existing. The creation will work recursively
     *
     * @param path path to the directory
     * @param onCreate a {@link Consumer} with the directory file. It will be called if the directory was created
     */
    public static void generateDirectory(String path, Consumer<File> onCreate) {
        generateDirectory(path, onCreate, null, null);
    }


    /**
     * Create a directory if not existing. The creation will work recursively
     *
     * @param path path to the directory
     */
    public static void generateDirectory(String path) {
        generateDirectory(path, null
                , null, null);
    }
}
