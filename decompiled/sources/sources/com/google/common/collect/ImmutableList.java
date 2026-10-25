package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* loaded from: classes.dex */
public abstract class ImmutableList<E> extends ImmutableCollection<E> implements List<E>, RandomAccess {
    private static final UnmodifiableListIterator<Object> EMPTY_ITR = null;

    public static final class Builder<E> extends ImmutableCollection.ArrayBasedBuilder<E> {
        public Builder() {
            this(4);
        }

        public Builder(int r1) {
            super(r1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableCollection.Builder
        public /* bridge */ /* synthetic */ ImmutableCollection.Builder add(Object r1) {
            return add(r1);
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> add(E r1) {
            super.add(r1);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder
        public Builder<E> add(E... r1) {
            super.add(r1);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> addAll(Iterable<? extends E> r1) {
            super.addAll(r1);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> addAll(Iterator<? extends E> r1) {
            super.addAll(r1);
            return this;
        }

        public ImmutableList<E> build() {
            this.forceCopy = true;
            return ImmutableList.asImmutableList(this.contents, this.size);
        }
    }

    public static final class Itr<E> extends AbstractIndexedListIterator<E> {
        private final ImmutableList<E> list;

        public Itr(ImmutableList<E> r2, int r3) {
            super(r2.size(), r3);
            this.list = r2;
        }

        @Override // com.google.common.collect.AbstractIndexedListIterator
        public E get(int r1) {
            return this.list.get(r1);
        }
    }

    /* loaded from: classes5.dex */
    public static final class ReverseImmutableList<E> extends ImmutableList<E> {
        private final transient ImmutableList<E> forwardList;

        public ReverseImmutableList(ImmutableList<E> r1) {
            this.forwardList = r1;
        }

        private int reverseIndex(int r1) {
            return (size() - 1) - r1;
        }

        private int reversePosition(int r1) {
            return size() - r1;
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object r1) {
            return this.forwardList.contains(r1);
        }

        @Override // java.util.List
        public E get(int r2) {
            Preconditions.checkElementIndex(r2, size());
            return this.forwardList.get(reverseIndex(r2));
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public int indexOf(Object r2) {
            int r22 = this.forwardList.lastIndexOf(r2);
            if (r22 >= 0) goto L5;
            return -1;
        L5:
            return reverseIndex(r22);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return this.forwardList.isPartialView();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public /* bridge */ /* synthetic */ Iterator iterator() {
            return super.iterator();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public int lastIndexOf(Object r2) {
            int r22 = this.forwardList.indexOf(r2);
            if (r22 >= 0) goto L5;
            return -1;
        L5:
            return reverseIndex(r22);
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public /* bridge */ /* synthetic */ ListIterator listIterator() {
            return super.listIterator();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public /* bridge */ /* synthetic */ ListIterator listIterator(int r1) {
            return super.listIterator(r1);
        }

        @Override // com.google.common.collect.ImmutableList
        public ImmutableList<E> reverse() {
            return this.forwardList;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.forwardList.size();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public ImmutableList<E> subList(int r2, int r3) {
            Preconditions.checkPositionIndexes(r2, r3, size());
            return this.forwardList.subList(reversePosition(r3), reversePosition(r2)).reverse();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public /* bridge */ /* synthetic */ List subList(int r1, int r2) {
            return subList(r1, r2);
        }
    }

    /* loaded from: classes5.dex */
    public final class SubList extends ImmutableList<E> {
        final transient int length;
        final transient int offset;
        final /* synthetic */ ImmutableList this$0;

        public SubList(ImmutableList r1, int r2, int r3) {
            this.this$0 = r1;
            this.offset = r2;
            this.length = r3;
        }

        @Override // java.util.List
        public E get(int r2) {
            Preconditions.checkElementIndex(r2, this.length);
            return this.this$0.get(r2 + this.offset);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public Object[] internalArray() {
            return this.this$0.internalArray();
        }

        @Override // com.google.common.collect.ImmutableCollection
        public int internalArrayEnd() {
            return (this.this$0.internalArrayStart() + this.offset) + this.length;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public int internalArrayStart() {
            return this.this$0.internalArrayStart() + this.offset;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public /* bridge */ /* synthetic */ Iterator iterator() {
            return super.iterator();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public /* bridge */ /* synthetic */ ListIterator listIterator() {
            return super.listIterator();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public /* bridge */ /* synthetic */ ListIterator listIterator(int r1) {
            return super.listIterator(r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.length;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public ImmutableList<E> subList(int r2, int r3) {
            Preconditions.checkPositionIndexes(r2, r3, this.length);
            ImmutableList r0 = this.this$0;
            int r1 = this.offset;
            return r0.subList(r2 + r1, r3 + r1);
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public /* bridge */ /* synthetic */ List subList(int r1, int r2) {
            return subList(r1, r2);
        }
    }

    static {
        EMPTY_ITR = new Itr(RegularImmutableList.EMPTY, 0);
    }

    public ImmutableList() {
    }

    public static <E> ImmutableList<E> asImmutableList(Object[] r1) {
        return asImmutableList(r1, r1.length);
    }

    public static <E> ImmutableList<E> asImmutableList(Object[] r1, int r2) {
        if (r2 != 0) goto L6;
        return of();
    L6:
        return new RegularImmutableList(r1, r2);
    }

    public static <E> Builder<E> builder() {
        return new Builder();
    }

    public static <E> Builder<E> builderWithExpectedSize(int r1) {
        CollectPreconditions.checkNonnegative(r1, "expectedSize");
        return new Builder(r1);
    }

    private static <E> ImmutableList<E> construct(Object... r0) {
        return asImmutableList(ObjectArrays.checkElementsNotNull(r0));
    }

    public static <E> ImmutableList<E> copyOf(Iterable<? extends E> r1) {
        Preconditions.checkNotNull(r1);
        if ((r1 instanceof Collection) == false) goto L7;
        return copyOf((Collection) r1);
    L7:
        return copyOf(r1.iterator());
    }

    public static <E> ImmutableList<E> copyOf(Collection<? extends E> r1) {
        if ((r1 instanceof ImmutableCollection) == false) goto L9;
        ImmutableList<E> r12 = ((ImmutableCollection) r1).asList();
        if (r12.isPartialView() == true) goto L7;
        return r12;
    L7:
        return asImmutableList(r12.toArray());
    L9:
        return construct(r1.toArray());
    }

    public static <E> ImmutableList<E> copyOf(Iterator<? extends E> r2) {
        if (r2.hasNext() == false) goto L5;
        E r0 = r2.next();
        if (r2.hasNext() == true) goto L11;
        return of(r0);
    L11:
        return new Builder().add(r0).addAll(r2).build();
    L5:
        return of();
    }

    public static <E> ImmutableList<E> copyOf(E[] r1) {
        if (r1.length != 0) goto L7;
        return of();
    L7:
        return construct((Object[]) r1.clone());
    }

    public static <E> ImmutableList<E> of() {
        return (ImmutableList<E>) RegularImmutableList.EMPTY;
    }

    public static <E> ImmutableList<E> of(E r0) {
        return construct(new Object[]{r0});
    }

    public static <E> ImmutableList<E> of(E r0, E r1) {
        return construct(new Object[]{r0, r1});
    }

    public static <E> ImmutableList<E> of(E r0, E r1, E r2) {
        return construct(new Object[]{r0, r1, r2});
    }

    public static <E> ImmutableList<E> of(E r0, E r1, E r2, E r3, E r4) {
        return construct(new Object[]{r0, r1, r2, r3, r4});
    }

    public static <E> ImmutableList<E> of(E r0, E r1, E r2, E r3, E r4, E r5, E r6) {
        return construct(new Object[]{r0, r1, r2, r3, r4, r5, r6});
    }

    @SafeVarargs
    public static <E> ImmutableList<E> of(E r5, E r6, E r7, E r8, E r9, E r10, E r11, E r12, E r13, E r14, E r15, E r16, E... r17) {
        if (r17.length > 2147483635) goto L5;
        boolean r1 = true;
    L6:
        Preconditions.checkArgument(r1, "the total number of elements must fit in an int");
        Object[] r18 = new Object[r17.length + 12];
        r18[0] = r5;
        r18[1] = r6;
        r18[2] = r7;
        r18[3] = r8;
        r18[4] = r9;
        r18[5] = r10;
        r18[6] = r11;
        r18[7] = r12;
        r18[8] = r13;
        r18[9] = r14;
        r18[10] = r15;
        r18[11] = r16;
        System.arraycopy(r17, 0, r18, 12, r17.length);
        return construct(r18);
    L5:
        r1 = false;
        goto L6
    }

    public static <E> ImmutableList<E> sortedCopyOf(Comparator<? super E> r0, Iterable<? extends E> r1) {
        Preconditions.checkNotNull(r0);
        Object[] r12 = Iterables.toArray(r1);
        ObjectArrays.checkElementsNotNull(r12);
        Arrays.sort(r12, r0);
        return asImmutableList(r12);
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int r1, E r2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int r1, Collection<? extends E> r2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableCollection
    @Deprecated
    public final ImmutableList<E> asList() {
        return this;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object r1) {
        if (indexOf(r1) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] r5, int r6) {
        int r0 = size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L6;
        r5[r6 + r1] = get(r1);
        r1 = r1 + 1;
        goto L3
    L6:
        return r6 + r0;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object r1) {
        return Lists.equalsImpl(this, r1);
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = size();
        int r1 = 1;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1 = ~(~((r1 * 31) + get(r2).hashCode()));
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    @Override // java.util.List
    public int indexOf(Object r1) {
        if (r1 != null) goto L6;
        return -1;
    L6:
        return Lists.indexOfImpl(this, r1);
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public UnmodifiableIterator<E> iterator() {
        return listIterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // java.util.List
    public int lastIndexOf(Object r1) {
        if (r1 != null) goto L6;
        return -1;
    L6:
        return Lists.lastIndexOfImpl(this, r1);
    }

    @Override // java.util.List
    public final UnmodifiableListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public UnmodifiableListIterator<E> listIterator(int r2) {
        Preconditions.checkPositionIndex(r2, size());
        if (isEmpty() == false) goto L7;
        return (UnmodifiableListIterator<E>) EMPTY_ITR;
    L7:
        return new Itr(this, r2);
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ ListIterator listIterator() {
        return listIterator();
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ ListIterator listIterator(int r1) {
        return listIterator(r1);
    }

    @Override // java.util.List
    @Deprecated
    public final E remove(int r1) {
        throw new UnsupportedOperationException();
    }

    public ImmutableList<E> reverse() {
        if (size() > 1) goto L6;
        return this;
    L6:
        return new ReverseImmutableList(this);
    }

    @Override // java.util.List
    @Deprecated
    public final E set(int r1, E r2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public ImmutableList<E> subList(int r3, int r4) {
        Preconditions.checkPositionIndexes(r3, r4, size());
        int r0 = r4 - r3;
        if (r0 != size()) goto L5;
        return this;
    L5:
        if (r0 != 0) goto L9;
        return of();
    L9:
        return subListUnchecked(r3, r4);
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return subList(r1, r2);
    }

    public ImmutableList<E> subListUnchecked(int r2, int r3) {
        return new SubList(this, r2, r3 - r2);
    }
}
