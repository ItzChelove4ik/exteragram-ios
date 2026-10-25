package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public abstract class Multimaps {

    public static final class CustomListMultimap<K, V> extends AbstractListMultimap<K, V> {
        transient Supplier<? extends List<V>> factory;

        public CustomListMultimap(Map<K, Collection<V>> r1, Supplier<? extends List<V>> r2) {
            super(r1);
            this.factory = (Supplier) Preconditions.checkNotNull(r2);
        }

        @Override // com.google.common.collect.AbstractMultimap
        public Map<K, Collection<V>> createAsMap() {
            return createMaybeNavigableAsMap();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap
        public /* bridge */ /* synthetic */ Collection createCollection() {
            return createCollection();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap
        public List<V> createCollection() {
            return this.factory.get();
        }

        @Override // com.google.common.collect.AbstractMultimap
        public Set<K> createKeySet() {
            return createMaybeNavigableKeySet();
        }
    }

    public static abstract class Entries<K, V> extends AbstractCollection<Map.Entry<K, V>> {
        public Entries() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            multimap().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object r2) {
            if ((r2 instanceof Map.Entry) == false) goto L6;
            Map.Entry r22 = (Map.Entry) r2;
            return multimap().containsEntry(r22.getKey(), r22.getValue());
        L6:
            return false;
        }

        public abstract Multimap<K, V> multimap();

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object r2) {
            if ((r2 instanceof Map.Entry) == false) goto L6;
            Map.Entry r22 = (Map.Entry) r2;
            return multimap().remove(r22.getKey(), r22.getValue());
        L6:
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return multimap().size();
        }
    }

    public static boolean equalsImpl(Multimap<?, ?> r1, Object r2) {
        if (r2 != r1) goto L6;
        return true;
    L6:
        if ((r2 instanceof Multimap) == true) goto L8;
        return false;
    L8:
        return r1.asMap().equals(((Multimap) r2).asMap());
    }

    public static <K, V> ListMultimap<K, V> newListMultimap(Map<K, Collection<V>> r1, Supplier<? extends List<V>> r2) {
        return new CustomListMultimap(r1, r2);
    }
}
