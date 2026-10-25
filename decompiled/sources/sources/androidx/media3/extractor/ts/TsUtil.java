package androidx.media3.extractor.ts;

import androidx.media3.common.util.ParsableByteArray;

/* loaded from: classes4.dex */
public abstract class TsUtil {
    public static int findSyncBytePosition(byte[] r2, int r3, int r4) {
    L2:
        if (r3 >= r4) goto L6;
        if (r2[r3] == 71) goto L6;
        r3 = r3 + 1;
    L6:
        return r3;
    }

    public static boolean isStartOfTsPacket(byte[] r5, int r6, int r7, int r8) {
        int r1 = -4;
        int r2 = 0;
    L4:
        if (r1 > 4) goto L16;
        int r3 = (r1 * 188) + r8;
        if (r3 < r6) goto L14;
        if (r3 >= r7) goto L14;
        if (r5[r3] != 71) goto L14;
        r2 = r2 + 1;
        if (r2 != 5) goto L15;
        return true;
    L15:
        r1 = r1 + 1;
    L14:
        r2 = 0;
        goto L15
    L16:
        return false;
    }

    public static long readPcrFromPacket(ParsableByteArray r3, int r4, int r5) {
        r3.setPosition(r4);
        if (r3.bytesLeft() >= 5) goto L5;
        return -9223372036854775807L;
    L5:
        int r42 = r3.readInt();
        if ((8388608 & r42) == 0) goto L9;
        return -9223372036854775807L;
    L9:
        if (((2096896 & r42) >> 8) == r5) goto L12;
        return -9223372036854775807L;
    L12:
        if ((r42 & 32) != 0) goto L14;
    L21:
        return -9223372036854775807L;
    L14:
        if (r3.readUnsignedByte() < 7) goto L21;
        if (r3.bytesLeft() < 7) goto L21;
        if ((r3.readUnsignedByte() & 16) != 16) goto L21;
        byte[] r52 = new byte[6];
        r3.readBytes(r52, 0, 6);
        return readPcrValueFromPcrBytes(r52);
    }

    private static long readPcrValueFromPcrBytes(byte[] r8) {
        return (((((r8[0] & 255) << 25) | ((r8[1] & 255) << 17)) | ((r8[2] & 255) << 9)) | ((r8[3] & 255) << 1)) | ((255 & r8[4]) >> 7);
    }
}
