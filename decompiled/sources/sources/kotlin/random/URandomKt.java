package kotlin.random;

import androidx.collection.MutableScatterMap$$ExternalSyntheticBackport0;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte$$ExternalSyntheticBackport4;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.UIntRange;
import kotlin.ranges.ULongRange;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\u001a\u0013\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0004\b\u0005\u0010\u0006\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0004\b\b\u0010\t\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0087\u0080\u0004¢\u0006\u0002\u0010\f\u001a\u0013\u0010\r\u001a\u00020\u000e*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\u000f\u001a\u001d\u0010\r\u001a\u00020\u000e*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u000eH\u0087\u0080\u0004¢\u0006\u0004\b\u0010\u0010\u0011\u001a%\u0010\r\u001a\u00020\u000e*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000eH\u0087\u0080\u0004¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u001b\u0010\r\u001a\u00020\u000e*\u00020\u00022\u0006\u0010\n\u001a\u00020\u0014H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0015\u001a\u001d\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0017H\u0087\u0080\b¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u001b\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001cH\u0087\u0080\u0004¢\u0006\u0002\u0010\u001d\u001a1\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u001e\u001a\u00020\u001c2\b\b\u0002\u0010\u001f\u001a\u00020\u001cH\u0087\u0080\b¢\u0006\u0004\b \u0010!\u001a!\u0010\"\u001a\u00020#2\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0080\u0080\u0004¢\u0006\u0004\b$\u0010%\u001a!\u0010&\u001a\u00020#2\u0006\u0010\u0007\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000eH\u0080\u0080\u0004¢\u0006\u0004\b'\u0010(¨\u0006)"}, d2 = {"nextUInt", "Lkotlin/UInt;", "Lkotlin/random/Random;", "(Lkotlin/random/Random;)I", "until", "nextUInt-qCasIEU", "(Lkotlin/random/Random;I)I", "from", "nextUInt-a8DCA5k", "(Lkotlin/random/Random;II)I", "range", "Lkotlin/ranges/UIntRange;", "(Lkotlin/random/Random;Lkotlin/ranges/UIntRange;)I", "nextULong", "Lkotlin/ULong;", "(Lkotlin/random/Random;)J", "nextULong-V1Xi4fY", "(Lkotlin/random/Random;J)J", "nextULong-jmpaW-c", "(Lkotlin/random/Random;JJ)J", "Lkotlin/ranges/ULongRange;", "(Lkotlin/random/Random;Lkotlin/ranges/ULongRange;)J", "nextUBytes", "Lkotlin/UByteArray;", "array", "nextUBytes-EVgfTAA", "(Lkotlin/random/Random;[B)[B", "size", _UrlKt.FRAGMENT_ENCODE_SET, "(Lkotlin/random/Random;I)[B", "fromIndex", "toIndex", "nextUBytes-Wvrt4B4", "(Lkotlin/random/Random;[BII)[B", "checkUIntRangeBounds", _UrlKt.FRAGMENT_ENCODE_SET, "checkUIntRangeBounds-J1ME1BU", "(II)V", "checkULongRangeBounds", "checkULongRangeBounds-eb3DHEI", "(JJ)V", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nURandom.kt\nKotlin\n*S Kotlin\n*F\n+ 1 URandom.kt\nkotlin/random/URandomKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,149:1\n1#2:150\n*E\n"})
/* loaded from: classes5.dex */
public final class URandomKt {
    /* renamed from: checkUIntRangeBounds-J1ME1BU, reason: not valid java name */
    public static final void m3249checkUIntRangeBoundsJ1ME1BU(int r1, int r2) {
        if (UByte$$ExternalSyntheticBackport4.m(r2, r1) <= 0) goto L5;
        return;
    L5:
        RandomKt$$ExternalSyntheticBUOutline0.m(RandomKt.boundsErrorMessage(UInt.m2107boximpl(r1), UInt.m2107boximpl(r2)));
    }

    /* renamed from: checkULongRangeBounds-eb3DHEI, reason: not valid java name */
    public static final void m3250checkULongRangeBoundseb3DHEI(long r1, long r3) {
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r3, r1) <= 0) goto L5;
        return;
    L5:
        RandomKt$$ExternalSyntheticBUOutline0.m(RandomKt.boundsErrorMessage(ULong.m2186boximpl(r1), ULong.m2186boximpl(r3)));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final byte[] nextUBytes(Random r0, int r1) {
        return UByteArray.m2089constructorimpl(r0.nextBytes(r1));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @IgnorableReturnValue
    /* renamed from: nextUBytes-EVgfTAA, reason: not valid java name */
    public static final byte[] m3251nextUBytesEVgfTAA(Random r0, byte[] r1) {
        r0.nextBytes(r1);
        return r1;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @IgnorableReturnValue
    /* renamed from: nextUBytes-Wvrt4B4, reason: not valid java name */
    public static final byte[] m3252nextUBytesWvrt4B4(Random r0, byte[] r1, int r2, int r3) {
        r0.nextBytes(r1, r2, r3);
        return r1;
    }

    /* renamed from: nextUBytes-Wvrt4B4$default, reason: not valid java name */
    public static /* synthetic */ byte[] m3253nextUBytesWvrt4B4$default(Random r0, byte[] r1, int r2, int r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = 0;
    L6:
        if ((r4 & 4) == 0) goto L9;
        r3 = UByteArray.m2095getSizeimpl(r1);
    L9:
        return m3252nextUBytesWvrt4B4(r0, r1, r2, r3);
    }

    @SinceKotlin(version = "1.5")
    public static final int nextUInt(Random r0) {
        return UInt.m2113constructorimpl(r0.nextInt());
    }

    @SinceKotlin(version = "1.5")
    public static final int nextUInt(Random r3, UIntRange r4) {
        if (r4.isEmpty() == false) goto L5;
        Native$$ExternalSyntheticBUOutline5.m("Cannot get random in empty range: ", r4);
        return 0;
    L5:
        if (UByte$$ExternalSyntheticBackport4.m(r4.m3259getLastpVg5ArA(), -1) >= 0) goto L9;
        return m3254nextUInta8DCA5k(r3, r4.m3258getFirstpVg5ArA(), UInt.m2113constructorimpl(r4.m3259getLastpVg5ArA() + 1));
    L9:
        if (UByte$$ExternalSyntheticBackport4.m(r4.m3258getFirstpVg5ArA(), 0) <= 0) goto L13;
        return UInt.m2113constructorimpl(m3254nextUInta8DCA5k(r3, UInt.m2113constructorimpl(r4.m3258getFirstpVg5ArA() - 1), r4.m3259getLastpVg5ArA()) + 1);
    L13:
        return nextUInt(r3);
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: nextUInt-a8DCA5k, reason: not valid java name */
    public static final int m3254nextUInta8DCA5k(Random r1, int r2, int r3) {
        m3249checkUIntRangeBoundsJ1ME1BU(r2, r3);
        return UInt.m2113constructorimpl(r1.nextInt(r2 ^ Integer.MIN_VALUE, r3 ^ Integer.MIN_VALUE) ^ Integer.MIN_VALUE);
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: nextUInt-qCasIEU, reason: not valid java name */
    public static final int m3255nextUIntqCasIEU(Random r1, int r2) {
        return m3254nextUInta8DCA5k(r1, 0, r2);
    }

    @SinceKotlin(version = "1.5")
    public static final long nextULong(Random r2) {
        return ULong.m2192constructorimpl(r2.nextLong());
    }

    @SinceKotlin(version = "1.5")
    public static final long nextULong(Random r7, ULongRange r8) {
        if (r8.isEmpty() == false) goto L5;
        Native$$ExternalSyntheticBUOutline5.m("Cannot get random in empty range: ", r8);
        return 0;
    L5:
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r8.m3268getLastsVKNKU(), -1) >= 0) goto L9;
        return m3257nextULongjmpaWc(r7, r8.m3267getFirstsVKNKU(), ULong.m2192constructorimpl(r8.m3268getLastsVKNKU() + ULong.m2192constructorimpl(1)));
    L9:
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r8.m3267getFirstsVKNKU(), 0) <= 0) goto L13;
        return ULong.m2192constructorimpl(m3257nextULongjmpaWc(r7, ULong.m2192constructorimpl(r8.m3267getFirstsVKNKU() - ULong.m2192constructorimpl(1)), r8.m3268getLastsVKNKU()) + ULong.m2192constructorimpl(1));
    L13:
        return nextULong(r7);
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: nextULong-V1Xi4fY, reason: not valid java name */
    public static final long m3256nextULongV1Xi4fY(Random r2, long r3) {
        return m3257nextULongjmpaWc(r2, 0, r3);
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: nextULong-jmpaW-c, reason: not valid java name */
    public static final long m3257nextULongjmpaWc(Random r2, long r3, long r5) {
        m3250checkULongRangeBoundseb3DHEI(r3, r5);
        return ULong.m2192constructorimpl(r2.nextLong(r3 ^ Long.MIN_VALUE, r5 ^ Long.MIN_VALUE) ^ Long.MIN_VALUE);
    }
}
