package androidx.datastore.preferences.protobuf;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.datastore.preferences.protobuf.FieldSet;
import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
abstract class SmallSortedMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private List<SmallSortedMap<K, V>.Entry> entryList;
    private boolean isImmutable;
    private volatile SmallSortedMap<K, V>.DescendingEntrySet lazyDescendingEntrySet;
    private volatile SmallSortedMap<K, V>.EntrySet lazyEntrySet;
    private Map<K, V> overflowEntries;
    private Map<K, V> overflowEntriesDescending;

    /* JADX INFO: Add missing generic type declarations: [FieldDescriptorType] */
    /* renamed from: androidx.datastore.preferences.protobuf.SmallSortedMap$1, reason: invalid class name */
    public class AnonymousClass1<FieldDescriptorType> extends SmallSortedMap<FieldDescriptorType, Object> {
        public AnonymousClass1() {
            super(null);
        }

        @Override // androidx.datastore.preferences.protobuf.SmallSortedMap
        public void makeImmutable() {
            if (isImmutable() == false) goto L5;
        L13:
            super.makeImmutable();
            return;
        L5:
            if (getNumArrayEntries() > 0) goto L11;
            Iterator<Map.Entry<FieldDescriptorType, Object>> r0 = getOverflowEntries().iterator();
            if (r0.hasNext() == false) goto L13;
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.next().getKey());
            throw null;
        L11:
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(getArrayEntryAt(0).getKey());
            throw null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public /* bridge */ /* synthetic */ Object put(Object r1, Object r2) {
            return super.put((Comparable) r1, r2);
        }
    }

    /* loaded from: classes3.dex */
    public class DescendingEntryIterator implements Iterator<Map.Entry<K, V>> {
        private Iterator<Map.Entry<K, V>> lazyOverflowIterator;
        private int pos;
        final /* synthetic */ SmallSortedMap this$0;

        private DescendingEntryIterator(SmallSortedMap r1) {
            this.this$0 = r1;
            this.pos = SmallSortedMap.access$600(r1).size();
        }

        public /* synthetic */ DescendingEntryIterator(SmallSortedMap r1, AnonymousClass1 r2) {
            this(r1);
        }

        private Iterator<Map.Entry<K, V>> getOverflowIterator() {
            if (this.lazyOverflowIterator != null) goto L6;
            this.lazyOverflowIterator = SmallSortedMap.access$900(this.this$0).entrySet().iterator();
        L6:
            return this.lazyOverflowIterator;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            int r0 = this.pos;
            if (r0 <= 0) goto L7;
            if (r0 > SmallSortedMap.access$600(this.this$0).size()) goto L7;
            return true;
        L7:
            if (getOverflowIterator().hasNext() == true) goto L12;
            return false;
        L12:
            return true;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ Object next() {
            return next();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            if (getOverflowIterator().hasNext() == true) goto L5;
            List r0 = SmallSortedMap.access$600(this.this$0);
            int r1 = this.pos - 1;
            this.pos = r1;
            return (Map.Entry) r0.get(r1);
        L5:
            return getOverflowIterator().next();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes3.dex */
    public class DescendingEntrySet extends SmallSortedMap<K, V>.EntrySet {
        final /* synthetic */ SmallSortedMap this$0;

        private DescendingEntrySet(SmallSortedMap r2) {
            this.this$0 = r2;
            super(r2, null);
        }

        public /* synthetic */ DescendingEntrySet(SmallSortedMap r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.datastore.preferences.protobuf.SmallSortedMap.EntrySet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new DescendingEntryIterator(this.this$0, null);
        }
    }

    /* loaded from: classes3.dex */
    public class Entry implements Map.Entry<K, V>, Comparable<SmallSortedMap<K, V>.Entry> {
        private final K key;
        final /* synthetic */ SmallSortedMap this$0;
        private V value;

        public Entry(SmallSortedMap r1, K r2, V r3) {
            this.this$0 = r1;
            this.key = r2;
            this.value = r3;
        }

        public Entry(SmallSortedMap r2, Map.Entry<K, V> r3) {
            this(r2, r3.getKey(), r3.getValue());
        }

        private boolean equals(Object r1, Object r2) {
            if (r1 != null) goto L9;
            if (r2 != null) goto L6;
            return true;
        L6:
            return false;
        L9:
            return r1.equals(r2);
        }

        public int compareTo(SmallSortedMap<K, V>.Entry r1) {
            return getKey().compareTo(r1.getKey());
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Object r1) {
            return compareTo((Entry) r1);
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object r5) {
            if (r5 != this) goto L6;
            return true;
        L6:
            if ((r5 instanceof Map.Entry) == true) goto L8;
            return false;
        L8:
            Map.Entry r52 = (Map.Entry) r5;
            if (equals(this.key, r52.getKey()) == true) goto L11;
        L13:
            return false;
        L11:
            if (equals(this.value, r52.getValue()) == false) goto L13;
            return true;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public /* bridge */ /* synthetic */ Object getKey() {
            return getKey();
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K r0 = this.key;
            int r1 = 0;
            if (r0 != null) goto L5;
            int r02 = 0;
        L6:
            V r2 = this.value;
            if (r2 == null) goto L11;
            r1 = r2.hashCode();
        L11:
            return r02 ^ r1;
        L5:
            r02 = r0.hashCode();
            goto L6
        }

        @Override // java.util.Map.Entry
        public V setValue(V r2) {
            SmallSortedMap.access$300(this.this$0);
            V r0 = this.value;
            this.value = r2;
            return r0;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    /* loaded from: classes3.dex */
    public class EntryIterator implements Iterator<Map.Entry<K, V>> {
        private Iterator<Map.Entry<K, V>> lazyOverflowIterator;
        private boolean nextCalledBeforeRemove;
        private int pos;
        final /* synthetic */ SmallSortedMap this$0;

        private EntryIterator(SmallSortedMap r1) {
            this.this$0 = r1;
            this.pos = -1;
        }

        public /* synthetic */ EntryIterator(SmallSortedMap r1, AnonymousClass1 r2) {
            this(r1);
        }

        private Iterator<Map.Entry<K, V>> getOverflowIterator() {
            if (this.lazyOverflowIterator != null) goto L6;
            this.lazyOverflowIterator = SmallSortedMap.access$700(this.this$0).entrySet().iterator();
        L6:
            return this.lazyOverflowIterator;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if ((this.pos + 1) >= SmallSortedMap.access$600(this.this$0).size()) goto L5;
        L11:
            return true;
        L5:
            if (SmallSortedMap.access$700(this.this$0).isEmpty() == false) goto L7;
            return false;
        L7:
            if (getOverflowIterator().hasNext() == true) goto L11;
            return false;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ Object next() {
            return next();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            this.nextCalledBeforeRemove = true;
            int r1 = this.pos + 1;
            this.pos = r1;
            if (r1 >= SmallSortedMap.access$600(this.this$0).size()) goto L7;
            return (Map.Entry) SmallSortedMap.access$600(this.this$0).get(this.pos);
        L7:
            return getOverflowIterator().next();
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.nextCalledBeforeRemove == false) goto L10;
            this.nextCalledBeforeRemove = false;
            SmallSortedMap.access$300(this.this$0);
            if (this.pos >= SmallSortedMap.access$600(this.this$0).size()) goto L8;
            SmallSortedMap r0 = this.this$0;
            int r1 = this.pos;
            this.pos = r1 - 1;
            SmallSortedMap.access$800(r0, r1);
            return;
        L8:
            getOverflowIterator().remove();
            return;
        L10:
            Segment$$ExternalSyntheticBUOutline1.m("remove() was called before next()");
        }
    }

    /* loaded from: classes3.dex */
    public class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        final /* synthetic */ SmallSortedMap this$0;

        private EntrySet(SmallSortedMap r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ EntrySet(SmallSortedMap r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public /* bridge */ /* synthetic */ boolean add(Object r1) {
            return add((Map.Entry) r1);
        }

        public boolean add(Map.Entry<K, V> r2) {
            if (contains(r2) == true) goto L6;
            this.this$0.put(r2.getKey(), r2.getValue());
            return true;
        L6:
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.this$0.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object r2) {
            Map.Entry r22 = (Map.Entry) r2;
            Object r1 = this.this$0.get(r22.getKey());
            Object r23 = r22.getValue();
            if (r1 == r23) goto L10;
            if (r1 != null) goto L6;
            return false;
        L6:
            if (r1.equals(r23) == true) goto L13;
            return false;
        L13:
            return true;
        L10:
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator(this.this$0, null);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object r2) {
            Map.Entry r22 = (Map.Entry) r2;
            if (contains(r22) == false) goto L6;
            this.this$0.remove(r22.getKey());
            return true;
        L6:
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.this$0.size();
        }
    }

    private SmallSortedMap() {
        this.entryList = Collections.EMPTY_LIST;
        Map<K, V> r0 = Collections.EMPTY_MAP;
        this.overflowEntries = r0;
        this.overflowEntriesDescending = r0;
    }

    public /* synthetic */ SmallSortedMap(AnonymousClass1 r1) {
        this();
    }

    public static /* synthetic */ void access$300(SmallSortedMap r0) {
        r0.checkMutable();
    }

    public static /* synthetic */ List access$600(SmallSortedMap r0) {
        return r0.entryList;
    }

    public static /* synthetic */ Map access$700(SmallSortedMap r0) {
        return r0.overflowEntries;
    }

    public static /* synthetic */ Object access$800(SmallSortedMap r0, int r1) {
        return r0.removeArrayEntryAt(r1);
    }

    public static /* synthetic */ Map access$900(SmallSortedMap r0) {
        return r0.overflowEntriesDescending;
    }

    private int binarySearchInArray(K r5) {
        int r0 = this.entryList.size();
        int r1 = r0 - 1;
        if (r1 < 0) goto L11;
        int r2 = r5.compareTo(this.entryList.get(r1).getKey());
        if (r2 <= 0) goto L9;
        int r02 = r0 + 1;
    L8:
        return -r02;
    L9:
        if (r2 != 0) goto L11;
        return r1;
    L11:
        int r03 = 0;
    L12:
        if (r03 > r1) goto L19;
        int r22 = (r03 + r1) / 2;
        int r3 = r5.compareTo(this.entryList.get(r22).getKey());
        if (r3 < 0) goto L15;
        if (r3 <= 0) goto L18;
        r03 = r22 + 1;
        goto L12
    L18:
        return r22;
    L15:
        r1 = r22 - 1;
        goto L12
    L19:
        r02 = r03 + 1;
        goto L8
    }

    private void checkMutable() {
        if (this.isImmutable == true) goto L5;
        return;
    L5:
        Type$$ExternalSyntheticBUOutline0.m();
    }

    private void ensureEntryArrayMutable() {
        checkMutable();
        if (this.entryList.isEmpty() == true) goto L5;
        return;
    L5:
        if ((this.entryList instanceof ArrayList) == true) goto L9;
        this.entryList = new ArrayList(16);
        return;
    }

    private SortedMap<K, V> getOverflowEntriesMutable() {
        checkMutable();
        if (this.overflowEntries.isEmpty() == false) goto L8;
        if ((this.overflowEntries instanceof TreeMap) == true) goto L8;
        TreeMap r0 = new TreeMap();
        this.overflowEntries = r0;
        this.overflowEntriesDescending = r0.descendingMap();
    L8:
        return (SortedMap) this.overflowEntries;
    }

    public static <FieldDescriptorType extends FieldSet.FieldDescriptorLite<FieldDescriptorType>> SmallSortedMap<FieldDescriptorType, Object> newFieldMap() {
        return new AnonymousClass1();
    }

    private V removeArrayEntryAt(int r5) {
        checkMutable();
        V r52 = this.entryList.remove(r5).getValue();
        if (this.overflowEntries.isEmpty() == true) goto L5;
        Iterator<Map.Entry<K, V>> r0 = getOverflowEntriesMutable().entrySet().iterator();
        this.entryList.add(new Entry(this, r0.next()));
        r0.remove();
    L5:
        return r52;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        checkMutable();
        if (this.entryList.isEmpty() == true) goto L6;
        this.entryList.clear();
    L6:
        if (this.overflowEntries.isEmpty() == true) goto L9;
        this.overflowEntries.clear();
        return;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object r2) {
        Comparable r22 = (Comparable) r2;
        if (binarySearchInArray(r22) < 0) goto L5;
        return true;
    L5:
        if (this.overflowEntries.containsKey(r22) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public Set<Map.Entry<K, V>> descendingEntrySet() {
        if (this.lazyDescendingEntrySet != null) goto L6;
        this.lazyDescendingEntrySet = new DescendingEntrySet(this, null);
    L6:
        return this.lazyDescendingEntrySet;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.lazyEntrySet != null) goto L6;
        this.lazyEntrySet = new EntrySet(this, null);
    L6:
        return this.lazyEntrySet;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof SmallSortedMap) == false) goto L8;
        SmallSortedMap r82 = (SmallSortedMap) r8;
        int r1 = size();
        if (r1 == r82.size()) goto L12;
        return false;
    L12:
        int r2 = getNumArrayEntries();
        if (r2 != r82.getNumArrayEntries()) goto L15;
        int r4 = 0;
    L17:
        if (r4 >= r2) goto L22;
        if (getArrayEntryAt(r4).equals(r82.getArrayEntryAt(r4)) == false) goto L20;
        r4 = r4 + 1;
        goto L17
    L20:
        return false;
    L22:
        if (r2 != r1) goto L24;
        return true;
    L24:
        return this.overflowEntries.equals(r82.overflowEntries);
    L15:
        return entrySet().equals(r82.entrySet());
    L8:
        return super.equals(r8);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object r2) {
        Comparable r22 = (Comparable) r2;
        int r0 = binarySearchInArray(r22);
        if (r0 < 0) goto L7;
        return this.entryList.get(r0).getValue();
    L7:
        return this.overflowEntries.get(r22);
    }

    public Map.Entry<K, V> getArrayEntryAt(int r1) {
        return this.entryList.get(r1);
    }

    public int getNumArrayEntries() {
        return this.entryList.size();
    }

    public int getNumOverflowEntries() {
        return this.overflowEntries.size();
    }

    public Iterable<Map.Entry<K, V>> getOverflowEntries() {
        if (this.overflowEntries.isEmpty() == false) goto L7;
        return Collections.EMPTY_SET;
    L7:
        return this.overflowEntries.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int r0 = getNumArrayEntries();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L6;
        r2 = r2 + this.entryList.get(r1).hashCode();
        r1 = r1 + 1;
        goto L3
    L6:
        if (getNumOverflowEntries() > 0) goto L8;
        return r2;
    L8:
        return r2 + this.overflowEntries.hashCode();
    }

    public boolean isImmutable() {
        return this.isImmutable;
    }

    public void makeImmutable() {
        if (this.isImmutable == false) goto L5;
        return;
    L5:
        if (this.overflowEntries.isEmpty() == false) goto L7;
        Map<K, V> r0 = Collections.EMPTY_MAP;
    L8:
        this.overflowEntries = r0;
        if (this.overflowEntriesDescending.isEmpty() == false) goto L11;
        Map<K, V> r02 = Collections.EMPTY_MAP;
    L12:
        this.overflowEntriesDescending = r02;
        this.isImmutable = true;
        return;
    L11:
        r02 = Collections.unmodifiableMap(this.overflowEntriesDescending);
        goto L12
    L7:
        r0 = Collections.unmodifiableMap(this.overflowEntries);
        goto L8
    }

    public V put(K r5, V r6) {
        checkMutable();
        int r0 = binarySearchInArray(r5);
        if (r0 >= 0) goto L5;
        ensureEntryArrayMutable();
        int r02 = -(r0 + 1);
        if (r02 < 16) goto L11;
        return getOverflowEntriesMutable().put(r5, r6);
    L11:
        if (this.entryList.size() != 16) goto L13;
        SmallSortedMap<K, V>.Entry r1 = this.entryList.remove(15);
        getOverflowEntriesMutable().put(r1.getKey(), r1.getValue());
    L13:
        this.entryList.add(r02, new Entry(this, r5, r6));
        return null;
    L5:
        return this.entryList.get(r0).setValue(r6);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object r2) {
        checkMutable();
        Comparable r22 = (Comparable) r2;
        int r0 = binarySearchInArray(r22);
        if (r0 < 0) goto L7;
        return removeArrayEntryAt(r0);
    L7:
        if (this.overflowEntries.isEmpty() == false) goto L11;
        return null;
    L11:
        return this.overflowEntries.remove(r22);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.entryList.size() + this.overflowEntries.size();
    }
}
