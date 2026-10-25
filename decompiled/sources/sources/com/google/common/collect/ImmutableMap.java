package com.google.common.collect;

import com.google.common.collect.ImmutableCollection;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;

/* loaded from: classes.dex */
public abstract class ImmutableMap<K, V> implements Map<K, V>, Serializable {
    static final Map.Entry<?, ?>[] EMPTY_ENTRY_ARRAY = null;

    public static class Builder<K, V> {
        Object[] alternatingKeysAndValues;
        DuplicateKey duplicateKey;
        boolean entriesUsed;
        int size;
        Comparator<? super V> valueComparator;

        /* loaded from: classes5.dex */
        public static final class DuplicateKey {
            private final Object key;
            private final Object value1;
            private final Object value2;

            public DuplicateKey(Object r1, Object r2, Object r3) {
                this.key = r1;
                this.value1 = r2;
                this.value2 = r3;
            }

            public IllegalArgumentException exception() {
                return new IllegalArgumentException("Multiple entries with same key: " + this.key + "=" + this.value1 + " and " + this.key + "=" + this.value2);
            }
        }

        public Builder() {
            this(4);
        }

        public Builder(int r1) {
            this.alternatingKeysAndValues = new Object[r1 * 2];
            this.size = 0;
            this.entriesUsed = false;
        }

        private ImmutableMap<K, V> build(boolean r6) {
            if (r6 == false) goto L8;
            DuplicateKey r0 = this.duplicateKey;
            if (r0 == null) goto L8;
            throw r0.exception();
        L8:
            int r02 = this.size;
            if (this.valueComparator != null) goto L12;
            Object[] r1 = this.alternatingKeysAndValues;
        L20:
            this.entriesUsed = true;
            RegularImmutableMap r03 = RegularImmutableMap.create(r02, r1, this);
            if (r6 == false) goto L27;
            DuplicateKey r5 = this.duplicateKey;
            if (r5 == null) goto L27;
            throw r5.exception();
        L27:
            return r03;
        L12:
            if (this.entriesUsed == false) goto L14;
            this.alternatingKeysAndValues = Arrays.copyOf(this.alternatingKeysAndValues, r02 * 2);
        L14:
            r1 = this.alternatingKeysAndValues;
            if (r6 == true) goto L19;
            r1 = lastEntryForEachKey(r1, this.size);
            if (r1.length >= this.alternatingKeysAndValues.length) goto L19;
            r02 = r1.length >>> 1;
        L19:
            sortEntries(r1, r02, this.valueComparator);
            goto L20
        }

        private void ensureCapacity(int r3) {
            int r32 = r3 * 2;
            Object[] r0 = this.alternatingKeysAndValues;
            if (r32 <= r0.length) goto L6;
            this.alternatingKeysAndValues = Arrays.copyOf(r0, ImmutableCollection.Builder.expandedCapacity(r0.length, r32));
            this.entriesUsed = false;
            return;
        }

        private Object[] lastEntryForEachKey(Object[] r7, int r8) {
            HashSet r6 = new HashSet();
            BitSet r0 = new BitSet();
            int r1 = r8 - 1;
        L3:
            if (r1 < 0) goto L9;
            Object r2 = r7[r1 * 2];
            Objects.requireNonNull(r2);
            if (r6.add(r2) == true) goto L7;
            r0.set(r1);
        L7:
            r1 = r1 - 1;
            goto L3
        L9:
            if (r0.isEmpty() == false) goto L11;
            return r7;
        L11:
            Object[] r62 = new Object[(r8 - r0.cardinality()) * 2];
            int r12 = 0;
            int r22 = 0;
        L13:
            if (r12 >= (r8 * 2)) goto L18;
            if (r0.get(r12 >>> 1) == true) goto L16;
            int r3 = r22 + 1;
            int r4 = r12 + 1;
            Object r5 = r7[r12];
            Objects.requireNonNull(r5);
            r62[r22] = r5;
            r22 = r22 + 2;
            r12 = r12 + 2;
            Object r42 = r7[r4];
            Objects.requireNonNull(r42);
            r62[r3] = r42;
            goto L13
        L16:
            r12 = r12 + 2;
            goto L13
        L18:
            return r62;
        }

        public static <V> void sortEntries(Object[] r6, int r7, Comparator<? super V> r8) {
            Map.Entry[] r0 = new Map.Entry[r7];
            int r1 = 0;
            int r2 = 0;
        L3:
            if (r2 >= r7) goto L5;
            int r3 = r2 * 2;
            Object r4 = r6[r3];
            Objects.requireNonNull(r4);
            Object r32 = r6[r3 + 1];
            Objects.requireNonNull(r32);
            r0[r2] = new AbstractMap.SimpleImmutableEntry(r4, r32);
            r2 = r2 + 1;
            goto L3
        L5:
            Arrays.sort(r0, 0, r7, Ordering.from(r8).onResultOf(new ImmutableMap$Builder$$ExternalSyntheticLambda0()));
        L6:
            if (r1 >= r7) goto L8;
            int r82 = r1 * 2;
            r6[r82] = r0[r1].getKey();
            r6[r82 + 1] = r0[r1].getValue();
            r1 = r1 + 1;
            goto L6
        }

        public ImmutableMap<K, V> build() {
            return buildOrThrow();
        }

        public ImmutableMap<K, V> buildKeepingLast() {
            return build(false);
        }

        public ImmutableMap<K, V> buildOrThrow() {
            return build(true);
        }

        public Builder<K, V> put(K r4, V r5) {
            ensureCapacity(this.size + 1);
            CollectPreconditions.checkEntryNotNull(r4, r5);
            Object[] r0 = this.alternatingKeysAndValues;
            int r1 = this.size;
            r0[r1 * 2] = r4;
            r0[(r1 * 2) + 1] = r5;
            this.size = r1 + 1;
            return this;
        }

        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> r2) {
            return put(r2.getKey(), r2.getValue());
        }

        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> r3) {
            if ((r3 instanceof Collection) == false) goto L5;
            ensureCapacity(this.size + ((Collection) r3).size());
        L5:
            Iterator<? extends Map.Entry<? extends K, ? extends V>> r32 = r3.iterator();
        L7:
            if (r32.hasNext() == false) goto L9;
            put(r32.next());
            goto L7
        L9:
            return this;
        }

        public Builder<K, V> putAll(Map<? extends K, ? extends V> r1) {
            return putAll(r1.entrySet());
        }
    }

    static {
        EMPTY_ENTRY_ARRAY = new Map.Entry[0];
    }

    public ImmutableMap() {
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder();
    }

    public static <K, V> Builder<K, V> builderWithExpectedSize(int r1) {
        CollectPreconditions.checkNonnegative(r1, "expectedSize");
        return new Builder(r1);
    }

    public static <K, V> ImmutableMap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> r2) {
        if ((r2 instanceof Collection) == false) goto L5;
        int r0 = ((Collection) r2).size();
    L6:
        Builder r1 = new Builder(r0);
        r1.putAll(r2);
        return r1.build();
    L5:
        r0 = 4;
        goto L6
    }

    public static <K, V> ImmutableMap<K, V> copyOf(Map<? extends K, ? extends V> r2) {
        if ((r2 instanceof ImmutableMap) == false) goto L10;
        if ((r2 instanceof SortedMap) == true) goto L10;
        ImmutableMap<K, V> r0 = (ImmutableMap) r2;
        if (r0.isPartialView() == true) goto L10;
        return r0;
    L10:
        return copyOf(r2.entrySet());
    }

    public static <K, V> ImmutableMap<K, V> of() {
        return (ImmutableMap<K, V>) RegularImmutableMap.EMPTY;
    }

    public static <K, V> ImmutableMap<K, V> of(K r1, V r2, K r3, V r4, K r5, V r6, K r7, V r8) {
        CollectPreconditions.checkEntryNotNull(r1, r2);
        CollectPreconditions.checkEntryNotNull(r3, r4);
        CollectPreconditions.checkEntryNotNull(r5, r6);
        CollectPreconditions.checkEntryNotNull(r7, r8);
        return RegularImmutableMap.create(4, new Object[]{r1, r2, r3, r4, r5, r6, r7, r8});
    }

    public static <K, V> ImmutableMap<K, V> of(K r1, V r2, K r3, V r4, K r5, V r6, K r7, V r8, K r9, V r10) {
        CollectPreconditions.checkEntryNotNull(r1, r2);
        CollectPreconditions.checkEntryNotNull(r3, r4);
        CollectPreconditions.checkEntryNotNull(r5, r6);
        CollectPreconditions.checkEntryNotNull(r7, r8);
        CollectPreconditions.checkEntryNotNull(r9, r10);
        return RegularImmutableMap.create(5, new Object[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10});
    }

    public static <K, V> ImmutableMap<K, V> of(K r1, V r2, K r3, V r4, K r5, V r6, K r7, V r8, K r9, V r10, K r11, V r12, K r13, V r14) {
        CollectPreconditions.checkEntryNotNull(r1, r2);
        CollectPreconditions.checkEntryNotNull(r3, r4);
        CollectPreconditions.checkEntryNotNull(r5, r6);
        CollectPreconditions.checkEntryNotNull(r7, r8);
        CollectPreconditions.checkEntryNotNull(r9, r10);
        CollectPreconditions.checkEntryNotNull(r11, r12);
        CollectPreconditions.checkEntryNotNull(r13, r14);
        return RegularImmutableMap.create(7, new Object[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14});
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public boolean containsKey(Object r1) {
        if (get(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Map
    public boolean containsValue(Object r1) {
        return values().contains(r1);
    }

    @Override // java.util.Map
    public abstract ImmutableSet<Map.Entry<K, V>> entrySet();

    @Override // java.util.Map
    public boolean equals(Object r1) {
        return Maps.equalsImpl(this, r1);
    }

    @Override // java.util.Map
    public abstract V get(Object r1);

    @Override // java.util.Map
    public final V getOrDefault(Object r1, V r2) {
        V r0 = get(r1);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return r2;
    }

    @Override // java.util.Map
    public int hashCode() {
        return Sets.hashCodeImpl(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        if (size() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public abstract boolean isPartialView();

    @Override // java.util.Map
    public abstract ImmutableSet<K> keySet();

    @Override // java.util.Map
    @Deprecated
    public final V put(K r1, V r2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final V remove(Object r1) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return Maps.toStringImpl(this);
    }

    @Override // java.util.Map
    public abstract ImmutableCollection<V> values();
}
