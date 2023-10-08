package de.flammenfuchs.javalib.lang.tuple;

public interface Tuple<A, B> {

    A a();

    B b();

    static <T, U> FinalTuple<T, U> finalTuple(T t, U u) {
        return new FinalTuple<>(t, u);
    }

    static <T, U> ModifiableTuple<T, U> modifiableTuple(T t, U u) {
        return new ModifiableTuple<>(t, u);
    }
}
