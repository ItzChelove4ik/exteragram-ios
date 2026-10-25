package okhttp3.internal;

import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.idn.IdnaMappingTableInstanceKt;
import okhttp3.internal.idn.Punycode;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\u001a\f\u0010\u0005\u001a\u00020\u0003*\u00020\u0004H\u0000\u001a\f\u0010\u0006\u001a\u00020\u0003*\u00020\u0004H\u0000\u001a\"\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000\u001a0\u0010\r\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0000\u001a\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\bH\u0000\u001a\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0000\u001a\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\bH\u0002\u001a\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\bH\u0000\u001a\u000e\u0010\u0014\u001a\u0004\u0018\u00010\u0004*\u00020\u0004H\u0000\u001a\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"VERIFY_AS_IP_ADDRESS", "Lkotlin/text/Regex;", "canParseAsIpAddress", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "containsInvalidLabelLengths", "containsInvalidHostnameAsciiCodes", "decodeIpv6", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.TYPE_INPUT, "pos", _UrlKt.FRAGMENT_ENCODE_SET, "limit", "decodeIpv4Suffix", "address", "addressOffset", "inet6AddressToAscii", "canonicalizeInetAddress", "isMappedIpv4Address", "inet4AddressToAscii", "toCanonicalHost", "idnToAscii", "host", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class _HostnamesCommonKt {
    private static final Regex VERIFY_AS_IP_ADDRESS = null;

    static {
        VERIFY_AS_IP_ADDRESS = new Regex("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
    }

    public static final boolean canParseAsIpAddress(String r1) {
        return VERIFY_AS_IP_ADDRESS.matches(r1);
    }

    public static final byte[] canonicalizeInetAddress(byte[] r2) {
        if (isMappedIpv4Address(r2) == true) goto L5;
        return r2;
    L5:
        return ArraysKt.sliceArray(r2, RangesKt.until(12, 16));
    }

    public static final boolean containsInvalidHostnameAsciiCodes(String r10) {
        int r0 = r10.length();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L14;
        char r4 = r10.charAt(r2);
        if (Intrinsics.compare(r4, 31) <= 0) goto L13;
        if (Intrinsics.compare(r4, 127) >= 0) goto L13;
        if (StringsKt.indexOf$default(" #%/:?@[\\]", r4, 0, false, 6, null) != (-1)) goto L11;
        r2 = r2 + 1;
        goto L3
    L11:
        return true;
    L13:
        return true;
    L14:
        return false;
    }

    public static final boolean containsInvalidLabelLengths(String r8) {
        int r0 = r8.length();
        if (1 <= r0) goto L5;
    L19:
        return true;
    L5:
        if (r0 >= 254) goto L19;
        int r4 = 0;
    L7:
        String r2 = r8;
        int r82 = StringsKt.indexOf$default(r2, '.', r4, false, 4, null);
        if (r82 != (-1)) goto L10;
        int r5 = r2.length() - r4;
    L11:
        if (1 > r5) goto L19;
        if (r5 >= 64) goto L19;
        if (r82 == (-1)) goto L18;
        if (r82 == (r2.length() - 1)) goto L18;
        r4 = r82 + 1;
        r8 = r2;
    L18:
        return false;
    L10:
        r5 = r82 - r4;
        goto L11
    }

    public static final boolean decodeIpv4Suffix(String r7, int r8, int r9, byte[] r10, int r11) {
        int r0 = r11;
    L4:
        if (r8 >= r9) goto L32;
        if (r0 == r10.length) goto L7;
        if (r0 != r11) goto L10;
    L13:
        int r2 = r8;
        int r3 = 0;
    L14:
        if (r2 >= r9) goto L28;
        char r4 = r7.charAt(r2);
        if (Intrinsics.compare(r4, 48) < 0) goto L28;
        if (Intrinsics.compare(r4, 57) > 0) goto L28;
        if (r3 != 0) goto L23;
        if (r8 == r2) goto L23;
        return false;
    L23:
        r3 = ((r3 * 10) + r4) - 48;
        if (r3 > 255) goto L25;
        r2 = r2 + 1;
        goto L14
    L25:
        return false;
    L28:
        if ((r2 - r8) == 0) goto L29;
        r10[r0] = (byte) r3;
        r0 = r0 + 1;
        r8 = r2;
        goto L4
    L29:
        return false;
    L10:
        if (r7.charAt(r8) != '.') goto L11;
        r8 = r8 + 1;
        goto L13
    L11:
        return false;
    L7:
        return false;
    L32:
        if (r0 != (r11 + 4)) goto L35;
        return true;
    L35:
        return false;
    }

    public static final byte[] decodeIpv6(String r16, int r17, int r18) {
        byte[] r8 = new byte[16];
        int r2 = r17;
        int r11 = 0;
        int r12 = -1;
        int r13 = -1;
    L4:
        if (r2 >= r18) goto L41;
        if (r11 == 16) goto L6;
        int r15 = r2 + 2;
        if (r15 <= r18) goto L10;
    L17:
        if (r11 != 0) goto L19;
    L21:
        String r0 = r16;
        r13 = r2;
    L29:
        int r1 = 0;
        r2 = r13;
    L30:
        if (r2 >= r18) goto L34;
        int r3 = _UtilCommonKt.parseHexDigit(r0.charAt(r2));
        if (r3 == (-1)) goto L34;
        r1 = (r1 << 4) + r3;
        r2 = r2 + 1;
    L34:
        int r32 = r2 - r13;
        if (r32 == 0) goto L40;
        if (r32 > 4) goto L40;
        int r33 = r11 + 1;
        r8[r11] = (byte) ((r1 >>> 8) & 255);
        r11 = r11 + 2;
        r8[r33] = (byte) (r1 & 255);
    L40:
        return null;
    L19:
        if (StringsKt.startsWith$default(r16, ":", r2, false, 4, null) == false) goto L23;
        r2 = r2 + 1;
        goto L21
    L23:
        if (StringsKt.startsWith$default(r16, ".", r2, false, 4, null) == true) goto L25;
        return null;
    L25:
        if (decodeIpv4Suffix(r16, r13, r18, r8, r11 - 2) == true) goto L27;
        return null;
    L27:
        r11 = r11 + 2;
        goto L41
    L10:
        if (StringsKt.startsWith$default(r16, "::", r2, false, 4, null) == false) goto L17;
        if (r12 != (-1)) goto L12;
        r11 = r11 + 2;
        if (r15 == r18) goto L15;
        r0 = r16;
        r12 = r11;
        r13 = r15;
        goto L29
    L15:
        r12 = r11;
        goto L41
    L12:
        return null;
    L6:
        return null;
    L41:
        if (r11 == 16) goto L45;
        if (r12 != (-1)) goto L44;
        return null;
    L44:
        ArraysKt.copyInto(r8, r8, 16 - (r11 - r12), r12, r11);
        ArraysKt.fill(r8, (byte) 0, r12, (16 - r11) + r12);
    L45:
        return r8;
    }

    public static final String idnToAscii(String r4) {
        Buffer r42 = new Buffer().writeUtf8(r4);
        Buffer r0 = new Buffer();
    L4:
        if (r42.exhausted() == true) goto L8;
        int r1 = r42.readUtf8CodePoint();
        if (IdnaMappingTableInstanceKt.getIDNA_MAPPING_TABLE().map(r1, r0) == true) goto L4;
        return null;
    L8:
        r42.writeUtf8(_NormalizeJvmKt.normalizeNfc(r0.readUtf8()));
        Punycode r02 = Punycode.INSTANCE;
        String r43 = r02.decode(r42.readUtf8());
        if (r43 != null) goto L12;
        return null;
    L12:
        if (Intrinsics.areEqual(r43, _NormalizeJvmKt.normalizeNfc(r43)) == true) goto L15;
        return null;
    L15:
        return r02.encode(r43);
    }

    public static final String inet4AddressToAscii(byte[] r5) {
        if (r5.length == 4) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        return null;
    L5:
        return new Buffer().writeDecimalLong(_UtilCommonKt.and(r5[0], 255)).writeByte(46).writeDecimalLong(_UtilCommonKt.and(r5[1], 255)).writeByte(46).writeDecimalLong(_UtilCommonKt.and(r5[2], 255)).writeByte(46).writeDecimalLong(_UtilCommonKt.and(r5[3], 255)).readUtf8();
    }

    public static final String inet6AddressToAscii(byte[] r8) {
        int r0 = -1;
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r2 >= r8.length) goto L18;
        int r4 = r2;
    L6:
        if (r4 >= 16) goto L12;
        if (r8[r4] != 0) goto L12;
        if (r8[r4 + 1] != 0) goto L12;
        r4 = r4 + 2;
    L12:
        int r5 = r4 - r2;
        if (r5 <= r3) goto L17;
        if (r5 < 4) goto L17;
        r0 = r2;
        r3 = r5;
    L17:
        r2 = r4 + 2;
        goto L4
    L18:
        Buffer r22 = new Buffer();
    L20:
        if (r1 >= r8.length) goto L30;
        if (r1 == r0) goto L23;
        if (r1 <= 0) goto L28;
        r22.writeByte(58);
    L28:
        r22.writeHexadecimalUnsignedLong((_UtilCommonKt.and(r8[r1], 255) << 8) | _UtilCommonKt.and(r8[r1 + 1], 255));
        r1 = r1 + 2;
        goto L20
    L23:
        r22.writeByte(58);
        r1 = r1 + r3;
        if (r1 != 16) goto L20;
        r22.writeByte(58);
        goto L20
    L30:
        return r22.readUtf8();
    }

    private static final boolean isMappedIpv4Address(byte[] r3) {
        if (r3.length == 16) goto L5;
        return false;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= 10) goto L13;
        if (r3[r0] != 0) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        return false;
    L13:
        if (r3[10] == (-1)) goto L16;
        return false;
    L16:
        if (r3[11] == (-1)) goto L18;
        return false;
    L18:
        return true;
    }

    public static final String toCanonicalHost(String r4) {
        if (StringsKt.contains$default(r4, ":", false, 2, null) == true) goto L5;
        String r42 = idnToAscii(r4);
        if (r42 != null) goto L26;
        return null;
    L26:
        if (r42.length() != 0) goto L29;
        return null;
    L29:
        if (containsInvalidHostnameAsciiCodes(r42) == false) goto L32;
        return null;
    L32:
        if (containsInvalidLabelLengths(r42) == false) goto L34;
        return null;
    L34:
        return r42;
    L5:
        if (StringsKt.startsWith$default(r4, "[", false, 2, null) == true) goto L7;
    L9:
        byte[] r0 = decodeIpv6(r4, 0, r4.length());
    L10:
        if (r0 != null) goto L12;
        return null;
    L12:
        byte[] r02 = canonicalizeInetAddress(r0);
        if (r02.length != 16) goto L17;
        return inet6AddressToAscii(r02);
    L17:
        if (r02.length != 4) goto L21;
        return inet4AddressToAscii(r02);
    L21:
        throw new AssertionError("Invalid IPv6 address: '" + r4 + '\'');
    L7:
        if (StringsKt.endsWith$default(r4, "]", false, 2, null) == false) goto L9;
        r0 = decodeIpv6(r4, 1, r4.length() - 1);
        goto L10
    }
}
