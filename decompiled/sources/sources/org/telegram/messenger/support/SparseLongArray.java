package org.telegram.messenger.support;

/* loaded from: classes3.dex */
public class SparseLongArray implements Cloneable {
    private int[] mKeys;
    private int mSize;
    private long[] mValues;

    public SparseLongArray() {
        this(10);
    }

    public SparseLongArray(int r2) {
        int r22 = ArrayUtils.idealLongArraySize(r2);
        this.mKeys = new int[r22];
        this.mValues = new long[r22];
        this.mSize = 0;
    }

    private static int binarySearch(int[] r5, int r6, int r7, long r8) {
        int r72 = r7 + r6;
        int r62 = r6 - 1;
        int r1 = r72;
    L4:
        if ((r1 - r62) <= 1) goto L9;
        int r2 = (r1 + r62) / 2;
        if (r5[r2] < r8) goto L7;
        r1 = r2;
        goto L4
    L7:
        r62 = r2;
        goto L4
    L9:
        if (r1 != r72) goto L13;
        return ~r72;
    L13:
        if (r5[r1] != r8) goto L16;
        return r1;
    L16:
        return ~r1;
    }

    private void growKeyAndValueArrays(int r5) {
        int r52 = ArrayUtils.idealLongArraySize(r5);
        int[] r0 = new int[r52];
        long[] r53 = new long[r52];
        int[] r1 = this.mKeys;
        System.arraycopy(r1, 0, r0, 0, r1.length);
        long[] r12 = this.mValues;
        System.arraycopy(r12, 0, r53, 0, r12.length);
        this.mKeys = r0;
        this.mValues = r53;
    }

    public void append(int r4, long r5) {
        int r0 = this.mSize;
        if (r0 == 0) goto L9;
        if (r4 > this.mKeys[r0 - 1]) goto L9;
        put(r4, r5);
        return;
    L9:
        if (r0 < this.mKeys.length) goto L11;
        growKeyAndValueArrays(r0 + 1);
    L11:
        this.mKeys[r0] = r4;
        this.mValues[r0] = r5;
        this.mSize = r0 + 1;
    }

    public void clear() {
        this.mSize = 0;
    }

    public /* bridge */ /* synthetic */ Object clone() {
        return m5173clone();
    }

    /* renamed from: clone, reason: collision with other method in class */
    public SparseLongArray m5173clone() {
        SparseLongArray r1 = (SparseLongArray) super.clone();     // Catch: CloneNotSupportedException -> L8
        r1.mKeys = (int[]) this.mKeys.clone();     // Catch: CloneNotSupportedException -> L6
        r1.mValues = (long[]) this.mValues.clone();     // Catch: CloneNotSupportedException -> L6
        return r1;
    L7:
        return r1;
    L13:
        return null;
    }

    public void delete(int r5) {
        int r52 = binarySearch(this.mKeys, 0, this.mSize, r5);
        if (r52 < 0) goto L6;
        removeAt(r52);
        return;
    }

    public long get(int r3) {
        return get(r3, 0);
    }

    public long get(int r5, long r6) {
        int r52 = binarySearch(this.mKeys, 0, this.mSize, r5);
        if (r52 >= 0) goto L6;
        return r6;
    L6:
        return this.mValues[r52];
    }

    public int indexOfKey(int r4) {
        return binarySearch(this.mKeys, 0, this.mSize, r4);
    }

    public int indexOfValue(long r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.mSize) goto L9;
        if (this.mValues[r0] == r5) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    public int keyAt(int r1) {
        return this.mKeys[r1];
    }

    public void put(int r6, long r7) {
        int r0 = binarySearch(this.mKeys, 0, this.mSize, r6);
        if (r0 < 0) goto L6;
        this.mValues[r0] = r7;
        return;
    L6:
        int r02 = ~r0;
        int r1 = this.mSize;
        if (r1 < this.mKeys.length) goto L9;
        growKeyAndValueArrays(r1 + 1);
    L9:
        int r12 = this.mSize;
        if ((r12 - r02) == 0) goto L12;
        int[] r2 = this.mKeys;
        int r3 = r02 + 1;
        System.arraycopy(r2, r02, r2, r3, r12 - r02);
        long[] r13 = this.mValues;
        System.arraycopy(r13, r02, r13, r3, this.mSize - r02);
    L12:
        this.mKeys[r02] = r6;
        this.mValues[r02] = r7;
        this.mSize++;
    }

    public void removeAt(int r4) {
        int[] r0 = this.mKeys;
        int r1 = r4 + 1;
        System.arraycopy(r0, r1, r0, r4, this.mSize - r1);
        long[] r02 = this.mValues;
        System.arraycopy(r02, r1, r02, r4, this.mSize - r1);
        this.mSize--;
    }

    public int size() {
        return this.mSize;
    }

    public long valueAt(int r3) {
        return this.mValues[r3];
    }
}
