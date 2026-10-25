package com.android.dex;

import com.android.dex.util.ByteInput;
import com.android.dex.util.ByteOutput;
import kotlin.UByte;

/* loaded from: classes4.dex */
public abstract class EncodedValueCodec {
    public static int readSignedInt(ByteInput r3, int r4) {
        int r0 = 0;
        int r1 = r4;
    L3:
        if (r1 < 0) goto L6;
        r0 = (r0 >>> 8) | ((r3.readByte() & UByte.MAX_VALUE) << 24);
        r1 = r1 - 1;
        goto L3
    L6:
        return r0 >> ((3 - r4) * 8);
    }

    public static long readSignedLong(ByteInput r7, int r8) {
        long r0 = 0;
        int r2 = r8;
    L4:
        if (r2 < 0) goto L7;
        r0 = (r0 >>> 8) | ((r7.readByte() & 255) << 56);
        r2 = r2 - 1;
        goto L4
    L7:
        return r0 >> ((7 - r8) * 8);
    }

    public static int readUnsignedInt(ByteInput r2, int r3, boolean r4) {
        int r0 = 0;
        if (r4 == true) goto L9;
        int r42 = r3;
    L5:
        if (r42 < 0) goto L8;
        r0 = (r0 >>> 8) | ((r2.readByte() & UByte.MAX_VALUE) << 24);
        r42 = r42 - 1;
        goto L5
    L8:
        return r0 >>> ((3 - r3) * 8);
    L9:
        if (r3 < 0) goto L11;
        r0 = ((r2.readByte() & UByte.MAX_VALUE) << 24) | (r0 >>> 8);
        r3 = r3 - 1;
        goto L9
    L11:
        return r0;
    }

    public static long readUnsignedLong(ByteInput r8, int r9, boolean r10) {
        long r4 = 0;
        if (r10 == true) goto L9;
        int r102 = r9;
    L5:
        if (r102 < 0) goto L8;
        r4 = (r4 >>> 8) | ((r8.readByte() & 255) << 56);
        r102 = r102 - 1;
        goto L5
    L8:
        return r4 >>> ((7 - r9) * 8);
    L9:
        if (r9 < 0) goto L11;
        r4 = (r4 >>> 8) | ((r8.readByte() & 255) << 56);
        r9 = r9 - 1;
        goto L9
    L11:
        return r4;
    }

    public static void writeRightZeroExtendedValue(ByteOutput r2, int r3, long r4) {
        int r0 = 64 - Long.numberOfTrailingZeros(r4);
        if (r0 != 0) goto L5;
        r0 = 1;
    L5:
        int r02 = (r0 + 7) >> 3;
        long r42 = r4 >> (64 - (r02 * 8));
        r2.writeByte(r3 | ((r02 - 1) << 5));
    L6:
        if (r02 <= 0) goto L8;
        r2.writeByte((byte) r42);
        r42 = r42 >> 8;
        r02 = r02 - 1;
        goto L6
    }

    public static void writeSignedIntegralValue(ByteOutput r2, int r3, long r4) {
        int r0 = (72 - Long.numberOfLeadingZeros((r4 >> 63) ^ r4)) >> 3;
        r2.writeByte(r3 | ((r0 - 1) << 5));
    L3:
        if (r0 <= 0) goto L5;
        r2.writeByte((byte) r4);
        r4 = r4 >> 8;
        r0 = r0 - 1;
        goto L3
    }

    public static void writeUnsignedIntegralValue(ByteOutput r2, int r3, long r4) {
        int r0 = 64 - Long.numberOfLeadingZeros(r4);
        if (r0 != 0) goto L5;
        r0 = 1;
    L5:
        int r02 = (r0 + 7) >> 3;
        r2.writeByte(r3 | ((r02 - 1) << 5));
    L6:
        if (r02 <= 0) goto L8;
        r2.writeByte((byte) r4);
        r4 = r4 >> 8;
        r02 = r02 - 1;
        goto L6
    }
}
