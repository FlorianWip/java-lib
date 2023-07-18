package de.flammenfuchs.javalib.reflect;

public class ReflectionUtil {

    public static boolean hasEmptyConstructor(Class<?> clazz) {
        try {
            clazz.getDeclaredConstructor();
            return true;
        } catch (NoSuchMethodException ex) {
            return false;
        }
    }
}
