package com.google.zxing.qrcode.encoder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.StringUtils;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import com.google.zxing.qrcode.encoder.MinimalEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Map;
import kotlin.UByte;

/* loaded from: classes5.dex */
public abstract class Encoder {
    private static final int[] ALPHANUMERIC_TABLE = null;
    static final Charset DEFAULT_BYTE_MODE_ENCODING = null;

    /* renamed from: com.google.zxing.qrcode.encoder.Encoder$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$qrcode$decoder$Mode = null;

        static {
            int[] r0 = new int[Mode.values().length];
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode = r0;
            r0[Mode.NUMERIC.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.ALPHANUMERIC.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.BYTE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.KANJI.ordinal()] = 4;     // Catch: NoSuchFieldError -> L11
            return;
        }
    }

    static {
        ALPHANUMERIC_TABLE = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};
        DEFAULT_BYTE_MODE_ENCODING = StandardCharsets.ISO_8859_1;
    }

    public static void append8BitBytes(String r3, BitArray r4, Charset r5) {
        byte[] r32 = r3.getBytes(r5);
        int r52 = r32.length;
        int r0 = 0;
    L3:
        if (r0 >= r52) goto L5;
        r4.appendBits(r32[r0], 8);
        r0 = r0 + 1;
        goto L3
    }

    public static void appendAlphanumericBytes(CharSequence r5, BitArray r6) {
        int r0 = r5.length();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L16;
        int r2 = getAlphanumericCode(r5.charAt(r1));
        if (r2 == (-1)) goto L15;
        int r4 = r1 + 1;
        if (r4 < r0) goto L8;
        r6.appendBits(r2, 6);
        r1 = r4;
        goto L3
    L8:
        int r42 = getAlphanumericCode(r5.charAt(r4));
        if (r42 == (-1)) goto L12;
        r6.appendBits((r2 * 45) + r42, 11);
        r1 = r1 + 2;
        goto L3
    L12:
        throw new WriterException();
    L15:
        throw new WriterException();
    }

    public static void appendBytes(String r2, Mode r3, BitArray r4, Charset r5) {
        int r0 = AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode[r3.ordinal()];
        if (r0 != 1) goto L5;
        appendNumericBytes(r2, r4);
        return;
    L5:
        if (r0 != 2) goto L7;
        appendAlphanumericBytes(r2, r4);
        return;
    L7:
        if (r0 != 3) goto L9;
        append8BitBytes(r2, r4, r5);
        return;
    L9:
        if (r0 != 4) goto L13;
        appendKanjiBytes(r2, r4);
        return;
    L13:
        throw new WriterException("Invalid mode: " + r3);
    }

    private static void appendECI(CharacterSetECI r2, BitArray r3) {
        r3.appendBits(Mode.ECI.getBits(), 4);
        r3.appendBits(r2.getValue(), 8);
    }

    public static void appendKanjiBytes(String r6, BitArray r7) {
        Charset r0 = StringUtils.SHIFT_JIS_CHARSET;
        if (r0 == null) goto L27;
        byte[] r62 = r6.getBytes(r0);
        if ((r62.length % 2) != 0) goto L25;
        int r02 = r62.length - 1;
        int r1 = 0;
    L7:
        if (r1 >= r02) goto L23;
        int r2 = ((r62[r1] & UByte.MAX_VALUE) << 8) | (r62[r1 + 1] & UByte.MAX_VALUE);
        int r3 = 33088;
        if (r2 < 33088) goto L14;
        if (r2 > 40956) goto L14;
    L12:
        int r22 = r2 - r3;
    L19:
        if (r22 == (-1)) goto L22;
        r7.appendBits(((r22 >> 8) * 192) + (r22 & 255), 13);
        r1 = r1 + 2;
        goto L7
    L22:
        throw new WriterException("Invalid byte sequence");
    L14:
        if (r2 >= 57408) goto L16;
    L18:
        r22 = -1;
        goto L19
    L16:
        if (r2 > 60351) goto L18;
        r3 = 49472;
        goto L12
    L23:
        return;
    L25:
        throw new WriterException("Kanji byte size not even");
    L27:
        throw new WriterException("SJIS Charset not supported on this platform");
    }

    public static void appendLengthInfo(int r1, Version r2, Mode r3, BitArray r4) {
        int r22 = r3.getCharacterCountBits(r2);
        int r0 = 1 << r22;
        if (r1 >= r0) goto L7;
        r4.appendBits(r1, r22);
        return;
    L7:
        throw new WriterException(r1 + " is bigger than " + (r0 - 1));
    }

    public static void appendModeInfo(Mode r1, BitArray r2) {
        r2.appendBits(r1.getBits(), 4);
    }

    public static void appendNumericBytes(CharSequence r6, BitArray r7) {
        int r0 = r6.length();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L11;
        int r2 = r6.charAt(r1) - '0';
        int r3 = r1 + 2;
        if (r3 < r0) goto L6;
        r1 = r1 + 1;
        if (r1 < r0) goto L9;
        r7.appendBits(r2, 4);
        goto L3
    L9:
        r7.appendBits((r2 * 10) + (r6.charAt(r1) - '0'), 7);
        r1 = r3;
        goto L3
    L6:
        r7.appendBits(((r2 * 100) + ((r6.charAt(r1 + 1) - '0') * 10)) + (r6.charAt(r3) - '0'), 10);
        r1 = r1 + 3;
        goto L3
    }

    private static int calculateBitsNeeded(Mode r0, BitArray r1, BitArray r2, Version r3) {
        return (r1.getSize() + r0.getCharacterCountBits(r3)) + r2.getSize();
    }

    private static int calculateMaskPenalty(ByteMatrix r2) {
        return ((MaskUtil.applyMaskPenaltyRule1(r2) + MaskUtil.applyMaskPenaltyRule2(r2)) + MaskUtil.applyMaskPenaltyRule3(r2)) + MaskUtil.applyMaskPenaltyRule4(r2);
    }

    private static int chooseMaskPattern(BitArray r4, ErrorCorrectionLevel r5, Version r6, ByteMatrix r7) {
        int r0 = Integer.MAX_VALUE;
        int r1 = -1;
        int r2 = 0;
    L4:
        if (r2 >= 8) goto L9;
        MatrixUtil.buildMatrix(r4, r5, r6, r2, r7);
        int r3 = calculateMaskPenalty(r7);
        if (r3 >= r0) goto L8;
        r1 = r2;
        r0 = r3;
    L8:
        r2 = r2 + 1;
        goto L4
    L9:
        return r1;
    }

    private static Mode chooseMode(String r5, Charset r6) {
        Charset r0 = StringUtils.SHIFT_JIS_CHARSET;
        if (r0 != null) goto L5;
    L10:
        int r62 = 0;
        boolean r02 = false;
        boolean r1 = false;
    L12:
        if (r62 >= r5.length()) goto L24;
        char r2 = r5.charAt(r62);
        if (r2 < '0') goto L19;
        if (r2 > '9') goto L19;
        r1 = true;
    L21:
        r62 = r62 + 1;
    L19:
        if (getAlphanumericCode(r2) == (-1)) goto L23;
        r02 = true;
        goto L21
    L23:
        return Mode.BYTE;
    L24:
        if (r02 == true) goto L26;
        if (r1 == false) goto L31;
        return Mode.NUMERIC;
    L31:
        return Mode.BYTE;
    L26:
        return Mode.ALPHANUMERIC;
    L5:
        if (r0.equals(r6) == false) goto L10;
        if (isOnlyDoubleByteKanji(r5) == false) goto L10;
        return Mode.KANJI;
    }

    private static Version chooseVersion(int r3, ErrorCorrectionLevel r4) {
        int r0 = 1;
    L4:
        if (r0 > 40) goto L10;
        Version r1 = Version.getVersionForNumber(r0);
        if (willFit(r3, r1, r4) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L10:
        throw new WriterException("Data too big");
    }

    public static QRCode encode(String r6, ErrorCorrectionLevel r7, Map<EncodeHintType, ?> r8) {
        boolean r0 = false;
        if (r8 == null) goto L9;
        EncodeHintType r2 = EncodeHintType.GS1_FORMAT;
        if (r8.containsKey(r2) == false) goto L9;
        if (Boolean.parseBoolean(r8.get(r2).toString()) == false) goto L9;
        boolean r22 = true;
    L10:
        if (r8 == null) goto L16;
        EncodeHintType r3 = EncodeHintType.QR_COMPACT;
        if (r8.containsKey(r3) == false) goto L16;
        if (Boolean.parseBoolean(r8.get(r3).toString()) == false) goto L16;
        boolean r32 = true;
    L17:
        Charset r4 = DEFAULT_BYTE_MODE_ENCODING;
        if (r8 != null) goto L20;
    L22:
        if (r0 == true) goto L65;
    L24:
        if (r32 == false) goto L29;
        Mode r02 = Mode.BYTE;
        if (r4.equals(DEFAULT_BYTE_MODE_ENCODING) == false) goto L28;
        r4 = null;
    L28:
        MinimalEncoder.ResultList r62 = MinimalEncoder.encode(r6, null, r4, r22, r7);
        BitArray r1 = new BitArray();
        r62.getBits(r1);
        Version r63 = r62.getVersion();
    L52:
        Version.ECBlocks r23 = r63.getECBlocksForLevel(r7);
        int r33 = r63.getTotalCodewords() - r23.getTotalECCodewords();
        terminateBits(r33, r1);
        BitArray r12 = interleaveWithECBytes(r1, r63.getTotalCodewords(), r33, r23.getNumBlocks());
        QRCode r24 = new QRCode();
        r24.setECLevel(r7);
        r24.setMode(r02);
        r24.setVersion(r63);
        int r03 = r63.getDimensionForVersion();
        ByteMatrix r34 = new ByteMatrix(r03, r03);
        if (r8 == null) goto L59;
        EncodeHintType r42 = EncodeHintType.QR_MASK_PATTERN;
        if (r8.containsKey(r42) == false) goto L59;
        int r82 = Integer.parseInt(r8.get(r42).toString());
        if (QRCode.isValidMaskPattern(r82) == false) goto L59;
    L60:
        if (r82 != (-1)) goto L62;
        r82 = chooseMaskPattern(r12, r7, r63, r34);
    L62:
        r24.setMaskPattern(r82);
        MatrixUtil.buildMatrix(r12, r7, r63, r82, r34);
        r24.setMatrix(r34);
        return r24;
    L59:
        r82 = -1;
        goto L60
    L29:
        Mode r13 = chooseMode(r6, r4);
        BitArray r35 = new BitArray();
        Mode r5 = Mode.BYTE;
        if (r13 != r5) goto L35;
        if (r0 == false) goto L35;
        CharacterSetECI r04 = CharacterSetECI.getCharacterSetECI(r4);
        if (r04 == null) goto L35;
        appendECI(r04, r35);
    L35:
        if (r22 == false) goto L37;
        appendModeInfo(Mode.FNC1_FIRST_POSITION, r35);
    L37:
        appendModeInfo(r13, r35);
        BitArray r05 = new BitArray();
        appendBytes(r6, r13, r05, r4);
        if (r8 == null) goto L46;
        EncodeHintType r25 = EncodeHintType.QR_VERSION;
        if (r8.containsKey(r25) == false) goto L46;
        Version r26 = Version.getVersionForNumber(Integer.parseInt(r8.get(r25).toString()));
        if (willFit(calculateBitsNeeded(r13, r35, r05, r26), r26, r7) == false) goto L45;
    L47:
        BitArray r43 = new BitArray();
        r43.appendBitArray(r35);
        if (r13 != r5) goto L50;
        int r64 = r05.getSizeInBytes();
    L51:
        appendLengthInfo(r64, r26, r13, r43);
        r43.appendBitArray(r05);
        r02 = r13;
        r63 = r26;
        r1 = r43;
        goto L52
    L50:
        r64 = r6.length();
        goto L51
    L45:
        throw new WriterException("Data too big for requested version");
    L46:
        r26 = recommendVersion(r7, r13, r35, r05);
        goto L47
    L65:
        r4 = Charset.forName(r8.get(EncodeHintType.CHARACTER_SET).toString());     // Catch: UnsupportedCharsetException -> L64
        goto L24
    L20:
        if (r8.containsKey(EncodeHintType.CHARACTER_SET) == false) goto L22;
        r0 = true;
    L16:
        r32 = false;
    L9:
        r22 = false;
        goto L10
    }

    public static byte[] generateECBytes(byte[] r5, int r6) {
        int r0 = r5.length;
        int[] r1 = new int[r0 + r6];
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L5;
        r1[r3] = r5[r3] & UByte.MAX_VALUE;
        r3 = r3 + 1;
        goto L3
    L5:
        new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(r1, r6);
        byte[] r52 = new byte[r6];
    L6:
        if (r2 >= r6) goto L8;
        r52[r2] = (byte) r1[r0 + r2];
        r2 = r2 + 1;
        goto L6
    L8:
        return r52;
    }

    public static int getAlphanumericCode(int r2) {
        int[] r0 = ALPHANUMERIC_TABLE;
        if (r2 < r0.length) goto L5;
        return -1;
    L5:
        return r0[r2];
    }

    public static void getNumDataBytesAndNumECBytesForBlockID(int r6, int r7, int r8, int r9, int[] r10, int[] r11) {
        if (r9 >= r8) goto L22;
        int r0 = r6 % r8;
        int r1 = r8 - r0;
        int r2 = r6 / r8;
        int r3 = r2 + 1;
        int r72 = r7 / r8;
        int r4 = r72 + 1;
        int r22 = r2 - r72;
        int r32 = r3 - r4;
        if (r22 != r32) goto L20;
        if (r8 != (r1 + r0)) goto L18;
        if (r6 != (((r72 + r22) * r1) + ((r4 + r32) * r0))) goto L16;
        if (r9 >= r1) goto L13;
        r10[0] = r72;
        r11[0] = r22;
        return;
    L13:
        r10[0] = r4;
        r11[0] = r32;
        return;
    L16:
        throw new WriterException("Total bytes mismatch");
    L18:
        throw new WriterException("RS blocks mismatch");
    L20:
        throw new WriterException("EC bytes mismatch");
    L22:
        throw new WriterException("Block ID too large");
    }

    public static BitArray interleaveWithECBytes(BitArray r11, int r12, int r13, int r14) {
        if (r11.getSizeInBytes() != r13) goto L34;
        ArrayList r0 = new ArrayList(r14);
        int r5 = 0;
        int r8 = 0;
        int r9 = 0;
        int r10 = 0;
    L5:
        if (r5 >= r14) goto L7;
        int[] r6 = new int[1];
        int[] r7 = new int[1];
        int r2 = r12;
        int r3 = r13;
        int r4 = r14;
        getNumDataBytesAndNumECBytesForBlockID(r2, r3, r4, r5, r6, r7);
        int r122 = r6[0];
        byte[] r132 = new byte[r122];
        r11.toBytes(r8 * 8, r132, 0, r122);
        byte[] r142 = generateECBytes(r132, r7[0]);
        r0.add(new BlockPair(r132, r142));
        r9 = Math.max(r9, r122);
        r10 = Math.max(r10, r142.length);
        r8 = r8 + r6[0];
        r5 = r5 + 1;
        r12 = r2;
        r13 = r3;
        r14 = r4;
        goto L5
    L7:
        int r22 = r12;
        if (r13 != r8) goto L32;
        BitArray r112 = new BitArray();
        int r123 = 0;
    L11:
        if (r123 >= r9) goto L18;
        int r143 = r0.size();
        int r32 = 0;
    L13:
        if (r32 >= r143) goto L17;
        Object r42 = r0.get(r32);
        r32 = r32 + 1;
        byte[] r43 = ((BlockPair) r42).getDataBytes();
        if (r123 >= r43.length) goto L13;
        r112.appendBits(r43[r123], 8);
        goto L13
    L17:
        r123 = r123 + 1;
        goto L11
    L18:
        int r124 = 0;
    L19:
        if (r124 >= r10) goto L27;
        int r144 = r0.size();
        int r33 = 0;
    L21:
        if (r33 >= r144) goto L25;
        Object r44 = r0.get(r33);
        r33 = r33 + 1;
        byte[] r45 = ((BlockPair) r44).getErrorCorrectionBytes();
        if (r124 >= r45.length) goto L21;
        r112.appendBits(r45[r124], 8);
        goto L21
    L25:
        r124 = r124 + 1;
        goto L19
    L27:
        if (r22 != r112.getSizeInBytes()) goto L30;
        return r112;
    L30:
        throw new WriterException("Interleaving error: " + r22 + " and " + r112.getSizeInBytes() + " differ.");
    L32:
        throw new WriterException("Data bytes does not match offset");
    L34:
        throw new WriterException("Number of bits and data bytes does not match");
    }

    public static boolean isOnlyDoubleByteKanji(String r5) {
        byte[] r52 = r5.getBytes(StringUtils.SHIFT_JIS_CHARSET);
        int r0 = r52.length;
        if ((r0 % 2) == 0) goto L5;
        return false;
    L5:
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L18;
        int r3 = r52[r1] & UByte.MAX_VALUE;
        if (r3 < 129) goto L12;
        if (r3 > 159) goto L12;
    L16:
        r1 = r1 + 2;
    L12:
        if (r3 < 224) goto L17;
        if (r3 <= 235) goto L16;
    L17:
        return false;
    L18:
        return true;
    }

    private static Version recommendVersion(ErrorCorrectionLevel r1, Mode r2, BitArray r3, BitArray r4) {
        return chooseVersion(calculateBitsNeeded(r2, r3, r4, chooseVersion(calculateBitsNeeded(r2, r3, r4, Version.getVersionForNumber(1)), r1)), r1);
    }

    public static void terminateBits(int r4, BitArray r5) {
        int r0 = r4 * 8;
        if (r5.getSize() > r0) goto L27;
        int r1 = 0;
        int r2 = 0;
    L6:
        if (r2 >= 4) goto L10;
        if (r5.getSize() >= r0) goto L10;
        r5.appendBit(false);
        r2 = r2 + 1;
    L10:
        int r22 = r5.getSize() & 7;
        if (r22 <= 0) goto L14;
    L12:
        if (r22 >= 8) goto L14;
        r5.appendBit(false);
        r22 = r22 + 1;
    L14:
        int r42 = r4 - r5.getSizeInBytes();
    L15:
        if (r1 >= r42) goto L22;
        if ((r1 & 1) != 0) goto L19;
        int r23 = 236;
    L20:
        r5.appendBits(r23, 8);
        r1 = r1 + 1;
        goto L15
    L19:
        r23 = 17;
        goto L20
    L22:
        if (r5.getSize() != r0) goto L25;
        return;
    L25:
        throw new WriterException("Bits size does not equal capacity");
    L27:
        throw new WriterException("data bits cannot fit in the QR Code" + r5.getSize() + " > " + r0);
    }

    public static boolean willFit(int r1, Version r2, ErrorCorrectionLevel r3) {
        if ((r2.getTotalCodewords() - r2.getECBlocksForLevel(r3).getTotalECCodewords()) < ((r1 + 7) / 8)) goto L6;
        return true;
    L6:
        return false;
    }
}
