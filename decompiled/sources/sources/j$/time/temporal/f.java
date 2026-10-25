package j$.time.temporal;

import j$.time.LocalDate;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum f extends g {
    public f() {
        super("WEEK_BASED_YEAR", 3);
    }

    @Override // j$.time.temporal.p
    public final s D(l r1) {
        if (s(r1) == false) goto L7;
        return a.YEAR.f447b;
    L7:
        throw new r("Unsupported field: WeekBasedYear");
    }

    @Override // j$.time.temporal.p
    public final s K() {
        return a.YEAR.f447b;
    }

    @Override // j$.time.temporal.p
    public final long S(l r1) {
        if (s(r1) == false) goto L7;
        return g.b0(LocalDate.I(r1));
    L7:
        throw new r("Unsupported field: WeekBasedYear");
    }

    @Override // j$.time.temporal.p
    public final Temporal Y(Temporal r4, long r5) {
        if (s(r4) == false) goto L12;
        int r3 = a.YEAR.f447b.a(r5, g.WEEK_BASED_YEAR);
        LocalDate r52 = LocalDate.I(r4);
        int r0 = r52.g(a.DAY_OF_WEEK);
        int r53 = g.a0(r52);
        if (r53 == 53) goto L7;
    L9:
        int r54 = (r53 - 1) * 7;
        return r4.h(LocalDate.of(r3, 1, 4).plusDays(r54 + (r0 - r3.g(r6))));
    L7:
        if (g.c0(r3) != 52) goto L9;
        r53 = 52;
        goto L9
    L12:
        throw new r("Unsupported field: WeekBasedYear");
    }

    @Override // j$.time.temporal.p
    public final boolean s(l r1) {
        if (r1.i(a.EPOCH_DAY) == false) goto L8;
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
        return "WeekBasedYear";
    }
}
