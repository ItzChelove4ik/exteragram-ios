package j$.time.format;

import j$.time.ZoneId;
import okhttp3.internal.url._UrlKt;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class s implements j$.time.temporal.l {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ j$.time.chrono.b f406a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ j$.time.temporal.l f407b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ j$.time.chrono.k f408c;
    public final /* synthetic */ ZoneId d;

    public s(j$.time.chrono.b r1, j$.time.temporal.l r2, j$.time.chrono.k r3, ZoneId r4) {
        this.f406a = r1;
        this.f407b = r2;
        this.f408c = r3;
        this.d = r4;
    }

    @Override // j$.time.temporal.l
    public final Object d(j$.time.e r2) {
        if (r2 != j$.time.temporal.q.f464b) goto L7;
        return this.f408c;
    L7:
        if (r2 != j$.time.temporal.q.f463a) goto L11;
        return this.d;
    L11:
        if (r2 != j$.time.temporal.q.f465c) goto L15;
        return this.f407b.d(r2);
    L15:
        return r2.l(this);
    }

    @Override // j$.time.temporal.l
    public final boolean i(j$.time.temporal.p r3) {
        j$.time.chrono.b r0 = this.f406a;
        if (r0 == null) goto L9;
        if (r3.isDateBased() == false) goto L9;
        return r0.i(r3);
    L9:
        return this.f407b.i(r3);
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r3) {
        j$.time.chrono.b r0 = this.f406a;
        if (r0 == null) goto L9;
        if (r3.isDateBased() == false) goto L9;
        return r0.k(r3);
    L9:
        return this.f407b.k(r3);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s l(j$.time.temporal.p r3) {
        j$.time.chrono.b r0 = this.f406a;
        if (r0 == null) goto L9;
        if (r3.isDateBased() == false) goto L9;
        return r0.l(r3);
    L9:
        return this.f407b.l(r3);
    }

    public final String toString() {
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        j$.time.chrono.k r1 = this.f408c;
        if (r1 == null) goto L5;
        String r12 = " with chronology " + r1;
    L6:
        ZoneId r2 = this.d;
        if (r2 == null) goto L10;
        r0 = " with zone " + r2;
    L10:
        return this.f407b + r12 + r0;
    L5:
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }
}
