package j$.time;

import de.robv.android.xposed.callbacks.XCallback;
import j$.time.chrono.ChronoLocalDateTime;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import org.mvel2.asm.signature.SignatureVisitor;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class LocalDate implements Temporal, j$.time.temporal.m, j$.time.chrono.b, Serializable {
    public static final LocalDate d = null;
    public static final LocalDate e = null;
    private static final long serialVersionUID = 2942565459149668126L;

    /* renamed from: a, reason: collision with root package name */
    public final int f288a;

    /* renamed from: b, reason: collision with root package name */
    public final short f289b;

    /* renamed from: c, reason: collision with root package name */
    public final short f290c;

    static {
        d = of(-999999999, 1, 1);
        e = of(999999999, 12, 31);
        of(1970, 1, 1);
    }

    public LocalDate(int r1, int r2, int r3) {
        this.f288a = r1;
        this.f289b = (short) r2;
        this.f290c = (short) r3;
    }

    public static LocalDate D(int r5, int r6, int r7) {
        int r0 = 28;
        if (r7 <= 28) goto L26;
        if (r6 != 2) goto L7;
        j$.time.chrono.r.f353c.getClass();
        if (j$.time.chrono.r.a0(r5) == false) goto L19;
        r0 = 29;
    L19:
        if (r7 <= r0) goto L26;
        if (r7 != 29) goto L24;
        j$.nio.file.b.c("Invalid date 'February 29' as '", r5, "' is not a leap year");
        return null;
    L24:
        throw new b("Invalid date '" + k.K(r6).name() + " " + r7 + "'");
    L7:
        if (r6 != 4) goto L9;
    L15:
        r0 = 30;
        goto L19
    L9:
        if (r6 == 6) goto L15;
        if (r6 == 9) goto L15;
        if (r6 == 11) goto L15;
        r0 = 31;
    L26:
        return new LocalDate(r5, r6, r7);
    }

    public static LocalDate I(j$.time.temporal.l r3) {
        Objects.requireNonNull(r3, "temporal");
        LocalDate r0 = (LocalDate) r3.d(j$.time.temporal.q.f);
        if (r0 == null) goto L5;
        return r0;
    L5:
        j$.nio.file.b.f("Unable to obtain LocalDate from TemporalAccessor: ", r3, " of type ", r3.getClass().getName());
        return null;
    }

    public static LocalDate f0(a r4) {
        Instant r0 = Instant.ofEpochMilli(System.currentTimeMillis());
        ZoneId r42 = r4.f321a;
        Objects.requireNonNull(r0, "instant");
        Objects.requireNonNull(r42, "zone");
        ZoneOffset r43 = r42.getRules().getOffset(r0);
        return g0(Math.floorDiv(r0.getEpochSecond() + r43.getTotalSeconds(), 86400));
    }

    public static LocalDate g0(long r23) {
        j$.time.temporal.a.EPOCH_DAY.a0(r23);
        long r2 = 719468 + r23;
        if (r2 >= 0) goto L5;
        long r0 = ((r23 + 719469) / 146097) - 1;
        long r13 = r0 * 400;
        r2 = r2 + ((-r0) * 146097);
    L6:
        long r02 = ((r2 * 400) + 591) / 146097;
        long r19 = r2 - ((r02 / 400) + (((r02 / 4) + (r02 * 365)) - (r02 / 100)));
        if (r19 >= 0) goto L9;
        r02 = r02 - 1;
        r19 = r2 - ((r02 / 400) + (((r02 / 4) + (365 * r02)) - (r02 / 100)));
    L9:
        int r22 = (int) r19;
        int r3 = ((r22 * 5) + 2) / 153;
        int r4 = ((r3 + 2) % 12) + 1;
        int r24 = (r22 - (((r3 * 306) + 5) / 10)) + 1;
        long r03 = (r02 + r13) + (r3 / 10);
        j$.time.temporal.a r32 = j$.time.temporal.a.YEAR;
        return new LocalDate(r32.f447b.a(r03, r32), r4, r24);
    L5:
        r13 = 0;
        goto L6
    }

    public static LocalDate h0(int r5, int r6) {
        long r1 = r5;
        j$.time.temporal.a.YEAR.a0(r1);
        j$.time.temporal.a.DAY_OF_YEAR.a0(r6);
        j$.time.chrono.r.f353c.getClass();
        boolean r0 = j$.time.chrono.r.a0(r1);
        if (r6 != 366) goto L8;
        if (r0 == true) goto L8;
        j$.nio.file.b.c("Invalid date 'DayOfYear 366' as '", r5, "' is not a leap year");
        return null;
    L8:
        k r12 = k.K(((r6 - 1) / 31) + 1);
        int r2 = r12.s(r0);
        if (r6 <= ((r12.D(r0) + r2) - 1)) goto L12;
        r12 = k.f428a[(r12.ordinal() + 13) % 12];
    L12:
        return new LocalDate(r5, r12.getValue(), (r6 - r12.s(r0)) + 1);
    }

    public static LocalDate m0(int r3, int r4, int r5) {
        if (r4 != 2) goto L5;
        j$.time.chrono.r.f353c.getClass();
        if (j$.time.chrono.r.a0(r3) == false) goto L17;
        int r0 = 29;
    L18:
        r5 = Math.min(r5, r0);
    L20:
        return new LocalDate(r3, r4, r5);
    L17:
        r0 = 28;
        goto L18
    L5:
        if (r4 != 4) goto L7;
    L13:
        r5 = Math.min(r5, 30);
        goto L20
    L7:
        if (r4 == 6) goto L13;
        if (r4 == 9) goto L13;
        if (r4 == 11) goto L13;
        goto L13
    }

    public static LocalDate now() {
        return f0(j$.desugar.sun.nio.fs.g.A());
    }

    public static LocalDate of(int r3, int r4, int r5) {
        j$.time.temporal.a.YEAR.a0(r3);
        j$.time.temporal.a.MONTH_OF_YEAR.a0(r4);
        j$.time.temporal.a.DAY_OF_MONTH.a0(r5);
        return D(r3, r4, r5);
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new o((byte) 3, this);
    }

    public final int K(j$.time.temporal.p r3) {
        switch(g.f422a[((j$.time.temporal.a) r3).ordinal()]) {
            case 1: goto L37;
            case 2: goto L35;
            case 3: goto L33;
            case 4: goto L27;
            case 5: goto L26;
            case 6: goto L24;
            case 7: goto L22;
            case 8: goto L20;
            case 9: goto L18;
            case 10: goto L16;
            case 11: goto L14;
            case 12: goto L12;
            case 13: goto L7;
            default: goto L5;
        };
    L27:
        int r2 = this.f288a;
        if (r2 < 1) goto L31;
        return r2;
    L31:
        return 1 - r2;
    L5:
        throw new j$.time.temporal.r(c.a("Unsupported field: ", r3));
    L7:
        if (this.f288a < 1) goto L9;
        return 1;
    L9:
        return 0;
    L12:
        return this.f288a;
    L14:
        throw new j$.time.temporal.r("Invalid field 'ProlepticMonth' for get() method, use getLong() instead");
    L16:
        return this.f289b;
    L18:
        return ((Y() - 1) / 7) + 1;
    L20:
        throw new j$.time.temporal.r("Invalid field 'EpochDay' for get() method, use getLong() instead");
    L22:
        return ((Y() - 1) % 7) + 1;
    L24:
        return ((this.f290c - 1) % 7) + 1;
    L26:
        return S().getValue();
    L33:
        return ((this.f290c - 1) / 7) + 1;
    L35:
        return Y();
    L37:
        return this.f290c;
    }

    @Override // j$.time.chrono.b
    public final long L() {
        long r0 = this.f288a;
        long r2 = this.f289b;
        long r4 = 365 * r0;
        if (r0 < 0) goto L5;
        long r02 = (((r0 + 399) / 400) + (((3 + r0) / 4) - ((99 + r0) / 100))) + r4;
    L6:
        long r42 = ((((367 * r2) - 362) / 12) + r02) + (this.f290c - 1);
        if (r2 <= 2) goto L13;
        long r22 = r42 - 1;
        if (c0() == true) goto L11;
        r42 = r42 - 2;
        goto L13
    L11:
        r42 = r22;
    L13:
        return r42 - 719528;
    L5:
        r02 = r4 - ((r0 / (-400)) + ((r0 / (-4)) - (r0 / (-100))));
        goto L6
    }

    @Override // j$.time.chrono.b
    public final ChronoLocalDateTime M(LocalTime r1) {
        return LocalDateTime.of(this, r1);
    }

    @Override // j$.time.chrono.b
    public final j$.time.chrono.l P() {
        if (getYear() < 1) goto L7;
        return j$.time.chrono.s.CE;
    L7:
        return j$.time.chrono.s.BCE;
    }

    public final DayOfWeek S() {
        return DayOfWeek.s(((int) Math.floorMod(L() + 3, 7)) + 1);
    }

    @Override // j$.time.chrono.b
    public final j$.time.chrono.b U(j$.time.temporal.o r5) {
        if (r5 == null) goto L5;
        Period r52 = (Period) r5;
        return j0((r52.f306a * 12) + r52.f307b).plusDays(r52.getDays());
    L5:
        Objects.requireNonNull(r5, "amountToAdd");
        return (LocalDate) ((Period) r5).s(this);
    }

    @Override // j$.time.chrono.b
    public final int X(j$.time.chrono.b r2) {
        if ((r2 instanceof LocalDate) == false) goto L7;
        return s((LocalDate) r2);
    L7:
        return super.X(r2);
    }

    public final int Y() {
        return (k.K(this.f289b).s(c0()) + this.f290c) - 1;
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ j$.time.chrono.b a(long r1, j$.time.temporal.p r3) {
        return n0(r1, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal a(long r1, j$.time.temporal.p r3) {
        return n0(r1, r3);
    }

    public final long a0() {
        return ((this.f288a * 12) + this.f289b) - 1;
    }

    public LocalDateTime atStartOfDay() {
        return LocalDateTime.of(this, LocalTime.g);
    }

    public ZonedDateTime atStartOfDay(ZoneId r3) {
        Objects.requireNonNull(r3, "zone");
        LocalDateTime r2 = LocalDateTime.of(this, LocalTime.g);
        if ((r3 instanceof ZoneOffset) == true) goto L10;
        j$.time.zone.b r0 = r3.getRules().e(r2);
        if (r0 == null) goto L10;
        if (r0.s() == false) goto L10;
        r2 = r0.f475b.Y(r0.d.getTotalSeconds() - r0.f476c.getTotalSeconds());
    L10:
        return ZonedDateTime.I(r2, null, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ j$.time.chrono.b b(long r1, TemporalUnit r3) {
        return i0(r1, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal b(long r1, TemporalUnit r3) {
        return i0(r1, r3);
    }

    public final boolean b0(j$.time.chrono.b r6) {
        if ((r6 instanceof LocalDate) == false) goto L9;
        if (s((LocalDate) r6) >= 0) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (L() >= r6.L()) goto L11;
        return true;
    L11:
        return false;
    }

    @Override // j$.time.temporal.Temporal
    public final j$.time.chrono.b c(long r1, TemporalUnit r3) {
        return i0(-1, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal c(long r3, TemporalUnit r5) {
        if (r3 != Long.MIN_VALUE) goto L7;
        LocalDate this = i0(LongCompanionObject.MAX_VALUE, r5);
        long r32 = 1;
    L6:
        return this.i0(r32, r5);
    L7:
        r32 = -r3;
        goto L6
    }

    public final boolean c0() {
        j$.time.chrono.r r0 = j$.time.chrono.r.f353c;
        long r1 = this.f288a;
        r0.getClass();
        return j$.time.chrono.r.a0(r1);
    }

    @Override // j$.time.chrono.b, java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object r1) {
        return X((j$.time.chrono.b) r1);
    }

    @Override // j$.time.temporal.l
    public final Object d(e r2) {
        if (r2 != j$.time.temporal.q.f) goto L6;
        return this;
    L6:
        return super.d(r2);
    }

    public final int d0() {
        short r0 = this.f289b;
        if (r0 == 2) goto L17;
        if (r0 != 4) goto L7;
        return 30;
    L7:
        if (r0 != 6) goto L9;
        return 30;
    L9:
        if (r0 != 9) goto L11;
        return 30;
    L11:
        if (r0 == 11) goto L24;
        return 31;
    L24:
        return 30;
    L17:
        if (c0() == false) goto L20;
        return 29;
    L20:
        return 28;
    }

    public final long e0(LocalDate r7) {
        return (((r7.a0() * 32) + r7.getDayOfMonth()) - ((a0() * 32) + getDayOfMonth())) / 32;
    }

    @Override // j$.time.chrono.b
    public final boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof LocalDate) == true) goto L8;
    L10:
        return false;
    L8:
        if (s((LocalDate) r4) != 0) goto L10;
        return true;
    }

    @Override // j$.time.chrono.b
    public final j$.time.chrono.k f() {
        return j$.time.chrono.r.f353c;
    }

    @Override // j$.time.temporal.l
    public final int g(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == false) goto L7;
        return K(r2);
    L7:
        return super.g(r2);
    }

    public int getDayOfMonth() {
        return this.f290c;
    }

    public int getMonthValue() {
        return this.f289b;
    }

    public int getYear() {
        return this.f288a;
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal h(LocalDate r1) {
        return o0(r1);
    }

    @Override // j$.time.chrono.b
    public final int hashCode() {
        int r0 = this.f288a;
        short r1 = this.f289b;
        int r2 = r0 & (-2048);
        int r02 = (r0 << 11) + (r1 << 6);
        return r2 ^ (r02 + this.f290c);
    }

    public final LocalDate i0(long r3, TemporalUnit r5) {
        if ((r5 instanceof ChronoUnit) == false) goto L25;
        switch(g.f423b[((ChronoUnit) r5).ordinal()]) {
            case 1: goto L23;
            case 2: goto L21;
            case 3: goto L19;
            case 4: goto L17;
            case 5: goto L15;
            case 6: goto L13;
            case 7: goto L11;
            case 8: goto L8;
            default: goto L6;
        };
    L6:
        j$.nio.file.b.d("Unsupported unit: ", r5);
        return null;
    L8:
        j$.time.temporal.a r52 = j$.time.temporal.a.ERA;
        return n0(Math.addExact(k(r52), r3), r52);
    L11:
        return l0(Math.multiplyExact(r3, 1000));
    L13:
        return l0(Math.multiplyExact(r3, 100));
    L15:
        return l0(Math.multiplyExact(r3, 10));
    L17:
        return l0(r3);
    L19:
        return j0(r3);
    L21:
        return k0(r3);
    L23:
        return plusDays(r3);
    L25:
        return (LocalDate) r5.s(this, r3);
    }

    @Override // j$.time.chrono.b
    public final /* bridge */ /* synthetic */ j$.time.chrono.b j(j$.time.temporal.m r1) {
        return o0(r1);
    }

    public final LocalDate j0(long r7) {
        if (r7 != 0) goto L5;
        return this;
    L5:
        long r0 = ((this.f288a * 12) + (this.f289b - 1)) + r7;
        j$.time.temporal.a r72 = j$.time.temporal.a.YEAR;
        return m0(r72.f447b.a(Math.floorDiv(r0, 12), r72), ((int) Math.floorMod(r0, 12)) + 1, this.f290c);
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == false) goto L15;
        if (r2 != j$.time.temporal.a.EPOCH_DAY) goto L9;
        return L();
    L9:
        if (r2 != j$.time.temporal.a.PROLEPTIC_MONTH) goto L13;
        return a0();
    L13:
        return K(r2);
    L15:
        return r2.S(this);
    }

    public final LocalDate k0(long r3) {
        return plusDays(Math.multiplyExact(r3, 7));
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s l(j$.time.temporal.p r5) {
        if ((r5 instanceof j$.time.temporal.a) == false) goto L41;
        j$.time.temporal.a r0 = (j$.time.temporal.a) r5;
        if (r0.isDateBased() == false) goto L39;
        int r52 = g.f422a[r0.ordinal()];
        if (r52 == 1) goto L37;
        if (r52 == 2) goto L31;
        if (r52 == 3) goto L23;
        if (r52 == 4) goto L17;
        return r0.f447b;
    L17:
        if (getYear() > 0) goto L21;
        return j$.time.temporal.s.f(1, 1000000000);
    L21:
        return j$.time.temporal.s.f(1, 999999999);
    L23:
        if (k.K(this.f289b) == k.FEBRUARY) goto L25;
    L27:
        long r4 = 5;
    L29:
        return j$.time.temporal.s.f(1, r4);
    L25:
        if (c0() == true) goto L27;
        r4 = 4;
        goto L29
    L31:
        if (c0() == false) goto L33;
        int r42 = 366;
    L35:
        return j$.time.temporal.s.f(1, r42);
    L33:
        r42 = 365;
        goto L35
    L37:
        return j$.time.temporal.s.f(1, d0());
    L39:
        throw new j$.time.temporal.r(c.a("Unsupported field: ", r5));
    L41:
        return r5.D(this);
    }

    public final LocalDate l0(long r4) {
        if (r4 != 0) goto L5;
        return this;
    L5:
        j$.time.temporal.a r0 = j$.time.temporal.a.YEAR;
        return m0(r0.f447b.a(this.f288a + r4, r0), this.f289b, this.f290c);
    }

    @Override // j$.time.temporal.Temporal
    public final long n(Temporal r3, TemporalUnit r4) {
        LocalDate r32 = I(r3);
        if ((r4 instanceof ChronoUnit) == false) goto L25;
        switch(g.f423b[((ChronoUnit) r4).ordinal()]) {
            case 1: goto L23;
            case 2: goto L21;
            case 3: goto L19;
            case 4: goto L17;
            case 5: goto L15;
            case 6: goto L13;
            case 7: goto L11;
            case 8: goto L8;
            default: goto L6;
        };
    L6:
        j$.nio.file.b.d("Unsupported unit: ", r4);
        return 0;
    L8:
        j$.time.temporal.a r42 = j$.time.temporal.a.ERA;
        return r32.k(r42) - k(r42);
    L11:
        return e0(r32) / 12000;
    L13:
        return e0(r32) / 1200;
    L15:
        return e0(r32) / 120;
    L17:
        return e0(r32) / 12;
    L19:
        return e0(r32);
    L21:
        return (r32.L() - L()) / 7;
    L23:
        return r32.L() - L();
    L25:
        return r4.between(this, r32);
    }

    public final LocalDate n0(long r5, j$.time.temporal.p r7) {
        if ((r7 instanceof j$.time.temporal.a) == false) goto L51;
        j$.time.temporal.a r0 = (j$.time.temporal.a) r7;
        r0.a0(r5);
        switch(g.f422a[r0.ordinal()]) {
            case 1: goto L45;
            case 2: goto L40;
            case 3: goto L39;
            case 4: goto L33;
            case 5: goto L31;
            case 6: goto L29;
            case 7: goto L27;
            case 8: goto L25;
            case 9: goto L23;
            case 10: goto L17;
            case 11: goto L16;
            case 12: goto L14;
            case 13: goto L9;
            default: goto L7;
        };
    L17:
        int r52 = (int) r5;
        if (this.f289b == r52) goto L47;
        j$.time.temporal.a.MONTH_OF_YEAR.a0(r52);
        return m0(this.f288a, r52, this.f290c);
    L47:
        return this;
    L40:
        int r53 = (int) r5;
        if (Y() == r53) goto L47;
        return h0(this.f288a, r53);
    L45:
        int r54 = (int) r5;
        if (this.f290c == r54) goto L47;
        return of(this.f288a, this.f289b, r54);
    L7:
        throw new j$.time.temporal.r(c.a("Unsupported field: ", r7));
    L9:
        if (k(j$.time.temporal.a.ERA) == r5) goto L47;
        return p0(1 - this.f288a);
    L14:
        return p0((int) r5);
    L16:
        return j0(r5 - a0());
    L23:
        return k0(r5 - k(j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR));
    L25:
        return g0(r5);
    L27:
        return plusDays(r5 - k(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR));
    L29:
        return plusDays(r5 - k(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH));
    L31:
        return plusDays(r5 - S().getValue());
    L33:
        if (this.f288a >= 1) goto L37;
        r5 = 1 - r5;
    L37:
        return p0((int) r5);
    L39:
        return k0(r5 - k(j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH));
    L51:
        return (LocalDate) r7.Y(this, r5);
    }

    public final LocalDate o0(j$.time.temporal.m r2) {
        if ((r2 instanceof LocalDate) == false) goto L7;
        return (LocalDate) r2;
    L7:
        return (LocalDate) r2.e(this);
    }

    public final LocalDate p0(int r4) {
        if (this.f288a != r4) goto L5;
        return this;
    L5:
        j$.time.temporal.a.YEAR.a0(r4);
        return m0(r4, this.f289b, this.f290c);
    }

    public LocalDate plusDays(long r8) {
        if (r8 != 0) goto L5;
        return this;
    L5:
        long r2 = this.f290c + r8;
        if (r2 <= 0) goto L24;
        if (r2 > 28) goto L12;
        return new LocalDate(this.f288a, this.f289b, (int) r2);
    L12:
        if (r2 > 59) goto L24;
        long r82 = d0();
        if (r2 <= r82) goto L16;
        short r0 = this.f289b;
        if (r0 < 12) goto L20;
        j$.time.temporal.a.YEAR.a0(this.f288a + 1);
        return new LocalDate(this.f288a + 1, 1, (int) (r2 - r82));
    L20:
        return new LocalDate(this.f288a, r0 + 1, (int) (r2 - r82));
    L16:
        return new LocalDate(this.f288a, this.f289b, (int) r2);
    L24:
        return g0(Math.addExact(L(), r8));
    }

    public final int s(LocalDate r3) {
        int r0 = this.f288a - r3.f288a;
        if (r0 != 0) goto L8;
        r0 = this.f289b - r3.f289b;
        if (r0 != 0) goto L8;
        return this.f290c - r3.f290c;
    L8:
        return r0;
    }

    @Override // j$.time.chrono.b
    public final String toString() {
        int r0 = this.f288a;
        short r1 = this.f289b;
        short r6 = this.f290c;
        int r2 = Math.abs(r0);
        StringBuilder r3 = new StringBuilder(10);
        if (r2 >= 1000) goto L8;
        if (r0 >= 0) goto L6;
        r3.append(r0 + XCallback.PRIORITY_LOWEST);
        r3.deleteCharAt(1);
    L11:
        String r02 = "-";
        if (r1 >= 10) goto L14;
        String r5 = "-0";
    L15:
        r3.append(r5);
        r3.append(r1);
        if (r6 >= 10) goto L18;
        r02 = "-0";
    L18:
        r3.append(r02);
        r3.append(r6);
        return r3.toString();
    L14:
        r5 = "-";
        goto L15
    L6:
        r3.append(r0 + 10000);
        r3.deleteCharAt(0);
        goto L11
    L8:
        if (r0 <= 9999) goto L10;
        r3.append(SignatureVisitor.EXTENDS);
    L10:
        r3.append(r0);
        goto L11
    }
}
