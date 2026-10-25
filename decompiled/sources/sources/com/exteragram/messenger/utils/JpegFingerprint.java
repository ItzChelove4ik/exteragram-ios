package com.exteragram.messenger.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import kotlin.UByte;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.FileLog;

/* loaded from: classes4.dex */
public class JpegFingerprint {
    private static final int[] STANDARD_LUMA = null;
    private static final int[] ZIGZAG = null;
    public int components;
    public boolean exif;
    public boolean frameBeforeQuantization;
    public int height;
    public int horizontalSampling;
    public int huffmanTables;
    public String iccDescription;
    public int iccLength;
    public int iccVersion;
    public int iccYear;
    public int jfifDensityX;
    public int jfifDensityY;
    public boolean jfifThumbnail;
    public int jfifUnits;
    public int jfifVersion;
    public String markerOrder;
    public boolean progressive;
    public int quality;
    public boolean quantizationTable;
    public boolean restartInterval;
    public int verticalSampling;
    public int width;
    public boolean xmp;

    static {
        ZIGZAG = new int[]{0, 1, 8, 16, 9, 2, 3, 10, 17, 24, 32, 25, 18, 11, 4, 5, 12, 19, 26, 33, 40, 48, 41, 34, 27, 20, 13, 6, 7, 14, 21, 28, 35, 42, 49, 56, 57, 50, 43, 36, 29, 22, 15, 23, 30, 37, 44, 51, 58, 59, 52, 45, 38, 31, 39, 46, 53, 60, 61, 54, 47, 55, 62, 63};
        STANDARD_LUMA = new int[]{16, 11, 10, 16, 24, 40, 51, 61, 12, 12, 14, 19, 26, 58, 60, 55, 14, 13, 16, 24, 40, 57, 69, 56, 14, 17, 22, 29, 51, 87, 80, 62, 18, 22, 37, 56, 68, 109, 103, 77, 24, 35, 55, 64, 81, 104, 113, 92, 49, 64, 78, 87, 103, 121, 120, 101, 72, 92, 95, 98, 112, 100, 103, 99};
    }

    public JpegFingerprint() {
        this.jfifVersion = -1;
        this.jfifUnits = -1;
        this.quality = -1;
        this.markerOrder = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static JpegFingerprint parse(String r14) {
        File r0 = new File(r14);
        if (r0.exists() == true) goto L99;
        return null;
    L99:
        BufferedInputStream r142 = new BufferedInputStream(new FileInputStream(r0), 16384);     // Catch: IOException -> L84
    L20:
        th = move-exception;
        r142.close();     // Catch: Throwable -> L90
    L92:
        throw th;     // Catch: IOException -> L84
    L90:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L84
        goto L92
    L7:
        if (r142.read() == 255) goto L9;
    L86:
        r142.close();     // Catch: IOException -> L84
        return null;
    L9:
        if (r142.read() != 216) goto L86;
        JpegFingerprint r02 = new JpegFingerprint();     // Catch: Throwable -> L20
        StringBuilder r3 = new StringBuilder();     // Catch: Throwable -> L20
        int r4 = 2;
    L13:
        if (r4 >= 262144) goto L81;
        if (r142.read() != 255) goto L81;
        int r5 = r142.read();     // Catch: Throwable -> L20
    L18:
        if (r5 != 255) goto L22;
        r5 = r142.read();     // Catch: Throwable -> L20
        r4 = r4 + 1;     // Catch: Throwable -> L20
        goto L18
    L22:
        r4 = r4 + 2;     // Catch: Throwable -> L20
        if (r5 < 0) goto L81;
        if (r5 == 217) goto L81;
        if (r5 == 218) goto L81;
        if (r5 == 1) goto L13;
        if (r5 < 208) goto L36;
        if (r5 <= 215) goto L13;
    L36:
        int r6 = r142.read();     // Catch: Throwable -> L20
        int r7 = r142.read();     // Catch: Throwable -> L20
        if (r6 < 0) goto L81;
        if (r7 < 0) goto L81;
        int r62 = (r6 << 8) | r7;     // Catch: Throwable -> L20
        int r72 = r62 - 2;
        if (r72 < 0) goto L81;
        r4 = r4 + r62;     // Catch: Throwable -> L20
        if (r3.length() <= 0) goto L46;
        r3.append(Typography.greater);     // Catch: Throwable -> L20
    L46:
        r3.append(String.format(Locale.US, "%02X", new Object[]{Integer.valueOf(r5)}));     // Catch: Throwable -> L20
        if (r5 == 224) goto L72;
        if (r5 == 225) goto L72;
        if (r5 == 226) goto L72;
        if (r5 == 219) goto L72;
        if (r5 == 196) goto L72;
        if (r5 == 221) goto L72;
        if (r5 < 192) goto L63;
        if (r5 <= 194) goto L72;
    L63:
        long r8 = 0;
    L64:
        long r10 = r72;
        if (r8 >= r10) goto L70;
        long r102 = r142.skip(r10 - r8);     // Catch: Throwable -> L20
        if (r102 <= 0) goto L70;
        r8 = r8 + r102;     // Catch: Throwable -> L20
    L70:
        if (r8 >= r10) goto L13;
    L72:
        byte[] r63 = new byte[r72];     // Catch: Throwable -> L20
        int r82 = 0;
    L73:
        if (r82 >= r72) goto L78;
        int r9 = r142.read(r63, r82, r72 - r82);     // Catch: Throwable -> L20
        if (r9 < 0) goto L78;
        r82 = r82 + r9;     // Catch: Throwable -> L20
    L78:
        if (r82 < r72) goto L81;
        r02.readSegment(r5, r63);     // Catch: Throwable -> L20
    L81:
        r02.markerOrder = r3.toString();     // Catch: Throwable -> L20
        r142.close();     // Catch: IOException -> L84
        return r02;
    L84:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private static String readIccDescription(byte[] r7, int r8) {
        int r0 = readInt(r7, r8 + 128);     // Catch: Exception -> L43
        int r2 = 0;
    L5:
        if (r2 >= Math.min(r0, 64)) goto L42;
        int r3 = (r8 + 132) + (r2 * 12);     // Catch: Exception -> L43
        if ((r3 + 12) > r7.length) goto L42;
        if (r7[r3] != 100) goto L41;
        if (r7[r3 + 1] != 101) goto L41;
        if (r7[r3 + 2] != 115) goto L41;
        if (r7[r3 + 3] != 99) goto L41;
        int r02 = readInt(r7, r3 + 4) + r8;     // Catch: Exception -> L43
        int r22 = readInt(r7, r3 + 8);     // Catch: Exception -> L43
        if (r02 < r8) goto L42;
        if (r22 < 12) goto L42;
        int r32 = r02 + r22;     // Catch: Exception -> L43
        if (r32 > r7.length) goto L42;
        byte r4 = r7[r02];     // Catch: Exception -> L43
        if (r4 != 100) goto L33;
        int r23 = Math.min(readInt(r7, r02 + 8) - 1, r22 - 12);     // Catch: Exception -> L43
        if (r23 > 0) goto L30;
        return null;
    L30:
        return new String(r7, r02 + 12, r23, StandardCharsets.US_ASCII);
    L33:
        if (r4 != 109) goto L42;
        int r82 = readInt(r7, r02 + 20);     // Catch: Exception -> L43
        int r24 = readInt(r7, r02 + 24) + r02;     // Catch: Exception -> L43
        if (r82 <= 0) goto L42;
        if (r24 < r02) goto L42;
        if ((r24 + r82) > r32) goto L42;
        return new String(r7, r24, r82, StandardCharsets.UTF_16BE);
    L41:
        r2 = r2 + 1;
    L42:
        return null;
    }

    private static int readInt(byte[] r2, int r3) {
        int r0 = (((r2[r3] & UByte.MAX_VALUE) << 24) | ((r2[r3 + 1] & UByte.MAX_VALUE) << 16)) | ((r2[r3 + 2] & UByte.MAX_VALUE) << 8);
        return (r2[r3 + 3] & UByte.MAX_VALUE) | r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readSegment(int r12, byte[] r13) {
        int r5 = 0;
        boolean r52 = false;
        boolean r53 = false;
        boolean r54 = false;
        r54 = false;
        boolean r55 = false;
        if (r12 != 224) goto L16;
        if (r13.length >= 14) goto L7;
        return;
    L7:
        if (startsWith(r13, "JFIF") == false) goto L95;
        this.jfifVersion = ((r13[5] & UByte.MAX_VALUE) << 8) | (r13[6] & UByte.MAX_VALUE);
        this.jfifUnits = r13[7] & UByte.MAX_VALUE;
        this.jfifDensityX = ((r13[8] & UByte.MAX_VALUE) << 8) | (r13[9] & UByte.MAX_VALUE);
        this.jfifDensityY = ((r13[10] & UByte.MAX_VALUE) << 8) | (r13[11] & UByte.MAX_VALUE);
        if (r13[12] == 0) goto L11;
    L12:
        r52 = true;
    L13:
        this.jfifThumbnail = r52;
        return;
    L11:
        if (r13[13] == 0) goto L13;
    L95:
        return;
    L16:
        if (r12 != 225) goto L32;
        if (this.exif == false) goto L20;
    L23:
        boolean r122 = true;
    L24:
        this.exif = r122;
        if (this.xmp == false) goto L27;
    L28:
        r53 = true;
    L29:
        this.xmp = r53;
        return;
    L27:
        if (startsWith(r13, "http://ns.adobe.com/xap/1.0/") == false) goto L29;
    L20:
        if (startsWith(r13, "Exif") == true) goto L23;
        r122 = false;
        goto L24
    L32:
        if (r12 != 226) goto L42;
        if (this.iccLength == 0) goto L36;
        return;
    L36:
        if (r13.length >= 50) goto L38;
        return;
    L38:
        if (startsWith(r13, "ICC_PROFILE") == false) goto L98;
        this.iccLength = ((((r13[14] & UByte.MAX_VALUE) << 24) | ((r13[15] & UByte.MAX_VALUE) << 16)) | ((r13[16] & UByte.MAX_VALUE) << 8)) | (r13[17] & UByte.MAX_VALUE);
        this.iccVersion = ((r13[22] & UByte.MAX_VALUE) << 8) | (r13[23] & UByte.MAX_VALUE);
        this.iccYear = ((r13[38] & UByte.MAX_VALUE) << 8) | (r13[39] & UByte.MAX_VALUE);
        this.iccDescription = readIccDescription(r13, 14);
        return;
    L98:
        return;
    L42:
        if (r12 != 219) goto L59;
    L44:
        if (r5 >= r13.length) goto L102;
        byte r123 = r13[r5];
        int r0 = (r123 & 240) >> 4;
        boolean r124 = r123 & 15;
        if (r0 != 0) goto L48;
        int r1 = 64;
    L49:
        int r56 = r5 + 1;
        int r14 = r1 + r56;
        if (r14 > r13.length) goto L103;
        if (r124 != 0) goto L57;
        if (r0 != 0) goto L57;
        if (this.quantizationTable == true) goto L57;
        this.quantizationTable = true;
        this.quality = standardQuality(r13, r56);
    L57:
        r5 = r14;
        goto L44
    L103:
        return;
    L48:
        r1 = 128;
        goto L49
    L102:
        return;
    L59:
        if (r12 != 196) goto L68;
        int r125 = 0;
    L62:
        if ((r125 + 17) > r13.length) goto L104;
        int r02 = 0;
        int r15 = 0;
    L64:
        if (r02 >= 16) goto L66;
        r15 = r15 + (r13[(r125 + 1) + r02] & UByte.MAX_VALUE);
        r02 = r02 + 1;
        goto L64
    L66:
        this.huffmanTables++;
        r125 = r125 + (r15 + 17);
        goto L62
    L104:
        return;
    L68:
        if (r12 != 221) goto L77;
        if (r13.length >= 2) goto L72;
    L74:
        this.restartInterval = r54;
        return;
    L72:
        if ((((r13[0] & UByte.MAX_VALUE) << 8) | (r13[1] & UByte.MAX_VALUE)) <= 0) goto L74;
        r54 = true;
        goto L74
    L77:
        if (r12 >= 192) goto L79;
        return;
    L79:
        if (r12 <= 194) goto L81;
        return;
    L81:
        if (r13.length < 9) goto L101;
        if (r12 != 194) goto L84;
        r55 = true;
    L84:
        this.progressive = r55;
        this.frameBeforeQuantization = !this.quantizationTable;
        this.height = ((r13[1] & UByte.MAX_VALUE) << 8) | (r13[2] & UByte.MAX_VALUE);
        this.width = ((r13[3] & UByte.MAX_VALUE) << 8) | (r13[4] & UByte.MAX_VALUE);
        this.components = r13[5] & UByte.MAX_VALUE;
        byte r126 = r13[7];
        this.horizontalSampling = (r126 & 240) >> 4;
        this.verticalSampling = r126 & 15;
        return;
    }

    private static int standardQuality(byte[] r9, int r10) {
        int r1 = 100;
    L4:
        if (r1 < 1) goto L22;
        if (r1 >= 50) goto L8;
        int r4 = 5000 / r1;
    L9:
        int r5 = 0;
    L11:
        if (r5 >= 64) goto L21;
        int r6 = ((STANDARD_LUMA[ZIGZAG[r5]] * r4) + 50) / 100;
        if (r6 >= 1) goto L15;
        r6 = 1;
    L18:
        if (r6 != (255 & r9[r10 + r5])) goto L19;
        r5 = r5 + 1;
        goto L11
    L19:
        r1 = r1 - 1;
        goto L4
    L15:
        if (r6 <= 255) goto L18;
        r6 = 255;
        goto L18
    L21:
        return r1;
    L8:
        r4 = 200 - (r1 * 2);
        goto L9
    L22:
        return -1;
    }

    private static boolean startsWith(byte[] r4, String r5) {
        if (r4.length > r5.length()) goto L5;
        return false;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= r5.length()) goto L12;
        if (r4[r0] != ((byte) r5.charAt(r0))) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        return false;
    L12:
        return true;
    }

    public String describe() {
        StringBuilder r0 = new StringBuilder();
        if (this.jfifUnits < 0) goto L5;
        r0.append(String.format(Locale.US, "jfif=%d.%02d/u%d/%dx%d", new Object[]{Integer.valueOf(this.jfifVersion >> 8), Integer.valueOf(this.jfifVersion & 255), Integer.valueOf(this.jfifUnits), Integer.valueOf(this.jfifDensityX), Integer.valueOf(this.jfifDensityY)}));
    L7:
        if (this.jfifThumbnail == false) goto L9;
        r0.append("/thumb");
    L9:
        int r1 = this.iccLength;
        if (r1 <= 0) goto L16;
        Locale r2 = Locale.US;
        Integer r12 = Integer.valueOf(r1);
        Integer r3 = Integer.valueOf(this.iccVersion);
        Integer r4 = Integer.valueOf(this.iccYear);
        String r5 = this.iccDescription;
        if (r5 != null) goto L15;
        r5 = "?";
    L15:
        r0.append(String.format(r2, " icc=%d/%04x/%d/%s", new Object[]{r12, r3, r4, r5}));
    L17:
        int r13 = this.quality;
        if (r13 <= 0) goto L21;
        String r14 = String.format(Locale.US, " dqt=q%d", new Object[]{Integer.valueOf(r13)});
    L24:
        r0.append(r14);
        Locale r15 = Locale.US;
        if (this.progressive == false) goto L28;
        String r22 = "prog";
    L27:
        String r32 = r22;
        r0.append(String.format(r15, " sof=%s/%dc/%dx%d/%dx%d", new Object[]{r32, Integer.valueOf(this.components), Integer.valueOf(this.horizontalSampling), Integer.valueOf(this.verticalSampling), Integer.valueOf(this.width), Integer.valueOf(this.height)}));
        r0.append(String.format(r15, " dht=%d", new Object[]{Integer.valueOf(this.huffmanTables)}));
        if (this.exif == false) goto L33;
        r0.append(" exif");
    L33:
        if (this.xmp == false) goto L36;
        r0.append(" xmp");
    L36:
        if (this.restartInterval == false) goto L38;
        r0.append(" dri");
    L38:
        r0.append(" order=");
        r0.append(this.markerOrder);
        return r0.toString();
    L28:
        r22 = "base";
        goto L27
    L21:
        if (this.quantizationTable == false) goto L23;
        r14 = " dqt=custom";
        goto L24
    L23:
        r14 = " dqt=none";
        goto L24
    L16:
        r0.append(" icc=none");
        goto L17
    L5:
        r0.append("jfif=none");
        goto L7
    }
}
