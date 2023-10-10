package de.flammenfuchs.javalib.util;

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
}
