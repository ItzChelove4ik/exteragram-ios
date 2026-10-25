package j$.time.format;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum z extends Enum {
    public static final z LENIENT = null;
    public static final z SMART = null;
    public static final z STRICT = null;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ z[] f421a = null;

    static {
        z r0 = new z("STRICT", 0);
        STRICT = r0;
        z r1 = new z("SMART", 1);
        SMART = r1;
        z r2 = new z("LENIENT", 2);
        LENIENT = r2;
        f421a = new z[]{r0, r1, r2};
    }

    public static z valueOf(String r1) {
        return (z) Enum.valueOf(z.class, r1);
    }

    public static z[] values() {
        return (z[]) f421a.clone();
    }
}
