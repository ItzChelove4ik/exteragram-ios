package org.telegram.messenger.support;

/* loaded from: classes.dex */
public class LongSparseIntArray implements Cloneable {
    private long[] mKeys;
    private int mSize;
    private int[] mValues;

    public LongSparseIntArray() {
        this(10);
    }

    public LongSparseIntArray(int r2) {
        int r22 = ArrayUtils.idealLongArraySize(r2);
        this.mKeys = new long[r22];
        this.mValues = new int[r22];
        this.mSize = 0;
    }

    private static int binarySearch(long[] r5, int r6, int r7, long r8) {
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
        long[] r0 = new long[r52];
        int[] r53 = new int[r52];
        long[] r1 = this.mKeys;
        System.arraycopy(r1, 0, r0, 0, r1.length);
        int[] r12 = this.mValues;
        System.arraycopy(r12, 0, r53, 0, r12.length);
        this.mKeys = r0;
        this.mValues = r53;
    }

    public void append(long r5, int r7) {
        int r0 = this.mSize;
        if (r0 == 0) goto L9;
        if (r5 > this.mKeys[r0 - 1]) goto L9;
        put(r5, r7);
        return;
    L9:
        if (r0 < this.mKeys.length) goto L11;
        growKeyAndValueArrays(r0 + 1);
    L11:
        this.mKeys[r0] = r5;
        this.mValues[r0] = r7;
        this.mSize = r0 + 1;
    }

    public void clear() {
        this.mSize = 0;
    }

    public /* bridge */ /* synthetic */ Object clone() {
        return m5171clone();
    }

    /* renamed from: clone, reason: collision with other method in class */
    public LongSparseIntArray m5171clone() {
        LongSparseIntArray r1 = (LongSparseIntArray) super.clone();     // Catch: CloneNotSupportedException -> L8
        r1.mKeys = (long[]) this.mKeys.clone();     // Catch: CloneNotSupportedException -> L6
        r1.mValues = (int[]) this.mValues.clone();     // Catch: CloneNotSupportedException -> L6
        return r1;
    L7:
        return r1;
    L13:
        return null;
    }

    public void delete(long r4) {
        int r42 = binarySearch(this.mKeys, 0, this.mSize, r4);
        if (r42 < 0) goto L6;
        removeAt(r42);
        return;
    }

    public int get(long r2) {
        return get(r2, 0);
    }

    public int get(long r4, int r6) {
        int r42 = binarySearch(this.mKeys, 0, this.mSize, r4);
        if (r42 >= 0) goto L6;
        return r6;
    L6:
        return this.mValues[r42];
    }

    public int indexOfKey(long r3) {
        return binarySearch(this.mKeys, 0, this.mSize, r3);
    }

    public int indexOfValue(long r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.mSize) goto L9;
        if (this.mValues[r0] == r4) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    public long keyAt(int r3) {
        return this.mKeys[r3];
    }

    public void put(long r5, int r7) {
        int r0 = binarySearch(this.mKeys, 0, this.mSize, r5);
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
        long[] r2 = this.mKeys;
        int r3 = r02 + 1;
        System.arraycopy(r2, r02, r2, r3, r12 - r02);
        int[] r13 = this.mValues;
        System.arraycopy(r13, r02, r13, r3, this.mSize - r02);
    L12:
        this.mKeys[r02] = r5;
        this.mValues[r02] = r7;
        this.mSize++;
    }

    public void removeAt(int r4) {
        long[] r0 = this.mKeys;
        int r1 = r4 + 1;
        System.arraycopy(r0, r1, r0, r4, this.mSize - r1);
        int[] r02 = this.mValues;
        System.arraycopy(r02, r1, r02, r4, this.mSize - r1);
        this.mSize--;
    }

    public int size() {
        return this.mSize;
    }

    public int valueAt(int r1) {
        return this.mValues[r1];
    }
}
