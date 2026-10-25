package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
final class RegularImmutableSortedSet<E> extends ImmutableSortedSet<E> {
    static final RegularImmutableSortedSet<Comparable> NATURAL_EMPTY_SET = null;
    final transient ImmutableList<E> elements;

    static {
        NATURAL_EMPTY_SET = new RegularImmutableSortedSet(ImmutableList.of(), Ordering.natural());
    }

    public RegularImmutableSortedSet(ImmutableList<E> r1, Comparator<? super E> r2) {
        super(r2);
        this.elements = r1;
    }

    private int unsafeBinarySearch(Object r2) {
        return Collections.binarySearch(this.elements, r2, unsafeComparator());
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        return this.elements;
    }

    @Override // java.util.NavigableSet
    public E ceiling(E r2) {
        int r22 = tailIndex(r2, true);
        if (r22 != size()) goto L7;
        return null;
    L7:
        return this.elements.get(r22);
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object r2) {
        if (r2 != null) goto L10;
    L8:
        return false;
    L10:
        if (unsafeBinarySearch(r2) < 0) goto L8;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> r7) {
        if ((r7 instanceof Multiset) == false) goto L6;
        r7 = ((Multiset) r7).elementSet();
    L6:
        if (SortedIterables.hasSameComparator(comparator(), r7) == false) goto L29;
        if (r7.size() <= 1) goto L29;
        UnmodifiableIterator<E> r0 = iterator();
        Iterator<?> r72 = r7.iterator();
        if (r0.hasNext() == true) goto L13;
        return false;
    L13:
        Object r2 = r72.next();
        E r4 = r0.next();
    L31:
        int r5 = unsafeCompare(r4, r2);     // Catch: Throwable -> L30
        if (r5 < 0) goto L17;
        if (r5 == 0) goto L22;
        if (r5 <= 0) goto L31;
    L22:
        if (r72.hasNext() == false) goto L23;
        r2 = r72.next();     // Catch: Throwable -> L30
        goto L31
    L23:
        return true;
    L17:
        if (r0.hasNext() == false) goto L18;
        r4 = r0.next();     // Catch: Throwable -> L30
        goto L31
    L18:
        return false;
    L27:
        return false;
    L29:
        return super.containsAll(r7);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] r1, int r2) {
        return this.elements.copyIntoArray(r1, r2);
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public ImmutableSortedSet<E> createDescendingSet() {
        Comparator r0 = Collections.reverseOrder(this.comparator);
        if (isEmpty() == false) goto L7;
        return ImmutableSortedSet.emptySet(r0);
    L7:
        return new RegularImmutableSortedSet(this.elements.reverse(), r0);
    }

    @Override // java.util.NavigableSet
    public UnmodifiableIterator<E> descendingIterator() {
        return this.elements.reverse().iterator();
    }

    @Override // java.util.NavigableSet
    public /* bridge */ /* synthetic */ Iterator descendingIterator() {
        return descendingIterator();
    }

    @Override // com.google.common.collect.ImmutableSet, java.util.Collection, java.util.Set
    public boolean equals(Object r6) {
        if (r6 != this) goto L6;
        return true;
    L6:
        if ((r6 instanceof Set) == true) goto L8;
        return false;
    L8:
        Set r62 = (Set) r6;
        if (size() == r62.size()) goto L12;
        return false;
    L12:
        if (isEmpty() == false) goto L15;
        return true;
    L15:
        if (SortedIterables.hasSameComparator(this.comparator, r62) == false) goto L28;
        Iterator<E> r63 = r62.iterator();
        UnmodifiableIterator<E> r1 = iterator();     // Catch: Throwable -> L26
    L18:
        if (r1.hasNext() == false) goto L25;
        E r3 = r1.next();     // Catch: Throwable -> L26
        E r4 = r63.next();     // Catch: Throwable -> L26
        if (r4 == null) goto L24;
        if (unsafeCompare(r3, r4) == 0) goto L18;
    L24:
        return false;
    L25:
        return true;
    L26:
        return false;
    L28:
        return containsAll(r62);
    }

    @Override // java.util.SortedSet
    public E first() {
        if (isEmpty() == false) goto L5;
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        return this.elements.get(0);
    }

    @Override // java.util.NavigableSet
    public E floor(E r2) {
        int r22 = headIndex(r2, true) - 1;
        if (r22 != (-1)) goto L7;
        return null;
    L7:
        return this.elements.get(r22);
    }

    public RegularImmutableSortedSet<E> getSubSet(int r3, int r4) {
        if (r3 == 0) goto L4;
    L6:
        if (r3 >= r4) goto L10;
        return new RegularImmutableSortedSet(this.elements.subList(r3, r4), this.comparator);
    L10:
        return ImmutableSortedSet.emptySet(this.comparator);
    L4:
        if (r4 != size()) goto L6;
        return this;
    }

    public int headIndex(E r2, boolean r3) {
        int r1 = Collections.binarySearch(this.elements, Preconditions.checkNotNull(r2), comparator());
        if (r1 < 0) goto L8;
        if (r3 == true) goto L6;
        return r1;
    L6:
        return r1 + 1;
    L8:
        return ~r1;
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public ImmutableSortedSet<E> headSetImpl(E r2, boolean r3) {
        return getSubSet(0, headIndex(r2, r3));
    }

    @Override // java.util.NavigableSet
    public E higher(E r2) {
        int r22 = tailIndex(r2, false);
        if (r22 != size()) goto L7;
        return null;
    L7:
        return this.elements.get(r22);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public Object[] internalArray() {
        return this.elements.internalArray();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int internalArrayEnd() {
        return this.elements.internalArrayEnd();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int internalArrayStart() {
        return this.elements.internalArrayStart();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return this.elements.isPartialView();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public UnmodifiableIterator<E> iterator() {
        return this.elements.iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // java.util.SortedSet
    public E last() {
        if (isEmpty() == false) goto L5;
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        return this.elements.get(size() - 1);
    }

    @Override // java.util.NavigableSet
    public E lower(E r2) {
        int r22 = headIndex(r2, false) - 1;
        if (r22 != (-1)) goto L7;
        return null;
    L7:
        return this.elements.get(r22);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.elements.size();
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public ImmutableSortedSet<E> subSetImpl(E r1, boolean r2, E r3, boolean r4) {
        return tailSetImpl(r1, r2).headSetImpl(r3, r4);
    }

    public int tailIndex(E r2, boolean r3) {
        int r1 = Collections.binarySearch(this.elements, Preconditions.checkNotNull(r2), comparator());
        if (r1 < 0) goto L9;
        if (r3 == false) goto L7;
        return r1;
    L7:
        return r1 + 1;
    L9:
        return ~r1;
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public ImmutableSortedSet<E> tailSetImpl(E r1, boolean r2) {
        return getSubSet(tailIndex(r1, r2), size());
    }

    public Comparator<Object> unsafeComparator() {
        return this.comparator;
    }
}
