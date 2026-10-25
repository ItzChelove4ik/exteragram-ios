package kotlin.time;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b-\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0005\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0007\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0002\u0010\t\u001a\u001d\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0001H\u0087\u008a\u0004¢\u0006\u0004\b\f\u0010\r\u001a\u001d\u0010\n\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0001H\u0087\u008a\u0004¢\u0006\u0004\b\u000e\u0010\u000f\u001a)\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014H\u0082\u0080\u0004¢\u0006\u0002\u0010\u0016\u001a'\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0014H\u0082\u0080\u0004¢\u0006\u0002\u0010\u0019\u001a/\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0082\u0080\u0004¢\u0006\u0002\u0010\u001c\u001a\u0016\u0010\u001d\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0006H\u0082\u0080\u0004\u001a\u000e\u0010\u001f\u001a\u00020\u0014*\u00020\u0006H\u0083\u0088\u0004\u001a\u000e\u0010 \u001a\u00020\u0014*\u00020\u0006H\u0083\u0088\u0004\u001a\u001a\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0006H\u0083\u0088\u0004\u001a&\u0010$\u001a\u00020\u0006*\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0082\u0080\u0004\u001a\u0016\u0010&\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0082\u0080\u0004\u001a!\u0010'\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010(\u001a\u00020\u0012H\u0083\u0088\u0004¢\u0006\u0002\u0010)\u001a'\u0010*\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u000e\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010,H\u0082\u0088\u0004¢\u0006\u0004\b-\u0010.\u001a\u0018\u0010/\u001a\u0004\u0018\u00010\u0004*\u00020\u00122\u0006\u00100\u001a\u00020\u0002H\u0082\u0080\u0004\u001a\u0018\u00101\u001a\u0004\u0018\u00010\u0004*\u00020\u00122\u0006\u00100\u001a\u00020\u0002H\u0082\u0080\u0004\u001a\u000e\u0010=\u001a\u00020\u0006*\u00020\u0006H\u0083\u0088\u0004\u001a\u000e\u0010=\u001a\u00020\u0002*\u00020\u0002H\u0083\u0088\u0004\u001a\u0012\u0010J\u001a\u00020\u00062\u0006\u0010K\u001a\u00020\u0006H\u0082\u0080\u0004\u001a\u0012\u0010L\u001a\u00020\u00062\u0006\u0010M\u001a\u00020\u0006H\u0082\u0080\u0004\u001a\u0017\u0010N\u001a\u00020\u00012\u0006\u0010O\u001a\u00020\u0006H\u0082\u0080\u0004¢\u0006\u0002\u0010P\u001a\u0017\u0010Q\u001a\u00020\u00012\u0006\u0010R\u001a\u00020\u0006H\u0082\u0080\u0004¢\u0006\u0002\u0010P\u001a\u001f\u0010S\u001a\u00020\u00012\u0006\u0010T\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u0002H\u0082\u0080\u0004¢\u0006\u0002\u0010V\u001a\u0017\u0010W\u001a\u00020\u00012\u0006\u0010K\u001a\u00020\u0006H\u0082\u0080\u0004¢\u0006\u0002\u0010P\u001a\u0017\u0010X\u001a\u00020\u00012\u0006\u0010M\u001a\u00020\u0006H\u0082\u0080\u0004¢\u0006\u0002\u0010P\"\u001f\u00102\u001a\u00020\b*\u00020\u00048BX\u0082\u0084\b¢\u0006\f\u0012\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0019\u00107\u001a\u00020\u0006*\u00020\u00048BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b8\u00109\"\u0019\u0010:\u001a\u00020\u0002*\u00020\u00048BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b;\u0010<\"\u000f\u0010>\u001a\u00020\u0002X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010?\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010@\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010A\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010B\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010C\u001a\u00020\u0006X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010D\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010E\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010F\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010G\u001a\u00020\u0006X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010H\u001a\u00020\u0012X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010I\u001a\u00020\u0002X\u0082Ô\b¢\u0006\u0002\n\u0000¨\u0006Y"}, d2 = {"toDuration", "Lkotlin/time/Duration;", _UrlKt.FRAGMENT_ENCODE_SET, "unit", "Lkotlin/time/DurationUnit;", "(ILkotlin/time/DurationUnit;)J", _UrlKt.FRAGMENT_ENCODE_SET, "(JLkotlin/time/DurationUnit;)J", _UrlKt.FRAGMENT_ENCODE_SET, "(DLkotlin/time/DurationUnit;)J", "times", "duration", "times-mvk6XK0", "(IJ)J", "times-kIfJnKk", "(DJ)J", "parseDuration", "value", _UrlKt.FRAGMENT_ENCODE_SET, "strictIso", _UrlKt.FRAGMENT_ENCODE_SET, "throwException", "(Ljava/lang/String;ZZ)J", "parseIsoStringFormat", "startIndex", "(Ljava/lang/String;IZ)J", "parseDefaultStringFormat", "hasSign", "(Ljava/lang/String;IZZ)J", "addMillisWithoutOverflow", "other", "isInfiniteMillis", "isFiniteMillis", "sameSign", "a", "b", "parseFractionFallback", "endIndex", "fractionDigitsToNanos", "handleError", "message", "(ZLjava/lang/String;)J", "onInvalid", "block", "Lkotlin/Function0;", "onInvalid-ge6A_vg", "(JLkotlin/jvm/functions/Function0;)Lkotlin/time/Duration;", "defaultDurationUnitByShortNameOrNull", "start", "isoDurationUnitByShortNameOrNull", "fractionMultiplier", "getFractionMultiplier$annotations", "(Lkotlin/time/DurationUnit;)V", "getFractionMultiplier", "(Lkotlin/time/DurationUnit;)D", "fallbackFractionMultiplier", "getFallbackFractionMultiplier", "(Lkotlin/time/DurationUnit;)J", "shortNameLength", "getShortNameLength", "(Lkotlin/time/DurationUnit;)I", "multiplyBy10", "NANOS_IN_MILLIS", "MICROS_IN_MILLIS", "NANOS_IN_MICROS", "MAX_NANOS", "MAX_MILLIS", "MAX_NANOS_IN_MILLIS", "MILLIS_IN_SECOND", "MILLIS_IN_MINUTE", "MILLIS_IN_HOUR", "MILLIS_IN_DAY", "INFINITY_STRING", "FRACTION_LIMIT", "nanosToMillis", "nanos", "millisToNanos", "millis", "durationOfNanos", "normalNanos", "(J)J", "durationOfMillis", "normalMillis", "durationOf", "normalValue", "unitDiscriminator", "(JI)J", "durationOfNanosNormalized", "durationOfMillisNormalized", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDuration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Duration.kt\nkotlin/time/DurationKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Duration.kt\nkotlin/time/LongParser\n+ 4 Strings.kt\nkotlin/text/StringsKt__StringsKt\n+ 5 Duration.kt\nkotlin/time/FractionalParser\n*L\n1#1,1613:1\n1#2:1614\n1284#3,12:1615\n1296#3,15:1630\n1284#3,12:1674\n1296#3,15:1689\n1656#4,3:1627\n1656#4,3:1668\n1656#4,3:1671\n1656#4,3:1686\n1656#4,3:1727\n1342#5,23:1645\n1342#5,23:1704\n*S KotlinDebug\n*F\n+ 1 Duration.kt\nkotlin/time/DurationKt\n*L\n1100#1:1615,12\n1100#1:1630,15\n1179#1:1674,12\n1179#1:1689,15\n1100#1:1627,3\n1109#1:1668,3\n1174#1:1671,3\n1179#1:1686,3\n1191#1:1727,3\n1109#1:1645,23\n1191#1:1704,23\n*E\n"})
/* loaded from: classes.dex */
public final class DurationKt {
    private static final int FRACTION_LIMIT = 15;
    private static final String INFINITY_STRING = "Infinity";
    public static final long MAX_MILLIS = 4611686018427387903L;
    public static final long MAX_NANOS = 4611686018426999999L;
    private static final long MAX_NANOS_IN_MILLIS = 4611686018426L;
    public static final long MICROS_IN_MILLIS = 1000;
    public static final long MILLIS_IN_DAY = 86400000;
    public static final long MILLIS_IN_HOUR = 3600000;
    public static final long MILLIS_IN_MINUTE = 60000;
    public static final long MILLIS_IN_SECOND = 1000;
    public static final long NANOS_IN_MICROS = 1000;
    public static final int NANOS_IN_MILLIS = 1000000;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[DurationUnit.values().length];
            r0[DurationUnit.MICROSECONDS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L12
        L21:
            r0[DurationUnit.NANOSECONDS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L13
        L31:
            r0[DurationUnit.MILLISECONDS.ordinal()] = 3;     // Catch: NoSuchFieldError -> L14
        L23:
            r0[DurationUnit.SECONDS.ordinal()] = 4;     // Catch: NoSuchFieldError -> L15
        L25:
            r0[DurationUnit.MINUTES.ordinal()] = 5;     // Catch: NoSuchFieldError -> L16
        L19:
            r0[DurationUnit.HOURS.ordinal()] = 6;     // Catch: NoSuchFieldError -> L17
        L27:
            r0[DurationUnit.DAYS.ordinal()] = 7;     // Catch: NoSuchFieldError -> L18
        L10:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public static final /* synthetic */ long access$addMillisWithoutOverflow(long r0, long r2) {
        return addMillisWithoutOverflow(r0, r2);
    }

    public static final /* synthetic */ long access$durationOf(long r0, int r2) {
        return durationOf(r0, r2);
    }

    public static final /* synthetic */ long access$durationOfMillis(long r0) {
        return durationOfMillis(r0);
    }

    public static final /* synthetic */ long access$durationOfMillisNormalized(long r0) {
        return durationOfMillisNormalized(r0);
    }

    public static final /* synthetic */ long access$durationOfNanos(long r0) {
        return durationOfNanos(r0);
    }

    public static final /* synthetic */ long access$durationOfNanosNormalized(long r0) {
        return durationOfNanosNormalized(r0);
    }

    public static final /* synthetic */ long access$millisToNanos(long r0) {
        return millisToNanos(r0);
    }

    public static final /* synthetic */ long access$nanosToMillis(long r0) {
        return nanosToMillis(r0);
    }

    public static final /* synthetic */ long access$parseDuration(String r0, boolean r1, boolean r2) {
        return parseDuration(r0, r1, r2);
    }

    private static final long addMillisWithoutOverflow(long r7, long r9) {
        if (r7 == MAX_MILLIS) goto L16;
        if (r7 == (-4611686018427387903L)) goto L16;
        if (r9 != MAX_MILLIS) goto L10;
    L14:
        return r9;
    L10:
        if (r9 == (-4611686018427387903L)) goto L14;
        return RangesKt.coerceIn(r7 + r9, -4611686018427387903L, MAX_MILLIS);
    L16:
        if ((-4611686018427387903L) >= r9) goto L21;
        if (r9 >= MAX_MILLIS) goto L21;
        return r7;
    L21:
        if ((r9 ^ r7) < 0) goto L23;
        return r7;
    L23:
        return Duration.INVALID_RAW_VALUE;
    }

    private static final DurationUnit defaultDurationUnitByShortNameOrNull(String r3, int r4) {
        char r0 = r3.charAt(r4);
        if (r4 >= StringsKt.getLastIndex(r3)) goto L5;
        char r32 = r3.charAt(r4 + 1);
    L7:
        if (r0 == 'd') goto L37;
        if (r0 == 'h') goto L35;
        if (r0 == 's') goto L33;
        if (r0 != 'u') goto L15;
        if (r32 == 's') goto L30;
        return null;
    L30:
        return DurationUnit.MICROSECONDS;
    L15:
        if (r0 != 'm') goto L17;
        if (r32 != 's') goto L27;
        return DurationUnit.MILLISECONDS;
    L27:
        return DurationUnit.MINUTES;
    L17:
        if (r0 == 'n') goto L19;
        return null;
    L19:
        if (r32 == 's') goto L21;
        return null;
    L21:
        return DurationUnit.NANOSECONDS;
    L33:
        return DurationUnit.SECONDS;
    L35:
        return DurationUnit.HOURS;
    L37:
        return DurationUnit.DAYS;
    L5:
        r32 = 0;
        goto L7
    }

    private static final long durationOf(long r3, int r5) {
        return Duration.Companion.m3466fromRawValueUwyO8pc$kotlin_stdlib((r3 << 1) + r5);
    }

    private static final long durationOfMillis(long r3) {
        return Duration.Companion.m3466fromRawValueUwyO8pc$kotlin_stdlib((r3 << 1) + 1);
    }

    private static final long durationOfMillisNormalized(long r6) {
        if ((-4611686018426L) > r6) goto L9;
        if (r6 >= 4611686018427L) goto L9;
        return durationOfNanos(millisToNanos(r6));
    L9:
        return durationOfMillis(RangesKt.coerceIn(r6, -4611686018427387903L, MAX_MILLIS));
    }

    private static final long durationOfNanos(long r2) {
        return Duration.Companion.m3466fromRawValueUwyO8pc$kotlin_stdlib(r2 << 1);
    }

    private static final long durationOfNanosNormalized(long r2) {
        if ((-4611686018426999999L) > r2) goto L9;
        if (r2 >= 4611686018427000000L) goto L9;
        return durationOfNanos(r2);
    L9:
        return durationOfMillis(nanosToMillis(r2));
    }

    private static final long fractionDigitsToNanos(long r2, DurationUnit r4) {
        return MathKt.roundToLong(r2 * getFractionMultiplier(r4));
    }

    private static final long getFallbackFractionMultiplier(DurationUnit r2) {
        int r0 = WhenMappings.$EnumSwitchMapping$0[r2.ordinal()];
        if (r0 != 5) goto L5;
        return 60000000000L;
    L5:
        if (r0 != 6) goto L7;
        return 3600000000000L;
    L7:
        if (r0 != 7) goto L10;
        return 86400000000000L;
    L10:
        DurationKt$$ExternalSyntheticBUOutline1.m("Invalid unit: ", r2, " for fallback fraction multiplier");
        return 0;
    }

    private static final double getFractionMultiplier(DurationUnit r2) {
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
        return 0.0d;
    L6:
        return 0.0864d;
    L8:
        return 0.0036d;
    L10:
        return 6.0E-5d;
    L12:
        return 1.0E-6d;
    L14:
        return 1.0E-9d;
    L16:
        return 1.0E-15d;
    L18:
        return 1.0E-12d;
    }

    private static /* synthetic */ void getFractionMultiplier$annotations(DurationUnit r0) {
    }

    private static final int getShortNameLength(DurationUnit r3) {
        int r32 = WhenMappings.$EnumSwitchMapping$0[r3.ordinal()];
        if (r32 == 1) goto L8;
        if (r32 == 2) goto L8;
        if (r32 == 3) goto L8;
        return 1;
    L8:
        return 2;
    }

    @InlineOnly
    private static final long handleError(boolean r0, String r1) {
        if (r0 == false) goto L4;
        f$$ExternalSyntheticBUOutline1.m(r1);
        return 0;
    L4:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    }

    public static /* synthetic */ long handleError$default(boolean r0, String r1, int r2, Object r3) {
        if ((r2 & 2) == 0) goto L5;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        if (r0 == false) goto L7;
        f$$ExternalSyntheticBUOutline1.m(r1);
        return 0;
    L7:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    }

    @InlineOnly
    private static final boolean isFiniteMillis(long r2) {
        if ((-4611686018427387903L) < r2) goto L5;
        return false;
    L5:
        if (r2 >= MAX_MILLIS) goto L10;
        return true;
    L10:
        return false;
    }

    @InlineOnly
    private static final boolean isInfiniteMillis(long r2) {
        if (r2 != MAX_MILLIS) goto L5;
        return true;
    L5:
        if (r2 == (-4611686018427387903L)) goto L11;
        return false;
    L11:
        return true;
    }

    private static final DurationUnit isoDurationUnitByShortNameOrNull(String r0, int r1) {
        char r02 = r0.charAt(r1);
        if (r02 == 'D') goto L19;
        if (r02 == 'H') goto L17;
        if (r02 == 'M') goto L15;
        if (r02 == 'S') goto L13;
        return null;
    L13:
        return DurationUnit.SECONDS;
    L15:
        return DurationUnit.MINUTES;
    L17:
        return DurationUnit.HOURS;
    L19:
        return DurationUnit.DAYS;
    }

    private static final long millisToNanos(long r2) {
        return r2 * 1000000;
    }

    @InlineOnly
    private static final int multiplyBy10(int r1) {
        return (r1 << 3) + (r1 << 1);
    }

    @InlineOnly
    private static final long multiplyBy10(long r3) {
        return (r3 << 3) + (r3 << 1);
    }

    private static final long nanosToMillis(long r2) {
        return r2 / 1000000;
    }

    /* renamed from: onInvalid-ge6A_vg, reason: not valid java name */
    private static final Duration m3475onInvalidge6A_vg(long r2, Function0<Duration> r4) {
        if (Duration.m3380equalsimpl0(r2, Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib()) == false) goto L7;
        return r4.invoke();
    L7:
        return Duration.m3373boximpl(r2);
    }

    private static final long parseDefaultStringFormat(String r25, int r26, boolean r27, boolean r28) {
        int r1 = r25.length();
        boolean r2 = !r27;
        long r3 = 0;
        if (r27 == true) goto L5;
    L16:
        boolean r6 = r2;
        int r22 = r26;
    L17:
        DurationUnit r7 = null;
        long r8 = 0;
        long r10 = 0;
        boolean r12 = true;
    L18:
        if (r22 >= r1) goto L159;
        if (r12 == true) goto L26;
        if (r6 == false) goto L26;
    L22:
        if (r22 >= r25.length()) goto L26;
        if (r25.charAt(r22) != ' ') goto L26;
        r22 = r22 + 1;
    L26:
        LongParser r122 = LongParser.Companion.getDefault();
        if (LongParser.access$getAllowSign$p(r122) == false) goto L34;
        char r13 = r25.charAt(r22);
        if (r13 != '+') goto L31;
    L33:
        int r132 = r22 + 1;
    L35:
        char r15 = '0';
        if (r132 >= r25.length()) goto L40;
        if (r25.charAt(r132) != '0') goto L40;
        r132 = r132 + 1;
    L40:
        long r16 = r3;
    L42:
        if (r132 >= r25.length()) goto L66;
        char r14 = r25.charAt(r132);
        if ('0' > r14) goto L66;
        if (r14 >= ':') goto L66;
        int r142 = r14 - '0';
        if (r3 > LongParser.access$getOverflowThreshold$p(r122)) goto L55;
        boolean r262 = r6;
        if (r3 != LongParser.access$getOverflowThreshold$p(r122)) goto L53;
        if (r142 > LongParser.access$getLastDigitMax$p(r122)) goto L55;
    L53:
        r3 = r142 + ((r3 << 3) + (r3 << 1));
        r132 = r132 + 1;
        r6 = r262;
    L55:
        if (r132 >= r25.length()) goto L61;
        char r17 = r25.charAt(r132);
        if ('0' > r17) goto L61;
        if (r17 >= ':') goto L61;
        r132 = r132 + 1;
    L61:
        if (r28 == false) goto L63;
        f$$ExternalSyntheticBUOutline1.m(_UrlKt.FRAGMENT_ENCODE_SET);
        return r16;
    L63:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L66:
        boolean r263 = r6;
        if (r132 == r22) goto L153;
        if (r132 == r1) goto L153;
        if (r25.charAt(r132) != '.') goto L72;
        boolean r23 = true;
    L73:
        if (r23 == false) goto L111;
        int r5 = r132 + 1;
        FractionalParser r62 = FractionalParser.INSTANCE;
        int r63 = Math.min(r132 + 7, r25.length());
        int r143 = r5;
        int r20 = 0;
    L75:
        if (r143 >= r63) goto L81;
        char r123 = r25.charAt(r143);
        if (r15 > r123) goto L81;
        if (r123 >= ':') goto L81;
        r20 = ((r20 << 3) + (r20 << 1)) + (r123 - '0');
        r143 = r143 + 1;
        r15 = '0';
    L81:
        int r124 = r20;
        int r152 = 0;
    L82:
        if (r152 >= (6 - (r143 - r5))) goto L84;
        r124 = (r124 << 3) + (r124 << 1);
        r152 = r152 + 1;
        goto L82
    L84:
        int r64 = Math.min(r143 + 9, r25.length());
        int r153 = r143;
        int r202 = 0;
    L85:
        boolean r222 = r23;
        if (r153 >= r64) goto L92;
        char r24 = r25.charAt(r153);
        int r232 = r64;
        if ('0' > r24) goto L92;
        if (r24 >= ':') goto L92;
        r202 = ((r202 << 3) + (r202 << 1)) + (r24 - '0');
        r153 = r153 + 1;
        r23 = r222;
        r64 = r232;
    L92:
        int r29 = 9 - (r153 - r143);
        int r65 = r202;
        int r144 = 0;
    L93:
        if (r144 >= r29) goto L96;
        r65 = (r65 << 3) + (r65 << 1);
        r144 = r144 + 1;
    L96:
        if (r153 >= r25.length()) goto L102;
        char r210 = r25.charAt(r153);
        if ('0' > r210) goto L102;
        if (r210 >= ':') goto L102;
        r153 = r153 + 1;
    L102:
        if (r153 == r5) goto L106;
        if (r153 == r1) goto L106;
        int r211 = r132;
        long r125 = (r124 * 1000000000) + r65;
    L112:
        DurationUnit r52 = defaultDurationUnitByShortNameOrNull(r25, r153);
        if (r52 == null) goto L114;
        if (r7 != null) goto L122;
    L128:
        int r66 = WhenMappings.$EnumSwitchMapping$0[r52.ordinal()];
        if (r66 != 1) goto L131;
        r8 = r8 + (r3 / 1000);
        if (r8 > MAX_NANOS_IN_MILLIS) goto L137;
        r10 = (r3 % 1000) * 1000;
    L137:
        int r32 = getShortNameLength(r52) + r153;
        if (r222 == false) goto L152;
        if (r32 < r1) goto L140;
        if (r52.compareTo(DurationUnit.MINUTES) >= 0) goto L148;
    L150:
        long r126 = fractionDigitsToNanos(r125, r52);
    L151:
        r10 = r10 + r126;
        goto L152
    L148:
        if ((r32 - r211) <= 15) goto L150;
        r126 = parseFractionFallback(r25, r211, r32 - getShortNameLength(r52), r52);
        goto L151
    L140:
        if (r28 == false) goto L142;
        f$$ExternalSyntheticBUOutline1.m("Fractional component must be last");
        return r16;
    L142:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L152:
        r6 = r263;
        r22 = r32;
        r7 = r52;
        r3 = r16;
        r12 = false;
        goto L18
    L131:
        if (r66 == 2) goto L133;
        r8 = addMillisWithoutOverflow(r8, DurationUnitKt__DurationUnitKt.convertDurationUnitToMilliseconds(r3, r52));
        goto L137
    L133:
        r8 = r8 + (r3 / 1000000);
        r10 = r10 + (r3 % 1000000);
        goto L137
    L122:
        if (r7.compareTo(r52) > 0) goto L128;
        if (r28 == false) goto L125;
        f$$ExternalSyntheticBUOutline1.m("Unexpected order of duration components");
        return r16;
    L125:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L114:
        String r0 = "Unknown duration unit short name: " + r25.charAt(r153);
        if (r28 == false) goto L117;
        f$$ExternalSyntheticBUOutline1.m(r0);
        return r16;
    L117:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L106:
        if (r28 == false) goto L108;
        f$$ExternalSyntheticBUOutline1.m(_UrlKt.FRAGMENT_ENCODE_SET);
        return r16;
    L108:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L111:
        r222 = r23;
        r153 = r132;
        r211 = -1;
        r125 = r16;
        goto L112
    L72:
        r23 = false;
    L153:
        if (r28 == false) goto L155;
        f$$ExternalSyntheticBUOutline1.m(_UrlKt.FRAGMENT_ENCODE_SET);
        return r16;
    L155:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L31:
        if (r13 == '-') goto L33;
    L34:
        r132 = r22;
        goto L35
    L159:
        return Duration.m3404plusLRDsOJo(toDuration(r8, DurationUnit.MILLISECONDS), toDuration(r10, DurationUnit.NANOSECONDS));
    L5:
        if (r25.charAt(r26) != '(') goto L16;
        if (r25.charAt(r1 - 1) != ')') goto L16;
        r22 = r26 + 1;
        r1 = r1 - 1;
        if (r22 != r1) goto L15;
        if (r28 == false) goto L12;
        f$$ExternalSyntheticBUOutline1.m("No components");
        return 0;
    L12:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L15:
        r6 = true;
        goto L17
    }

    private static final long parseDuration(String r12, boolean r13, boolean r14) {
        if (r12.length() != 0) goto L9;
        if (r14 == false) goto L6;
        f$$ExternalSyntheticBUOutline1.m("The string is empty");
        return 0;
    L6:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L9:
        boolean r0 = false;
        char r3 = r12.charAt(0);
        if (r3 != '+') goto L12;
        int r32 = 0;
        int r7 = 1;
    L17:
        if (r7 <= 0) goto L20;
        r0 = true;
    L20:
        if (r12.length() > r7) goto L27;
        if (r14 == false) goto L23;
        f$$ExternalSyntheticBUOutline1.m("No components");
        return 0;
    L23:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L27:
        if (r12.charAt(r7) != 'P') goto L29;
        long r122 = parseIsoStringFormat(r12, r7 + 1, r14);
    L39:
        if (r32 != 0) goto L41;
        return r122;
    L41:
        if (Duration.m3380equalsimpl0(r122, Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib()) == false) goto L43;
        return r122;
    L43:
        return Duration.m3419unaryMinusUwyO8pc(r122);
    L29:
        if (r13 == false) goto L36;
        if (r14 == false) goto L32;
        f$$ExternalSyntheticBUOutline1.m(_UrlKt.FRAGMENT_ENCODE_SET);
        return 0;
    L32:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L36:
        if (StringsKt.regionMatches(r12, r7, INFINITY_STRING, 0, Math.max(r12.length() - r7, 8), true) == false) goto L38;
        r122 = Duration.Companion.m3467getINFINITEUwyO8pc();
        goto L39
    L38:
        r122 = parseDefaultStringFormat(r12, r7, r0, r14);
        goto L39
    L12:
        if (r3 == '-') goto L15;
        r32 = 0;
    L14:
        r7 = r32;
        goto L17
    L15:
        r32 = 1;
        goto L14
    }

    public static /* synthetic */ long parseDuration$default(String r0, boolean r1, boolean r2, int r3, Object r4) {
        if ((r3 & 4) == 0) goto L6;
        r2 = true;
    L6:
        return parseDuration(r0, r1, r2);
    }

    private static final long parseFractionFallback(String r0, int r1, int r2, DurationUnit r3) {
        return MathKt.roundToLong(Double.parseDouble(r0.substring(r1, r2)) * getFallbackFractionMultiplier(r3));
    }

    private static final long parseIsoStringFormat(String r25, int r26, boolean r27) {
        long r2 = 0;
        int r5 = r26;
        if (r5 != r25.length()) goto L9;
        if (r27 == false) goto L6;
        f$$ExternalSyntheticBUOutline1.m(_UrlKt.FRAGMENT_ENCODE_SET);
        return 0;
    L6:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L9:
        DurationUnit r6 = null;
        long r7 = 0;
        long r9 = 0;
        boolean r11 = false;
    L11:
        if (r5 >= r25.length()) goto L181;
        char r12 = r25.charAt(r5);
        if (r12 == 'T') goto L14;
        LongParser r13 = LongParser.Companion.getIso();
        long r16 = r2;
        if (LongParser.access$getAllowSign$p(r13) == false) goto L33;
        char r3 = r25.charAt(r5);
        if (r3 == '+') goto L31;
        if (r3 != '-') goto L33;
        int r32 = r5 + 1;
        int r15 = -1;
    L35:
        char r1 = '0';
        if (r32 >= r25.length()) goto L40;
        if (r25.charAt(r32) != '0') goto L40;
        r32 = r32 + 1;
    L40:
        long r19 = r16;
    L42:
        if (r32 >= r25.length()) goto L82;
        char r14 = r25.charAt(r32);
        if (r1 > r14) goto L82;
        if (r14 >= ':') goto L82;
        int r142 = r14 - '0';
        if (r19 > LongParser.access$getOverflowThreshold$p(r13)) goto L55;
        if (r19 != LongParser.access$getOverflowThreshold$p(r13)) goto L53;
        int r21 = r32;
        if (r142 <= LongParser.access$getLastDigitMax$p(r13)) goto L54;
    L56:
        int r33 = r21;
    L58:
        if (r33 >= r25.length()) goto L65;
        char r17 = r25.charAt(r33);
        if ('0' > r17) goto L65;
        if (r17 >= ':') goto L65;
        r33 = r33 + 1;
    L65:
        if (r33 == r25.length()) goto L77;
        if (r12 != '+') goto L69;
    L71:
        int r143 = 1;
    L73:
        if (r33 == (r5 + r143)) goto L77;
        r19 = LongParser.access$getOverflowLimit$p(r13);
    L76:
        long r18 = r19;
        if (r25.charAt(r33) != '.') goto L135;
        int r52 = r33 + 1;
        FractionalParser r92 = FractionalParser.INSTANCE;
        int r34 = Math.min(r33 + 7, r25.length());
        int r93 = r52;
        int r10 = 0;
    L96:
        if (r93 >= r34) goto L102;
        char r122 = r25.charAt(r93);
        if ('0' > r122) goto L102;
        if (r122 >= ':') goto L102;
        r10 = ((r10 << 3) + (r10 << 1)) + (r122 - '0');
        r93 = r93 + 1;
    L102:
        int r123 = 0;
    L103:
        if (r123 >= (6 - (r93 - r52))) goto L105;
        r10 = (r10 << 1) + (r10 << 3);
        r123 = r123 + 1;
        goto L103
    L105:
        int r35 = Math.min(r93 + 9, r25.length());
        int r124 = r93;
        int r132 = 0;
    L106:
        if (r124 >= r35) goto L112;
        char r144 = r25.charAt(r124);
        int r262 = r35;
        if ('0' > r144) goto L112;
        if (r144 >= ':') goto L112;
        r132 = ((r132 << 3) + (r132 << 1)) + (r144 - '0');
        r124 = r124 + 1;
        r35 = r262;
    L112:
        int r36 = 9 - (r124 - r93);
        int r94 = 0;
    L113:
        if (r94 >= r36) goto L115;
        r132 = (r132 << 1) + (r132 << 3);
        r94 = r94 + 1;
        goto L113
    L115:
        r33 = r124;
    L117:
        if (r33 >= r25.length()) goto L123;
        char r95 = r25.charAt(r33);
        if ('0' > r95) goto L123;
        if (r95 >= ':') goto L123;
        r33 = r33 + 1;
    L123:
        if (r33 == r52) goto L130;
        if (r33 == r25.length()) goto L130;
        if (r25.charAt(r33) != 'S') goto L130;
        r9 = r15 * fractionDigitsToNanos((r10 * 1000000000) + r132, DurationUnit.SECONDS);
    L130:
        if (r27 == false) goto L132;
        f$$ExternalSyntheticBUOutline1.m(_UrlKt.FRAGMENT_ENCODE_SET);
        return r16;
    L132:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L135:
        DurationUnit r53 = isoDurationUnitByShortNameOrNull(r25, r33);
        if (r53 == null) goto L137;
        if (r6 == null) goto L152;
        if (r6.compareTo(r53) > 0) goto L152;
        if (r27 == false) goto L148;
        f$$ExternalSyntheticBUOutline1.m("Unexpected order of duration components");
        return r16;
    L148:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L152:
        if (r53 != DurationUnit.DAYS) goto L160;
        if (r11 == true) goto L154;
        r7 = r15 * DurationUnitKt__DurationUnitKt.convertDurationUnitToMilliseconds(r18, r53);
    L174:
        r6 = r53;
        r2 = r16;
        r5 = r33 + 1;
        goto L11
    L154:
        if (r27 == false) goto L156;
        f$$ExternalSyntheticBUOutline1.m(_UrlKt.FRAGMENT_ENCODE_SET);
        return r16;
    L156:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L160:
        if (r11 == false) goto L161;
        long r110 = addMillisWithoutOverflow(r7, r15 * DurationUnitKt__DurationUnitKt.convertDurationUnitToMilliseconds(r18, r53));
        if (r110 == Duration.INVALID_RAW_VALUE) goto L168;
        r7 = r110;
        goto L174
    L168:
        if (r27 == false) goto L170;
        f$$ExternalSyntheticBUOutline1.m(_UrlKt.FRAGMENT_ENCODE_SET);
        return r16;
    L170:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L161:
        if (r27 == false) goto L163;
        f$$ExternalSyntheticBUOutline1.m(_UrlKt.FRAGMENT_ENCODE_SET);
        return r16;
    L163:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L137:
        String r0 = "Unknown duration unit short name: " + r25.charAt(r33);
        if (r27 == false) goto L140;
        f$$ExternalSyntheticBUOutline1.m(r0);
        return r16;
    L140:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L69:
        if (r12 == '-') goto L71;
        r143 = 0;
    L77:
        if (r27 == false) goto L79;
        f$$ExternalSyntheticBUOutline1.m(_UrlKt.FRAGMENT_ENCODE_SET);
        return r16;
    L79:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L54:
        r19 = ((r19 << 3) + (r19 << 1)) + r142;
        r32 = r21 + 1;
        r1 = '0';
        goto L42
    L53:
        r21 = r32;
        goto L54
    L55:
        r21 = r32;
    L82:
        r33 = r32;
        if (r33 == r25.length()) goto L175;
        if (r12 != '+') goto L87;
    L89:
        int r145 = 1;
    L91:
        if (r33 != (r5 + r145)) goto L76;
    L87:
        if (r12 == '-') goto L89;
        r145 = 0;
    L175:
        if (r27 == false) goto L177;
        f$$ExternalSyntheticBUOutline1.m(_UrlKt.FRAGMENT_ENCODE_SET);
        return r16;
    L177:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L31:
        r32 = r5 + 1;
    L32:
        r15 = 1;
    L33:
        r32 = r5;
        goto L32
    L14:
        if (r11 == true) goto L19;
        r5 = r5 + 1;
        if (r5 == r25.length()) goto L19;
        r11 = true;
    L19:
        if (r27 == false) goto L21;
        f$$ExternalSyntheticBUOutline1.m(_UrlKt.FRAGMENT_ENCODE_SET);
        return r2;
    L21:
        return Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib();
    L181:
        return Duration.m3404plusLRDsOJo(toDuration(r7, DurationUnit.MILLISECONDS), toDuration(r9, DurationUnit.NANOSECONDS));
    }

    @InlineOnly
    private static final boolean sameSign(long r0, long r2) {
        if ((r0 ^ r2) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    /* renamed from: times-kIfJnKk, reason: not valid java name */
    private static final long m3476timeskIfJnKk(double r0, long r2) {
        return Duration.m3405timesUwyO8pc(r2, r0);
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    /* renamed from: times-mvk6XK0, reason: not valid java name */
    private static final long m3477timesmvk6XK0(int r0, long r1) {
        return Duration.m3406timesUwyO8pc(r1, r0);
    }

    @SinceKotlin(version = "1.6")
    public static final long toDuration(double r4, DurationUnit r6) {
        double r0 = DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(r4, r6, DurationUnit.NANOSECONDS);
        if (Double.isNaN(r0) == true) goto L12;
        long r02 = MathKt.roundToLong(r0);
        if ((-4611686018426999999L) > r02) goto L11;
        if (r02 >= 4611686018427000000L) goto L11;
        return durationOfNanos(r02);
    L11:
        return durationOfMillisNormalized(MathKt.roundToLong(DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(r4, r6, DurationUnit.MILLISECONDS)));
    L12:
        f$$ExternalSyntheticBUOutline1.m("Duration value cannot be NaN.");
        return 0;
    }

    @SinceKotlin(version = "1.6")
    public static final long toDuration(int r2, DurationUnit r3) {
        if (r3.compareTo(DurationUnit.SECONDS) > 0) goto L7;
        return durationOfNanos(DurationUnitKt__DurationUnitJvmKt.convertDurationUnitOverflow(r2, r3, DurationUnit.NANOSECONDS));
    L7:
        return toDuration(r2, r3);
    }

    @SinceKotlin(version = "1.6")
    public static final long toDuration(long r6, DurationUnit r8) {
        DurationUnit r0 = DurationUnit.NANOSECONDS;
        long r1 = DurationUnitKt__DurationUnitJvmKt.convertDurationUnitOverflow(MAX_NANOS, r0, r8);
        if ((-r1) <= r6) goto L5;
    L8:
        DurationUnit r02 = DurationUnit.MILLISECONDS;
        if (r8.compareTo(r02) < 0) goto L13;
        return durationOfMillis(MathKt.getSign(r6) * DurationUnitKt__DurationUnitKt.convertDurationUnitToMilliseconds(Math.abs(RangesKt.coerceAtLeast(r6, -9223372036854775807L)), r8));
    L13:
        return durationOfMillis(RangesKt.coerceIn(DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(r6, r8, r02), -4611686018427387903L, MAX_MILLIS));
    L5:
        if (r6 > r1) goto L8;
        return durationOfNanos(DurationUnitKt__DurationUnitJvmKt.convertDurationUnitOverflow(r6, r8, r0));
    }
}
