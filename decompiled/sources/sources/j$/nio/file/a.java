package j$.nio.file;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum a extends Enum {
    public static final a EXECUTE = null;
    public static final a READ = null;
    public static final a WRITE = null;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ a[] f185a = null;

    static {
        a r0 = new a("READ", 0);
        READ = r0;
        a r1 = new a("WRITE", 1);
        WRITE = r1;
        a r2 = new a("EXECUTE", 2);
        EXECUTE = r2;
        f185a = new a[]{r0, r1, r2};
    }

    public static a valueOf(String r1) {
        return (a) Enum.valueOf(a.class, r1);
    }

    public static a[] values() {
        return (a[]) f185a.clone();
    }
}
