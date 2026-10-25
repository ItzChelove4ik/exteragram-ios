package com.coremedia.iso;

import com.sun.jna.Native$$ExternalSyntheticBUOutline0;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public abstract class IsoTypeWriter {
    static {
    }

    public static void writeFixedPoint0230(ByteBuffer r2, double r3) {
        int r32 = (int) (r3 * 1.073741824E9d);
        r2.put((byte) (((-16777216) & r32) >> 24));
        r2.put((byte) ((16711680 & r32) >> 16));
        r2.put((byte) ((65280 & r32) >> 8));
        r2.put((byte) (r32 & 255));
    }

    public static void writeFixedPoint1616(ByteBuffer r2, double r3) {
        int r32 = (int) (r3 * 65536.0d);
        r2.put((byte) (((-16777216) & r32) >> 24));
        r2.put((byte) ((16711680 & r32) >> 16));
        r2.put((byte) ((65280 & r32) >> 8));
        r2.put((byte) (r32 & 255));
    }

    public static void writeFixedPoint88(ByteBuffer r2, double r3) {
        short r32 = (short) (r3 * 256.0d);
        r2.put((byte) ((65280 & r32) >> 8));
        r2.put((byte) (r32 & 255));
    }

    public static void writeIso639(ByteBuffer r5, String r6) {
        if (r6.getBytes().length != 3) goto L9;
        int r0 = 0;
        int r2 = 0;
    L5:
        if (r0 >= 3) goto L6;
        r2 = r2 + ((r6.getBytes()[r0] - 96) << ((2 - r0) * 5));
        r0 = r0 + 1;
        goto L5
    L6:
        writeUInt16(r5, r2);
        return;
    L9:
        Native$$ExternalSyntheticBUOutline0.m("\"", r6, "\" language string isn't exactly 3 characters long!");
    }

    public static void writeUInt16(ByteBuffer r1, int r2) {
        writeUInt8(r1, (65535 & r2) >> 8);
        writeUInt8(r1, r2 & 255);
    }

    public static void writeUInt24(ByteBuffer r1, int r2) {
        int r22 = r2 & 16777215;
        writeUInt16(r1, r22 >> 8);
        writeUInt8(r1, r22);
    }

    public static void writeUInt32(ByteBuffer r0, long r1) {
        r0.putInt((int) r1);
    }

    public static void writeUInt48(ByteBuffer r3, long r4) {
        writeUInt16(r3, (int) ((281474976710655L & r4) >> 32));
        writeUInt32(r3, r4 & 4294967295L);
    }

    public static void writeUInt64(ByteBuffer r0, long r1) {
        r0.putLong(r1);
    }

    public static void writeUInt8(ByteBuffer r0, int r1) {
        r0.put((byte) (r1 & 255));
    }

    public static void writeUtf8String(ByteBuffer r0, String r1) {
        r0.put(Utf8.convert(r1));
        writeUInt8(r0, 0);
    }
}
