package okhttp3.internal.url;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.internal._UtilCommonKt;
import okio.Buffer;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aV\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0000\u001a\\\u0010\u001f\u001a\u00020\u0005*\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u00192\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0000\u001a,\u0010 \u001a\u00020\u0011*\u00020\u00122\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0019H\u0000\u001aP\u0010\"\u001a\u00020\u0005*\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u0019H\u0000\u001a*\u0010#\u001a\u00020\u0005*\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u001b\u001a\u00020\u0019H\u0000\u001a\u001c\u0010$\u001a\u00020\u0019*\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"HEX_DIGITS", _UrlKt.FRAGMENT_ENCODE_SET, "getHEX_DIGITS", "()[C", "USERNAME_ENCODE_SET", _UrlKt.FRAGMENT_ENCODE_SET, "PASSWORD_ENCODE_SET", "PATH_SEGMENT_ENCODE_SET", "PATH_SEGMENT_ENCODE_SET_URI", "QUERY_ENCODE_SET", "QUERY_COMPONENT_REENCODE_SET", "QUERY_COMPONENT_ENCODE_SET", "QUERY_COMPONENT_ENCODE_SET_URI", "FORM_ENCODE_SET", "FRAGMENT_ENCODE_SET", "FRAGMENT_ENCODE_SET_URI", "writeCanonicalized", _UrlKt.FRAGMENT_ENCODE_SET, "Lokio/Buffer;", PluginsConstants.Settings.TYPE_INPUT, "pos", _UrlKt.FRAGMENT_ENCODE_SET, "limit", "encodeSet", "alreadyEncoded", _UrlKt.FRAGMENT_ENCODE_SET, "strict", "plusIsSpace", "unicodeAllowed", "charset", "Ljava/nio/charset/Charset;", "canonicalizeWithCharset", "writePercentDecoded", "encoded", "canonicalize", "percentDecode", "isPercentEncoded", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class _UrlKt {
    public static final String FORM_ENCODE_SET = " !\"#$&'()+,/:;<=>?@[\\]^`{|}~";
    public static final String FRAGMENT_ENCODE_SET = "";
    public static final String FRAGMENT_ENCODE_SET_URI = " \"#<>\\^`{|}";
    private static final char[] HEX_DIGITS = null;
    public static final String PASSWORD_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    public static final String PATH_SEGMENT_ENCODE_SET = " \"<>^`{}|/\\?#";
    public static final String PATH_SEGMENT_ENCODE_SET_URI = "[]";
    public static final String QUERY_COMPONENT_ENCODE_SET = " !\"#$&'(),/:;<=>?@[]\\^`{|}~";
    public static final String QUERY_COMPONENT_ENCODE_SET_URI = "\\^`{|}";
    public static final String QUERY_COMPONENT_REENCODE_SET = " \"'<>#&=";
    public static final String QUERY_ENCODE_SET = " \"'<>#";
    public static final String USERNAME_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";

    static {
        HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    public static final String canonicalize(String r11, int r12, int r13, String r14, boolean r15, boolean r16, boolean r17, boolean r18) {
        return canonicalizeWithCharset$default(r11, r12, r13, r14, r15, r16, r17, r18, null, 128, null);
    }

    public static /* synthetic */ String canonicalize$default(String r1, int r2, int r3, String r4, boolean r5, boolean r6, boolean r7, boolean r8, int r9, Object r10) {
        if ((r9 & 1) == 0) goto L6;
        r2 = 0;
    L6:
        if ((r9 & 2) == 0) goto L9;
        r3 = r1.length();
    L9:
        if ((r9 & 8) == 0) goto L12;
        r5 = false;
    L12:
        if ((r9 & 16) == 0) goto L15;
        r6 = false;
    L15:
        if ((r9 & 32) == 0) goto L18;
        r7 = false;
    L18:
        if ((r9 & 64) == 0) goto L21;
        r8 = false;
    L21:
        return canonicalize(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static final String canonicalizeWithCharset(String r10, int r11, int r12, String r13, boolean r14, boolean r15, boolean r16, boolean r17, Charset r18) {
        int r2 = r11;
    L3:
        if (r2 >= r12) goto L27;
        int r0 = r10.codePointAt(r2);
        if (r0 < 32) goto L24;
        if (r0 == 127) goto L24;
        if (r0 < 128) goto L12;
        if (r17 == false) goto L24;
    L12:
        if (StringsKt.contains$default(r13, (char) r0, false, 2, null) == true) goto L24;
        if (r0 != 37) goto L20;
        if (r14 == false) goto L24;
        if (r15 == false) goto L20;
        if (isPercentEncoded(r10, r2, r12) == false) goto L24;
    L20:
        if (r0 != 43) goto L23;
        if (r16 == true) goto L24;
    L23:
        r2 = r2 + Character.charCount(r0);
    L24:
        Buffer r02 = new Buffer();
        r02.writeUtf8(r10, r11, r2);
        writeCanonicalized(r02, r10, r2, r12, r13, r14, r15, r16, r17, r18);
        return r02.readUtf8();
    L27:
        return r10.substring(r11, r12);
    }

    public static /* synthetic */ String canonicalizeWithCharset$default(String r1, int r2, int r3, String r4, boolean r5, boolean r6, boolean r7, boolean r8, Charset r9, int r10, Object r11) {
        if ((r10 & 1) == 0) goto L6;
        r2 = 0;
    L6:
        if ((r10 & 2) == 0) goto L9;
        r3 = r1.length();
    L9:
        if ((r10 & 8) == 0) goto L12;
        r5 = false;
    L12:
        if ((r10 & 16) == 0) goto L15;
        r6 = false;
    L15:
        if ((r10 & 32) == 0) goto L18;
        r7 = false;
    L18:
        if ((r10 & 64) == 0) goto L21;
        r8 = false;
    L21:
        if ((r10 & 128) == 0) goto L24;
        r9 = null;
    L24:
        return canonicalizeWithCharset(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static final char[] getHEX_DIGITS() {
        return HEX_DIGITS;
    }

    public static final boolean isPercentEncoded(String r2, int r3, int r4) {
        int r0 = r3 + 2;
        if (r0 < r4) goto L5;
        return false;
    L5:
        if (r2.charAt(r3) == '%') goto L7;
        return false;
    L7:
        if (_UtilCommonKt.parseHexDigit(r2.charAt(r3 + 1)) != (-1)) goto L9;
        return false;
    L9:
        if (_UtilCommonKt.parseHexDigit(r2.charAt(r0)) == (-1)) goto L15;
        return true;
    L15:
        return false;
    }

    public static final String percentDecode(String r3, int r4, int r5, boolean r6) {
        int r0 = r4;
    L3:
        if (r0 >= r5) goto L14;
        char r1 = r3.charAt(r0);
        if (r1 == '%') goto L11;
        if (r1 != '+') goto L10;
        if (r6 == true) goto L11;
    L10:
        r0 = r0 + 1;
    L11:
        Buffer r12 = new Buffer();
        r12.writeUtf8(r3, r4, r0);
        writePercentDecoded(r12, r3, r0, r5, r6);
        return r12.readUtf8();
    L14:
        return r3.substring(r4, r5);
    }

    public static /* synthetic */ String percentDecode$default(String r1, int r2, int r3, boolean r4, int r5, Object r6) {
        if ((r5 & 1) == 0) goto L6;
        r2 = 0;
    L6:
        if ((r5 & 2) == 0) goto L9;
        r3 = r1.length();
    L9:
        if ((r5 & 4) == 0) goto L12;
        r4 = false;
    L12:
        return percentDecode(r1, r2, r3, r4);
    }

    public static final void writeCanonicalized(Buffer r8, String r9, int r10, int r11, String r12, boolean r13, boolean r14, boolean r15, boolean r16, Charset r17) {
        Buffer r2 = null;
    L3:
        if (r10 >= r11) goto L56;
        int r3 = r9.codePointAt(r10);
        if (r13 == true) goto L7;
    L15:
        String r4 = "+";
        if (r3 != 32) goto L21;
        if (r12 != FORM_ENCODE_SET) goto L21;
        r8.writeUtf8("+");
    L55:
        r10 = r10 + Character.charCount(r3);
    L21:
        if (r3 != 43) goto L28;
        if (r15 == false) goto L28;
        if (r13 == true) goto L26;
        r4 = "%2B";
    L26:
        r8.writeUtf8(r4);
    L28:
        if (r3 >= 32) goto L30;
    L43:
        if (r2 != null) goto L45;
        r2 = new Buffer();
    L45:
        if (r17 != null) goto L47;
    L50:
        r2.writeUtf8CodePoint(r3);
    L52:
        if (r2.exhausted() == true) goto L54;
        byte r5 = r2.readByte();
        int r6 = r5 & UByte.MAX_VALUE;
        r8.writeByte(37);
        char[] r7 = HEX_DIGITS;
        r8.writeByte(r7[(r6 >> 4) & 15]);
        r8.writeByte(r7[r5 & 15]);
        goto L52
    L54:
        Unit r42 = Unit.INSTANCE;
        goto L55
    L47:
        if (Intrinsics.areEqual(r17, Charsets.UTF_8) == true) goto L50;
        r2.writeString(r9, r10, Character.charCount(r3) + r10, r17);
        goto L52
    L30:
        if (r3 == 127) goto L43;
        if (r3 < 128) goto L35;
        if (r16 == false) goto L43;
    L35:
        if (StringsKt.contains$default(r12, (char) r3, false, 2, null) == true) goto L43;
        if (r3 != 37) goto L42;
        if (r13 == false) goto L43;
        if (r14 == false) goto L42;
        if (isPercentEncoded(r9, r10, r11) == false) goto L43;
    L42:
        r8.writeUtf8CodePoint(r3);
        goto L55
    L7:
        if (r3 != 9) goto L9;
    L14:
        Unit r43 = Unit.INSTANCE;
        goto L55
    L9:
        if (r3 == 10) goto L14;
        if (r3 == 12) goto L14;
        if (r3 != 13) goto L15;
    }

    public static final void writePercentDecoded(Buffer r5, String r6, int r7, int r8, boolean r9) {
    L2:
        if (r7 >= r8) goto L16;
        int r0 = r6.codePointAt(r7);
        if (r0 != 37) goto L12;
        int r1 = r7 + 2;
        if (r1 >= r8) goto L12;
        int r2 = _UtilCommonKt.parseHexDigit(r6.charAt(r7 + 1));
        int r3 = _UtilCommonKt.parseHexDigit(r6.charAt(r1));
        if (r2 == (-1)) goto L15;
        if (r3 == (-1)) goto L15;
        r5.writeByte((r2 << 4) + r3);
        r7 = Character.charCount(r0) + r1;
    L15:
        r5.writeUtf8CodePoint(r0);
        r7 = r7 + Character.charCount(r0);
    L12:
        if (r0 != 43) goto L15;
        if (r9 == false) goto L15;
        r5.writeByte(32);
        r7 = r7 + 1;
        goto L2
    }
}
