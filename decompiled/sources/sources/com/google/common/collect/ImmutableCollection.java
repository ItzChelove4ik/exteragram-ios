package com.google.common.collect;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;

/* loaded from: classes.dex */
public abstract class ImmutableCollection<E> extends AbstractCollection<E> implements Serializable {
    private static final Object[] EMPTY_ARRAY = null;

    public static abstract class ArrayBasedBuilder<E> extends Builder<E> {
        Object[] contents;
        boolean forceCopy;
        int size;

        public ArrayBasedBuilder(int r2) {
            CollectPreconditions.checkNonnegative(r2, "initialCapacity");
            this.contents = new Object[r2];
            this.size = 0;
        }

        private void ensureRoomFor(int r4) {
            Object[] r0 = this.contents;
            int r42 = Builder.expandedCapacity(r0.length, this.size + r4);
            if (r42 <= r0.length) goto L5;
        L8:
            this.contents = Arrays.copyOf(this.contents, r42);
            this.forceCopy = false;
            return;
        L5:
            if (this.forceCopy == true) goto L8;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public ArrayBasedBuilder<E> add(E r4) {
            Preconditions.checkNotNull(r4);
            ensureRoomFor(1);
            Object[] r0 = this.contents;
            int r1 = this.size;
            this.size = r1 + 1;
            r0[r1] = r4;
            return this;
        }

        public Builder<E> add(E... r2) {
            addAll(r2, r2.length);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> addAll(Iterable<? extends E> r3) {
            if ((r3 instanceof Collection) == false) goto L8;
            Collection r0 = (Collection) r3;
            ensureRoomFor(r0.size());
            if ((r0 instanceof ImmutableCollection) == false) goto L8;
            this.size = ((ImmutableCollection) r0).copyIntoArray(this.contents, this.size);
            return this;
        L8:
            super.addAll(r3);
            return this;
        }

        public final void addAll(Object[] r4, int r5) {
            ObjectArrays.checkElementsNotNull(r4, r5);
            ensureRoomFor(r5);
            System.arraycopy(r4, 0, this.contents, this.size, r5);
            this.size += r5;
        }
    }

    public static abstract class Builder<E> {
        public Builder() {
        }

        public static int expandedCapacity(int r1, int r2) {
            if (r2 < 0) goto L11;
            if (r2 > r1) goto L5;
            return r1;
        L5:
            int r12 = (r1 + (r1 >> 1)) + 1;
            if (r12 >= r2) goto L8;
            r12 = Integer.highestOneBit(r2 - 1) << 1;
        L8:
            if (r12 >= 0) goto L13;
            return Integer.MAX_VALUE;
        L13:
            return r12;
        L11:
            f$$ExternalSyntheticBUOutline1.m("cannot store more than Integer.MAX_VALUE elements");
            return 0;
        }

        public abstract Builder<E> add(E r1);

        public Builder<E> addAll(Iterable<? extends E> r2) {
            Iterator<? extends E> r22 = r2.iterator();
        L4:
            if (r22.hasNext() == false) goto L6;
            add(r22.next());
            goto L4
        L6:
            return this;
        }

        public Builder<E> addAll(Iterator<? extends E> r2) {
        L3:
            if (r2.hasNext() == false) goto L5;
            add(r2.next());
            goto L3
        L5:
            return this;
        }
    }

    static {
        EMPTY_ARRAY = new Object[0];
    }

    public ImmutableCollection() {
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean add(E r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean addAll(Collection<? extends E> r1) {
        throw new UnsupportedOperationException();
    }

    public abstract ImmutableList<E> asList();

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public abstract boolean contains(Object r1);

    public abstract int copyIntoArray(Object[] r1, int r2);

    public Object[] internalArray() {
        return null;
    }

    public int internalArrayEnd() {
        throw new UnsupportedOperationException();
    }

    public int internalArrayStart() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean isPartialView();

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public abstract UnmodifiableIterator<E> iterator();

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean remove(Object r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean removeAll(Collection<?> r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean retainAll(Collection<?> r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 1296);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final Object[] toArray() {
        return toArray(EMPTY_ARRAY);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final <T> T[] toArray(T[] r3) {
        Preconditions.checkNotNull(r3);
        int r0 = size();
        if (r3.length >= r0) goto L10;
        Object[] r1 = internalArray();
        if (r1 != null) goto L7;
        r3 = (T[]) ObjectArrays.newArray(r3, r0);
    L12:
        copyIntoArray(r3, 0);
        return r3;
    L7:
        return (T[]) Platform.copy(r1, internalArrayStart(), internalArrayEnd(), r3);
    L10:
        if (r3.length <= r0) goto L12;
        r3[r0] = null;
        goto L12
    }
}
