package com.google.protobuf;

import com.android.dx.util.IntList$$ExternalSyntheticBUOutline0;
import com.google.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
final class BooleanArrayList extends AbstractProtobufList<Boolean> implements Internal.BooleanList, RandomAccess, PrimitiveNonBoxingCollection {
    private static final BooleanArrayList EMPTY_LIST = null;
    private boolean[] array;
    private int size;

    static {
        BooleanArrayList r0 = new BooleanArrayList(new boolean[0], 0);
        EMPTY_LIST = r0;
        r0.makeImmutable();
    }

    private BooleanArrayList(boolean[] r1, int r2) {
        this.array = r1;
        this.size = r2;
    }

    private void addBoolean(int r5, boolean r6) {
        ensureIsMutable();
        if (r5 < 0) goto L12;
        int r0 = this.size;
        if (r5 > r0) goto L12;
        boolean[] r1 = this.array;
        if (r0 >= r1.length) goto L9;
        System.arraycopy(r1, r5, r1, r5 + 1, r0 - r5);
    L10:
        this.array[r5] = r6;
        this.size++;
        ((AbstractList) this).modCount++;
        return;
    L9:
        boolean[] r02 = new boolean[((r0 * 3) / 2) + 1];
        System.arraycopy(r1, 0, r02, 0, r5);
        System.arraycopy(this.array, r5, r02, r5 + 1, this.size - r5);
        this.array = r02;
    L12:
        IntList$$ExternalSyntheticBUOutline0.m(makeOutOfBoundsExceptionMessage(r5));
    }

    public static BooleanArrayList emptyList() {
        return EMPTY_LIST;
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

    public void add(int r1, Boolean r2) {
        addBoolean(r1, r2.booleanValue());
    }

    @Override // java.util.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ void add(int r1, Object r2) {
        add(r1, (Boolean) r2);
    }

    public boolean add(Boolean r1) {
        addBoolean(r1.booleanValue());
        return true;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean add(Object r1) {
        return add((Boolean) r1);
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends Boolean> r6) {
        ensureIsMutable();
        Internal.checkNotNull(r6);
        if ((r6 instanceof BooleanArrayList) == false) goto L5;
        BooleanArrayList r62 = (BooleanArrayList) r6;
        int r0 = r62.size;
        if (r0 != 0) goto L9;
        return false;
    L9:
        int r2 = this.size;
        if ((Integer.MAX_VALUE - r2) < r0) goto L17;
        int r22 = r2 + r0;
        boolean[] r02 = this.array;
        if (r22 <= r02.length) goto L14;
        this.array = Arrays.copyOf(r02, r22);
    L14:
        System.arraycopy(r62.array, 0, this.array, this.size, r62.size);
        this.size = r22;
        ((AbstractList) this).modCount++;
        return true;
    L17:
        throw new OutOfMemoryError();
    L5:
        return super.addAll(r6);
    }

    public void addBoolean(boolean r5) {
        ensureIsMutable();
        int r0 = this.size;
        boolean[] r1 = this.array;
        if (r0 != r1.length) goto L5;
        boolean[] r2 = new boolean[((r0 * 3) / 2) + 1];
        System.arraycopy(r1, 0, r2, 0, r0);
        this.array = r2;
    L5:
        boolean[] r02 = this.array;
        int r12 = this.size;
        this.size = r12 + 1;
        r02[r12] = r5;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object r1) {
        if (indexOf(r1) == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object r6) {
        if (this != r6) goto L6;
        return true;
    L6:
        if ((r6 instanceof BooleanArrayList) == false) goto L8;
        BooleanArrayList r62 = (BooleanArrayList) r6;
        if (this.size == r62.size) goto L12;
        return false;
    L12:
        boolean[] r63 = r62.array;
        int r1 = 0;
    L14:
        if (r1 >= this.size) goto L19;
        if (this.array[r1] != r63[r1]) goto L17;
        r1 = r1 + 1;
        goto L14
    L17:
        return false;
    L19:
        return true;
    L8:
        return super.equals(r6);
    }

    @Override // java.util.AbstractList, java.util.List
    public Boolean get(int r1) {
        return Boolean.valueOf(getBoolean(r1));
    }

    @Override // java.util.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int r1) {
        return get(r1);
    }

    public boolean getBoolean(int r1) {
        ensureIndexInRange(r1);
        return this.array[r1];
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int r0 = 1;
        int r1 = 0;
    L4:
        if (r1 >= this.size) goto L6;
        r0 = (r0 * 31) + Internal.hashBoolean(this.array[r1]);
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object r5) {
        if ((r5 instanceof Boolean) == true) goto L5;
        return -1;
    L5:
        boolean r52 = ((Boolean) r5).booleanValue();
        int r0 = size();
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L11;
        if (this.array[r2] == r52) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return r2;
    L11:
        return -1;
    }

    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
    public Internal.ProtobufList<Boolean> mutableCopyWithCapacity(int r3) {
        if (r3 >= this.size) goto L5;
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        return new BooleanArrayList(Arrays.copyOf(this.array, r3), this.size);
    }

    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
    /* renamed from: mutableCopyWithCapacity, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ Internal.ProtobufList<Boolean> mutableCopyWithCapacity2(int r1) {
        return mutableCopyWithCapacity(r1);
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Boolean remove(int r5) {
        ensureIsMutable();
        ensureIndexInRange(r5);
        boolean[] r0 = this.array;
        boolean r1 = r0[r5];
        if (r5 >= (this.size - 1)) goto L5;
        System.arraycopy(r0, r5 + 1, r0, r5, (r2 - r5) - 1);
    L5:
        this.size--;
        ((AbstractList) this).modCount++;
        return Boolean.valueOf(r1);
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object remove(int r1) {
        return remove(r1);
    }

    @Override // java.util.AbstractList
    public void removeRange(int r3, int r4) {
        ensureIsMutable();
        if (r4 < r3) goto L6;
        boolean[] r0 = this.array;
        System.arraycopy(r0, r4, r0, r3, this.size - r4);
        this.size -= r4 - r3;
        ((AbstractList) this).modCount++;
        return;
    L6:
        IntList$$ExternalSyntheticBUOutline0.m("toIndex < fromIndex");
    }

    public Boolean set(int r1, Boolean r2) {
        return Boolean.valueOf(setBoolean(r1, r2.booleanValue()));
    }

    @Override // java.util.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object set(int r1, Object r2) {
        return set(r1, (Boolean) r2);
    }

    public boolean setBoolean(int r2, boolean r3) {
        ensureIsMutable();
        ensureIndexInRange(r2);
        boolean[] r1 = this.array;
        boolean r0 = r1[r2];
        r1[r2] = r3;
        return r0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }
}
