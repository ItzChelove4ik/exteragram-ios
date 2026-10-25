package kotlin.text;

import androidx.collection.MutableScatterMap$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.UByte$$ExternalSyntheticBackport3;
import kotlin.UByte$$ExternalSyntheticBackport4;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0004\b\b\u0010\t\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0004\b\u000b\u0010\f\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0013\u0010\u0010\u001a\u00020\u0002*\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0011\u001a\u001b\u0010\u0010\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0012\u001a\u0013\u0010\u0013\u001a\u00020\u0007*\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0014\u001a\u001b\u0010\u0013\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0015\u001a\u0013\u0010\u0016\u001a\u00020\n*\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0017\u001a\u001b\u0010\u0016\u001a\u00020\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0018\u001a\u0013\u0010\u0019\u001a\u00020\r*\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0002\u0010\u001a\u001a\u001b\u0010\u0019\u001a\u00020\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0002\u0010\u001b\u001a\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0087\u0080\u0004\u001a\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004\u001a\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0007*\u00020\u0001H\u0087\u0080\u0004\u001a\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004\u001a\u0010\u0010\u001e\u001a\u0004\u0018\u00010\n*\u00020\u0001H\u0087\u0080\u0004\u001a\u0018\u0010\u001e\u001a\u0004\u0018\u00010\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004\u001a\u0010\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u0001H\u0087\u0080\u0004\u001a\u0018\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¨\u0006 "}, d2 = {"toString", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/UByte;", "radix", _UrlKt.FRAGMENT_ENCODE_SET, "toString-LxnNnR4", "(BI)Ljava/lang/String;", "Lkotlin/UShort;", "toString-olVBNx4", "(SI)Ljava/lang/String;", "Lkotlin/UInt;", "toString-V7xB4Y4", "(II)Ljava/lang/String;", "Lkotlin/ULong;", "toString-JSWoG40", "(JI)Ljava/lang/String;", "toUByte", "(Ljava/lang/String;)B", "(Ljava/lang/String;I)B", "toUShort", "(Ljava/lang/String;)S", "(Ljava/lang/String;I)S", "toUInt", "(Ljava/lang/String;)I", "(Ljava/lang/String;I)I", "toULong", "(Ljava/lang/String;)J", "(Ljava/lang/String;I)J", "toUByteOrNull", "toUShortOrNull", "toUIntOrNull", "toULongOrNull", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@JvmName(name = "UStringsKt")
/* loaded from: classes5.dex */
public final class UStringsKt {
    @SinceKotlin(version = "1.5")
    /* renamed from: toString-JSWoG40, reason: not valid java name */
    public static final String m3359toStringJSWoG40(long r0, int r2) {
        return UnsignedKt.ulongToString(r0, CharsKt__CharJVMKt.checkRadix(r2));
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: toString-LxnNnR4, reason: not valid java name */
    public static final String m3360toStringLxnNnR4(byte r0, int r1) {
        return Integer.toString(r0 & UByte.MAX_VALUE, CharsKt__CharJVMKt.checkRadix(r1));
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: toString-V7xB4Y4, reason: not valid java name */
    public static final String m3361toStringV7xB4Y4(int r4, int r5) {
        return UnsignedKt.ulongToString(r4 & 4294967295L, CharsKt__CharJVMKt.checkRadix(r5));
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: toString-olVBNx4, reason: not valid java name */
    public static final String m3362toStringolVBNx4(short r1, int r2) {
        return Integer.toString(r1 & UShort.MAX_VALUE, CharsKt__CharJVMKt.checkRadix(r2));
    }

    @SinceKotlin(version = "1.5")
    public static final byte toUByte(String r1) {
        UByte r0 = toUByteOrNull(r1);
        if (r0 != null) goto L5;
        StringsKt__StringNumberConversionsKt.numberFormatError(r1);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L5:
        return r0.m2086unboximpl();
    }

    @SinceKotlin(version = "1.5")
    public static final byte toUByte(String r0, int r1) {
        UByte r12 = toUByteOrNull(r0, r1);
        if (r12 != null) goto L5;
        StringsKt__StringNumberConversionsKt.numberFormatError(r0);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L5:
        return r12.m2086unboximpl();
    }

    @SinceKotlin(version = "1.5")
    public static final UByte toUByteOrNull(String r1) {
        return toUByteOrNull(r1, 10);
    }

    @SinceKotlin(version = "1.5")
    public static final UByte toUByteOrNull(String r1, int r2) {
        UInt r12 = toUIntOrNull(r1, r2);
        if (r12 == null) goto L9;
        int r13 = r12.m2165unboximpl();
        if (UByte$$ExternalSyntheticBackport4.m(r13, UInt.m2113constructorimpl(255)) <= 0) goto L8;
        return null;
    L8:
        return UByte.m2030boximpl(UByte.m2036constructorimpl((byte) r13));
    L9:
        return null;
    }

    @SinceKotlin(version = "1.5")
    public static final int toUInt(String r1) {
        UInt r0 = toUIntOrNull(r1);
        if (r0 != null) goto L5;
        StringsKt__StringNumberConversionsKt.numberFormatError(r1);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L5:
        return r0.m2165unboximpl();
    }

    @SinceKotlin(version = "1.5")
    public static final int toUInt(String r0, int r1) {
        UInt r12 = toUIntOrNull(r0, r1);
        if (r12 != null) goto L5;
        StringsKt__StringNumberConversionsKt.numberFormatError(r0);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L5:
        return r12.m2165unboximpl();
    }

    @SinceKotlin(version = "1.5")
    public static final UInt toUIntOrNull(String r1) {
        return toUIntOrNull(r1, 10);
    }

    @SinceKotlin(version = "1.5")
    public static final UInt toUIntOrNull(String r9, int r10) {
        CharsKt__CharJVMKt.checkRadix(r10);
        int r0 = r9.length();
        if (r0 != 0) goto L5;
        return null;
    L5:
        int r2 = 0;
        char r3 = r9.charAt(0);
        if (Intrinsics.compare(r3, 48) >= 0) goto L12;
        int r4 = 1;
        if (r0 != 1) goto L10;
    L11:
        return null;
    L10:
        if (r3 != '+') goto L11;
    L13:
        int r32 = UInt.m2113constructorimpl(r10);
        int r6 = 119304647;
    L14:
        if (r4 >= r0) goto L29;
        int r7 = CharsKt__CharJVMKt.digitOf(r9.charAt(r4), r10);
        if (r7 < 0) goto L17;
        if (UByte$$ExternalSyntheticBackport4.m(r2, r6) <= 0) goto L24;
        if (r6 != 119304647) goto L23;
        r6 = UByte$$ExternalSyntheticBackport0.m(-1, r32);
        if (UByte$$ExternalSyntheticBackport4.m(r2, r6) <= 0) goto L24;
    L23:
        return null;
    L24:
        int r22 = UInt.m2113constructorimpl(r2 * r32);
        int r72 = UInt.m2113constructorimpl(UInt.m2113constructorimpl(r7) + r22);
        if (UByte$$ExternalSyntheticBackport4.m(r72, r22) < 0) goto L26;
        r4 = r4 + 1;
        r2 = r72;
        goto L14
    L26:
        return null;
    L17:
        return null;
    L29:
        return UInt.m2107boximpl(r2);
    L12:
        r4 = 0;
        goto L13
    }

    @SinceKotlin(version = "1.5")
    public static final long toULong(String r2) {
        ULong r0 = toULongOrNull(r2);
        if (r0 != null) goto L5;
        StringsKt__StringNumberConversionsKt.numberFormatError(r2);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L5:
        return r0.m2244unboximpl();
    }

    @SinceKotlin(version = "1.5")
    public static final long toULong(String r0, int r1) {
        ULong r12 = toULongOrNull(r0, r1);
        if (r12 != null) goto L5;
        StringsKt__StringNumberConversionsKt.numberFormatError(r0);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L5:
        return r12.m2244unboximpl();
    }

    @SinceKotlin(version = "1.5")
    public static final ULong toULongOrNull(String r1) {
        return toULongOrNull(r1, 10);
    }

    @SinceKotlin(version = "1.5")
    public static final ULong toULongOrNull(String r17, int r18) {
        CharsKt__CharJVMKt.checkRadix(r18);
        int r2 = r17.length();
        if (r2 != 0) goto L5;
        return null;
    L5:
        int r4 = 0;
        char r5 = r17.charAt(0);
        if (Intrinsics.compare(r5, 48) >= 0) goto L12;
        r4 = 1;
        if (r2 != 1) goto L10;
    L11:
        return null;
    L10:
        if (r5 != '+') goto L11;
    L12:
        long r52 = ULong.m2192constructorimpl(r18);
        long r9 = 0;
        long r11 = 512409557603043100L;
    L13:
        if (r4 >= r2) goto L29;
        if (CharsKt__CharJVMKt.digitOf(r17.charAt(r4), r18) < 0) goto L16;
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r9, r11) > 0) goto L20;
    L24:
        long r92 = ULong.m2192constructorimpl(r9 * r52);
        long r13 = ULong.m2192constructorimpl(ULong.m2192constructorimpl(UInt.m2113constructorimpl(r13) & 4294967295L) + r92);
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r13, r92) < 0) goto L26;
        r4 = r4 + 1;
        r9 = r13;
        goto L13
    L26:
        return null;
    L20:
        if (r11 != 512409557603043100L) goto L23;
        r11 = UByte$$ExternalSyntheticBackport3.m(-1, r52);
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r9, r11) <= 0) goto L24;
    L23:
        return null;
    L16:
        return null;
    L29:
        return ULong.m2186boximpl(r9);
    }

    @SinceKotlin(version = "1.5")
    public static final short toUShort(String r1) {
        UShort r0 = toUShortOrNull(r1);
        if (r0 != null) goto L5;
        StringsKt__StringNumberConversionsKt.numberFormatError(r1);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L5:
        return r0.m2349unboximpl();
    }

    @SinceKotlin(version = "1.5")
    public static final short toUShort(String r0, int r1) {
        UShort r12 = toUShortOrNull(r0, r1);
        if (r12 != null) goto L5;
        StringsKt__StringNumberConversionsKt.numberFormatError(r0);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L5:
        return r12.m2349unboximpl();
    }

    @SinceKotlin(version = "1.5")
    public static final UShort toUShortOrNull(String r1) {
        return toUShortOrNull(r1, 10);
    }

    @SinceKotlin(version = "1.5")
    public static final UShort toUShortOrNull(String r1, int r2) {
        UInt r12 = toUIntOrNull(r1, r2);
        if (r12 == null) goto L9;
        int r13 = r12.m2165unboximpl();
        if (UByte$$ExternalSyntheticBackport4.m(r13, UInt.m2113constructorimpl(65535)) <= 0) goto L8;
        return null;
    L8:
        return UShort.m2293boximpl(UShort.m2299constructorimpl((short) r13));
    L9:
        return null;
    }
}
