package com.google.common.collect;

import androidx.collection.ArraySet$$ExternalSyntheticBUOutline0;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
class CompactHashMap<K, V> implements Map<K, V>, Serializable {
    private static final Object NOT_FOUND = null;
    transient int[] entries;
    transient Object[] keys;
    private transient int metadata;
    private transient int size;
    private transient Object table;
    transient Object[] values;

    /* renamed from: com.google.common.collect.CompactHashMap$1, reason: invalid class name */
    public class AnonymousClass1 extends CompactHashMap<K, V>.Itr<K> {
        final /* synthetic */ CompactHashMap this$0;

        public AnonymousClass1(CompactHashMap r2) {
            this.this$0 = r2;
            super(r2, null);
        }

        @Override // com.google.common.collect.CompactHashMap.Itr
        public K getOutput(int r1) {
            return (K) CompactHashMap.access$100(this.this$0, r1);
        }
    }

    /* renamed from: com.google.common.collect.CompactHashMap$2, reason: invalid class name */
    public class AnonymousClass2 extends CompactHashMap<K, V>.Itr<Map.Entry<K, V>> {
        final /* synthetic */ CompactHashMap this$0;

        public AnonymousClass2(CompactHashMap r2) {
            this.this$0 = r2;
            super(r2, null);
        }

        @Override // com.google.common.collect.CompactHashMap.Itr
        public /* bridge */ /* synthetic */ Object getOutput(int r1) {
            return getOutput(r1);
        }

        @Override // com.google.common.collect.CompactHashMap.Itr
        public Map.Entry<K, V> getOutput(int r2) {
            return new MapEntry(this.this$0, r2);
        }
    }

    /* renamed from: com.google.common.collect.CompactHashMap$3, reason: invalid class name */
    public class AnonymousClass3 extends CompactHashMap<K, V>.Itr<V> {
        final /* synthetic */ CompactHashMap this$0;

        public AnonymousClass3(CompactHashMap r2) {
            this.this$0 = r2;
            super(r2, null);
        }

        @Override // com.google.common.collect.CompactHashMap.Itr
        public V getOutput(int r1) {
            return (V) CompactHashMap.access$800(this.this$0, r1);
        }
    }

    public final class EntrySetView extends AbstractSet<Map.Entry<K, V>> {
        final /* synthetic */ CompactHashMap this$0;

        private EntrySetView(CompactHashMap r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ EntrySetView(CompactHashMap r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.this$0.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object r4) {
            Map<K, V> r0 = this.this$0.delegateOrNull();
            if (r0 == null) goto L7;
            return r0.entrySet().contains(r4);
        L7:
            if ((r4 instanceof Map.Entry) == false) goto L14;
            Map.Entry r42 = (Map.Entry) r4;
            int r02 = CompactHashMap.access$700(this.this$0, r42.getKey());
            if (r02 == (-1)) goto L14;
            if (Objects.equals(CompactHashMap.access$800(this.this$0, r02), r42.getValue()) == false) goto L14;
            return true;
        L14:
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return this.this$0.entrySetIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object r10) {
            Map<K, V> r0 = this.this$0.delegateOrNull();
            if (r0 == null) goto L7;
            return r0.entrySet().remove(r10);
        L7:
            if ((r10 instanceof Map.Entry) == false) goto L16;
            Map.Entry r102 = (Map.Entry) r10;
            if (this.this$0.needsAllocArrays() == false) goto L11;
            return false;
        L11:
            int r4 = CompactHashMap.access$900(this.this$0);
            int r103 = CompactHashing.remove(r102.getKey(), r102.getValue(), r4, CompactHashMap.access$1000(this.this$0), CompactHashMap.access$1100(this.this$0), CompactHashMap.access$1200(this.this$0), CompactHashMap.access$1300(this.this$0));
            if (r103 != (-1)) goto L14;
            return false;
        L14:
            this.this$0.moveLastEntry(r103, r4);
            CompactHashMap.access$1410(this.this$0);
            this.this$0.incrementModCount();
            return true;
        L16:
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.this$0.size();
        }
    }

    public abstract class Itr<T> implements Iterator<T> {
        int currentIndex;
        int expectedMetadata;
        int indexToRemove;
        final /* synthetic */ CompactHashMap this$0;

        private Itr(CompactHashMap r2) {
            this.this$0 = r2;
            this.expectedMetadata = CompactHashMap.access$000(r2);
            this.currentIndex = r2.firstEntryIndex();
            this.indexToRemove = -1;
        }

        public /* synthetic */ Itr(CompactHashMap r1, AnonymousClass1 r2) {
            this(r1);
        }

        private void checkForConcurrentModification() {
            if (CompactHashMap.access$000(this.this$0) != this.expectedMetadata) goto L5;
            return;
        L5:
            ArraySet$$ExternalSyntheticBUOutline0.m();
        }

        public abstract T getOutput(int r1);

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.currentIndex < 0) goto L6;
            return true;
        L6:
            return false;
        }

        public void incrementExpectedModCount() {
            this.expectedMetadata += 32;
        }

        @Override // java.util.Iterator
        public T next() {
            checkForConcurrentModification();
            if (hasNext() == false) goto L6;
            int r0 = this.currentIndex;
            this.indexToRemove = r0;
            T r02 = getOutput(r0);
            this.currentIndex = this.this$0.getSuccessor(this.currentIndex);
            return r02;
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
            checkForConcurrentModification();
            if (this.indexToRemove < 0) goto L5;
            boolean r0 = true;
        L6:
            CollectPreconditions.checkRemove(r0);
            incrementExpectedModCount();
            CompactHashMap r02 = this.this$0;
            r02.remove(CompactHashMap.access$100(r02, this.indexToRemove));
            this.currentIndex = this.this$0.adjustAfterRemove(this.currentIndex, this.indexToRemove);
            this.indexToRemove = -1;
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    public final class KeySetView extends AbstractSet<K> {
        final /* synthetic */ CompactHashMap this$0;

        private KeySetView(CompactHashMap r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ KeySetView(CompactHashMap r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.this$0.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object r1) {
            return this.this$0.containsKey(r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return this.this$0.keySetIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object r2) {
            Map<K, V> r0 = this.this$0.delegateOrNull();
            if (r0 == null) goto L7;
            return r0.keySet().remove(r2);
        L7:
            if (CompactHashMap.access$300(this.this$0, r2) == CompactHashMap.access$400()) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.this$0.size();
        }
    }

    public final class MapEntry extends AbstractMapEntry<K, V> {
        private final K key;
        private int lastKnownIndex;
        final /* synthetic */ CompactHashMap this$0;

        public MapEntry(CompactHashMap r1, int r2) {
            this.this$0 = r1;
            this.key = (K) CompactHashMap.access$100(r1, r2);
            this.lastKnownIndex = r2;
        }

        private void updateLastKnownIndex() {
            int r0 = this.lastKnownIndex;
            if (r0 != (-1)) goto L5;
        L10:
            this.lastKnownIndex = CompactHashMap.access$700(this.this$0, this.key);
            return;
        L5:
            if (r0 >= this.this$0.size()) goto L10;
            if (Objects.equals(this.key, CompactHashMap.access$100(this.this$0, this.lastKnownIndex)) == false) goto L10;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V getValue() {
            Map<K, V> r0 = this.this$0.delegateOrNull();
            if (r0 != null) goto L5;
            updateLastKnownIndex();
            int r02 = this.lastKnownIndex;
            if (r02 != (-1)) goto L11;
            return (V) NullnessCasts.unsafeNull();
        L11:
            return (V) CompactHashMap.access$800(this.this$0, r02);
        L5:
            return (V) NullnessCasts.uncheckedCastNullableTToT(r0.get(this.key));
        }

        @Override // java.util.Map.Entry
        public V setValue(V r4) {
            Map<K, V> r0 = this.this$0.delegateOrNull();
            if (r0 != null) goto L5;
            updateLastKnownIndex();
            int r02 = this.lastKnownIndex;
            CompactHashMap r1 = this.this$0;
            if (r02 != (-1)) goto L10;
            r1.put(this.key, r4);
            return (V) NullnessCasts.unsafeNull();
        L10:
            V r03 = (V) CompactHashMap.access$800(r1, r02);
            CompactHashMap.access$1500(this.this$0, this.lastKnownIndex, r4);
            return r03;
        L5:
            return (V) NullnessCasts.uncheckedCastNullableTToT(r0.put(this.key, r4));
        }
    }

    public final class ValuesView extends AbstractCollection<V> {
        final /* synthetic */ CompactHashMap this$0;

        private ValuesView(CompactHashMap r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ ValuesView(CompactHashMap r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.this$0.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return this.this$0.valuesIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.this$0.size();
        }
    }

    static {
        NOT_FOUND = new Object();
    }

    public CompactHashMap(int r1) {
        init(r1);
    }

    public static /* synthetic */ int access$000(CompactHashMap r0) {
        return r0.metadata;
    }

    public static /* synthetic */ Object access$100(CompactHashMap r0, int r1) {
        return r0.key(r1);
    }

    public static /* synthetic */ Object access$1000(CompactHashMap r0) {
        return r0.requireTable();
    }

    public static /* synthetic */ int[] access$1100(CompactHashMap r0) {
        return r0.requireEntries();
    }

    public static /* synthetic */ Object[] access$1200(CompactHashMap r0) {
        return r0.requireKeys();
    }

    public static /* synthetic */ Object[] access$1300(CompactHashMap r0) {
        return r0.requireValues();
    }

    public static /* synthetic */ int access$1410(CompactHashMap r2) {
        int r0 = r2.size;
        r2.size = r0 - 1;
        return r0;
    }

    public static /* synthetic */ void access$1500(CompactHashMap r0, int r1, Object r2) {
        r0.setValue(r1, r2);
    }

    public static /* synthetic */ Object access$300(CompactHashMap r0, Object r1) {
        return r0.removeHelper(r1);
    }

    public static /* synthetic */ Object access$400() {
        return NOT_FOUND;
    }

    public static /* synthetic */ int access$700(CompactHashMap r0, Object r1) {
        return r0.indexOf(r1);
    }

    public static /* synthetic */ Object access$800(CompactHashMap r0, int r1) {
        return r0.value(r1);
    }

    public static /* synthetic */ int access$900(CompactHashMap r0) {
        return r0.hashTableMask();
    }

    public static <K, V> CompactHashMap<K, V> createWithExpectedSize(int r1) {
        return new CompactHashMap(r1);
    }

    private int entry(int r1) {
        return requireEntries()[r1];
    }

    private int hashTableMask() {
        return (1 << (this.metadata & 31)) - 1;
    }

    private int indexOf(Object r7) {
        if (needsAllocArrays() == false) goto L5;
        return -1;
    L5:
        int r0 = Hashing.smearedHash(r7);
        int r2 = hashTableMask();
        int r3 = CompactHashing.tableGet(requireTable(), r0 & r2);
        if (r3 != 0) goto L8;
        return -1;
    L8:
        int r02 = CompactHashing.getHashPrefix(r0, r2);
    L9:
        int r32 = r3 - 1;
        int r4 = entry(r32);
        if (CompactHashing.getHashPrefix(r4, r2) == r02) goto L12;
    L14:
        r3 = CompactHashing.getNext(r4, r2);
        if (r3 != 0) goto L9;
        return -1;
    L12:
        if (Objects.equals(r7, key(r32)) == false) goto L14;
        return r32;
    }

    private K key(int r1) {
        return (K) requireKeys()[r1];
    }

    private Object removeHelper(Object r8) {
        if (needsAllocArrays() == true) goto L5;
        int r2 = hashTableMask();
        int r82 = CompactHashing.remove(r8, null, r2, requireTable(), requireEntries(), requireKeys(), null);
        if (r82 == (-1)) goto L9;
        V r0 = value(r82);
        moveLastEntry(r82, r2);
        this.size--;
        incrementModCount();
        return r0;
    L9:
        return NOT_FOUND;
    L5:
        return NOT_FOUND;
    }

    private int[] requireEntries() {
        int[] r0 = this.entries;
        Objects.requireNonNull(r0);
        return r0;
    }

    private Object[] requireKeys() {
        Object[] r0 = this.keys;
        Objects.requireNonNull(r0);
        return r0;
    }

    private Object requireTable() {
        Object r0 = this.table;
        Objects.requireNonNull(r0);
        return r0;
    }

    private Object[] requireValues() {
        Object[] r0 = this.values;
        Objects.requireNonNull(r0);
        return r0;
    }

    private void resizeMeMaybe(int r3) {
        int r0 = requireEntries().length;
        if (r3 <= r0) goto L8;
        int r32 = Math.min(1073741823, (Math.max(1, r0 >>> 1) + r0) | 1);
        if (r32 == r0) goto L9;
        resizeEntries(r32);
        return;
    L9:
        return;
    }

    private int resizeTable(int r9, int r10, int r11, int r12) {
        Object r0 = CompactHashing.createTable(r10);
        int r102 = r10 - 1;
        if (r12 == 0) goto L5;
        CompactHashing.tableSet(r0, r11 & r102, r12 + 1);
    L5:
        Object r112 = requireTable();
        int[] r122 = requireEntries();
        int r1 = 0;
    L6:
        if (r1 > r9) goto L11;
        int r2 = CompactHashing.tableGet(r112, r1);
    L8:
        if (r2 == 0) goto L10;
        int r3 = r2 - 1;
        int r4 = r122[r3];
        int r5 = CompactHashing.getHashPrefix(r4, r9) | r1;
        int r6 = r5 & r102;
        int r7 = CompactHashing.tableGet(r0, r6);
        CompactHashing.tableSet(r0, r6, r2);
        r122[r3] = CompactHashing.maskCombine(r5, r7, r102);
        r2 = CompactHashing.getNext(r4, r9);
        goto L8
    L10:
        r1 = r1 + 1;
        goto L6
    L11:
        this.table = r0;
        setHashTableMask(r102);
        return r102;
    }

    private void setEntry(int r1, int r2) {
        requireEntries()[r1] = r2;
    }

    private void setHashTableMask(int r3) {
        int r32 = 32 - Integer.numberOfLeadingZeros(r3);
        this.metadata = CompactHashing.maskCombine(this.metadata, r32, 31);
    }

    private void setKey(int r1, K r2) {
        requireKeys()[r1] = r2;
    }

    private void setValue(int r1, V r2) {
        requireValues()[r1] = r2;
    }

    private V value(int r1) {
        return (V) requireValues()[r1];
    }

    public void accessEntry(int r1) {
    }

    public int adjustAfterRemove(int r1, int r2) {
        return r1 - 1;
    }

    public int allocArrays() {
        Preconditions.checkState(needsAllocArrays(), "Arrays already allocated");
        int r0 = this.metadata;
        int r1 = CompactHashing.tableSize(r0);
        this.table = CompactHashing.createTable(r1);
        setHashTableMask(r1 - 1);
        this.entries = new int[r0];
        this.keys = new Object[r0];
        this.values = new Object[r0];
        return r0;
    }

    @Override // java.util.Map
    public void clear() {
        if (needsAllocArrays() == false) goto L5;
        return;
    L5:
        incrementModCount();
        Map<K, V> r0 = delegateOrNull();
        if (r0 == null) goto L9;
        this.metadata = Ints.constrainToRange(size(), 3, 1073741823);
        r0.clear();
        this.table = null;
        this.size = 0;
        return;
    L9:
        Arrays.fill(requireKeys(), 0, this.size, null);
        Arrays.fill(requireValues(), 0, this.size, null);
        CompactHashing.tableClear(requireTable());
        Arrays.fill(requireEntries(), 0, this.size, 0);
        this.size = 0;
    }

    @Override // java.util.Map
    public boolean containsKey(Object r2) {
        Map<K, V> r0 = delegateOrNull();
        if (r0 == null) goto L7;
        return r0.containsKey(r2);
    L7:
        if (indexOf(r2) == (-1)) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // java.util.Map
    public boolean containsValue(Object r4) {
        Map<K, V> r0 = delegateOrNull();
        if (r0 != null) goto L5;
        int r1 = 0;
    L8:
        if (r1 >= this.size) goto L14;
        if (Objects.equals(r4, value(r1)) == true) goto L11;
        r1 = r1 + 1;
        goto L8
    L11:
        return true;
    L14:
        return false;
    L5:
        return r0.containsValue(r4);
    }

    public Map<K, V> convertToHashFloodingResistantImplementation() {
        Map<K, V> r0 = createHashFloodingResistantDelegate(hashTableMask() + 1);
        int r1 = firstEntryIndex();
    L3:
        if (r1 < 0) goto L5;
        r0.put(key(r1), value(r1));
        r1 = getSuccessor(r1);
        goto L3
    L5:
        this.table = r0;
        this.entries = null;
        this.keys = null;
        this.values = null;
        incrementModCount();
        return r0;
    }

    public Map<K, V> createHashFloodingResistantDelegate(int r2) {
        return new LinkedHashMap(r2, 1.0f);
    }

    public Map<K, V> delegateOrNull() {
        Object r1 = this.table;
        if ((r1 instanceof Map) == true) goto L5;
        return null;
    L5:
        return (Map) r1;
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return new EntrySetView(this, null);
    }

    public Iterator<Map.Entry<K, V>> entrySetIterator() {
        Map<K, V> r0 = delegateOrNull();
        if (r0 == null) goto L7;
        return r0.entrySet().iterator();
    L7:
        return new AnonymousClass2(this);
    }

    @Override // java.util.Map
    public boolean equals(Object r1) {
        return Maps.equalsImpl(this, r1);
    }

    public int firstEntryIndex() {
        if (isEmpty() == false) goto L6;
        return -1;
    L6:
        return 0;
    }

    @Override // java.util.Map
    public V get(Object r2) {
        Map<K, V> r0 = delegateOrNull();
        if (r0 != null) goto L5;
        int r22 = indexOf(r2);
        if (r22 != (-1)) goto L10;
        return null;
    L10:
        accessEntry(r22);
        return value(r22);
    L5:
        return r0.get(r2);
    }

    public int getSuccessor(int r1) {
        int r12 = r1 + 1;
        if (r12 >= this.size) goto L5;
        return r12;
    L5:
        return -1;
    }

    @Override // java.util.Map
    public int hashCode() {
        return entrySet().hashCode();
    }

    public void incrementModCount() {
        this.metadata += 32;
    }

    public void init(int r4) {
        if (r4 < 0) goto L5;
        boolean r1 = true;
    L6:
        Preconditions.checkArgument(r1, "Expected size must be >= 0");
        this.metadata = Ints.constrainToRange(r4, 1, 1073741823);
        return;
    L5:
        r1 = false;
        goto L6
    }

    public void insertEntry(int r2, K r3, V r4, int r5, int r6) {
        setEntry(r2, CompactHashing.maskCombine(r5, 0, r6));
        setKey(r2, r3);
        setValue(r2, r4);
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        if (size() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return new KeySetView(this, null);
    }

    public Iterator<K> keySetIterator() {
        Map<K, V> r0 = delegateOrNull();
        if (r0 == null) goto L7;
        return r0.keySet().iterator();
    L7:
        return new AnonymousClass1(this);
    }

    public void moveLastEntry(int r10, int r11) {
        Object r0 = requireTable();
        int[] r1 = requireEntries();
        Object[] r2 = requireKeys();
        Object[] r3 = requireValues();
        int r9 = size();
        int r4 = r9 - 1;
        if (r10 >= r4) goto L13;
        Object r7 = r2[r4];
        r2[r10] = r7;
        r3[r10] = r3[r4];
        r2[r4] = null;
        r3[r4] = null;
        r1[r10] = r1[r4];
        r1[r4] = 0;
        int r22 = Hashing.smearedHash(r7) & r11;
        int r32 = CompactHashing.tableGet(r0, r22);
        if (r32 != r9) goto L8;
        CompactHashing.tableSet(r0, r22, r10 + 1);
        return;
    L8:
        int r33 = r32 - 1;
        int r02 = r1[r33];
        int r23 = CompactHashing.getNext(r02, r11);
        if (r23 == r9) goto L10;
        r32 = r23;
        goto L8
    L10:
        r1[r33] = CompactHashing.maskCombine(r02, r10 + 1, r11);
        return;
    L13:
        r2[r10] = null;
        r3[r10] = null;
        r1[r10] = 0;
    }

    public boolean needsAllocArrays() {
        if (this.table != null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Map
    public V put(K r13, V r14) {
        if (needsAllocArrays() == false) goto L5;
        allocArrays();
    L5:
        Map<K, V> r0 = delegateOrNull();
        if (r0 != null) goto L8;
        int[] r02 = requireEntries();
        Object[] r1 = requireKeys();
        Object[] r2 = requireValues();
        int r4 = this.size;
        int r9 = r4 + 1;
        int r7 = Hashing.smearedHash(r13);
        int r3 = hashTableMask();
        int r5 = r7 & r3;
        int r6 = CompactHashing.tableGet(requireTable(), r5);
        if (r6 != 0) goto L15;
        if (r9 <= r3) goto L14;
        r3 = resizeTable(r3, CompactHashing.newCapacity(r3), r7, r4);
    L13:
        int r8 = r3;
        resizeMeMaybe(r9);
        insertEntry(r4, r13, r14, r7, r8);
        this.size = r9;
        incrementModCount();
        return null;
    L14:
        CompactHashing.tableSet(requireTable(), r5, r9);
        goto L13
    L15:
        int r52 = CompactHashing.getHashPrefix(r7, r3);
        int r82 = 0;
    L16:
        int r62 = r6 - 1;
        int r10 = r02[r62];
        if (CompactHashing.getHashPrefix(r10, r3) == r52) goto L19;
    L22:
        int r11 = CompactHashing.getNext(r10, r3);
        r82 = r82 + 1;
        if (r11 == 0) goto L25;
        r6 = r11;
        goto L16
    L25:
        if (r82 >= 9) goto L27;
        if (r9 <= r3) goto L30;
        r3 = resizeTable(r3, CompactHashing.newCapacity(r3), r7, r4);
        goto L13
    L30:
        r02[r62] = CompactHashing.maskCombine(r10, r9, r3);
        goto L13
    L27:
        return convertToHashFloodingResistantImplementation().put(r13, r14);
    L19:
        if (Objects.equals(r13, r1[r62]) == false) goto L22;
        V r132 = (V) r2[r62];
        r2[r62] = r14;
        accessEntry(r62);
        return r132;
    L8:
        return r0.put(r13, r14);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> r3) {
        Iterator<Map.Entry<? extends K, ? extends V>> r32 = r3.entrySet().iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        Map.Entry<? extends K, ? extends V> r0 = r32.next();
        put(r0.getKey(), r0.getValue());
        goto L4
    }

    @Override // java.util.Map
    public V remove(Object r2) {
        Map<K, V> r0 = delegateOrNull();
        if (r0 != null) goto L5;
        V r1 = (V) removeHelper(r2);
        if (r1 != NOT_FOUND) goto L10;
        return null;
    L10:
        return r1;
    L5:
        return r0.remove(r2);
    }

    public void resizeEntries(int r2) {
        this.entries = Arrays.copyOf(requireEntries(), r2);
        this.keys = Arrays.copyOf(requireKeys(), r2);
        this.values = Arrays.copyOf(requireValues(), r2);
    }

    @Override // java.util.Map
    public int size() {
        Map<K, V> r0 = delegateOrNull();
        if (r0 == null) goto L7;
        return r0.size();
    L7:
        return this.size;
    }

    public String toString() {
        return Maps.toStringImpl(this);
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return new ValuesView(this, null);
    }

    public Iterator<V> valuesIterator() {
        Map<K, V> r0 = delegateOrNull();
        if (r0 == null) goto L7;
        return r0.values().iterator();
    L7:
        return new AnonymousClass3(this);
    }
}
