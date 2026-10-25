package com.google.zxing.common;

import com.google.zxing.DecodeHintType;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Map;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes5.dex */
public abstract class StringUtils {
    private static final boolean ASSUME_SHIFT_JIS = false;
    private static final Charset EUC_JP = null;
    public static final Charset GB2312_CHARSET = null;
    private static final Charset PLATFORM_DEFAULT_ENCODING = null;
    public static final Charset SHIFT_JIS_CHARSET = null;

    static {
        PLATFORM_DEFAULT_ENCODING = Charset.defaultCharset();
        Charset r0 = null;
        Charset r1 = Charset.forName("SJIS");     // Catch: UnsupportedCharsetException -> L5
    L6:
        SHIFT_JIS_CHARSET = r1;
        Charset r12 = Charset.forName("GB2312");     // Catch: UnsupportedCharsetException -> L9
    L10:
        GB2312_CHARSET = r12;
        r0 = Charset.forName("EUC_JP");     // Catch: UnsupportedCharsetException -> L23
    L12:
        EUC_JP = r0;
        Charset r13 = SHIFT_JIS_CHARSET;
        if (r13 != null) goto L15;
    L16:
        if (r0 != null) goto L18;
    L20:
        boolean r02 = false;
    L21:
        ASSUME_SHIFT_JIS = r02;
        return;
    L18:
        if (r0.equals(PLATFORM_DEFAULT_ENCODING) == false) goto L20;
    L19:
        r02 = true;
        goto L21
    L15:
        if (r13.equals(PLATFORM_DEFAULT_ENCODING) == true) goto L19;
    L9:
        r12 = null;
    L5:
        r1 = null;
        goto L6
    }

    public static Charset guessCharset(byte[] r21, Map<DecodeHintType, ?> r22) {
        byte[] r0 = r21;
        if (r22 == null) goto L8;
        DecodeHintType r2 = DecodeHintType.CHARACTER_SET;
        if (r22.containsKey(r2) == false) goto L8;
        return Charset.forName(r22.get(r2).toString());
    L8:
        boolean r3 = true;
        int r4 = 0;
        if (r0.length <= 2) goto L19;
        byte r1 = r0[0];
        if (r1 == (-2)) goto L13;
    L14:
        if (r1 != (-1)) goto L19;
        if (r0[1] != (-2)) goto L19;
    L18:
        return StandardCharsets.UTF_16;
    L13:
        if (r0[1] == (-1)) goto L18;
    L19:
        int r12 = r0.length;
        if (SHIFT_JIS_CHARSET == null) goto L22;
        boolean r5 = true;
    L24:
        if (r0.length > 3) goto L26;
    L32:
        boolean r6 = false;
    L33:
        int r23 = 0;
        int r9 = 0;
        int r10 = 0;
        int r11 = 0;
        int r122 = 0;
        int r13 = 0;
        int r14 = 0;
        int r15 = 0;
        int r16 = 0;
        int r17 = 0;
        int r18 = 0;
        boolean r8 = r5;
        boolean r52 = true;
    L34:
        if (r9 >= r12) goto L39;
        if (r3 == true) goto L40;
        if (r8 == true) goto L40;
        if (r52 == false) goto L39;
    L40:
        byte r7 = r0[r9];
        int r02 = r7 & UByte.MAX_VALUE;
        if (r52 == false) goto L48;
        if (r10 > 0) goto L44;
        boolean r19 = r3;
        if ((r7 & ByteCompanionObject.MIN_VALUE) != 0) goto L52;
    L65:
        if (r19 == false) goto L78;
        if (r02 <= 127) goto L70;
        if (r02 >= 160) goto L70;
        boolean r32 = false;
    L79:
        if (r8 == false) goto L112;
        if (r11 <= 0) goto L89;
        boolean r192 = r32;
        if (r02 < 64) goto L88;
        if (r02 == 127) goto L88;
        if (r02 > 252) goto L88;
        r11 = r11 - 1;
    L113:
        r9 = r9 + 1;
        r0 = r21;
        r3 = r192;
    L88:
        r8 = false;
        goto L113
    L89:
        r192 = r32;
        if (r02 == 128) goto L88;
        if (r02 == 160) goto L88;
        if (r02 > 239) goto L88;
        if (r02 <= 160) goto L105;
        if (r02 >= 224) goto L105;
        r23 = r23 + 1;
        int r03 = r18 + 1;
        if (r03 <= r15) goto L103;
        r15 = r03;
        r18 = r15;
    L102:
        r17 = 0;
        goto L113
    L103:
        r18 = r03;
    L105:
        if (r02 <= 127) goto L111;
        r11 = r11 + 1;
        int r04 = r17 + 1;
        if (r04 <= r4) goto L110;
        r4 = r04;
        r17 = r4;
    L109:
        r18 = 0;
        goto L113
    L110:
        r17 = r04;
        goto L109
    L111:
        r17 = 0;
        goto L109
    L112:
        r192 = r32;
    L70:
        if (r02 <= 159) goto L78;
        if (r02 >= 192) goto L74;
    L77:
        r16 = r16 + 1;
        goto L78
    L74:
        if (r02 == 215) goto L77;
        if (r02 == 247) goto L77;
    L78:
        r32 = r19;
        goto L79
    L52:
        if ((r7 & 64) == 0) goto L46;
        int r33 = r10 + 1;
        if ((r7 & 32) != 0) goto L58;
        r122 = r122 + 1;
    L57:
        r10 = r33;
        goto L65
    L58:
        r33 = r10 + 2;
        if ((r7 & 16) != 0) goto L61;
        r13 = r13 + 1;
        goto L57
    L61:
        r10 = r10 + 3;
        if ((r7 & 8) != 0) goto L46;
        r14 = r14 + 1;
    L46:
        r52 = false;
        goto L65
    L44:
        if ((r7 & ByteCompanionObject.MIN_VALUE) != 0) goto L47;
        r19 = r3;
        goto L46
    L47:
        r10 = r10 - 1;
    L48:
        r19 = r3;
    L39:
        boolean r193 = r3;
        if (r52 == false) goto L117;
        if (r10 <= 0) goto L117;
        r52 = false;
    L117:
        if (r8 == false) goto L120;
        if (r11 <= 0) goto L120;
        r8 = false;
    L120:
        if (r52 == false) goto L126;
        if (r6 == true) goto L125;
        if (((r122 + r13) + r14) <= 0) goto L126;
    L125:
        return StandardCharsets.UTF_8;
    L126:
        if (r8 == true) goto L128;
    L134:
        if (r193 == false) goto L145;
        if (r8 == false) goto L145;
        if (r15 != 2) goto L140;
        if (r23 != 2) goto L140;
    L142:
        return SHIFT_JIS_CHARSET;
    L140:
        if ((r16 * 10) >= r12) goto L142;
        return StandardCharsets.ISO_8859_1;
    L145:
        if (r193 == true) goto L147;
        if (r8 == true) goto L150;
        if (r52 == false) goto L155;
        return StandardCharsets.UTF_8;
    L155:
        return PLATFORM_DEFAULT_ENCODING;
    L150:
        return SHIFT_JIS_CHARSET;
    L147:
        return StandardCharsets.ISO_8859_1;
    L128:
        if (ASSUME_SHIFT_JIS == true) goto L133;
        if (r15 >= 3) goto L133;
        if (r4 < 3) goto L134;
    L133:
        return SHIFT_JIS_CHARSET;
    L26:
        if (r0[0] != (-17)) goto L32;
        if (r0[1] != (-69)) goto L32;
        if (r0[2] != (-65)) goto L32;
        r6 = true;
        goto L33
    L22:
        r5 = false;
        goto L24
    }
}
