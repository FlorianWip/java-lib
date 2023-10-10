package de.flammenfuchs.javalib.lang.tuple;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * An implementation for {@link Tuple} with unmodifiable objects (final)
 *
 * @param <A> Type of first object
 * @param <B> Type of second object
 */
@RequiredArgsConstructor
@Getter
@Accessors(fluent = true)
public class FinalTuple<A, B> implements Tuple<A, B> {

    private final A a;
    private final B b;

}
