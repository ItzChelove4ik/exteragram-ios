package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public class ArrayMap<K, V> extends SimpleArrayMap<K, V> implements Map<K, V> {
    ArrayMap<K, V>.EntrySet mEntrySet;
    ArrayMap<K, V>.KeySet mKeySet;
    ArrayMap<K, V>.ValueCollection mValues;

    public final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        final /* synthetic */ ArrayMap this$0;

        public EntrySet(ArrayMap r1) {
            this.this$0 = r1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new MapIterator(this.this$0);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.this$0.size();
        }
    }

    /* loaded from: classes3.dex */
    public final class KeyIterator extends IndexBasedArrayIterator<K> {
        final /* synthetic */ ArrayMap this$0;

        public KeyIterator(ArrayMap r1) {
            this.this$0 = r1;
            super(r1.size());
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        public K elementAt(int r1) {
            return this.this$0.keyAt(r1);
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        public void removeAt(int r1) {
            this.this$0.removeAt(r1);
        }
    }

    /* loaded from: classes3.dex */
    public final class KeySet implements Set<K> {
        final /* synthetic */ ArrayMap this$0;

        public KeySet(ArrayMap r1) {
            this.this$0 = r1;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean add(K r1) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection<? extends K> r1) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            this.this$0.clear();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object r1) {
            return this.this$0.containsKey(r1);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection<?> r1) {
            return this.this$0.containsAll(r1);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean equals(Object r1) {
            return ArrayMap.equalsSetHelper(this, r1);
        }

        @Override // java.util.Set, java.util.Collection
        public int hashCode() {
            int r0 = this.this$0.size() - 1;
            int r2 = 0;
        L3:
            if (r0 < 0) goto L9;
            K r3 = this.this$0.keyAt(r0);
            if (r3 != null) goto L7;
            int r32 = 0;
        L8:
            r2 = r2 + r32;
            r0 = r0 - 1;
            goto L3
        L7:
            r32 = r3.hashCode();
            goto L8
        L9:
            return r2;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return this.this$0.isEmpty();
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator<K> iterator() {
            return new KeyIterator(this.this$0);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object r2) {
            int r22 = this.this$0.indexOfKey(r2);
            if (r22 < 0) goto L6;
            this.this$0.removeAt(r22);
            return true;
        L6:
            return false;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection<?> r1) {
            return this.this$0.removeAll(r1);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection<?> r1) {
            return this.this$0.retainAll(r1);
        }

        @Override // java.util.Set, java.util.Collection
        public int size() {
            return this.this$0.size();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            int r0 = this.this$0.size();
            Object[] r1 = new Object[r0];
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L5;
            r1[r2] = this.this$0.keyAt(r2);
            r2 = r2 + 1;
            goto L3
        L5:
            return r1;
        }

        @Override // java.util.Set, java.util.Collection
        public <T> T[] toArray(T[] r4) {
            int r0 = size();
            if (r4.length >= r0) goto L5;
            r4 = (T[]) ((Object[]) Array.newInstance(r4.getClass().getComponentType(), r0));
        L5:
            int r1 = 0;
        L6:
            if (r1 >= r0) goto L9;
            T[] r42 = r4;
            r42[r1] = this.this$0.keyAt(r1);
            r1 = r1 + 1;
            goto L6
        L9:
            if (r4.length <= r0) goto L11;
            r4[r0] = null;
        L11:
            return r4;
        }
    }

    public final class MapIterator implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {
        int mEnd;
        boolean mEntryValid;
        int mIndex;
        final /* synthetic */ ArrayMap this$0;

        public MapIterator(ArrayMap r1) {
            this.this$0 = r1;
            this.mEnd = r1.size() - 1;
            this.mIndex = -1;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object r5) {
            if (this.mEntryValid == true) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("This container does not support retaining Map.Entry objects");
            return false;
        L5:
            if ((r5 instanceof Map.Entry) == true) goto L7;
            return false;
        L7:
            Map.Entry r52 = (Map.Entry) r5;
            if (ContainerHelpersKt.equal(r52.getKey(), this.this$0.keyAt(this.mIndex)) == true) goto L10;
        L13:
            return false;
        L10:
            if (ContainerHelpersKt.equal(r52.getValue(), this.this$0.valueAt(this.mIndex)) == false) goto L13;
            return true;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            if (this.mEntryValid == true) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("This container does not support retaining Map.Entry objects");
            return null;
        L5:
            return this.this$0.keyAt(this.mIndex);
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            if (this.mEntryValid == true) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("This container does not support retaining Map.Entry objects");
            return null;
        L5:
            return this.this$0.valueAt(this.mIndex);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.mIndex >= this.mEnd) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            int r1 = 0;
            if (this.mEntryValid == false) goto L13;
            K r0 = this.this$0.keyAt(this.mIndex);
            V r3 = this.this$0.valueAt(this.mIndex);
            if (r0 != null) goto L7;
            int r02 = 0;
        L8:
            if (r3 == null) goto L12;
            r1 = r3.hashCode();
        L12:
            return r02 ^ r1;
        L7:
            r02 = r0.hashCode();
            goto L8
        L13:
            Segment$$ExternalSyntheticBUOutline1.m("This container does not support retaining Map.Entry objects");
            return 0;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ Object next() {
            return next();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            if (hasNext() == false) goto L6;
            this.mIndex++;
            this.mEntryValid = true;
            return this;
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.mEntryValid == false) goto L6;
            this.this$0.removeAt(this.mIndex);
            this.mIndex--;
            this.mEnd--;
            this.mEntryValid = false;
            return;
        L6:
            MethodWriter$$ExternalSyntheticBUOutline0.m();
        }

        @Override // java.util.Map.Entry
        public V setValue(V r2) {
            if (this.mEntryValid == true) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("This container does not support retaining Map.Entry objects");
            return null;
        L5:
            return this.this$0.setValueAt(this.mIndex, r2);
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    public final class ValueCollection implements Collection<V> {
        final /* synthetic */ ArrayMap this$0;

        public ValueCollection(ArrayMap r1) {
            this.this$0 = r1;
        }

        @Override // java.util.Collection
        public boolean add(V r1) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends V> r1) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public void clear() {
            this.this$0.clear();
        }

        @Override // java.util.Collection
        public boolean contains(Object r1) {
            if (this.this$0.__restricted$indexOfValue(r1) < 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection<?> r2) {
            Iterator<?> r22 = r2.iterator();
        L4:
            if (r22.hasNext() == false) goto L9;
            if (contains(r22.next()) == true) goto L4;
            return false;
        L9:
            return true;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            return this.this$0.isEmpty();
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new ValueIterator(this.this$0);
        }

        @Override // java.util.Collection
        public boolean remove(Object r2) {
            int r22 = this.this$0.__restricted$indexOfValue(r2);
            if (r22 < 0) goto L6;
            this.this$0.removeAt(r22);
            return true;
        L6:
            return false;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection<?> r6) {
            int r0 = this.this$0.size();
            int r1 = 0;
            boolean r2 = false;
        L3:
            if (r1 >= r0) goto L8;
            if (r6.contains(this.this$0.valueAt(r1)) == false) goto L7;
            this.this$0.removeAt(r1);
            r1 = r1 - 1;
            r0 = r0 - 1;
            r2 = true;
        L7:
            r1 = r1 + 1;
            goto L3
        L8:
            return r2;
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection<?> r6) {
            int r0 = this.this$0.size();
            int r1 = 0;
            boolean r2 = false;
        L3:
            if (r1 >= r0) goto L8;
            if (r6.contains(this.this$0.valueAt(r1)) == true) goto L7;
            this.this$0.removeAt(r1);
            r1 = r1 - 1;
            r0 = r0 - 1;
            r2 = true;
        L7:
            r1 = r1 + 1;
            goto L3
        L8:
            return r2;
        }

        @Override // java.util.Collection
        public int size() {
            return this.this$0.size();
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            int r0 = this.this$0.size();
            Object[] r1 = new Object[r0];
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L5;
            r1[r2] = this.this$0.valueAt(r2);
            r2 = r2 + 1;
            goto L3
        L5:
            return r1;
        }

        @Override // java.util.Collection
        public <T> T[] toArray(T[] r4) {
            int r0 = size();
            if (r4.length >= r0) goto L5;
            r4 = (T[]) ((Object[]) Array.newInstance(r4.getClass().getComponentType(), r0));
        L5:
            int r1 = 0;
        L6:
            if (r1 >= r0) goto L9;
            T[] r42 = r4;
            r42[r1] = this.this$0.valueAt(r1);
            r1 = r1 + 1;
            goto L6
        L9:
            if (r4.length <= r0) goto L11;
            r4[r0] = null;
        L11:
            return r4;
        }
    }

    public final class ValueIterator extends IndexBasedArrayIterator<V> {
        final /* synthetic */ ArrayMap this$0;

        public ValueIterator(ArrayMap r1) {
            this.this$0 = r1;
            super(r1.size());
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        public V elementAt(int r1) {
            return this.this$0.valueAt(r1);
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        public void removeAt(int r1) {
            this.this$0.removeAt(r1);
        }
    }

    public ArrayMap() {
    }

    public ArrayMap(int r1) {
        super(r1);
    }

    public ArrayMap(SimpleArrayMap r1) {
        super(r1);
    }

    public static <T> boolean equalsSetHelper(Set<T> r4, Object r5) {
        if (r4 != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Set) == false) goto L13;
        Set r52 = (Set) r5;
        if (r4.size() != r52.size()) goto L13;
        if (r4.containsAll(r52) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean containsAll(Collection<?> r2) {
        Iterator<?> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L9;
        if (containsKey(r22.next()) == true) goto L4;
        return false;
    L9:
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.collection.SimpleArrayMap, java.util.Map
    public boolean containsKey(Object r1) {
        return super.containsKey(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.collection.SimpleArrayMap, java.util.Map
    public boolean containsValue(Object r1) {
        return super.containsValue(r1);
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        ArrayMap<K, V>.EntrySet r0 = this.mEntrySet;
        if (r0 != null) goto L6;
        ArrayMap<K, V>.EntrySet r02 = new EntrySet(this);
        this.mEntrySet = r02;
        return r02;
    L6:
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.collection.SimpleArrayMap, java.util.Map
    public V get(Object r1) {
        return (V) super.get(r1);
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        ArrayMap<K, V>.KeySet r0 = this.mKeySet;
        if (r0 != null) goto L6;
        ArrayMap<K, V>.KeySet r02 = new KeySet(this);
        this.mKeySet = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> r3) {
        ensureCapacity(size() + r3.size());
        Iterator<Map.Entry<? extends K, ? extends V>> r32 = r3.entrySet().iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        Map.Entry<? extends K, ? extends V> r0 = r32.next();
        put(r0.getKey(), r0.getValue());
        goto L4
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.collection.SimpleArrayMap, java.util.Map
    public V remove(Object r1) {
        return (V) super.remove(r1);
    }

    public boolean removeAll(Collection<?> r3) {
        int r0 = size();
        Iterator<?> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L7;
        remove(r32.next());
        goto L4
    L7:
        if (r0 == size()) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean retainAll(Collection<?> r5) {
        int r0 = size();
        int r1 = size() - 1;
    L3:
        if (r1 < 0) goto L9;
        if (r5.contains(keyAt(r1)) == true) goto L7;
        removeAt(r1);
    L7:
        r1 = r1 - 1;
        goto L3
    L9:
        if (r0 == size()) goto L11;
        return true;
    L11:
        return false;
    }

    @Override // java.util.Map
    public Collection<V> values() {
        ArrayMap<K, V>.ValueCollection r0 = this.mValues;
        if (r0 != null) goto L6;
        ArrayMap<K, V>.ValueCollection r02 = new ValueCollection(this);
        this.mValues = r02;
        return r02;
    L6:
        return r0;
    }
}
