package de.flammenfuchs.javalib.reflect;

import de.flammenfuchs.javalib.lang.list.NotNullArrayList;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class allows you to scan through packages
 */
public class ClassScanner {

    private final String packageName;

    private boolean recursiveSearch = true;

    @Setter
    private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    @Getter
    private List<String> ignoredPackages = new ArrayList<>();

    /**
     * Create a ClassScanner
     *
     * @param mainClass the package name
     */
    public ClassScanner(String mainClass) {
        this.packageName = mainClass;
    }

    /**
     * Create a ClassScanner
     *
     * @param mainClass an object in the searching package
     */
    public ClassScanner(Object mainClass) {
        this.packageName = mainClass.getClass().getPackage().getName();
    }

    /**
     * Create a ClassScanner
     *
     * @param mainClass a class in the searching package
     */
    public ClassScanner(Class<?> mainClass) {
        this.packageName = mainClass.getPackage().getName();
    }

    /**
     * Starts the scan in the top level package
     *
     * @return the found classes
     */
    @SneakyThrows
    public List<Class<?>> scan() {
        return lookup(packageName);
    }

    /**
     * Scan a package
     *
     * @param lookupName the package to scan
     * @return fount classes
     */
    private List<Class<?>> lookup(String lookupName) {
        InputStream stream = classLoader
                .getResourceAsStream(lookupName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        List<Class<?>> classes = new NotNullArrayList<>();

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
            classes.add(getClass(line, lookupName));
        }
        return classes;
    }

    /**
     * Load a class from the classloader
     *
     * @param className simple or complete name of the class
     * @param packageName its package
     * @return the {@link Class}
     */
    private Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Add packages to ignore in the scan
     *
     * @param packages packages to ignore
     */
    public void addIgnoredPackages(String... packages) {
        this.ignoredPackages.addAll(List.of(packages));
    }

    /**
     * Add packages to ignore in the scan
     *
     * @param packages packages to ignore
     */
    public void addIgnoredPackages(Collection<String> packages) {
        this.ignoredPackages.addAll(packages);
    }

    /**
     * Disable the recursive search in the package
     */
    public void disableRecursiveSearch() {
        recursiveSearch = false;
    }

}
