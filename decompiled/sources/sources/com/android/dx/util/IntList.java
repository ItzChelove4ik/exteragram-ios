package com.android.dx.util;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class IntList extends MutabilityControl {
    public static final IntList EMPTY = null;
    private int size;
    private boolean sorted;
    private int[] values;

    static {
        IntList r0 = new IntList(0);
        EMPTY = r0;
        r0.setImmutable();
    }

    public IntList() {
        this(4);
    }

    public IntList(int r2) {
        super(true);
        this.values = new int[r2];     // Catch: NegativeArraySizeException -> L6
        this.size = 0;
        this.sorted = true;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("size < 0");
        throw null;
    }

    private void growIfNeeded() {
        int r0 = this.size;
        int[] r1 = this.values;
        if (r0 != r1.length) goto L6;
        int[] r2 = new int[((r0 * 3) / 2) + 10];
        System.arraycopy(r1, 0, r2, 0, r0);
        this.values = r2;
        return;
    }

    public static IntList makeImmutable(int r2) {
        IntList r0 = new IntList(1);
        r0.add(r2);
        r0.setImmutable();
        return r0;
    }

    public static IntList makeImmutable(int r2, int r3) {
        IntList r0 = new IntList(2);
        r0.add(r2);
        r0.add(r3);
        r0.setImmutable();
        return r0;
    }

    public void add(int r5) {
        throwIfImmutable();
        growIfNeeded();
        int[] r0 = this.values;
        int r1 = this.size;
        int r2 = r1 + 1;
        this.size = r2;
        r0[r1] = r5;
        if (this.sorted == false) goto L12;
        boolean r3 = true;
        if (r2 > 1) goto L7;
        return;
    L7:
        if (r5 >= r0[r1 - 1]) goto L10;
        r3 = false;
    L10:
        this.sorted = r3;
        return;
    }

    public int binarysearch(int r6) {
        int r0 = this.size;
        if (this.sorted == true) goto L12;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L11;
        if (this.values[r1] == r6) goto L8;
        r1 = r1 + 1;
        goto L5
    L8:
        return r1;
    L11:
        return -r0;
    L12:
        int r12 = -1;
        int r2 = r0;
    L14:
        if (r2 <= (r12 + 1)) goto L19;
        int r3 = ((r2 - r12) >> 1) + r12;
        if (r6 <= this.values[r3]) goto L17;
        r12 = r3;
        goto L14
    L17:
        r2 = r3;
        goto L14
    L19:
        if (r2 != r0) goto L21;
        int r5 = -r0;
    L25:
        return r5 - 1;
    L21:
        if (r6 != this.values[r2]) goto L23;
        return r2;
    L23:
        r5 = -r2;
        goto L25
    }

    public boolean contains(int r1) {
        if (indexOf(r1) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean equals(Object r6) {
        if (r6 != this) goto L6;
        return true;
    L6:
        if ((r6 instanceof IntList) == true) goto L8;
        return false;
    L8:
        IntList r62 = (IntList) r6;
        if (this.sorted == r62.sorted) goto L12;
        return false;
    L12:
        if (this.size == r62.size) goto L14;
        return false;
    L14:
        int r1 = 0;
    L16:
        if (r1 >= this.size) goto L21;
        if (this.values[r1] != r62.values[r1]) goto L19;
        r1 = r1 + 1;
        goto L16
    L19:
        return false;
    L21:
        return true;
    }

    public int get(int r2) {
        if (r2 < this.size) goto L10;
        IntList$$ExternalSyntheticBUOutline0.m("n >= size()");
        return 0;
    L10:
        return this.values[r2];
    L6:
        IntList$$ExternalSyntheticBUOutline0.m("n < 0");
        return 0;
    }

    public int hashCode() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= this.size) goto L6;
        r1 = (r1 * 31) + this.values[r0];
        r0 = r0 + 1;
        goto L4
    L6:
        return r1;
    }

    public int indexOf(int r1) {
        int r0 = binarysearch(r1);
        if (r0 < 0) goto L5;
        return r0;
    L5:
        return -1;
    }

    public void insert(int r5, int r6) {
        if (r5 > this.size) goto L16;
        growIfNeeded();
        int[] r0 = this.values;
        int r1 = r5 + 1;
        System.arraycopy(r0, r5, r0, r1, this.size - r5);
        int[] r02 = this.values;
        r02[r5] = r6;
        int r2 = this.size;
        this.size = r2 + 1;
        if (this.sorted == false) goto L13;
        if (r5 != 0) goto L8;
    L9:
        if (r5 != r2) goto L11;
    L12:
        boolean r52 = true;
    L14:
        this.sorted = r52;
        return;
    L11:
        if (r6 >= r02[r1]) goto L13;
    L8:
        if (r6 > r02[r5 - 1]) goto L9;
    L13:
        r52 = false;
        goto L14
    L16:
        IntList$$ExternalSyntheticBUOutline0.m("n > size()");
    }

    public IntList mutableCopy() {
        int r0 = this.size;
        IntList r1 = new IntList(r0);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1.add(this.values[r2]);
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    public int pop() {
        throwIfImmutable();
        this.size--;
        return get(this.size - 1);
    }

    public void pop(int r2) {
        throwIfImmutable();
        this.size -= r2;
    }

    public void removeIndex(int r4) {
        if (r4 >= this.size) goto L6;
        int[] r1 = this.values;
        System.arraycopy(r1, r4 + 1, r1, r4, (r0 - r4) - 1);
        this.size--;
        return;
    L6:
        IntList$$ExternalSyntheticBUOutline0.m("n >= size()");
    }

    public void set(int r2, int r3) {
        throwIfImmutable();
        if (r2 < this.size) goto L12;
        IntList$$ExternalSyntheticBUOutline0.m("n >= size()");
        return;
    L12:
        this.values[r2] = r3;     // Catch: ArrayIndexOutOfBoundsException -> L6
        this.sorted = false;     // Catch: ArrayIndexOutOfBoundsException -> L6
        return;
    L6:
        if (r2 < 0) goto L8;
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("n < 0");
    }

    public void shrink(int r2) {
        if (r2 >= 0) goto L4;
        f$$ExternalSyntheticBUOutline1.m("newSize < 0");
        return;
    L4:
        if (r2 > this.size) goto L7;
        throwIfImmutable();
        this.size = r2;
        return;
    L7:
        f$$ExternalSyntheticBUOutline1.m("newSize > size");
    }

    public int size() {
        return this.size;
    }

    public void sort() {
        throwIfImmutable();
        if (this.sorted == true) goto L6;
        Arrays.sort(this.values, 0, this.size);
        this.sorted = true;
        return;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder((this.size * 5) + 10);
        r0.append('{');
        int r1 = 0;
    L4:
        if (r1 >= this.size) goto L8;
        if (r1 == 0) goto L7;
        r0.append(", ");
    L7:
        r0.append(this.values[r1]);
        r1 = r1 + 1;
        goto L4
    L8:
        r0.append('}');
        return r0.toString();
    }

    public int top() {
        return get(this.size - 1);
    }
}
