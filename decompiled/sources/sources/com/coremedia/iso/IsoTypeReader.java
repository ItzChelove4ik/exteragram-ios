package com.coremedia.iso;

import java.nio.ByteBuffer;
import kotlin.UByte;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class IsoTypeReader {
    public static int byte2int(byte r0) {
        if (r0 < 0) goto L4;
        return r0;
    L4:
        return r0 + 256;
    }

    public static String read4cc(ByteBuffer r2) {
        byte[] r0 = new byte[4];
        r2.get(r0);
        return new String(r0, "ISO-8859-1");
    L5:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static double readFixedPoint0230(ByteBuffer r4) {
        r4.get(new byte[4]);
        return (((((r0[0] << 24) & (-16777216)) | ((r0[1] << 16) & 16711680)) | ((r0[2] << 8) & 65280)) | (r0[3] & UByte.MAX_VALUE)) / 1.073741824E9d;
    }

    public static double readFixedPoint1616(ByteBuffer r4) {
        r4.get(new byte[4]);
        return (((((r0[0] << 24) & (-16777216)) | ((r0[1] << 16) & 16711680)) | ((r0[2] << 8) & 65280)) | (r0[3] & UByte.MAX_VALUE)) / 65536.0d;
    }

    public static float readFixedPoint88(ByteBuffer r2) {
        r2.get(new byte[2]);
        return ((short) (((short) ((r0[0] << 8) & 65280)) | (r0[1] & UByte.MAX_VALUE))) / 256.0f;
    }

    public static String readIso639(ByteBuffer r3) {
        int r32 = readUInt16(r3);
        StringBuilder r0 = new StringBuilder();
        int r1 = 0;
    L4:
        if (r1 >= 3) goto L6;
        r0.append((char) (((r32 >> ((2 - r1) * 5)) & 31) + 96));
        r1 = r1 + 1;
        goto L4
    L6:
        return r0.toString();
    }

    public static String readString(ByteBuffer r0, int r1) {
        byte[] r12 = new byte[r1];
        r0.get(r12);
        return Utf8.convert(r12);
    }

    public static int readUInt16(ByteBuffer r1) {
        return (byte2int(r1.get()) << 8) + byte2int(r1.get());
    }

    public static int readUInt24(ByteBuffer r1) {
        return (readUInt16(r1) << 8) + byte2int(r1.get());
    }

    public static long readUInt32(ByteBuffer r4) {
        long r0 = r4.getInt();
        if (r0 < 0) goto L5;
        return r0;
    L5:
        return r0 + 4294967296L;
    }

    public static long readUInt48(ByteBuffer r4) {
        long r0 = readUInt16(r4) << 32;
        if (r0 >= 0) goto L5;
        GlShader$$ExternalSyntheticBUOutline1.m("I don't know how to deal with UInt64! long is not sufficient and I don't want to use BigInt");
        return 0;
    L5:
        return r0 + readUInt32(r4);
    }

    public static long readUInt64(ByteBuffer r4) {
        long r0 = readUInt32(r4) << 32;
        if (r0 >= 0) goto L5;
        GlShader$$ExternalSyntheticBUOutline1.m("I don't know how to deal with UInt64! long is not sufficient and I don't want to use BigInt");
        return 0;
    L5:
        return r0 + readUInt32(r4);
    }

    public static int readUInt8(ByteBuffer r0) {
        return byte2int(r0.get());
    }
}
