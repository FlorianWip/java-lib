package de.flammenfuchs.javalib.reflect.scanner;

import de.flammenfuchs.javalib.lang.list.NotNullArrayList;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DefaultClassScanner implements ClassScanner {

    private final String packageName;
    private final ClassLoader classLoader;

    private boolean recursiveSearch = true;
    private List<String> ignoredPackages = new ArrayList<>();

    public DefaultClassScanner(String packageName, ClassLoader classLoader) {
        this.packageName = packageName;
        this.classLoader = classLoader;
    }

    public DefaultClassScanner(Class<?> mainClass, ClassLoader classLoader) {
        this(mainClass.getPackageName(), classLoader);
    }

    public DefaultClassScanner(Object main, ClassLoader classLoader) {
        this(main.getClass().getPackageName(), classLoader);
    }

    public DefaultClassScanner(String packageName) {
        this(packageName, ClassLoader.getSystemClassLoader());
    }

    public DefaultClassScanner(Class<?> mainClass) {
        this(mainClass.getPackageName(), ClassLoader.getSystemClassLoader());
    }

    public DefaultClassScanner(Object main) {
        this(main.getClass().getPackageName(), ClassLoader.getSystemClassLoader());
    }

    @Override
    public ClassLoader getScanningClassLoader() {
        return classLoader;
    }

    @SneakyThrows
    public List<Class<?>> scan() {
        return lookup(packageName);
    }

    private List<Class<?>> lookup(String lookupName) {
        InputStream stream = getClass().getClassLoader()
                .getResourceAsStream(lookupName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        List<Class<?>> classes = new NotNullArrayList<>();
        List<String> lines = reader.lines().toList();
        for (String line : lines) {
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
            classes.add(getClass(line, lookupName));
        }
        return classes;
    }

    private Class<?> getClass(String className, String packageName) {
        try {
            return classLoader.loadClass(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public void addIgnoredPackages(String... packages) {
        Collections.addAll(this.ignoredPackages, packages);
    }

    @Override
    public void addIgnoredPackages(Collection<String> packages) {
        this.ignoredPackages.addAll(packages);
    }

    @Override
    public void disableRecursiveSearch() {
        recursiveSearch = false;
    }
}
