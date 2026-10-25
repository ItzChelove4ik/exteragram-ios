package j$.time.temporal;

import j$.time.Duration;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum h extends Enum implements TemporalUnit {
    public static final h QUARTER_YEARS = null;
    public static final h WEEK_BASED_YEARS = null;

    /* renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ h[] f451c = null;

    /* renamed from: a, reason: collision with root package name */
    public final String f452a;

    /* renamed from: b, reason: collision with root package name */
    public final Duration f453b;

    static {
        h r0 = new h("WEEK_BASED_YEARS", 0, "WeekBasedYears", Duration.ofSeconds(31556952));
        WEEK_BASED_YEARS = r0;
        h r1 = new h("QUARTER_YEARS", 1, "QuarterYears", Duration.ofSeconds(7889238));
        QUARTER_YEARS = r1;
        f451c = new h[]{r0, r1};
    }

    h(String r1, int r2, String r3, Duration r4) {
        this.f452a = r3;
        this.f453b = r4;
    }

    public static h valueOf(String r1) {
        return (h) Enum.valueOf(h.class, r1);
    }

    public static h[] values() {
        return (h[]) f451c.clone();
    }

    @Override // j$.time.temporal.TemporalUnit
    public final long between(Temporal r3, Temporal r4) {
        if (r3.getClass() != r4.getClass()) goto L5;
        int r2 = b.f448a[ordinal()];
        if (r2 != 1) goto L9;
        g r22 = i.f456c;
        return Math.subtractExact(r4.k(r22), r3.k(r22));
    L9:
        if (r2 != 2) goto L13;
        return r3.n(r4, ChronoUnit.MONTHS) / 3;
    L13:
        throw new IllegalStateException("Unreachable");
    L5:
        return r3.n(r4, this);
    }

    @Override // j$.time.temporal.TemporalUnit
    public final Duration getDuration() {
        return this.f453b;
    }

    @Override // j$.time.temporal.TemporalUnit
    public final boolean isDurationEstimated() {
        return true;
    }

    @Override // j$.time.temporal.TemporalUnit
    public final Temporal s(Temporal r5, long r6) {
        int r4 = b.f448a[ordinal()];
        if (r4 == 1) goto L11;
        if (r4 != 2) goto L9;
        return r5.b(r6 / 4, ChronoUnit.YEARS).b((r6 % 4) * 3, ChronoUnit.MONTHS);
    L9:
        throw new IllegalStateException("Unreachable");
    L11:
        return r5.a(Math.addExact(r5.g(r4), r6), i.f456c);
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f452a;
    }
}
