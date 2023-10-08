package de.flammenfuchs.javalib.lang.triple;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Getter
@Accessors(fluent = true)
public class FinalTriple<A, B, C> implements Triple<A, B, C> {

    private final A a;
    private final B b;
    private final C c;

}
