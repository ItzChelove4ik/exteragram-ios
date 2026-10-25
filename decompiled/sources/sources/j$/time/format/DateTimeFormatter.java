package j$.time.format;

import j$.time.LocalTime;
import j$.time.Period;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.chrono.ChronoLocalDateTime;
import j$.time.chrono.ChronoZonedDateTime;
import java.io.IOException;
import java.text.ParsePosition;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.mvel2.asm.signature.SignatureVisitor;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class DateTimeFormatter {
    public static final DateTimeFormatter ISO_LOCAL_DATE = null;
    public static final DateTimeFormatter RFC_1123_DATE_TIME = null;
    public static final DateTimeFormatter f = null;

    /* renamed from: a, reason: collision with root package name */
    public final d f370a;

    /* renamed from: b, reason: collision with root package name */
    public final Locale f371b;

    /* renamed from: c, reason: collision with root package name */
    public final x f372c;
    public final z d;
    public final j$.time.chrono.k e;

    static {
        q r0 = new q();
        j$.time.temporal.a r1 = j$.time.temporal.a.YEAR;
        a0 r2 = a0.EXCEEDS_PAD;
        r0.h(r1, 4, 10, r2);
        r0.c(SignatureVisitor.SUPER);
        j$.time.temporal.a r6 = j$.time.temporal.a.MONTH_OF_YEAR;
        r0.g(r6, 2);
        r0.c(SignatureVisitor.SUPER);
        j$.time.temporal.a r8 = j$.time.temporal.a.DAY_OF_MONTH;
        r0.g(r8, 2);
        z r9 = z.STRICT;
        j$.time.chrono.r r10 = j$.time.chrono.r.f353c;
        DateTimeFormatter r02 = r0.k(r9, r10);
        ISO_LOCAL_DATE = r02;
        q r11 = new q();
        l r12 = l.INSENSITIVE;
        r11.b(r12);
        r11.a(r02);
        i r13 = i.e;
        r11.b(r13);
        r11.k(r9, r10);
        q r112 = new q();
        r112.b(r12);
        r112.a(r02);
        r112.j();
        r112.b(r13);
        r112.k(r9, r10);
        q r113 = new q();
        j$.time.temporal.a r14 = j$.time.temporal.a.HOUR_OF_DAY;
        r113.g(r14, 2);
        r113.c(':');
        j$.time.temporal.a r5 = j$.time.temporal.a.MINUTE_OF_HOUR;
        r113.g(r5, 2);
        r113.j();
        r113.c(':');
        j$.time.temporal.a r15 = j$.time.temporal.a.SECOND_OF_MINUTE;
        r113.g(r15, 2);
        r113.j();
        r113.b(new f(j$.time.temporal.a.NANO_OF_SECOND));
        DateTimeFormatter r7 = r113.k(r9, null);
        q r114 = new q();
        r114.b(r12);
        r114.a(r7);
        r114.b(r13);
        r114.k(r9, null);
        q r115 = new q();
        r115.b(r12);
        r115.a(r7);
        r115.j();
        r115.b(r13);
        r115.k(r9, null);
        q r116 = new q();
        r116.b(r12);
        r116.a(r02);
        r116.c('T');
        r116.a(r7);
        DateTimeFormatter r03 = r116.k(r9, r10);
        q r72 = new q();
        r72.b(r12);
        r72.a(r03);
        l r117 = l.LENIENT;
        r72.b(r117);
        r72.b(r13);
        l r3 = l.STRICT;
        r72.b(r3);
        DateTimeFormatter r73 = r72.k(r9, r10);
        q r4 = new q();
        r4.a(r73);
        r4.j();
        r4.c('[');
        l r74 = l.SENSITIVE;
        r4.b(r74);
        j$.time.e r52 = q.f;
        r4.b(new o(r52, "ZoneRegionId()"));
        r4.c(']');
        r4.k(r9, r10);
        q r42 = new q();
        r42.a(r03);
        r42.j();
        r42.b(r13);
        r42.j();
        r42.c('[');
        r42.b(r74);
        r42.b(new o(r52, "ZoneRegionId()"));
        r42.c(']');
        r42.k(r9, r10);
        q r04 = new q();
        r04.b(r12);
        r04.h(r1, 4, 10, r2);
        r04.c(SignatureVisitor.SUPER);
        r04.g(j$.time.temporal.a.DAY_OF_YEAR, 3);
        r04.j();
        r04.b(r13);
        r04.k(r9, r10);
        q r05 = new q();
        r05.b(r12);
        r05.h(j$.time.temporal.i.f456c, 4, 10, r2);
        r05.d("-W");
        r05.g(j$.time.temporal.i.f455b, 2);
        r05.c(SignatureVisitor.SUPER);
        j$.time.temporal.a r22 = j$.time.temporal.a.DAY_OF_WEEK;
        r05.g(r22, 1);
        r05.j();
        r05.b(r13);
        r05.k(r9, r10);
        q r06 = new q();
        r06.b(r12);
        r06.b(new g());
        f = r06.k(r9, null);
        q r07 = new q();
        r07.b(r12);
        r07.g(r1, 4);
        r07.g(r6, 2);
        r07.g(r8, 2);
        r07.j();
        r07.b(r117);
        r07.b(new i("+HHMMss", "Z"));
        r07.b(r3);
        r07.k(r9, r10);
        HashMap r08 = new HashMap();
        r08.put(1L, "Mon");
        r08.put(2L, "Tue");
        r08.put(3L, "Wed");
        r08.put(4L, "Thu");
        r08.put(5L, "Fri");
        r08.put(6L, "Sat");
        r08.put(7L, "Sun");
        HashMap r43 = new HashMap();
        r43.put(1L, "Jan");
        r43.put(2L, "Feb");
        r43.put(3L, "Mar");
        r43.put(4L, "Apr");
        r43.put(5L, "May");
        r43.put(6L, "Jun");
        r43.put(7L, "Jul");
        r43.put(8L, "Aug");
        r43.put(9L, "Sep");
        r43.put(10L, "Oct");
        r43.put(11L, "Nov");
        r43.put(12L, "Dec");
        q r32 = new q();
        r32.b(r12);
        r32.b(r117);
        r32.j();
        r32.e(r22, r08);
        r32.d(", ");
        r32.i();
        r32.h(r8, 1, 2, a0.NOT_NEGATIVE);
        r32.c(' ');
        r32.e(r6, r43);
        r32.c(' ');
        r32.g(r1, 4);
        r32.c(' ');
        r32.g(r14, 2);
        r32.c(':');
        r32.g(r5, 2);
        r32.j();
        r32.c(':');
        r32.g(r15, 2);
        r32.i();
        r32.c(' ');
        r32.b(new i("+HHMM", "GMT"));
        RFC_1123_DATE_TIME = r32.k(z.SMART, r10);
    }

    public DateTimeFormatter(d r2, Locale r3, z r4, j$.time.chrono.k r5) {
        x r0 = x.f417a;
        this.f370a = r2;
        Objects.requireNonNull(r3, "locale");
        this.f371b = r3;
        this.f372c = r0;
        Objects.requireNonNull(r4, "resolverStyle");
        this.d = r4;
        this.e = r5;
    }

    public final String a(j$.time.temporal.l r4) {
        StringBuilder r0 = new StringBuilder(32);
        d r1 = this.f370a;
        r1.s(new t(r4, this), r0);     // Catch: IOException -> L6
        return r0.toString();
    L6:
        e = move-exception;
        throw new j$.time.b(e.getMessage(), e);
    }

    public final Object b(CharSequence r5, j$.time.e r6) {
        Objects.requireNonNull(r5, "text");
        return c(r5).d(r6);
    L12:
        e = move-exception;
        throw e;
    L5:
        e = move-exception;
        if (r5.length() <= 64) goto L9;
        String r62 = r5.subSequence(0, 64).toString() + "...";
    L10:
        DateTimeParseException r0 = new DateTimeParseException("Text '" + r62 + "' could not be parsed: " + e.getMessage(), e);
        r5.toString();
        throw r0;
    L9:
        r62 = r5.toString();
        goto L10
    }

    public final y c(CharSequence r24) {
        int r3 = 0;
        ParsePosition r2 = new ParsePosition(0);
        Objects.requireNonNull(r24, "text");
        r r4 = new r(this);
        int r5 = this.f370a.D(r4, r24, r2.getIndex());
        if (r5 >= 0) goto L5;
        r2.setErrorIndex(~r5);
        r4 = null;
    L6:
        if (r4 == null) goto L149;
        DateTimeFormatter r52 = r4.f403a;
        if (r2.getErrorIndex() >= 0) goto L149;
        if (r2.getIndex() < r24.length()) goto L149;
        y r9 = r4.c();
        j$.time.chrono.k r1 = r4.c().f420c;
        if (r1 != null) goto L17;
        r1 = r52.e;
        if (r1 != null) goto L17;
        r1 = j$.time.chrono.r.f353c;
    L17:
        r9.f420c = r1;
        ZoneId r12 = r9.f419b;
        if (r12 != null) goto L21;
        r52.getClass();
        r12 = null;
    L21:
        r9.f419b = r12;
        r9.e = this.d;
        r9.q();
        r9.w(r9.f420c.V(r9.f418a, r9.e));
        r9.u();
        if (((HashMap) r9.f418a).size() <= 0) goto L61;
    L24:
        if (r3 >= 50) goto L55;
        Iterator r13 = ((HashMap) r9.f418a).entrySet().iterator();
    L27:
        if (r13.hasNext() == false) goto L55;
        j$.time.temporal.p r22 = (j$.time.temporal.p) ((Map.Entry) r13.next()).getKey();
        j$.time.temporal.l r42 = r22.I(r9.f418a, r9, r9.e);
        if (r42 != null) goto L31;
        if (((HashMap) r9.f418a).containsKey(r22) == true) goto L27;
    L43:
        r3 = r3 + 1;
        goto L24
    L31:
        if ((r42 instanceof ChronoZonedDateTime) == false) goto L41;
        ChronoZonedDateTime r43 = (ChronoZonedDateTime) r42;
        ZoneId r0 = r9.f419b;
        if (r0 != null) goto L36;
        r9.f419b = r43.getZone();
    L37:
        r42 = r43.A();
        goto L41
    L36:
        if (r0.equals(r43.getZone()) == true) goto L37;
        throw new j$.time.b("ChronoZonedDateTime must use the effective parsed zone: " + r9.f419b);
    L41:
        if ((r42 instanceof ChronoLocalDateTime) == false) goto L45;
        ChronoLocalDateTime r44 = (ChronoLocalDateTime) r42;
        r9.v(r44.toLocalTime(), Period.d);
        r9.w(r44.m());
        goto L43
    L45:
        if ((r42 instanceof j$.time.chrono.b) == false) goto L48;
        r9.w((j$.time.chrono.b) r42);
        goto L43
    L48:
        if ((r42 instanceof LocalTime) == false) goto L50;
        r9.v((LocalTime) r42, Period.d);
        goto L43
    L50:
        j$.nio.file.b.h("Method resolve() can only return ChronoZonedDateTime, ChronoLocalDateTime, ChronoLocalDate or LocalTime");
        return null;
    L55:
        if (r3 == 50) goto L58;
        if (r3 <= 0) goto L61;
        r9.q();
        r9.w(r9.f420c.V(r9.f418a, r9.e));
        r9.u();
        goto L61
    L58:
        j$.nio.file.b.h("One of the parsed fields has an incorrectly implemented resolve method");
        return null;
    L61:
        if (r9.g != null) goto L93;
        Map r02 = r9.f418a;
        j$.time.temporal.a r7 = j$.time.temporal.a.MILLI_OF_SECOND;
        boolean r03 = ((HashMap) r02).containsKey(r7);
        Map r8 = r9.f418a;
        if (r03 == false) goto L68;
        long r10 = ((Long) ((HashMap) r8).remove(r7)).longValue();
        Map r04 = r9.f418a;
        j$.time.temporal.a r82 = j$.time.temporal.a.MICRO_OF_SECOND;
        boolean r05 = ((HashMap) r04).containsKey(r82);
        Map r122 = r9.f418a;
        if (r05 == false) goto L67;
        long r123 = (((Long) ((HashMap) r122).get(r82)).longValue() % 1000) + (r10 * 1000);
        r9.x(r7, r82, Long.valueOf(r123));
        ((HashMap) r9.f418a).remove(r82);
        ((HashMap) r9.f418a).put(j$.time.temporal.a.NANO_OF_SECOND, Long.valueOf(r123 * 1000));
    L71:
        Map r06 = r9.f418a;
        j$.time.temporal.a r72 = j$.time.temporal.a.HOUR_OF_DAY;
        Long r07 = (Long) ((HashMap) r06).get(r72);
        if (r07 == null) goto L93;
        Map r83 = r9.f418a;
        j$.time.temporal.a r102 = j$.time.temporal.a.MINUTE_OF_HOUR;
        Long r84 = (Long) ((HashMap) r83).get(r102);
        Map r11 = r9.f418a;
        j$.time.temporal.a r124 = j$.time.temporal.a.SECOND_OF_MINUTE;
        Long r112 = (Long) ((HashMap) r11).get(r124);
        Map r132 = r9.f418a;
        j$.time.temporal.a r14 = j$.time.temporal.a.NANO_OF_SECOND;
        Long r133 = (Long) ((HashMap) r132).get(r14);
        if (r84 != null) goto L79;
        if (r112 != null) goto L78;
        if (r133 == null) goto L79;
    L78:
        long r23 = 1000000;
    L106:
        j$.time.chrono.b r08 = r9.f;
        if (r08 == null) goto L109;
        r9.p(r08);
    L109:
        LocalTime r09 = r9.g;
        if (r09 == null) goto L117;
        r9.p(r09);
        if (r9.f == null) goto L117;
        if (((HashMap) r9.f418a).size() <= 0) goto L117;
        r9.p(r9.f.M(r9.g));
    L117:
        if (r9.f == null) goto L125;
        if (r9.g == null) goto L125;
        Period r010 = r9.h;
        r010.getClass();
        Period r15 = Period.d;
        if (r010 == r15) goto L125;
        r9.f = r9.f.U(r9.h);
        r9.h = r15;
    L125:
        if (r9.g != null) goto L137;
        if (((HashMap) r9.f418a).containsKey(j$.time.temporal.a.INSTANT_SECONDS) == false) goto L129;
    L132:
        Map r16 = r9.f418a;
        j$.time.temporal.a r25 = j$.time.temporal.a.NANO_OF_SECOND;
        boolean r17 = ((HashMap) r16).containsKey(r25);
        Map r53 = r9.f418a;
        if (r17 == false) goto L135;
        long r011 = ((Long) ((HashMap) r53).get(r25)).longValue();
        ((HashMap) r9.f418a).put(j$.time.temporal.a.MICRO_OF_SECOND, Long.valueOf(r011 / 1000));
        ((HashMap) r9.f418a).put(j$.time.temporal.a.MILLI_OF_SECOND, Long.valueOf(r011 / r23));
        goto L137
    L135:
        ((HashMap) r53).put(r25, 0L);
        ((HashMap) r9.f418a).put(j$.time.temporal.a.MICRO_OF_SECOND, 0L);
        ((HashMap) r9.f418a).put(j$.time.temporal.a.MILLI_OF_SECOND, 0L);
        goto L137
    L129:
        if (((HashMap) r9.f418a).containsKey(j$.time.temporal.a.SECOND_OF_DAY) == true) goto L132;
        if (((HashMap) r9.f418a).containsKey(j$.time.temporal.a.SECOND_OF_MINUTE) == true) goto L132;
    L137:
        if (r9.f != null) goto L139;
    L147:
        return r9;
    L139:
        if (r9.g == null) goto L147;
        Long r012 = (Long) ((HashMap) r9.f418a).get(j$.time.temporal.a.OFFSET_SECONDS);
        if (r012 == null) goto L145;
        long r013 = r9.f.M(r9.g).H(ZoneOffset.ofTotalSeconds(r012.intValue())).Z();
        ((HashMap) r9.f418a).put(j$.time.temporal.a.INSTANT_SECONDS, Long.valueOf(r013));
        return r9;
    L145:
        if (r9.f419b == null) goto L147;
        long r014 = r9.f.M(r9.g).H(r9.f419b).Z();
        ((HashMap) r9.f418a).put(j$.time.temporal.a.INSTANT_SECONDS, Long.valueOf(r014));
    L79:
        if (r84 == null) goto L83;
        if (r112 != null) goto L83;
        if (r133 != null) goto L78;
    L83:
        if (r84 == null) goto L85;
        long r152 = r84.longValue();
    L86:
        if (r112 == null) goto L88;
        long r172 = r112.longValue();
    L89:
        if (r133 == null) goto L91;
        long r19 = r133.longValue();
    L92:
        r23 = 1000000;
        r9.s(r07.longValue(), r152, r172, r19);
        ((HashMap) r9.f418a).remove(r72);
        ((HashMap) r9.f418a).remove(r102);
        ((HashMap) r9.f418a).remove(r124);
        ((HashMap) r9.f418a).remove(r14);
    L95:
        if (r9.e == z.LENIENT) goto L106;
        if (((HashMap) r9.f418a).size() <= 0) goto L106;
        Iterator r015 = ((HashMap) r9.f418a).entrySet().iterator();
    L100:
        if (r015.hasNext() == false) goto L106;
        Map.Entry r18 = (Map.Entry) r015.next();
        j$.time.temporal.p r26 = (j$.time.temporal.p) r18.getKey();
        if ((r26 instanceof j$.time.temporal.a) == false) goto L100;
        j$.time.temporal.a r27 = (j$.time.temporal.a) r26;
        if (r27.b0() == false) goto L100;
        r27.a0(((Long) r18.getValue()).longValue());
        goto L100
    L91:
        r19 = 0;
        goto L92
    L88:
        r172 = 0;
        goto L89
    L85:
        r152 = 0;
        goto L86
    L67:
        ((HashMap) r122).put(j$.time.temporal.a.NANO_OF_SECOND, Long.valueOf(r10 * 1000000));
        goto L71
    L68:
        j$.time.temporal.a r016 = j$.time.temporal.a.MICRO_OF_SECOND;
        if (((HashMap) r8).containsKey(r016) == false) goto L71;
        long r73 = ((Long) ((HashMap) r9.f418a).remove(r016)).longValue();
        ((HashMap) r9.f418a).put(j$.time.temporal.a.NANO_OF_SECOND, Long.valueOf(r73 * 1000));
    L93:
        r23 = 1000000;
    L149:
        if (r24.length() <= 64) goto L151;
        String r017 = r24.subSequence(0, 64).toString() + "...";
    L153:
        if (r2.getErrorIndex() < 0) goto L156;
        String r018 = "Text '" + r017 + "' could not be parsed at index " + r2.getErrorIndex();
        r2.getErrorIndex();
        throw new DateTimeParseException(r018, r24);
    L156:
        String r019 = "Text '" + r017 + "' could not be parsed, unparsed text found at index " + r2.getIndex();
        r2.getIndex();
        throw new DateTimeParseException(r019, r24);
    L151:
        r017 = r24.toString();
        goto L153
    L5:
        r2.setIndex(r5);
        goto L6
    }

    public final String toString() {
        String r2 = this.f370a.toString();
        if (r2.startsWith("[") == false) goto L6;
        return r2;
    L6:
        return r2.substring(1, r2.length() - 1);
    }
}
