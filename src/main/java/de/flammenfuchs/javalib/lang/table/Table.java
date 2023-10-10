package de.flammenfuchs.javalib.lang.table;

import de.flammenfuchs.javalib.lang.triple.Triple;
import de.flammenfuchs.javalib.lang.tuple.Tuple;

import java.util.Collection;
import java.util.Set;

/**
 * A table allows you to link a value to two different keys
 *
 * @param <T> type of first key
 * @param <U> type of second key
 * @param <V> type of value
 */
public interface Table <T, U, V> {

    /**
     * Size of the Table (how many values are stored)
     *
     * @return the size
     */
    int size();

    /**
     * Check if the is table empty
     *
     * @return true if size == 0
     */
    boolean isEmpty();

    /**
     * Check if this pair of keys exist
     *
     * @param t key 1 to lookup
     * @param u key 2 to lookup
     * @return true if exist
     */
    boolean containsKey(T t, U u);


    /**
     * Check if this value exist
     *
     * @param v value to lookup
     * @return true if exist
     */
    boolean containsValue(V v);

    /**
     * Get a value from the table
     *
     * @param t key 1 to lookup
     * @param u key 2 to lookup
     * @return the representing value or null if not exist
     */
    V get(T t, U u);

    /**
     * Put a value into the table
     *
     * @param t key 1
     * @param u key 2
     * @param v the representing value
     * @return the representing value
     */
    V put(T t, U u, V v);

    /**
     * Remove a value from the table
     *
     * @param t key 1
     * @param u key 2
     * @return the removed value or null if not existed
     */
    V remove(T t, U u);

    /**
     * Put all elements of another table into this table
     *
     * @param table the {@link Table} to add into this table
     */
    void putAll(Table<T, U, V> table);

    /**
     * Clear all values from this table
     */
    void clear();

    /**
     * Get a {@link Set} with all keys in the table as a {@link Tuple}
     *
     * @return the {@link Set}
     */
    Set<Tuple<T, U>> keySet();

    /**
     * Get a {@link Collection} with all values in the table
     *
     * @return the {@link Collection}
     */
    Collection<V> values();

    /**
     * Get a {@link Set} with all elements of the table as a {@link Triple}
     *
     * @return the {@link Triple}
     */
    Set<Triple<T, U, V>> elements();

    /**
     * Get a value from the map or return the default value
     * NOTE: The default value will not be inserted
     *
     * @param t key 1
     * @param u key 2
     * @param defaultValue the default value
     * @return the value from the map or if not exist the default value
     */
    V getOrDefault(T t, U u, V defaultValue);


    /**
     * Put the given value in the map when no value is existing in the table
     *
     * @param t key 1
     * @param u key 2
     * @param value the given value
     * @return the already existing value in the table or the given value if inserted
     */
    V putIfAbsent(T t, U u, V value);
}
