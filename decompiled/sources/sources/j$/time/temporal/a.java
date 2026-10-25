package j$.time.temporal;

import kotlin.jvm.internal.LongCompanionObject;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum a extends Enum implements p {
    public static final a ALIGNED_DAY_OF_WEEK_IN_MONTH = null;
    public static final a ALIGNED_DAY_OF_WEEK_IN_YEAR = null;
    public static final a ALIGNED_WEEK_OF_MONTH = null;
    public static final a ALIGNED_WEEK_OF_YEAR = null;
    public static final a AMPM_OF_DAY = null;
    public static final a CLOCK_HOUR_OF_AMPM = null;
    public static final a CLOCK_HOUR_OF_DAY = null;
    public static final a DAY_OF_MONTH = null;
    public static final a DAY_OF_WEEK = null;
    public static final a DAY_OF_YEAR = null;
    public static final a EPOCH_DAY = null;
    public static final a ERA = null;
    public static final a HOUR_OF_AMPM = null;
    public static final a HOUR_OF_DAY = null;
    public static final a INSTANT_SECONDS = null;
    public static final a MICRO_OF_DAY = null;
    public static final a MICRO_OF_SECOND = null;
    public static final a MILLI_OF_DAY = null;
    public static final a MILLI_OF_SECOND = null;
    public static final a MINUTE_OF_DAY = null;
    public static final a MINUTE_OF_HOUR = null;
    public static final a MONTH_OF_YEAR = null;
    public static final a NANO_OF_DAY = null;
    public static final a NANO_OF_SECOND = null;
    public static final a OFFSET_SECONDS = null;
    public static final a PROLEPTIC_MONTH = null;
    public static final a SECOND_OF_DAY = null;
    public static final a SECOND_OF_MINUTE = null;
    public static final a YEAR = null;
    public static final a YEAR_OF_ERA = null;

    /* renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ a[] f445c = null;

    /* renamed from: a, reason: collision with root package name */
    public final String f446a;

    /* renamed from: b, reason: collision with root package name */
    public final s f447b;

    static {
        ChronoUnit r4 = ChronoUnit.NANOS;
        ChronoUnit r9 = ChronoUnit.SECONDS;
        a r0 = new a("NANO_OF_SECOND", 0, "NanoOfSecond", r4, r9, s.f(0, 999999999));
        NANO_OF_SECOND = r0;
        ChronoUnit r19 = ChronoUnit.DAYS;
        a r1 = new a("NANO_OF_DAY", 1, "NanoOfDay", r4, r19, s.f(0, 86399999999999L));
        NANO_OF_DAY = r1;
        ChronoUnit r92 = ChronoUnit.MICROS;
        a r3 = new a("MICRO_OF_SECOND", 2, "MicroOfSecond", r92, r9, s.f(0, 999999));
        MICRO_OF_SECOND = r3;
        a r15 = new a("MICRO_OF_DAY", 3, "MicroOfDay", r92, r19, s.f(0, 86399999999L));
        MICRO_OF_DAY = r15;
        ChronoUnit r93 = ChronoUnit.MILLIS;
        a r5 = new a("MILLI_OF_SECOND", 4, "MilliOfSecond", r93, r9, s.f(0, 999));
        MILLI_OF_SECOND = r5;
        a r6 = new a("MILLI_OF_DAY", 5, "MilliOfDay", r93, r19, s.f(0, 86399999));
        MILLI_OF_DAY = r6;
        ChronoUnit r10 = ChronoUnit.MINUTES;
        a r52 = new a("SECOND_OF_MINUTE", 6, "SecondOfMinute", r9, r10, s.f(0, 59), 0);
        SECOND_OF_MINUTE = r52;
        a r53 = new a("SECOND_OF_DAY", 7, "SecondOfDay", r9, r19, s.f(0, 86399));
        SECOND_OF_DAY = r53;
        ChronoUnit r39 = ChronoUnit.HOURS;
        a r26 = new a("MINUTE_OF_HOUR", 8, "MinuteOfHour", r10, r39, s.f(0, 59), 0);
        MINUTE_OF_HOUR = r26;
        a r102 = new a("MINUTE_OF_DAY", 9, "MinuteOfDay", r10, r19, s.f(0, 1439));
        MINUTE_OF_DAY = r102;
        ChronoUnit r40 = ChronoUnit.HALF_DAYS;
        a r35 = new a("HOUR_OF_AMPM", 10, "HourOfAmPm", r39, r40, s.f(0, 11));
        HOUR_OF_AMPM = r35;
        a r352 = new a("CLOCK_HOUR_OF_AMPM", 11, "ClockHourOfAmPm", r39, r40, s.f(1, 12));
        CLOCK_HOUR_OF_AMPM = r352;
        a r152 = new a("HOUR_OF_DAY", 12, "HourOfDay", r39, r19, s.f(0, 23), 0);
        HOUR_OF_DAY = r152;
        a r153 = new a("CLOCK_HOUR_OF_DAY", 13, "ClockHourOfDay", r39, r19, s.f(1, 24));
        CLOCK_HOUR_OF_DAY = r153;
        a r154 = new a("AMPM_OF_DAY", 14, "AmPmOfDay", r40, r19, s.f(0, 1), 0);
        AMPM_OF_DAY = r154;
        ChronoUnit r20 = ChronoUnit.WEEKS;
        a r16 = new a("DAY_OF_WEEK", 15, "DayOfWeek", r19, r20, s.f(1, 7), 0);
        DAY_OF_WEEK = r16;
        a r17 = new a("ALIGNED_DAY_OF_WEEK_IN_MONTH", 16, "AlignedDayOfWeekInMonth", r19, r20, s.f(1, 7));
        ALIGNED_DAY_OF_WEEK_IN_MONTH = r17;
        a r18 = new a("ALIGNED_DAY_OF_WEEK_IN_YEAR", 17, "AlignedDayOfWeekInYear", r19, r20, s.f(1, 7));
        ALIGNED_DAY_OF_WEEK_IN_YEAR = r18;
        ChronoUnit r45 = ChronoUnit.MONTHS;
        a r155 = new a("DAY_OF_MONTH", 18, "DayOfMonth", r19, r45, s.g(28, 31), 0);
        DAY_OF_MONTH = r155;
        ChronoUnit r532 = ChronoUnit.YEARS;
        a r202 = new a("DAY_OF_YEAR", 19, "DayOfYear", r19, r532, s.g(365, 366));
        DAY_OF_YEAR = r202;
        ChronoUnit r54 = ChronoUnit.FOREVER;
        a r21 = new a("EPOCH_DAY", 20, "EpochDay", r19, r54, s.f(-365243219162L, 365241780471L));
        EPOCH_DAY = r21;
        a r36 = new a("ALIGNED_WEEK_OF_MONTH", 21, "AlignedWeekOfMonth", r20, r45, s.g(4, 5));
        ALIGNED_WEEK_OF_MONTH = r36;
        a r362 = new a("ALIGNED_WEEK_OF_YEAR", 22, "AlignedWeekOfYear", r20, r532, s.f(1, 53));
        ALIGNED_WEEK_OF_YEAR = r362;
        a r41 = new a("MONTH_OF_YEAR", 23, "MonthOfYear", r45, r532, s.f(1, 12), 0);
        MONTH_OF_YEAR = r41;
        a r412 = new a("PROLEPTIC_MONTH", 24, "ProlepticMonth", r45, r54, s.f(-11999999988L, 11999999999L));
        PROLEPTIC_MONTH = r412;
        a r49 = new a("YEAR_OF_ERA", 25, "YearOfEra", r532, r54, s.g(999999999, 1000000000));
        YEAR_OF_ERA = r49;
        a r492 = new a("YEAR", 26, "Year", r532, r54, s.f(-999999999, 999999999), 0);
        YEAR = r492;
        a r542 = new a("ERA", 27, "Era", ChronoUnit.ERAS, r54, s.f(0, 1), 0);
        ERA = r542;
        a r55 = new a("INSTANT_SECONDS", 28, "InstantSeconds", r9, r54, s.f(Long.MIN_VALUE, LongCompanionObject.MAX_VALUE));
        INSTANT_SECONDS = r55;
        a r56 = new a("OFFSET_SECONDS", 29, "OffsetSeconds", r9, r54, s.f(-64800, 64800));
        OFFSET_SECONDS = r56;
        f445c = new a[]{r0, r1, r3, r15, r5, r6, r52, r53, r26, r102, r35, r352, r152, r153, r154, r16, r17, r18, r155, r202, r21, r36, r362, r41, r412, r49, r492, r542, r55, r56};
    }

    a(String r1, int r2, String r3, ChronoUnit r4, ChronoUnit r5, s r6) {
        this.f446a = r3;
        this.f447b = r6;
    }

    a(String r1, int r2, String r3, ChronoUnit r4, ChronoUnit r5, s r6, int r7) {
        this.f446a = r3;
        this.f447b = r6;
    }

    public static a valueOf(String r1) {
        return (a) Enum.valueOf(a.class, r1);
    }

    public static a[] values() {
        return (a[]) f445c.clone();
    }

    @Override // j$.time.temporal.p
    public final s D(l r1) {
        return r1.l(this);
    }

    @Override // j$.time.temporal.p
    public final s K() {
        return this.f447b;
    }

    @Override // j$.time.temporal.p
    public final long S(l r1) {
        return r1.k(this);
    }

    @Override // j$.time.temporal.p
    public final Temporal Y(Temporal r1, long r2) {
        return r1.a(r2, this);
    }

    public final void a0(long r2) {
        this.f447b.b(r2, this);
    }

    public final boolean b0() {
        if (ordinal() >= DAY_OF_WEEK.ordinal()) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // j$.time.temporal.p
    public final boolean isDateBased() {
        if (ordinal() >= DAY_OF_WEEK.ordinal()) goto L5;
        return false;
    L5:
        if (ordinal() > ERA.ordinal()) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // j$.time.temporal.p
    public final boolean s(l r1) {
        return r1.i(this);
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f446a;
    }
}
