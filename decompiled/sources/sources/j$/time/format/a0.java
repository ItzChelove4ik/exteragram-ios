package j$.time.format;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum a0 extends Enum {
    public static final a0 ALWAYS = null;
    public static final a0 EXCEEDS_PAD = null;
    public static final a0 NEVER = null;
    public static final a0 NORMAL = null;
    public static final a0 NOT_NEGATIVE = null;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ a0[] f375a = null;

    static {
        a0 r0 = new a0("NORMAL", 0);
        NORMAL = r0;
        a0 r1 = new a0("ALWAYS", 1);
        ALWAYS = r1;
        a0 r2 = new a0("NEVER", 2);
        NEVER = r2;
        a0 r3 = new a0("NOT_NEGATIVE", 3);
        NOT_NEGATIVE = r3;
        a0 r4 = new a0("EXCEEDS_PAD", 4);
        EXCEEDS_PAD = r4;
        f375a = new a0[]{r0, r1, r2, r3, r4};
    }

    public static a0 valueOf(String r1) {
        return (a0) Enum.valueOf(a0.class, r1);
    }

    public static a0[] values() {
        return (a0[]) f375a.clone();
    }
}
