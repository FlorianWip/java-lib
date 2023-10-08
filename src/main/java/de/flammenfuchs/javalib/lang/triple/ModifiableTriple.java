package de.flammenfuchs.javalib.lang.triple;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@Accessors(fluent = true)
public class ModifiableTriple<A, B, C> implements Triple<A, B, C> {

    private A a;
    private B b;
    private C c;
}
