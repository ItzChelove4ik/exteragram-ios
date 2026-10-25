package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0080\u0004\u001a\u000e\u0010\u0003\u001a\u00020\u0002*\u00020\u0001H\u0087\u0080\u0004\u001a\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0081\u0080\u0004\u001a\"\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0081\u0080\u0004\u001a\"\u0010\u0004\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0081\u0080\u0004¨\u0006\u000b"}, d2 = {"toTimeUnit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "toDurationUnit", "convertDurationUnit", _UrlKt.FRAGMENT_ENCODE_SET, "value", "sourceUnit", "targetUnit", "convertDurationUnitOverflow", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/time/DurationUnitKt")
/* loaded from: classes.dex */
class DurationUnitKt__DurationUnitJvmKt {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[TimeUnit.values().length];
            r0[TimeUnit.NANOSECONDS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L12
        L21:
            r0[TimeUnit.MICROSECONDS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L13
        L31:
            r0[TimeUnit.MILLISECONDS.ordinal()] = 3;     // Catch: NoSuchFieldError -> L14
        L23:
            r0[TimeUnit.SECONDS.ordinal()] = 4;     // Catch: NoSuchFieldError -> L15
        L25:
            r0[TimeUnit.MINUTES.ordinal()] = 5;     // Catch: NoSuchFieldError -> L16
        L19:
            r0[TimeUnit.HOURS.ordinal()] = 6;     // Catch: NoSuchFieldError -> L17
        L27:
            r0[TimeUnit.DAYS.ordinal()] = 7;     // Catch: NoSuchFieldError -> L18
        L10:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public DurationUnitKt__DurationUnitJvmKt() {
    }

    @SinceKotlin(version = "1.3")
    public static final double convertDurationUnit(double r6, DurationUnit r8, DurationUnit r9) {
        long r0 = r9.getTimeUnit$kotlin_stdlib().convert(1, r8.getTimeUnit$kotlin_stdlib());
        if (r0 <= 0) goto L7;
        return r6 * r0;
    L7:
        return r6 / r8.getTimeUnit$kotlin_stdlib().convert(1, r9.getTimeUnit$kotlin_stdlib());
    }

    @SinceKotlin(version = "1.5")
    public static final long convertDurationUnit(long r0, DurationUnit r2, DurationUnit r3) {
        return r3.getTimeUnit$kotlin_stdlib().convert(r0, r2.getTimeUnit$kotlin_stdlib());
    }

    @SinceKotlin(version = "1.5")
    public static final long convertDurationUnitOverflow(long r0, DurationUnit r2, DurationUnit r3) {
        return r3.getTimeUnit$kotlin_stdlib().convert(r0, r2.getTimeUnit$kotlin_stdlib());
    }

    @SinceKotlin(version = "1.8")
    public static final DurationUnit toDurationUnit(TimeUnit r1) {
        switch(WhenMappings.$EnumSwitchMapping$0[r1.ordinal()]) {
            case 1: goto L19;
            case 2: goto L17;
            case 3: goto L15;
            case 4: goto L13;
            case 5: goto L11;
            case 6: goto L9;
            case 7: goto L7;
            default: goto L4;
        };
    L4:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L7:
        return DurationUnit.DAYS;
    L9:
        return DurationUnit.HOURS;
    L11:
        return DurationUnit.MINUTES;
    L13:
        return DurationUnit.SECONDS;
    L15:
        return DurationUnit.MILLISECONDS;
    L17:
        return DurationUnit.MICROSECONDS;
    L19:
        return DurationUnit.NANOSECONDS;
    }

    @SinceKotlin(version = "1.8")
    public static final TimeUnit toTimeUnit(DurationUnit r0) {
        return r0.getTimeUnit$kotlin_stdlib();
    }
}
