package j$.time.chrono;

import j$.time.DayOfWeek;
import j$.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract class a implements k {

    /* renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap f322a = null;

    /* renamed from: b, reason: collision with root package name */
    public static final ConcurrentHashMap f323b = null;

    static {
        f322a = new ConcurrentHashMap();
        f323b = new ConcurrentHashMap();
        new Locale("ja", "JP", "JP");
    }

    public a() {
    }

    public static k D(k r2, String r3) {
        k r32 = (k) f322a.putIfAbsent(r3, r2);
        if (r32 != null) goto L7;
        String r0 = r2.v();
        if (r0 == null) goto L7;
        f323b.putIfAbsent(r0, r2);
    L7:
        return r32;
    }

    public static b I(b r4, long r5, long r7, long r9) {
        b r42 = r4.b(r5, ChronoUnit.MONTHS);
        ChronoUnit r52 = ChronoUnit.WEEKS;
        b r43 = r42.b(r7, r52);
        if (r9 <= 7) goto L7;
        long r92 = r9 - 1;
        r43 = r43.b(r92 / 7, r52);
        long r93 = r92 % 7;
    L5:
        r9 = r93 + 1;
    L10:
        return r43.j(new j$.time.temporal.n(DayOfWeek.s((int) r9).getValue(), 0));
    L7:
        if (r9 >= 1) goto L10;
        r43 = r43.b(Math.subtractExact(r9, 7) / 7, r52);
        r93 = (r9 + 6) % 7;
        goto L5
    }

    public static void s(Map r3, j$.time.temporal.a r4, long r5) {
        Long r0 = (Long) r3.get(r4);
        if (r0 != null) goto L5;
    L9:
        r3.put(r4, Long.valueOf(r5));
        return;
    L5:
        if (r0.longValue() == r5) goto L9;
        throw new j$.time.b("Conflict found: " + r4 + " " + r0 + " differs from " + r4 + " " + r5);
    }

    public void K(Map r5, j$.time.format.z r6) {
        j$.time.temporal.a r0 = j$.time.temporal.a.PROLEPTIC_MONTH;
        Long r1 = (Long) r5.remove(r0);
        if (r1 != null) goto L5;
        return;
    L5:
        if (r6 == j$.time.format.z.LENIENT) goto L7;
        r0.a0(r1.longValue());
    L7:
        b r4 = N().a(1, j$.time.temporal.a.DAY_OF_MONTH).a(r1.longValue(), r0);
        s(r5, j$.time.temporal.a.MONTH_OF_YEAR, r4.g(r6));
        s(r5, j$.time.temporal.a.YEAR, r4.g(r6));
    }

    public b S(Map r8, j$.time.format.z r9) {
        j$.time.temporal.a r0 = j$.time.temporal.a.YEAR;
        int r02 = B(r0).a(((Long) r8.remove(r0)).longValue(), r0);
        if (r9 != j$.time.format.z.LENIENT) goto L6;
        long r3 = Math.subtractExact(((Long) r8.remove(j$.time.temporal.a.MONTH_OF_YEAR)).longValue(), 1);
        long r82 = Math.subtractExact(((Long) r8.remove(j$.time.temporal.a.DAY_OF_MONTH)).longValue(), 1);
        return T(r02, 1, 1).b(r3, ChronoUnit.MONTHS).b(r82, ChronoUnit.DAYS);
    L6:
        j$.time.temporal.a r1 = j$.time.temporal.a.MONTH_OF_YEAR;
        int r12 = B(r1).a(((Long) r8.remove(r1)).longValue(), r1);
        j$.time.temporal.a r32 = j$.time.temporal.a.DAY_OF_MONTH;
        int r83 = B(r32).a(((Long) r8.remove(r32)).longValue(), r32);
        if (r9 != j$.time.format.z.SMART) goto L13;
        return T(r02, r12, r83);
    L11:
        return this.T(r02, r12, 1).j(new j$.time.e(3));
    L13:
        return T(r02, r12, r83);
    }

    @Override // j$.time.chrono.k
    public b V(Map r20, j$.time.format.z r21) {
        j$.time.temporal.a r3 = j$.time.temporal.a.EPOCH_DAY;
        if (r20.containsKey(r3) == true) goto L5;
        K(r20, r21);
        b r32 = Y(r20, r21);
        if (r32 == null) goto L9;
        return r32;
    L9:
        j$.time.temporal.a r33 = j$.time.temporal.a.YEAR;
        if (r20.containsKey(r33) == false) goto L85;
        j$.time.temporal.a r4 = j$.time.temporal.a.MONTH_OF_YEAR;
        if (r20.containsKey(r4) == true) goto L14;
    L47:
        j$.time.temporal.a r42 = j$.time.temporal.a.DAY_OF_YEAR;
        if (r20.containsKey(r42) == false) goto L55;
        int r34 = B(r33).a(((Long) r20.remove(r33)).longValue(), r33);
        if (r21 != j$.time.format.z.LENIENT) goto L54;
        return x(r34, 1).b(Math.subtractExact(((Long) r20.remove(r42)).longValue(), 1), ChronoUnit.DAYS);
    L54:
        return x(r34, B(r42).a(((Long) r20.remove(r42)).longValue(), r42));
    L55:
        j$.time.temporal.a r43 = j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR;
        if (r20.containsKey(r43) == false) goto L85;
        j$.time.temporal.a r6 = j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR;
        if (r20.containsKey(r6) == false) goto L71;
        int r7 = B(r33).a(((Long) r20.remove(r33)).longValue(), r33);
        if (r21 != j$.time.format.z.LENIENT) goto L63;
        long r2 = Math.subtractExact(((Long) r20.remove(r43)).longValue(), 1);
        return x(r7, 1).b(r2, ChronoUnit.WEEKS).b(Math.subtractExact(((Long) r20.remove(r6)).longValue(), 1), ChronoUnit.DAYS);
    L63:
        int r44 = B(r43).a(((Long) r20.remove(r43)).longValue(), r43);
        int r1 = B(r6).a(((Long) r20.remove(r6)).longValue(), r6) - 1;
        b r0 = x(r7, 1).b(r1 + ((r44 - 1) * 7), ChronoUnit.DAYS);
        if (r21 == j$.time.format.z.STRICT) goto L66;
    L70:
        return r0;
    L66:
        if (r0.g(r33) == r7) goto L70;
        j$.nio.file.b.h("Strict mode rejected resolved date as it is in a different year");
        return null;
    L71:
        j$.time.temporal.a r62 = j$.time.temporal.a.DAY_OF_WEEK;
        if (r20.containsKey(r62) == false) goto L85;
        int r11 = B(r33).a(((Long) r20.remove(r33)).longValue(), r33);
        if (r21 == j$.time.format.z.LENIENT) goto L76;
        int r45 = B(r43).a(((Long) r20.remove(r43)).longValue(), r43);
        b r02 = x(r11, 1).b((r45 - 1) * 7, ChronoUnit.DAYS).j(new j$.time.temporal.n(DayOfWeek.s(B(r62).a(((Long) r20.remove(r62)).longValue(), r62)).getValue(), 0));
        if (r21 == j$.time.format.z.STRICT) goto L80;
    L84:
        return r02;
    L80:
        if (r02.g(r33) == r11) goto L84;
        j$.nio.file.b.h("Strict mode rejected resolved date as it is in a different year");
        return null;
    L76:
        return I(x(r11, 1), 0, Math.subtractExact(((Long) r20.remove(r43)).longValue(), 1), Math.subtractExact(((Long) r20.remove(r62)).longValue(), 1));
    L14:
        if (r20.containsKey(j$.time.temporal.a.DAY_OF_MONTH) == true) goto L16;
        j$.time.temporal.a r63 = j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH;
        if (r20.containsKey(r63) == false) goto L47;
        j$.time.temporal.a r112 = j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH;
        if (r20.containsKey(r112) == false) goto L33;
        int r35 = B(r33).a(((Long) r20.remove(r33)).longValue(), r33);
        if (r21 != j$.time.format.z.LENIENT) goto L25;
        long r46 = Math.subtractExact(((Long) r20.remove(r4)).longValue(), 1);
        long r64 = Math.subtractExact(((Long) r20.remove(r63)).longValue(), 1);
        return T(r35, 1, 1).b(r46, ChronoUnit.MONTHS).b(r64, ChronoUnit.WEEKS).b(Math.subtractExact(((Long) r20.remove(r112)).longValue(), 1), ChronoUnit.DAYS);
    L25:
        int r72 = B(r4).a(((Long) r20.remove(r4)).longValue(), r4);
        int r65 = B(r63).a(((Long) r20.remove(r63)).longValue(), r63);
        int r12 = B(r112).a(((Long) r20.remove(r112)).longValue(), r112) - 1;
        b r03 = T(r35, r72, 1).b(r12 + ((r65 - 1) * 7), ChronoUnit.DAYS);
        if (r21 == j$.time.format.z.STRICT) goto L28;
    L32:
        return r03;
    L28:
        if (r03.g(r4) == r72) goto L32;
        j$.nio.file.b.h("Strict mode rejected resolved date as it is in a different month");
        return null;
    L33:
        j$.time.temporal.a r113 = j$.time.temporal.a.DAY_OF_WEEK;
        if (r20.containsKey(r113) == false) goto L47;
        int r36 = B(r33).a(((Long) r20.remove(r33)).longValue(), r33);
        if (r21 == j$.time.format.z.LENIENT) goto L38;
        int r9 = B(r4).a(((Long) r20.remove(r4)).longValue(), r4);
        int r66 = B(r63).a(((Long) r20.remove(r63)).longValue(), r63);
        b r04 = T(r36, r9, 1).b((r66 - 1) * 7, ChronoUnit.DAYS).j(new j$.time.temporal.n(DayOfWeek.s(B(r113).a(((Long) r20.remove(r113)).longValue(), r113)).getValue(), 0));
        if (r21 == j$.time.format.z.STRICT) goto L42;
    L46:
        return r04;
    L42:
        if (r04.g(r4) == r9) goto L46;
        j$.nio.file.b.h("Strict mode rejected resolved date as it is in a different month");
        return null;
    L38:
        return I(T(r36, 1, 1), Math.subtractExact(((Long) r20.remove(r4)).longValue(), 1), Math.subtractExact(((Long) r20.remove(r63)).longValue(), 1), Math.subtractExact(((Long) r20.remove(r113)).longValue(), 1));
    L16:
        return S(r20, r21);
    L85:
        return null;
    L5:
        return p(((Long) r20.remove(r3)).longValue());
    }

    public b Y(Map r8, j$.time.format.z r9) {
        j$.time.temporal.a r0 = j$.time.temporal.a.YEAR_OF_ERA;
        Long r1 = (Long) r8.remove(r0);
        if (r1 == null) goto L20;
        j$.time.temporal.a r2 = j$.time.temporal.a.ERA;
        Long r3 = (Long) r8.remove(r2);
        if (r9 == j$.time.format.z.LENIENT) goto L7;
        int r4 = B(r0).a(r1.longValue(), r0);
    L8:
        if (r3 == null) goto L10;
        l r92 = E(B(r2).a(r3.longValue(), r2));
        s(r8, j$.time.temporal.a.YEAR, F(r92, r4));
        return null;
    L10:
        j$.time.temporal.a r22 = j$.time.temporal.a.YEAR;
        if (r8.containsKey(r22) == false) goto L14;
        s(r8, r22, F(x(B(r22).a(((Long) r8.get(r22)).longValue(), r22), 1).P(), r4));
        return null;
    L14:
        if (r9 != j$.time.format.z.STRICT) goto L17;
        r8.put(r0, r1);
        return null;
    L17:
        if (C().isEmpty() == false) goto L19;
        s(r8, r22, r4);
        return null;
    L19:
        s(r8, r22, F((l) r9.get(r9.size() - 1), r4));
        return null;
    L7:
        r4 = Math.toIntExact(r1.longValue());
        goto L8
    L20:
        j$.time.temporal.a r93 = j$.time.temporal.a.ERA;
        if (r8.containsKey(r93) == false) goto L30;
        B(r93).b(((Long) r8.get(r93)).longValue(), r93);
        return null;
    L30:
        return null;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object r1) {
        return getId().compareTo(((k) r1).getId());
    }

    @Override // j$.time.chrono.k
    public final boolean equals(Object r2) {
        if (this != r2) goto L5;
        return true;
    L5:
        if ((r2 instanceof a) == true) goto L7;
        return false;
    L7:
        if (getId().compareTo(((a) r2).getId()) != 0) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // j$.time.chrono.k
    public final int hashCode() {
        int r0 = getClass().hashCode();
        return getId().hashCode() ^ r0;
    }

    @Override // j$.time.chrono.k
    public final String toString() {
        return getId();
    }
}
