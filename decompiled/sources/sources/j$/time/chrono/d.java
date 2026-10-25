package j$.time.chrono;

import j$.time.LocalDate;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;
import java.io.Serializable;
import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract class d implements b, Temporal, j$.time.temporal.m, Serializable {
    private static final long serialVersionUID = 6282433883239719096L;

    public d() {
    }

    public static b s(k r1, Temporal r2) {
        b r22 = (b) r2;
        if (r1.equals(r22.f()) == false) goto L5;
        return r22;
    L5:
        j$.nio.file.b.k("Chronology mismatch, expected: ", r1.getId(), r22.f().getId());
        return null;
    }

    public final long D(b r9) {
        if (f().B(j$.time.temporal.a.MONTH_OF_YEAR).d != 12) goto L7;
        j$.time.temporal.a r0 = j$.time.temporal.a.PROLEPTIC_MONTH;
        long r1 = k(r0) * 32;
        j$.time.temporal.a r5 = j$.time.temporal.a.DAY_OF_MONTH;
        return (((r9.k(r0) * 32) + r9.g(r5)) - (r1 + g(r5))) / 32;
    L7:
        throw new IllegalStateException("ChronoLocalDateImpl only supports Chronologies with 12 months per year");
    }

    public abstract b I(long r1);

    public abstract b K(long r1);

    public abstract b S(long r1);

    @Override // j$.time.chrono.b
    public b U(j$.time.temporal.o r2) {
        return s(f(), r2.s(this));
    }

    @Override // j$.time.chrono.b, j$.time.temporal.Temporal
    public b a(long r2, j$.time.temporal.p r4) {
        if ((r4 instanceof j$.time.temporal.a) == true) goto L7;
        return s(f(), r4.Y(this, r2));
    L7:
        throw new j$.time.temporal.r(j$.time.c.a("Unsupported field: ", r4));
    }

    @Override // j$.time.temporal.Temporal
    public /* bridge */ /* synthetic */ Temporal a(long r1, j$.time.temporal.p r3) {
        return a(r1, r3);
    }

    @Override // j$.time.chrono.b, j$.time.temporal.Temporal
    public b b(long r5, TemporalUnit r7) {
        boolean r0 = r7 instanceof ChronoUnit;
        if (r0 == true) goto L5;
        if (r0 == false) goto L26;
        j$.nio.file.b.d("Unsupported unit: ", r7);
        return null;
    L26:
        return s(f(), r7.s(this, r5));
    L5:
        switch(c.f326a[((ChronoUnit) r7).ordinal()]) {
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
        j$.nio.file.b.d("Unsupported unit: ", r7);
        return null;
    L8:
        j$.time.temporal.a r72 = j$.time.temporal.a.ERA;
        return a(Math.addExact(k(r72), r5), r72);
    L11:
        return S(Math.multiplyExact(r5, 1000));
    L13:
        return S(Math.multiplyExact(r5, 100));
    L15:
        return S(Math.multiplyExact(r5, 10));
    L17:
        return S(r5);
    L19:
        return K(r5);
    L21:
        return I(Math.multiplyExact(r5, 7));
    L23:
        return I(r5);
    }

    @Override // j$.time.temporal.Temporal
    public /* bridge */ /* synthetic */ Temporal b(long r1, TemporalUnit r3) {
        return b(r1, r3);
    }

    @Override // j$.time.chrono.b, j$.time.temporal.Temporal
    public /* bridge */ /* synthetic */ Temporal c(long r1, TemporalUnit r3) {
        return c(r1, r3);
    }

    @Override // j$.time.chrono.b
    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof b) == true) goto L8;
    L10:
        return false;
    L8:
        if (X((b) r4) != 0) goto L10;
        return true;
    }

    @Override // j$.time.temporal.Temporal
    public /* bridge */ /* synthetic */ Temporal h(LocalDate r1) {
        return j(r1);
    }

    @Override // j$.time.chrono.b
    public int hashCode() {
        long r0 = L();
        return f().hashCode() ^ ((int) (r0 ^ (r0 >>> 32)));
    }

    @Override // j$.time.chrono.b
    public b j(j$.time.temporal.m r2) {
        return s(f(), r2.e(this));
    }

    @Override // j$.time.chrono.b, j$.time.temporal.Temporal
    public final long n(Temporal r3, TemporalUnit r4) {
        Objects.requireNonNull(r3, "endExclusive");
        b r32 = f().J(r3);
        if ((r4 instanceof ChronoUnit) == true) goto L5;
        Objects.requireNonNull(r4, "unit");
        return r4.between(this, r32);
    L5:
        switch(c.f326a[((ChronoUnit) r4).ordinal()]) {
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
        return D(r32) / 12000;
    L13:
        return D(r32) / 1200;
    L15:
        return D(r32) / 120;
    L17:
        return D(r32) / 12;
    L19:
        return D(r32);
    L21:
        return (r32.L() - L()) / 7;
    L23:
        return r32.L() - L();
    }

    @Override // j$.time.chrono.b
    public final String toString() {
        long r0 = k(j$.time.temporal.a.YEAR_OF_ERA);
        long r2 = k(j$.time.temporal.a.MONTH_OF_YEAR);
        long r4 = k(j$.time.temporal.a.DAY_OF_MONTH);
        StringBuilder r6 = new StringBuilder(30);
        r6.append(f().toString());
        r6.append(" ");
        r6.append(P());
        r6.append(" ");
        r6.append(r0);
        String r7 = "-";
        if (r2 >= 10) goto L5;
        String r9 = "-0";
    L6:
        r6.append(r9);
        r6.append(r2);
        if (r4 >= 10) goto L9;
        r7 = "-0";
    L9:
        r6.append(r7);
        r6.append(r4);
        return r6.toString();
    L5:
        r9 = "-";
        goto L6
    }
}
