package com.google.protobuf;

import com.android.dx.util.IntList$$ExternalSyntheticBUOutline0;
import com.google.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
final class ProtobufArrayList<E> extends AbstractProtobufList<E> implements RandomAccess {
    private static final ProtobufArrayList<Object> EMPTY_LIST = null;
    private E[] array;
    private int size;

    static {
        ProtobufArrayList<Object> r0 = new ProtobufArrayList(new Object[0], 0);
        EMPTY_LIST = r0;
        r0.makeImmutable();
    }

    private ProtobufArrayList(E[] r1, int r2) {
        this.array = r1;
        this.size = r2;
    }

    private static <E> E[] createArray(int r0) {
        return (E[]) new Object[r0];
    }

    public static <E> ProtobufArrayList<E> emptyList() {
        return (ProtobufArrayList<E>) EMPTY_LIST;
    }

    private void ensureIndexInRange(int r2) {
        if (r2 >= 0) goto L4;
    L6:
        IntList$$ExternalSyntheticBUOutline0.m(makeOutOfBoundsExceptionMessage(r2));
        return;
    L4:
        if (r2 >= this.size) goto L6;
    }

    private String makeOutOfBoundsExceptionMessage(int r3) {
        return "Index:" + r3 + ", Size:" + this.size;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int r5, E r6) {
        ensureIsMutable();
        if (r5 < 0) goto L12;
        int r0 = this.size;
        if (r5 > r0) goto L12;
        E[] r1 = this.array;
        if (r0 >= r1.length) goto L9;
        System.arraycopy(r1, r5, r1, r5 + 1, r0 - r5);
    L10:
        this.array[r5] = r6;
        this.size++;
        ((AbstractList) this).modCount++;
        return;
    L9:
        E[] r02 = (E[]) createArray(((r0 * 3) / 2) + 1);
        System.arraycopy(this.array, 0, r02, 0, r5);
        System.arraycopy(this.array, r5, r02, r5 + 1, this.size - r5);
        this.array = r02;
    L12:
        IntList$$ExternalSyntheticBUOutline0.m(makeOutOfBoundsExceptionMessage(r5));
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E r5) {
        ensureIsMutable();
        int r0 = this.size;
        E[] r1 = this.array;
        if (r0 != r1.length) goto L5;
        this.array = (E[]) Arrays.copyOf(r1, ((r0 * 3) / 2) + 1);
    L5:
        E[] r02 = this.array;
        int r12 = this.size;
        this.size = r12 + 1;
        r02[r12] = r5;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int r1) {
        ensureIndexInRange(r1);
        return this.array[r1];
    }

    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
    public /* bridge */ /* synthetic */ Internal.ProtobufList mutableCopyWithCapacity(int r1) {
        return mutableCopyWithCapacity(r1);
    }

    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
    public ProtobufArrayList<E> mutableCopyWithCapacity(int r2) {
        if (r2 >= this.size) goto L5;
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        return new ProtobufArrayList(Arrays.copyOf(this.array, r2), this.size);
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public E remove(int r5) {
        ensureIsMutable();
        ensureIndexInRange(r5);
        E[] r0 = this.array;
        E r1 = r0[r5];
        if (r5 >= (this.size - 1)) goto L5;
        System.arraycopy(r0, r5 + 1, r0, r5, (r2 - r5) - 1);
    L5:
        this.size--;
        ((AbstractList) this).modCount++;
        return r1;
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int r3, E r4) {
        ensureIsMutable();
        ensureIndexInRange(r3);
        E[] r0 = this.array;
        E r1 = r0[r3];
        r0[r3] = r4;
        ((AbstractList) this).modCount++;
        return r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }
}
