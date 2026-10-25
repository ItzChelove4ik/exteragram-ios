package j$.time.format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class q {
    public static final j$.time.e f = null;

    /* renamed from: a, reason: collision with root package name */
    public q f400a;

    /* renamed from: b, reason: collision with root package name */
    public final q f401b;

    /* renamed from: c, reason: collision with root package name */
    public final List f402c;
    public final boolean d;
    public int e;

    static {
        f = new j$.time.e(2);
        HashMap r0 = new HashMap();
        r0.put('G', j$.time.temporal.a.ERA);
        r0.put('y', j$.time.temporal.a.YEAR_OF_ERA);
        r0.put('u', j$.time.temporal.a.YEAR);
        j$.time.temporal.g r2 = j$.time.temporal.i.f454a;
        r0.put('Q', r2);
        r0.put('q', r2);
        j$.time.temporal.a r22 = j$.time.temporal.a.MONTH_OF_YEAR;
        r0.put('M', r22);
        r0.put('L', r22);
        r0.put('D', j$.time.temporal.a.DAY_OF_YEAR);
        r0.put('d', j$.time.temporal.a.DAY_OF_MONTH);
        r0.put('F', j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH);
        j$.time.temporal.a r23 = j$.time.temporal.a.DAY_OF_WEEK;
        r0.put('E', r23);
        r0.put('c', r23);
        r0.put('e', r23);
        r0.put('a', j$.time.temporal.a.AMPM_OF_DAY);
        r0.put('H', j$.time.temporal.a.HOUR_OF_DAY);
        r0.put('k', j$.time.temporal.a.CLOCK_HOUR_OF_DAY);
        r0.put('K', j$.time.temporal.a.HOUR_OF_AMPM);
        r0.put('h', j$.time.temporal.a.CLOCK_HOUR_OF_AMPM);
        r0.put('m', j$.time.temporal.a.MINUTE_OF_HOUR);
        r0.put('s', j$.time.temporal.a.SECOND_OF_MINUTE);
        j$.time.temporal.a r24 = j$.time.temporal.a.NANO_OF_SECOND;
        r0.put('S', r24);
        r0.put('A', j$.time.temporal.a.MILLI_OF_DAY);
        r0.put('n', r24);
        r0.put('N', j$.time.temporal.a.NANO_OF_DAY);
        r0.put('g', j$.time.temporal.k.f460a);
    }

    public q() {
        this.f400a = this;
        this.f402c = new ArrayList();
        this.e = -1;
        this.f401b = null;
        this.d = false;
    }

    public q(q r2) {
        this.f400a = this;
        this.f402c = new ArrayList();
        this.e = -1;
        this.f401b = r2;
        this.d = true;
    }

    public final void a(DateTimeFormatter r3) {
        Objects.requireNonNull(r3, "formatter");
        d r32 = r3.f370a;
        if (r32.f382b == false) goto L6;
        r32 = new d(r32.f381a, false);
    L6:
        b(r32);
    }

    public final int b(e r2) {
        Objects.requireNonNull(r2, "pp");
        q r0 = this.f400a;
        r0.getClass();
        ((ArrayList) r0.f402c).add(r2);
        this.f400a.e = -1;
        return ((ArrayList) r1.f402c).size() - 1;
    }

    public final void c(char r2) {
        b(new c(r2));
    }

    public final void d(String r3) {
        if (r3.isEmpty() == false) goto L5;
        return;
    L5:
        if (r3.length() != 1) goto L8;
        b(new c(r3.charAt(0)));
        return;
    L8:
        b(new m(r3));
    }

    public final void e(j$.time.temporal.a r3, Map r4) {
        Objects.requireNonNull(r3, "field");
        LinkedHashMap r0 = new LinkedHashMap(r4);
        TextStyle r42 = TextStyle.FULL;
        b(new n(r3, r42, new a(new v(Collections.singletonMap(r42, r0)))));
    }

    public final void f(h r6) {
        q r0 = this.f400a;
        int r1 = r0.e;
        if (r1 < 0) goto L12;
        h r02 = (h) ((ArrayList) r0.f402c).get(r1);
        int r2 = r6.f384b;
        int r3 = r6.f385c;
        if (r2 == r3) goto L7;
    L9:
        h r03 = r02.b();
        q r22 = this.f400a;
        r22.e = b(r6);
    L10:
        ((ArrayList) this.f400a.f402c).set(r1, r03);
        return;
    L7:
        if (r6.d != a0.NOT_NEGATIVE) goto L9;
        r03 = r02.c(r3);
        b(r6.b());
        this.f400a.e = r1;
        goto L10
    L12:
        r0.e = b(r6);
    }

    public final void g(j$.time.temporal.p r3, int r4) {
        Objects.requireNonNull(r3, "field");
        if (r4 >= 1) goto L5;
    L8:
        j$.nio.file.b.i("The width must be from 1 to 19 inclusive but was ", r4);
        return;
    L5:
        if (r4 > 19) goto L8;
        f(new h(r3, r4, r4, a0.NOT_NEGATIVE));
    }

    public final void h(j$.time.temporal.p r3, int r4, int r5, a0 r6) {
        if (r4 == r5) goto L4;
    L7:
        Objects.requireNonNull(r3, "field");
        Objects.requireNonNull(r6, "signStyle");
        if (r4 >= 1) goto L10;
    L20:
        j$.nio.file.b.i("The minimum width must be from 1 to 19 inclusive but was ", r4);
        return;
    L10:
        if (r4 > 19) goto L20;
        if (r5 < 1) goto L18;
        if (r5 > 19) goto L18;
        if (r5 < r4) goto L17;
        f(new h(r3, r4, r5, r6));
        return;
    L17:
        throw new IllegalArgumentException("The maximum width must exceed or equal the minimum width but " + r5 + " < " + r4);
    L18:
        j$.nio.file.b.i("The maximum width must be from 1 to 19 inclusive but was ", r5);
        return;
    L4:
        if (r6 != a0.NOT_NEGATIVE) goto L7;
        g(r3, r5);
    }

    public final void i() {
        q r0 = this.f400a;
        if (r0.f401b == null) goto L11;
        int r02 = ((ArrayList) r0.f402c).size();
        q r1 = this.f400a;
        if (r02 <= 0) goto L8;
        d r03 = new d(r1.f402c, r1.d);
        this.f400a = this.f400a.f401b;
        b(r03);
        return;
    L8:
        this.f400a = r1.f401b;
        return;
    L11:
        throw new IllegalStateException("Cannot call optionalEnd() as there was no previous call to optionalStart()");
    }

    public final void j() {
        q r0 = this.f400a;
        r0.e = -1;
        this.f400a = new q(r0);
    }

    public final DateTimeFormatter k(z r2, j$.time.chrono.k r3) {
        return l(Locale.getDefault(), r2, r3);
    }

    public final DateTimeFormatter l(Locale r3, z r4, j$.time.chrono.k r5) {
        Objects.requireNonNull(r3, "locale");
    L4:
        if (this.f400a.f401b == null) goto L6;
        i();
        goto L4
    L6:
        d r0 = new d(this.f402c, false);
        x r1 = x.f417a;
        return new DateTimeFormatter(r0, r3, r4, r5);
    }
}
