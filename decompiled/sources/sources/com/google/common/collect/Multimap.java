package com.google.common.collect;

import java.util.Collection;
import java.util.Map;

/* loaded from: classes5.dex */
public interface Multimap<K, V> {
    Map<K, Collection<V>> asMap();

    void clear();

    boolean containsEntry(Object r1, Object r2);

    Collection<Map.Entry<K, V>> entries();

    Collection<V> get(K r1);

    boolean put(K r1, V r2);

    boolean remove(Object r1, Object r2);

    int size();

    Collection<V> values();
}
