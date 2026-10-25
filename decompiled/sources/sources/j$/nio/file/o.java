package j$.nio.file;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum o extends Enum {
    public static final o END_DIRECTORY = null;
    public static final o ENTRY = null;
    public static final o START_DIRECTORY = null;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ o[] f250a = null;

    static {
        o r0 = new o("START_DIRECTORY", 0);
        START_DIRECTORY = r0;
        o r1 = new o("END_DIRECTORY", 1);
        END_DIRECTORY = r1;
        o r2 = new o("ENTRY", 2);
        ENTRY = r2;
        f250a = new o[]{r0, r1, r2};
    }

    public static o valueOf(String r1) {
        return (o) Enum.valueOf(o.class, r1);
    }

    public static o[] values() {
        return (o[]) f250a.clone();
    }
}
