package de.flammenfuchs.javalib.util;

import java.util.StringJoiner;

public class StringUtil {

    /**
     * Format a String Array into a readable String
     *
     * @param delimiter the {@link String} between two Strings of the array
     * @param prefix the {@link String} before the first {@link String} of the array
     * @param suffix the {@link String} after the last {@link String} of the array
     * @param elements the String Array
     * @return the formatted {@link String}
     */
    public static String stringArrayToString(String delimiter, String prefix, String suffix, String... elements) {
        StringJoiner joiner = new StringJoiner(delimiter, prefix, suffix);
        for (String string : elements) {
            joiner.add(string);
        }
        return joiner.toString();
    }

    /**
     * Format a String Array into a readable String
     *
     * @param delimiter the {@link String} between two Strings of the array
     * @param elements the String Array
     * @return the formatted {@link String}
     */
    public static String stringArrayToString(String delimiter, String... elements) {
        return stringArrayToString(delimiter, null, null, elements);
    }

    /**
     * Check if a string only contains the letters from A to Z and a to z and numbers from 0 to 9
     *
     * @param var1 the checked {@link String}
     * @return true if it is alphanumerical
     */
    public static boolean isAlphaNumerical(String var1) {
        return var1.matches("[A-Za-z0-9]+");
    }
}
