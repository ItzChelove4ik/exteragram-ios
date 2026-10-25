package com.android.dex;

import com.android.dex.util.ByteInput;
import com.android.dx.io.Opcodes;
import java.io.UTFDataFormatException;
import kotlin.UByte;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: classes4.dex */
public abstract class Mutf8 {
    private static long countBytes(String r6, boolean r7) {
        int r0 = r6.length();
        long r1 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L21;
        char r4 = r6.charAt(r3);
        if (r4 == 0) goto L11;
        if (r4 > 127) goto L11;
        long r42 = 1;
    L9:
        r1 = r1 + r42;
        if (r7 == false) goto L20;
        if (r1 <= WebSocketProtocol.PAYLOAD_SHORT_MAX) goto L20;
        throw new UTFDataFormatException("String more than 65535 UTF bytes long");
    L20:
        r3 = r3 + 1;
    L11:
        if (r4 > 2047) goto L13;
        r42 = 2;
        goto L9
    L13:
        r42 = 3;
        goto L9
    L21:
        return r1;
    }

    public static String decode(ByteInput r7, char[] r8) {
        int r1 = 0;
    L3:
        char r2 = (char) (r7.readByte() & UByte.MAX_VALUE);
        if (r2 == 0) goto L6;
        r8[r1] = r2;
        if (r2 < 128) goto L9;
        if ((r2 & 224) != 192) goto L19;
        byte r4 = r7.readByte();
        if ((r4 & 192) != 128) goto L17;
        int r3 = r1 + 1;
        r8[r1] = (char) (((r2 & 31) << 6) | (r4 & 63));
    L15:
        r1 = r3;
        goto L3
    L17:
        throw new UTFDataFormatException("bad second byte");
    L19:
        if ((r2 & 240) != 224) goto L28;
        byte r42 = r7.readByte();
        byte r5 = r7.readByte();
        if ((r42 & 192) != 128) goto L26;
        if ((r5 & 192) != 128) goto L26;
        r3 = r1 + 1;
        r8[r1] = (char) ((((r2 & 15) << 12) | ((r42 & 63) << 6)) | (r5 & 63));
    L26:
        throw new UTFDataFormatException("bad second or third byte");
    L28:
        throw new UTFDataFormatException("bad byte");
    L9:
        r1 = r1 + 1;
        goto L3
    L6:
        return new String(r8, 0, r1);
    }

    public static void encode(byte[] r6, int r7, String r8) {
        int r0 = r8.length();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L14;
        char r2 = r8.charAt(r1);
        if (r2 == 0) goto L10;
        if (r2 > 127) goto L10;
        r6[r7] = (byte) r2;
        r7 = r7 + 1;
    L13:
        r1 = r1 + 1;
    L10:
        if (r2 > 2047) goto L12;
        int r3 = r7 + 1;
        r6[r7] = (byte) (((r2 >> 6) & 31) | 192);
        r7 = r7 + 2;
        r6[r3] = (byte) ((r2 & '?') | 128);
        goto L13
    L12:
        r6[r7] = (byte) (((r2 >> '\f') & 15) | Opcodes.SHL_INT_LIT8);
        int r4 = r7 + 2;
        r6[r7 + 1] = (byte) (((r2 >> 6) & 63) | 128);
        r7 = r7 + 3;
        r6[r4] = (byte) ((r2 & '?') | 128);
        goto L13
    }

    public static byte[] encode(String r2) {
        byte[] r0 = new byte[(int) countBytes(r2, true)];
        encode(r0, 0, r2);
        return r0;
    }
}
