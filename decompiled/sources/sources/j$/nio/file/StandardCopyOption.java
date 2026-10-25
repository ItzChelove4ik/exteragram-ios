package j$.nio.file;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum StandardCopyOption extends Enum<StandardCopyOption> implements CopyOption {
    public static final StandardCopyOption ATOMIC_MOVE = null;
    public static final StandardCopyOption COPY_ATTRIBUTES = null;
    public static final StandardCopyOption REPLACE_EXISTING = null;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ StandardCopyOption[] f183a = null;

    static {
        StandardCopyOption r0 = new StandardCopyOption("REPLACE_EXISTING", 0);
        REPLACE_EXISTING = r0;
        StandardCopyOption r1 = new StandardCopyOption("COPY_ATTRIBUTES", 1);
        COPY_ATTRIBUTES = r1;
        StandardCopyOption r2 = new StandardCopyOption("ATOMIC_MOVE", 2);
        ATOMIC_MOVE = r2;
        f183a = new StandardCopyOption[]{r0, r1, r2};
    }

    public static StandardCopyOption valueOf(String r1) {
        return (StandardCopyOption) Enum.valueOf(StandardCopyOption.class, r1);
    }

    public static StandardCopyOption[] values() {
        return (StandardCopyOption[]) f183a.clone();
    }
}
