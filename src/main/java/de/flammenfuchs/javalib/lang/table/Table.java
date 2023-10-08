package de.flammenfuchs.javalib.lang.table;

import de.flammenfuchs.javalib.lang.triple.Triple;
import de.flammenfuchs.javalib.lang.tuple.Tuple;

import java.util.Collection;
import java.util.Set;

public interface Table <T, U, V> {

    int size();

    boolean isEmpty();

    boolean containsKey(T t, U u);

    boolean containsValue(V v);

    V get(T t, U u);

    V put(T t, U u, V v);

    V remove(T t, U u);

    void putAll(Table<T, U, V> table);

    void clear();

    Set<Tuple<T, U>> keySet();

    Collection<V> values();

    Set<Triple<T, U, V>> elements();

    V getOrDefault(T t, U u, V defaultValue);


    V putIfAbsent(T t, U u, V value);
}
