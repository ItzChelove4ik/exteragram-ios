package j$.time.temporal;

import j$.time.Duration;
import kotlin.jvm.internal.LongCompanionObject;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum ChronoUnit extends Enum<ChronoUnit> implements TemporalUnit {
    public static final ChronoUnit CENTURIES = null;
    public static final ChronoUnit DAYS = null;
    public static final ChronoUnit DECADES = null;
    public static final ChronoUnit ERAS = null;
    public static final ChronoUnit FOREVER = null;
    public static final ChronoUnit HALF_DAYS = null;
    public static final ChronoUnit HOURS = null;
    public static final ChronoUnit MICROS = null;
    public static final ChronoUnit MILLENNIA = null;
    public static final ChronoUnit MILLIS = null;
    public static final ChronoUnit MINUTES = null;
    public static final ChronoUnit MONTHS = null;
    public static final ChronoUnit NANOS = null;
    public static final ChronoUnit SECONDS = null;
    public static final ChronoUnit WEEKS = null;
    public static final ChronoUnit YEARS = null;

    /* renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ ChronoUnit[] f442c = null;

    /* renamed from: a, reason: collision with root package name */
    public final String f443a;

    /* renamed from: b, reason: collision with root package name */
    public final Duration f444b;

    static {
        ChronoUnit r1 = new ChronoUnit("NANOS", 0, "Nanos", Duration.ofNanos(1));
        NANOS = r1;
        ChronoUnit r2 = new ChronoUnit("MICROS", 1, "Micros", Duration.ofNanos(1000));
        MICROS = r2;
        ChronoUnit r3 = new ChronoUnit("MILLIS", 2, "Millis", Duration.ofNanos(1000000));
        MILLIS = r3;
        ChronoUnit r4 = new ChronoUnit("SECONDS", 3, "Seconds", Duration.ofSeconds(1));
        SECONDS = r4;
        ChronoUnit r5 = new ChronoUnit("MINUTES", 4, "Minutes", Duration.ofSeconds(60));
        MINUTES = r5;
        ChronoUnit r6 = new ChronoUnit("HOURS", 5, "Hours", Duration.ofSeconds(3600));
        HOURS = r6;
        ChronoUnit r7 = new ChronoUnit("HALF_DAYS", 6, "HalfDays", Duration.ofSeconds(43200));
        HALF_DAYS = r7;
        ChronoUnit r8 = new ChronoUnit("DAYS", 7, "Days", Duration.ofSeconds(86400));
        DAYS = r8;
        ChronoUnit r9 = new ChronoUnit("WEEKS", 8, "Weeks", Duration.ofSeconds(604800));
        WEEKS = r9;
        ChronoUnit r10 = new ChronoUnit("MONTHS", 9, "Months", Duration.ofSeconds(2629746));
        MONTHS = r10;
        ChronoUnit r11 = new ChronoUnit("YEARS", 10, "Years", Duration.ofSeconds(31556952));
        YEARS = r11;
        ChronoUnit r12 = new ChronoUnit("DECADES", 11, "Decades", Duration.ofSeconds(315569520));
        DECADES = r12;
        ChronoUnit r13 = new ChronoUnit("CENTURIES", 12, "Centuries", Duration.ofSeconds(3155695200L));
        CENTURIES = r13;
        ChronoUnit r14 = new ChronoUnit("MILLENNIA", 13, "Millennia", Duration.ofSeconds(31556952000L));
        MILLENNIA = r14;
        ChronoUnit r15 = new ChronoUnit("ERAS", 14, "Eras", Duration.ofSeconds(31556952000000000L));
        ERAS = r15;
        ChronoUnit r0 = new ChronoUnit("FOREVER", 15, "Forever", Duration.ofSeconds(LongCompanionObject.MAX_VALUE, 999999999));
        FOREVER = r0;
        f442c = new ChronoUnit[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r0};
    }

    ChronoUnit(String r1, int r2, String r3, Duration r4) {
        this.f443a = r3;
        this.f444b = r4;
    }

    public static ChronoUnit valueOf(String r1) {
        return (ChronoUnit) Enum.valueOf(ChronoUnit.class, r1);
    }

    public static ChronoUnit[] values() {
        return (ChronoUnit[]) f442c.clone();
    }

    @Override // j$.time.temporal.TemporalUnit
    public long between(Temporal r1, Temporal r2) {
        return r1.n(r2, this);
    }

    @Override // j$.time.temporal.TemporalUnit
    public final Duration getDuration() {
        return this.f444b;
    }

    @Override // j$.time.temporal.TemporalUnit
    public final boolean isDurationEstimated() {
        if (compareTo(DAYS) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // j$.time.temporal.TemporalUnit
    public final Temporal s(Temporal r1, long r2) {
        return r1.b(r2, this);
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f443a;
    }
}
