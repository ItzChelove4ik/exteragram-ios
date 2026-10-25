package org.telegram.messenger.wallpaper.pgm;

import android.graphics.Bitmap;
import c.f$$ExternalSyntheticBUOutline1;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import okio.ZipFileSystem$$ExternalSyntheticBUOutline0;
import okio.internal.ZipFilesKt$$ExternalSyntheticBUOutline0;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes6.dex */
public abstract class PGMImage {
    private static String nextNonCommentToken(BufferedInputStream r2, List<String> r3) {
    L2:
        String r0 = nextToken(r2, r3);
        if (r0 == null) goto L7;
        if (r0.startsWith("#") == true) goto L2;
        return r0;
    L7:
        Model$$ExternalSyntheticBUOutline0.m("Unexpected EOF in header");
        return null;
    }

    private static String nextToken(BufferedInputStream r5, List<String> r6) {
        StringBuilder r0 = new StringBuilder();
    L3:
        r5.mark(1);
        int r2 = r5.read();
        if (r2 == (-1)) goto L6;
        if (Character.isWhitespace(r2) == true) goto L14;
        if (r2 != 35) goto L26;
        String r1 = readLineAscii(r5);
        if (r6 == null) goto L23;
        r6.add(r1);
    L23:
        if (r0.length() <= 0) goto L3;
        return r0.toString();
    L26:
        r0.append((char) r2);
    L27:
        r5.mark(1);
        int r62 = r5.read();
        if (r62 == (-1)) goto L37;
        if (Character.isWhitespace(r62) == true) goto L37;
        if (r62 == 35) goto L33;
        r0.append((char) r62);
        goto L27
    L33:
        r5.reset();
        return r0.toString();
    L37:
        return r0.toString();
    L14:
        if (r0.length() <= 0) goto L3;
        return r0.toString();
    L6:
        if (r0.length() != 0) goto L10;
        return null;
    L10:
        return r0.toString();
    }

    private static int parsePositiveInt(String r5, String r6) {
        int r2 = Integer.parseInt(r5);     // Catch: NumberFormatException -> L8
        if (r2 <= 0) goto L7;
        return r2;
    L7:
        throw new IOException("Invalid " + r6 + ": " + r2);     // Catch: NumberFormatException -> L8
    L8:
        e = move-exception;
        throw new IOException("Invalid " + r6 + ": " + r5, e);
    }

    public static Bitmap read(InputStream r9, List<String> r10) {
        BufferedInputStream r0 = new BufferedInputStream(r9);
        String r92 = nextToken(r0, r10);
        if ("P5".equals(r92) == false) goto L13;
        int r93 = parsePositiveInt(nextNonCommentToken(r0, r10), "width");
        int r1 = parsePositiveInt(nextNonCommentToken(r0, r10), "height");
        int r102 = parsePositiveInt(nextNonCommentToken(r0, r10), "maxval");
        if (r102 != 255) goto L11;
        Bitmap r103 = Bitmap.createBitmap(r93, r1, Bitmap.Config.ALPHA_8);
        int r2 = r103.getRowBytes();
        byte[] r3 = new byte[r2 * r1];
        ByteBuffer r4 = ByteBuffer.wrap(r3);
        byte[] r5 = new byte[r93];
        int r7 = 0;
        int r8 = 0;
    L7:
        if (r7 >= r1) goto L9;
        readFully(r0, r5, 0, r93);
        System.arraycopy(r5, 0, r3, r8, r93);
        r8 = r8 + r2;
        r7 = r7 + 1;
        goto L7
    L9:
        r103.copyPixelsFromBuffer(r4);
        return r103;
    L11:
        ZipFilesKt$$ExternalSyntheticBUOutline0.m("Only 8-bit PGM supported (maxval=255), got: ", r102);
        return null;
    L13:
        ZipFileSystem$$ExternalSyntheticBUOutline0.m("Not a binary PGM (P5), got: ", r92);
        return null;
    }

    private static void readFully(InputStream r3, byte[] r4, int r5, int r6) {
        int r0 = 0;
    L3:
        if (r0 >= r6) goto L11;
        int r1 = r3.read(r4, r5 + r0, r6 - r0);
        if (r1 < 0) goto L7;
        r0 = r0 + r1;
        goto L3
    L7:
        Model$$ExternalSyntheticBUOutline0.m("Unexpected EOF");
        return;
    }

    private static String readLineAscii(InputStream r3) {
        StringBuilder r0 = new StringBuilder();
    L3:
        int r1 = r3.read();
        if (r1 == (-1)) goto L12;
        if (r1 == 10) goto L12;
        if (r1 == 13) goto L3;
        r0.append((char) r1);
    L12:
        return r0.toString();
    }

    public static void write(Bitmap r6, OutputStream r7, List<String> r8) {
        if (r6.getConfig() != Bitmap.Config.ALPHA_8) goto L20;
        int r0 = r6.getWidth();
        int r1 = r6.getHeight();
        r7.write("P5\n".getBytes(StandardCharsets.US_ASCII));
        if (r8 != null) goto L7;
    L16:
        Charset r2 = StandardCharsets.US_ASCII;
        r7.write((r0 + " " + r1 + "\n").getBytes(r2));
        r7.write("255\n".getBytes(r2));
        int r82 = r6.getRowBytes();
        byte[] r22 = new byte[r82 * r1];
        r6.copyPixelsToBuffer(ByteBuffer.wrap(r22));
        int r62 = 0;
        int r3 = 0;
    L17:
        if (r62 >= r1) goto L19;
        r7.write(r22, r3, r0);
        r62 = r62 + 1;
        r3 = r3 + r82;
        goto L17
    L19:
        return;
    L7:
        if (r8.isEmpty() == true) goto L16;
        Iterator<String> r83 = r8.iterator();
    L10:
        if (r83.hasNext() == false) goto L16;
        String r32 = r83.next();
        if (r32 != null) goto L14;
        String r33 = _UrlKt.FRAGMENT_ENCODE_SET;
    L15:
        r7.write(("#" + r33 + "\n").getBytes(StandardCharsets.US_ASCII));
        goto L10
    L14:
        r33 = r32.replace('\r', ' ').replace('\n', ' ');
        goto L15
    L20:
        f$$ExternalSyntheticBUOutline1.m("Only Bitmap.Config.ALPHA_8 is supported");
    }
}
