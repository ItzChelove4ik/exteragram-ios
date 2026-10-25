package j$.time.zone;

import j$.time.DayOfWeek;
import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.LocalDateTime;
import j$.time.LocalTime;
import j$.time.ZoneOffset;
import j$.time.chrono.r;
import j$.time.k;
import j$.time.temporal.n;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class ZoneRules implements Serializable {
    public static final long[] i = null;
    public static final e[] j = null;
    public static final LocalDateTime[] k = null;
    public static final b[] l = null;
    private static final long serialVersionUID = 3044319355680032515L;

    /* renamed from: a, reason: collision with root package name */
    public final long[] f469a;

    /* renamed from: b, reason: collision with root package name */
    public final ZoneOffset[] f470b;

    /* renamed from: c, reason: collision with root package name */
    public final long[] f471c;
    public final LocalDateTime[] d;
    public final ZoneOffset[] e;
    public final e[] f;
    public final TimeZone g;
    public final transient ConcurrentMap h;

    static {
        i = new long[0];
        j = new e[0];
        k = new LocalDateTime[0];
        l = new b[0];
    }

    public ZoneRules(ZoneOffset r3) {
        this.h = new ConcurrentHashMap();
        ZoneOffset[] r0 = {r3};
        this.f470b = r0;
        long[] r32 = i;
        this.f469a = r32;
        this.f471c = r32;
        this.d = k;
        this.e = r0;
        this.f = j;
        this.g = null;
    }

    public ZoneRules(TimeZone r4) {
        this.h = new ConcurrentHashMap();
        ZoneOffset[] r0 = {h(r4.getRawOffset())};
        this.f470b = r0;
        long[] r1 = i;
        this.f469a = r1;
        this.f471c = r1;
        this.d = k;
        this.e = r0;
        this.f = j;
        this.g = r4;
    }

    public ZoneRules(long[] r6, ZoneOffset[] r7, long[] r8, ZoneOffset[] r9, e[] r10) {
        this.h = new ConcurrentHashMap();
        this.f469a = r6;
        this.f470b = r7;
        this.f471c = r8;
        this.e = r9;
        this.f = r10;
        if (r8.length != 0) goto L5;
        this.d = k;
    L14:
        this.g = null;
        return;
    L5:
        ArrayList r62 = new ArrayList();
        int r72 = 0;
    L7:
        if (r72 >= r8.length) goto L13;
        int r0 = r72 + 1;
        b r2 = new b(r8[r72], r9[r72], r9[r0]);
        boolean r73 = r2.s();
        LocalDateTime r102 = r2.f475b;
        if (r73 == false) goto L11;
        r62.add(r102);
        r62.add(r2.f475b.Y(r2.d.getTotalSeconds() - r2.f476c.getTotalSeconds()));
    L12:
        r72 = r0;
        goto L7
    L11:
        r62.add(r102.Y(r2.d.getTotalSeconds() - r2.f476c.getTotalSeconds()));
        r62.add(r2.f475b);
        goto L12
    L13:
        this.d = (LocalDateTime[]) r62.toArray(new LocalDateTime[r62.size()]);
        goto L14
    }

    public static Object a(LocalDateTime r3, b r4) {
        LocalDateTime r0 = r4.f475b;
        if (r4.s() == false) goto L14;
        if (r3.I(r0) == false) goto L9;
        return r4.f476c;
    L9:
        if (r3.I(r4.f475b.Y(r4.d.getTotalSeconds() - r4.f476c.getTotalSeconds())) == false) goto L12;
    L21:
        return r4;
    L12:
        return r4.d;
    L14:
        if (r3.I(r0) == true) goto L18;
        return r4.d;
    L18:
        if (r3.I(r4.f475b.Y(r4.d.getTotalSeconds() - r4.f476c.getTotalSeconds())) == false) goto L21;
        return r4.f476c;
    }

    public static int c(long r2, ZoneOffset r4) {
        return LocalDate.g0(Math.floorDiv(r2 + r4.getTotalSeconds(), 86400)).getYear();
    }

    public static ZoneOffset h(int r0) {
        return ZoneOffset.ofTotalSeconds(r0 / 1000);
    }

    private void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        if (this.g == null) goto L5;
        byte r1 = 100;
    L7:
        return new a(r1, this);
    L5:
        r1 = 1;
        goto L7
    }

    public final b[] b(int r21) {
        b[] r2 = l;
        Integer r3 = Integer.valueOf(r21);
        b[] r4 = (b[]) ((ConcurrentHashMap) this.h).get(r3);
        if (r4 == null) goto L5;
        return r4;
    L5:
        long r6 = 1;
        int r8 = 0;
        if (this.g != null) goto L8;
        e[] r22 = this.f;
        b[] r42 = new b[r22.length];
        int r62 = 0;
    L39:
        if (r62 >= r22.length) goto L60;
        e r7 = r22[r62];
        byte r10 = r7.f480b;
        k r11 = r7.f479a;
        if (r10 >= 0) goto L45;
        long r12 = r21;
        r.f353c.getClass();
        int r102 = (r11.D(r.a0(r12)) + 1) + r7.f480b;
        LocalDate r14 = LocalDate.d;
        j$.time.temporal.a.YEAR.a0(r12);
        j$.time.temporal.a.DAY_OF_MONTH.a0(r102);
        LocalDate r103 = LocalDate.D(r21, r11.getValue(), r102);
        DayOfWeek r112 = r7.f481c;
        if (r112 == null) goto L49;
        r103 = r103.o0(new n(r112.getValue(), 1));
    L49:
        if (r7.e == false) goto L52;
        r103 = r103.plusDays(1);
    L52:
        LocalDateTime r104 = LocalDateTime.of(r103, r7.d);
        d r13 = r7.f;
        ZoneOffset r142 = r7.g;
        ZoneOffset r15 = r7.h;
        int r132 = c.f477a[r13.ordinal()];
        if (r132 != 1) goto L55;
        r104 = r104.Y(r15.getTotalSeconds() - ZoneOffset.UTC.getTotalSeconds());
    L59:
        r42[r62] = new b(r104, r7.h, r7.i);
        r62 = r62 + 1;
        r8 = 0;
        goto L39
    L55:
        if (r132 != 2) goto L59;
        r104 = r104.Y(r15.getTotalSeconds() - r142.getTotalSeconds());
        goto L59
    L45:
        LocalDate r122 = LocalDate.d;
        j$.time.temporal.a.YEAR.a0(r21);
        j$.time.temporal.a.DAY_OF_MONTH.a0(r10);
        r103 = LocalDate.D(r21, r11.getValue(), r10);
        DayOfWeek r113 = r7.f481c;
        if (r113 == null) goto L49;
        r103 = r103.o0(new n(r113.getValue(), r8));
        goto L49
    L60:
        if (r21 >= 2100) goto L62;
        ((ConcurrentHashMap) this.h).putIfAbsent(r3, r42);
    L62:
        return r42;
    L8:
        if (r21 >= 1800) goto L10;
        return r2;
    L10:
        LocalDateTime r105 = LocalDateTime.f291c;
        LocalDate r43 = LocalDate.of(r21 - 1, 12, 31);
        j$.time.temporal.a.HOUR_OF_DAY.a0(0);
        long r106 = new LocalDateTime(r43, LocalTime.h[0]).toEpochSecond(this.f470b[0]);
        long r123 = 1000;
        int r44 = this.g.getOffset(r106 * 1000);
        long r143 = 31968000 + r106;
    L12:
        if (r106 >= r143) goto L33;
        long r16 = r106 + 7776000;
        long r18 = r123;
        if (r44 != this.g.getOffset(r16 * r18)) goto L16;
        r106 = r16;
    L31:
        r123 = r18;
        r6 = 1;
    L16:
        if ((r16 - r106) <= r6) goto L23;
        long r63 = Math.floorDiv(r16 + r106, 2);
        if (this.g.getOffset(r63 * r18) != r44) goto L20;
        r106 = r63;
    L21:
        r6 = 1;
        goto L16
    L20:
        r16 = r63;
        goto L21
    L23:
        if (this.g.getOffset(r106 * r18) != r44) goto L26;
        r106 = r16;
    L26:
        ZoneOffset r45 = h(r44);
        int r64 = this.g.getOffset(r106 * r18);
        ZoneOffset r72 = h(r64);
        if (c(r106, r72) != r21) goto L29;
        r2 = (b[]) Arrays.copyOf(r2, r2.length + 1);
        r2[r2.length - 1] = new b(r106, r45, r72);
    L29:
        r44 = r64;
        goto L31
    L33:
        if (1916 > r21) goto L36;
        if (r21 >= 2100) goto L36;
        ((ConcurrentHashMap) this.h).putIfAbsent(r3, r2);
    L36:
        return r2;
    }

    public final Object d(LocalDateTime r9) {
        Object r1 = null;
        int r2 = 0;
        if (this.g == null) goto L19;
        b[] r0 = b(r9.f292a.getYear());
        if (r0.length == 0) goto L7;
        int r8 = r0.length;
    L9:
        if (r2 >= r8) goto L17;
        b r12 = r0[r2];
        Object r3 = a(r9, r12);
        if ((r3 instanceof b) == true) goto L16;
        if (r3.equals(r12.f476c) == true) goto L16;
        r2 = r2 + 1;
        r1 = r3;
    L16:
        return r3;
    L17:
        return r1;
    L7:
        return h(this.g.getOffset(r9.toEpochSecond(this.f470b[0]) * 1000));
    L19:
        if (this.f471c.length != 0) goto L23;
        return this.f470b[0];
    L23:
        if (this.f.length <= 0) goto L47;
        LocalDateTime[] r02 = this.d;
        LocalDateTime r03 = r02[r02.length - 1];
        if (r03 == null) goto L30;
        r9.getClass();
        if (r9.s(r03) <= 0) goto L29;
    L28:
        boolean r04 = true;
    L36:
        if (r04 == false) goto L47;
        b[] r82 = b(r9.f292a.getYear());
        int r05 = r82.length;
    L38:
        if (r2 >= r05) goto L46;
        b r13 = r82[r2];
        Object r32 = a(r9, r13);
        if ((r32 instanceof b) == true) goto L45;
        if (r32.equals(r13.f476c) == true) goto L45;
        r2 = r2 + 1;
        r1 = r32;
    L45:
        return r32;
    L46:
        return r1;
    L29:
        r04 = false;
        goto L36
    L30:
        long r4 = r9.toLocalDate().L();
        long r6 = r03.toLocalDate().L();
        if (r4 > r6) goto L28;
        if (r4 != r6) goto L29;
        if (r9.toLocalTime().f0() <= r03.toLocalTime().f0()) goto L29;
    L47:
        int r92 = Arrays.binarySearch(this.d, r9);
        if (r92 == (-1)) goto L50;
        if (r92 >= 0) goto L53;
        r92 = (-r92) - 2;
    L59:
        if ((r92 & 1) != 0) goto L67;
        LocalDateTime[] r06 = this.d;
        LocalDateTime r14 = r06[r92];
        LocalDateTime r07 = r06[r92 + 1];
        ZoneOffset[] r83 = this.e;
        int r93 = r92 / 2;
        ZoneOffset r22 = r83[r93];
        ZoneOffset r84 = r83[r93 + 1];
        if (r84.getTotalSeconds() <= r22.getTotalSeconds()) goto L65;
        return new b(r14, r22, r84);
    L65:
        return new b(r07, r22, r84);
    L67:
        return this.e[(r92 / 2) + 1];
    L53:
        Object[] r08 = this.d;
        if (r92 >= (r08.length - 1)) goto L59;
        int r23 = r92 + 1;
        if (r08[r92].equals(r08[r23]) == false) goto L59;
        r92 = r23;
        goto L59
    L50:
        return this.e[0];
    }

    public final b e(LocalDateTime r1) {
        Object r0 = d(r1);
        if ((r0 instanceof b) == true) goto L5;
        return null;
    L5:
        return (b) r0;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof ZoneRules) == false) goto L20;
        ZoneRules r52 = (ZoneRules) r5;
        if (Objects.equals(this.g, r52.g) == false) goto L20;
        if (Arrays.equals(this.f469a, r52.f469a) == false) goto L20;
        if (Arrays.equals(this.f470b, r52.f470b) == false) goto L20;
        if (Arrays.equals(this.f471c, r52.f471c) == false) goto L20;
        if (Arrays.equals(this.e, r52.e) == false) goto L20;
        if (Arrays.equals(this.f, r52.f) == false) goto L20;
        return true;
    L20:
        return false;
    }

    public final List f(LocalDateTime r1) {
        Object r0 = d(r1);
        if ((r0 instanceof b) == false) goto L11;
        b r02 = (b) r0;
        if (r02.s() == false) goto L9;
        return Collections.EMPTY_LIST;
    L9:
        return j$.desugar.sun.nio.fs.g.p(new Object[]{r02.f476c, r02.d});
    L11:
        return Collections.singletonList((ZoneOffset) r0);
    }

    public final boolean g(Instant r4) {
        TimeZone r0 = this.g;
        if (r0 == null) goto L6;
        ZoneOffset r02 = h(r0.getRawOffset());
    L13:
        return !r02.equals(getOffset(r4));
    L6:
        if (this.f471c.length != 0) goto L8;
        r02 = this.f470b[0];
        goto L13
    L8:
        int r03 = Arrays.binarySearch(this.f469a, r4.getEpochSecond());
        if (r03 >= 0) goto L11;
        r03 = (-r03) - 2;
    L11:
        r02 = this.f470b[r03 + 1];
        goto L13
    }

    public ZoneOffset getOffset(Instant r7) {
        TimeZone r0 = this.g;
        if (r0 != null) goto L5;
        int r1 = 0;
        if (this.f471c.length == 0) goto L9;
        long r2 = r7.getEpochSecond();
        if (this.f.length > 0) goto L13;
    L24:
        int r72 = Arrays.binarySearch(this.f471c, r2);
        if (r72 >= 0) goto L28;
        r72 = (-r72) - 2;
    L28:
        return this.e[r72 + 1];
    L13:
        if (r2 <= this.f471c[r7.length - 1]) goto L24;
        b[] r6 = b(c(r2, this.e[r7.length - 1]));
        b r73 = null;
    L16:
        if (r1 >= r6.length) goto L23;
        r73 = r6[r1];
        if (r2 < r73.f474a) goto L20;
        r1 = r1 + 1;
        goto L16
    L20:
        return r73.f476c;
    L23:
        return r73.d;
    L9:
        return this.f470b[0];
    L5:
        return h(r0.getOffset(r7.toEpochMilli()));
    }

    public ZoneOffset getOffset(LocalDateTime r1) {
        Object r0 = d(r1);
        if ((r0 instanceof b) == false) goto L7;
        return ((b) r0).f476c;
    L7:
        return (ZoneOffset) r0;
    }

    public final int hashCode() {
        int r0 = (((Objects.hashCode(this.g) ^ Arrays.hashCode(this.f469a)) ^ Arrays.hashCode(this.f470b)) ^ Arrays.hashCode(this.f471c)) ^ Arrays.hashCode(this.e);
        return Arrays.hashCode(this.f) ^ r0;
    }

    public final String toString() {
        TimeZone r0 = this.g;
        if (r0 == null) goto L7;
        return "ZoneRules[timeZone=" + r0.getID() + "]";
    L7:
        return "ZoneRules[currentStandardOffset=" + this.f470b[r3.length - 1] + "]";
    }
}
