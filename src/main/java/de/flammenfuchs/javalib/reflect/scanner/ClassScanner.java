package de.flammenfuchs.javalib.reflect.scanner;

import java.util.Collection;
import java.util.List;

public interface ClassScanner {

    /**
     * Get the scanned class loader
     *
     * @return the lookup classloader
     */
    ClassLoader getScanningClassLoader();

    /**
     * Starts the scan in the top level package
     *
     * @return the found classes
     */
    List<Class<?>> scan();

    /**
     * Add packages to ignore in the scan
     *
     * @param packages packages to ignore
     */
    void addIgnoredPackages(String... packages);

    /**
     * Add packages to ignore in the scan
     *
     * @param packages packages to ignore
     */
    void addIgnoredPackages(Collection<String> packages);

    /**
     * Disable the recursive search in the package
     */
    void disableRecursiveSearch();
}
