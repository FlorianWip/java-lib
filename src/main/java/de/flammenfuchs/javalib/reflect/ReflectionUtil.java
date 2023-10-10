package de.flammenfuchs.javalib.reflect;

/**
 * A Util for Reflections
 */
public class ReflectionUtil {

    /**
     * Check if a class has an empty constructor
     *
     * @param clazz the {@link Class}
     * @return true if it is empty
     */
    public static boolean hasEmptyConstructor(Class<?> clazz) {
        try {
            clazz.getDeclaredConstructor();
            return true;
        } catch (NoSuchMethodException ex) {
            return false;
        }
    }
}
