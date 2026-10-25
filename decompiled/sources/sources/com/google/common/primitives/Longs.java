package com.google.common.primitives;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class Longs {

    public static final class AsciiDigits {
        private static final byte[] asciiDigits = null;

        static {
            byte[] r0 = new byte[128];
            Arrays.fill(r0, (byte) -1);
            int r1 = 0;
            int r2 = 0;
        L4:
            if (r2 >= 10) goto L7;
            r0[r2 + 48] = (byte) r2;
            r2 = r2 + 1;
        L7:
            if (r1 >= 26) goto L9;
            byte r3 = (byte) (r1 + 10);
            r0[r1 + 65] = r3;
            r0[r1 + 97] = r3;
            r1 = r1 + 1;
            goto L7
        L9:
            asciiDigits = r0;
        }

        public static int digit(char r1) {
            if (r1 < 128) goto L5;
            return -1;
        L5:
            return asciiDigits[r1];
        }
    }

    public static final class LongArrayAsList extends AbstractList<Long> implements RandomAccess, Serializable {
    }

    private static int checkNoOverflow(long r3) {
        int r0 = (int) r3;
        if (r3 != r0) goto L5;
        boolean r1 = true;
    L6:
        Preconditions.checkArgument(r1, "the total number of elements (%s) in the arrays must fit in an int", r3);
        return r0;
    L5:
        r1 = false;
        goto L6
    }

    public static long[] concat(long[]... r7) {
        long r1 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r7.length) goto L5;
        r1 = r1 + r7[r4].length;
        r4 = r4 + 1;
        goto L3
    L5:
        long[] r0 = new long[checkNoOverflow(r1)];
        int r12 = r7.length;
        int r2 = 0;
        int r42 = 0;
    L6:
        if (r2 >= r12) goto L8;
        long[] r5 = r7[r2];
        System.arraycopy(r5, 0, r0, r42, r5.length);
        r42 = r42 + r5.length;
        r2 = r2 + 1;
        goto L6
    L8:
        return r0;
    }

    public static int hashCode(long r0) {
        return Long.hashCode(r0);
    }

    public static long max(long... r6) {
        int r2 = 1;
        if (r6.length <= 0) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0);
        long r02 = r6[0];
    L8:
        if (r2 >= r6.length) goto L13;
        long r3 = r6[r2];
        if (r3 <= r02) goto L12;
        r02 = r3;
    L12:
        r2 = r2 + 1;
        goto L8
    L13:
        return r02;
    L5:
        r0 = false;
        goto L6
    }

    public static long[] toArray(Collection<? extends Number> r5) {
        if ((r5 instanceof LongArrayAsList) == true) goto L8;
        Object[] r52 = r5.toArray();
        int r0 = r52.length;
        long[] r1 = new long[r0];
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L7;
        r1[r2] = ((Number) Preconditions.checkNotNull(r52[r2])).longValue();
        r2 = r2 + 1;
        goto L5
    L7:
        return r1;
    L8:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        throw null;
    }

    public static Long tryParse(String r18, int r19) {
        if (((String) Preconditions.checkNotNull(r18)).isEmpty() == false) goto L6;
        return null;
    L6:
        if (r19 >= 2) goto L8;
    L42:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("radix must be between MIN_RADIX and MAX_RADIX but was ", r19);
        return null;
    L8:
        if (r19 > 36) goto L42;
        int r2 = 0;
        if (r18.charAt(0) != '-') goto L13;
        r2 = 1;
    L13:
        if (r2 != r18.length()) goto L15;
        return null;
    L15:
        int r4 = r2 + 1;
        int r5 = AsciiDigits.digit(r18.charAt(r2));
        if (r5 < 0) goto L41;
        if (r5 >= r19) goto L41;
        long r52 = -r5;
        long r7 = r19;
        long r11 = Long.MIN_VALUE / r7;
    L21:
        if (r4 >= r18.length()) goto L33;
        int r13 = r4 + 1;
        int r42 = AsciiDigits.digit(r18.charAt(r4));
        if (r42 < 0) goto L32;
        if (r42 >= r19) goto L32;
        if (r52 < r11) goto L32;
        long r53 = r52 * r7;
        long r14 = r42;
        if (r53 < (r14 - Long.MIN_VALUE)) goto L30;
        r52 = r53 - r14;
        r4 = r13;
        goto L21
    L30:
        return null;
    L32:
        return null;
    L33:
        if (r2 == 0) goto L37;
        return Long.valueOf(r52);
    L37:
        if (r52 != Long.MIN_VALUE) goto L40;
        return null;
    L40:
        return Long.valueOf(-r52);
    L41:
        return null;
    }
}
