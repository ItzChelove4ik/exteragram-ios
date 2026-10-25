package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import org.telegram.tgnet.TLObject;

/* loaded from: classes.dex */
public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {
    private transient ImmutableList<E> asList;

    /* loaded from: classes5.dex */
    public static class Builder<E> extends ImmutableCollection.ArrayBasedBuilder<E> {
        private int hashCode;
        Object[] hashTable;

        public Builder() {
            super(4);
        }

        private void addDeduping(E r6) {
            Objects.requireNonNull(this.hashTable);
            int r0 = this.hashTable.length - 1;
            int r1 = r6.hashCode();
            int r2 = Hashing.smear(r1);
        L3:
            int r22 = r2 & r0;
            Object[] r3 = this.hashTable;
            Object r4 = r3[r22];
            if (r4 == null) goto L5;
            if (r4.equals(r6) == true) goto L9;
            r2 = r22 + 1;
            goto L3
        L9:
            return;
        L5:
            r3[r22] = r6;
            this.hashCode += r1;
            super.add(r6);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableCollection.Builder
        public /* bridge */ /* synthetic */ ImmutableCollection.Builder add(Object r1) {
            return add(r1);
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> add(E r3) {
            Preconditions.checkNotNull(r3);
            if (this.hashTable != null) goto L5;
        L8:
            this.hashTable = null;
            super.add(r3);
            return this;
        L5:
            if (ImmutableSet.chooseTableSize(this.size) > this.hashTable.length) goto L8;
            addDeduping(r3);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder
        public Builder<E> add(E... r4) {
            if (this.hashTable == null) goto L8;
            int r0 = r4.length;
            int r1 = 0;
        L5:
            if (r1 >= r0) goto L7;
            add(r4[r1]);
            r1 = r1 + 1;
            goto L5
        L7:
            return this;
        L8:
            super.add(r4);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> addAll(Iterable<? extends E> r2) {
            Preconditions.checkNotNull(r2);
            if (this.hashTable == null) goto L9;
            Iterator<? extends E> r22 = r2.iterator();
        L6:
            if (r22.hasNext() == false) goto L8;
            add(r22.next());
            goto L6
        L8:
            return this;
        L9:
            super.addAll(r2);
            return this;
        }

        public ImmutableSet<E> build() {
            int r0 = this.size;
            if (r0 == 0) goto L20;
            if (r0 != 1) goto L7;
            Object r9 = this.contents[0];
            Objects.requireNonNull(r9);
            return ImmutableSet.of(r9);
        L7:
            if (this.hashTable != null) goto L9;
        L14:
            ImmutableSet<E> r3 = ImmutableSet.access$100(this.size, this.contents);
            this.size = r3.size();
        L15:
            this.forceCopy = true;
            this.hashTable = null;
            return r3;
        L9:
            if (ImmutableSet.chooseTableSize(r0) != this.hashTable.length) goto L14;
            boolean r02 = ImmutableSet.access$000(this.size, this.contents.length);
            Object[] r2 = this.contents;
            if (r02 == false) goto L13;
            r2 = Arrays.copyOf(r2, this.size);
        L13:
            r3 = new RegularImmutableSet(r2, this.hashCode, this.hashTable, r6.length - 1, this.size);
            goto L15
        L20:
            return ImmutableSet.of();
        }
    }

    public ImmutableSet() {
    }

    public static /* synthetic */ boolean access$000(int r0, int r1) {
        return shouldTrim(r0, r1);
    }

    public static /* synthetic */ ImmutableSet access$100(int r0, Object[] r1) {
        return construct(r0, r1);
    }

    public static int chooseTableSize(int r5) {
        int r52 = Math.max(r5, 2);
        boolean r1 = true;
        if (r52 >= 751619276) goto L10;
        int r0 = Integer.highestOneBit(r52 - 1) << 1;
    L6:
        if ((r0 * 0.7d) >= r52) goto L8;
        r0 = r0 << 1;
        goto L6
    L8:
        return r0;
    L10:
        if (r52 < 1073741824) goto L13;
        r1 = false;
    L13:
        Preconditions.checkArgument(r1, "collection too large");
        return TLObject.FLAG_30;
    }

    private static <E> ImmutableSet<E> construct(int r13, Object... r14) {
        if (r13 == 0) goto L31;
        if (r13 == 1) goto L28;
        int r2 = chooseTableSize(r13);
        Object[] r6 = new Object[r2];
        int r7 = r2 - 1;
        int r3 = 0;
        int r5 = 0;
        int r8 = 0;
    L6:
        if (r3 >= r13) goto L15;
        Object r4 = ObjectArrays.checkElementNotNull(r14[r3], r3);
        int r9 = r4.hashCode();
        int r10 = Hashing.smear(r9);
    L8:
        int r11 = r10 & r7;
        Object r12 = r6[r11];
        if (r12 == null) goto L10;
        if (r12.equals(r4) == true) goto L13;
        r10 = r10 + 1;
    L13:
        r3 = r3 + 1;
        goto L6
    L10:
        r14[r8] = r4;
        r6[r11] = r4;
        r5 = r5 + r9;
        r8 = r8 + 1;
        goto L13
    L15:
        Arrays.fill(r14, r8, r13, null);
        if (r8 != 1) goto L20;
        Object r132 = r14[0];
        Objects.requireNonNull(r132);
        return new SingletonImmutableSet(r132);
    L20:
        if (chooseTableSize(r8) >= (r2 / 2)) goto L24;
        return construct(r8, r14);
    L24:
        if (shouldTrim(r8, r14.length) == false) goto L27;
        r14 = Arrays.copyOf(r14, r8);
    L27:
        return new RegularImmutableSet(r14, r5, r6, r7, r8);
    L28:
        Object r133 = r14[0];
        Objects.requireNonNull(r133);
        return of(r133);
    L31:
        return of();
    }

    public static <E> ImmutableSet<E> copyOf(Collection<? extends E> r2) {
        if ((r2 instanceof ImmutableSet) == true) goto L5;
    L9:
        Object[] r22 = r2.toArray();
        return construct(r22.length, r22);
    L5:
        if ((r2 instanceof SortedSet) == true) goto L9;
        ImmutableSet<E> r0 = (ImmutableSet) r2;
        if (r0.isPartialView() == true) goto L9;
        return r0;
    }

    public static <E> ImmutableSet<E> copyOf(E[] r2) {
        int r0 = r2.length;
        if (r0 == 0) goto L11;
        if (r0 == 1) goto L9;
        return construct(r2.length, (Object[]) r2.clone());
    L9:
        return of(r2[0]);
    L11:
        return of();
    }

    public static <E> ImmutableSet<E> of() {
        return RegularImmutableSet.EMPTY;
    }

    public static <E> ImmutableSet<E> of(E r1) {
        return new SingletonImmutableSet(r1);
    }

    public static <E> ImmutableSet<E> of(E r1, E r2) {
        return construct(2, new Object[]{r1, r2});
    }

    public static <E> ImmutableSet<E> of(E r1, E r2, E r3) {
        return construct(3, new Object[]{r1, r2, r3});
    }

    public static <E> ImmutableSet<E> of(E r1, E r2, E r3, E r4, E r5) {
        return construct(5, new Object[]{r1, r2, r3, r4, r5});
    }

    @SafeVarargs
    public static <E> ImmutableSet<E> of(E r5, E r6, E r7, E r8, E r9, E r10, E... r11) {
        if (r11.length > 2147483641) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0, "the total number of elements must fit in an int");
        int r02 = r11.length + 6;
        Object[] r4 = new Object[r02];
        r4[0] = r5;
        r4[1] = r6;
        r4[2] = r7;
        r4[3] = r8;
        r4[4] = r9;
        r4[5] = r10;
        System.arraycopy(r11, 0, r4, 6, r11.length);
        return construct(r02, r4);
    L5:
        r0 = false;
        goto L6
    }

    private static boolean shouldTrim(int r1, int r2) {
        if (r1 >= ((r2 >> 1) + (r2 >> 2))) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        ImmutableList<E> r0 = this.asList;
        if (r0 != null) goto L6;
        ImmutableList<E> r02 = createAsList();
        this.asList = r02;
        return r02;
    L6:
        return r0;
    }

    public ImmutableList<E> createAsList() {
        return ImmutableList.asImmutableList(toArray());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object r3) {
        if (r3 != this) goto L6;
        return true;
    L6:
        if ((r3 instanceof ImmutableSet) == false) goto L16;
        if (isHashCodeFast() == false) goto L16;
        if (((ImmutableSet) r3).isHashCodeFast() == false) goto L16;
        if (hashCode() == r3.hashCode()) goto L16;
        return false;
    L16:
        return Sets.equalsImpl(this, r3);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }

    public boolean isHashCodeFast() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public abstract UnmodifiableIterator<E> iterator();
}
