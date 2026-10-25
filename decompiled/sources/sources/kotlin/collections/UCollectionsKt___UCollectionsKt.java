package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.JvmName;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0007\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0004\u001a\u0019\u0010\u0005\u001a\u00020\u0006*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\b\u001a\u0019\u0010\t\u001a\u00020\n*\b\u0012\u0004\u0012\u00020\u000b0\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\f\u001a\u0019\u0010\r\u001a\u00020\u000e*\b\u0012\u0004\u0012\u00020\u000f0\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0010\u001a\u001b\u0010\u0011\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0012H\u0087\u0080\u0004¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001b\u0010\u0011\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\u000b0\u0012H\u0087\u0080\u0004¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u001b\u0010\u0011\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00030\u0012H\u0087\u0080\u0004¢\u0006\u0004\b\u0017\u0010\u0014\u001a\u001b\u0010\u0011\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u000f0\u0012H\u0087\u0080\u0004¢\u0006\u0004\b\u0018\u0010\u0014¨\u0006\u0019"}, d2 = {"toUByteArray", "Lkotlin/UByteArray;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/UByte;", "(Ljava/util/Collection;)[B", "toUIntArray", "Lkotlin/UIntArray;", "Lkotlin/UInt;", "(Ljava/util/Collection;)[I", "toULongArray", "Lkotlin/ULongArray;", "Lkotlin/ULong;", "(Ljava/util/Collection;)[J", "toUShortArray", "Lkotlin/UShortArray;", "Lkotlin/UShort;", "(Ljava/util/Collection;)[S", "sum", _UrlKt.FRAGMENT_ENCODE_SET, "sumOfUInt", "(Ljava/lang/Iterable;)I", "sumOfULong", "(Ljava/lang/Iterable;)J", "sumOfUByte", "sumOfUShort", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/collections/UCollectionsKt")
/* loaded from: classes5.dex */
class UCollectionsKt___UCollectionsKt {
    public UCollectionsKt___UCollectionsKt() {
    }

    @SinceKotlin(version = "1.5")
    @JvmName(name = "sumOfUByte")
    public static final int sumOfUByte(Iterable<UByte> r2) {
        Iterator<UByte> r22 = r2.iterator();
        int r0 = 0;
    L4:
        if (r22.hasNext() == false) goto L6;
        r0 = UInt.m2113constructorimpl(r0 + UInt.m2113constructorimpl(r22.next().m2086unboximpl() & UByte.MAX_VALUE));
        goto L4
    L6:
        return r0;
    }

    @SinceKotlin(version = "1.5")
    @JvmName(name = "sumOfUInt")
    public static final int sumOfUInt(Iterable<UInt> r2) {
        Iterator<UInt> r22 = r2.iterator();
        int r0 = 0;
    L4:
        if (r22.hasNext() == false) goto L6;
        r0 = UInt.m2113constructorimpl(r0 + r22.next().m2165unboximpl());
        goto L4
    L6:
        return r0;
    }

    @SinceKotlin(version = "1.5")
    @JvmName(name = "sumOfULong")
    public static final long sumOfULong(Iterable<ULong> r4) {
        Iterator<ULong> r42 = r4.iterator();
        long r0 = 0;
    L4:
        if (r42.hasNext() == false) goto L6;
        r0 = ULong.m2192constructorimpl(r0 + r42.next().m2244unboximpl());
        goto L4
    L6:
        return r0;
    }

    @SinceKotlin(version = "1.5")
    @JvmName(name = "sumOfUShort")
    public static final int sumOfUShort(Iterable<UShort> r3) {
        Iterator<UShort> r32 = r3.iterator();
        int r0 = 0;
    L4:
        if (r32.hasNext() == false) goto L6;
        r0 = UInt.m2113constructorimpl(r0 + UInt.m2113constructorimpl(r32.next().m2349unboximpl() & UShort.MAX_VALUE));
        goto L4
    L6:
        return r0;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final byte[] toUByteArray(Collection<UByte> r4) {
        byte[] r0 = UByteArray.m2088constructorimpl(r4.size());
        Iterator<UByte> r42 = r4.iterator();
        int r1 = 0;
    L4:
        if (r42.hasNext() == false) goto L6;
        UByteArray.m2099setVurrAj0(r0, r1, r42.next().m2086unboximpl());
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final int[] toUIntArray(Collection<UInt> r4) {
        int[] r0 = UIntArray.m2167constructorimpl(r4.size());
        Iterator<UInt> r42 = r4.iterator();
        int r1 = 0;
    L4:
        if (r42.hasNext() == false) goto L6;
        UIntArray.m2178setVXSXFK8(r0, r1, r42.next().m2165unboximpl());
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final long[] toULongArray(Collection<ULong> r5) {
        long[] r0 = ULongArray.m2246constructorimpl(r5.size());
        Iterator<ULong> r52 = r5.iterator();
        int r1 = 0;
    L4:
        if (r52.hasNext() == false) goto L6;
        ULongArray.m2257setk8EXiF4(r0, r1, r52.next().m2244unboximpl());
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final short[] toUShortArray(Collection<UShort> r4) {
        short[] r0 = UShortArray.m2351constructorimpl(r4.size());
        Iterator<UShort> r42 = r4.iterator();
        int r1 = 0;
    L4:
        if (r42.hasNext() == false) goto L6;
        UShortArray.m2362set01HTLdE(r0, r1, r42.next().m2349unboximpl());
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }
}
