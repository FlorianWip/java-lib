package de.flammenfuchs.javalib.lang.tuple;

/**
 * A container for 2 objects
 *
 * @param <A> type of first object
 * @param <B> type of second object
 */
public interface Tuple<A, B> {

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
     * Create a {@link FinalTuple}
     *
     * @param t the first object
     * @param u the second object
     * @return the created {@link FinalTuple}
     * @param <T> type of the first object
     * @param <U> type of the second object
     */
    static <T, U> FinalTuple<T, U> finalTuple(T t, U u) {
        return new FinalTuple<>(t, u);
    }

    /**
     * Create a {@link ModifiableTuple}
     *
     * @param t the first object
     * @param u the second object
     * @return the created {@link ModifiableTuple}
     * @param <T> type of the first object
     * @param <U> type of the second object
     */
    static <T, U> ModifiableTuple<T, U> modifiableTuple(T t, U u) {
        return new ModifiableTuple<>(t, u);
    }
}
