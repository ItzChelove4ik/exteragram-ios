package com.google.common.collect;

import java.util.Iterator;

/* loaded from: classes.dex */
final class RegularImmutableSet<E> extends ImmutableSet<E> {
    static final RegularImmutableSet<Object> EMPTY = null;
    private static final Object[] EMPTY_ARRAY = null;
    final transient Object[] elements;
    private final transient int hashCode;
    private final transient int mask;
    private final transient int size;
    final transient Object[] table;

    static {
        Object[] r2 = new Object[0];
        EMPTY_ARRAY = r2;
        EMPTY = new RegularImmutableSet(r2, 0, r2, 0, 0);
    }

    public RegularImmutableSet(Object[] r1, int r2, Object[] r3, int r4, int r5) {
        this.elements = r1;
        this.hashCode = r2;
        this.table = r3;
        this.mask = r4;
        this.size = r5;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object r5) {
        Object[] r0 = this.table;
        if (r5 != null) goto L5;
    L16:
        return false;
    L5:
        if (r0.length == 0) goto L16;
        int r2 = Hashing.smearedHash(r5);
    L8:
        int r22 = r2 & this.mask;
        Object r3 = r0[r22];
        if (r3 == null) goto L10;
        if (r3.equals(r5) == true) goto L13;
        r2 = r22 + 1;
        goto L8
    L13:
        return true;
    L10:
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] r4, int r5) {
        System.arraycopy(this.elements, 0, r4, r5, this.size);
        return r5 + this.size;
    }

    @Override // com.google.common.collect.ImmutableSet
    public ImmutableList<E> createAsList() {
        return ImmutableList.asImmutableList(this.elements, this.size);
    }

    @Override // com.google.common.collect.ImmutableSet, java.util.Collection, java.util.Set
    public int hashCode() {
        return this.hashCode;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public Object[] internalArray() {
        return this.elements;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int internalArrayEnd() {
        return this.size;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int internalArrayStart() {
        return 0;
    }

    @Override // com.google.common.collect.ImmutableSet
    public boolean isHashCodeFast() {
        return true;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public UnmodifiableIterator<E> iterator() {
        return asList().iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.size;
    }
}
