package org.mvel2.util;

import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.mvel2.ImmutableElementException;

/* loaded from: classes5.dex */
public class FastList<E> extends AbstractList<E> implements Externalizable {
    private E[] elements;
    private int size;
    private boolean updated;

    /* renamed from: org.mvel2.util.FastList$1, reason: invalid class name */
    public class AnonymousClass1 implements Iterator {
        private int cursor;
        final /* synthetic */ FastList this$0;
        final /* synthetic */ int val$size;

        public AnonymousClass1(FastList r1, int r2) {
            this.this$0 = r1;
            this.val$size = r2;
            this.cursor = 0;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.cursor >= this.val$size) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // java.util.Iterator
        public Object next() {
            Object[] r0 = FastList.access$000(this.this$0);
            int r1 = this.cursor;
            this.cursor = r1 + 1;
            return r0[r1];
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new ImmutableElementException("cannot change elements in immutable list");
        }
    }

    /* renamed from: org.mvel2.util.FastList$2, reason: invalid class name */
    public class AnonymousClass2 implements ListIterator<E> {
        private int i;
        final /* synthetic */ FastList this$0;

        public AnonymousClass2(FastList r1) {
            this.this$0 = r1;
            this.i = -1;
        }

        @Override // java.util.ListIterator
        public void add(Object r1) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            if (this.i >= (FastList.access$100(this.this$0) - 1)) goto L5;
            return true;
        L5:
            return false;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            if (this.i <= 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public E next() {
            Object[] r0 = FastList.access$000(this.this$0);
            int r1 = this.i + 1;
            this.i = r1;
            return (E) r0[r1];
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            int r0 = this.i;
            this.i = r0 + 1;
            return r0;
        }

        @Override // java.util.ListIterator
        public E previous() {
            Object[] r0 = FastList.access$000(this.this$0);
            int r1 = this.i - 1;
            this.i = r1;
            return (E) r0[r1];
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            int r0 = this.i;
            this.i = r0 - 1;
            return r0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator
        public void set(E r2) {
            FastList.access$000(this.this$0)[this.i] = r2;
        }
    }

    public FastList() {
        this(10);
    }

    public FastList(int r2) {
        this.size = 0;
        this.updated = false;
        if (r2 != 0) goto L5;
        r2 = 1;
    L5:
        this.elements = (E[]) new Object[r2];
    }

    public FastList(E[] r2) {
        this.size = 0;
        this.updated = false;
        this.elements = r2;
        this.size = r2.length;
    }

    public static /* synthetic */ Object[] access$000(FastList r0) {
        return r0.elements;
    }

    public static /* synthetic */ int access$100(FastList r0) {
        return r0.size;
    }

    private void copyArray() {
        increaseSize(this.elements.length);
    }

    private void ensureCapacity(int r4) {
        int r0 = this.size;
        if ((r0 + r4) <= this.elements.length) goto L6;
        increaseSize((r0 + r4) * 2);
        return;
    }

    private void increaseSize(int r4) {
        E[] r42 = (E[]) new Object[r4];
        int r0 = 0;
    L3:
        E[] r1 = this.elements;
        if (r0 >= r1.length) goto L6;
        r42[r0] = r1[r0];
        r0 = r0 + 1;
        goto L3
    L6:
        this.elements = r42;
        this.updated = true;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int r4, E r5) {
        int r0 = this.size;
        E[] r1 = this.elements;
        if (r0 != r1.length) goto L5;
        increaseSize(r1.length * 2);
    L5:
        int r02 = this.size;
    L6:
        E[] r12 = this.elements;
        if (r02 == r4) goto L9;
        r12[r02] = r12[r02 - 1];
        r02 = r02 - 1;
        goto L6
    L9:
        r12[r4] = r5;
        this.size++;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E r4) {
        int r0 = this.size;
        E[] r1 = this.elements;
        if (r0 != r1.length) goto L5;
        increaseSize(r1.length * 2);
    L5:
        E[] r02 = this.elements;
        int r12 = this.size;
        this.size = r12 + 1;
        r02[r12] = r4;
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int r7, Collection<? extends E> r8) {
        int r0 = r8.size();
        ensureCapacity(this.size + r0);
        if (r7 == 0) goto L9;
        int r2 = r7;
    L6:
        if (r2 == (r7 + r0)) goto L9;
        E[] r3 = this.elements;
        r3[(r2 + r0) + 1] = r3[r2];
        r2 = r2 + 1;
    L9:
        if (this.size != 0) goto L11;
        int r72 = -1;
    L12:
        Iterator<? extends E> r82 = r8.iterator();
    L14:
        if (r82.hasNext() == false) goto L16;
        this.elements[r72 + r0] = r82.next();
        r72 = r72 + 1;
        goto L14
    L16:
        this.size += r0;
        return true;
    L11:
        r72 = 0;
        goto L12
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection r2) {
        return addAll(this.size, r2);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        this.elements = (E[]) new Object[1];
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object r1) {
        if (indexOf(r1) == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean containsAll(Collection r1) {
        throw new RuntimeException("not implemented");
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof List) == true) goto L8;
        return false;
    L8:
        ListIterator<E> r4 = listIterator();
        ListIterator<E> r52 = ((List) r5).listIterator();
    L10:
        if (r4.hasNext() == false) goto L21;
        if (r52.hasNext() == false) goto L21;
        E r1 = r4.next();
        E r3 = r52.next();
        if (r1 == null) goto L15;
        if (r1.equals(r3) == true) goto L10;
    L19:
        return false;
    L15:
        if (r3 != null) goto L19;
    L21:
        if (r4.hasNext() == false) goto L23;
    L25:
        return false;
    L23:
        if (r52.hasNext() == true) goto L25;
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int r1) {
        return this.elements[r1];
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        return super.hashCode();
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object r5) {
        if (r5 != null) goto L5;
        return -1;
    L5:
        int r1 = 0;
    L6:
        E[] r2 = this.elements;
        if (r1 >= r2.length) goto L12;
        if (r5.equals(r2[r1]) == true) goto L10;
        r1 = r1 + 1;
        goto L6
    L10:
        return r1;
    L12:
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        if (this.size != 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator iterator() {
        return new AnonymousClass1(this, this.size);
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object r4) {
        if (r4 != null) goto L5;
        return -1;
    L5:
        int r1 = this.elements.length - 1;
    L6:
        if (r1 == (-1)) goto L11;
        if (r4.equals(this.elements[r1]) == true) goto L9;
        r1 = r1 - 1;
        goto L6
    L9:
        return r1;
    L11:
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator() {
        return new AnonymousClass2(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator listIterator(int r1) {
        return super.listIterator(r1);
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput r4) {
        int r0 = r4.readInt();
        this.size = r0;
        this.elements = (E[]) new Object[r0];
        int r02 = 0;
    L4:
        if (r02 >= this.size) goto L6;
        ((E[]) this.elements)[r02] = r4.readObject();
        r02 = r02 + 1;
        goto L4
    }

    @Override // java.util.AbstractList, java.util.List
    public E remove(int r5) {
        E r0 = this.elements[r5];
    L3:
        r5 = r5 + 1;
        int r1 = this.size;
        if (r5 >= r1) goto L6;
        E[] r12 = this.elements;
        r12[r5 - 1] = r12[r5];
        r12[r5] = null;
        goto L3
    L6:
        this.size = r1 - 1;
        return r0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object r1) {
        throw new RuntimeException("not implemented");
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection r1) {
        throw new RuntimeException("not implemented");
    }

    @Override // java.util.AbstractList
    public void removeRange(int r1, int r2) {
        throw new RuntimeException("not implemented");
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection r1) {
        throw new RuntimeException("not implemented");
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int r2, E r3) {
        if (this.updated == true) goto L5;
        copyArray();
    L5:
        E[] r1 = this.elements;
        E r0 = r1[r2];
        r1[r2] = r3;
        return r0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractList, java.util.List
    public List subList(int r1, int r2) {
        return super.subList(r1, r2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        return toArray(new Object[this.size]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray(Object[] r3) {
        int r0 = r3.length;
        int r1 = this.size;
        if (r0 >= r1) goto L5;
        r3 = new Object[r1];
    L5:
        int r02 = 0;
    L7:
        if (r02 >= this.size) goto L9;
        r3[r02] = this.elements[r02];
        r02 = r02 + 1;
        goto L7
    L9:
        return r3;
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return super.toString();
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput r3) {
        r3.writeInt(this.size);
        int r0 = 0;
    L4:
        if (r0 >= this.size) goto L6;
        r3.writeObject(this.elements[r0]);
        r0 = r0 + 1;
        goto L4
    }
}
