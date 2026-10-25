package j$.time.format;

import java.util.Iterator;
import java.util.Map;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class n implements e {

    /* renamed from: a, reason: collision with root package name */
    public final j$.time.temporal.p f394a;

    /* renamed from: b, reason: collision with root package name */
    public final TextStyle f395b;

    /* renamed from: c, reason: collision with root package name */
    public final w f396c;
    public volatile h d;

    public n(j$.time.temporal.p r1, TextStyle r2, w r3) {
        this.f394a = r1;
        this.f395b = r2;
        this.f396c = r3;
    }

    @Override // j$.time.format.e
    public final int D(r r12, CharSequence r13, int r14) {
        w r2 = this.f396c;
        j$.time.temporal.p r7 = this.f394a;
        int r3 = r13.length();
        if (r14 < 0) goto L50;
        if (r14 > r3) goto L50;
        boolean r32 = r12.f405c;
        DateTimeFormatter r5 = r12.f403a;
        if (r32 == false) goto L8;
        TextStyle r33 = this.f395b;
    L9:
        j$.time.chrono.k r6 = r12.c().f420c;
        if (r6 != null) goto L14;
        r6 = r12.f403a.e;
        if (r6 != null) goto L14;
        r6 = j$.time.chrono.r.f353c;
    L14:
        j$.time.chrono.k r8 = r6;
        if (r8 != null) goto L17;
    L21:
        Iterator r22 = r2.e(r7, r33, r5.f371b);
    L20:
        Iterator r9 = r22;
        if (r9 == null) goto L45;
    L24:
        if (r9.hasNext() == false) goto L30;
        Map.Entry r10 = (Map.Entry) r9.next();
        String r23 = (String) r10.getKey();
        if (r12.g(r23, 0, r13, r14, r23.length()) == false) goto L24;
        return r12.f(this.f394a, ((Long) r10.getValue()).longValue(), r14, r23.length() + r14);
    L30:
        if (r7 != j$.time.temporal.a.ERA) goto L41;
        if (r12.f405c == true) goto L41;
        Iterator r72 = r8.C().iterator();
    L35:
        if (r72.hasNext() == false) goto L41;
        String r24 = ((j$.time.chrono.l) r72.next()).toString();
        if (r12.g(r24, 0, r13, r14, r24.length()) == false) goto L35;
        return r12.f(this.f394a, r8.getValue(), r14, r24.length() + r14);
    L41:
        if (r12.f405c == false) goto L45;
        return ~r14;
    L45:
        if (this.d != null) goto L48;
        this.d = new h(this.f394a, 1, 19, a0.NORMAL);
    L48:
        return this.d.D(r12, r13, r14);
    L17:
        if (r8 == j$.time.chrono.r.f353c) goto L21;
        r22 = r2.d(r8, r7, r33, r5.f371b);
        goto L20
    L8:
        r33 = null;
    L50:
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.time.format.e
    public final boolean s(t r11, StringBuilder r12) {
        Long r0 = r11.a(this.f394a);
        DateTimeFormatter r1 = r11.f410b;
        if (r0 != null) goto L6;
        return false;
    L6:
        j$.time.chrono.k r4 = (j$.time.chrono.k) r11.f409a.d(j$.time.temporal.q.f464b);
        if (r4 == null) goto L12;
        if (r4 == j$.time.chrono.r.f353c) goto L12;
        String r02 = this.f396c.b(r4, this.f394a, r0.longValue(), this.f395b, r1.f371b);
    L14:
        if (r02 == null) goto L16;
        r12.append(r02);
        return true;
    L16:
        if (this.d != null) goto L19;
        this.d = new h(this.f394a, 1, 19, a0.NORMAL);
    L19:
        return this.d.s(r11, r12);
    L12:
        r02 = this.f396c.c(this.f394a, r0.longValue(), this.f395b, r1.f371b);
        goto L14
    }

    public final String toString() {
        TextStyle r0 = TextStyle.FULL;
        TextStyle r3 = this.f395b;
        j$.time.temporal.p r4 = this.f394a;
        if (r3 != r0) goto L7;
        return "Text(" + r4 + ")";
    L7:
        return "Text(" + r4 + "," + r3 + ")";
    }
}
