package j$.time.chrono;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum i0 extends Enum implements l {
    public static final i0 BE = null;
    public static final i0 BEFORE_BE = null;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ i0[] f342a = null;

    static {
        i0 r0 = new i0("BEFORE_BE", 0);
        BEFORE_BE = r0;
        i0 r1 = new i0("BE", 1);
        BE = r1;
        f342a = new i0[]{r0, r1};
    }

    public static i0 valueOf(String r1) {
        return (i0) Enum.valueOf(i0.class, r1);
    }

    public static i0[] values() {
        return (i0[]) f342a.clone();
    }

    @Override // j$.time.chrono.l
    public final int getValue() {
        return ordinal();
    }
}
