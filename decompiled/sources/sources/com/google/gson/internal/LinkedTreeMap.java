package com.google.gson.internal;

import androidx.collection.ArraySet$$ExternalSyntheticBUOutline0;
import c.f$$ExternalSyntheticBUOutline2;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    private static final Comparator<Comparable> NATURAL_ORDER = null;
    private final boolean allowNullValues;
    private final Comparator<? super K> comparator;
    private LinkedTreeMap<K, V>.EntrySet entrySet;
    final Node<K, V> header;
    private LinkedTreeMap<K, V>.KeySet keySet;
    int modCount;
    Node<K, V> root;
    int size;

    /* renamed from: com.google.gson.internal.LinkedTreeMap$1, reason: invalid class name */
    public class AnonymousClass1 implements Comparator<Comparable> {
        public AnonymousClass1() {
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(Comparable r1, Comparable r2) {
            return r1.compareTo(r2);
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Comparable r1, Comparable r2) {
            return compare2(r1, r2);
        }
    }

    /* loaded from: classes5.dex */
    public class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        final /* synthetic */ LinkedTreeMap this$0;

        /* renamed from: com.google.gson.internal.LinkedTreeMap$EntrySet$1, reason: invalid class name */
        public class AnonymousClass1 extends LinkedTreeMap<K, V>.LinkedTreeMapIterator<Map.Entry<K, V>> {
            final /* synthetic */ EntrySet this$1;

            public AnonymousClass1(EntrySet r1) {
                this.this$1 = r1;
                super(r1.this$0);
            }

            @Override // java.util.Iterator
            public /* bridge */ /* synthetic */ Object next() {
                return next();
            }

            @Override // java.util.Iterator
            public Map.Entry<K, V> next() {
                return nextNode();
            }
        }

        public EntrySet(LinkedTreeMap r1) {
            this.this$0 = r1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.this$0.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object r2) {
            if ((r2 instanceof Map.Entry) == true) goto L5;
            return false;
        L5:
            if (this.this$0.findByEntry((Map.Entry) r2) == null) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new AnonymousClass1(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object r3) {
            if ((r3 instanceof Map.Entry) == true) goto L5;
            return false;
        L5:
            Node<K, V> r32 = this.this$0.findByEntry((Map.Entry) r3);
            if (r32 != null) goto L8;
            return false;
        L8:
            this.this$0.removeInternal(r32, true);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.this$0.size;
        }
    }

    /* loaded from: classes5.dex */
    public final class KeySet extends AbstractSet<K> {
        final /* synthetic */ LinkedTreeMap this$0;

        /* renamed from: com.google.gson.internal.LinkedTreeMap$KeySet$1, reason: invalid class name */
        public class AnonymousClass1 extends LinkedTreeMap<K, V>.LinkedTreeMapIterator<K> {
            final /* synthetic */ KeySet this$1;

            public AnonymousClass1(KeySet r1) {
                this.this$1 = r1;
                super(r1.this$0);
            }

            @Override // java.util.Iterator
            public K next() {
                return nextNode().key;
            }
        }

        public KeySet(LinkedTreeMap r1) {
            this.this$0 = r1;
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
            return new AnonymousClass1(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object r1) {
            if (this.this$0.removeInternalByKey(r1) == null) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.this$0.size;
        }
    }

    /* loaded from: classes5.dex */
    public abstract class LinkedTreeMapIterator<T> implements Iterator<T> {
        int expectedModCount;
        Node<K, V> lastReturned;
        Node<K, V> next;
        final /* synthetic */ LinkedTreeMap this$0;

        public LinkedTreeMapIterator(LinkedTreeMap r2) {
            this.this$0 = r2;
            this.next = r2.header.next;
            this.lastReturned = null;
            this.expectedModCount = r2.modCount;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.next == this.this$0.header) goto L6;
            return true;
        L6:
            return false;
        }

        public final Node<K, V> nextNode() {
            Node<K, V> r0 = this.next;
            LinkedTreeMap r1 = this.this$0;
            if (r0 != r1.header) goto L5;
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        L5:
            if (r1.modCount != this.expectedModCount) goto L8;
            this.next = r0.next;
            this.lastReturned = r0;
            return r0;
        L8:
            ArraySet$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // java.util.Iterator
        public final void remove() {
            Node<K, V> r0 = this.lastReturned;
            if (r0 == null) goto L6;
            this.this$0.removeInternal(r0, true);
            this.lastReturned = null;
            this.expectedModCount = this.this$0.modCount;
            return;
        L6:
            MethodWriter$$ExternalSyntheticBUOutline0.m();
        }
    }

    public static final class Node<K, V> implements Map.Entry<K, V> {
        final boolean allowNullValue;
        int height;
        final K key;
        Node<K, V> left;
        Node<K, V> next;
        Node<K, V> parent;
        Node<K, V> prev;
        Node<K, V> right;
        V value;

        public Node(boolean r2) {
            this.key = null;
            this.allowNullValue = r2;
            this.prev = this;
            this.next = this;
        }

        public Node(boolean r1, Node<K, V> r2, K r3, Node<K, V> r4, Node<K, V> r5) {
            this.parent = r2;
            this.key = r3;
            this.allowNullValue = r1;
            this.height = 1;
            this.next = r4;
            this.prev = r5;
            r5.next = this;
            r4.prev = this;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object r4) {
            if ((r4 instanceof Map.Entry) == false) goto L20;
            Map.Entry r42 = (Map.Entry) r4;
            K r0 = this.key;
            if (r0 != null) goto L10;
            if (r42.getKey() != null) goto L20;
        L11:
            V r3 = this.value;
            if (r3 != null) goto L17;
            if (r42.getValue() != null) goto L20;
            return true;
        L17:
            if (r3.equals(r42.getValue()) == false) goto L20;
            return true;
        L10:
            if (r0.equals(r42.getKey()) == true) goto L11;
        L20:
            return false;
        }

        public Node<K, V> first() {
            Node<K, V> r0 = this.left;
        L3:
            Node<K, V> r1 = r0;
            Node<K, V> r02 = this;
            Node<K, V> this = r1;
            if (this == null) goto L6;
            r0 = this.left;
            goto L3
        L6:
            return r02;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
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

        public Node<K, V> last() {
            Node<K, V> r0 = this.right;
        L3:
            Node<K, V> r1 = r0;
            Node<K, V> r02 = this;
            Node<K, V> this = r1;
            if (this == null) goto L6;
            r0 = this.right;
            goto L3
        L6:
            return r02;
        }

        @Override // java.util.Map.Entry
        public V setValue(V r2) {
            if (r2 == null) goto L4;
        L8:
            V r0 = this.value;
            this.value = r2;
            return r0;
        L4:
            if (this.allowNullValue == true) goto L8;
            f$$ExternalSyntheticBUOutline2.m("value == null");
            return null;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    static {
        NATURAL_ORDER = new AnonymousClass1();
    }

    public LinkedTreeMap() {
        this(NATURAL_ORDER, true);
    }

    public LinkedTreeMap(Comparator<? super K> r2, boolean r3) {
        this.size = 0;
        this.modCount = 0;
        if (r2 != null) goto L6;
        r2 = NATURAL_ORDER;
    L6:
        this.comparator = r2;
        this.allowNullValues = r3;
        this.header = new Node(r3);
    }

    public LinkedTreeMap(boolean r2) {
        this(NATURAL_ORDER, r2);
    }

    private static boolean equal(Object r0, Object r1) {
        return Objects.equals(r0, r1);
    }

    private void rebalance(Node<K, V> r8, boolean r9) {
    L2:
        if (r8 == null) goto L52;
        Node<K, V> r0 = r8.left;
        Node<K, V> r1 = r8.right;
        int r2 = 0;
        if (r0 == null) goto L6;
        int r3 = r0.height;
    L7:
        if (r1 == null) goto L9;
        int r4 = r1.height;
    L10:
        int r5 = r3 - r4;
        if (r5 != (-2)) goto L28;
        Node<K, V> r02 = r1.left;
        Node<K, V> r32 = r1.right;
        if (r32 == null) goto L15;
        int r33 = r32.height;
    L16:
        if (r02 == null) goto L18;
        r2 = r02.height;
    L18:
        int r22 = r2 - r33;
        if (r22 == (-1)) goto L24;
        if (r22 != 0) goto L23;
        if (r9 == false) goto L24;
    L23:
        rotateRight(r1);
        rotateLeft(r8);
    L25:
        if (r9 == false) goto L51;
        return;
    L51:
        r8 = r8.parent;
    L24:
        rotateLeft(r8);
        goto L25
    L15:
        r33 = 0;
        goto L16
    L28:
        if (r5 != 2) goto L44;
        Node<K, V> r12 = r0.left;
        Node<K, V> r34 = r0.right;
        if (r34 == null) goto L32;
        int r35 = r34.height;
    L33:
        if (r12 == null) goto L35;
        r2 = r12.height;
    L35:
        int r23 = r2 - r35;
        if (r23 == 1) goto L41;
        if (r23 != 0) goto L40;
        if (r9 == false) goto L41;
    L40:
        rotateLeft(r0);
        rotateRight(r8);
    L42:
        if (r9 == false) goto L51;
        return;
    L41:
        rotateRight(r8);
        goto L42
    L32:
        r35 = 0;
        goto L33
    L44:
        if (r5 != 0) goto L48;
        r8.height = r3 + 1;
        if (r9 == false) goto L51;
        return;
    L48:
        r8.height = Math.max(r3, r4) + 1;
        if (r9 == true) goto L51;
        return;
    L9:
        r4 = 0;
        goto L10
    L6:
        r3 = 0;
        goto L7
    }

    private void replaceInParent(Node<K, V> r3, Node<K, V> r4) {
        Node<K, V> r0 = r3.parent;
        r3.parent = null;
        if (r4 == null) goto L5;
        r4.parent = r0;
    L5:
        if (r0 != null) goto L7;
        this.root = r4;
        return;
    L7:
        if (r0.left != r3) goto L10;
        r0.left = r4;
        return;
    L10:
        r0.right = r4;
    }

    private void rotateLeft(Node<K, V> r5) {
        Node<K, V> r0 = r5.left;
        Node<K, V> r1 = r5.right;
        Node<K, V> r2 = r1.left;
        Node<K, V> r3 = r1.right;
        r5.right = r2;
        if (r2 == null) goto L5;
        r2.parent = r5;
    L5:
        replaceInParent(r5, r1);
        r1.left = r5;
        r5.parent = r1;
        int r4 = 0;
        if (r0 == null) goto L8;
        int r02 = r0.height;
    L9:
        if (r2 == null) goto L11;
        int r22 = r2.height;
    L12:
        int r03 = Math.max(r02, r22) + 1;
        r5.height = r03;
        if (r3 == null) goto L15;
        r4 = r3.height;
    L15:
        r1.height = Math.max(r03, r4) + 1;
        return;
    L11:
        r22 = 0;
        goto L12
    L8:
        r02 = 0;
        goto L9
    }

    private void rotateRight(Node<K, V> r5) {
        Node<K, V> r0 = r5.left;
        Node<K, V> r1 = r5.right;
        Node<K, V> r2 = r0.left;
        Node<K, V> r3 = r0.right;
        r5.left = r3;
        if (r3 == null) goto L5;
        r3.parent = r5;
    L5:
        replaceInParent(r5, r0);
        r0.right = r5;
        r5.parent = r0;
        int r4 = 0;
        if (r1 == null) goto L8;
        int r12 = r1.height;
    L9:
        if (r3 == null) goto L11;
        int r32 = r3.height;
    L12:
        int r13 = Math.max(r12, r32) + 1;
        r5.height = r13;
        if (r2 == null) goto L15;
        r4 = r2.height;
    L15:
        r0.height = Math.max(r13, r4) + 1;
        return;
    L11:
        r32 = 0;
        goto L12
    L8:
        r12 = 0;
        goto L9
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        Node<K, V> r1 = this.header;
        r1.prev = r1;
        r1.next = r1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object r1) {
        if (findByObject(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        LinkedTreeMap<K, V>.EntrySet r0 = this.entrySet;
        if (r0 != null) goto L6;
        LinkedTreeMap<K, V>.EntrySet r02 = new EntrySet(this);
        this.entrySet = r02;
        return r02;
    L6:
        return r0;
    }

    public Node<K, V> find(K r12, boolean r13) {
        Comparator<? super K> r0 = this.comparator;
        Node<K, V> r1 = this.root;
        if (r1 != null) goto L5;
        int r4 = 0;
    L18:
        Node<K, V> r7 = r1;
        if (r13 == true) goto L23;
        return null;
    L23:
        Node<K, V> r9 = this.header;
        if (r7 == null) goto L26;
        Node<K, V> r5 = new Node(this.allowNullValues, r7, r12, r9, r9.prev);
        if (r4 >= 0) goto L36;
        r7.left = r5;
    L37:
        rebalance(r7, true);
    L38:
        this.size++;
        this.modCount++;
        return r5;
    L36:
        r7.right = r5;
        goto L37
    L26:
        if (r0 == NATURAL_ORDER) goto L28;
    L32:
        r5 = new Node(this.allowNullValues, r7, r12, r9, r9.prev);
        this.root = r5;
        goto L38
    L28:
        if ((r12 instanceof Comparable) == true) goto L32;
        throw new ClassCastException(r12.getClass().getName().concat(" is not Comparable"));
    L5:
        if (r0 != NATURAL_ORDER) goto L7;
        Comparable r3 = (Comparable) r12;
    L8:
        K r42 = r1.key;
        if (r3 == null) goto L11;
        r4 = r3.compareTo(r42);
    L12:
        if (r4 == 0) goto L13;
        if (r4 >= 0) goto L16;
        Node<K, V> r52 = r1.left;
    L17:
        if (r52 == null) goto L18;
        r1 = r52;
        goto L8
    L16:
        r52 = r1.right;
        goto L17
    L13:
        return r1;
    L11:
        r4 = r0.compare(r12, r42);
        goto L12
    L7:
        r3 = null;
        goto L8
    }

    public Node<K, V> findByEntry(Map.Entry<?, ?> r2) {
        Node<K, V> r1 = findByObject(r2.getKey());
        if (r1 != null) goto L5;
        return null;
    L5:
        if (equal(r1.value, r2.getValue()) == false) goto L9;
        return r1;
    L9:
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Node<K, V> findByObject(Object r3) {
        if (r3 != 0) goto L9;
    L7:
        return null;
    L9:
        return find(r3, false);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object r1) {
        Node<K, V> r0 = findByObject(r1);
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.value;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        LinkedTreeMap<K, V>.KeySet r0 = this.keySet;
        if (r0 != null) goto L6;
        LinkedTreeMap<K, V>.KeySet r02 = new KeySet(this);
        this.keySet = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K r3, V r4) {
        if (r3 == null) goto L12;
        if (r4 == null) goto L6;
    L10:
        Node<K, V> r2 = find(r3, true);
        V r32 = r2.value;
        r2.value = r4;
        return r32;
    L6:
        if (this.allowNullValues == true) goto L10;
        f$$ExternalSyntheticBUOutline2.m("value == null");
        return null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("key == null");
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object r1) {
        Node<K, V> r0 = removeInternalByKey(r1);
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.value;
    }

    public void removeInternal(Node<K, V> r6, boolean r7) {
        if (r7 == false) goto L4;
        Node<K, V> r72 = r6.prev;
        r72.next = r6.next;
        r6.next.prev = r72;
    L4:
        Node<K, V> r73 = r6.left;
        Node<K, V> r0 = r6.right;
        Node<K, V> r1 = r6.parent;
        int r2 = 0;
        if (r73 == null) goto L20;
        if (r0 == null) goto L20;
        if (r73.height <= r0.height) goto L10;
        Node<K, V> r74 = r73.last();
    L11:
        removeInternal(r74, false);
        Node<K, V> r02 = r6.left;
        if (r02 == null) goto L14;
        int r12 = r02.height;
        r74.left = r02;
        r02.parent = r74;
        r6.left = null;
    L15:
        Node<K, V> r03 = r6.right;
        if (r03 == null) goto L18;
        r2 = r03.height;
        r74.right = r03;
        r03.parent = r74;
        r6.right = null;
    L18:
        r74.height = Math.max(r12, r2) + 1;
        replaceInParent(r6, r74);
        return;
    L14:
        r12 = 0;
        goto L15
    L10:
        r74 = r0.first();
    L20:
        if (r73 == null) goto L22;
        replaceInParent(r6, r73);
        r6.left = null;
    L25:
        rebalance(r1, false);
        this.size--;
        this.modCount++;
        return;
    L22:
        if (r0 == null) goto L24;
        replaceInParent(r6, r0);
        r6.right = null;
        goto L25
    L24:
        replaceInParent(r6, null);
        goto L25
    }

    public Node<K, V> removeInternalByKey(Object r2) {
        Node<K, V> r22 = findByObject(r2);
        if (r22 == null) goto L5;
        removeInternal(r22, true);
    L5:
        return r22;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }
}
