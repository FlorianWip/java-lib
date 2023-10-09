package de.flammenfuchs.javalib.duration;

import java.time.Duration;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

public class DurationFormatter {

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

    public static String format(Duration duration) {
        return format(duration, TimeUnit.MILLISECONDS, " ", false);
    }

    public static String format(long millis) {
        return format(Duration.ofMillis(millis), TimeUnit.MILLISECONDS, " ", false);
    }

    public static String format(Duration duration, TimeUnit lowestUnit) {
        return format(duration, lowestUnit, " ", false);
    }

    public static String format(Duration duration, String delimiter) {
        return format(duration, TimeUnit.MILLISECONDS, delimiter, false);
    }

    public static String format(Duration duration, boolean trim) {
        return format(duration, TimeUnit.MILLISECONDS, " ", trim);
    }

    public static String format(Duration duration, TimeUnit lowestUnit, String delimiter) {
        return format(duration, lowestUnit, delimiter, false);
    }

    public static String format(Duration duration, TimeUnit lowestUnit, boolean trim) {
        return format(duration, lowestUnit, " ", trim);
    }

    public static String format(Duration duration, String delimiter, boolean trim) {
        return format(duration, TimeUnit.MILLISECONDS, delimiter, trim);
    }
}
