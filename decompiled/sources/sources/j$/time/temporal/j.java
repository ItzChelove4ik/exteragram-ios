package j$.time.temporal;

import j$.time.format.y;
import j$.time.format.z;
import java.util.Map;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum j extends Enum implements p {
    public static final j JULIAN_DAY = null;
    public static final j MODIFIED_JULIAN_DAY = null;
    public static final j RATA_DIE = null;
    public static final /* synthetic */ j[] d = null;
    private static final long serialVersionUID = -7501623920830201812L;

    /* renamed from: a, reason: collision with root package name */
    public final transient String f457a;

    /* renamed from: b, reason: collision with root package name */
    public final transient s f458b;

    /* renamed from: c, reason: collision with root package name */
    public final transient long f459c;

    static {
        ChronoUnit r5 = ChronoUnit.DAYS;
        ChronoUnit r6 = ChronoUnit.FOREVER;
        j r0 = new j("JULIAN_DAY", 0, "JulianDay", r5, r6, 2440588);
        JULIAN_DAY = r0;
        j r1 = new j("MODIFIED_JULIAN_DAY", 1, "ModifiedJulianDay", r5, r6, 40587);
        MODIFIED_JULIAN_DAY = r1;
        j r12 = new j("RATA_DIE", 2, "RataDie", r5, r6, 719163);
        RATA_DIE = r12;
        d = new j[]{r0, r1, r12};
    }

    j(String r1, int r2, String r3, ChronoUnit r4, ChronoUnit r5, long r6) {
        this.f457a = r3;
        this.f458b = s.f((-365243219162L) + r6, 365241780471L + r6);
        this.f459c = r6;
    }

    public static j valueOf(String r1) {
        return (j) Enum.valueOf(j.class, r1);
    }

    public static j[] values() {
        return (j[]) d.clone();
    }

    @Override // j$.time.temporal.p
    public final s D(l r2) {
        if (r2.i(a.EPOCH_DAY) == true) goto L5;
        j$.nio.file.b.j("Unsupported field: ", this);
        return null;
    L5:
        return this.f458b;
    }

    @Override // j$.time.temporal.p
    public final l I(Map r5, y r6, z r7) {
        long r0 = ((Long) r5.remove(this)).longValue();
        j$.time.chrono.k r52 = j$.time.chrono.k.r(r6);
        z r62 = z.LENIENT;
        long r2 = this.f459c;
        if (r7 == r62) goto L5;
        this.f458b.b(r0, this);
        return r52.p(r0 - r2);
    L5:
        return r52.p(Math.subtractExact(r0, r2));
    }

    @Override // j$.time.temporal.p
    public final s K() {
        return this.f458b;
    }

    @Override // j$.time.temporal.p
    public final long S(l r3) {
        return r3.k(a.EPOCH_DAY) + this.f459c;
    }

    @Override // j$.time.temporal.p
    public final Temporal Y(Temporal r4, long r5) {
        if (this.f458b.e(r5) == false) goto L7;
        return r4.a(Math.subtractExact(r5, this.f459c), a.EPOCH_DAY);
    L7:
        throw new j$.time.b("Invalid value: " + this.f457a + " " + r5);
    }

    @Override // j$.time.temporal.p
    public final boolean isDateBased() {
        return true;
    }

    @Override // j$.time.temporal.p
    public final boolean s(l r1) {
        return r1.i(a.EPOCH_DAY);
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f457a;
    }
}
