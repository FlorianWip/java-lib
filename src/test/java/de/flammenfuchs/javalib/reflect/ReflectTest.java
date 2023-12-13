package de.flammenfuchs.javalib.reflect;

import de.flammenfuchs.javalib.reflect.sample.Test2A;
import de.flammenfuchs.javalib.reflect.scanner.ClassScanner;
import de.flammenfuchs.javalib.reflect.scanner.DefaultClassScanner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReflectTest {

    @Test
    public void testClassScannerRecursivelyWithClass() {
        ClassScanner classScanner = new DefaultClassScanner(Test2A.class);
        assertEquals(3, classScanner.scan().size());
    }

    @Test
    public void testClassScannerRecursivelyWithObject() {
        ClassScanner classScanner = new DefaultClassScanner(new Test2A());
        assertEquals(3, classScanner.scan().size());
    }

    @Test
    public void testClassScannerRecursivelyWithString() {
        ClassScanner classScanner = new DefaultClassScanner("de.flammenfuchs.javalib.reflect.sample");
        assertEquals(3, classScanner.scan().size());
    }

    @Test
    public void testClassScannerWithIgnoredPackageWithClass() {
        ClassScanner classScanner = new DefaultClassScanner(Test2A.class);
        classScanner.addIgnoredPackages("de.flammenfuchs.javalib.reflect.sample.example");
        assertEquals(2, classScanner.scan().size());
    }

    @Test
    public void testClassScannerWithIgnoredPackageWithObject() {
        ClassScanner classScanner = new DefaultClassScanner(new Test2A());
        classScanner.addIgnoredPackages("de.flammenfuchs.javalib.reflect.sample.example");
        assertEquals(2, classScanner.scan().size());
    }

    @Test
    public void testClassScannerWithIgnoredPackageWithString() {
        ClassScanner classScanner = new DefaultClassScanner("de.flammenfuchs.javalib.reflect.sample");
        classScanner.addIgnoredPackages("de.flammenfuchs.javalib.reflect.sample.example");
        assertEquals(2, classScanner.scan().size());
    }

    @Test
    public void testClassScannerWithDisabledRecursiveSearchWithClass() {
        ClassScanner classScanner = new DefaultClassScanner(Test2A.class);
        classScanner.disableRecursiveSearch();
        assertEquals(2, classScanner.scan().size());
    }

    @Test
    public void testClassScannerWithDisabledRecursiveSearchWithObject() {
        ClassScanner classScanner = new DefaultClassScanner(new Test2A());
        classScanner.disableRecursiveSearch();
        assertEquals(2, classScanner.scan().size());
    }

    @Test
    public void testClassScannerWithDisabledRecursiveSearchWithString() {
        ClassScanner classScanner = new DefaultClassScanner("de.flammenfuchs.javalib.reflect.sample");
        classScanner.disableRecursiveSearch();
        assertEquals(2, classScanner.scan().size());
    }
}
