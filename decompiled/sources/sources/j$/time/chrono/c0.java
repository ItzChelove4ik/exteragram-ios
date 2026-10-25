package j$.time.chrono;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum c0 extends Enum implements l {
    public static final c0 BEFORE_ROC = null;
    public static final c0 ROC = null;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ c0[] f327a = null;

    static {
        c0 r0 = new c0("BEFORE_ROC", 0);
        BEFORE_ROC = r0;
        c0 r1 = new c0("ROC", 1);
        ROC = r1;
        f327a = new c0[]{r0, r1};
    }

    public static c0 valueOf(String r1) {
        return (c0) Enum.valueOf(c0.class, r1);
    }

    public static c0[] values() {
        return (c0[]) f327a.clone();
    }

    @Override // j$.time.chrono.l
    public final int getValue() {
        return ordinal();
    }
}
