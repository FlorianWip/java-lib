package de.flammenfuchs.javalib;

import java.util.StringJoiner;

public class StringUtil {

    public static String stringArrayToString(String delimiter, String prefix, String suffix, String... elements) {
        StringJoiner joiner = new StringJoiner(delimiter, prefix, suffix);
        for (String string : elements) {
            joiner.add(string);
        }
        return joiner.toString();
    }

    public static String stringArrayToString(String delimiter, String... elements) {
        return stringArrayToString(delimiter, null, null, elements);
    }
}
