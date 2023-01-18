package de.flammenfuchs.javalib.duration;

import java.time.Duration;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

public class DurationFormatter {

    public static String format(long millis) {
        long remaining = millis;
        StringJoiner format = new StringJoiner(" ");
        long days = TimeUnit.MILLISECONDS.toDays(remaining);
        if (days > 0) {
            format.add(days + "d");
            remaining -= days * 24 * 60 * 60 * 1000;
        }
        long hours = TimeUnit.MILLISECONDS.toHours(remaining);
        if ((days > 0 || hours > 0) && remaining > 0) {
            format.add(hours + "h");
            remaining -= hours * 60 * 60 * 1000;
        }
        long minutes = TimeUnit.MILLISECONDS.toMinutes(remaining);
        if ((days > 0 || hours > 0 || minutes > 0) && remaining > 0) {
            format.add(minutes + "min");
            remaining -= minutes * 60 * 1000;
        }
        long seconds = TimeUnit.MILLISECONDS.toSeconds(remaining);
        if ((days > 0 || hours > 0 || minutes > 0 || seconds > 0) && remaining > 0) {
            format.add(seconds + "sec");
            remaining -= seconds * 1000;
        }
        if (remaining > 0) {
            format.add(remaining + "ms");
        }
        return format.toString();
    }

    public static String format(Duration duration) {
        return format(duration.toMillis());
    }
}
