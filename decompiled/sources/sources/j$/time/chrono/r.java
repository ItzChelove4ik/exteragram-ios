package j$.time.chrono;

import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.LocalDateTime;
import j$.time.Year;
import j$.time.ZoneId;
import j$.time.ZonedDateTime;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class r extends a implements Serializable {

    /* renamed from: c, reason: collision with root package name */
    public static final r f353c = null;
    private static final long serialVersionUID = -1440403870442975015L;

    static {
        f353c = new r();
    }

    private r() {
    }

    public static boolean a0(long r4) {
        if ((3 & r4) == 0) goto L5;
        return false;
    L5:
        if ((r4 % 100) == 0) goto L7;
        return true;
    L7:
        if ((r4 % 400) != 0) goto L13;
        return true;
    L13:
        return false;
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.chrono.k
    public final j$.time.temporal.s B(j$.time.temporal.a r1) {
        return r1.f447b;
    }

    @Override // j$.time.chrono.k
    public final List C() {
        return j$.desugar.sun.nio.fs.g.p(s.values());
    }

    @Override // j$.time.chrono.k
    public final l E(int r1) {
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L6;
        j$.nio.file.b.b("Invalid era: ", r1);
        return null;
    L6:
        return s.CE;
    L10:
        return s.BCE;
    }

    @Override // j$.time.chrono.k
    public final int F(l r1, int r2) {
        if ((r1 instanceof s) == false) goto L10;
        if (r1 != s.CE) goto L8;
        return r2;
    L8:
        return 1 - r2;
    L10:
        throw new ClassCastException("Era must be IsoEra");
    }

    @Override // j$.time.chrono.k
    public final b J(j$.time.temporal.l r1) {
        return LocalDate.I(r1);
    }

    @Override // j$.time.chrono.a
    public final void K(Map r6, j$.time.format.z r7) {
        j$.time.temporal.a r5 = j$.time.temporal.a.PROLEPTIC_MONTH;
        Long r0 = (Long) r6.remove(r5);
        if (r0 != null) goto L5;
        return;
    L5:
        if (r7 == j$.time.format.z.LENIENT) goto L7;
        r5.a0(r0.longValue());
    L7:
        a.s(r6, j$.time.temporal.a.MONTH_OF_YEAR, ((int) Math.floorMod(r0.longValue(), 12)) + 1);
        a.s(r6, j$.time.temporal.a.YEAR, Math.floorDiv(r0.longValue(), 12));
    }

    @Override // j$.time.chrono.k
    public final b N() {
        return LocalDate.I(LocalDate.f0(j$.desugar.sun.nio.fs.g.A()));
    }

    @Override // j$.time.chrono.k
    public final ChronoLocalDateTime O(j$.time.temporal.l r1) {
        return LocalDateTime.D(r1);
    }

    @Override // j$.time.chrono.a
    public final b S(Map r9, j$.time.format.z r10) {
        j$.time.temporal.a r8 = j$.time.temporal.a.YEAR;
        int r82 = r8.f447b.a(((Long) r9.remove(r8)).longValue(), r8);
        boolean r1 = true;
        if (r10 != j$.time.format.z.LENIENT) goto L6;
        long r2 = Math.subtractExact(((Long) r9.remove(j$.time.temporal.a.MONTH_OF_YEAR)).longValue(), 1);
        return LocalDate.of(r82, 1, 1).j0(r2).plusDays(Math.subtractExact(((Long) r9.remove(j$.time.temporal.a.DAY_OF_MONTH)).longValue(), 1));
    L6:
        j$.time.temporal.a r0 = j$.time.temporal.a.MONTH_OF_YEAR;
        int r02 = r0.f447b.a(((Long) r9.remove(r0)).longValue(), r0);
        j$.time.temporal.a r22 = j$.time.temporal.a.DAY_OF_MONTH;
        int r92 = r22.f447b.a(((Long) r9.remove(r22)).longValue(), r22);
        if (r10 != j$.time.format.z.SMART) goto L30;
        if (r02 != 4) goto L11;
    L28:
        r92 = Math.min(r92, 30);
        goto L30
    L11:
        if (r02 == 6) goto L28;
        if (r02 == 9) goto L28;
        if (r02 == 11) goto L28;
        if (r02 != 2) goto L30;
        j$.time.k r102 = j$.time.k.FEBRUARY;
        long r23 = r82;
        int r4 = Year.f309b;
        if ((3 & r23) == 0) goto L22;
    L26:
        r1 = false;
    L27:
        r92 = Math.min(r92, r102.D(r1));
        goto L30
    L22:
        if ((r23 % 100) != 0) goto L27;
        if ((r23 % 400) != 0) goto L26;
    L30:
        return LocalDate.of(r82, r02, r92);
    }

    @Override // j$.time.chrono.k
    public final b T(int r1, int r2, int r3) {
        return LocalDate.of(r1, r2, r3);
    }

    @Override // j$.time.chrono.a, j$.time.chrono.k
    public final b V(Map r1, j$.time.format.z r2) {
        return (LocalDate) super.V(r1, r2);
    }

    @Override // j$.time.chrono.k
    public final ChronoZonedDateTime W(Instant r3, ZoneId r4) {
        Objects.requireNonNull(r3, "instant");
        Objects.requireNonNull(r4, "zone");
        return ZonedDateTime.s(r3.getEpochSecond(), r3.getNano(), r4);
    }

    @Override // j$.time.chrono.a
    public final b Y(Map r10, j$.time.format.z r11) {
        j$.time.temporal.a r9 = j$.time.temporal.a.YEAR_OF_ERA;
        Long r0 = (Long) r10.remove(r9);
        if (r0 != null) goto L5;
        j$.time.temporal.a r92 = j$.time.temporal.a.ERA;
        if (r10.containsKey(r92) == false) goto L36;
        r92.a0(((Long) r10.get(r92)).longValue());
    L36:
        return null;
    L5:
        if (r11 == j$.time.format.z.LENIENT) goto L7;
        r9.a0(r0.longValue());
    L7:
        Long r2 = (Long) r10.remove(j$.time.temporal.a.ERA);
        if (r2 != null) goto L26;
        j$.time.temporal.a r22 = j$.time.temporal.a.YEAR;
        Long r7 = (Long) r10.get(r22);
        if (r11 != j$.time.format.z.STRICT) goto L18;
        if (r7 == null) goto L17;
        long r72 = r7.longValue();
        long r3 = r0.longValue();
        if (r72 > 0) goto L16;
        r3 = Math.subtractExact(1, r3);
    L16:
        a.s(r10, r22, r3);
        goto L36
    L17:
        r10.put(r9, r0);
        goto L36
    L18:
        if (r7 != null) goto L20;
    L23:
        long r32 = r0.longValue();
    L24:
        a.s(r10, r22, r32);
        goto L36
    L20:
        if (r7.longValue() > 0) goto L23;
        r32 = Math.subtractExact(1, r0.longValue());
        goto L24
    L26:
        if (r2.longValue() != 1) goto L29;
        a.s(r10, j$.time.temporal.a.YEAR, r0.longValue());
        goto L36
    L29:
        if (r2.longValue() != 0) goto L31;
        a.s(r10, j$.time.temporal.a.YEAR, Math.subtractExact(1, r0.longValue()));
        goto L36
    L31:
        j$.nio.file.b.j("Invalid value for era: ", r2);
        return null;
    }

    @Override // j$.time.chrono.k
    public final String getId() {
        return "ISO";
    }

    @Override // j$.time.chrono.k
    public final b p(long r1) {
        return LocalDate.g0(r1);
    }

    @Override // j$.time.chrono.k
    public final String v() {
        return "iso8601";
    }

    @Override // j$.time.chrono.k
    public final ChronoZonedDateTime w(j$.time.temporal.l r1) {
        return ZonedDateTime.D(r1);
    }

    public Object writeReplace() {
        return new d0((byte) 1, this);
    }

    @Override // j$.time.chrono.k
    public final b x(int r1, int r2) {
        return LocalDate.h0(r1, r2);
    }
}
