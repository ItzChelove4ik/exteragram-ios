package com.google.zxing.common;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Arrays;

/* loaded from: classes5.dex */
public final class BitMatrix implements Cloneable {
    private int[] bits;
    private int height;
    private int rowSize;
    private int width;

    public BitMatrix(int r1) {
        this(r1, r1);
    }

    public BitMatrix(int r2, int r3) {
        if (r2 < 1) goto L7;
        if (r3 < 1) goto L7;
        this.width = r2;
        this.height = r3;
        int r22 = (r2 + 31) / 32;
        this.rowSize = r22;
        this.bits = new int[r22 * r3];
        return;
    L7:
        f$$ExternalSyntheticBUOutline1.m("Both dimensions must be greater than 0");
        throw null;
    }

    private BitMatrix(int r1, int r2, int r3, int[] r4) {
        this.width = r1;
        this.height = r2;
        this.rowSize = r3;
        this.bits = r4;
    }

    private String buildToString(String r6, String r7, String r8) {
        StringBuilder r0 = new StringBuilder(this.height * (this.width + 1));
        int r2 = 0;
    L4:
        if (r2 >= this.height) goto L15;
        int r3 = 0;
    L7:
        if (r3 >= this.width) goto L13;
        if (get(r3, r2) == false) goto L11;
        String r4 = r6;
    L12:
        r0.append(r4);
        r3 = r3 + 1;
        goto L7
    L11:
        r4 = r7;
        goto L12
    L13:
        r0.append(r8);
        r2 = r2 + 1;
        goto L4
    L15:
        return r0.toString();
    }

    public BitMatrix clone() {
        return new BitMatrix(this.width, this.height, this.rowSize, (int[]) this.bits.clone());
    }

    /* renamed from: clone, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m2006clone() {
        return clone();
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof BitMatrix) == true) goto L5;
        return false;
    L5:
        BitMatrix r42 = (BitMatrix) r4;
        if (this.width == r42.width) goto L8;
    L15:
        return false;
    L8:
        if (this.height != r42.height) goto L15;
        if (this.rowSize != r42.rowSize) goto L15;
        if (Arrays.equals(this.bits, r42.bits) == false) goto L15;
        return true;
    }

    public void flip(int r3, int r4) {
        int r42 = (r4 * this.rowSize) + (r3 / 32);
        int[] r2 = this.bits;
        int r32 = 1 << (r3 & 31);
        r2[r42] = r32 ^ r2[r42];
    }

    public boolean get(int r2, int r3) {
        int r32 = (r3 * this.rowSize) + (r2 / 32);
        if (((this.bits[r32] >>> (r2 & 31)) & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public int[] getBottomRightOnBit() {
        int r0 = this.bits.length - 1;
    L3:
        if (r0 < 0) goto L7;
        if (this.bits[r0] != 0) goto L7;
        r0 = r0 - 1;
    L7:
        if (r0 >= 0) goto L10;
        return null;
    L10:
        int r1 = this.rowSize;
        int r2 = r0 / r1;
        int r12 = (r0 % r1) * 32;
        int r4 = this.bits[r0];
        int r02 = 31;
    L12:
        if ((r4 >>> r02) != 0) goto L15;
        r02 = r02 - 1;
        goto L12
    L15:
        return new int[]{r12 + r02, r2};
    }

    public int getHeight() {
        return this.height;
    }

    public int[] getTopLeftOnBit() {
        int r0 = 0;
        int r1 = 0;
    L3:
        int[] r2 = this.bits;
        if (r1 >= r2.length) goto L9;
        if (r2[r1] != 0) goto L9;
        r1 = r1 + 1;
    L9:
        if (r1 != r2.length) goto L12;
        return null;
    L12:
        int r4 = this.rowSize;
        int r3 = r1 / r4;
        int r42 = (r1 % r4) * 32;
    L14:
        if ((r2[r1] << (31 - r0)) != 0) goto L17;
        r0 = r0 + 1;
        goto L14
    L17:
        return new int[]{r42 + r0, r3};
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        int r0 = this.width;
        return (((((((r0 * 31) + r0) * 31) + this.height) * 31) + this.rowSize) * 31) + Arrays.hashCode(this.bits);
    }

    public void set(int r3, int r4) {
        int r42 = (r4 * this.rowSize) + (r3 / 32);
        int[] r2 = this.bits;
        int r32 = 1 << (r3 & 31);
        r2[r42] = r32 | r2[r42];
    }

    public void setRegion(int r8, int r9, int r10, int r11) {
        if (r9 < 0) goto L21;
        if (r8 < 0) goto L21;
        if (r11 < 1) goto L19;
        if (r10 < 1) goto L19;
        int r102 = r10 + r8;
        int r112 = r11 + r9;
        if (r112 <= this.height) goto L10;
    L17:
        f$$ExternalSyntheticBUOutline1.m("The region must fit inside the matrix");
        return;
    L10:
        if (r102 > this.width) goto L17;
    L11:
        if (r9 >= r112) goto L16;
        int r1 = this.rowSize * r9;
        int r2 = r8;
    L13:
        if (r2 >= r102) goto L15;
        int[] r3 = this.bits;
        int r4 = (r2 / 32) + r1;
        r3[r4] = r3[r4] | (1 << (r2 & 31));
        r2 = r2 + 1;
        goto L13
    L15:
        r9 = r9 + 1;
        goto L11
    L16:
        return;
    L19:
        f$$ExternalSyntheticBUOutline1.m("Height and width must be at least 1");
        return;
    L21:
        f$$ExternalSyntheticBUOutline1.m("Left and top must be nonnegative");
    }

    public String toString() {
        return toString("X ", "  ");
    }

    public String toString(String r2, String r3) {
        return buildToString(r2, r3, "\n");
    }
}
