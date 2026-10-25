package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0080\u0080\u0004\u001a\u001b\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0002\b\u0007\u001a\u000e\u0010\u000b\u001a\u00020\f*\u00020\u0004H\u0081\u0080\u0004\"\u0019\u0010\b\u001a\u00020\u0001*\u00020\u00048BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"convertDurationUnitToMilliseconds", _UrlKt.FRAGMENT_ENCODE_SET, "value", "unit", "Lkotlin/time/DurationUnit;", "multiplyNonNegativeWithoutOverflow", "other", "multiplyNonNegativeWithoutOverflow$DurationUnitKt__DurationUnitKt", "millisMultiplier", "getMillisMultiplier$DurationUnitKt__DurationUnitKt", "(Lkotlin/time/DurationUnit;)J", "shortName", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/time/DurationUnitKt")
/* loaded from: classes.dex */
class DurationUnitKt__DurationUnitKt extends DurationUnitKt__DurationUnitJvmKt {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[DurationUnit.values().length];
            r0[DurationUnit.DAYS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L12
        L21:
            r0[DurationUnit.HOURS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L13
        L31:
            r0[DurationUnit.MINUTES.ordinal()] = 3;     // Catch: NoSuchFieldError -> L14
        L23:
            r0[DurationUnit.SECONDS.ordinal()] = 4;     // Catch: NoSuchFieldError -> L15
        L25:
            r0[DurationUnit.MILLISECONDS.ordinal()] = 5;     // Catch: NoSuchFieldError -> L16
        L19:
            r0[DurationUnit.NANOSECONDS.ordinal()] = 6;     // Catch: NoSuchFieldError -> L17
        L27:
            r0[DurationUnit.MICROSECONDS.ordinal()] = 7;     // Catch: NoSuchFieldError -> L18
        L10:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public DurationUnitKt__DurationUnitKt() {
    }

    public static final long convertDurationUnitToMilliseconds(long r2, DurationUnit r4) {
        return multiplyNonNegativeWithoutOverflow$DurationUnitKt__DurationUnitKt(r2, getMillisMultiplier$DurationUnitKt__DurationUnitKt(r4));
    }

    private static final long getMillisMultiplier$DurationUnitKt__DurationUnitKt(DurationUnit r2) {
        int r0 = WhenMappings.$EnumSwitchMapping$0[r2.ordinal()];
        if (r0 != 1) goto L5;
        return DurationKt.MILLIS_IN_DAY;
    L5:
        if (r0 != 2) goto L7;
        return DurationKt.MILLIS_IN_HOUR;
    L7:
        if (r0 != 3) goto L9;
        return 60000;
    L9:
        if (r0 != 4) goto L11;
        return 1000;
    L11:
        if (r0 != 5) goto L14;
        return 1;
    L14:
        DurationKt$$ExternalSyntheticBUOutline0.m("Wrong unit for millisMultiplier: ", r2);
        return 0;
    }

    private static final long multiplyNonNegativeWithoutOverflow$DurationUnitKt__DurationUnitKt(long r5, long r7) {
        if (r5 != 0) goto L6;
        return 0;
    L6:
        if (r5 != 1) goto L10;
        return RangesKt.coerceAtMost(r7, DurationKt.MAX_MILLIS);
    L10:
        if (r7 == 1) goto L12;
        int r0 = (128 - Long.numberOfLeadingZeros(r5)) - Long.numberOfLeadingZeros(r7);
        if (r0 < 63) goto L16;
        if (r0 <= 63) goto L20;
        return DurationKt.MAX_MILLIS;
    L20:
        return RangesKt.coerceAtMost(r5 * r7, DurationKt.MAX_MILLIS);
    L16:
        return r5 * r7;
    L12:
        return RangesKt.coerceAtMost(r5, DurationKt.MAX_MILLIS);
    }

    @SinceKotlin(version = "1.3")
    public static final String shortName(DurationUnit r2) {
        switch(WhenMappings.$EnumSwitchMapping$0[r2.ordinal()]) {
            case 1: goto L18;
            case 2: goto L16;
            case 3: goto L14;
            case 4: goto L12;
            case 5: goto L10;
            case 6: goto L8;
            case 7: goto L6;
            default: goto L4;
        };
    L4:
        DurationKt$$ExternalSyntheticBUOutline0.m("Unknown unit: ", r2);
        return null;
    L6:
        return "us";
    L8:
        return "ns";
    L10:
        return "ms";
    L12:
        return "s";
    L14:
        return "m";
    L16:
        return "h";
    L18:
        return "d";
    }
}
