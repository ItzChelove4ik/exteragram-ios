package kotlin.time;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.Duration;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\u001a)\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0080\u0080\u0004¢\u0006\u0004\b\u0007\u0010\b\u001a)\u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0004\b\u000b\u0010\f\u001a)\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0082\u0080\u0004¢\u0006\u0004\b\u000e\u0010\b\u001a\u0017\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0002\u0010\u0010\u001a'\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0080\u0080\u0004¢\u0006\u0002\u0010\u0014\u001a'\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0080\u0080\u0004¢\u0006\u0002\u0010\u0014\u001a'\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0082\u0080\u0004¢\u0006\u0002\u0010\u0014\u001a\u000e\u0010\u001b\u001a\u00020\u001c*\u00020\u0001H\u0080\u0088\u0004¨\u0006\u001d"}, d2 = {"saturatingAdd", _UrlKt.FRAGMENT_ENCODE_SET, "value", "unit", "Lkotlin/time/DurationUnit;", "duration", "Lkotlin/time/Duration;", "saturatingAdd-NuflL3o", "(JLkotlin/time/DurationUnit;J)J", "checkInfiniteSumDefined", "durationInUnit", "checkInfiniteSumDefined-PjuGub4", "(JJJ)J", "saturatingAddInHalves", "saturatingAddInHalves-NuflL3o", "infinityOfSign", "(J)J", "saturatingDiff", "valueNs", "origin", "(JJLkotlin/time/DurationUnit;)J", "saturatingOriginsDiff", "origin1", "origin2", "saturatingFiniteDiff", "value1", "value2", "isSaturated", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nlongSaturatedMath.kt\nKotlin\n*S Kotlin\n*F\n+ 1 longSaturatedMath.kt\nkotlin/time/LongSaturatedMathKt\n*L\n1#1,81:1\n80#1:82\n80#1:83\n80#1:84\n80#1:85\n80#1:86\n80#1:87\n*S KotlinDebug\n*F\n+ 1 longSaturatedMath.kt\nkotlin/time/LongSaturatedMathKt\n*L\n14#1:82\n17#1:83\n36#1:84\n46#1:85\n53#1:86\n57#1:87\n*E\n"})
/* loaded from: classes5.dex */
public final class LongSaturatedMathKt {
    /* renamed from: checkInfiniteSumDefined-PjuGub4, reason: not valid java name */
    private static final long m3482checkInfiniteSumDefinedPjuGub4(long r0, long r2, long r4) {
        if (Duration.m3400isInfiniteimpl(r2) == true) goto L5;
        return r0;
    L5:
        if ((r0 ^ r4) >= 0) goto L10;
        f$$ExternalSyntheticBUOutline1.m("Summing infinities of different signs");
        return 0;
    L10:
        return r0;
    }

    private static final long infinityOfSign(long r2) {
        if (r2 >= 0) goto L7;
        return Duration.Companion.m3469getNEG_INFINITEUwyO8pc$kotlin_stdlib();
    L7:
        return Duration.Companion.m3467getINFINITEUwyO8pc();
    }

    public static final boolean isSaturated(long r2) {
        if (((r2 - 1) | 1) != LongCompanionObject.MAX_VALUE) goto L6;
        return true;
    L6:
        return false;
    }

    /* renamed from: saturatingAdd-NuflL3o, reason: not valid java name */
    public static final long m3483saturatingAddNuflL3o(long r10, DurationUnit r12, long r13) {
        long r4 = Duration.m3414toLongimpl(r13, r12);
        if (((r10 - 1) | 1) != LongCompanionObject.MAX_VALUE) goto L7;
        return m3482checkInfiniteSumDefinedPjuGub4(r10, r13, r4);
    L7:
        if (((r4 - 1) | 1) == LongCompanionObject.MAX_VALUE) goto L9;
        long r102 = r10 + r4;
        if (((r10 ^ r102) & (r4 ^ r102)) < 0) goto L13;
        return r102;
    L13:
        if (r10 >= 0) goto L16;
        return Long.MIN_VALUE;
    L16:
        return LongCompanionObject.MAX_VALUE;
    L9:
        return m3484saturatingAddInHalvesNuflL3o(r10, r12, r13);
    }

    /* renamed from: saturatingAddInHalves-NuflL3o, reason: not valid java name */
    private static final long m3484saturatingAddInHalvesNuflL3o(long r8, DurationUnit r10, long r11) {
        long r0 = Duration.m3378divUwyO8pc(r11, 2);
        long r2 = Duration.m3414toLongimpl(r0, r10);
        if ((1 | (r2 - 1)) != LongCompanionObject.MAX_VALUE) goto L6;
        return r2;
    L6:
        return m3483saturatingAddNuflL3o(m3483saturatingAddNuflL3o(r8, r10, r0), r10, Duration.m3403minusLRDsOJo(r11, r0));
    }

    public static final long saturatingDiff(long r4, long r6, DurationUnit r8) {
        if ((1 | (r6 - 1)) != LongCompanionObject.MAX_VALUE) goto L7;
        return Duration.m3419unaryMinusUwyO8pc(infinityOfSign(r6));
    L7:
        return saturatingFiniteDiff(r4, r6, r8);
    }

    private static final long saturatingFiniteDiff(long r7, long r9, DurationUnit r11) {
        long r0 = r7 - r9;
        if (((r0 ^ r7) & (~(r0 ^ r9))) >= 0) goto L11;
        DurationUnit r2 = DurationUnit.MILLISECONDS;
        if (r11.compareTo(r2) >= 0) goto L9;
        long r02 = DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(1, r2, r11);
        long r3 = (r7 / r02) - (r9 / r02);
        long r72 = (r7 % r02) - (r9 % r02);
        Duration.Companion r92 = Duration.Companion;
        return Duration.m3404plusLRDsOJo(DurationKt.toDuration(r3, r2), DurationKt.toDuration(r72, r11));
    L9:
        return Duration.m3419unaryMinusUwyO8pc(infinityOfSign(r0));
    L11:
        return DurationKt.toDuration(r0, r11);
    }

    public static final long saturatingOriginsDiff(long r6, long r8, DurationUnit r10) {
        if (((r8 - 1) | 1) != LongCompanionObject.MAX_VALUE) goto L11;
        if (r6 != r8) goto L9;
        return Duration.Companion.m3470getZEROUwyO8pc();
    L9:
        return Duration.m3419unaryMinusUwyO8pc(infinityOfSign(r8));
    L11:
        if ((1 | (r6 - 1)) != LongCompanionObject.MAX_VALUE) goto L15;
        return infinityOfSign(r6);
    L15:
        return saturatingFiniteDiff(r6, r8, r10);
    }
}
