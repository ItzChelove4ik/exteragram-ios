package j$.time.temporal;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class n implements m {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f461a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f462b;

    public /* synthetic */ n(int r1, int r2) {
        this.f461a = r2;
        this.f462b = r1;
    }

    @Override // j$.time.temporal.m
    public final Temporal e(Temporal r3) {
        int r0 = this.f461a;
        int r2 = this.f462b;
        switch(r0) {
            case 0: goto L14;
            default: goto L4;
        };
    L4:
        int r02 = r3.g(a.DAY_OF_WEEK);
        if (r02 == r2) goto L24;
        int r22 = r2 - r02;
        if (r22 < 0) goto L11;
        int r23 = 7 - r22;
    L13:
        return r3.c(r23, ChronoUnit.DAYS);
    L11:
        r23 = -r22;
        goto L13
    L24:
        return r3;
    L14:
        int r03 = r3.g(a.DAY_OF_WEEK);
        if (r03 == r2) goto L25;
        int r04 = r03 - r2;
        if (r04 < 0) goto L21;
        int r24 = 7 - r04;
    L23:
        return r3.b(r24, ChronoUnit.DAYS);
    L21:
        r24 = -r04;
        goto L23
    L25:
        return r3;
    }
}
