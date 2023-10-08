package de.flammenfuchs.javalib.reflect;

import de.flammenfuchs.javalib.reflect.sample.test1.Test1A;
import de.flammenfuchs.javalib.reflect.sample.test2.Test2A;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReflectTest {

    @Test
    public void testClassScannerWithClass() {
        ClassScanner classScanner = new ClassScanner(Test1A.class);
        assertEquals(2, classScanner.scan().size());
    }

    @Test
    public void testClassScannerWithObject() {
        ClassScanner classScanner = new ClassScanner(new Test1A());
        assertEquals(2, classScanner.scan().size());
    }

    @Test
    public void testClassScannerWithString() {
        ClassScanner classScanner = new ClassScanner("de.flammenfuchs.javalib.reflect.sample.test1");
        assertEquals(2, classScanner.scan().size());
    }

    @Test
    public void testClassScannerRecursivelyWithClass() {
        ClassScanner classScanner = new ClassScanner(Test2A.class);
        assertEquals(3, classScanner.scan().size());
    }

    @Test
    public void testClassScannerRecursivelyWithObject() {
        ClassScanner classScanner = new ClassScanner(new Test2A());
        assertEquals(3, classScanner.scan().size());
    }

    @Test
    public void testClassScannerRecursivelyWithString() {
        ClassScanner classScanner = new ClassScanner("de.flammenfuchs.javalib.reflect.sample.test2");
        assertEquals(3, classScanner.scan().size());
    }

    @Test
    public void testClassScannerWithIgnoredPackageWithClass() {
        ClassScanner classScanner = new ClassScanner(Test2A.class);
        classScanner.addIgnoredPackages("de.flammenfuchs.javalib.reflect.sample.test2.example");
        assertEquals(2, classScanner.scan().size());
    }

    @Test
    public void testClassScannerWithIgnoredPackageWithObject() {
        ClassScanner classScanner = new ClassScanner(new Test2A());
        classScanner.addIgnoredPackages("de.flammenfuchs.javalib.reflect.sample.test2.example");
        assertEquals(2, classScanner.scan().size());
    }

    @Test
    public void testClassScannerWithIgnoredPackageWithString() {
        ClassScanner classScanner = new ClassScanner("de.flammenfuchs.javalib.reflect.sample.test2");
        classScanner.addIgnoredPackages("de.flammenfuchs.javalib.reflect.sample.test2.example");
        assertEquals(2, classScanner.scan().size());
    }

    @Test
    public void testClassScannerWithDisabledRecursiveSearchWithClass() {
        ClassScanner classScanner = new ClassScanner(Test2A.class);
        classScanner.disableRecursiveSearch();
        assertEquals(2, classScanner.scan().size());
    }

    @Test
    public void testClassScannerWithDisabledRecursiveSearchWithObject() {
        ClassScanner classScanner = new ClassScanner(new Test2A());
        classScanner.disableRecursiveSearch();
        assertEquals(2, classScanner.scan().size());
    }

    @Test
    public void testClassScannerWithDisabledRecursiveSearchWithString() {
        ClassScanner classScanner = new ClassScanner("de.flammenfuchs.javalib.reflect.sample.test2");
        classScanner.disableRecursiveSearch();
        assertEquals(2, classScanner.scan().size());
    }
}
