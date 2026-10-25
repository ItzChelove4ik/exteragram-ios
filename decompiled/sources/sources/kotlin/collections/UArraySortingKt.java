package kotlin.collections;

import androidx.collection.MutableScatterMap$$ExternalSyntheticBackport0;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByte$$ExternalSyntheticBackport4;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u001a)\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0083\u0080\u0004¢\u0006\u0004\b\u0006\u0010\u0007\u001a)\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0083\u0080\u0004¢\u0006\u0004\b\n\u0010\u000b\u001a)\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0083\u0080\u0004¢\u0006\u0004\b\r\u0010\u000e\u001a)\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0083\u0080\u0004¢\u0006\u0004\b\u000f\u0010\u0010\u001a)\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0083\u0080\u0004¢\u0006\u0004\b\u0012\u0010\u0013\u001a)\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0083\u0080\u0004¢\u0006\u0004\b\u0014\u0010\u0015\u001a)\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0083\u0080\u0004¢\u0006\u0004\b\u0017\u0010\u0018\u001a)\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0083\u0080\u0004¢\u0006\u0004\b\u0019\u0010\u001a\u001a)\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0081\u0080\u0004¢\u0006\u0004\b\u001e\u0010\u000b\u001a)\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0081\u0080\u0004¢\u0006\u0004\b\u001f\u0010\u0010\u001a)\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0081\u0080\u0004¢\u0006\u0004\b \u0010\u0015\u001a)\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0081\u0080\u0004¢\u0006\u0004\b!\u0010\u001a¨\u0006\""}, d2 = {"partition", _UrlKt.FRAGMENT_ENCODE_SET, "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "quickSort", _UrlKt.FRAGMENT_ENCODE_SET, "quickSort-4UcCI2c", "([BII)V", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort-Aa5vz7o", "([SII)V", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "quickSort-oBK06Vg", "([III)V", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "quickSort--nroSd4", "([JII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-Aa5vz7o", "sortArray-oBK06Vg", "sortArray--nroSd4", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class UArraySortingKt {
    @ExperimentalUnsignedTypes
    /* renamed from: partition--nroSd4, reason: not valid java name */
    private static final int m2477partitionnroSd4(long[] r6, int r7, int r8) {
        long r0 = ULongArray.m2252getsVKNKU(r6, (r7 + r8) / 2);
    L3:
        if (r7 > r8) goto L12;
    L5:
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(ULongArray.m2252getsVKNKU(r6, r7), r0) >= 0) goto L8;
        r7 = r7 + 1;
    L8:
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(ULongArray.m2252getsVKNKU(r6, r8), r0) <= 0) goto L10;
        r8 = r8 - 1;
        goto L8
    L10:
        if (r7 > r8) goto L3;
        long r2 = ULongArray.m2252getsVKNKU(r6, r7);
        ULongArray.m2257setk8EXiF4(r6, r7, ULongArray.m2252getsVKNKU(r6, r8));
        ULongArray.m2257setk8EXiF4(r6, r8, r2);
        r7 = r7 + 1;
        r8 = r8 - 1;
        goto L3
    L12:
        return r7;
    }

    @ExperimentalUnsignedTypes
    /* renamed from: partition-4UcCI2c, reason: not valid java name */
    private static final int m2478partition4UcCI2c(byte[] r3, int r4, int r5) {
        byte r0 = UByteArray.m2094getw2LRezQ(r3, (r4 + r5) / 2);
    L3:
        if (r4 > r5) goto L12;
    L4:
        int r1 = UByteArray.m2094getw2LRezQ(r3, r4) & UByte.MAX_VALUE;
        int r2 = r0 & UByte.MAX_VALUE;
        if (Intrinsics.compare(r1, r2) >= 0) goto L8;
        r4 = r4 + 1;
    L8:
        if (Intrinsics.compare(UByteArray.m2094getw2LRezQ(r3, r5) & UByte.MAX_VALUE, r2) <= 0) goto L10;
        r5 = r5 - 1;
        goto L8
    L10:
        if (r4 > r5) goto L3;
        byte r12 = UByteArray.m2094getw2LRezQ(r3, r4);
        UByteArray.m2099setVurrAj0(r3, r4, UByteArray.m2094getw2LRezQ(r3, r5));
        UByteArray.m2099setVurrAj0(r3, r5, r12);
        r4 = r4 + 1;
        r5 = r5 - 1;
        goto L3
    L12:
        return r4;
    }

    @ExperimentalUnsignedTypes
    /* renamed from: partition-Aa5vz7o, reason: not valid java name */
    private static final int m2479partitionAa5vz7o(short[] r4, int r5, int r6) {
        short r0 = UShortArray.m2357getMh2AYeg(r4, (r5 + r6) / 2);
    L3:
        if (r5 > r6) goto L12;
    L4:
        int r1 = UShortArray.m2357getMh2AYeg(r4, r5) & UShort.MAX_VALUE;
        int r3 = r0 & UShort.MAX_VALUE;
        if (Intrinsics.compare(r1, r3) >= 0) goto L8;
        r5 = r5 + 1;
    L8:
        if (Intrinsics.compare(UShortArray.m2357getMh2AYeg(r4, r6) & UShort.MAX_VALUE, r3) <= 0) goto L10;
        r6 = r6 - 1;
        goto L8
    L10:
        if (r5 > r6) goto L3;
        short r12 = UShortArray.m2357getMh2AYeg(r4, r5);
        UShortArray.m2362set01HTLdE(r4, r5, UShortArray.m2357getMh2AYeg(r4, r6));
        UShortArray.m2362set01HTLdE(r4, r6, r12);
        r5 = r5 + 1;
        r6 = r6 - 1;
        goto L3
    L12:
        return r5;
    }

    @ExperimentalUnsignedTypes
    /* renamed from: partition-oBK06Vg, reason: not valid java name */
    private static final int m2480partitionoBK06Vg(int[] r3, int r4, int r5) {
        int r0 = UIntArray.m2173getpVg5ArA(r3, (r4 + r5) / 2);
    L3:
        if (r4 > r5) goto L12;
    L5:
        if (UByte$$ExternalSyntheticBackport4.m(UIntArray.m2173getpVg5ArA(r3, r4), r0) >= 0) goto L8;
        r4 = r4 + 1;
    L8:
        if (UByte$$ExternalSyntheticBackport4.m(UIntArray.m2173getpVg5ArA(r3, r5), r0) <= 0) goto L10;
        r5 = r5 - 1;
        goto L8
    L10:
        if (r4 > r5) goto L3;
        int r1 = UIntArray.m2173getpVg5ArA(r3, r4);
        UIntArray.m2178setVXSXFK8(r3, r4, UIntArray.m2173getpVg5ArA(r3, r5));
        UIntArray.m2178setVXSXFK8(r3, r5, r1);
        r4 = r4 + 1;
        r5 = r5 - 1;
        goto L3
    L12:
        return r4;
    }

    @ExperimentalUnsignedTypes
    /* renamed from: quickSort--nroSd4, reason: not valid java name */
    private static final void m2481quickSortnroSd4(long[] r2, int r3, int r4) {
        int r0 = m2477partitionnroSd4(r2, r3, r4);
        int r1 = r0 - 1;
        if (r3 >= r1) goto L5;
        m2481quickSortnroSd4(r2, r3, r1);
    L5:
        if (r0 >= r4) goto L8;
        m2481quickSortnroSd4(r2, r0, r4);
        return;
    }

    @ExperimentalUnsignedTypes
    /* renamed from: quickSort-4UcCI2c, reason: not valid java name */
    private static final void m2482quickSort4UcCI2c(byte[] r2, int r3, int r4) {
        int r0 = m2478partition4UcCI2c(r2, r3, r4);
        int r1 = r0 - 1;
        if (r3 >= r1) goto L5;
        m2482quickSort4UcCI2c(r2, r3, r1);
    L5:
        if (r0 >= r4) goto L8;
        m2482quickSort4UcCI2c(r2, r0, r4);
        return;
    }

    @ExperimentalUnsignedTypes
    /* renamed from: quickSort-Aa5vz7o, reason: not valid java name */
    private static final void m2483quickSortAa5vz7o(short[] r2, int r3, int r4) {
        int r0 = m2479partitionAa5vz7o(r2, r3, r4);
        int r1 = r0 - 1;
        if (r3 >= r1) goto L5;
        m2483quickSortAa5vz7o(r2, r3, r1);
    L5:
        if (r0 >= r4) goto L8;
        m2483quickSortAa5vz7o(r2, r0, r4);
        return;
    }

    @ExperimentalUnsignedTypes
    /* renamed from: quickSort-oBK06Vg, reason: not valid java name */
    private static final void m2484quickSortoBK06Vg(int[] r2, int r3, int r4) {
        int r0 = m2480partitionoBK06Vg(r2, r3, r4);
        int r1 = r0 - 1;
        if (r3 >= r1) goto L5;
        m2484quickSortoBK06Vg(r2, r3, r1);
    L5:
        if (r0 >= r4) goto L8;
        m2484quickSortoBK06Vg(r2, r0, r4);
        return;
    }

    @ExperimentalUnsignedTypes
    /* renamed from: sortArray--nroSd4, reason: not valid java name */
    public static final void m2485sortArraynroSd4(long[] r0, int r1, int r2) {
        m2481quickSortnroSd4(r0, r1, r2 - 1);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: sortArray-4UcCI2c, reason: not valid java name */
    public static final void m2486sortArray4UcCI2c(byte[] r0, int r1, int r2) {
        m2482quickSort4UcCI2c(r0, r1, r2 - 1);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: sortArray-Aa5vz7o, reason: not valid java name */
    public static final void m2487sortArrayAa5vz7o(short[] r0, int r1, int r2) {
        m2483quickSortAa5vz7o(r0, r1, r2 - 1);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: sortArray-oBK06Vg, reason: not valid java name */
    public static final void m2488sortArrayoBK06Vg(int[] r0, int r1, int r2) {
        m2484quickSortoBK06Vg(r0, r1, r2 - 1);
    }
}
