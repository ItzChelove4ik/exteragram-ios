package kotlin;

import androidx.collection.MutableScatterMap$$ExternalSyntheticBackport0;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a!\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0081\u0080\u0004¢\u0006\u0004\b\u0004\u0010\u0005\u001a!\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0081\u0080\u0004¢\u0006\u0004\b\u0007\u0010\u0005\u001a!\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\tH\u0081\u0080\u0004¢\u0006\u0004\b\n\u0010\u000b\u001a!\u0010\f\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\tH\u0081\u0080\u0004¢\u0006\u0004\b\r\u0010\u000b\u001a\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000fH\u0081\u0080\u0004\u001a\u001a\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0011H\u0081\u0080\u0004\u001a\u0017\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u000fH\u0081\u0088\u0004¢\u0006\u0002\u0010\u0014\u001a\u0012\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000fH\u0081\u0088\u0004\u001a\u0012\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u000fH\u0081\u0088\u0004\u001a\u0017\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0017H\u0081\u0088\u0004¢\u0006\u0002\u0010\u0019\u001a\u0012\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u000fH\u0081\u0080\u0004\u001a\u0017\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u001bH\u0081\u0080\u0004¢\u0006\u0002\u0010\u001d\u001a\u0012\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0011H\u0081\u0088\u0004\u001a\u0017\u0010\u001f\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0017H\u0081\u0088\u0004¢\u0006\u0002\u0010 \u001a\u0012\u0010!\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u0011H\u0081\u0080\u0004\u001a\u0017\u0010\"\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u001bH\u0081\u0080\u0004¢\u0006\u0002\u0010#\u001a\u0012\u0010$\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020\u000fH\u0081\u0088\u0004\u001a\u001a\u0010$\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\u000fH\u0081\u0088\u0004\u001a\u0012\u0010'\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020\u0011H\u0081\u0088\u0004\u001a\u001a\u0010'\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u000fH\u0080\u0080\u0004¨\u0006("}, d2 = {"uintRemainder", "Lkotlin/UInt;", "v1", "v2", "uintRemainder-J1ME1BU", "(II)I", "uintDivide", "uintDivide-J1ME1BU", "ulongDivide", "Lkotlin/ULong;", "ulongDivide-eb3DHEI", "(JJ)J", "ulongRemainder", "ulongRemainder-eb3DHEI", "uintCompare", _UrlKt.FRAGMENT_ENCODE_SET, "ulongCompare", _UrlKt.FRAGMENT_ENCODE_SET, "uintToULong", "value", "(I)J", "uintToLong", "uintToFloat", _UrlKt.FRAGMENT_ENCODE_SET, "floatToUInt", "(F)I", "uintToDouble", _UrlKt.FRAGMENT_ENCODE_SET, "doubleToUInt", "(D)I", "ulongToFloat", "floatToULong", "(F)J", "ulongToDouble", "doubleToULong", "(D)J", "uintToString", _UrlKt.FRAGMENT_ENCODE_SET, "base", "ulongToString", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@JvmName(name = "UnsignedKt")
/* loaded from: classes5.dex */
public final class UnsignedKt {
    @PublishedApi
    public static final int doubleToUInt(double r4) {
        if (Double.isNaN(r4) == false) goto L6;
        return 0;
    L6:
        if (r4 > 0.0d) goto L9;
        return 0;
    L9:
        if (r4 < 4.294967295E9d) goto L13;
        return -1;
    L13:
        if (r4 > 2.147483647E9d) goto L17;
        return UInt.m2113constructorimpl((int) r4);
    L17:
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl((int) (r4 - 2.147483647E9d)) + Integer.MAX_VALUE);
    }

    @PublishedApi
    public static final long doubleToULong(double r5) {
        if (Double.isNaN(r5) == false) goto L6;
        return 0;
    L6:
        if (r5 > 0.0d) goto L9;
        return 0;
    L9:
        if (r5 < 1.8446744073709552E19d) goto L13;
        return -1;
    L13:
        if (r5 >= 9.223372036854776E18d) goto L17;
        return ULong.m2192constructorimpl((long) r5);
    L17:
        return ULong.m2192constructorimpl(ULong.m2192constructorimpl((long) (r5 - 9.223372036854776E18d)) - Long.MIN_VALUE);
    }

    @PublishedApi
    @InlineOnly
    private static final int floatToUInt(float r2) {
        return doubleToUInt(r2);
    }

    @PublishedApi
    @InlineOnly
    private static final long floatToULong(float r2) {
        return doubleToULong(r2);
    }

    @PublishedApi
    public static final int uintCompare(int r1, int r2) {
        return Intrinsics.compare(r1 ^ Integer.MIN_VALUE, r2 ^ Integer.MIN_VALUE);
    }

    @PublishedApi
    /* renamed from: uintDivide-J1ME1BU, reason: not valid java name */
    public static final int m2369uintDivideJ1ME1BU(int r4, int r5) {
        return UInt.m2113constructorimpl((int) ((r4 & 4294967295L) / (r5 & 4294967295L)));
    }

    @PublishedApi
    /* renamed from: uintRemainder-J1ME1BU, reason: not valid java name */
    public static final int m2370uintRemainderJ1ME1BU(int r4, int r5) {
        return UInt.m2113constructorimpl((int) ((r4 & 4294967295L) % (r5 & 4294967295L)));
    }

    @PublishedApi
    public static final double uintToDouble(int r6) {
        return (Integer.MAX_VALUE & r6) + (((r6 >>> 31) << 30) * 2.0d);
    }

    @PublishedApi
    @InlineOnly
    private static final float uintToFloat(int r2) {
        return (float) uintToDouble(r2);
    }

    @PublishedApi
    @InlineOnly
    private static final long uintToLong(int r4) {
        return r4 & 4294967295L;
    }

    @InlineOnly
    private static final String uintToString(int r4) {
        return String.valueOf(r4 & 4294967295L);
    }

    @InlineOnly
    private static final String uintToString(int r4, int r5) {
        return ulongToString(r4 & 4294967295L, r5);
    }

    @PublishedApi
    @InlineOnly
    private static final long uintToULong(int r4) {
        return ULong.m2192constructorimpl(r4 & 4294967295L);
    }

    @PublishedApi
    public static final int ulongCompare(long r2, long r4) {
        return Intrinsics.compare(r2 ^ Long.MIN_VALUE, r4 ^ Long.MIN_VALUE);
    }

    @PublishedApi
    /* renamed from: ulongDivide-eb3DHEI, reason: not valid java name */
    public static final long m2371ulongDivideeb3DHEI(long r5, long r7) {
        if (r7 >= 0) goto L11;
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r5, r7) >= 0) goto L9;
        return ULong.m2192constructorimpl(0);
    L9:
        return ULong.m2192constructorimpl(1);
    L11:
        if (r5 >= 0) goto L13;
        int r0 = 1;
        long r1 = ((r5 >>> 1) / r7) << 1;
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(ULong.m2192constructorimpl(r5 - (r1 * r7)), ULong.m2192constructorimpl(r7)) >= 0) goto L19;
        r0 = 0;
    L19:
        return ULong.m2192constructorimpl(r1 + r0);
    L13:
        return ULong.m2192constructorimpl(r5 / r7);
    }

    @PublishedApi
    /* renamed from: ulongRemainder-eb3DHEI, reason: not valid java name */
    public static final long m2372ulongRemaindereb3DHEI(long r6, long r8) {
        if (r8 >= 0) goto L10;
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r6, r8) >= 0) goto L8;
        return r6;
    L8:
        return ULong.m2192constructorimpl(r6 - r8);
    L10:
        if (r6 >= 0) goto L12;
        long r62 = r6 - ((((r6 >>> 1) / r8) << 1) * r8);
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(ULong.m2192constructorimpl(r62), ULong.m2192constructorimpl(r8)) >= 0) goto L18;
        r8 = 0;
    L18:
        return ULong.m2192constructorimpl(r62 - r8);
    L12:
        return ULong.m2192constructorimpl(r6 % r8);
    }

    @PublishedApi
    public static final double ulongToDouble(long r4) {
        return ((r4 >>> 11) * 2048.0d) + (r4 & 2047);
    }

    @PublishedApi
    @InlineOnly
    private static final float ulongToFloat(long r0) {
        return (float) ulongToDouble(r0);
    }

    @InlineOnly
    private static final String ulongToString(long r1) {
        return ulongToString(r1, 10);
    }

    public static final String ulongToString(long r7, int r9) {
        if (r7 >= 0) goto L5;
        long r3 = r9;
        long r0 = ((r7 >>> 1) / r3) << 1;
        long r72 = r7 - (r0 * r3);
        if (r72 < r3) goto L10;
        r72 = r72 - r3;
        r0 = r0 + 1;
    L10:
        return Long.toString(r0, CharsKt.checkRadix(r9)) + Long.toString(r72, CharsKt.checkRadix(r9));
    L5:
        return Long.toString(r7, CharsKt.checkRadix(r9));
    }
}
