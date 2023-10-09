package de.flammenfuchs.javalib.duration;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DurationFormatterTest {

    private static final long DURATION_VALUE = 126_003_001;

    @Test
    public void testDurationFormatterWithAllTimeUnits() {
        assertEquals("1d 11h 0min 3sec 1ms", DurationFormatter.format(Duration.ofMillis(DURATION_VALUE)));
    }

    @Test
    public void testDurationFormatterWithAllTimeUnitsAsMillis() {
        assertEquals("1d 11h 0min 3sec 1ms", DurationFormatter.format(DURATION_VALUE));
    }

    @Test
    public void testDurationFormatterWithAllTimeUnitsTrimmed() {
        Duration duration = Duration.ofMillis(DURATION_VALUE);
        assertEquals("1d 11h 3sec 1ms", DurationFormatter.format(duration, true));
    }

    @Test
    public void testDurationFormatterWithAllTimeUnitsWithHigherLowestUnit() {
        Duration duration = Duration.ofMillis(DURATION_VALUE);
        assertEquals("1d 11h 0min 3sec", DurationFormatter.format(duration, TimeUnit.SECONDS));
    }

    @Test
    public void testDurationFormatterWithAllTimeUnitsWithDelimiter() {
        Duration duration = Duration.ofMillis(DURATION_VALUE);
        assertEquals("1d, 11h, 0min, 3sec, 1ms", DurationFormatter.format(duration, ", "));
    }

}
