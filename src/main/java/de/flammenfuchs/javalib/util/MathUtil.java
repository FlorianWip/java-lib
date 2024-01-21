package de.flammenfuchs.javalib.util;

import lombok.val;

/**
 * A util for calculations, math etc.
 */
public class MathUtil {

    /**
     * Round a double
     *
     * @param d the double to round
     * @param digits how many digits after the separator
     * @return the rounded double
     */
    public static double round(double d, int digits) {
        double factor = java.lang.Math.pow(10, digits);
        return java.lang.Math.round(d * factor) / factor;
    }

    /**
     * Round a float
     *
     * @param d the float to round
     * @param digits how many digits after the separator
     * @return the rounded float
     */
    public static float round(float d, int digits) {
        double factor = java.lang.Math.pow(10, digits);
        return (float) (java.lang.Math.round(d * factor) / factor);
    }

    /**
     * Check if value is in range
     *
     *
     * @param val value to check
     * @param min minimal value
     * @param max maximum value
     * @return value in or not in range (min/max included)
     */
    public static boolean isInRange(byte val, byte min, byte max) {
        return val >= min && val <= max;
    }

    /**
     * Check if value is in range
     *
     *
     * @param val value to check
     * @param min minimal value
     * @param max maximum value
     * @return value in or not in range (min/max included)
     */
    public static boolean isInRange(short val, short min, short max) {
        return val >= min && val <= max;
    }

    /**
     * Check if value is in range
     *
     *
     * @param val value to check
     * @param min minimal value
     * @param max maximum value
     * @return value in or not in range (min/max included)
     */
    public static boolean isInRange(int val, int min, int max) {
        return val >= min && val <= max;
    }

    /**
     * Check if value is in range
     *
     *
     * @param val value to check
     * @param min minimal value
     * @param max maximum value
     * @return value in or not in range (min/max included)
     */
    public static boolean isInRange(long val, long min, long max) {
        return val >= min && val <= max;
    }

    /**
     * Check if value is in range
     *
     *
     * @param val value to check
     * @param min minimal value
     * @param max maximum value
     * @return value in or not in range (min/max included)
     */
    public static boolean isInRange(float val, float min, float max) {
        return val >= min && val <= max;
    }

    /**
     * Check if value is in range
     *
     *
     * @param val value to check
     * @param min minimal value
     * @param max maximum value
     * @return value in or not in range (min/max included)
     */
    public static boolean isInRange(double val, double min, double max) {
        return val >= min && val <= max;
    }
}
