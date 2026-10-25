package j$.time.temporal;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum d extends g {
    public d() {
        super("QUARTER_OF_YEAR", 1);
    }

    @Override // j$.time.temporal.p
    public final s D(l r1) {
        if (s(r1) == false) goto L7;
        return K();
    L7:
        throw new r("Unsupported field: QuarterOfYear");
    }

    @Override // j$.time.temporal.p
    public final s K() {
        return s.f(1, 4);
    }

    @Override // j$.time.temporal.p
    public final long S(l r3) {
        if (s(r3) == false) goto L7;
        return (r3.k(a.MONTH_OF_YEAR) + 2) / 3;
    L7:
        throw new r("Unsupported field: QuarterOfYear");
    }

    @Override // j$.time.temporal.p
    public final Temporal Y(Temporal r5, long r6) {
        long r0 = S(r5);
        K().b(r6, this);
        a r4 = a.MONTH_OF_YEAR;
        long r62 = (r6 - r0) * 3;
        return r5.a(r62 + r5.k(r4), r4);
    }

    @Override // j$.time.temporal.p
    public final boolean s(l r1) {
        if (r1.i(a.MONTH_OF_YEAR) == false) goto L8;
        g r0 = i.f454a;
        if (j$.time.chrono.k.r(r1).equals(j$.time.chrono.r.f353c) == false) goto L10;
        return true;
    L10:
        return false;
    L8:
        return false;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "QuarterOfYear";
    }
}
