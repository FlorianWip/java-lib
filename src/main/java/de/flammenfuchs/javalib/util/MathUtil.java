package de.flammenfuchs.javalib.util;

public class MathUtil {

    public static double round(double d, int digits) {
        double factor = java.lang.Math.pow(10, digits);
        return java.lang.Math.round(d * factor) / factor;
    }

    public static float round(float d, int digits) {
        double factor = java.lang.Math.pow(10, digits);
        return (float) (java.lang.Math.round(d * factor) / factor);
    }
}
