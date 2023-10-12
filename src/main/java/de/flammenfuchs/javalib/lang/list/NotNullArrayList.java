package de.flammenfuchs.javalib.lang.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Simple ArrayList Extension, which does not allow to add null elements
 * Null-Elements will simply not add
 *
 * @param <T> ArrayList Type
 */
public class NotNullArrayList<T> extends ArrayList<T> {

    @Override
    public T set(int index, T element) {
        if (element == null) {
            return null;
        }
        return super.set(index, element);
    }

    @Override
    public boolean add(T t) {
        if (t == null) {
            return false;
        }
        return super.add(t);
    }

    @Override
    public void add(int index, T element) {
        if (element == null) {
            return;
        }
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return super.addAll(
                collection.stream().filter(Objects::nonNull)
                        .toList()
        );
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        return super.addAll(index, collection.stream().filter(Objects::nonNull)
                        .toList()
                );
    }
}
