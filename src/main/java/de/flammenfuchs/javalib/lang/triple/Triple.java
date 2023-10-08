package de.flammenfuchs.javalib.lang.triple;

public interface Triple<A, B, C> {

    A a();

    B b();

    C c();

    static <T, U, V> FinalTriple<T, U, V> finalTriple(T t, U u, V v) {
        return new FinalTriple<>(t, u, v);
    }

    static <T, U, V> ModifiableTriple<T, U, V> modifiableTriple(T t, U u, V v) {
        return new ModifiableTriple<>(t, u, v);
    }
}
