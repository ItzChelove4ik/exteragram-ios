package j$.time.chrono;

import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.ZoneId;
import j$.time.temporal.ChronoUnit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class u extends a implements Serializable {

    /* renamed from: c, reason: collision with root package name */
    public static final u f356c = null;
    private static final long serialVersionUID = 459996390165777884L;

    static {
        f356c = new u();
    }

    private u() {
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.chrono.k
    public final j$.time.temporal.s B(j$.time.temporal.a r8) {
        int r0 = 0;
        switch(t.f355a[r8.ordinal()]) {
            case 1: goto L28;
            case 2: goto L28;
            case 3: goto L28;
            case 4: goto L28;
            case 5: goto L22;
            case 6: goto L10;
            case 7: goto L9;
            case 8: goto L6;
            default: goto L5;
        };
    L6:
        long r7 = x.d.f361a;
        x[] r02 = x.e;
        return j$.time.temporal.s.f(r7, r02[r02.length - 1].f361a);
    L10:
        x r72 = x.d;
        long r73 = j$.time.temporal.a.DAY_OF_YEAR.f447b.f468c;
        x[] r2 = x.e;
        int r3 = r2.length;
    L11:
        if (r0 >= r3) goto L21;
        x r4 = r2[r0];
        if (r4.f362b.c0() == false) goto L15;
        int r5 = 366;
    L16:
        r73 = Math.min(r73, (r5 - r4.f362b.Y()) + 1);
        if (r4.q() == null) goto L19;
        r73 = Math.min(r73, r4.q().f362b.Y() - 1);
    L19:
        r0 = r0 + 1;
        goto L11
    L15:
        r5 = 365;
        goto L16
    L21:
        return j$.time.temporal.s.g(r73, j$.time.temporal.a.DAY_OF_YEAR.f447b.d);
    L22:
        x[] r74 = x.e;
        int r82 = r74[r74.length - 1].f362b.getYear();
        int r32 = 1000000000 - r74[r74.length - 1].f362b.getYear();
        int r75 = r74[0].f362b.getYear();
        int r03 = 1;
    L23:
        x[] r22 = x.e;
        if (r03 >= r22.length) goto L27;
        x r23 = r22[r03];
        r32 = Math.min(r32, (r23.f362b.getYear() - r75) + 1);
        r75 = r23.f362b.getYear();
        r03 = r03 + 1;
        goto L23
    L27:
        return j$.time.temporal.s.g(r32, 999999999 - r82);
    L28:
        j$.nio.file.b.d("Unsupported field: ", r8);
        return null;
    L5:
        return r8.f447b;
    L9:
        return j$.time.temporal.s.f(w.d.getYear(), 999999999);
    }

    @Override // j$.time.chrono.k
    public final List C() {
        x[] r1 = x.e;
        return j$.desugar.sun.nio.fs.g.p((x[]) Arrays.copyOf(r1, r1.length));
    }

    @Override // j$.time.chrono.k
    public final l E(int r1) {
        return x.r(r1);
    }

    @Override // j$.time.chrono.k
    public final int F(l r3, int r4) {
        if ((r3 instanceof x) == false) goto L19;
        x r2 = (x) r3;
        int r0 = (r2.f362b.getYear() + r4) - 1;
        if (r4 != 1) goto L8;
    L15:
        return r0;
    L8:
        if (r0 >= (-999999999)) goto L10;
    L16:
        j$.nio.file.b.h("Invalid yearOfEra value");
        return 0;
    L10:
        if (r0 > 999999999) goto L16;
        if (r0 < r2.f362b.getYear()) goto L16;
        if (r3 != x.p(LocalDate.of(r0, 1, 1))) goto L16;
    L19:
        throw new ClassCastException("Era must be JapaneseEra");
    }

    @Override // j$.time.chrono.k
    public final b J(j$.time.temporal.l r1) {
        if ((r1 instanceof w) == false) goto L7;
        return (w) r1;
    L7:
        return new w(LocalDate.I(r1));
    }

    @Override // j$.time.chrono.k
    public final b N() {
        return new w(LocalDate.I(LocalDate.f0(j$.desugar.sun.nio.fs.g.A())));
    }

    @Override // j$.time.chrono.k
    public final b T(int r1, int r2, int r3) {
        return new w(LocalDate.of(r1, r2, r3));
    }

    @Override // j$.time.chrono.a, j$.time.chrono.k
    public final b V(Map r1, j$.time.format.z r2) {
        return (w) super.V(r1, r2);
    }

    @Override // j$.time.chrono.k
    public final ChronoZonedDateTime W(Instant r1, ZoneId r2) {
        return j.I(this, r1, r2);
    }

    @Override // j$.time.chrono.a
    public final b Y(Map r12, j$.time.format.z r13) {
        j$.time.temporal.a r0 = j$.time.temporal.a.ERA;
        Long r1 = (Long) r12.get(r0);
        if (r1 == null) goto L5;
        x r14 = x.r(B(r0).a(r1.longValue(), r0));
    L6:
        j$.time.temporal.a r3 = j$.time.temporal.a.YEAR_OF_ERA;
        Long r4 = (Long) r12.get(r3);
        if (r4 == null) goto L9;
        int r5 = B(r3).a(r4.longValue(), r3);
    L11:
        if (r14 != null) goto L18;
        if (r4 == null) goto L18;
        if (r12.containsKey(j$.time.temporal.a.YEAR) == true) goto L18;
        if (r13 == j$.time.format.z.STRICT) goto L18;
        x[] r15 = x.e;
        r14 = ((x[]) Arrays.copyOf(r15, r15.length))[((x[]) Arrays.copyOf(r15, r15.length)).length - 1];
    L18:
        if (r4 == null) goto L71;
        if (r14 == null) goto L71;
        j$.time.temporal.a r42 = j$.time.temporal.a.MONTH_OF_YEAR;
        if (r12.containsKey(r42) == false) goto L54;
        j$.time.temporal.a r7 = j$.time.temporal.a.DAY_OF_MONTH;
        if (r12.containsKey(r7) == false) goto L54;
        r12.remove(r0);
        r12.remove(r3);
        if (r13 != j$.time.format.z.LENIENT) goto L28;
        int r11 = (r14.f362b.getYear() + r5) - 1;
        long r02 = Math.subtractExact(((Long) r12.remove(r42)).longValue(), 1);
        return new w(LocalDate.of(r11, 1, 1)).Y(r02, ChronoUnit.MONTHS).Y(Math.subtractExact(((Long) r12.remove(r7)).longValue(), 1), ChronoUnit.DAYS);
    L28:
        int r03 = B(r42).a(((Long) r12.remove(r42)).longValue(), r42);
        int r112 = B(r7).a(((Long) r12.remove(r7)).longValue(), r7);
        if (r13 != j$.time.format.z.SMART) goto L46;
        if (r5 < 1) goto L44;
        int r122 = (r14.f362b.getYear() + r5) - 1;
        w r132 = new w(LocalDate.of(r122, r03, r112));     // Catch: j$.time.b -> L34
    L36:
        if (r132.f359b != r14) goto L38;
    L43:
        return r132;
    L38:
        if (r132.g(j$.time.temporal.a.YEAR_OF_ERA) <= 1) goto L43;
        if (r5 <= 1) goto L43;
        throw new j$.time.b("Invalid YearOfEra for Era: " + r14 + " " + r5);
    L34:
        r132 = new w(LocalDate.of(r122, r03, 1)).b0(new j$.time.e(3));
        goto L36
    L44:
        j$.nio.file.b.b("Invalid YearOfEra: ", r5);
        return null;
    L46:
        LocalDate r123 = w.d;
        LocalDate r113 = LocalDate.of((r14.f362b.getYear() + r5) - 1, r03, r112);
        if (r113.b0(r14.f362b) == false) goto L49;
    L52:
        j$.nio.file.b.h("year, month, and day not valid for Era");
        return null;
    L49:
        if (r14 != x.p(r113)) goto L52;
        return new w(r14, r5, r113);
    L54:
        j$.time.temporal.a r43 = j$.time.temporal.a.DAY_OF_YEAR;
        if (r12.containsKey(r43) == false) goto L71;
        r12.remove(r0);
        r12.remove(r3);
        if (r13 != j$.time.format.z.LENIENT) goto L60;
        int r114 = (r14.f362b.getYear() + r5) - 1;
        return new w(LocalDate.h0(r114, 1)).Y(Math.subtractExact(((Long) r12.remove(r43)).longValue(), 1), ChronoUnit.DAYS);
    L60:
        int r115 = B(r43).a(((Long) r12.remove(r43)).longValue(), r43);
        LocalDate r124 = w.d;
        LocalDate r125 = r14.f362b;
        if (r5 != 1) goto L63;
        LocalDate r116 = LocalDate.h0(r125.getYear(), (r14.f362b.Y() + r115) - 1);
    L65:
        if (r116.b0(r14.f362b) == false) goto L67;
    L70:
        j$.nio.file.b.h("Invalid parameters");
        goto L71
    L67:
        if (r14 != x.p(r116)) goto L70;
        return new w(r14, r5, r116);
    L63:
        r116 = LocalDate.h0((r125.getYear() + r5) - 1, r115);
    L71:
        return null;
    L9:
        r5 = 0;
        goto L11
    L5:
        r14 = null;
        goto L6
    }

    @Override // j$.time.chrono.k
    public final String getId() {
        return "Japanese";
    }

    @Override // j$.time.chrono.k
    public final b p(long r1) {
        return new w(LocalDate.g0(r1));
    }

    @Override // j$.time.chrono.k
    public final String v() {
        return "japanese";
    }

    public Object writeReplace() {
        return new d0((byte) 1, this);
    }

    @Override // j$.time.chrono.k
    public final b x(int r1, int r2) {
        return new w(LocalDate.h0(r1, r2));
    }
}
