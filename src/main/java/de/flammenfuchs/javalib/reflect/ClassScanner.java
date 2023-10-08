package de.flammenfuchs.javalib.reflect;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClassScanner {

    private final String packageName;

    private boolean recursiveSearch = true;

    @Setter
    private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    @Getter
    private List<String> ignoredPackages = new ArrayList<>();

    public ClassScanner(String mainClass) {
        this.packageName = mainClass;
    }

    public ClassScanner(Object mainClass) {
        this.packageName = mainClass.getClass().getPackage().getName();
    }

    public ClassScanner(Class<?> mainClass) {
        this.packageName = mainClass.getPackage().getName();
    }

    @SneakyThrows
    public List<Class<?>> scan() {
        return lookup(packageName);
    }

    private List<Class<?>> lookup(String lookupName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(lookupName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        List<Class<?>> classes = new ArrayList<>();

        for (String line : reader.lines().toList()) {
            if (!line.contains(".") && recursiveSearch) {
                classes.addAll(lookup(lookupName + "." + line));
                continue;
            }
            if (!line.endsWith(".class")) {
                continue;
            }
            boolean ignore = false;
            for (String rule : ignoredPackages) {
                if (lookupName.startsWith(rule)) {
                    ignore = true;
                    break;
                }
            }
            if (ignore) {
                continue;
            }
            classes.add(getClass(line, packageName));
        }
        return classes;
    }

    private Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public void addIgnoredPackages(String... packages) {
        this.ignoredPackages.addAll(List.of(packages));
    }

    public void addIgnoredPackages(Collection<String> packages) {
        this.ignoredPackages.addAll(packages);
    }

    public void disableRecursiveSearch() {
        recursiveSearch = false;
    }

}
