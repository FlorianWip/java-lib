package de.flammenfuchs.javalib.lang.triple;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * An implementation for {@link Triple} with modifiable objects (not final)
 *
 * @param <A> Type of first object
 * @param <B> Type of second object
 * @param <C> Type of third object
 */
@Getter
@Setter
@AllArgsConstructor
@Accessors(fluent = true)
public class ModifiableTriple<A, B, C> implements Triple<A, B, C> {

    private A a;
    private B b;
    private C c;
}
