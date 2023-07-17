package de.flammenfuchs.javalib.reflect;

import lombok.Setter;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ClassScanner {

    private final String packageName;

    @Setter
    private ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    public ClassScanner(String mainClass) {
        this.packageName = mainClass;
    }

    public ClassScanner(Object mainClass) {
        this.packageName = mainClass.getClass().getPackageName();
    }

    public List<Class> scan() {
        final InputStream stream = classLoader.getResourceAsStream(
                packageName.replaceAll("[.]", "/"));
        final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .toList();
    }

    @SneakyThrows
    private Class getClass(String className, String packageName) {
        return Class.forName(packageName + "."
                + className.substring(0, className.lastIndexOf('.')));
    }
}
