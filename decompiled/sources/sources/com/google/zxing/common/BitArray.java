package com.google.zxing.common;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Arrays;

/* loaded from: classes5.dex */
public final class BitArray implements Cloneable {
    private static final int[] EMPTY_BITS = null;
    private int[] bits;
    private int size;

    static {
        EMPTY_BITS = new int[0];
    }

    public BitArray() {
        this.size = 0;
        this.bits = EMPTY_BITS;
    }

    public BitArray(int[] r1, int r2) {
        this.bits = r1;
        this.size = r2;
    }

    private void ensureCapacity(int r4) {
        if (r4 <= (this.bits.length * 32)) goto L6;
        int[] r42 = makeArray((int) Math.ceil(r4 / 0.75f));
        int[] r0 = this.bits;
        System.arraycopy(r0, 0, r42, 0, r0.length);
        this.bits = r42;
        return;
    }

    private static int[] makeArray(int r0) {
        return new int[(r0 + 31) / 32];
    }

    public void appendBit(boolean r5) {
        ensureCapacity(this.size + 1);
        if (r5 == false) goto L5;
        int[] r52 = this.bits;
        int r0 = this.size;
        int r2 = r0 / 32;
        r52[r2] = (1 << (r0 & 31)) | r52[r2];
    L5:
        this.size++;
    }

    public void appendBitArray(BitArray r4) {
        int r0 = r4.size;
        ensureCapacity(this.size + r0);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        appendBit(r4.get(r1));
        r1 = r1 + 1;
        goto L3
    }

    public void appendBits(int r7, int r8) {
        if (r8 >= 0) goto L4;
    L13:
        f$$ExternalSyntheticBUOutline1.m("Num bits must be between 0 and 32");
        return;
    L4:
        if (r8 > 32) goto L13;
        int r0 = this.size;
        ensureCapacity(r0 + r8);
        int r82 = r8 - 1;
    L6:
        if (r82 < 0) goto L11;
        if (((1 << r82) & r7) == 0) goto L10;
        int[] r2 = this.bits;
        int r3 = r0 / 32;
        r2[r3] = r2[r3] | (1 << (r0 & 31));
    L10:
        r0 = r0 + 1;
        r82 = r82 - 1;
        goto L6
    L11:
        this.size = r0;
    }

    public BitArray clone() {
        return new BitArray((int[]) this.bits.clone(), this.size);
    }

    /* renamed from: clone, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m2005clone() {
        return clone();
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof BitArray) == true) goto L5;
        return false;
    L5:
        BitArray r42 = (BitArray) r4;
        if (this.size == r42.size) goto L8;
    L11:
        return false;
    L8:
        if (Arrays.equals(this.bits, r42.bits) == false) goto L11;
        return true;
    }

    public boolean get(int r2) {
        if ((this.bits[r2 / 32] & (1 << (r2 & 31))) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public int getSize() {
        return this.size;
    }

    public int getSizeInBytes() {
        return (this.size + 7) / 8;
    }

    public int hashCode() {
        return (this.size * 31) + Arrays.hashCode(this.bits);
    }

    public void toBytes(int r7, byte[] r8, int r9, int r10) {
        int r1 = 0;
    L3:
        if (r1 >= r10) goto L12;
        int r2 = 0;
        int r3 = 0;
    L6:
        if (r2 >= 8) goto L11;
        if (get(r7) == false) goto L10;
        r3 = r3 | (1 << (7 - r2));
    L10:
        r7 = r7 + 1;
        r2 = r2 + 1;
        goto L6
    L11:
        r8[r9 + r1] = (byte) r3;
        r1 = r1 + 1;
        goto L3
    }

    public String toString() {
        int r1 = this.size;
        StringBuilder r0 = new StringBuilder((r1 + (r1 / 8)) + 1);
        int r12 = 0;
    L4:
        if (r12 >= this.size) goto L14;
        if ((r12 & 7) != 0) goto L9;
        r0.append(' ');
    L9:
        if (get(r12) == false) goto L11;
        char r2 = 'X';
    L12:
        r0.append(r2);
        r12 = r12 + 1;
        goto L4
    L11:
        r2 = '.';
        goto L12
    L14:
        return r0.toString();
    }

    public void xor(BitArray r5) {
        if (this.size != r5.size) goto L9;
        int r0 = 0;
    L5:
        int[] r1 = this.bits;
        if (r0 >= r1.length) goto L8;
        r1[r0] = r1[r0] ^ r5.bits[r0];
        r0 = r0 + 1;
        goto L5
    L8:
        return;
    L9:
        f$$ExternalSyntheticBUOutline1.m("Sizes don't match");
    }
}
