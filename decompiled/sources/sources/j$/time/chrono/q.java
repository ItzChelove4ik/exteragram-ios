package j$.time.chrono;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum q extends Enum implements l {
    public static final q AH = null;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ q[] f352a = null;

    static {
        q r0 = new q("AH", 0);
        AH = r0;
        f352a = new q[]{r0};
    }

    public static q valueOf(String r1) {
        return (q) Enum.valueOf(q.class, r1);
    }

    public static q[] values() {
        return (q[]) f352a.clone();
    }

    @Override // j$.time.chrono.l
    public final int getValue() {
        return 1;
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s l(j$.time.temporal.p r2) {
        if (r2 != j$.time.temporal.a.ERA) goto L7;
        return j$.time.temporal.s.f(1, 1);
    L7:
        return super.l(r2);
    }
}
