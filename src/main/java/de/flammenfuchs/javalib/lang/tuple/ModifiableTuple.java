package de.flammenfuchs.javalib.lang.tuple;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@Accessors(fluent = true)
public class ModifiableTuple<A, B> implements Tuple<A, B> {

    private A a;
    private B b;
}
