package com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public abstract class ForwardingMap<K, V> extends ForwardingObject implements Map<K, V> {
    public ForwardingMap() {
    }

    @Override // java.util.Map
    public void clear() {
        delegate().clear();
    }

    public boolean containsKey(Object r1) {
        return delegate().containsKey(r1);
    }

    @Override // com.google.common.collect.ForwardingObject
    public abstract Map<K, V> delegate();

    public Set<Map.Entry<K, V>> entrySet() {
        return delegate().entrySet();
    }

    public V get(Object r1) {
        return delegate().get(r1);
    }

    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    public Set<K> keySet() {
        return delegate().keySet();
    }

    @Override // java.util.Map
    public V put(K r1, V r2) {
        return delegate().put(r1, r2);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> r1) {
        delegate().putAll(r1);
    }

    @Override // java.util.Map
    public V remove(Object r1) {
        return delegate().remove(r1);
    }

    public int size() {
        return delegate().size();
    }

    public boolean standardContainsValue(Object r1) {
        return Maps.containsValueImpl(this, r1);
    }

    public boolean standardEquals(Object r1) {
        return Maps.equalsImpl(this, r1);
    }

    public int standardHashCode() {
        return Sets.hashCodeImpl(entrySet());
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return delegate().values();
    }
}
