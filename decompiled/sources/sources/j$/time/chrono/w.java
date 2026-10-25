package j$.time.chrono;

import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class w extends d {
    public static final LocalDate d = null;
    private static final long serialVersionUID = -305327627230580483L;

    /* renamed from: a, reason: collision with root package name */
    public final transient LocalDate f358a;

    /* renamed from: b, reason: collision with root package name */
    public final transient x f359b;

    /* renamed from: c, reason: collision with root package name */
    public final transient int f360c;

    static {
        d = LocalDate.of(1873, 1, 1);
    }

    public w(LocalDate r3) {
        if (r3.b0(d) == true) goto L6;
        x r0 = x.p(r3);
        this.f359b = r0;
        this.f360c = (r3.getYear() - r0.f362b.getYear()) + 1;
        this.f358a = r3;
        return;
    L6:
        j$.nio.file.b.h("JapaneseDate before Meiji 6 is not supported");
        throw null;
    }

    public w(x r2, int r3, LocalDate r4) {
        if (r4.b0(d) == true) goto L6;
        this.f359b = r2;
        this.f360c = r3;
        this.f358a = r4;
        return;
    L6:
        j$.nio.file.b.h("JapaneseDate before Meiji 6 is not supported");
        throw null;
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new d0((byte) 4, this);
    }

    @Override // j$.time.chrono.d
    public final b I(long r2) {
        return c0(this.f358a.plusDays(r2));
    }

    @Override // j$.time.chrono.d
    public final b K(long r2) {
        return c0(this.f358a.j0(r2));
    }

    @Override // j$.time.chrono.b
    public final long L() {
        return this.f358a.L();
    }

    @Override // j$.time.chrono.b
    public final ChronoLocalDateTime M(LocalTime r2) {
        return new f(this, r2);
    }

    @Override // j$.time.chrono.b
    public final l P() {
        return this.f359b;
    }

    @Override // j$.time.chrono.d
    public final b S(long r2) {
        return c0(this.f358a.l0(r2));
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final b U(j$.time.temporal.o r1) {
        return (w) super.U(r1);
    }

    public final w Y(long r1, ChronoUnit r3) {
        return (w) super.b(r1, r3);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b, j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ b a(long r1, j$.time.temporal.p r3) {
        return a0(r1, r3);
    }

    @Override // j$.time.chrono.d, j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal a(long r1, j$.time.temporal.p r3) {
        return a0(r1, r3);
    }

    public final w a0(long r8, j$.time.temporal.p r10) {
        if ((r10 instanceof j$.time.temporal.a) == false) goto L25;
        j$.time.temporal.a r0 = (j$.time.temporal.a) r10;
        if (k(r0) != r8) goto L7;
        return this;
    L7:
        int[] r1 = v.f357a;
        int r2 = r1[r0.ordinal()];
        if (r2 == 3) goto L12;
        if (r2 == 8) goto L12;
        if (r2 == 9) goto L12;
    L17:
        return c0(this.f358a.n0(r8, r10));
    L12:
        u r22 = u.f356c;
        int r6 = r22.B(r0).a(r8, r0);
        int r02 = r1[r0.ordinal()];
        if (r02 == 3) goto L22;
        if (r02 == 8) goto L20;
        if (r02 != 9) goto L17;
        return c0(this.f358a.p0(r6));
    L20:
        int r82 = r22.F(x.r(r6), this.f360c);
        return c0(this.f358a.p0(r82));
    L22:
        int r83 = r22.F(this.f359b, r6);
        return c0(this.f358a.p0(r83));
    L25:
        return (w) super.a(r8, r10);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b, j$.time.temporal.Temporal
    public final b b(long r1, TemporalUnit r3) {
        return (w) super.b(r1, r3);
    }

    @Override // j$.time.chrono.d, j$.time.temporal.Temporal
    public final Temporal b(long r1, TemporalUnit r3) {
        return (w) super.b(r1, r3);
    }

    public final w b0(j$.time.e r1) {
        return (w) super.j(r1);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b, j$.time.temporal.Temporal
    public final b c(long r1, TemporalUnit r3) {
        return (w) super.c(r1, r3);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b, j$.time.temporal.Temporal
    public final Temporal c(long r1, TemporalUnit r3) {
        return (w) super.c(r1, r3);
    }

    public final w c0(LocalDate r2) {
        if (r2.equals(this.f358a) == false) goto L6;
        return this;
    L6:
        return new w(r2);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof w) == true) goto L8;
        return false;
    L8:
        return this.f358a.equals(((w) r2).f358a);
    }

    @Override // j$.time.chrono.b
    public final k f() {
        return u.f356c;
    }

    @Override // j$.time.chrono.d, j$.time.temporal.Temporal
    public final Temporal h(LocalDate r1) {
        return (w) super.j(r1);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final int hashCode() {
        u.f356c.getClass();
        return this.f358a.hashCode() ^ (-688086063);
    }

    @Override // j$.time.chrono.b, j$.time.temporal.l
    public final boolean i(j$.time.temporal.p r2) {
        if (r2 != j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH) goto L5;
        return false;
    L5:
        if (r2 != j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR) goto L7;
        return false;
    L7:
        if (r2 != j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH) goto L9;
        return false;
    L9:
        if (r2 != j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR) goto L12;
        return false;
    L12:
        if ((r2 instanceof j$.time.temporal.a) == true) goto L14;
        if (r2 != null) goto L17;
        return false;
    L17:
        if (r2.s(this) == false) goto L25;
        return true;
    L25:
        return false;
    L14:
        return ((j$.time.temporal.a) r2).isDateBased();
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final b j(j$.time.temporal.m r1) {
        return (w) super.j(r1);
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r3) {
        if ((r3 instanceof j$.time.temporal.a) == false) goto L21;
        switch(v.f357a[((j$.time.temporal.a) r3).ordinal()]) {
            case 2: goto L14;
            case 3: goto L13;
            case 4: goto L11;
            case 5: goto L11;
            case 6: goto L11;
            case 7: goto L11;
            case 8: goto L9;
            default: goto L7;
        };
    L14:
        int r32 = this.f360c;
        LocalDate r0 = this.f358a;
        if (r32 != 1) goto L19;
        return (r0.Y() - this.f359b.f362b.Y()) + 1;
    L19:
        return r0.Y();
    L7:
        return this.f358a.k(r3);
    L9:
        return this.f359b.f361a;
    L11:
        throw new j$.time.temporal.r(j$.time.c.a("Unsupported field: ", r3));
    L13:
        return this.f360c;
    L21:
        return r3.S(this);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s l(j$.time.temporal.p r6) {
        if ((r6 instanceof j$.time.temporal.a) == false) goto L39;
        if (i(r6) == false) goto L37;
        j$.time.temporal.a r62 = (j$.time.temporal.a) r6;
        int r0 = v.f357a[r62.ordinal()];
        if (r0 == 1) goto L35;
        if (r0 != 2) goto L11;
        x r63 = this.f359b.q();
        if (r63 == null) goto L26;
        if (r63.f362b.getYear() != this.f358a.getYear()) goto L26;
        int r64 = r63.f362b.Y() - 1;
    L30:
        if (this.f360c != 1) goto L33;
        r64 = r64 - (this.f359b.f362b.Y() - 1);
    L33:
        return j$.time.temporal.s.f(1, r64);
    L26:
        if (this.f358a.c0() == false) goto L28;
        r64 = 366;
        goto L30
    L28:
        r64 = 365;
        goto L30
    L11:
        if (r0 != 3) goto L13;
        int r65 = this.f359b.f362b.getYear();
        if (this.f359b.q() == null) goto L19;
        return j$.time.temporal.s.f(1, (r5.f362b.getYear() - r65) + 1);
    L19:
        return j$.time.temporal.s.f(1, 999999999 - r65);
    L13:
        return u.f356c.B(r62);
    L35:
        return j$.time.temporal.s.f(1, this.f358a.d0());
    L37:
        throw new j$.time.temporal.r(j$.time.c.a("Unsupported field: ", r6));
    L39:
        return r6.D(this);
    }
}
