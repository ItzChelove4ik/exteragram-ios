package j$.time.format;

import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.Period;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class y implements j$.time.temporal.l {

    /* renamed from: a, reason: collision with root package name */
    public final Map f418a;

    /* renamed from: b, reason: collision with root package name */
    public ZoneId f419b;

    /* renamed from: c, reason: collision with root package name */
    public j$.time.chrono.k f420c;
    public boolean d;
    public z e;
    public j$.time.chrono.b f;
    public LocalTime g;
    public Period h;

    public y() {
        this.f418a = new HashMap();
        this.h = Period.d;
    }

    @Override // j$.time.temporal.l
    public final Object d(j$.time.e r3) {
        if (r3 != j$.time.temporal.q.f463a) goto L7;
        return this.f419b;
    L7:
        if (r3 != j$.time.temporal.q.f464b) goto L11;
        return this.f420c;
    L11:
        if (r3 != j$.time.temporal.q.f) goto L17;
        j$.time.chrono.b r2 = this.f;
        if (r2 != null) goto L15;
        return null;
    L15:
        return LocalDate.I(r2);
    L17:
        if (r3 != j$.time.temporal.q.g) goto L21;
        return this.g;
    L21:
        if (r3 != j$.time.temporal.q.d) goto L32;
        Map r0 = this.f418a;
        HashMap r02 = (HashMap) r0;
        Long r03 = (Long) r02.get(j$.time.temporal.a.OFFSET_SECONDS);
        if (r03 != null) goto L25;
        ZoneId r04 = this.f419b;
        if ((r04 instanceof ZoneOffset) == false) goto L30;
        return r04;
    L30:
        return r3.l(this);
    L25:
        return ZoneOffset.ofTotalSeconds(r03.intValue());
    L32:
        if (r3 != j$.time.temporal.q.e) goto L36;
        return r3.l(this);
    L36:
        if (r3 != j$.time.temporal.q.f465c) goto L40;
        return null;
    L40:
        return r3.l(this);
    }

    @Override // j$.time.temporal.l
    public final boolean i(j$.time.temporal.p r2) {
        if (((HashMap) this.f418a).containsKey(r2) == true) goto L21;
        j$.time.chrono.b r0 = this.f;
        if (r0 != null) goto L7;
    L8:
        LocalTime r02 = this.g;
        if (r02 != null) goto L11;
    L13:
        if (r2 != null) goto L15;
        return false;
    L15:
        if ((r2 instanceof j$.time.temporal.a) == false) goto L17;
        return false;
    L17:
        if (r2.s(this) == true) goto L27;
        return false;
    L27:
        return true;
    L11:
        if (r02.i(r2) == false) goto L13;
        return true;
    L7:
        if (r0.i(r2) == false) goto L8;
        return true;
    L21:
        return true;
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r2) {
        Objects.requireNonNull(r2, "field");
        Long r0 = (Long) ((HashMap) this.f418a).get(r2);
        if (r0 != null) goto L5;
        j$.time.chrono.b r02 = this.f;
        if (r02 != null) goto L9;
    L12:
        LocalTime r03 = this.g;
        if (r03 == null) goto L19;
        if (r03.i(r2) == false) goto L19;
        return this.g.k(r2);
    L19:
        if ((r2 instanceof j$.time.temporal.a) == true) goto L23;
        return r2.S(this);
    L23:
        throw new j$.time.temporal.r(j$.time.c.a("Unsupported field: ", r2));
    L9:
        if (r02.i(r2) == false) goto L12;
        return this.f.k(r2);
    L5:
        return r0.longValue();
    }

    public final void p(j$.time.temporal.l r8) {
        Iterator r7 = ((HashMap) this.f418a).entrySet().iterator();
    L4:
        if (r7.hasNext() == false) goto L13;
        Map.Entry r0 = (Map.Entry) r7.next();
        j$.time.temporal.p r1 = (j$.time.temporal.p) r0.getKey();
        if (r8.i(r1) == false) goto L4;
        long r2 = r8.k(r1);     // Catch: RuntimeException -> L14
        long r4 = ((Long) r0.getValue()).longValue();
        if (r2 != r4) goto L12;
        r7.remove();
        goto L4
    L12:
        throw new j$.time.b("Conflict found: Field " + r1 + " " + r2 + " differs from " + r1 + " " + r4 + " derived from " + r8);
    }

    public final void q() {
        Map r0 = this.f418a;
        HashMap r02 = (HashMap) r0;
        if (r02.containsKey(j$.time.temporal.a.INSTANT_SECONDS) == false) goto L12;
        ZoneId r03 = this.f419b;
        if (r03 == null) goto L8;
        r(r03);
        return;
    L8:
        Map r04 = this.f418a;
        HashMap r05 = (HashMap) r04;
        Long r06 = (Long) r05.get(j$.time.temporal.a.OFFSET_SECONDS);
        if (r06 == null) goto L13;
        r(ZoneOffset.ofTotalSeconds(r06.intValue()));
        return;
    L13:
        return;
    }

    public final void r(ZoneId r5) {
        Map r0 = this.f418a;
        j$.time.temporal.a r1 = j$.time.temporal.a.INSTANT_SECONDS;
        w(this.f420c.W(Instant.s(((Long) ((HashMap) r0).remove(r1)).longValue(), 0), r5).m());
        x(r1, j$.time.temporal.a.SECOND_OF_DAY, Long.valueOf(r5.toLocalTime().g0()));
    }

    public final void s(long r4, long r6, long r8, long r10) {
        if (this.e != z.LENIENT) goto L6;
        long r42 = Math.addExact(Math.addExact(Math.addExact(Math.multiplyExact(r4, 3600000000000L), Math.multiplyExact(r6, 60000000000L)), Math.multiplyExact(r8, 1000000000)), r10);
        int r82 = (int) Math.floorDiv(r42, 86400000000000L);
        v(LocalTime.S(Math.floorMod(r42, 86400000000000L)), Period.a(0, 0, r82));
        return;
    L6:
        j$.time.temporal.a r0 = j$.time.temporal.a.MINUTE_OF_HOUR;
        int r62 = r0.f447b.a(r6, r0);
        j$.time.temporal.a r7 = j$.time.temporal.a.NANO_OF_SECOND;
        int r72 = r7.f447b.a(r10, r7);
        if (this.e == z.SMART) goto L9;
    L16:
        j$.time.temporal.a r102 = j$.time.temporal.a.HOUR_OF_DAY;
        int r43 = r102.f447b.a(r4, r102);
        j$.time.temporal.a r5 = j$.time.temporal.a.SECOND_OF_MINUTE;
        v(LocalTime.of(r43, r62, r5.f447b.a(r8, r5), r72), Period.d);
        return;
    L9:
        if (r4 != 24) goto L16;
        if (r62 != 0) goto L16;
        if (r8 != 0) goto L16;
        if (r72 != 0) goto L16;
        v(LocalTime.g, Period.a(0, 0, 1));
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder(64);
        r0.append(this.f418a);
        r0.append(',');
        r0.append(this.f420c);
        if (this.f419b == null) goto L6;
        r0.append(',');
        r0.append(this.f419b);
    L6:
        if (this.f == null) goto L8;
    L9:
        r0.append(" resolved to ");
        j$.time.chrono.b r1 = this.f;
        if (r1 == null) goto L14;
        r0.append(r1);
        if (this.g == null) goto L16;
        r0.append('T');
        r0.append(this.g);
    L16:
        return r0.toString();
    L14:
        r0.append(this.g);
        goto L16
    L8:
        if (this.g == null) goto L16;
        goto L9
    }

    public final void u() {
        Map r0 = this.f418a;
        j$.time.temporal.a r1 = j$.time.temporal.a.CLOCK_HOUR_OF_DAY;
        long r2 = 0;
        if (((HashMap) r0).containsKey(r1) == false) goto L15;
        long r4 = ((Long) ((HashMap) this.f418a).remove(r1)).longValue();
        z r02 = this.e;
        if (r02 != z.STRICT) goto L7;
    L10:
        r1.a0(r4);
    L11:
        j$.time.temporal.a r03 = j$.time.temporal.a.HOUR_OF_DAY;
        if (r4 != 24) goto L14;
        r4 = 0;
    L14:
        x(r1, r03, Long.valueOf(r4));
        goto L15
    L7:
        if (r02 != z.SMART) goto L11;
        if (r4 == 0) goto L11;
    L15:
        Map r04 = this.f418a;
        j$.time.temporal.a r12 = j$.time.temporal.a.CLOCK_HOUR_OF_AMPM;
        if (((HashMap) r04).containsKey(r12) == false) goto L29;
        long r6 = ((Long) ((HashMap) this.f418a).remove(r12)).longValue();
        z r05 = this.e;
        if (r05 != z.STRICT) goto L20;
    L23:
        r12.a0(r6);
    L24:
        j$.time.temporal.a r06 = j$.time.temporal.a.HOUR_OF_AMPM;
        if (r6 == 12) goto L28;
        r2 = r6;
    L28:
        x(r12, r06, Long.valueOf(r2));
        goto L29
    L20:
        if (r05 != z.SMART) goto L24;
        if (r6 == 0) goto L24;
    L29:
        Map r07 = this.f418a;
        j$.time.temporal.a r13 = j$.time.temporal.a.AMPM_OF_DAY;
        if (((HashMap) r07).containsKey(r13) == false) goto L37;
        Map r08 = this.f418a;
        j$.time.temporal.a r22 = j$.time.temporal.a.HOUR_OF_AMPM;
        if (((HashMap) r08).containsKey(r22) == false) goto L37;
        long r62 = ((Long) ((HashMap) this.f418a).remove(r13)).longValue();
        long r8 = ((Long) ((HashMap) this.f418a).remove(r22)).longValue();
        if (this.e != z.LENIENT) goto L36;
        x(r13, j$.time.temporal.a.HOUR_OF_DAY, Long.valueOf(Math.addExact(Math.multiplyExact(r62, 12), r8)));
        goto L37
    L36:
        r13.a0(r62);
        r22.a0(r62);
        x(r13, j$.time.temporal.a.HOUR_OF_DAY, Long.valueOf((r62 * 12) + r8));
    L37:
        Map r09 = this.f418a;
        j$.time.temporal.a r14 = j$.time.temporal.a.NANO_OF_DAY;
        if (((HashMap) r09).containsKey(r14) == false) goto L43;
        long r42 = ((Long) ((HashMap) this.f418a).remove(r14)).longValue();
        if (this.e == z.LENIENT) goto L42;
        r14.a0(r42);
    L42:
        x(r14, j$.time.temporal.a.HOUR_OF_DAY, Long.valueOf(r42 / 3600000000000L));
        x(r14, j$.time.temporal.a.MINUTE_OF_HOUR, Long.valueOf((r42 / 60000000000L) % 60));
        x(r14, j$.time.temporal.a.SECOND_OF_MINUTE, Long.valueOf((r42 / 1000000000) % 60));
        x(r14, j$.time.temporal.a.NANO_OF_SECOND, Long.valueOf(r42 % 1000000000));
    L43:
        Map r010 = this.f418a;
        j$.time.temporal.a r15 = j$.time.temporal.a.MICRO_OF_DAY;
        if (((HashMap) r010).containsKey(r15) == false) goto L49;
        long r63 = ((Long) ((HashMap) this.f418a).remove(r15)).longValue();
        if (this.e == z.LENIENT) goto L48;
        r15.a0(r63);
    L48:
        x(r15, j$.time.temporal.a.SECOND_OF_DAY, Long.valueOf(r63 / 1000000));
        x(r15, j$.time.temporal.a.MICRO_OF_SECOND, Long.valueOf(r63 % 1000000));
    L49:
        Map r011 = this.f418a;
        j$.time.temporal.a r16 = j$.time.temporal.a.MILLI_OF_DAY;
        if (((HashMap) r011).containsKey(r16) == false) goto L55;
        long r82 = ((Long) ((HashMap) this.f418a).remove(r16)).longValue();
        if (this.e == z.LENIENT) goto L54;
        r16.a0(r82);
    L54:
        x(r16, j$.time.temporal.a.SECOND_OF_DAY, Long.valueOf(r82 / 1000));
        x(r16, j$.time.temporal.a.MILLI_OF_SECOND, Long.valueOf(r82 % 1000));
    L55:
        Map r012 = this.f418a;
        j$.time.temporal.a r17 = j$.time.temporal.a.SECOND_OF_DAY;
        if (((HashMap) r012).containsKey(r17) == false) goto L61;
        long r83 = ((Long) ((HashMap) this.f418a).remove(r17)).longValue();
        if (this.e == z.LENIENT) goto L60;
        r17.a0(r83);
    L60:
        x(r17, j$.time.temporal.a.HOUR_OF_DAY, Long.valueOf(r83 / 3600));
        x(r17, j$.time.temporal.a.MINUTE_OF_HOUR, Long.valueOf((r83 / 60) % 60));
        x(r17, j$.time.temporal.a.SECOND_OF_MINUTE, Long.valueOf(r83 % 60));
    L61:
        Map r013 = this.f418a;
        j$.time.temporal.a r18 = j$.time.temporal.a.MINUTE_OF_DAY;
        if (((HashMap) r013).containsKey(r18) == false) goto L67;
        long r84 = ((Long) ((HashMap) this.f418a).remove(r18)).longValue();
        if (this.e == z.LENIENT) goto L66;
        r18.a0(r84);
    L66:
        x(r18, j$.time.temporal.a.HOUR_OF_DAY, Long.valueOf(r84 / 60));
        x(r18, j$.time.temporal.a.MINUTE_OF_HOUR, Long.valueOf(r84 % 60));
    L67:
        Map r014 = this.f418a;
        j$.time.temporal.a r19 = j$.time.temporal.a.NANO_OF_SECOND;
        if (((HashMap) r014).containsKey(r19) == false) goto L84;
        long r23 = ((Long) ((HashMap) this.f418a).get(r19)).longValue();
        z r015 = this.e;
        z r85 = z.LENIENT;
        if (r015 == r85) goto L72;
        r19.a0(r23);
    L72:
        Map r016 = this.f418a;
        j$.time.temporal.a r9 = j$.time.temporal.a.MICRO_OF_SECOND;
        if (((HashMap) r016).containsKey(r9) == false) goto L78;
        long r10 = ((Long) ((HashMap) this.f418a).remove(r9)).longValue();
        if (this.e == r85) goto L77;
        r9.a0(r10);
    L77:
        r23 = (r23 % 1000) + (r10 * 1000);
        x(r9, r19, Long.valueOf(r23));
    L78:
        Map r017 = this.f418a;
        j$.time.temporal.a r64 = j$.time.temporal.a.MILLI_OF_SECOND;
        if (((HashMap) r017).containsKey(r64) == false) goto L84;
        long r92 = ((Long) ((HashMap) this.f418a).remove(r64)).longValue();
        if (this.e == r85) goto L83;
        r64.a0(r92);
    L83:
        x(r64, r19, Long.valueOf((r23 % 1000000) + (r92 * 1000000)));
    L84:
        Map r018 = this.f418a;
        j$.time.temporal.a r24 = j$.time.temporal.a.HOUR_OF_DAY;
        if (((HashMap) r018).containsKey(r24) == false) goto L94;
        Map r019 = this.f418a;
        j$.time.temporal.a r3 = j$.time.temporal.a.MINUTE_OF_HOUR;
        if (((HashMap) r019).containsKey(r3) == false) goto L95;
        Map r020 = this.f418a;
        j$.time.temporal.a r43 = j$.time.temporal.a.SECOND_OF_MINUTE;
        if (((HashMap) r020).containsKey(r43) == true) goto L91;
        return;
    L91:
        if (((HashMap) this.f418a).containsKey(r19) == false) goto L97;
        s(((Long) ((HashMap) this.f418a).remove(r24)).longValue(), ((Long) ((HashMap) this.f418a).remove(r3)).longValue(), ((Long) ((HashMap) this.f418a).remove(r43)).longValue(), ((Long) ((HashMap) this.f418a).remove(r19)).longValue());
        return;
    L97:
        return;
    L95:
        return;
    }

    public final void v(LocalTime r3, Period r4) {
        LocalTime r0 = this.g;
        if (r0 != null) goto L5;
        this.g = r3;
        this.h = r4;
        return;
    L5:
        if (r0.equals(r3) == false) goto L17;
        Period r32 = this.h;
        r32.getClass();
        Period r02 = Period.d;
        if (r32 == r02) goto L13;
        if (r4 == r02) goto L13;
        if (this.h.equals(r4) == true) goto L13;
        j$.nio.file.b.f("Conflict found: Fields resolved to different excess periods: ", this.h, " ", r4);
        return;
    L13:
        this.h = r4;
        return;
    L17:
        j$.nio.file.b.f("Conflict found: Fields resolved to different times: ", this.g, " ", r3);
    }

    public final void w(j$.time.chrono.b r3) {
        j$.time.chrono.b r0 = this.f;
        if (r0 == null) goto L10;
        if (r3 != null) goto L6;
        return;
    L6:
        if (r0.equals(r3) == true) goto L19;
        j$.nio.file.b.f("Conflict found: Fields resolved to two different dates: ", this.f, " ", r3);
        return;
    L19:
        return;
    L10:
        if (r3 != null) goto L12;
        return;
    L12:
        if (this.f420c.equals(r3.f()) == false) goto L16;
        this.f = r3;
        return;
    L16:
        throw new j$.time.b("ChronoLocalDate must use the effective parsed chronology: " + this.f420c);
    }

    public final void x(j$.time.temporal.p r5, j$.time.temporal.a r6, Long r7) {
        Long r4 = (Long) ((HashMap) this.f418a).put(r6, r7);
        if (r4 != null) goto L5;
        return;
    L5:
        if (r4.longValue() != r7.longValue()) goto L8;
        return;
    L8:
        throw new j$.time.b("Conflict found: " + r6 + " " + r4 + " differs from " + r6 + " " + r7 + " while resolving  " + r5);
    }
}
