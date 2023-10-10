package de.flammenfuchs.javalib.lang.tuple;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * An implementation for {@link Tuple} with modifiable objects (not final)
 *
 * @param <A> Type of first object
 * @param <B> Type of second object
 */
@Getter
@Setter
@AllArgsConstructor
@Accessors(fluent = true)
public class ModifiableTuple<A, B> implements Tuple<A, B> {

    private A a;
    private B b;
}
