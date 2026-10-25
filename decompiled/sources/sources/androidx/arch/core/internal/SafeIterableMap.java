package androidx.arch.core.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public class SafeIterableMap<K, V> implements Iterable<Map.Entry<K, V>> {
    private Entry<K, V> mEnd;
    private final WeakHashMap<SupportRemove<K, V>, Boolean> mIterators;
    private int mSize;
    Entry<K, V> mStart;

    /* loaded from: classes3.dex */
    public static class AscendingIterator<K, V> extends ListIterator<K, V> {
        public AscendingIterator(Entry<K, V> r1, Entry<K, V> r2) {
            super(r1, r2);
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        public Entry<K, V> backward(Entry<K, V> r1) {
            return r1.mPrevious;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        public Entry<K, V> forward(Entry<K, V> r1) {
            return r1.mNext;
        }
    }

    public static class Entry<K, V> implements Map.Entry<K, V> {
        final K mKey;
        Entry<K, V> mNext;
        Entry<K, V> mPrevious;
        final V mValue;

        public Entry(K r1, V r2) {
            this.mKey = r1;
            this.mValue = r2;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object r5) {
            if (r5 != this) goto L6;
            return true;
        L6:
            if ((r5 instanceof Entry) == true) goto L8;
            return false;
        L8:
            Entry r52 = (Entry) r5;
            if (this.mKey.equals(r52.mKey) == true) goto L11;
        L13:
            return false;
        L11:
            if (this.mValue.equals(r52.mValue) == false) goto L13;
            return true;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.mKey;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.mValue;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            int r0 = this.mKey.hashCode();
            return this.mValue.hashCode() ^ r0;
        }

        @Override // java.util.Map.Entry
        public V setValue(V r1) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.mKey + "=" + this.mValue;
        }
    }

    public class IteratorWithAdditions extends SupportRemove<K, V> implements Iterator<Map.Entry<K, V>> {
        private boolean mBeforeStart;
        private Entry<K, V> mCurrent;
        final /* synthetic */ SafeIterableMap this$0;

        public IteratorWithAdditions(SafeIterableMap r1) {
            this.this$0 = r1;
            this.mBeforeStart = true;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.mBeforeStart == true) goto L5;
            Entry<K, V> r3 = this.mCurrent;
            if (r3 != null) goto L11;
        L13:
            return false;
        L11:
            if (r3.mNext == null) goto L13;
            return true;
        L5:
            if (this.this$0.mStart == null) goto L7;
            return true;
        L7:
            return false;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ Object next() {
            return next();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            if (this.mBeforeStart == false) goto L5;
            this.mBeforeStart = false;
            this.mCurrent = this.this$0.mStart;
        L11:
            return this.mCurrent;
        L5:
            Entry<K, V> r0 = this.mCurrent;
            if (r0 == null) goto L8;
            Entry<K, V> r02 = r0.mNext;
        L9:
            this.mCurrent = r02;
            goto L11
        L8:
            r02 = null;
            goto L9
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.SupportRemove
        public void supportRemove(Entry<K, V> r2) {
            Entry<K, V> r0 = this.mCurrent;
            if (r2 != r0) goto L10;
            Entry<K, V> r22 = r0.mPrevious;
            this.mCurrent = r22;
            if (r22 != null) goto L7;
            boolean r23 = true;
        L8:
            this.mBeforeStart = r23;
            return;
        L7:
            r23 = false;
            goto L8
        }
    }

    public static abstract class ListIterator<K, V> extends SupportRemove<K, V> implements Iterator<Map.Entry<K, V>> {
        Entry<K, V> mExpectedEnd;
        Entry<K, V> mNext;

        public ListIterator(Entry<K, V> r1, Entry<K, V> r2) {
            this.mExpectedEnd = r2;
            this.mNext = r1;
        }

        private Entry<K, V> nextNode() {
            Entry<K, V> r0 = this.mNext;
            Entry<K, V> r1 = this.mExpectedEnd;
            if (r0 == r1) goto L8;
            if (r1 != null) goto L7;
            return null;
        L7:
            return forward(r0);
        L8:
            return null;
        }

        public abstract Entry<K, V> backward(Entry<K, V> r1);

        public abstract Entry<K, V> forward(Entry<K, V> r1);

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.mNext == null) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ Object next() {
            return next();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            Entry<K, V> r0 = this.mNext;
            this.mNext = nextNode();
            return r0;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.SupportRemove
        public void supportRemove(Entry<K, V> r2) {
            if (this.mExpectedEnd == r2) goto L5;
        L7:
            Entry<K, V> r0 = this.mExpectedEnd;
            if (r0 != r2) goto L11;
            this.mExpectedEnd = backward(r0);
        L11:
            if (this.mNext != r2) goto L14;
            this.mNext = nextNode();
            return;
        L14:
            return;
        L5:
            if (r2 != this.mNext) goto L7;
            this.mNext = null;
            this.mExpectedEnd = null;
            goto L7
        }
    }

    public static abstract class SupportRemove<K, V> {
        public SupportRemove() {
        }

        public abstract void supportRemove(Entry<K, V> r1);
    }

    public SafeIterableMap() {
        this.mIterators = new WeakHashMap();
        this.mSize = 0;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof SafeIterableMap) == true) goto L8;
        return false;
    L8:
        SafeIterableMap r52 = (SafeIterableMap) r5;
        if (size() == r52.size()) goto L11;
        return false;
    L11:
        Iterator<Map.Entry<K, V>> r4 = iterator();
        Iterator<Map.Entry<K, V>> r53 = r52.iterator();
    L13:
        if (r4.hasNext() == false) goto L24;
        if (r53.hasNext() == false) goto L24;
        Map.Entry<K, V> r1 = r4.next();
        Map.Entry<K, V> r3 = r53.next();
        if (r1 != null) goto L19;
        if (r3 == null) goto L19;
    L22:
        return false;
    L19:
        if (r1 == null) goto L13;
        if (r1.equals(r3) == true) goto L13;
    L24:
        if (r4.hasNext() == false) goto L26;
    L28:
        return false;
    L26:
        if (r53.hasNext() == true) goto L28;
        return true;
    }

    public Entry<K, V> get(K r2) {
        Entry<K, V> r1 = this.mStart;
    L3:
        if (r1 == null) goto L8;
        if (r1.mKey.equals(r2) == true) goto L8;
        r1 = r1.mNext;
    L8:
        return r1;
    }

    public int hashCode() {
        Iterator<Map.Entry<K, V>> r2 = iterator();
        int r0 = 0;
    L4:
        if (r2.hasNext() == false) goto L6;
        r0 = r0 + r2.next().hashCode();
        goto L4
    L6:
        return r0;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        AscendingIterator r0 = new AscendingIterator(this.mStart, this.mEnd);
        this.mIterators.put(r0, Boolean.FALSE);
        return r0;
    }

    public SafeIterableMap<K, V>.IteratorWithAdditions iteratorWithAdditions() {
        SafeIterableMap<K, V>.IteratorWithAdditions r0 = new IteratorWithAdditions(this);
        this.mIterators.put(r0, Boolean.FALSE);
        return r0;
    }

    public Entry<K, V> put(K r2, V r3) {
        Entry<K, V> r0 = new Entry(r2, r3);
        this.mSize++;
        Entry<K, V> r22 = this.mEnd;
        if (r22 != null) goto L6;
        this.mStart = r0;
        this.mEnd = r0;
        return r0;
    L6:
        r22.mNext = r0;
        r0.mPrevious = r22;
        this.mEnd = r0;
        return r0;
    }

    public V putIfAbsent(K r2, V r3) {
        Entry<K, V> r0 = get(r2);
        if (r0 != null) goto L5;
        put(r2, r3);
        return null;
    L5:
        return r0.mValue;
    }

    public V remove(K r4) {
        Entry<K, V> r42 = get(r4);
        if (r42 != null) goto L5;
        return null;
    L5:
        this.mSize--;
        if (this.mIterators.isEmpty() == true) goto L11;
        Iterator<SupportRemove<K, V>> r1 = this.mIterators.keySet().iterator();
    L9:
        if (r1.hasNext() == false) goto L11;
        r1.next().supportRemove(r42);
    L11:
        Entry<K, V> r12 = r42.mPrevious;
        Entry<K, V> r2 = r42.mNext;
        if (r12 == null) goto L14;
        r12.mNext = r2;
    L15:
        Entry<K, V> r22 = r42.mNext;
        if (r22 == null) goto L18;
        r22.mPrevious = r12;
    L19:
        r42.mNext = null;
        r42.mPrevious = null;
        return r42.mValue;
    L18:
        this.mEnd = r12;
        goto L19
    L14:
        this.mStart = r2;
        goto L15
    }

    public int size() {
        return this.mSize;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("[");
        Iterator<Map.Entry<K, V>> r2 = iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        r0.append(r2.next().toString());
        if (r2.hasNext() == false) goto L4;
        r0.append(", ");
        goto L4
    L8:
        r0.append("]");
        return r0.toString();
    }
}
