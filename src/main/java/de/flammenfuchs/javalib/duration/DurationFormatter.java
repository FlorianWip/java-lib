package de.flammenfuchs.javalib.duration;

import java.time.Duration;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

/**
 * Util class to format durations to a readable/pretty format
 */
public class DurationFormatter {

    /**
     * Format a Duration to a readable/pretty format
     *
     * @param duration the {@link Duration} to format
     * @param lowestUnit the lowest {@link TimeUnit} to format e.g. TimeUnit.SECONDS
     * @param delimiter the delimiting string between all time units
     * @param trim should time units with 0 as value be trimmed out
     * @return the formatted string
     */
    public static String format(Duration duration, TimeUnit lowestUnit, String delimiter, boolean trim) {
        StringJoiner stringJoiner = new StringJoiner(delimiter);

        long days = duration.toDaysPart();
        if (!trim || days != 0L) {
            stringJoiner.add(days + "d");
        }
        if (lowestUnit != TimeUnit.DAYS) {
            long hours = duration.toHoursPart();
            if (!trim || hours != 0L) {
                stringJoiner.add(hours + "h");
            }
            if (lowestUnit != TimeUnit.HOURS) {
                long minutes = duration.toMinutesPart();
                if (!trim || minutes != 0L) {
                    stringJoiner.add(minutes + "min");
                }
                if (lowestUnit != TimeUnit.MINUTES) {
                    long seconds = duration.toSecondsPart();
                    if (!trim || seconds != 0L) {
                        stringJoiner.add(seconds + "sec");
                    }
                    if (lowestUnit != TimeUnit.SECONDS) {
                        long millis = duration.toMillisPart();
                        if (!trim || millis != 0L) {
                            stringJoiner.add(millis + "ms");
                        }
                    }
                }
            }
        }
        return stringJoiner.toString();
    }


    /**
     * Format a Duration to a readable/pretty format
     *
     * @param duration the {@link Duration} to format
     * @return the formatted string
     */
    public static String format(Duration duration) {
        return format(duration, TimeUnit.MILLISECONDS, " ", false);
    }


    /**
     * Format Milliseconds to a readable/pretty format
     *
     * @param millis the milliseconds to format
     * @return the formatted string
     */
    public static String format(long millis) {
        return format(Duration.ofMillis(millis), TimeUnit.MILLISECONDS, " ", false);
    }


    /**
     * Format a Duration to a readable/pretty format
     *
     * @param duration the {@link Duration} to format
     * @param lowestUnit the lowest {@link TimeUnit} to format e.g. TimeUnit.SECONDS
     * @return the formatted string
     */
    public static String format(Duration duration, TimeUnit lowestUnit) {
        return format(duration, lowestUnit, " ", false);
    }


    /**
     * Format a Duration to a readable/pretty format
     *
     * @param duration the {@link Duration} to format
     * @param delimiter the delimiting string between all time units
     * @return the formatted string
     */
    public static String format(Duration duration, String delimiter) {
        return format(duration, TimeUnit.MILLISECONDS, delimiter, false);
    }


    /**
     * Format a Duration to a readable/pretty format
     *
     * @param duration the {@link Duration} to format
     * @param trim should time units with 0 as value be trimmed out
     * @return the formatted string
     */
    public static String format(Duration duration, boolean trim) {
        return format(duration, TimeUnit.MILLISECONDS, " ", trim);
    }


    /**
     * Format a Duration to a readable/pretty format
     *
     * @param duration the {@link Duration} to format
     * @param lowestUnit the lowest {@link TimeUnit} to format e.g. TimeUnit.SECONDS
     * @param delimiter the delimiting string between all time units
     * @return the formatted string
     */
    public static String format(Duration duration, TimeUnit lowestUnit, String delimiter) {
        return format(duration, lowestUnit, delimiter, false);
    }


    /**
     * Format a Duration to a readable/pretty format
     *
     * @param duration the {@link Duration} to format
     * @param lowestUnit the lowest {@link TimeUnit} to format e.g. TimeUnit.SECONDS
     * @param trim should time units with 0 as value be trimmed out
     * @return the formatted string
     */
    public static String format(Duration duration, TimeUnit lowestUnit, boolean trim) {
        return format(duration, lowestUnit, " ", trim);
    }


    /**
     * Format a Duration to a readable/pretty format
     *
     * @param duration the {@link Duration} to format
     * @param delimiter the delimiting string between all time units
     * @param trim should time units with 0 as value be trimmed out
     * @return the formatted string
     */
    public static String format(Duration duration, String delimiter, boolean trim) {
        return format(duration, TimeUnit.MILLISECONDS, delimiter, trim);
    }
}
