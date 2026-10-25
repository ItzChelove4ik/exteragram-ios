package org.mvel2.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class SimpleIndexHashMapWrapper<K, V> implements Map<K, V> {
    private final ArrayList<SimpleIndexHashMapWrapper<K, V>.ValueContainer<K, V>> indexBasedLookup;
    private int indexCounter;
    private final Map<K, SimpleIndexHashMapWrapper<K, V>.ValueContainer<K, V>> wrappedMap;

    public class ValueContainer<K, V> {
        private int index;
        private K key;
        final /* synthetic */ SimpleIndexHashMapWrapper this$0;
        private V value;

        public ValueContainer(SimpleIndexHashMapWrapper r1, int r2, K r3, V r4) {
            this.this$0 = r1;
            this.index = r2;
            this.key = r3;
            this.value = r4;
        }

        public int getIndex() {
            return this.index;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public void setKey(K r1) {
            this.key = r1;
        }

        public void setValue(V r1) {
            this.value = r1;
        }
    }

    public SimpleIndexHashMapWrapper() {
        this.wrappedMap = new HashMap();
        this.indexBasedLookup = new ArrayList();
    }

    public SimpleIndexHashMapWrapper(SimpleIndexHashMapWrapper<K, V> r8, boolean r9) {
        this.indexBasedLookup = new ArrayList(r8.indexBasedLookup.size());
        this.wrappedMap = new HashMap();
        ArrayList<SimpleIndexHashMapWrapper<K, V>.ValueContainer<K, V>> r82 = r8.indexBasedLookup;
        int r0 = 0;
        if (r9 == false) goto L7;
        int r92 = r82.size();
        int r1 = 0;
    L5:
        if (r1 >= r92) goto L10;
        SimpleIndexHashMapWrapper<K, V>.ValueContainer<K, V> r2 = r82.get(r1);
        r1 = r1 + 1;
        SimpleIndexHashMapWrapper<K, V>.ValueContainer<K, V> r22 = r2;
        SimpleIndexHashMapWrapper<K, V>.ValueContainer<K, V> r3 = new ValueContainer(this, r0, r22.getKey(), null);
        this.indexBasedLookup.add(r3);
        this.wrappedMap.put(r22.getKey(), r3);
        r0 = r0 + 1;
        goto L5
    L10:
        return;
    L7:
        int r93 = r82.size();
        int r12 = 0;
    L8:
        if (r12 >= r93) goto L13;
        SimpleIndexHashMapWrapper<K, V>.ValueContainer<K, V> r23 = r82.get(r12);
        r12 = r12 + 1;
        SimpleIndexHashMapWrapper<K, V>.ValueContainer<K, V> r24 = r23;
        SimpleIndexHashMapWrapper<K, V>.ValueContainer<K, V> r32 = new ValueContainer(this, r0, r24.getKey(), r24.getValue());
        this.indexBasedLookup.add(r32);
        this.wrappedMap.put(r24.getKey(), r32);
        r0 = r0 + 1;
        goto L8
    }

    public SimpleIndexHashMapWrapper(K[] r3) {
        this.wrappedMap = new HashMap(r3.length * 2);
        this.indexBasedLookup = new ArrayList(r3.length);
        initWithKeys(r3);
    }

    public SimpleIndexHashMapWrapper(K[] r3, int r4, float r5) {
        this.wrappedMap = new HashMap(r4 * 2, r5);
        this.indexBasedLookup = new ArrayList(r4);
        initWithKeys(r3);
    }

    public void addKey(K r4) {
        int r1 = this.indexCounter;
        this.indexCounter = r1 + 1;
        SimpleIndexHashMapWrapper<K, V>.ValueContainer<K, V> r0 = new ValueContainer(this, r1, r4, null);
        this.indexBasedLookup.add(r0);
        this.wrappedMap.put(r4, r0);
    }

    public void addKey(K r4, V r5) {
        int r1 = this.indexCounter;
        this.indexCounter = r1 + 1;
        SimpleIndexHashMapWrapper<K, V>.ValueContainer<K, V> r0 = new ValueContainer(this, r1, r4, r5);
        this.indexBasedLookup.add(r0);
        this.wrappedMap.put(r4, r0);
    }

    @Override // java.util.Map
    public void clear() {
        throw new UnsupportedOperationException("cannot clear map");
    }

    @Override // java.util.Map
    public boolean containsKey(Object r1) {
        return this.wrappedMap.containsKey(r1);
    }

    @Override // java.util.Map
    public boolean containsValue(Object r1) {
        return this.wrappedMap.containsValue(r1);
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public V get(Object r1) {
        return this.wrappedMap.get(r1).getValue();
    }

    public V getByIndex(int r1) {
        return this.indexBasedLookup.get(r1).getValue();
    }

    public K getKeyAtIndex(int r1) {
        return this.indexBasedLookup.get(r1).getKey();
    }

    public int indexOf(K r1) {
        return this.wrappedMap.get(r1).getIndex();
    }

    public void initWithKeys(K[] r8) {
        int r0 = r8.length;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L5;
        K r3 = r8[r1];
        SimpleIndexHashMapWrapper<K, V>.ValueContainer<K, V> r4 = new ValueContainer(this, r2, r3, null);
        this.wrappedMap.put(r3, r4);
        this.indexBasedLookup.add(r4);
        r1 = r1 + 1;
        r2 = r2 + 1;
        goto L3
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.wrappedMap.isEmpty();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return this.wrappedMap.keySet();
    }

    @Override // java.util.Map
    public V put(K r2, V r3) {
        SimpleIndexHashMapWrapper<K, V>.ValueContainer<K, V> r32 = this.wrappedMap.get(r2);
        if (r32 == null) goto L6;
        this.indexBasedLookup.add(r32);
        return this.wrappedMap.put(r2, r32).getValue();
    L6:
        GlShader$$ExternalSyntheticBUOutline1.m("cannot add a new entry.  you must allocate a new key with addKey() first.");
        return null;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> r1) {
    }

    public void putAtIndex(int r1, V r2) {
        this.indexBasedLookup.get(r1).setValue(r2);
    }

    @Override // java.util.Map
    public V remove(Object r1) {
        throw new UnsupportedOperationException("cannot remove keys");
    }

    @Override // java.util.Map
    public int size() {
        return this.wrappedMap.size();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }
}
