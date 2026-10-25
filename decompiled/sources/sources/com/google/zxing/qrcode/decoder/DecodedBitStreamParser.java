package com.google.zxing.qrcode.decoder;

import com.android.dx.io.Opcodes;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.StringUtils;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes5.dex */
abstract class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS = null;

    /* renamed from: com.google.zxing.qrcode.decoder.DecodedBitStreamParser$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$qrcode$decoder$Mode = null;

        static {
            int[] r0 = new int[Mode.values().length];
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode = r0;
            r0[Mode.NUMERIC.ordinal()] = 1;     // Catch: NoSuchFieldError -> L14
        L26:
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.ALPHANUMERIC.ordinal()] = 2;     // Catch: NoSuchFieldError -> L15
        L32:
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.BYTE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L16
        L42:
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.KANJI.ordinal()] = 4;     // Catch: NoSuchFieldError -> L17
        L28:
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.TERMINATOR.ordinal()] = 5;     // Catch: NoSuchFieldError -> L18
        L34:
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.FNC1_FIRST_POSITION.ordinal()] = 6;     // Catch: NoSuchFieldError -> L19
        L36:
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.FNC1_SECOND_POSITION.ordinal()] = 7;     // Catch: NoSuchFieldError -> L20
        L38:
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.STRUCTURED_APPEND.ordinal()] = 8;     // Catch: NoSuchFieldError -> L21
        L24:
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.ECI.ordinal()] = 9;     // Catch: NoSuchFieldError -> L22
        L30:
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.HANZI.ordinal()] = 10;     // Catch: NoSuchFieldError -> L23
            return;
        }
    }

    static {
        ALPHANUMERIC_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".toCharArray();
    }

    public static DecoderResult decode(byte[] r19, Version r20, ErrorCorrectionLevel r21, Map<DecodeHintType, ?> r22) {
        byte[] r7 = r19;
        BitSource r1 = new BitSource(r7);
        StringBuilder r2 = new StringBuilder(50);
        int r8 = 1;
        ArrayList r5 = new ArrayList(1);
        int r10 = -1;
        int r11 = -1;
        boolean r12 = false;
        boolean r13 = false;
        boolean r14 = false;
        CharacterSetECI r4 = null;
    L64:
        if (r1.available() >= 4) goto L6;
        Mode r3 = Mode.TERMINATOR;     // Catch: IllegalArgumentException -> L62
    L7:
        int[] r6 = AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode;     // Catch: IllegalArgumentException -> L62
        switch(r6[r3.ordinal()]) {
            case 5: goto L17;
            case 6: goto L38;
            case 7: goto L37;
            case 8: goto L32;
            case 9: goto L27;
            case 10: goto L24;
            default: goto L9;
        };     // Catch: IllegalArgumentException -> L62
    L9:
        int r9 = r1.readBits(r3.getCharacterCountBits(r20));     // Catch: IllegalArgumentException -> L62
        int r62 = r6[r3.ordinal()];     // Catch: IllegalArgumentException -> L62
        if (r62 == 1) goto L23;
        if (r62 != 2) goto L13;
        Mode r92 = r3;
        decodeAlphanumericSegment(r1, r2, r9, r12);     // Catch: IllegalArgumentException -> L62
    L18:
        int r15 = 4;
    L40:
        if (r92 == Mode.TERMINATOR) goto L41;
        r7 = r19;
        goto L64
    L41:
        if (r4 == null) goto L48;
        if (r13 == false) goto L44;
        StringBuilder r32 = r2;
        int r93 = r15;
    L52:
        String r42 = r32.toString();
        if (r5.isEmpty() == false) goto L55;
        r5 = null;
    L55:
        if (r21 != null) goto L58;
        String r63 = null;
    L60:
        return new DecoderResult(r7, r42, r5, r63, r10, r11, r93);
    L58:
        r63 = r21.toString();
        goto L60
    L44:
        if (r14 == false) goto L47;
        r8 = 6;
    L46:
        r32 = r2;
        r93 = r8;
        goto L52
    L47:
        r32 = r2;
        r93 = 2;
        goto L52
    L48:
        if (r13 == false) goto L50;
        r32 = r2;
        r93 = 3;
        goto L52
    L50:
        if (r14 == false) goto L46;
        r8 = 5;
        goto L46
    L13:
        if (r62 != 3) goto L15;
        r92 = r3;
        decodeByteSegment(r1, r2, r9, r4, r5, r22);     // Catch: IllegalArgumentException -> L62
        goto L18
    L15:
        if (r62 != 4) goto L20;
        decodeKanjiSegment(r1, r2, r9);     // Catch: IllegalArgumentException -> L62
        goto L17
    L20:
        throw FormatException.getFormatInstance();     // Catch: IllegalArgumentException -> L62
    L23:
        r92 = r3;
        decodeNumericSegment(r1, r2, r9);     // Catch: IllegalArgumentException -> L62
    L17:
        r92 = r3;
        goto L18
    L24:
        r92 = r3;
        r15 = 4;
        int r33 = r1.readBits(4);     // Catch: IllegalArgumentException -> L62
        int r64 = r1.readBits(r92.getCharacterCountBits(r20));     // Catch: IllegalArgumentException -> L62
        if (r33 != 1) goto L40;
        decodeHanziSegment(r1, r2, r64);     // Catch: IllegalArgumentException -> L62
        goto L40
    L27:
        r92 = r3;
        r15 = 4;
        r4 = CharacterSetECI.getCharacterSetECIByValue(parseECIValue(r1));     // Catch: IllegalArgumentException -> L62
        if (r4 != null) goto L40;
        throw FormatException.getFormatInstance();     // Catch: IllegalArgumentException -> L62
    L32:
        r92 = r3;
        r15 = 4;
        if (r1.available() < 16) goto L36;
        r10 = r1.readBits(8);     // Catch: IllegalArgumentException -> L62
        r11 = r1.readBits(8);     // Catch: IllegalArgumentException -> L62
        goto L40
    L36:
        throw FormatException.getFormatInstance();     // Catch: IllegalArgumentException -> L62
    L37:
        r92 = r3;
        r15 = 4;
        r12 = true;
        r14 = true;
        goto L40
    L38:
        r92 = r3;
        r15 = 4;
        r12 = true;
        r13 = true;
        goto L40
    L6:
        r3 = Mode.forBits(r1.readBits(4));     // Catch: IllegalArgumentException -> L62
        goto L7
    L63:
        throw FormatException.getFormatInstance();
    }

    private static void decodeAlphanumericSegment(BitSource r3, StringBuilder r4, int r5, boolean r6) {
        int r0 = r4.length();
    L4:
        if (r5 <= 1) goto L10;
        if (r3.available() < 11) goto L9;
        int r1 = r3.readBits(11);
        r4.append(toAlphaNumericChar(r1 / 45));
        r4.append(toAlphaNumericChar(r1 % 45));
        r5 = r5 - 2;
        goto L4
    L9:
        throw FormatException.getFormatInstance();
    L10:
        if (r5 == 1) goto L12;
    L16:
        if (r6 == true) goto L18;
        return;
    L18:
        if (r0 >= r4.length()) goto L35;
        if (r4.charAt(r0) != '%') goto L27;
        if (r0 >= (r4.length() - 1)) goto L26;
        int r32 = r0 + 1;
        if (r4.charAt(r32) != '%') goto L26;
        r4.deleteCharAt(r32);
    L26:
        r4.setCharAt(r0, 29);
    L27:
        r0 = r0 + 1;
        goto L18
    L35:
        return;
    L12:
        if (r3.available() < 6) goto L15;
        r4.append(toAlphaNumericChar(r3.readBits(6)));
        goto L16
    L15:
        throw FormatException.getFormatInstance();
    }

    private static void decodeByteSegment(BitSource r3, StringBuilder r4, int r5, CharacterSetECI r6, Collection<byte[]> r7, Map<DecodeHintType, ?> r8) {
        if ((r5 * 8) > r3.available()) goto L13;
        byte[] r0 = new byte[r5];
        int r1 = 0;
    L5:
        if (r1 >= r5) goto L7;
        r0[r1] = (byte) r3.readBits(8);
        r1 = r1 + 1;
        goto L5
    L7:
        if (r6 != null) goto L9;
        Charset r32 = StringUtils.guessCharset(r0, r8);
    L10:
        r4.append(new String(r0, r32));
        r7.add(r0);
        return;
    L9:
        r32 = r6.getCharset();
        goto L10
    L13:
        throw FormatException.getFormatInstance();
    }

    private static void decodeHanziSegment(BitSource r4, StringBuilder r5, int r6) {
        if (StringUtils.GB2312_CHARSET == null) goto L19;
        if ((r6 * 13) > r4.available()) goto L17;
        byte[] r0 = new byte[r6 * 2];
        int r1 = 0;
    L7:
        if (r6 <= 0) goto L14;
        int r2 = r4.readBits(13);
        int r22 = (r2 % 96) | ((r2 / 96) << 8);
        if (r22 >= 2560) goto L12;
        int r3 = 41377;
    L11:
        int r23 = r22 + r3;
        r0[r1] = (byte) ((r23 >> 8) & 255);
        r0[r1 + 1] = (byte) (r23 & 255);
        r1 = r1 + 2;
        r6 = r6 - 1;
        goto L7
    L12:
        r3 = 42657;
        goto L11
    L14:
        r5.append(new String(r0, StringUtils.GB2312_CHARSET));
        return;
    L17:
        throw FormatException.getFormatInstance();
    L19:
        throw FormatException.getFormatInstance();
    }

    private static void decodeKanjiSegment(BitSource r4, StringBuilder r5, int r6) {
        if (StringUtils.SHIFT_JIS_CHARSET == null) goto L19;
        if ((r6 * 13) > r4.available()) goto L17;
        byte[] r0 = new byte[r6 * 2];
        int r1 = 0;
    L7:
        if (r6 <= 0) goto L14;
        int r2 = r4.readBits(13);
        int r22 = (r2 % 192) | ((r2 / 192) << 8);
        if (r22 >= 7936) goto L12;
        int r3 = 33088;
    L11:
        int r23 = r22 + r3;
        r0[r1] = (byte) (r23 >> 8);
        r0[r1 + 1] = (byte) r23;
        r1 = r1 + 2;
        r6 = r6 - 1;
        goto L7
    L12:
        r3 = 49472;
        goto L11
    L14:
        r5.append(new String(r0, StringUtils.SHIFT_JIS_CHARSET));
        return;
    L17:
        throw FormatException.getFormatInstance();
    L19:
        throw FormatException.getFormatInstance();
    }

    private static void decodeNumericSegment(BitSource r3, StringBuilder r4, int r5) {
    L3:
        if (r5 < 3) goto L14;
        if (r3.available() < 10) goto L12;
        int r0 = r3.readBits(10);
        if (r0 >= 1000) goto L10;
        r4.append(toAlphaNumericChar(r0 / 100));
        r4.append(toAlphaNumericChar((r0 / 10) % 10));
        r4.append(toAlphaNumericChar(r0 % 10));
        r5 = r5 - 3;
        goto L3
    L10:
        throw FormatException.getFormatInstance();
    L12:
        throw FormatException.getFormatInstance();
    L14:
        if (r5 != 2) goto L26;
        if (r3.available() < 7) goto L24;
        int r32 = r3.readBits(7);
        if (r32 >= 100) goto L22;
        r4.append(toAlphaNumericChar(r32 / 10));
        r4.append(toAlphaNumericChar(r32 % 10));
        return;
    L22:
        throw FormatException.getFormatInstance();
    L24:
        throw FormatException.getFormatInstance();
    L26:
        if (r5 == 1) goto L28;
        return;
    L28:
        if (r3.available() < 4) goto L36;
        int r33 = r3.readBits(4);
        if (r33 >= 10) goto L34;
        r4.append(toAlphaNumericChar(r33));
        return;
    L34:
        throw FormatException.getFormatInstance();
    L36:
        throw FormatException.getFormatInstance();
    }

    private static int parseECIValue(BitSource r4) {
        int r1 = r4.readBits(8);
        if ((r1 & 128) != 0) goto L7;
        return r1 & 127;
    L7:
        if ((r1 & 192) != 128) goto L11;
        return r4.readBits(8) | ((r1 & 63) << 8);
    L11:
        if ((r1 & Opcodes.SHL_INT_LIT8) != 192) goto L15;
        return r4.readBits(16) | ((r1 & 31) << 16);
    L15:
        throw FormatException.getFormatInstance();
    }

    private static char toAlphaNumericChar(int r2) {
        char[] r0 = ALPHANUMERIC_CHARS;
        if (r2 >= r0.length) goto L7;
        return r0[r2];
    L7:
        throw FormatException.getFormatInstance();
    }
}
