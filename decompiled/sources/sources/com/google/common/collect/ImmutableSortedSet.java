package com.google.common.collect;

import android.R;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;

/* loaded from: classes5.dex */
public abstract class ImmutableSortedSet<E> extends ImmutableSet<E> implements NavigableSet<E>, SortedIterable<E> {
    final transient Comparator<? super E> comparator;
    transient ImmutableSortedSet<E> descendingSet;

    public static final class Builder<E> extends ImmutableSet.Builder<E> {
        private final Comparator<? super E> comparator;

        public Builder(Comparator<? super E> r1) {
            this.comparator = (Comparator) Preconditions.checkNotNull(r1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.Builder
        public /* bridge */ /* synthetic */ ImmutableCollection.Builder add(Object r1) {
            return add(r1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public /* bridge */ /* synthetic */ ImmutableSet.Builder add(Object r1) {
            return add(r1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder
        public /* bridge */ /* synthetic */ ImmutableSet.Builder add(Object[] r1) {
            return add(r1);
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> add(E r1) {
            super.add(r1);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder
        public Builder<E> add(E... r1) {
            super.add(r1);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public /* bridge */ /* synthetic */ ImmutableSet.Builder addAll(Iterable r1) {
            return addAll(r1);
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> addAll(Iterable<? extends E> r1) {
            super.addAll(r1);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder
        public /* bridge */ /* synthetic */ ImmutableSet build() {
            return build();
        }

        @Override // com.google.common.collect.ImmutableSet.Builder
        public ImmutableSortedSet<E> build() {
            Object[] r0 = this.contents;
            ImmutableSortedSet<E> r02 = ImmutableSortedSet.construct(this.comparator, this.size, r0);
            this.size = r02.size();
            this.forceCopy = true;
            return r02;
        }
    }

    public ImmutableSortedSet(Comparator<? super E> r1) {
        this.comparator = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> ImmutableSortedSet<E> construct(Comparator<? super E> r4, int r5, E... r6) {
        if (r5 == 0) goto L4;
        ObjectArrays.checkElementsNotNull(r6, r5);
        Arrays.sort(r6, 0, r5, r4);
        int r0 = 1;
        int r1 = 1;
    L6:
        if (r0 >= r5) goto L11;
        R r2 = (Object) r6[r0];
        if (r4.compare(r2, (Object) r6[r1 - 1]) == 0) goto L10;
        r6[r1] = r2;
        r1 = r1 + 1;
    L10:
        r0 = r0 + 1;
        goto L6
    L11:
        Arrays.fill(r6, r1, r5, null);
        if (r1 >= (r6.length / 2)) goto L15;
        r6 = (E[]) Arrays.copyOf(r6, r1);
    L15:
        return new RegularImmutableSortedSet(ImmutableList.asImmutableList(r6, r1), r4);
    L4:
        return emptySet(r4);
    }

    public static <E> RegularImmutableSortedSet<E> emptySet(Comparator<? super E> r2) {
        if (Ordering.natural().equals(r2) == false) goto L7;
        return (RegularImmutableSortedSet<E>) RegularImmutableSortedSet.NATURAL_EMPTY_SET;
    L7:
        return new RegularImmutableSortedSet(ImmutableList.of(), r2);
    }

    public static <E extends Comparable<?>> Builder<E> naturalOrder() {
        return new Builder(Ordering.natural());
    }

    public static <E> ImmutableSortedSet<E> of() {
        return RegularImmutableSortedSet.NATURAL_EMPTY_SET;
    }

    public static int unsafeCompare(Comparator<?> r0, Object r1, Object r2) {
        return r0.compare(r1, r2);
    }

    @Override // java.util.SortedSet, com.google.common.collect.SortedIterable
    public final Comparator<? super E> comparator() {
        return this.comparator;
    }

    public abstract ImmutableSortedSet<E> createDescendingSet();

    @Override // java.util.NavigableSet
    public ImmutableSortedSet<E> descendingSet() {
        ImmutableSortedSet<E> r0 = this.descendingSet;
        if (r0 != null) goto L6;
        ImmutableSortedSet<E> r02 = createDescendingSet();
        this.descendingSet = r02;
        r02.descendingSet = this;
        return r02;
    L6:
        return r0;
    }

    @Override // java.util.NavigableSet
    public /* bridge */ /* synthetic */ NavigableSet descendingSet() {
        return descendingSet();
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public ImmutableSortedSet<E> headSet(E r2) {
        return headSet(r2, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableSet
    public ImmutableSortedSet<E> headSet(E r1, boolean r2) {
        return headSetImpl(Preconditions.checkNotNull(r1), r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableSet
    public /* bridge */ /* synthetic */ NavigableSet headSet(Object r1, boolean r2) {
        return headSet(r1, r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableSet, java.util.SortedSet
    public /* bridge */ /* synthetic */ SortedSet headSet(Object r1) {
        return headSet(r1);
    }

    public abstract ImmutableSortedSet<E> headSetImpl(E r1, boolean r2);

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public abstract UnmodifiableIterator<E> iterator();

    @Override // java.util.NavigableSet
    @Deprecated
    public final E pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet
    @Deprecated
    public final E pollLast() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public ImmutableSortedSet<E> subSet(E r3, E r4) {
        return subSet(r3, true, r4, false);
    }

    @Override // java.util.NavigableSet
    public ImmutableSortedSet<E> subSet(E r2, boolean r3, E r4, boolean r5) {
        Preconditions.checkNotNull(r2);
        Preconditions.checkNotNull(r4);
        if (this.comparator.compare(r2, r4) > 0) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0);
        return subSetImpl(r2, r3, r4, r5);
    L5:
        r0 = false;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableSet
    public /* bridge */ /* synthetic */ NavigableSet subSet(Object r1, boolean r2, Object r3, boolean r4) {
        return subSet(r1, r2, r3, r4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableSet, java.util.SortedSet
    public /* bridge */ /* synthetic */ SortedSet subSet(Object r1, Object r2) {
        return subSet(r1, r2);
    }

    public abstract ImmutableSortedSet<E> subSetImpl(E r1, boolean r2, E r3, boolean r4);

    @Override // java.util.NavigableSet, java.util.SortedSet
    public ImmutableSortedSet<E> tailSet(E r2) {
        return tailSet(r2, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableSet
    public ImmutableSortedSet<E> tailSet(E r1, boolean r2) {
        return tailSetImpl(Preconditions.checkNotNull(r1), r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableSet
    public /* bridge */ /* synthetic */ NavigableSet tailSet(Object r1, boolean r2) {
        return tailSet(r1, r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableSet, java.util.SortedSet
    public /* bridge */ /* synthetic */ SortedSet tailSet(Object r1) {
        return tailSet(r1);
    }

    public abstract ImmutableSortedSet<E> tailSetImpl(E r1, boolean r2);

    public final int unsafeCompare(Object r1, Object r2) {
        return unsafeCompare(this.comparator, r1, r2);
    }
}
