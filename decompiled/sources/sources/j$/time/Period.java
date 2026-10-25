package j$.time;

import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class Period implements j$.time.temporal.o, Serializable {
    public static final Period d = null;
    private static final long serialVersionUID = -3587258372562876L;

    /* renamed from: a, reason: collision with root package name */
    public final int f306a;

    /* renamed from: b, reason: collision with root package name */
    public final int f307b;

    /* renamed from: c, reason: collision with root package name */
    public final int f308c;

    static {
        d = new Period(0, 0, 0);
        Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)Y)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)W)?(?:([-+]?[0-9]+)D)?", 2);
        j$.desugar.sun.nio.fs.g.p(new Object[]{ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS});
    }

    public Period(int r1, int r2, int r3) {
        this.f306a = r1;
        this.f307b = r2;
        this.f308c = r3;
    }

    public static Period a(int r1, int r2, int r3) {
        if (((r1 | r2) | r3) != 0) goto L7;
        return d;
    L7:
        return new Period(r1, r2, r3);
    }

    public static Period between(LocalDate r6, LocalDate r7) {
        r6.getClass();
        LocalDate r72 = LocalDate.I(r7);
        long r0 = r72.a0() - r6.a0();
        int r2 = r72.f290c - r6.f290c;
        if (r0 <= 0) goto L6;
        if (r2 >= 0) goto L6;
        r0 = r0 - 1;
        LocalDate r62 = r6.j0(r0);
        r2 = (int) (r72.L() - r62.L());
    L9:
        long r3 = r0 / 12;
        int r73 = Math.toIntExact(r3);
        return of(r73, (int) (r0 % 12), r2);
    L6:
        if (r0 >= 0) goto L9;
        if (r2 <= 0) goto L9;
        r0 = r0 + 1;
        r2 = r2 - r72.d0();
        goto L9
    }

    public static Period of(int r0, int r1, int r2) {
        return a(r0, r1, r2);
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new o((byte) 14, this);
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Period) == false) goto L14;
        Period r52 = (Period) r5;
        if (this.f306a != r52.f306a) goto L14;
        if (this.f307b != r52.f307b) goto L14;
        if (this.f308c != r52.f308c) goto L14;
        return true;
    L14:
        return false;
    }

    public int getDays() {
        return this.f308c;
    }

    public int getMonths() {
        return this.f307b;
    }

    public int getYears() {
        return this.f306a;
    }

    public final int hashCode() {
        int r0 = this.f306a;
        int r1 = Integer.rotateLeft(this.f307b, 8) + r0;
        return Integer.rotateLeft(this.f308c, 16) + r1;
    }

    @Override // j$.time.temporal.o
    public final Temporal s(Temporal r6) {
        j$.time.chrono.k r0 = (j$.time.chrono.k) r6.d(j$.time.temporal.q.f464b);
        if (r0 != null) goto L5;
    L9:
        int r02 = this.f307b;
        int r1 = this.f306a;
        if (r02 != 0) goto L13;
        if (r1 == 0) goto L16;
        r6 = r6.b(r1, ChronoUnit.YEARS);
    L16:
        int r5 = this.f308c;
        if (r5 != 0) goto L19;
        return r6;
    L19:
        return r6.b(r5, ChronoUnit.DAYS);
    L13:
        long r12 = (r1 * 12) + r02;
        if (r12 == 0) goto L16;
        r6 = r6.b(r12, ChronoUnit.MONTHS);
        goto L16
    L5:
        if (j$.time.chrono.r.f353c.equals(r0) == true) goto L9;
        throw new b("Chronology mismatch, expected: ISO, actual: " + r0.getId());
    }

    public final String toString() {
        if (this != d) goto L6;
        return "P0D";
    L6:
        StringBuilder r0 = new StringBuilder("P");
        int r1 = this.f306a;
        if (r1 == 0) goto L9;
        r0.append(r1);
        r0.append('Y');
    L9:
        int r12 = this.f307b;
        if (r12 == 0) goto L12;
        r0.append(r12);
        r0.append('M');
    L12:
        int r2 = this.f308c;
        if (r2 == 0) goto L16;
        r0.append(r2);
        r0.append('D');
    L16:
        return r0.toString();
    }
}
