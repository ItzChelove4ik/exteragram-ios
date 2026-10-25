package kotlin.text;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0006\u001a\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\t\u001a\u001d\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0087\u0080\u0004¢\u0006\u0002\u0010\n\u001a\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0005*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\f\u001a\u001d\u0010\u000b\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0087\u0080\u0004¢\u0006\u0002\u0010\r\u001a\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0010\u001a\u001d\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0011\u001a\u0012\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0002H\u0080\u0080\u0004¨\u0006\u0015"}, d2 = {"toByteOrNull", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;)Ljava/lang/Byte;", "radix", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;I)Ljava/lang/Byte;", "toShortOrNull", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;)Ljava/lang/Short;", "(Ljava/lang/String;I)Ljava/lang/Short;", "toIntOrNull", "(Ljava/lang/String;)Ljava/lang/Integer;", "(Ljava/lang/String;I)Ljava/lang/Integer;", "toLongOrNull", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;)Ljava/lang/Long;", "(Ljava/lang/String;I)Ljava/lang/Long;", "numberFormatError", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.TYPE_INPUT, "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/text/StringsKt")
/* loaded from: classes.dex */
class StringsKt__StringNumberConversionsKt extends StringsKt__StringNumberConversionsJVMKt {
    public StringsKt__StringNumberConversionsKt() {
    }

    public static final Void numberFormatError(String r3) {
        throw new NumberFormatException("Invalid number format: '" + r3 + '\'');
    }

    @SinceKotlin(version = "1.1")
    public static final Byte toByteOrNull(String r1) {
        return toByteOrNull(r1, 10);
    }

    @SinceKotlin(version = "1.1")
    public static final Byte toByteOrNull(String r1, int r2) {
        Integer r12 = toIntOrNull(r1, r2);
        if (r12 == null) goto L11;
        int r13 = r12.intValue();
        if (r13 < (-128)) goto L11;
        if (r13 > 127) goto L11;
        return Byte.valueOf((byte) r13);
    L11:
        return null;
    }

    @SinceKotlin(version = "1.1")
    public static Integer toIntOrNull(String r1) {
        return toIntOrNull(r1, 10);
    }

    @SinceKotlin(version = "1.1")
    public static final Integer toIntOrNull(String r10, int r11) {
        CharsKt__CharJVMKt.checkRadix(r11);
        int r0 = r10.length();
        if (r0 != 0) goto L5;
        return null;
    L5:
        int r2 = 0;
        char r3 = r10.charAt(0);
        int r5 = -2147483647;
        if (Intrinsics.compare(r3, 48) >= 0) goto L17;
        int r4 = 1;
        if (r0 != 1) goto L11;
        return null;
    L11:
        if (r3 != '+') goto L13;
        boolean r32 = false;
    L18:
        int r7 = -59652323;
    L19:
        if (r4 >= r0) goto L32;
        int r8 = CharsKt__CharJVMKt.digitOf(r10.charAt(r4), r11);
        if (r8 < 0) goto L22;
        if (r2 >= r7) goto L28;
        if (r7 != (-59652323)) goto L27;
        r7 = r5 / r11;
        if (r2 >= r7) goto L28;
    L27:
        return null;
    L28:
        int r22 = r2 * r11;
        if (r22 < (r5 + r8)) goto L30;
        r2 = r22 - r8;
        r4 = r4 + 1;
        goto L19
    L30:
        return null;
    L22:
        return null;
    L32:
        if (r32 == false) goto L36;
        return Integer.valueOf(r2);
    L36:
        return Integer.valueOf(-r2);
    L13:
        if (r3 == '-') goto L15;
        return null;
    L15:
        r5 = Integer.MIN_VALUE;
        r32 = true;
        goto L18
    L17:
        r32 = false;
        r4 = 0;
        goto L18
    }

    @SinceKotlin(version = "1.1")
    public static Long toLongOrNull(String r1) {
        return toLongOrNull(r1, 10);
    }

    @SinceKotlin(version = "1.1")
    public static final Long toLongOrNull(String r20, int r21) {
        CharsKt__CharJVMKt.checkRadix(r21);
        int r2 = r20.length();
        Long r3 = null;
        if (r2 != 0) goto L5;
        return null;
    L5:
        int r4 = 0;
        char r5 = r20.charAt(0);
        long r7 = -9223372036854775807L;
        if (Intrinsics.compare(r5, 48) >= 0) goto L17;
        boolean r6 = true;
        if (r2 != 1) goto L11;
        return null;
    L11:
        if (r5 != '+') goto L13;
        r6 = false;
        r4 = 1;
    L18:
        long r11 = 0;
        long r13 = -256204778801521550L;
    L19:
        if (r4 >= r2) goto L36;
        int r52 = CharsKt__CharJVMKt.digitOf(r20.charAt(r4), r21);
        if (r52 < 0) goto L22;
        if (r11 < r13) goto L26;
    L30:
        Long r15 = r3;
        int r16 = r4;
        long r112 = r11 * r21;
        long r32 = r52;
        if (r112 < (r7 + r32)) goto L34;
        r11 = r112 - r32;
        r4 = r16 + 1;
        r3 = r15;
        goto L19
    L34:
        return r15;
    L26:
        if (r13 != (-256204778801521550L)) goto L31;
        r13 = r7 / r21;
        if (r11 >= r13) goto L30;
        return r3;
    L31:
        return r3;
    L22:
        return r3;
    L36:
        if (r6 == false) goto L40;
        return Long.valueOf(r11);
    L40:
        return Long.valueOf(-r11);
    L13:
        if (r5 == '-') goto L15;
        return null;
    L15:
        r7 = Long.MIN_VALUE;
        r4 = 1;
        goto L18
    L17:
        r6 = false;
        goto L18
    }

    @SinceKotlin(version = "1.1")
    public static final Short toShortOrNull(String r1) {
        return toShortOrNull(r1, 10);
    }

    @SinceKotlin(version = "1.1")
    public static final Short toShortOrNull(String r1, int r2) {
        Integer r12 = toIntOrNull(r1, r2);
        if (r12 == null) goto L11;
        int r13 = r12.intValue();
        if (r13 < (-32768)) goto L11;
        if (r13 > 32767) goto L11;
        return Short.valueOf((short) r13);
    L11:
        return null;
    }
}
