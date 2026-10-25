package j$.time.format;

import j$.time.ZoneId;
import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class t {

    /* renamed from: a, reason: collision with root package name */
    public final j$.time.temporal.l f409a;

    /* renamed from: b, reason: collision with root package name */
    public final DateTimeFormatter f410b;

    /* renamed from: c, reason: collision with root package name */
    public int f411c;

    public t(j$.time.temporal.l r10, DateTimeFormatter r11) {
        j$.time.chrono.k r0 = r11.e;
        if (r0 == null) goto L31;
        j$.time.chrono.k r1 = (j$.time.chrono.k) r10.d(j$.time.temporal.q.f464b);
        ZoneId r2 = (ZoneId) r10.d(j$.time.temporal.q.f463a);
        j$.time.chrono.b r4 = null;
        if (Objects.equals(r0, r1) == false) goto L8;
        r0 = null;
    L8:
        if (r0 == null) goto L31;
        if (r0 == null) goto L12;
        j$.time.chrono.k r3 = r0;
    L13:
        if (r0 != null) goto L15;
    L30:
        r10 = new s(r4, r10, r3, r2);
        goto L31
    L15:
        if (r10.i(j$.time.temporal.a.EPOCH_DAY) == false) goto L18;
        r4 = r3.J(r10);
        goto L30
    L18:
        if (r0 != j$.time.chrono.r.f353c) goto L20;
        if (r1 == null) goto L30;
    L20:
        j$.time.temporal.a[] r12 = j$.time.temporal.a.values();
        int r5 = r12.length;
        int r6 = 0;
    L21:
        if (r6 >= r5) goto L30;
        j$.time.temporal.a r7 = r12[r6];
        if (r7.isDateBased() == false) goto L29;
        if (r10.i(r7) == false) goto L29;
        throw new j$.time.b("Unable to apply override chronology '" + r0 + "' because the temporal object being formatted contains date fields but does not represent a whole date: " + r10);
    L29:
        r6 = r6 + 1;
        goto L21
    L12:
        r3 = r1;
    L31:
        this.f409a = r10;
        this.f410b = r11;
    }

    public final Long a(j$.time.temporal.p r2) {
        int r0 = this.f411c;
        j$.time.temporal.l r1 = this.f409a;
        if (r0 <= 0) goto L9;
        if (r1.i(r2) == true) goto L9;
        return null;
    L9:
        return Long.valueOf(r1.k(r2));
    }

    public final Object b(j$.time.e r4) {
        j$.time.temporal.l r0 = this.f409a;
        Object r1 = r0.d(r4);
        if (r1 == null) goto L5;
    L9:
        return r1;
    L5:
        if (this.f411c != 0) goto L9;
        throw new j$.time.b("Unable to extract " + r4 + " from temporal " + r0);
    }

    public final String toString() {
        return this.f409a.toString();
    }
}
