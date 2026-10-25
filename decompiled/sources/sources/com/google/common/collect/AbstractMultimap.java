package com.google.common.collect;

import com.google.common.collect.Multimaps;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
abstract class AbstractMultimap<K, V> implements Multimap<K, V> {
    private transient Map<K, Collection<V>> asMap;
    private transient Collection<Map.Entry<K, V>> entries;
    private transient Set<K> keySet;
    private transient Collection<V> values;

    public class Entries extends Multimaps.Entries<K, V> {
        final /* synthetic */ AbstractMultimap this$0;

        public Entries(AbstractMultimap r1) {
            this.this$0 = r1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public final Iterator<Map.Entry<K, V>> iterator() {
            return this.this$0.entryIterator();
        }

        @Override // com.google.common.collect.Multimaps.Entries
        public final Multimap<K, V> multimap() {
            return this.this$0;
        }
    }

    public final class Values extends AbstractCollection<V> {
        final /* synthetic */ AbstractMultimap this$0;

        public Values(AbstractMultimap r1) {
            this.this$0 = r1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.this$0.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object r1) {
            return this.this$0.containsValue(r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return this.this$0.valueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.this$0.size();
        }
    }

    public AbstractMultimap() {
    }

    @Override // com.google.common.collect.Multimap
    public Map<K, Collection<V>> asMap() {
        Map<K, Collection<V>> r0 = this.asMap;
        if (r0 != null) goto L6;
        Map<K, Collection<V>> r02 = createAsMap();
        this.asMap = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsEntry(Object r1, Object r2) {
        Collection<V> r0 = asMap().get(r1);
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.contains(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean containsValue(Object r2) {
        Iterator<Collection<V>> r1 = asMap().values().iterator();
    L4:
        if (r1.hasNext() == false) goto L9;
        if (r1.next().contains(r2) == false) goto L4;
        return true;
    L9:
        return false;
    }

    public abstract Map<K, Collection<V>> createAsMap();

    public abstract Collection<Map.Entry<K, V>> createEntries();

    public abstract Set<K> createKeySet();

    public abstract Collection<V> createValues();

    @Override // com.google.common.collect.Multimap
    public Collection<Map.Entry<K, V>> entries() {
        Collection<Map.Entry<K, V>> r0 = this.entries;
        if (r0 != null) goto L6;
        Collection<Map.Entry<K, V>> r02 = createEntries();
        this.entries = r02;
        return r02;
    L6:
        return r0;
    }

    public abstract Iterator<Map.Entry<K, V>> entryIterator();

    public boolean equals(Object r1) {
        return Multimaps.equalsImpl(this, r1);
    }

    public int hashCode() {
        return asMap().hashCode();
    }

    public Set<K> keySet() {
        Set<K> r0 = this.keySet;
        if (r0 != null) goto L6;
        Set<K> r02 = createKeySet();
        this.keySet = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // com.google.common.collect.Multimap
    public boolean remove(Object r1, Object r2) {
        Collection<V> r0 = asMap().get(r1);
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.remove(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final String toString() {
        return asMap().toString();
    }

    public abstract Iterator<V> valueIterator();

    @Override // com.google.common.collect.Multimap
    public Collection<V> values() {
        Collection<V> r0 = this.values;
        if (r0 != null) goto L6;
        Collection<V> r02 = createValues();
        this.values = r02;
        return r02;
    L6:
        return r0;
    }
}
