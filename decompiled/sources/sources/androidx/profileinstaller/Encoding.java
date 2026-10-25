package androidx.profileinstaller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import kotlin.UByte;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
abstract class Encoding {
    public static int bitsToBytes(int r0) {
        return ((r0 + 7) & (-8)) / 8;
    }

    public static byte[] compress(byte[] r3) {
        Deflater r0 = new Deflater(1);
        ByteArrayOutputStream r1 = new ByteArrayOutputStream();
        DeflaterOutputStream r2 = new DeflaterOutputStream(r1, r0);     // Catch: Throwable -> L8
        r2.write(r3);     // Catch: Throwable -> L10
        r2.close();     // Catch: Throwable -> L8
        r0.end();
        return r1.toByteArray();
    L10:
        th = move-exception;
        r2.close();     // Catch: Throwable -> L13
    L15:
        throw th;     // Catch: Throwable -> L8
    L13:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        r0.end();
        throw th;
    }

    public static RuntimeException error(String r1) {
        return new IllegalStateException(r1);
    }

    public static byte[] read(InputStream r3, int r4) {
        byte[] r0 = new byte[r4];
        int r1 = 0;
    L3:
        if (r1 >= r4) goto L9;
        int r2 = r3.read(r0, r1, r4 - r1);
        if (r2 < 0) goto L8;
        r1 = r1 + r2;
        goto L3
    L8:
        throw error("Not enough bytes to read: " + r4);
    L9:
        return r0;
    }

    public static byte[] readCompressed(InputStream r8, int r9, int r10) {
        Inflater r0 = new Inflater();
        byte[] r1 = new byte[r10];     // Catch: Throwable -> L15
        byte[] r2 = new byte[2048];     // Catch: Throwable -> L15
        int r4 = 0;
        int r5 = 0;
    L5:
        if (r0.finished() == true) goto L22;
        if (r0.needsDictionary() == true) goto L22;
        if (r4 >= r9) goto L22;
        int r6 = r8.read(r2);     // Catch: Throwable -> L15
        if (r6 < 0) goto L21;
        r0.setInput(r2, 0, r6);     // Catch: Throwable -> L15
        r5 = r5 + r0.inflate(r1, r5, r10 - r5);
        r4 = r4 + r6;
        goto L5
    L17:
        e = move-exception;
        throw error(e.getMessage());     // Catch: Throwable -> L15
    L21:
        throw error("Invalid zip data. Stream ended after $totalBytesRead bytes. Expected " + r9 + " bytes");     // Catch: Throwable -> L15
    L22:
        if (r4 != r9) goto L30;
        if (r0.finished() == false) goto L28;
        r0.end();
        return r1;
    L28:
        throw error("Inflater did not finish");     // Catch: Throwable -> L15
    L30:
        throw error("Didn't read enough bytes during decompression. expected=" + r9 + " actual=" + r4);     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        r0.end();
        throw th;
    }

    public static String readString(InputStream r1, int r2) {
        return new String(read(r1, r2), StandardCharsets.UTF_8);
    }

    public static long readUInt(InputStream r6, int r7) {
        byte[] r62 = read(r6, r7);
        long r0 = 0;
        int r2 = 0;
    L3:
        if (r2 >= r7) goto L5;
        r0 = r0 + ((r62[r2] & UByte.MAX_VALUE) << (r2 * 8));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    public static int readUInt16(InputStream r2) {
        return (int) readUInt(r2, 2);
    }

    public static long readUInt32(InputStream r2) {
        return readUInt(r2, 4);
    }

    public static int readUInt8(InputStream r2) {
        return (int) readUInt(r2, 1);
    }

    public static int utf8Length(String r1) {
        return r1.getBytes(StandardCharsets.UTF_8).length;
    }

    public static void writeAll(InputStream r2, OutputStream r3, FileLock r4) {
        if (r4 != null) goto L4;
    L10:
        Model$$ExternalSyntheticBUOutline0.m("Unable to acquire a lock on the underlying file channel.");
        return;
    L4:
        if (r4.isValid() == false) goto L10;
        byte[] r42 = new byte[512];
    L6:
        int r0 = r2.read(r42);
        if (r0 <= 0) goto L9;
        r3.write(r42, 0, r0);
        goto L6
    }

    public static void writeCompressed(OutputStream r2, byte[] r3) {
        writeUInt32(r2, r3.length);
        byte[] r32 = compress(r3);
        writeUInt32(r2, r32.length);
        r2.write(r32);
    }

    public static void writeString(OutputStream r1, String r2) {
        r1.write(r2.getBytes(StandardCharsets.UTF_8));
    }

    public static void writeUInt(OutputStream r6, long r7, int r9) {
        byte[] r0 = new byte[r9];
        int r1 = 0;
    L3:
        if (r1 >= r9) goto L5;
        r0[r1] = (byte) ((r7 >> (r1 * 8)) & 255);
        r1 = r1 + 1;
        goto L3
    L5:
        r6.write(r0);
    }

    public static void writeUInt16(OutputStream r2, int r3) {
        writeUInt(r2, r3, 2);
    }

    public static void writeUInt32(OutputStream r1, long r2) {
        writeUInt(r1, r2, 4);
    }

    public static void writeUInt8(OutputStream r2, int r3) {
        writeUInt(r2, r3, 1);
    }
}
