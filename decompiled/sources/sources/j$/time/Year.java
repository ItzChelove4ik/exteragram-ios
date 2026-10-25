package j$.time;

import j$.time.format.a0;
import j$.time.format.z;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class Year implements Temporal, j$.time.temporal.m, Comparable<Year>, Serializable {

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f309b = 0;
    private static final long serialVersionUID = -23038383694477807L;

    /* renamed from: a, reason: collision with root package name */
    public final int f310a;

    static {
        j$.time.format.q r0 = new j$.time.format.q();
        r0.h(j$.time.temporal.a.YEAR, 4, 10, a0.EXCEEDS_PAD);
        r0.l(Locale.getDefault(), z.SMART, null);
    }

    public Year(int r1) {
        this.f310a = r1;
    }

    public static Year of(int r3) {
        j$.time.temporal.a.YEAR.a0(r3);
        return new Year(r3);
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new o((byte) 11, this);
    }

    public final Year D(long r4) {
        if (r4 != 0) goto L5;
        return this;
    L5:
        j$.time.temporal.a r0 = j$.time.temporal.a.YEAR;
        return of(r0.f447b.a(this.f310a + r4, r0));
    }

    public final Year I(long r5, j$.time.temporal.p r7) {
        if ((r7 instanceof j$.time.temporal.a) == false) goto L25;
        j$.time.temporal.a r0 = (j$.time.temporal.a) r7;
        r0.a0(r5);
        int r02 = p.f435a[r0.ordinal()];
        if (r02 == 1) goto L20;
        if (r02 == 2) goto L18;
        if (r02 != 3) goto L16;
        if (k(j$.time.temporal.a.ERA) != r5) goto L14;
        return this;
    L14:
        return of(1 - this.f310a);
    L16:
        throw new j$.time.temporal.r(c.a("Unsupported field: ", r7));
    L18:
        return of((int) r5);
    L20:
        if (this.f310a >= 1) goto L23;
        r5 = 1 - r5;
    L23:
        return of((int) r5);
    L25:
        return (Year) r7.Y(this, r5);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal a(long r1, j$.time.temporal.p r3) {
        return I(r1, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final /* bridge */ /* synthetic */ Temporal b(long r1, TemporalUnit r3) {
        return s(r1, r3);
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal c(long r3, TemporalUnit r5) {
        if (r3 != Long.MIN_VALUE) goto L7;
        Year this = s(LongCompanionObject.MAX_VALUE, r5);
        long r32 = 1;
    L6:
        return this.s(r32, r5);
    L7:
        r32 = -r3;
        goto L6
    }

    @Override // java.lang.Comparable
    public final int compareTo(Year r1) {
        return this.f310a - r1.f310a;
    }

    @Override // j$.time.temporal.l
    public final Object d(e r2) {
        if (r2 != j$.time.temporal.q.f464b) goto L7;
        return j$.time.chrono.r.f353c;
    L7:
        if (r2 != j$.time.temporal.q.f465c) goto L11;
        return ChronoUnit.YEARS;
    L11:
        return super.d(r2);
    }

    @Override // j$.time.temporal.m
    public final Temporal e(Temporal r4) {
        if (j$.time.chrono.k.r(r4).equals(j$.time.chrono.r.f353c) == true) goto L5;
        j$.nio.file.b.h("Adjustment only supported on ISO date-time");
        return null;
    L5:
        return r4.a(this.f310a, j$.time.temporal.a.YEAR);
    }

    public final boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof Year) == true) goto L8;
    L10:
        return false;
    L8:
        if (this.f310a != ((Year) r4).f310a) goto L10;
        return true;
    }

    @Override // j$.time.temporal.l
    public final int g(j$.time.temporal.p r4) {
        return l(r4).a(k(r4), r4);
    }

    public int getValue() {
        return this.f310a;
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal h(LocalDate r1) {
        return (Year) r1.e(this);
    }

    public final int hashCode() {
        return this.f310a;
    }

    @Override // j$.time.temporal.l
    public final boolean i(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == true) goto L5;
        if (r2 != null) goto L13;
        return false;
    L13:
        if (r2.s(this) == false) goto L22;
        return true;
    L22:
        return false;
    L5:
        if (r2 != j$.time.temporal.a.YEAR) goto L7;
        return true;
    L7:
        if (r2 != j$.time.temporal.a.YEAR_OF_ERA) goto L9;
        return true;
    L9:
        if (r2 == j$.time.temporal.a.ERA) goto L20;
        return false;
    L20:
        return true;
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r4) {
        if ((r4 instanceof j$.time.temporal.a) == false) goto L25;
        int r0 = p.f435a[((j$.time.temporal.a) r4).ordinal()];
        int r1 = 1;
        if (r0 != 1) goto L7;
        int r3 = this.f310a;
        if (r3 >= 1) goto L23;
        r3 = 1 - r3;
    L23:
        return r3;
    L7:
        if (r0 == 2) goto L18;
        if (r0 != 3) goto L16;
        if (this.f310a >= 1) goto L14;
        r1 = 0;
    L14:
        return r1;
    L16:
        throw new j$.time.temporal.r(c.a("Unsupported field: ", r4));
    L18:
        return this.f310a;
    L25:
        return r4.S(this);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s l(j$.time.temporal.p r3) {
        if (r3 != j$.time.temporal.a.YEAR_OF_ERA) goto L11;
        if (this.f310a > 0) goto L9;
        long r2 = 1000000000;
    L8:
        return j$.time.temporal.s.f(1, r2);
    L9:
        r2 = 999999999;
        goto L8
    L11:
        return super.l(r3);
    }

    @Override // j$.time.temporal.Temporal
    public final long n(Temporal r8, TemporalUnit r9) {
        if ((r8 instanceof Year) == false) goto L5;
        Year r82 = (Year) r8;
    L13:
        if ((r9 instanceof ChronoUnit) == false) goto L35;
        long r3 = r82.f310a - this.f310a;
        int r0 = p.f436b[((ChronoUnit) r9).ordinal()];
        if (r0 != 1) goto L17;
        return r3;
    L17:
        if (r0 == 2) goto L33;
        if (r0 == 3) goto L31;
        if (r0 == 4) goto L29;
        if (r0 != 5) goto L26;
        j$.time.temporal.a r92 = j$.time.temporal.a.ERA;
        return r82.k(r92) - k(r92);
    L26:
        j$.nio.file.b.d("Unsupported unit: ", r9);
        return 0;
    L29:
        return r3 / 1000;
    L31:
        return r3 / 100;
    L33:
        return r3 / 10;
    L35:
        return r9.between(this, r82);
    L5:
        Objects.requireNonNull(r8, "temporal");
    L9:
        e = move-exception;
        j$.nio.file.b.g("Unable to obtain Year from TemporalAccessor: ", r8, r8.getClass().getName(), e);
        return 0;
    L7:
        if (j$.time.chrono.r.f353c.equals(j$.time.chrono.k.r(r8)) == true) goto L11;
        r8 = LocalDate.I(r8);     // Catch: b -> L9
    L11:
        r82 = of(r8.g(j$.time.temporal.a.YEAR));     // Catch: b -> L9
        goto L13
    }

    public final Year s(long r3, TemporalUnit r5) {
        if ((r5 instanceof ChronoUnit) == false) goto L27;
        int r0 = p.f436b[((ChronoUnit) r5).ordinal()];
        if (r0 == 1) goto L25;
        if (r0 == 2) goto L23;
        if (r0 == 3) goto L21;
        if (r0 == 4) goto L19;
        if (r0 != 5) goto L16;
        j$.time.temporal.a r52 = j$.time.temporal.a.ERA;
        return I(Math.addExact(k(r52), r3), r52);
    L16:
        j$.nio.file.b.d("Unsupported unit: ", r5);
        return null;
    L19:
        return D(Math.multiplyExact(r3, 1000));
    L21:
        return D(Math.multiplyExact(r3, 100));
    L23:
        return D(Math.multiplyExact(r3, 10));
    L25:
        return D(r3);
    L27:
        return (Year) r5.s(this, r3);
    }

    public final String toString() {
        return Integer.toString(this.f310a);
    }
}
