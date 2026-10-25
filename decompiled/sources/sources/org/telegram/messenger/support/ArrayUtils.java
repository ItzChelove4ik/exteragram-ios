package org.telegram.messenger.support;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class ArrayUtils {
    private static final int CACHE_SIZE = 73;
    private static Object[] EMPTY;
    private static Object[] sCache;

    static {
        EMPTY = new Object[0];
        sCache = new Object[73];
    }

    private ArrayUtils() {
    }

    public static <T> T[] appendElement(Class<T> r3, T[] r4, T r5) {
        int r0 = 0;
        if (r4 == null) goto L5;
        int r1 = r4.length;
        T[] r32 = (T[]) ((Object[]) Array.newInstance(r3, r1 + 1));
        System.arraycopy(r4, 0, r32, 0, r1);
        r0 = r1;
    L6:
        r32[r0] = r5;
        return r32;
    L5:
        r32 = (T[]) ((Object[]) Array.newInstance(r3, 1));
        goto L6
    }

    public static int[] appendInt(int[] r4, int r5) {
        if (r4 == null) goto L4;
        int r0 = r4.length;
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L11;
        if (r4[r2] == r5) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return r4;
    L11:
        int[] r22 = new int[r0 + 1];
        System.arraycopy(r4, 0, r22, 0, r0);
        r22[r0] = r5;
        return r22;
    L4:
        return new int[]{r5};
    }

    public static boolean contains(int[] r4, int r5) {
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r4[r2] == r5) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    public static <T> boolean contains(T[] r5, T r6) {
        int r0 = r5.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L13;
        T r3 = r5[r2];
        if (r3 != null) goto L8;
        if (r6 != null) goto L12;
        return true;
    L12:
        r2 = r2 + 1;
        goto L3
    L8:
        if (r6 == null) goto L12;
        if (r3.equals(r6) == false) goto L12;
        return true;
    L13:
        return false;
    }

    public static <T> T[] emptyArray(Class<T> r3) {
        if (r3 == Object.class) goto L5;
        int r0 = ((System.identityHashCode(r3) / 8) & Integer.MAX_VALUE) % 73;
        Object r1 = sCache[r0];
        if (r1 != null) goto L9;
    L10:
        r1 = Array.newInstance(r3, 0);
        sCache[r0] = r1;
    L12:
        return (T[]) ((Object[]) r1);
    L9:
        if (r1.getClass().getComponentType() == r3) goto L12;
    L5:
        return (T[]) EMPTY;
    }

    public static boolean equals(byte[] r5, byte[] r6, int r7) {
        if (r5 != r6) goto L6;
        return true;
    L6:
        if (r5 == null) goto L20;
        if (r6 == null) goto L20;
        if (r5.length < r7) goto L20;
        if (r6.length < r7) goto L20;
        int r2 = 0;
    L14:
        if (r2 >= r7) goto L19;
        if (r5[r2] != r6[r2]) goto L17;
        r2 = r2 + 1;
        goto L14
    L17:
        return false;
    L19:
        return true;
    L20:
        return false;
    }

    public static int idealBooleanArraySize(int r0) {
        return idealByteArraySize(r0);
    }

    public static int idealByteArraySize(int r2) {
        int r0 = 4;
    L4:
        if (r0 >= 32) goto L9;
        int r1 = (1 << r0) - 12;
        if (r2 <= r1) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return r2;
    }

    public static int idealCharArraySize(int r0) {
        return idealByteArraySize(r0 * 2) / 2;
    }

    public static int idealFloatArraySize(int r0) {
        return idealByteArraySize(r0 * 4) / 4;
    }

    public static int idealIntArraySize(int r0) {
        return idealByteArraySize(r0 * 4) / 4;
    }

    public static int idealLongArraySize(int r0) {
        return idealByteArraySize(r0 * 8) / 8;
    }

    public static int idealObjectArraySize(int r0) {
        return idealByteArraySize(r0 * 4) / 4;
    }

    public static int idealShortArraySize(int r0) {
        return idealByteArraySize(r0 * 2) / 2;
    }

    public static int indexOf(int[] r2, int r3) {
        if (r2 == null) goto L10;
        int r0 = 0;
    L5:
        if (r0 >= r2.length) goto L14;
        if (r2[r0] == r3) goto L8;
        r0 = r0 + 1;
        goto L5
    L8:
        return r0;
    L14:
        return -1;
    L10:
        return -1;
    }

    public static <T> T[] removeElement(Class<T> r4, T[] r5, T r6) {
        if (r5 == null) goto L14;
        int r0 = r5.length;
        int r2 = 0;
    L4:
        if (r2 >= r0) goto L14;
        if (r5[r2] == r6) goto L8;
        r2 = r2 + 1;
        goto L4
    L8:
        if (r0 != 1) goto L11;
        return null;
    L11:
        T[] r42 = (T[]) ((Object[]) Array.newInstance(r4, r0 - 1));
        System.arraycopy(r5, 0, r42, 0, r2);
        System.arraycopy(r5, r2 + 1, r42, r2, (r0 - r2) - 1);
        return r42;
    L14:
        return r5;
    }

    public static int[] removeInt(int[] r4, int r5) {
        if (r4 != null) goto L5;
        return null;
    L5:
        int r0 = r4.length;
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L16;
        if (r4[r2] == r5) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        int r52 = r0 - 1;
        int[] r3 = new int[r52];
        if (r2 <= 0) goto L12;
        System.arraycopy(r4, 0, r3, 0, r2);
    L12:
        if (r2 >= r52) goto L14;
        System.arraycopy(r4, r2 + 1, r3, r2, (r0 - r2) - 1);
    L14:
        return r3;
    L16:
        return r4;
    }

    public static long total(long[] r6) {
        int r0 = r6.length;
        long r1 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L5;
        r1 = r1 + r6[r3];
        r3 = r3 + 1;
        goto L3
    L5:
        return r1;
    }
}
