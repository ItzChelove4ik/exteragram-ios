package com.android.dx.rop.cst;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.io.Opcodes;
import com.android.dx.rop.type.Type;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public final class CstString extends TypedConstant {
    public static final CstString EMPTY_STRING = null;
    private final ByteArray bytes;
    private final String string;

    static {
        EMPTY_STRING = new CstString(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    public CstString(ByteArray r1) {
        if (r1 == null) goto L6;
        this.bytes = r1;
        this.string = utf8BytesToString(r1).intern();
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("bytes == null");
        throw null;
    }

    public CstString(String r2) {
        if (r2 == null) goto L6;
        this.string = r2.intern();
        this.bytes = new ByteArray(stringToUtf8Bytes(r2));
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("string == null");
        throw null;
    }

    public static byte[] stringToUtf8Bytes(String r9) {
        int r0 = r9.length();
        byte[] r1 = new byte[r0 * 3];
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r3 >= r0) goto L13;
        char r5 = r9.charAt(r3);
        if (r5 == 0) goto L9;
        if (r5 >= 128) goto L9;
        r1[r4] = (byte) r5;
        r4 = r4 + 1;
    L12:
        r3 = r3 + 1;
    L9:
        if (r5 >= 2048) goto L11;
        r1[r4] = (byte) (((r5 >> 6) & 31) | 192);
        r1[r4 + 1] = (byte) ((r5 & '?') | 128);
        r4 = r4 + 2;
        goto L12
    L11:
        r1[r4] = (byte) (((r5 >> '\f') & 15) | Opcodes.SHL_INT_LIT8);
        r1[r4 + 1] = (byte) (((r5 >> 6) & 63) | 128);
        r1[r4 + 2] = (byte) ((r5 & '?') | 128);
        r4 = r4 + 3;
        goto L12
    L13:
        byte[] r92 = new byte[r4];
        System.arraycopy(r1, 0, r92, 0, r4);
        return r92;
    }

    private static String throwBadUtf8(int r3, int r4) {
        throw new IllegalArgumentException("bad utf-8 byte " + Hex.u1(r3) + " at offset " + Hex.u4(r4));
    }

    public static String utf8BytesToString(ByteArray r11) {
        int r0 = r11.size();
        char[] r1 = new char[r0];
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r0 <= 0) goto L46;
        int r5 = r11.getUnsignedByte(r4);
        switch((r5 >> 4)) {
            case 0: goto L39;
            case 1: goto L39;
            case 2: goto L39;
            case 3: goto L39;
            case 4: goto L39;
            case 5: goto L39;
            case 6: goto L39;
            case 7: goto L39;
            case 8: goto L7;
            case 9: goto L7;
            case 10: goto L7;
            case 11: goto L7;
            case 12: goto L25;
            case 13: goto L25;
            case 14: goto L8;
            default: goto L7;
        };
    L8:
        r0 = r0 - 3;
        if (r0 < 0) goto L11;
        int r6 = r4 + 1;
        int r8 = r11.getUnsignedByte(r6);
        int r9 = r8 & 192;
        if (r9 != 128) goto L15;
        int r62 = r4 + 2;
        int r10 = r11.getUnsignedByte(r62);
        if (r9 != 128) goto L19;
        int r52 = (((r5 & 15) << 12) | ((r8 & 63) << 6)) | (r10 & 63);
        if (r52 < 2048) goto L23;
        char r53 = (char) r52;
        r4 = r4 + 3;
    L44:
        r1[r3] = r53;
        r3 = r3 + 1;
        goto L3
    L23:
        return throwBadUtf8(r10, r62);
    L19:
        return throwBadUtf8(r10, r62);
    L15:
        return throwBadUtf8(r8, r6);
    L11:
        return throwBadUtf8(r5, r4);
    L25:
        r0 = r0 - 2;
        if (r0 < 0) goto L28;
        int r63 = r4 + 1;
        int r82 = r11.getUnsignedByte(r63);
        if ((r82 & 192) != 128) goto L32;
        int r54 = ((r5 & 31) << 6) | (r82 & 63);
        if (r54 == 0) goto L38;
        if (r54 >= 128) goto L38;
        return throwBadUtf8(r82, r63);
    L38:
        r53 = (char) r54;
        r4 = r4 + 2;
        goto L44
    L32:
        return throwBadUtf8(r82, r63);
    L28:
        return throwBadUtf8(r5, r4);
    L39:
        r0 = r0 - 1;
        if (r5 == 0) goto L42;
        r53 = (char) r5;
        r4 = r4 + 1;
        goto L44
    L42:
        return throwBadUtf8(r5, r4);
    L7:
        return throwBadUtf8(r5, r4);
    L46:
        return new String(r1, 0, r3);
    }

    @Override // com.android.dx.rop.cst.Constant
    public int compareTo0(Constant r1) {
        return this.string.compareTo(((CstString) r1).string);
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof CstString) == true) goto L7;
        return false;
    L7:
        return this.string.equals(((CstString) r2).string);
    }

    public ByteArray getBytes() {
        return this.bytes;
    }

    public String getString() {
        return this.string;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.STRING;
    }

    public int getUtf16Size() {
        return this.string.length();
    }

    public int getUtf8Size() {
        return this.bytes.size();
    }

    public int hashCode() {
        return this.string.hashCode();
    }

    @Override // com.android.dx.rop.cst.Constant
    public boolean isCategory2() {
        return false;
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        int r0 = this.string.length();
        StringBuilder r1 = new StringBuilder((r0 * 3) / 2);
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L46;
        char r4 = this.string.charAt(r3);
        if (r4 < ' ') goto L14;
        if (r4 >= 127) goto L14;
        if (r4 != '\'') goto L10;
    L12:
        r1.append('\\');
    L13:
        r1.append(r4);
    L44:
        r3 = r3 + 1;
        goto L3
    L10:
        if (r4 == '\"') goto L12;
        if (r4 != '\\') goto L13;
    L14:
        if (r4 <= 127) goto L16;
        r1.append("\\u");
        r1.append(Character.forDigit(r4 >> '\f', 16));
        r1.append(Character.forDigit((r4 >> '\b') & 15, 16));
        r1.append(Character.forDigit((r4 >> 4) & 15, 16));
        r1.append(Character.forDigit(r4 & 15, 16));
        goto L44
    L16:
        if (r4 != '\t') goto L18;
        r1.append("\\t");
        goto L44
    L18:
        if (r4 != '\n') goto L20;
        r1.append("\\n");
        goto L44
    L20:
        if (r4 != '\r') goto L22;
        r1.append("\\r");
        goto L44
    L22:
        if (r3 >= (r0 - 1)) goto L24;
        char r5 = this.string.charAt(r3 + 1);
    L26:
        if (r5 >= '0') goto L28;
    L30:
        boolean r52 = false;
    L31:
        r1.append('\\');
        int r7 = 6;
    L32:
        if (r7 < 0) goto L38;
        char r9 = (char) (((r4 >> r7) & 7) + 48);
        if (r9 != '0') goto L36;
        if (r52 == true) goto L36;
    L37:
        r7 = r7 - 3;
    L36:
        r1.append(r9);
        r52 = true;
        goto L37
    L38:
        if (r52 == true) goto L44;
        r1.append('0');
        goto L44
    L28:
        if (r5 > '7') goto L30;
        r52 = true;
        goto L31
    L24:
        r5 = 0;
        goto L26
    L46:
        return r1.toString();
    }

    public String toQuoted() {
        return "\"" + toHuman() + Typography.quote;
    }

    public String toQuoted(int r3) {
        String r2 = toHuman();
        if (r2.length() > (r3 - 2)) goto L5;
        String r32 = _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return "\"" + r2 + r32 + Typography.quote;
    L5:
        r2 = r2.substring(0, r3 - 5);
        r32 = "...";
        goto L7
    }

    public String toString() {
        return "string{\"" + toHuman() + "\"}";
    }

    @Override // com.android.dx.rop.cst.Constant
    public String typeName() {
        return "utf8";
    }
}
