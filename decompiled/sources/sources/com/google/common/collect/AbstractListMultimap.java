package com.google.common.collect;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
abstract class AbstractListMultimap<K, V> extends AbstractMapBasedMultimap<K, V> implements ListMultimap<K, V> {
    public AbstractListMultimap(Map<K, Collection<V>> r1) {
        super(r1);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Map<K, Collection<V>> asMap() {
        return super.asMap();
    }

    @Override // com.google.common.collect.AbstractMultimap
    public final boolean equals(Object r1) {
        return super.equals(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ Collection get(Object r1) {
        return get(r1);
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.Multimap
    public List<V> get(K r1) {
        return (List) super.get(r1);
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.Multimap
    public final boolean put(K r1, V r2) {
        return super.put(r1, r2);
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap
    public final <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> r1) {
        return Collections.unmodifiableList((List) r1);
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap
    public final Collection<V> wrapCollection(K r2, Collection<V> r3) {
        return wrapList(r2, (List) r3, null);
    }
}
