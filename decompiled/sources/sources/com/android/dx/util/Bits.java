package com.android.dx.util;

/* loaded from: classes.dex */
public final class Bits {
    private Bits() {
    }

    public static boolean anyInRange(int[] r0, int r1, int r2) {
        int r02 = findFirst(r0, r1);
        if (r02 < 0) goto L7;
        if (r02 >= r2) goto L9;
        return true;
    L9:
        return false;
    L7:
        return false;
    }

    public static int bitCount(int[] r4) {
        int r0 = r4.length;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r2 = r2 + Integer.bitCount(r4[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r2;
    }

    public static void clear(int[] r2, int r3) {
        int r0 = r3 >> 5;
        int r1 = r2[r0];
        r2[r0] = (~(1 << (r3 & 31))) & r1;
    }

    public static int findFirst(int r1, int r2) {
        int r12 = Integer.numberOfTrailingZeros(r1 & (~((1 << r2) - 1)));
        if (r12 != 32) goto L6;
        return -1;
    L6:
        return r12;
    }

    public static int findFirst(int[] r3, int r4) {
        int r0 = r3.length;
        int r1 = r4 & 31;
        int r42 = r4 >> 5;
    L3:
        if (r42 >= r0) goto L11;
        int r2 = r3[r42];
        if (r2 == 0) goto L10;
        int r12 = findFirst(r2, r1);
        if (r12 < 0) goto L10;
        return (r42 << 5) + r12;
    L10:
        r42 = r42 + 1;
        r1 = 0;
        goto L3
    L11:
        return -1;
    }

    public static boolean get(int[] r2, int r3) {
        int r0 = r3 >> 5;
        int r22 = r2[r0];
        if ((r22 & (1 << (r3 & 31))) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public static int getMax(int[] r0) {
        return r0.length * 32;
    }

    public static boolean isEmpty(int[] r4) {
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        if (r4[r2] != 0) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return false;
    L8:
        return true;
    }

    public static int[] makeBitSet(int r0) {
        return new int[(r0 + 31) >> 5];
    }

    public static void or(int[] r3, int[] r4) {
        int r0 = 0;
    L4:
        if (r0 >= r4.length) goto L6;
        r3[r0] = r3[r0] | r4[r0];
        r0 = r0 + 1;
        goto L4
    }

    public static void set(int[] r2, int r3) {
        int r0 = r3 >> 5;
        r2[r0] = (1 << (r3 & 31)) | r2[r0];
    }

    public static void set(int[] r2, int r3, boolean r4) {
        int r0 = r3 >> 5;
        int r32 = 1 << (r3 & 31);
        if (r4 == false) goto L6;
        r2[r0] = r32 | r2[r0];
        return;
    L6:
        int r42 = r2[r0];
        r2[r0] = (~r32) & r42;
    }

    public static String toHuman(int[] r5) {
        StringBuilder r0 = new StringBuilder("{");
        int r1 = r5.length * 32;
        int r2 = 0;
        boolean r3 = false;
    L3:
        if (r2 >= r1) goto L10;
        if (get(r5, r2) == false) goto L9;
        if (r3 == false) goto L8;
        r0.append(',');
    L8:
        r0.append(r2);
        r3 = true;
    L9:
        r2 = r2 + 1;
        goto L3
    L10:
        r0.append('}');
        return r0.toString();
    }
}
