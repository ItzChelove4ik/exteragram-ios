package j$.time.temporal;

import j$.time.LocalDate;
import j$.time.format.y;
import j$.time.format.z;
import java.util.Map;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum c extends g {
    public c() {
        super("DAY_OF_QUARTER", 0);
    }

    @Override // j$.time.temporal.p
    public final s D(l r10) {
        if (s(r10) == false) goto L26;
        long r0 = r10.k(g.QUARTER_OF_YEAR);
        if (r0 != 1) goto L13;
        long r9 = r10.k(a.YEAR);
        j$.time.chrono.r.f353c.getClass();
        if (j$.time.chrono.r.a0(r9) == false) goto L11;
        return s.f(1, 91);
    L11:
        return s.f(1, 90);
    L13:
        if (r0 != 2) goto L17;
        return s.f(1, 91);
    L17:
        if (r0 == 3) goto L24;
        if (r0 == 4) goto L24;
        return K();
    L24:
        return s.f(1, 92);
    L26:
        throw new r("Unsupported field: DayOfQuarter");
    }

    @Override // j$.time.temporal.p
    public final l I(Map r12, y r13, z r14) {
        a r0 = a.YEAR;
        Long r1 = (Long) r12.get(r0);
        p r2 = g.QUARTER_OF_YEAR;
        Long r3 = (Long) r12.get(r2);
        if (r1 == null) goto L23;
        if (r3 == null) goto L23;
        long r5 = r1.longValue();
        int r15 = r0.f447b.a(r5, r0);
        long r52 = ((Long) r12.get(g.DAY_OF_QUARTER)).longValue();
        g r7 = i.f454a;
        if (j$.time.chrono.k.r(r13).equals(j$.time.chrono.r.f353c) == true) goto L9;
        j$.nio.file.b.h("Resolve requires IsoChronology");
        goto L23
    L9:
        if (r14 != z.LENIENT) goto L11;
        LocalDate r132 = LocalDate.of(r15, 1, 1).j0(Math.multiplyExact(Math.subtractExact(r3.longValue(), 1), 3));
        long r32 = Math.subtractExact(r52, 1);
    L20:
        r12.remove(this);
        r12.remove(r0);
        r12.remove(r2);
        return r132.plusDays(r32);
    L11:
        r132 = LocalDate.of(r15, ((r2.K().a(r3.longValue(), r2) - 1) * 3) + 1, 1);
        if (r52 < 1) goto L16;
        if (r52 > 90) goto L16;
    L19:
        r32 = r52 - 1;
    L16:
        if (r14 != z.STRICT) goto L18;
        D(r132).b(r52, this);
        goto L19
    L18:
        K().b(r52, this);
    L23:
        return null;
    }

    @Override // j$.time.temporal.p
    public final s K() {
        return s.g(90, 92);
    }

    @Override // j$.time.temporal.p
    public final long S(l r4) {
        if (s(r4) == false) goto L11;
        int r3 = r4.g(a.DAY_OF_YEAR);
        int r0 = r4.g(a.MONTH_OF_YEAR);
        long r1 = r4.k(a.YEAR);
        int r02 = (r0 - 1) / 3;
        j$.time.chrono.r.f353c.getClass();
        if (j$.time.chrono.r.a0(r1) == false) goto L7;
        int r42 = 4;
    L8:
        int r03 = r02 + r42;
        return r3 - g.f449a[r03];
    L7:
        r42 = 0;
        goto L8
    L11:
        throw new r("Unsupported field: DayOfQuarter");
    }

    @Override // j$.time.temporal.p
    public final Temporal Y(Temporal r5, long r6) {
        long r0 = S(r5);
        K().b(r6, this);
        a r4 = a.DAY_OF_YEAR;
        long r62 = r6 - r0;
        return r5.a(r62 + r5.k(r4), r4);
    }

    @Override // j$.time.temporal.p
    public final boolean s(l r1) {
        if (r1.i(a.DAY_OF_YEAR) == true) goto L5;
        return false;
    L5:
        if (r1.i(a.MONTH_OF_YEAR) == true) goto L7;
        return false;
    L7:
        if (r1.i(a.YEAR) == false) goto L15;
        g r0 = i.f454a;
        if (j$.time.chrono.k.r(r1).equals(j$.time.chrono.r.f353c) == false) goto L16;
        return true;
    L16:
        return false;
    L15:
        return false;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "DayOfQuarter";
    }
}
