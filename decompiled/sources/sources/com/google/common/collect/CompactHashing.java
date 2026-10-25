package com.google.common.collect;

import java.util.Arrays;
import java.util.Objects;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import kotlin.UByte;
import kotlin.UShort;

/* loaded from: classes5.dex */
abstract class CompactHashing {
    public static Object createTable(int r1) {
        if (r1 >= 2) goto L5;
    L18:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("must be power of 2 between 2^1 and 2^30: ", r1);
        return null;
    L5:
        if (r1 > 1073741824) goto L18;
        if (Integer.highestOneBit(r1) != r1) goto L18;
        if (r1 > 256) goto L13;
        return new byte[r1];
    L13:
        if (r1 > 65536) goto L17;
        return new short[r1];
    L17:
        return new int[r1];
    }

    public static int getHashPrefix(int r0, int r1) {
        return r0 & (~r1);
    }

    public static int getNext(int r0, int r1) {
        return r0 & r1;
    }

    public static int maskCombine(int r1, int r2, int r3) {
        return (r1 & (~r3)) | (r2 & r3);
    }

    public static int newCapacity(int r1) {
        if (r1 >= 32) goto L5;
        int r0 = 4;
    L7:
        return r0 * (r1 + 1);
    L5:
        r0 = 2;
        goto L7
    }

    public static int remove(Object r8, Object r9, int r10, Object r11, int[] r12, Object[] r13, Object[] r14) {
        int r0 = Hashing.smearedHash(r8);
        int r1 = r0 & r10;
        int r2 = tableGet(r11, r1);
        if (r2 != 0) goto L5;
        return -1;
    L5:
        int r02 = getHashPrefix(r0, r10);
        int r4 = -1;
    L6:
        int r22 = r2 - 1;
        int r5 = r12[r22];
        if (getHashPrefix(r5, r10) == r02) goto L9;
    L19:
        int r42 = getNext(r5, r10);
        if (r42 == 0) goto L21;
        r4 = r22;
        r2 = r42;
        goto L6
    L21:
        return -1;
    L9:
        if (Objects.equals(r8, r13[r22]) == false) goto L19;
        if (r14 == null) goto L13;
        if (Objects.equals(r9, r14[r22]) == false) goto L19;
    L13:
        int r82 = getNext(r5, r10);
        if (r4 != (-1)) goto L17;
        tableSet(r11, r1, r82);
        return r22;
    L17:
        r12[r4] = maskCombine(r12[r4], r82, r10);
        return r22;
    }

    public static void tableClear(Object r2) {
        if ((r2 instanceof byte[]) == false) goto L7;
        Arrays.fill((byte[]) r2, (byte) 0);
        return;
    L7:
        if ((r2 instanceof short[]) == false) goto L10;
        Arrays.fill((short[]) r2, 0);
        return;
    L10:
        Arrays.fill((int[]) r2, 0);
    }

    public static int tableGet(Object r1, int r2) {
        if ((r1 instanceof byte[]) == false) goto L7;
        return ((byte[]) r1)[r2] & UByte.MAX_VALUE;
    L7:
        if ((r1 instanceof short[]) == false) goto L11;
        return ((short[]) r1)[r2] & UShort.MAX_VALUE;
    L11:
        return ((int[]) r1)[r2];
    }

    public static void tableSet(Object r1, int r2, int r3) {
        if ((r1 instanceof byte[]) == false) goto L7;
        ((byte[]) r1)[r2] = (byte) r3;
        return;
    L7:
        if ((r1 instanceof short[]) == false) goto L10;
        ((short[]) r1)[r2] = (short) r3;
        return;
    L10:
        ((int[]) r1)[r2] = r3;
    }

    public static int tableSize(int r2) {
        return Math.max(4, Hashing.closedTableSize(r2 + 1, 1.0d));
    }
}
