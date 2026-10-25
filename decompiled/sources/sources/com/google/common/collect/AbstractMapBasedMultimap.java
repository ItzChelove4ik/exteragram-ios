package com.google.common.collect;

import androidx.collection.ArraySet$$ExternalSyntheticBUOutline0;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMultimap;
import com.google.common.collect.Maps;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes5.dex */
abstract class AbstractMapBasedMultimap<K, V> extends AbstractMultimap<K, V> implements Serializable {
    private transient Map<K, Collection<V>> map;
    private transient int totalSize;

    /* renamed from: com.google.common.collect.AbstractMapBasedMultimap$1, reason: invalid class name */
    public class AnonymousClass1 extends AbstractMapBasedMultimap<K, V>.Itr<V> {
        final /* synthetic */ AbstractMapBasedMultimap this$0;

        public AnonymousClass1(AbstractMapBasedMultimap r1) {
            this.this$0 = r1;
            super(r1);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.Itr
        public V output(K r1, V r2) {
            return r2;
        }
    }

    /* renamed from: com.google.common.collect.AbstractMapBasedMultimap$2, reason: invalid class name */
    public class AnonymousClass2 extends AbstractMapBasedMultimap<K, V>.Itr<Map.Entry<K, V>> {
        final /* synthetic */ AbstractMapBasedMultimap this$0;

        public AnonymousClass2(AbstractMapBasedMultimap r1) {
            this.this$0 = r1;
            super(r1);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.Itr
        public /* bridge */ /* synthetic */ Object output(Object r1, Object r2) {
            return output(r1, r2);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.Itr
        public Map.Entry<K, V> output(K r1, V r2) {
            return Maps.immutableEntry(r1, r2);
        }
    }

    public class AsMap extends Maps.ViewCachingAbstractMap<K, Collection<V>> {
        final transient Map<K, Collection<V>> submap;
        final /* synthetic */ AbstractMapBasedMultimap this$0;

        public final class AsMapEntries extends Maps.EntrySet<K, Collection<V>> {
            final /* synthetic */ AsMap this$1;

            public AsMapEntries(AsMap r1) {
                this.this$1 = r1;
            }

            @Override // com.google.common.collect.Maps.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public final boolean contains(Object r1) {
                return Collections2.safeContains(this.this$1.submap.entrySet(), r1);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public final Iterator<Map.Entry<K, Collection<V>>> iterator() {
                return new AsMapIterator(this.this$1);
            }

            @Override // com.google.common.collect.Maps.EntrySet
            public final Map<K, Collection<V>> map() {
                return this.this$1;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public final boolean remove(Object r2) {
                if (contains(r2) == true) goto L6;
                return false;
            L6:
                Map.Entry r22 = (Map.Entry) r2;
                Objects.requireNonNull(r22);
                AbstractMapBasedMultimap.access$300(this.this$1.this$0, r22.getKey());
                return true;
            }
        }

        public final class AsMapIterator implements Iterator<Map.Entry<K, Collection<V>>> {
            Collection<V> collection;
            final Iterator<Map.Entry<K, Collection<V>>> delegateIterator;
            final /* synthetic */ AsMap this$1;

            public AsMapIterator(AsMap r1) {
                this.this$1 = r1;
                this.delegateIterator = r1.submap.entrySet().iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.delegateIterator.hasNext();
            }

            @Override // java.util.Iterator
            public /* bridge */ /* synthetic */ Object next() {
                return next();
            }

            @Override // java.util.Iterator
            public Map.Entry<K, Collection<V>> next() {
                Map.Entry<K, Collection<V>> r0 = this.delegateIterator.next();
                this.collection = r0.getValue();
                return this.this$1.wrapEntry(r0);
            }

            @Override // java.util.Iterator
            public void remove() {
                if (this.collection == null) goto L5;
                boolean r0 = true;
            L6:
                Preconditions.checkState(r0, "no calls to next() since the last call to remove()");
                this.delegateIterator.remove();
                AbstractMapBasedMultimap.access$220(this.this$1.this$0, this.collection.size());
                this.collection.clear();
                this.collection = null;
                return;
            L5:
                r0 = false;
                goto L6
            }
        }

        public AsMap(AbstractMapBasedMultimap r1, Map<K, Collection<V>> r2) {
            this.this$0 = r1;
            this.submap = r2;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final void clear() {
            if (this.submap != AbstractMapBasedMultimap.access$000(this.this$0)) goto L6;
            this.this$0.clear();
            return;
        L6:
            Iterators.clear(new AsMapIterator(this));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final boolean containsKey(Object r1) {
            return Maps.safeContainsKey(this.submap, r1);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public final Set<Map.Entry<K, Collection<V>>> createEntrySet() {
            return new AsMapEntries(this);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final boolean equals(Object r1) {
            if (this != r1) goto L4;
            return true;
        L4:
            if (this.submap.equals(r1) == true) goto L10;
            return false;
        L10:
            return true;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public /* bridge */ /* synthetic */ Object get(Object r1) {
            return get(r1);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final Collection<V> get(Object r2) {
            Collection<V> r0 = (Collection) Maps.safeGet(this.submap, r2);
            if (r0 != null) goto L7;
            return null;
        L7:
            return this.this$0.wrapCollection(r2, r0);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final int hashCode() {
            return this.submap.hashCode();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return this.this$0.keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public /* bridge */ /* synthetic */ Object remove(Object r1) {
            return remove(r1);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final Collection<V> remove(Object r3) {
            Collection<V> r32 = this.submap.remove(r3);
            if (r32 != null) goto L6;
            return null;
        L6:
            Collection<V> r0 = this.this$0.createCollection();
            r0.addAll(r32);
            AbstractMapBasedMultimap.access$220(this.this$0, r32.size());
            r32.clear();
            return r0;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final int size() {
            return this.submap.size();
        }

        @Override // java.util.AbstractMap
        public final String toString() {
            return this.submap.toString();
        }

        public final Map.Entry<K, Collection<V>> wrapEntry(Map.Entry<K, Collection<V>> r2) {
            K r0 = r2.getKey();
            return Maps.immutableEntry(r0, this.this$0.wrapCollection(r0, r2.getValue()));
        }
    }

    public abstract class Itr<T> implements Iterator<T> {
        Collection<V> collection;
        K key;
        final Iterator<Map.Entry<K, Collection<V>>> keyIterator;
        final /* synthetic */ AbstractMapBasedMultimap this$0;
        Iterator<V> valueIterator;

        public Itr(AbstractMapBasedMultimap r1) {
            this.this$0 = r1;
            this.keyIterator = AbstractMapBasedMultimap.access$000(r1).entrySet().iterator();
            this.key = null;
            this.collection = null;
            this.valueIterator = Iterators.emptyModifiableIterator();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.keyIterator.hasNext() == false) goto L5;
            return true;
        L5:
            if (this.valueIterator.hasNext() == true) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // java.util.Iterator
        public final T next() {
            if (this.valueIterator.hasNext() == true) goto L6;
            Map.Entry<K, Collection<V>> r0 = this.keyIterator.next();
            this.key = r0.getKey();
            Collection<V> r02 = r0.getValue();
            this.collection = r02;
            this.valueIterator = r02.iterator();
        L6:
            return output(NullnessCasts.uncheckedCastNullableTToT(this.key), this.valueIterator.next());
        }

        public abstract T output(K r1, V r2);

        @Override // java.util.Iterator
        public final void remove() {
            this.valueIterator.remove();
            Collection<V> r0 = this.collection;
            Objects.requireNonNull(r0);
            if (r0.isEmpty() == false) goto L5;
            this.keyIterator.remove();
        L5:
            AbstractMapBasedMultimap.access$210(this.this$0);
        }
    }

    public class KeySet extends Maps.KeySet<K, Collection<V>> {
        final /* synthetic */ AbstractMapBasedMultimap this$0;

        /* renamed from: com.google.common.collect.AbstractMapBasedMultimap$KeySet$1, reason: invalid class name */
        public class AnonymousClass1 implements Iterator<K> {
            Map.Entry<K, Collection<V>> entry;
            final /* synthetic */ KeySet this$1;
            final /* synthetic */ Iterator val$entryIterator;

            public AnonymousClass1(KeySet r1, Iterator r2) {
                this.val$entryIterator = r2;
                this.this$1 = r1;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.val$entryIterator.hasNext();
            }

            @Override // java.util.Iterator
            public K next() {
                Map.Entry<K, Collection<V>> r0 = (Map.Entry) this.val$entryIterator.next();
                this.entry = r0;
                return r0.getKey();
            }

            @Override // java.util.Iterator
            public void remove() {
                if (this.entry == null) goto L5;
                boolean r0 = true;
            L6:
                Preconditions.checkState(r0, "no calls to next() since the last call to remove()");
                Collection<V> r02 = this.entry.getValue();
                this.val$entryIterator.remove();
                AbstractMapBasedMultimap.access$220(this.this$1.this$0, r02.size());
                r02.clear();
                this.entry = null;
                return;
            L5:
                r0 = false;
                goto L6
            }
        }

        public KeySet(AbstractMapBasedMultimap r1, Map<K, Collection<V>> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final void clear() {
            Iterators.clear(iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean containsAll(Collection<?> r1) {
            return map().keySet().containsAll(r1);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public final boolean equals(Object r1) {
            if (this != r1) goto L4;
            return true;
        L4:
            if (map().keySet().equals(r1) == true) goto L10;
            return false;
        L10:
            return true;
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public final int hashCode() {
            return map().keySet().hashCode();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public final Iterator<K> iterator() {
            return new AnonymousClass1(this, map().entrySet().iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(Object r3) {
            Collection<V> r32 = map().remove(r3);
            if (r32 == null) goto L5;
            int r1 = r32.size();
            r32.clear();
            AbstractMapBasedMultimap.access$220(this.this$0, r1);
        L6:
            if (r1 <= 0) goto L9;
            return true;
        L9:
            return false;
        L5:
            r1 = 0;
            goto L6
        }
    }

    public final class NavigableAsMap extends AbstractMapBasedMultimap<K, V>.SortedAsMap implements NavigableMap<K, Collection<V>> {
        final /* synthetic */ AbstractMapBasedMultimap this$0;

        public NavigableAsMap(AbstractMapBasedMultimap r1, NavigableMap<K, Collection<V>> r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, Collection<V>> ceilingEntry(K r2) {
            Map.Entry<K, Collection<V>> r22 = sortedMap().ceilingEntry(r2);
            if (r22 != null) goto L7;
            return null;
        L7:
            return wrapEntry(r22);
        }

        @Override // java.util.NavigableMap
        public final K ceilingKey(K r1) {
            return sortedMap().ceilingKey(r1);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap
        public final NavigableSet<K> createKeySet() {
            return new NavigableKeySet(this.this$0, sortedMap());
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap
        public /* bridge */ /* synthetic */ SortedSet createKeySet() {
            return createKeySet();
        }

        @Override // java.util.NavigableMap
        public final NavigableSet<K> descendingKeySet() {
            return descendingMap().navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public final NavigableMap<K, Collection<V>> descendingMap() {
            return new NavigableAsMap(this.this$0, sortedMap().descendingMap());
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, Collection<V>> firstEntry() {
            Map.Entry<K, Collection<V>> r0 = sortedMap().firstEntry();
            if (r0 != null) goto L7;
            return null;
        L7:
            return wrapEntry(r0);
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, Collection<V>> floorEntry(K r2) {
            Map.Entry<K, Collection<V>> r22 = sortedMap().floorEntry(r2);
            if (r22 != null) goto L7;
            return null;
        L7:
            return wrapEntry(r22);
        }

        @Override // java.util.NavigableMap
        public final K floorKey(K r1) {
            return sortedMap().floorKey(r1);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        public final NavigableMap<K, Collection<V>> headMap(K r2) {
            return headMap(r2, false);
        }

        @Override // java.util.NavigableMap
        public final NavigableMap<K, Collection<V>> headMap(K r3, boolean r4) {
            return new NavigableAsMap(this.this$0, sortedMap().headMap(r3, r4));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        public /* bridge */ /* synthetic */ SortedMap headMap(Object r1) {
            return headMap(r1);
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, Collection<V>> higherEntry(K r2) {
            Map.Entry<K, Collection<V>> r22 = sortedMap().higherEntry(r2);
            if (r22 != null) goto L7;
            return null;
        L7:
            return wrapEntry(r22);
        }

        @Override // java.util.NavigableMap
        public final K higherKey(K r1) {
            return sortedMap().higherKey(r1);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, com.google.common.collect.AbstractMapBasedMultimap.AsMap, java.util.AbstractMap, java.util.Map
        public final NavigableSet<K> keySet() {
            return (NavigableSet) super.keySet();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, com.google.common.collect.AbstractMapBasedMultimap.AsMap, java.util.AbstractMap, java.util.Map
        public /* bridge */ /* synthetic */ Set keySet() {
            return keySet();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, com.google.common.collect.AbstractMapBasedMultimap.AsMap, java.util.AbstractMap, java.util.Map
        public /* bridge */ /* synthetic */ SortedSet keySet() {
            return keySet();
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, Collection<V>> lastEntry() {
            Map.Entry<K, Collection<V>> r0 = sortedMap().lastEntry();
            if (r0 != null) goto L7;
            return null;
        L7:
            return wrapEntry(r0);
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, Collection<V>> lowerEntry(K r2) {
            Map.Entry<K, Collection<V>> r22 = sortedMap().lowerEntry(r2);
            if (r22 != null) goto L7;
            return null;
        L7:
            return wrapEntry(r22);
        }

        @Override // java.util.NavigableMap
        public final K lowerKey(K r1) {
            return sortedMap().lowerKey(r1);
        }

        @Override // java.util.NavigableMap
        public final NavigableSet<K> navigableKeySet() {
            return keySet();
        }

        public final Map.Entry<K, Collection<V>> pollAsMapEntry(Iterator<Map.Entry<K, Collection<V>>> r4) {
            if (r4.hasNext() == true) goto L6;
            return null;
        L6:
            Map.Entry<K, Collection<V>> r0 = r4.next();
            Collection<V> r1 = this.this$0.createCollection();
            r1.addAll(r0.getValue());
            r4.remove();
            return Maps.immutableEntry(r0.getKey(), this.this$0.unmodifiableCollectionSubclass(r1));
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, Collection<V>> pollFirstEntry() {
            return pollAsMapEntry(entrySet().iterator());
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, Collection<V>> pollLastEntry() {
            return pollAsMapEntry(descendingMap().entrySet().iterator());
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap
        public final NavigableMap<K, Collection<V>> sortedMap() {
            return (NavigableMap) super.sortedMap();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap
        public /* bridge */ /* synthetic */ SortedMap sortedMap() {
            return sortedMap();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        public final NavigableMap<K, Collection<V>> subMap(K r3, K r4) {
            return subMap(r3, true, r4, false);
        }

        @Override // java.util.NavigableMap
        public final NavigableMap<K, Collection<V>> subMap(K r3, boolean r4, K r5, boolean r6) {
            return new NavigableAsMap(this.this$0, sortedMap().subMap(r3, r4, r5, r6));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        public /* bridge */ /* synthetic */ SortedMap subMap(Object r1, Object r2) {
            return subMap(r1, r2);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        public final NavigableMap<K, Collection<V>> tailMap(K r2) {
            return tailMap(r2, true);
        }

        @Override // java.util.NavigableMap
        public final NavigableMap<K, Collection<V>> tailMap(K r3, boolean r4) {
            return new NavigableAsMap(this.this$0, sortedMap().tailMap(r3, r4));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        public /* bridge */ /* synthetic */ SortedMap tailMap(Object r1) {
            return tailMap(r1);
        }
    }

    public final class NavigableKeySet extends AbstractMapBasedMultimap<K, V>.SortedKeySet implements NavigableSet<K> {
        final /* synthetic */ AbstractMapBasedMultimap this$0;

        public NavigableKeySet(AbstractMapBasedMultimap r1, NavigableMap<K, Collection<V>> r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // java.util.NavigableSet
        public final K ceiling(K r1) {
            return sortedMap().ceilingKey(r1);
        }

        @Override // java.util.NavigableSet
        public final Iterator<K> descendingIterator() {
            return descendingSet().iterator();
        }

        @Override // java.util.NavigableSet
        public final NavigableSet<K> descendingSet() {
            return new NavigableKeySet(this.this$0, sortedMap().descendingMap());
        }

        @Override // java.util.NavigableSet
        public final K floor(K r1) {
            return sortedMap().floorKey(r1);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public final NavigableSet<K> headSet(K r2) {
            return headSet(r2, false);
        }

        @Override // java.util.NavigableSet
        public final NavigableSet<K> headSet(K r3, boolean r4) {
            return new NavigableKeySet(this.this$0, sortedMap().headMap(r3, r4));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public /* bridge */ /* synthetic */ SortedSet headSet(Object r1) {
            return headSet(r1);
        }

        @Override // java.util.NavigableSet
        public final K higher(K r1) {
            return sortedMap().higherKey(r1);
        }

        @Override // java.util.NavigableSet
        public final K lower(K r1) {
            return sortedMap().lowerKey(r1);
        }

        @Override // java.util.NavigableSet
        public final K pollFirst() {
            return (K) Iterators.pollNext(iterator());
        }

        @Override // java.util.NavigableSet
        public final K pollLast() {
            return (K) Iterators.pollNext(descendingIterator());
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet
        public final NavigableMap<K, Collection<V>> sortedMap() {
            return (NavigableMap) super.sortedMap();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet
        public /* bridge */ /* synthetic */ SortedMap sortedMap() {
            return sortedMap();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public final NavigableSet<K> subSet(K r3, K r4) {
            return subSet(r3, true, r4, false);
        }

        @Override // java.util.NavigableSet
        public final NavigableSet<K> subSet(K r3, boolean r4, K r5, boolean r6) {
            return new NavigableKeySet(this.this$0, sortedMap().subMap(r3, r4, r5, r6));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public /* bridge */ /* synthetic */ SortedSet subSet(Object r1, Object r2) {
            return subSet(r1, r2);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public final NavigableSet<K> tailSet(K r2) {
            return tailSet(r2, true);
        }

        @Override // java.util.NavigableSet
        public final NavigableSet<K> tailSet(K r3, boolean r4) {
            return new NavigableKeySet(this.this$0, sortedMap().tailMap(r3, r4));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        public /* bridge */ /* synthetic */ SortedSet tailSet(Object r1) {
            return tailSet(r1);
        }
    }

    public final class RandomAccessWrappedList extends AbstractMapBasedMultimap<K, V>.WrappedList implements RandomAccess {
        final /* synthetic */ AbstractMapBasedMultimap this$0;

        public RandomAccessWrappedList(AbstractMapBasedMultimap r1, K r2, List<V> r3, AbstractMapBasedMultimap<K, V>.WrappedCollection r4) {
            this.this$0 = r1;
            super(r1, r2, r3, r4);
        }
    }

    public class SortedAsMap extends AbstractMapBasedMultimap<K, V>.AsMap implements SortedMap<K, Collection<V>> {
        SortedSet<K> sortedKeySet;
        final /* synthetic */ AbstractMapBasedMultimap this$0;

        public SortedAsMap(AbstractMapBasedMultimap r1, SortedMap<K, Collection<V>> r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // java.util.SortedMap
        public final Comparator<? super K> comparator() {
            return sortedMap().comparator();
        }

        public SortedSet<K> createKeySet() {
            return new SortedKeySet(this.this$0, sortedMap());
        }

        @Override // java.util.SortedMap
        public final K firstKey() {
            return sortedMap().firstKey();
        }

        public SortedMap<K, Collection<V>> headMap(K r3) {
            return new SortedAsMap(this.this$0, sortedMap().headMap(r3));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.AsMap, java.util.AbstractMap, java.util.Map
        public /* bridge */ /* synthetic */ Set keySet() {
            return keySet();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.AsMap, java.util.AbstractMap, java.util.Map
        public SortedSet<K> keySet() {
            SortedSet<K> r0 = this.sortedKeySet;
            if (r0 != null) goto L6;
            SortedSet<K> r02 = createKeySet();
            this.sortedKeySet = r02;
            return r02;
        L6:
            return r0;
        }

        @Override // java.util.SortedMap
        public final K lastKey() {
            return sortedMap().lastKey();
        }

        public SortedMap<K, Collection<V>> sortedMap() {
            return (SortedMap) this.submap;
        }

        public SortedMap<K, Collection<V>> subMap(K r3, K r4) {
            return new SortedAsMap(this.this$0, sortedMap().subMap(r3, r4));
        }

        public SortedMap<K, Collection<V>> tailMap(K r3) {
            return new SortedAsMap(this.this$0, sortedMap().tailMap(r3));
        }
    }

    public class SortedKeySet extends AbstractMapBasedMultimap<K, V>.KeySet implements SortedSet<K> {
        final /* synthetic */ AbstractMapBasedMultimap this$0;

        public SortedKeySet(AbstractMapBasedMultimap r1, SortedMap<K, Collection<V>> r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // java.util.SortedSet
        public final Comparator<? super K> comparator() {
            return sortedMap().comparator();
        }

        @Override // java.util.SortedSet
        public final K first() {
            return sortedMap().firstKey();
        }

        public SortedSet<K> headSet(K r3) {
            return new SortedKeySet(this.this$0, sortedMap().headMap(r3));
        }

        @Override // java.util.SortedSet
        public final K last() {
            return sortedMap().lastKey();
        }

        public SortedMap<K, Collection<V>> sortedMap() {
            return (SortedMap) super.map();
        }

        public SortedSet<K> subSet(K r3, K r4) {
            return new SortedKeySet(this.this$0, sortedMap().subMap(r3, r4));
        }

        public SortedSet<K> tailSet(K r3) {
            return new SortedKeySet(this.this$0, sortedMap().tailMap(r3));
        }
    }

    public class WrappedCollection extends AbstractCollection<V> {
        final AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor;
        final Collection<V> ancestorDelegate;
        Collection<V> delegate;
        final K key;
        final /* synthetic */ AbstractMapBasedMultimap this$0;

        public class WrappedIterator implements Iterator<V> {
            final Iterator<V> delegateIterator;
            final Collection<V> originalDelegate;
            final /* synthetic */ WrappedCollection this$1;

            public WrappedIterator(WrappedCollection r1) {
                this.this$1 = r1;
                Collection<V> r12 = r1.delegate;
                this.originalDelegate = r12;
                this.delegateIterator = AbstractMapBasedMultimap.access$100(r12);
            }

            public WrappedIterator(WrappedCollection r1, Iterator<V> r2) {
                this.this$1 = r1;
                this.originalDelegate = r1.delegate;
                this.delegateIterator = r2;
            }

            public final Iterator<V> getDelegateIterator() {
                validateIterator();
                return this.delegateIterator;
            }

            @Override // java.util.Iterator
            public final boolean hasNext() {
                validateIterator();
                return this.delegateIterator.hasNext();
            }

            @Override // java.util.Iterator
            public final V next() {
                validateIterator();
                return this.delegateIterator.next();
            }

            @Override // java.util.Iterator
            public final void remove() {
                this.delegateIterator.remove();
                AbstractMapBasedMultimap.access$210(this.this$1.this$0);
                this.this$1.removeIfEmpty();
            }

            public final void validateIterator() {
                this.this$1.refreshIfEmpty();
                if (this.this$1.delegate != this.originalDelegate) goto L5;
                return;
            L5:
                ArraySet$$ExternalSyntheticBUOutline0.m();
            }
        }

        public WrappedCollection(AbstractMapBasedMultimap r1, K r2, Collection<V> r3, AbstractMapBasedMultimap<K, V>.WrappedCollection r4) {
            this.this$0 = r1;
            this.key = r2;
            this.delegate = r3;
            this.ancestor = r4;
            if (r4 != null) goto L5;
            Collection<V> r12 = null;
        L6:
            this.ancestorDelegate = r12;
            return;
        L5:
            r12 = r4.getDelegate();
            goto L6
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean add(V r3) {
            refreshIfEmpty();
            boolean r0 = this.delegate.isEmpty();
            boolean r32 = this.delegate.add(r3);
            if (r32 == false) goto L7;
            AbstractMapBasedMultimap.access$208(this.this$0);
            if (r0 == false) goto L7;
            addToMap();
        L7:
            return r32;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean addAll(Collection<? extends V> r4) {
            if (r4.isEmpty() == false) goto L6;
            return false;
        L6:
            int r0 = size();
            boolean r42 = this.delegate.addAll(r4);
            if (r42 == false) goto L11;
            int r1 = this.delegate.size();
            AbstractMapBasedMultimap.access$212(this.this$0, r1 - r0);
            if (r0 != 0) goto L11;
            addToMap();
        L11:
            return r42;
        }

        public final void addToMap() {
            AbstractMapBasedMultimap<K, V>.WrappedCollection r0 = this.ancestor;
            if (r0 == null) goto L6;
            r0.addToMap();
            return;
        L6:
            AbstractMapBasedMultimap.access$000(this.this$0).put(this.key, this.delegate);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final void clear() {
            int r0 = size();
            if (r0 != 0) goto L5;
            return;
        L5:
            this.delegate.clear();
            AbstractMapBasedMultimap.access$220(this.this$0, r0);
            removeIfEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean contains(Object r1) {
            refreshIfEmpty();
            return this.delegate.contains(r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean containsAll(Collection<?> r1) {
            refreshIfEmpty();
            return this.delegate.containsAll(r1);
        }

        @Override // java.util.Collection
        public final boolean equals(Object r1) {
            if (r1 != this) goto L5;
            return true;
        L5:
            refreshIfEmpty();
            return this.delegate.equals(r1);
        }

        public final AbstractMapBasedMultimap<K, V>.WrappedCollection getAncestor() {
            return this.ancestor;
        }

        public final Collection<V> getDelegate() {
            return this.delegate;
        }

        public final K getKey() {
            return this.key;
        }

        @Override // java.util.Collection
        public final int hashCode() {
            refreshIfEmpty();
            return this.delegate.hashCode();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public final Iterator<V> iterator() {
            refreshIfEmpty();
            return new WrappedIterator(this);
        }

        public final void refreshIfEmpty() {
            AbstractMapBasedMultimap<K, V>.WrappedCollection r0 = this.ancestor;
            if (r0 == null) goto L10;
            r0.refreshIfEmpty();
            if (this.ancestor.getDelegate() == this.ancestorDelegate) goto L17;
            ArraySet$$ExternalSyntheticBUOutline0.m();
            return;
        L17:
            return;
        L10:
            if (this.delegate.isEmpty() == false) goto L15;
            Collection<V> r02 = (Collection) AbstractMapBasedMultimap.access$000(this.this$0).get(this.key);
            if (r02 == null) goto L16;
            this.delegate = r02;
            return;
        L16:
            return;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean remove(Object r2) {
            refreshIfEmpty();
            boolean r22 = this.delegate.remove(r2);
            if (r22 == false) goto L5;
            AbstractMapBasedMultimap.access$210(this.this$0);
            removeIfEmpty();
        L5:
            return r22;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> r4) {
            if (r4.isEmpty() == false) goto L6;
            return false;
        L6:
            int r0 = size();
            boolean r42 = this.delegate.removeAll(r4);
            if (r42 == false) goto L9;
            int r1 = this.delegate.size();
            AbstractMapBasedMultimap.access$212(this.this$0, r1 - r0);
            removeIfEmpty();
        L9:
            return r42;
        }

        public final void removeIfEmpty() {
            AbstractMapBasedMultimap<K, V>.WrappedCollection r0 = this.ancestor;
            if (r0 == null) goto L7;
            r0.removeIfEmpty();
            return;
        L7:
            if (this.delegate.isEmpty() == false) goto L10;
            AbstractMapBasedMultimap.access$000(this.this$0).remove(this.key);
            return;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean retainAll(Collection<?> r4) {
            Preconditions.checkNotNull(r4);
            int r0 = size();
            boolean r42 = this.delegate.retainAll(r4);
            if (r42 == false) goto L5;
            int r1 = this.delegate.size();
            AbstractMapBasedMultimap.access$212(this.this$0, r1 - r0);
            removeIfEmpty();
        L5:
            return r42;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final int size() {
            refreshIfEmpty();
            return this.delegate.size();
        }

        @Override // java.util.AbstractCollection
        public final String toString() {
            refreshIfEmpty();
            return this.delegate.toString();
        }
    }

    public class WrappedList extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements List<V> {
        final /* synthetic */ AbstractMapBasedMultimap this$0;

        public final class WrappedListIterator extends AbstractMapBasedMultimap<K, V>.WrappedCollection.WrappedIterator implements ListIterator<V> {
            final /* synthetic */ WrappedList this$1;

            public WrappedListIterator(WrappedList r1) {
                this.this$1 = r1;
                super(r1);
            }

            public WrappedListIterator(WrappedList r2, int r3) {
                this.this$1 = r2;
                super(r2, r2.getListDelegate().listIterator(r3));
            }

            private ListIterator<V> getDelegateListIterator() {
                return (ListIterator) getDelegateIterator();
            }

            @Override // java.util.ListIterator
            public void add(V r3) {
                boolean r0 = this.this$1.isEmpty();
                getDelegateListIterator().add(r3);
                AbstractMapBasedMultimap.access$208(this.this$1.this$0);
                if (r0 == false) goto L6;
                this.this$1.addToMap();
                return;
            }

            @Override // java.util.ListIterator
            public boolean hasPrevious() {
                return getDelegateListIterator().hasPrevious();
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                return getDelegateListIterator().nextIndex();
            }

            @Override // java.util.ListIterator
            public V previous() {
                return getDelegateListIterator().previous();
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                return getDelegateListIterator().previousIndex();
            }

            @Override // java.util.ListIterator
            public void set(V r1) {
                getDelegateListIterator().set(r1);
            }
        }

        public WrappedList(AbstractMapBasedMultimap r1, K r2, List<V> r3, AbstractMapBasedMultimap<K, V>.WrappedCollection r4) {
            this.this$0 = r1;
            super(r1, r2, r3, r4);
        }

        @Override // java.util.List
        public final void add(int r3, V r4) {
            refreshIfEmpty();
            boolean r0 = getDelegate().isEmpty();
            getListDelegate().add(r3, r4);
            AbstractMapBasedMultimap.access$208(this.this$0);
            if (r0 == false) goto L6;
            addToMap();
            return;
        }

        @Override // java.util.List
        public final boolean addAll(int r3, Collection<? extends V> r4) {
            if (r4.isEmpty() == false) goto L6;
            return false;
        L6:
            int r0 = size();
            boolean r32 = getListDelegate().addAll(r3, r4);
            if (r32 == false) goto L11;
            int r42 = getDelegate().size();
            AbstractMapBasedMultimap.access$212(this.this$0, r42 - r0);
            if (r0 != 0) goto L11;
            addToMap();
        L11:
            return r32;
        }

        @Override // java.util.List
        public final V get(int r1) {
            refreshIfEmpty();
            return getListDelegate().get(r1);
        }

        public final List<V> getListDelegate() {
            return (List) getDelegate();
        }

        @Override // java.util.List
        public final int indexOf(Object r1) {
            refreshIfEmpty();
            return getListDelegate().indexOf(r1);
        }

        @Override // java.util.List
        public final int lastIndexOf(Object r1) {
            refreshIfEmpty();
            return getListDelegate().lastIndexOf(r1);
        }

        @Override // java.util.List
        public final ListIterator<V> listIterator() {
            refreshIfEmpty();
            return new WrappedListIterator(this);
        }

        @Override // java.util.List
        public final ListIterator<V> listIterator(int r2) {
            refreshIfEmpty();
            return new WrappedListIterator(this, r2);
        }

        @Override // java.util.List
        public final V remove(int r2) {
            refreshIfEmpty();
            V r22 = getListDelegate().remove(r2);
            AbstractMapBasedMultimap.access$210(this.this$0);
            removeIfEmpty();
            return r22;
        }

        @Override // java.util.List
        public final V set(int r1, V r2) {
            refreshIfEmpty();
            return getListDelegate().set(r1, r2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [com.google.common.collect.AbstractMapBasedMultimap] */
        /* JADX WARN: Type inference failed for: r3v1, types: [com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection] */
        /* JADX WARN: Type inference failed for: r3v4 */
        /* JADX WARN: Type inference failed for: r3v5 */
        @Override // java.util.List
        public final List<V> subList(int r4, int r5) {
            refreshIfEmpty();
            ?? r0 = this.this$0;
            Object r1 = getKey();
            List<V> r42 = getListDelegate().subList(r4, r5);
            AbstractMapBasedMultimap<K, V>.WrappedCollection r52 = getAncestor();
            ?? r3 = this;
            if (r52 == null) goto L7;
            r3 = getAncestor();
        L7:
            return r0.wrapList(r1, r42, r3);
        }
    }

    public AbstractMapBasedMultimap(Map<K, Collection<V>> r2) {
        Preconditions.checkArgument(r2.isEmpty());
        this.map = r2;
    }

    public static /* synthetic */ Map access$000(AbstractMapBasedMultimap r0) {
        return r0.map;
    }

    public static /* synthetic */ Iterator access$100(Collection r0) {
        return iteratorOrListIterator(r0);
    }

    public static /* synthetic */ int access$208(AbstractMapBasedMultimap r2) {
        int r0 = r2.totalSize;
        r2.totalSize = r0 + 1;
        return r0;
    }

    public static /* synthetic */ int access$210(AbstractMapBasedMultimap r2) {
        int r0 = r2.totalSize;
        r2.totalSize = r0 - 1;
        return r0;
    }

    public static /* synthetic */ int access$212(AbstractMapBasedMultimap r1, int r2) {
        int r0 = r1.totalSize + r2;
        r1.totalSize = r0;
        return r0;
    }

    public static /* synthetic */ int access$220(AbstractMapBasedMultimap r1, int r2) {
        int r0 = r1.totalSize - r2;
        r1.totalSize = r0;
        return r0;
    }

    public static /* synthetic */ void access$300(AbstractMapBasedMultimap r0, Object r1) {
        r0.removeValuesForKey(r1);
    }

    private static <E> Iterator<E> iteratorOrListIterator(Collection<E> r1) {
        if ((r1 instanceof List) == false) goto L7;
        return ((List) r1).listIterator();
    L7:
        return r1.iterator();
    }

    private void removeValuesForKey(Object r2) {
        Collection r22 = (Collection) Maps.safeRemove(this.map, r2);
        if (r22 == null) goto L6;
        int r0 = r22.size();
        r22.clear();
        this.totalSize -= r0;
        return;
    }

    @Override // com.google.common.collect.Multimap
    public final void clear() {
        Iterator<Collection<V>> r0 = this.map.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().clear();
        goto L4
    L6:
        this.map.clear();
        this.totalSize = 0;
    }

    public abstract Collection<V> createCollection();

    public Collection<V> createCollection(K r1) {
        return createCollection();
    }

    @Override // com.google.common.collect.AbstractMultimap
    public final Collection<Map.Entry<K, V>> createEntries() {
        return new AbstractMultimap.Entries(this);
    }

    public final Map<K, Collection<V>> createMaybeNavigableAsMap() {
        Map<K, Collection<V>> r0 = this.map;
        if ((r0 instanceof NavigableMap) == false) goto L7;
        return new NavigableAsMap(this, (NavigableMap) this.map);
    L7:
        if ((r0 instanceof SortedMap) == false) goto L11;
        return new SortedAsMap(this, (SortedMap) this.map);
    L11:
        return new AsMap(this, this.map);
    }

    public final Set<K> createMaybeNavigableKeySet() {
        Map<K, Collection<V>> r0 = this.map;
        if ((r0 instanceof NavigableMap) == false) goto L7;
        return new NavigableKeySet(this, (NavigableMap) this.map);
    L7:
        if ((r0 instanceof SortedMap) == false) goto L11;
        return new SortedKeySet(this, (SortedMap) this.map);
    L11:
        return new KeySet(this, this.map);
    }

    @Override // com.google.common.collect.AbstractMultimap
    public final Collection<V> createValues() {
        return new AbstractMultimap.Values(this);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Collection<Map.Entry<K, V>> entries() {
        return super.entries();
    }

    @Override // com.google.common.collect.AbstractMultimap
    public Iterator<Map.Entry<K, V>> entryIterator() {
        return new AnonymousClass2(this);
    }

    @Override // com.google.common.collect.Multimap
    public Collection<V> get(K r2) {
        Collection<V> r0 = this.map.get(r2);
        if (r0 != null) goto L6;
        r0 = createCollection(r2);
    L6:
        return wrapCollection(r2, r0);
    }

    @Override // com.google.common.collect.Multimap
    public boolean put(K r4, V r5) {
        Collection<V> r0 = this.map.get(r4);
        if (r0 != null) goto L11;
        Collection<V> r02 = createCollection(r4);
        if (r02.add(r5) == false) goto L8;
        this.totalSize++;
        this.map.put(r4, r02);
        return true;
    L8:
        Buffer$$ExternalSyntheticBUOutline2.m("New Collection violated the Collection spec");
        return false;
    L11:
        if (r0.add(r5) == false) goto L14;
        this.totalSize++;
        return true;
    L14:
        return false;
    }

    @Override // com.google.common.collect.Multimap
    public final int size() {
        return this.totalSize;
    }

    public abstract <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> r1);

    @Override // com.google.common.collect.AbstractMultimap
    public Iterator<V> valueIterator() {
        return new AnonymousClass1(this);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Collection<V> values() {
        return super.values();
    }

    public abstract Collection<V> wrapCollection(K r1, Collection<V> r2);

    public final List<V> wrapList(K r2, List<V> r3, AbstractMapBasedMultimap<K, V>.WrappedCollection r4) {
        if ((r3 instanceof RandomAccess) == false) goto L7;
        return new RandomAccessWrappedList(this, r2, r3, r4);
    L7:
        return new WrappedList(this, r2, r3, r4);
    }
}
