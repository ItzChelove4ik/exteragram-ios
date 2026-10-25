package kotlin.internal;

import androidx.collection.MutableScatterMap$$ExternalSyntheticBackport0;
import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.UByte$$ExternalSyntheticBackport1;
import kotlin.UByte$$ExternalSyntheticBackport2;
import kotlin.UByte$$ExternalSyntheticBackport4;
import kotlin.UInt;
import kotlin.ULong;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a)\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0004\b\u0005\u0010\u0006\u001a)\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0082\u0080\u0004¢\u0006\u0004\b\b\u0010\t\u001a)\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0081\u0080\u0004¢\u0006\u0004\b\u000f\u0010\u0006\u001a)\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0081\u0080\u0004¢\u0006\u0004\b\u0011\u0010\t¨\u0006\u0012"}, d2 = {"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", _UrlKt.FRAGMENT_ENCODE_SET, "getProgressionLastElement-Nkh28Cs", _UrlKt.FRAGMENT_ENCODE_SET, "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class UProgressionUtilKt {
    /* renamed from: differenceModulo-WZ9TVnA, reason: not valid java name */
    private static final int m3237differenceModuloWZ9TVnA(int r1, int r2, int r3) {
        int r12 = UByte$$ExternalSyntheticBackport1.m(r1, r3);
        int r22 = UByte$$ExternalSyntheticBackport1.m(r2, r3);
        int r0 = UByte$$ExternalSyntheticBackport4.m(r12, r22);
        int r13 = UInt.m2113constructorimpl(r12 - r22);
        if (r0 < 0) goto L6;
        return r13;
    L6:
        return UInt.m2113constructorimpl(r13 + r3);
    }

    /* renamed from: differenceModulo-sambcqE, reason: not valid java name */
    private static final long m3238differenceModulosambcqE(long r1, long r3, long r5) {
        long r12 = UByte$$ExternalSyntheticBackport2.m(r1, r5);
        long r32 = UByte$$ExternalSyntheticBackport2.m(r3, r5);
        int r0 = MutableScatterMap$$ExternalSyntheticBackport0.m(r12, r32);
        long r13 = ULong.m2192constructorimpl(r12 - r32);
        if (r0 < 0) goto L6;
        return r13;
    L6:
        return ULong.m2192constructorimpl(r13 + r5);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* renamed from: getProgressionLastElement-7ftBX0g, reason: not valid java name */
    public static final long m3239getProgressionLastElement7ftBX0g(long r8, long r10, long r12) {
        if (r12 <= 0) goto L10;
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r8, r10) < 0) goto L8;
        return r10;
    L8:
        return ULong.m2192constructorimpl(r10 - m3238differenceModulosambcqE(r10, r8, ULong.m2192constructorimpl(r12)));
    L10:
        if (r12 < 0) goto L12;
        f$$ExternalSyntheticBUOutline1.m("Step is zero.");
        return 0;
    L12:
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r8, r10) > 0) goto L15;
        return r10;
    L15:
        return ULong.m2192constructorimpl(r10 + m3238differenceModulosambcqE(r8, r10, ULong.m2192constructorimpl(-r12)));
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* renamed from: getProgressionLastElement-Nkh28Cs, reason: not valid java name */
    public static final int m3240getProgressionLastElementNkh28Cs(int r1, int r2, int r3) {
        if (r3 > 0) goto L4;
        if (r3 < 0) goto L10;
        f$$ExternalSyntheticBUOutline1.m("Step is zero.");
        return 0;
    L10:
        if (UByte$$ExternalSyntheticBackport4.m(r1, r2) > 0) goto L13;
    L11:
        return r2;
    L13:
        return UInt.m2113constructorimpl(r2 + m3237differenceModuloWZ9TVnA(r1, r2, UInt.m2113constructorimpl(-r3)));
    L4:
        if (UByte$$ExternalSyntheticBackport4.m(r1, r2) >= 0) goto L11;
        return UInt.m2113constructorimpl(r2 - m3237differenceModuloWZ9TVnA(r2, r1, UInt.m2113constructorimpl(r3)));
    }
}
