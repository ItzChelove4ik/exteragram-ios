package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes5.dex */
public abstract class Maps {

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* renamed from: com.google.common.collect.Maps$2, reason: invalid class name */
    public class AnonymousClass2<K, V> extends TransformedIterator<Map.Entry<K, V>, V> {
        public AnonymousClass2(Iterator r1) {
            super(r1);
        }

        @Override // com.google.common.collect.TransformedIterator
        public /* bridge */ /* synthetic */ Object transform(Object r1) {
            return transform((Map.Entry) r1);
        }

        public V transform(Map.Entry<K, V> r1) {
            return r1.getValue();
        }
    }

    public static abstract class EntrySet<K, V> extends Sets.ImprovedAbstractSet<Map.Entry<K, V>> {
        public EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            map().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public abstract boolean contains(Object r1);

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return map().isEmpty();
        }

        public abstract Map<K, V> map();

        @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> r2) {
            return super.removeAll((Collection) Preconditions.checkNotNull(r2));
        L5:
            return Sets.removeAllImpl(this, r2.iterator());
        }

        @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> r4) {
            return super.retainAll((Collection) Preconditions.checkNotNull(r4));
        L4:
            HashSet r0 = Sets.newHashSetWithExpectedSize(r4.size());
            Iterator<?> r42 = r4.iterator();
        L6:
            if (r42.hasNext() == false) goto L13;
            Object r1 = r42.next();
            if (this.contains(r1) == false) goto L6;
            if ((r1 instanceof Map.Entry) == false) goto L6;
            r0.add(((Map.Entry) r1).getKey());
            goto L6
        L13:
            return this.map().keySet().retainAll(r0);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return map().size();
        }
    }

    public static class KeySet<K, V> extends Sets.ImprovedAbstractSet<K> {
        final Map<K, V> map;

        public KeySet(Map<K, V> r1) {
            this.map = (Map) Preconditions.checkNotNull(r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object r1) {
            return map().containsKey(r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return map().isEmpty();
        }

        public Map<K, V> map() {
            return this.map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return map().size();
        }
    }

    public static class Values<K, V> extends AbstractCollection<V> {
        final Map<K, V> map;

        public Values(Map<K, V> r1) {
            this.map = (Map) Preconditions.checkNotNull(r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            map().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object r1) {
            return map().containsValue(r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return map().isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return Maps.valueIterator(map().entrySet().iterator());
        }

        public final Map<K, V> map() {
            return this.map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object r4) {
            return super.remove(r4);
        L4:
            Iterator<Map.Entry<K, V>> r0 = this.map().entrySet().iterator();
        L6:
            if (r0.hasNext() == false) goto L11;
            Map.Entry<K, V> r1 = r0.next();
            if (Objects.equals(r4, r1.getValue()) == false) goto L6;
            this.map().remove(r1.getKey());
            return true;
        L11:
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> r5) {
            return super.removeAll((Collection) Preconditions.checkNotNull(r5));
        L4:
            HashSet r0 = new HashSet();
            Iterator<Map.Entry<K, V>> r1 = this.map().entrySet().iterator();
        L6:
            if (r1.hasNext() == false) goto L11;
            Map.Entry<K, V> r2 = r1.next();
            if (r5.contains(r2.getValue()) == false) goto L6;
            r0.add(r2.getKey());
            goto L6
        L11:
            return this.map().keySet().removeAll(r0);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> r5) {
            return super.retainAll((Collection) Preconditions.checkNotNull(r5));
        L4:
            HashSet r0 = new HashSet();
            Iterator<Map.Entry<K, V>> r1 = this.map().entrySet().iterator();
        L6:
            if (r1.hasNext() == false) goto L11;
            Map.Entry<K, V> r2 = r1.next();
            if (r5.contains(r2.getValue()) == false) goto L6;
            r0.add(r2.getKey());
            goto L6
        L11:
            return this.map().keySet().retainAll(r0);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return map().size();
        }
    }

    public static abstract class ViewCachingAbstractMap<K, V> extends AbstractMap<K, V> {
        private transient Set<Map.Entry<K, V>> entrySet;
        private transient Collection<V> values;

        public ViewCachingAbstractMap() {
        }

        public abstract Set<Map.Entry<K, V>> createEntrySet();

        public Collection<V> createValues() {
            return new Values(this);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> r0 = this.entrySet;
            if (r0 != null) goto L6;
            Set<Map.Entry<K, V>> r02 = createEntrySet();
            this.entrySet = r02;
            return r02;
        L6:
            return r0;
        }

        @Override // java.util.AbstractMap, java.util.Map
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

    public static int capacity(int r4) {
        if (r4 >= 3) goto L7;
        CollectPreconditions.checkNonnegative(r4, "expectedSize");
        return r4 + 1;
    L7:
        if (r4 < 1073741824) goto L9;
        return Integer.MAX_VALUE;
    L9:
        return (int) Math.ceil(r4 / 0.75d);
    }

    public static boolean containsValueImpl(Map<?, ?> r0, Object r1) {
        return Iterators.contains(valueIterator(r0.entrySet().iterator()), r1);
    }

    public static boolean equalsImpl(Map<?, ?> r1, Object r2) {
        if (r1 != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof Map) == true) goto L8;
        return false;
    L8:
        return r1.entrySet().equals(((Map) r2).entrySet());
    }

    public static <K, V> Map.Entry<K, V> immutableEntry(K r1, V r2) {
        return new AbstractMap.SimpleImmutableEntry(r1, r2);
    }

    public static <K, V> HashMap<K, V> newHashMap(Map<? extends K, ? extends V> r1) {
        return new HashMap(r1);
    }

    public static <K, V> HashMap<K, V> newHashMapWithExpectedSize(int r1) {
        return new HashMap(capacity(r1));
    }

    public static boolean safeContainsKey(Map<?, ?> r0, Object r1) {
        Preconditions.checkNotNull(r0);
        return r0.containsKey(r1);
    L5:
        return false;
    }

    public static <V> V safeGet(Map<?, V> r0, Object r1) {
        Preconditions.checkNotNull(r0);
        return r0.get(r1);
    L5:
        return null;
    }

    public static <V> V safeRemove(Map<?, V> r0, Object r1) {
        Preconditions.checkNotNull(r0);
        return r0.remove(r1);
    L5:
        return null;
    }

    public static String toStringImpl(Map<?, ?> r3) {
        StringBuilder r0 = Collections2.newStringBuilderForCollection(r3.size());
        r0.append('{');
        Iterator<Map.Entry<?, ?>> r32 = r3.entrySet().iterator();
        boolean r1 = true;
    L4:
        if (r32.hasNext() == false) goto L9;
        Map.Entry<?, ?> r2 = r32.next();
        if (r1 == true) goto L8;
        r0.append(", ");
    L8:
        r0.append(r2.getKey());
        r0.append(SignatureVisitor.INSTANCEOF);
        r0.append(r2.getValue());
        r1 = false;
        goto L4
    L9:
        r0.append('}');
        return r0.toString();
    }

    public static <K, V> Iterator<V> valueIterator(Iterator<Map.Entry<K, V>> r1) {
        return new AnonymousClass2(r1);
    }
}
