package com.google.common.collect;

import com.google.common.collect.RegularImmutableMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
final class RegularImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    static final RegularImmutableBiMap<Object, Object> EMPTY = null;
    final transient Object[] alternatingKeysAndValues;
    private final transient RegularImmutableBiMap<V, K> inverse;
    private final transient Object keyHashTable;
    private final transient int keyOffset;
    private final transient int size;

    static {
        EMPTY = new RegularImmutableBiMap();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private RegularImmutableBiMap() {
        this.keyHashTable = null;
        this.alternatingKeysAndValues = new Object[0];
        this.keyOffset = 0;
        this.size = 0;
        this.inverse = this;
    }

    private RegularImmutableBiMap(Object r1, Object[] r2, int r3, RegularImmutableBiMap<V, K> r4) {
        this.keyHashTable = r1;
        this.alternatingKeysAndValues = r2;
        this.keyOffset = 1;
        this.size = r3;
        this.inverse = r4;
    }

    public RegularImmutableBiMap(Object[] r3, int r4) {
        this.alternatingKeysAndValues = r3;
        this.size = r4;
        this.keyOffset = 0;
        if (r4 < 2) goto L5;
        int r1 = ImmutableSet.chooseTableSize(r4);
    L6:
        this.keyHashTable = RegularImmutableMap.createHashTableOrThrow(r3, r4, r1, 0);
        this.inverse = new RegularImmutableBiMap(RegularImmutableMap.createHashTableOrThrow(r3, r4, r1, 1), r3, r4, this);
        return;
    L5:
        r1 = 0;
        goto L6
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public ImmutableSet<Map.Entry<K, V>> entrySet() {
        return new RegularImmutableMap.EntrySet(this, this.alternatingKeysAndValues, this.keyOffset, this.size);
    }

    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Set entrySet() {
        return entrySet();
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(Object r4) {
        V r3 = (V) RegularImmutableMap.get(this.keyHashTable, this.alternatingKeysAndValues, this.size, this.keyOffset, r4);
        if (r3 != null) goto L6;
        return null;
    L6:
        return r3;
    }

    @Override // com.google.common.collect.ImmutableBiMap
    public ImmutableBiMap<V, K> inverse() {
        return this.inverse;
    }

    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public ImmutableSet<K> keySet() {
        return new RegularImmutableMap.KeySet(this, new RegularImmutableMap.KeysOrValuesAsList(this.alternatingKeysAndValues, this.keyOffset, this.size));
    }

    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Set keySet() {
        return keySet();
    }

    @Override // java.util.Map
    public int size() {
        return this.size;
    }
}
