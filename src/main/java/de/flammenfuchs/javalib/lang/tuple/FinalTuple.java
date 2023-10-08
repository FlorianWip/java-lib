package de.flammenfuchs.javalib.lang.tuple;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Getter
@Accessors(fluent = true)
public class FinalTuple<A, B> implements Tuple<A, B> {

    private final A a;
    private final B b;

}
