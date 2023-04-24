package de.flammenfuchs.javalib.io;

import java.io.File;
import java.util.function.Consumer;

public class FileUtil {

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

    public static void generateDirectory(String path, Consumer<File> onCreate, Consumer<File> onExist) {
        generateDirectory(path, onCreate, onExist, null);
    }

    public static void generateDirectory(String path, Consumer<File> onCreate) {
        generateDirectory(path, onCreate, null, null);
    }

    public static void generateDirectory(String path) {
        generateDirectory(path, null
                , null, null);
    }
}
