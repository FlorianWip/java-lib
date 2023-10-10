package de.flammenfuchs.javalib.lang.triple;

/**
 * A container for 3 objects
 *
 * @param <A> type of first object
 * @param <B> type of second object
 * @param <C> type of third object
 */
public interface Triple<A, B, C> {

    /**
     * Get the first object
     *
     * @return the first object
     */
    A a();

    /**
     * Get the second object
     *
     * @return the second object
     */
    B b();

    /**
     * Get the third object
     *
     * @return the third object
     */
    C c();

    /**
     * Create a {@link FinalTriple}
     *
     * @param t the first object
     * @param u the second object
     * @param v the third object
     * @return the created {@link FinalTriple}
     * @param <T> type of the first object
     * @param <U> type of the second object
     * @param <V> type of the third object
     */
    static <T, U, V> FinalTriple<T, U, V> finalTriple(T t, U u, V v) {
        return new FinalTriple<>(t, u, v);
    }

    /**
     * Create a {@link ModifiableTriple}
     *
     * @param t the first object
     * @param u the second object
     * @param v the third object
     * @return the created {@link ModifiableTriple}
     * @param <T> type of the first object
     * @param <U> type of the second object
     * @param <V> type of the third object
     */
    static <T, U, V> ModifiableTriple<T, U, V> modifiableTriple(T t, U u, V v) {
        return new ModifiableTriple<>(t, u, v);
    }
}
