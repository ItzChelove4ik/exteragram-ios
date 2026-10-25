package j$.time.chrono;

import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class b0 extends d {
    private static final long serialVersionUID = 1300372329181994526L;

    /* renamed from: a, reason: collision with root package name */
    public final transient LocalDate f325a;

    public b0(LocalDate r2) {
        Objects.requireNonNull(r2, "isoDate");
        this.f325a = r2;
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new d0((byte) 7, this);
    }

    @Override // j$.time.chrono.d
    public final b I(long r2) {
        return b0(this.f325a.plusDays(r2));
    }

    @Override // j$.time.chrono.d
    public final b K(long r2) {
        return b0(this.f325a.j0(r2));
    }

    @Override // j$.time.chrono.b
    public final long L() {
        return this.f325a.L();
    }

    @Override // j$.time.chrono.b
    public final ChronoLocalDateTime M(LocalTime r2) {
        return new f(this, r2);
    }

    @Override // j$.time.chrono.b
    public final l P() {
        if (Y() < 1) goto L7;
        return c0.ROC;
    L7:
        return c0.BEFORE_ROC;
    }

    @Override // j$.time.chrono.d
    public final b S(long r2) {
        return b0(this.f325a.l0(r2));
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final b U(j$.time.temporal.o r1) {
        return (b0) super.U(r1);
    }

    public final int Y() {
        return this.f325a.getYear() - 1911;
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b, j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ b a(long r1, j$.time.temporal.p r3) {
        return a0(r1, r3);
    }

    @Override // j$.time.chrono.d, j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal a(long r1, j$.time.temporal.p r3) {
        return a0(r1, r3);
    }

    public final b0 a0(long r8, j$.time.temporal.p r10) {
        if ((r10 instanceof j$.time.temporal.a) == false) goto L33;
        j$.time.temporal.a r0 = (j$.time.temporal.a) r10;
        if (k(r0) != r8) goto L7;
        return this;
    L7:
        int[] r1 = a0.f324a;
        int r2 = r1[r0.ordinal()];
        if (r2 != 4) goto L10;
    L16:
        int r22 = z.f365c.B(r0).a(r8, r0);
        int r02 = r1[r0.ordinal()];
        if (r02 == 4) goto L26;
        if (r02 == 6) goto L25;
        if (r02 == 7) goto L23;
    L21:
        return b0(this.f325a.n0(r8, r10));
    L23:
        return b0(this.f325a.p0(1912 - Y()));
    L25:
        return b0(this.f325a.p0(r22 + 1911));
    L26:
        LocalDate r82 = this.f325a;
        if (Y() < 1) goto L29;
        int r23 = r22 + 1911;
    L31:
        return b0(r82.p0(r23));
    L29:
        r23 = 1912 - r22;
        goto L31
    L10:
        if (r2 == 5) goto L14;
        if (r2 == 6) goto L16;
        if (r2 == 7) goto L16;
    L14:
        z.f365c.B(r0).b(r8, r0);
        return b0(this.f325a.j0(r8 - (((Y() * 12) + this.f325a.getMonthValue()) - 1)));
    L33:
        return (b0) super.a(r8, r10);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b, j$.time.temporal.Temporal
    public final b b(long r1, TemporalUnit r3) {
        return (b0) super.b(r1, r3);
    }

    @Override // j$.time.chrono.d, j$.time.temporal.Temporal
    public final Temporal b(long r1, TemporalUnit r3) {
        return (b0) super.b(r1, r3);
    }

    public final b0 b0(LocalDate r2) {
        if (r2.equals(this.f325a) == false) goto L6;
        return this;
    L6:
        return new b0(r2);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b, j$.time.temporal.Temporal
    public final b c(long r1, TemporalUnit r3) {
        return (b0) super.c(r1, r3);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b, j$.time.temporal.Temporal
    public final Temporal c(long r1, TemporalUnit r3) {
        return (b0) super.c(r1, r3);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof b0) == true) goto L8;
        return false;
    L8:
        return this.f325a.equals(((b0) r2).f325a);
    }

    @Override // j$.time.chrono.b
    public final k f() {
        return z.f365c;
    }

    @Override // j$.time.chrono.d, j$.time.temporal.Temporal
    public final Temporal h(LocalDate r1) {
        return (b0) super.j(r1);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final int hashCode() {
        z.f365c.getClass();
        return this.f325a.hashCode() ^ (-1990173233);
    }

    @Override // j$.time.chrono.d, j$.time.chrono.b
    public final b j(j$.time.temporal.m r1) {
        return (b0) super.j(r1);
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r5) {
        if ((r5 instanceof j$.time.temporal.a) == false) goto L31;
        int r0 = a0.f324a[((j$.time.temporal.a) r5).ordinal()];
        int r2 = 1;
        if (r0 != 4) goto L7;
        int r4 = Y();
        if (r4 >= 1) goto L29;
        r4 = 1 - r4;
    L29:
        return r4;
    L7:
        if (r0 == 5) goto L23;
        if (r0 == 6) goto L21;
        if (r0 == 7) goto L15;
        return this.f325a.k(r5);
    L15:
        if (Y() >= 1) goto L19;
        r2 = 0;
    L19:
        return r2;
    L21:
        return Y();
    L23:
        return ((Y() * 12) + this.f325a.getMonthValue()) - 1;
    L31:
        return r5.S(this);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s l(j$.time.temporal.p r4) {
        if ((r4 instanceof j$.time.temporal.a) == false) goto L27;
        if (i(r4) == false) goto L25;
        j$.time.temporal.a r0 = (j$.time.temporal.a) r4;
        int r1 = a0.f324a[r0.ordinal()];
        if (r1 == 1) goto L23;
        if (r1 == 2) goto L23;
        if (r1 == 3) goto L23;
        if (r1 != 4) goto L15;
        j$.time.temporal.s r42 = j$.time.temporal.a.YEAR.f447b;
        if (Y() > 0) goto L19;
        long r3 = (-r42.f466a) + 1912;
    L21:
        return j$.time.temporal.s.f(1, r3);
    L19:
        r3 = r42.d - 1911;
        goto L21
    L15:
        return z.f365c.B(r0);
    L23:
        return this.f325a.l(r4);
    L25:
        throw new j$.time.temporal.r(j$.time.c.a("Unsupported field: ", r4));
    L27:
        return r4.D(this);
    }
}
