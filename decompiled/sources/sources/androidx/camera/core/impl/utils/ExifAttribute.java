package androidx.camera.core.impl.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
final class ExifAttribute {
    static final Charset ASCII = null;
    static final byte[] EXIF_ASCII_PREFIX = null;
    static final int[] IFD_FORMAT_BYTES_PER_FORMAT = null;
    static final String[] IFD_FORMAT_NAMES = null;
    public final byte[] bytes;
    public final long bytesOffset;
    public final int format;
    public final int numberOfComponents;

    static {
        ASCII = StandardCharsets.US_ASCII;
        IFD_FORMAT_NAMES = new String[]{_UrlKt.FRAGMENT_ENCODE_SET, "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};
        IFD_FORMAT_BYTES_PER_FORMAT = new int[]{0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
        EXIF_ASCII_PREFIX = new byte[]{65, 83, 67, 73, 73, 0, 0, 0};
    }

    public ExifAttribute(int r1, int r2, long r3, byte[] r5) {
        this.format = r1;
        this.numberOfComponents = r2;
        this.bytesOffset = r3;
        this.bytes = r5;
    }

    public ExifAttribute(int r7, int r8, byte[] r9) {
        this(r7, r8, -1, r9);
    }

    public static ExifAttribute createByte(String r5) {
        if (r5.length() == 1) goto L5;
    L10:
        byte[] r52 = r5.getBytes(ASCII);
        return new ExifAttribute(1, r52.length, r52);
    L5:
        if (r5.charAt(0) < '0') goto L10;
        if (r5.charAt(0) > '1') goto L10;
        return new ExifAttribute(1, 1, new byte[]{(byte) (r5.charAt(0) - '0')});
    }

    public static ExifAttribute createDouble(double[] r5, ByteOrder r6) {
        ByteBuffer r0 = ByteBuffer.wrap(new byte[IFD_FORMAT_BYTES_PER_FORMAT[12] * r5.length]);
        r0.order(r6);
        int r62 = r5.length;
        int r2 = 0;
    L3:
        if (r2 >= r62) goto L6;
        r0.putDouble(r5[r2]);
        r2 = r2 + 1;
        goto L3
    L6:
        return new ExifAttribute(12, r5.length, r0.array());
    }

    public static ExifAttribute createSLong(int[] r4, ByteOrder r5) {
        ByteBuffer r0 = ByteBuffer.wrap(new byte[IFD_FORMAT_BYTES_PER_FORMAT[9] * r4.length]);
        r0.order(r5);
        int r52 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r52) goto L6;
        r0.putInt(r4[r2]);
        r2 = r2 + 1;
        goto L3
    L6:
        return new ExifAttribute(9, r4.length, r0.array());
    }

    public static ExifAttribute createSRational(LongRational[] r6, ByteOrder r7) {
        ByteBuffer r0 = ByteBuffer.wrap(new byte[IFD_FORMAT_BYTES_PER_FORMAT[10] * r6.length]);
        r0.order(r7);
        int r72 = r6.length;
        int r2 = 0;
    L3:
        if (r2 >= r72) goto L6;
        LongRational r3 = r6[r2];
        r0.putInt((int) r3.getNumerator());
        r0.putInt((int) r3.getDenominator());
        r2 = r2 + 1;
        goto L3
    L6:
        return new ExifAttribute(10, r6.length, r0.array());
    }

    public static ExifAttribute createString(String r3) {
        byte[] r32 = (r3 + 0).getBytes(ASCII);
        return new ExifAttribute(2, r32.length, r32);
    }

    public static ExifAttribute createULong(long r2, ByteOrder r4) {
        return createULong(new long[]{r2}, r4);
    }

    public static ExifAttribute createULong(long[] r5, ByteOrder r6) {
        ByteBuffer r0 = ByteBuffer.wrap(new byte[IFD_FORMAT_BYTES_PER_FORMAT[4] * r5.length]);
        r0.order(r6);
        int r62 = r5.length;
        int r2 = 0;
    L3:
        if (r2 >= r62) goto L6;
        r0.putInt((int) r5[r2]);
        r2 = r2 + 1;
        goto L3
    L6:
        return new ExifAttribute(4, r5.length, r0.array());
    }

    public static ExifAttribute createURational(LongRational[] r6, ByteOrder r7) {
        ByteBuffer r0 = ByteBuffer.wrap(new byte[IFD_FORMAT_BYTES_PER_FORMAT[5] * r6.length]);
        r0.order(r7);
        int r72 = r6.length;
        int r2 = 0;
    L3:
        if (r2 >= r72) goto L6;
        LongRational r3 = r6[r2];
        r0.putInt((int) r3.getNumerator());
        r0.putInt((int) r3.getDenominator());
        r2 = r2 + 1;
        goto L3
    L6:
        return new ExifAttribute(5, r6.length, r0.array());
    }

    public static ExifAttribute createUShort(int[] r4, ByteOrder r5) {
        ByteBuffer r0 = ByteBuffer.wrap(new byte[IFD_FORMAT_BYTES_PER_FORMAT[3] * r4.length]);
        r0.order(r5);
        int r52 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r52) goto L6;
        r0.putShort((short) r4[r2]);
        r2 = r2 + 1;
        goto L3
    L6:
        return new ExifAttribute(3, r4.length, r0.array());
    }

    public int size() {
        return IFD_FORMAT_BYTES_PER_FORMAT[this.format] * this.numberOfComponents;
    }

    public String toString() {
        return "(" + IFD_FORMAT_NAMES[this.format] + ", data length:" + this.bytes.length + ")";
    }
}
