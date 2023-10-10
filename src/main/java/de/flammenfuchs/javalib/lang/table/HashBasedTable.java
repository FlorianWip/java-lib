package de.flammenfuchs.javalib.lang.table;

import de.flammenfuchs.javalib.lang.triple.Triple;
import de.flammenfuchs.javalib.lang.tuple.Tuple;

import java.util.*;

/**
 * An implementation of a {@link Table} based on a {@link HashMap}
 *
 * @param <T> the type of the first key
 * @param <U> the type of the second key
 * @param <V> the type of the stored value
 */
public class HashBasedTable<T, U, V> implements Table<T, U, V> {

    private final Map<T, Map<U, V>> data = new HashMap<>();

    @Override
    public int size() {
        int count = 0;
        for (Map<U, V> map : data.values()) {
            count += map.size();
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(T t, U u) {
        return data.containsKey(t) && data.get(t).containsKey(u);
    }

    @Override
    public boolean containsValue(V v) {
        for (Map<U, V> map : data.values()) {
            if (map.containsValue(v)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(T t, U u) {
        if (!data.containsKey(t)) {
            return null;
        }
        return data.get(t).get(u);
    }

    @Override
    public V put(T t, U u, V v) {
        if (!data.containsKey(t)) {
            data.put(t, new HashMap<>());
        }
        return data.get(t).put(u, v);
    }

    @Override
    public V remove(T t, U u) {
        if (!data.containsKey(t)) {
            return null;
        }
        V value = data.get(t).remove(u);
        if (data.get(t).isEmpty()) {
            data.remove(t);
        }
        return value;
    }

    @Override
    public void putAll(Table<T, U, V> table) {
        table.elements().forEach(triple -> put(triple.a(), triple.b(), triple.c()));
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public Set<Tuple<T, U>> keySet() {
        Set<Tuple<T, U>> foundKeys = new HashSet<>();
        data.entrySet().forEach(entry ->
                entry.getValue().keySet().forEach(v ->
                        foundKeys.add(Tuple.finalTuple(entry.getKey(), v)
                        )));
        return foundKeys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        data.values().forEach(map -> values.addAll(map.values()));
        return values;
    }

    @Override
    public Set<Triple<T, U, V>> elements() {
        Set<Triple<T, U, V>> elements = new HashSet<>();
        for (Map.Entry<T, Map<U, V>> upperEntry : data.entrySet()) {
            for (Map.Entry<U, V> lowerEntry : upperEntry.getValue().entrySet()) {
                elements.add(Triple.finalTriple(upperEntry.getKey(),
                        lowerEntry.getKey(), lowerEntry.getValue()));
            }
        }
        return elements;
    }

    @Override
    public V getOrDefault(T t, U u, V defaultValue) {
        V value = get(t, u);
        return value == null ? defaultValue : value;
    }

    @Override
    public V putIfAbsent(T t, U u, V value) {
        V mapValue = get(t, u);
        if (mapValue == null) {
            return put(t, u, value);
        }
        return mapValue;
    }
}
