package j$.time.temporal;

import j$.time.LocalDate;
import j$.time.format.y;
import j$.time.format.z;
import java.util.Map;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum e extends g {
    public e() {
        super("WEEK_OF_WEEK_BASED_YEAR", 2);
    }

    @Override // j$.time.temporal.p
    public final s D(l r1) {
        if (s(r1) == false) goto L7;
        return g.d0(LocalDate.I(r1));
    L7:
        throw new r("Unsupported field: WeekOfWeekBasedYear");
    }

    @Override // j$.time.temporal.p
    public final l I(Map r17, y r18, z r19) {
        p r3 = g.WEEK_BASED_YEAR;
        Long r4 = (Long) r17.get(r3);
        a r5 = a.DAY_OF_WEEK;
        Long r6 = (Long) r17.get(r5);
        if (r4 == null) goto L30;
        if (r6 == null) goto L30;
        int r42 = r3.K().a(r4.longValue(), r3);
        long r8 = ((Long) r17.get(g.WEEK_OF_WEEK_BASED_YEAR)).longValue();
        g r10 = i.f454a;
        if (j$.time.chrono.k.r(r18).equals(j$.time.chrono.r.f353c) == false) goto L29;
        LocalDate r43 = LocalDate.of(r42, 1, 4);
        if (r19 != z.LENIENT) goto L18;
        long r62 = r6.longValue();
        if (r62 <= 7) goto L15;
        long r63 = r62 - 1;
        r43 = r43.k0(r63 / 7);
        long r64 = r63 % 7;
    L13:
        r62 = r64 + 1;
    L17:
        LocalDate r2 = r43.k0(Math.subtractExact(r8, 1)).n0(r62, r5);
    L27:
        r17.remove(this);
        r17.remove(r3);
        r17.remove(r5);
        return r2;
    L15:
        if (r62 >= 1) goto L17;
        r43 = r43.k0(Math.subtractExact(r62, 7) / 7);
        r64 = (r62 + 6) % 7;
        goto L13
    L18:
        int r65 = r5.f447b.a(r6.longValue(), r5);
        if (r8 < 1) goto L23;
        if (r8 > 52) goto L23;
    L26:
        r2 = r43.k0(r8 - 1).n0(r65, r5);
    L23:
        if (r19 != z.STRICT) goto L25;
        g.d0(r43).b(r8, this);
        goto L26
    L25:
        K().b(r8, this);
        goto L26
    L29:
        j$.nio.file.b.h("Resolve requires IsoChronology");
    L30:
        return null;
    }

    @Override // j$.time.temporal.p
    public final s K() {
        return s.g(52, 53);
    }

    @Override // j$.time.temporal.p
    public final long S(l r1) {
        if (s(r1) == false) goto L7;
        return g.a0(LocalDate.I(r1));
    L7:
        throw new r("Unsupported field: WeekOfWeekBasedYear");
    }

    @Override // j$.time.temporal.p
    public final Temporal Y(Temporal r3, long r4) {
        K().b(r4, this);
        return r3.b(Math.subtractExact(r4, S(r3)), ChronoUnit.WEEKS);
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
        return "WeekOfWeekBasedYear";
    }
}
