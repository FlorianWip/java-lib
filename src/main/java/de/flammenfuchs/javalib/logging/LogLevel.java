package de.flammenfuchs.javalib.logging;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * The level of logging for the {@link Logger}
 */
@RequiredArgsConstructor
@Getter
@Accessors(fluent = true)
public enum LogLevel {
    /**
     * Disable logging
     */
    NONE(0),
    /**
     * Basic logging. Show current state and elapsed time
     */
    BASIC(1),
    /**
     * Log all
     */
    EXTENDED(2);

    private final int level;
}
