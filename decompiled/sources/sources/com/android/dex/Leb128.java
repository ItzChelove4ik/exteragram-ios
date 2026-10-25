package com.android.dex;

import com.android.dex.util.ByteInput;
import com.android.dex.util.ByteOutput;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes.dex */
public abstract class Leb128 {
    public static int readSignedLeb128(ByteInput r6) {
        int r0 = 0;
        int r2 = -1;
        int r1 = 0;
    L3:
        byte r3 = r6.readByte();
        r0 = r0 | ((r3 & ByteCompanionObject.MAX_VALUE) << (r1 * 7));
        r2 = r2 << 7;
        r1 = r1 + 1;
        int r32 = r3 & ByteCompanionObject.MIN_VALUE;
        if (r32 != 128) goto L7;
        if (r1 < 5) goto L3;
    L7:
        if (r32 == 128) goto L14;
        if (((r2 >> 1) & r0) != 0) goto L11;
        return r0;
    L11:
        return r0 | r2;
    L14:
        throw new DexException("invalid LEB128 sequence");
    }

    public static int readUnsignedLeb128(ByteInput r5) {
        int r0 = 0;
        int r1 = 0;
    L3:
        byte r2 = r5.readByte();
        r0 = r0 | ((r2 & ByteCompanionObject.MAX_VALUE) << (r1 * 7));
        r1 = r1 + 1;
        int r22 = r2 & ByteCompanionObject.MIN_VALUE;
        if (r22 != 128) goto L7;
        if (r1 < 5) goto L3;
    L7:
        if (r22 == 128) goto L10;
        return r0;
    L10:
        throw new DexException("invalid LEB128 sequence");
    }

    public static int unsignedLeb128Size(int r1) {
        int r12 = r1 >> 7;
        int r0 = 0;
    L3:
        if (r12 == 0) goto L6;
        r12 = r12 >> 7;
        r0 = r0 + 1;
        goto L3
    L6:
        return r0 + 1;
    }

    public static void writeSignedLeb128(ByteOutput r7, int r8) {
        int r0 = r8 >> 7;
        if ((Integer.MIN_VALUE & r8) != 0) goto L5;
        int r1 = 0;
    L6:
        int r02 = r8;
        int r82 = r0;
        boolean r4 = true;
    L7:
        if (r4 == false) goto L19;
        if (r82 == r1) goto L10;
    L13:
        r4 = true;
    L14:
        int r03 = r02 & 127;
        if (r4 == false) goto L17;
        int r5 = 128;
    L18:
        r7.writeByte((byte) (r03 | r5));
        r02 = r82;
        r82 = r82 >> 7;
        goto L7
    L17:
        r5 = 0;
        goto L18
    L10:
        if ((r82 & 1) != ((r02 >> 6) & 1)) goto L13;
        r4 = false;
        goto L14
    L19:
        return;
    L5:
        r1 = -1;
        goto L6
    }

    public static void writeUnsignedLeb128(ByteOutput r2, int r3) {
    L2:
        int r0 = r3;
        r3 = r3 >>> 7;
        if (r3 == 0) goto L5;
        r2.writeByte((byte) ((r0 & 127) | 128));
        goto L2
    L5:
        r2.writeByte((byte) (r0 & 127));
    }
}
