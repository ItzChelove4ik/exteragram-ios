package kotlin.comparisons;

import androidx.collection.MutableScatterMap$$ExternalSyntheticBackport0;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByte$$ExternalSyntheticBackport4;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u001a!\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0004\b\u0004\u0010\u0005\u001a!\u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\u0080\u0004¢\u0006\u0004\b\u0007\u0010\b\u001a!\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\tH\u0087\u0080\u0004¢\u0006\u0004\b\n\u0010\u000b\u001a!\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\fH\u0087\u0080\u0004¢\u0006\u0004\b\r\u0010\u000e\u001a)\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0087\u0088\u0004¢\u0006\u0004\b\u0010\u0010\u0011\u001a)\u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0087\u0088\u0004¢\u0006\u0004\b\u0012\u0010\u0013\u001a)\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0087\u0088\u0004¢\u0006\u0004\b\u0014\u0010\u0015\u001a)\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fH\u0087\u0088\u0004¢\u0006\u0004\b\u0016\u0010\u0017\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\u0018\u001a\u00020\u0019\"\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0004\b\u001a\u0010\u001b\u001a%\u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\n\u0010\u0018\u001a\u00020\u001c\"\u00020\u0006H\u0087\u0080\u0004¢\u0006\u0004\b\u001d\u0010\u001e\u001a%\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\n\u0010\u0018\u001a\u00020\u001f\"\u00020\tH\u0087\u0080\u0004¢\u0006\u0004\b \u0010!\u001a%\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\n\u0010\u0018\u001a\u00020\"\"\u00020\fH\u0087\u0080\u0004¢\u0006\u0004\b#\u0010$\u001a!\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0004\b&\u0010\u0005\u001a!\u0010%\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\u0080\u0004¢\u0006\u0004\b'\u0010\b\u001a!\u0010%\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\tH\u0087\u0080\u0004¢\u0006\u0004\b(\u0010\u000b\u001a!\u0010%\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\fH\u0087\u0080\u0004¢\u0006\u0004\b)\u0010\u000e\u001a)\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0087\u0088\u0004¢\u0006\u0004\b*\u0010\u0011\u001a)\u0010%\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0087\u0088\u0004¢\u0006\u0004\b+\u0010\u0013\u001a)\u0010%\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0087\u0088\u0004¢\u0006\u0004\b,\u0010\u0015\u001a)\u0010%\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fH\u0087\u0088\u0004¢\u0006\u0004\b-\u0010\u0017\u001a%\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\u0018\u001a\u00020\u0019\"\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0004\b.\u0010\u001b\u001a%\u0010%\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\n\u0010\u0018\u001a\u00020\u001c\"\u00020\u0006H\u0087\u0080\u0004¢\u0006\u0004\b/\u0010\u001e\u001a%\u0010%\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\n\u0010\u0018\u001a\u00020\u001f\"\u00020\tH\u0087\u0080\u0004¢\u0006\u0004\b0\u0010!\u001a%\u0010%\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\n\u0010\u0018\u001a\u00020\"\"\u00020\fH\u0087\u0080\u0004¢\u0006\u0004\b1\u0010$¨\u00062"}, d2 = {"maxOf", "Lkotlin/UInt;", "a", "b", "maxOf-J1ME1BU", "(II)I", "Lkotlin/ULong;", "maxOf-eb3DHEI", "(JJ)J", "Lkotlin/UByte;", "maxOf-Kr8caGY", "(BB)B", "Lkotlin/UShort;", "maxOf-5PvTz6A", "(SS)S", "c", "maxOf-WZ9TVnA", "(III)I", "maxOf-sambcqE", "(JJJ)J", "maxOf-b33U2AM", "(BBB)B", "maxOf-VKSA0NQ", "(SSS)S", "other", "Lkotlin/UIntArray;", "maxOf-Md2H83M", "(I[I)I", "Lkotlin/ULongArray;", "maxOf-R03FKyM", "(J[J)J", "Lkotlin/UByteArray;", "maxOf-Wr6uiD8", "(B[B)B", "Lkotlin/UShortArray;", "maxOf-t1qELG4", "(S[S)S", "minOf", "minOf-J1ME1BU", "minOf-eb3DHEI", "minOf-Kr8caGY", "minOf-5PvTz6A", "minOf-WZ9TVnA", "minOf-sambcqE", "minOf-b33U2AM", "minOf-VKSA0NQ", "minOf-Md2H83M", "minOf-R03FKyM", "minOf-Wr6uiD8", "minOf-t1qELG4", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/comparisons/UComparisonsKt")
/* loaded from: classes5.dex */
class UComparisonsKt___UComparisonsKt {
    public UComparisonsKt___UComparisonsKt() {
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: maxOf-5PvTz6A, reason: not valid java name */
    public static final short m3211maxOf5PvTz6A(short r2, short r3) {
        if (Intrinsics.compare(r2 & UShort.MAX_VALUE, 65535 & r3) < 0) goto L5;
        return r2;
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: maxOf-J1ME1BU, reason: not valid java name */
    public static int m3212maxOfJ1ME1BU(int r1, int r2) {
        if (UByte$$ExternalSyntheticBackport4.m(r1, r2) < 0) goto L5;
        return r1;
    L5:
        return r2;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: maxOf-Kr8caGY, reason: not valid java name */
    public static final byte m3213maxOfKr8caGY(byte r2, byte r3) {
        if (Intrinsics.compare(r2 & UByte.MAX_VALUE, r3 & UByte.MAX_VALUE) < 0) goto L5;
        return r2;
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: maxOf-Md2H83M, reason: not valid java name */
    public static final int m3214maxOfMd2H83M(int r3, int... r4) {
        int r0 = UIntArray.m2174getSizeimpl(r4);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3 = m3212maxOfJ1ME1BU(r3, UIntArray.m2173getpVg5ArA(r4, r1));
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: maxOf-R03FKyM, reason: not valid java name */
    public static final long m3215maxOfR03FKyM(long r4, long... r6) {
        int r0 = ULongArray.m2253getSizeimpl(r6);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r4 = m3220maxOfeb3DHEI(r4, ULongArray.m2252getsVKNKU(r6, r1));
        r1 = r1 + 1;
        goto L3
    L5:
        return r4;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: maxOf-VKSA0NQ, reason: not valid java name */
    private static final short m3216maxOfVKSA0NQ(short r0, short r1, short r2) {
        return m3211maxOf5PvTz6A(r0, m3211maxOf5PvTz6A(r1, r2));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: maxOf-WZ9TVnA, reason: not valid java name */
    private static final int m3217maxOfWZ9TVnA(int r0, int r1, int r2) {
        return m3212maxOfJ1ME1BU(r0, m3212maxOfJ1ME1BU(r1, r2));
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: maxOf-Wr6uiD8, reason: not valid java name */
    public static final byte m3218maxOfWr6uiD8(byte r3, byte... r4) {
        int r0 = UByteArray.m2095getSizeimpl(r4);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3 = m3213maxOfKr8caGY(r3, UByteArray.m2094getw2LRezQ(r4, r1));
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: maxOf-b33U2AM, reason: not valid java name */
    private static final byte m3219maxOfb33U2AM(byte r0, byte r1, byte r2) {
        return m3213maxOfKr8caGY(r0, m3213maxOfKr8caGY(r1, r2));
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: maxOf-eb3DHEI, reason: not valid java name */
    public static long m3220maxOfeb3DHEI(long r1, long r3) {
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r1, r3) < 0) goto L5;
        return r1;
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: maxOf-sambcqE, reason: not valid java name */
    private static final long m3221maxOfsambcqE(long r0, long r2, long r4) {
        return m3220maxOfeb3DHEI(r0, m3220maxOfeb3DHEI(r2, r4));
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: maxOf-t1qELG4, reason: not valid java name */
    public static final short m3222maxOft1qELG4(short r3, short... r4) {
        int r0 = UShortArray.m2358getSizeimpl(r4);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3 = m3211maxOf5PvTz6A(r3, UShortArray.m2357getMh2AYeg(r4, r1));
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: minOf-5PvTz6A, reason: not valid java name */
    public static final short m3223minOf5PvTz6A(short r2, short r3) {
        if (Intrinsics.compare(r2 & UShort.MAX_VALUE, 65535 & r3) > 0) goto L5;
        return r2;
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: minOf-J1ME1BU, reason: not valid java name */
    public static int m3224minOfJ1ME1BU(int r1, int r2) {
        if (UByte$$ExternalSyntheticBackport4.m(r1, r2) > 0) goto L5;
        return r1;
    L5:
        return r2;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: minOf-Kr8caGY, reason: not valid java name */
    public static final byte m3225minOfKr8caGY(byte r2, byte r3) {
        if (Intrinsics.compare(r2 & UByte.MAX_VALUE, r3 & UByte.MAX_VALUE) > 0) goto L5;
        return r2;
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: minOf-Md2H83M, reason: not valid java name */
    public static final int m3226minOfMd2H83M(int r3, int... r4) {
        int r0 = UIntArray.m2174getSizeimpl(r4);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3 = m3224minOfJ1ME1BU(r3, UIntArray.m2173getpVg5ArA(r4, r1));
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: minOf-R03FKyM, reason: not valid java name */
    public static final long m3227minOfR03FKyM(long r4, long... r6) {
        int r0 = ULongArray.m2253getSizeimpl(r6);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r4 = m3232minOfeb3DHEI(r4, ULongArray.m2252getsVKNKU(r6, r1));
        r1 = r1 + 1;
        goto L3
    L5:
        return r4;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: minOf-VKSA0NQ, reason: not valid java name */
    private static final short m3228minOfVKSA0NQ(short r0, short r1, short r2) {
        return m3223minOf5PvTz6A(r0, m3223minOf5PvTz6A(r1, r2));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: minOf-WZ9TVnA, reason: not valid java name */
    private static final int m3229minOfWZ9TVnA(int r0, int r1, int r2) {
        return m3224minOfJ1ME1BU(r0, m3224minOfJ1ME1BU(r1, r2));
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: minOf-Wr6uiD8, reason: not valid java name */
    public static final byte m3230minOfWr6uiD8(byte r3, byte... r4) {
        int r0 = UByteArray.m2095getSizeimpl(r4);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3 = m3225minOfKr8caGY(r3, UByteArray.m2094getw2LRezQ(r4, r1));
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: minOf-b33U2AM, reason: not valid java name */
    private static final byte m3231minOfb33U2AM(byte r0, byte r1, byte r2) {
        return m3225minOfKr8caGY(r0, m3225minOfKr8caGY(r1, r2));
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: minOf-eb3DHEI, reason: not valid java name */
    public static long m3232minOfeb3DHEI(long r1, long r3) {
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r1, r3) > 0) goto L5;
        return r1;
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: minOf-sambcqE, reason: not valid java name */
    private static final long m3233minOfsambcqE(long r0, long r2, long r4) {
        return m3232minOfeb3DHEI(r0, m3232minOfeb3DHEI(r2, r4));
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: minOf-t1qELG4, reason: not valid java name */
    public static final short m3234minOft1qELG4(short r3, short... r4) {
        int r0 = UShortArray.m2358getSizeimpl(r4);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3 = m3223minOf5PvTz6A(r3, UShortArray.m2357getMh2AYeg(r4, r1));
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }
}
