package j$.nio.file;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum StandardOpenOption extends Enum<StandardOpenOption> implements OpenOption {
    public static final StandardOpenOption APPEND = null;
    public static final StandardOpenOption CREATE = null;
    public static final StandardOpenOption CREATE_NEW = null;
    public static final StandardOpenOption DELETE_ON_CLOSE = null;
    public static final StandardOpenOption DSYNC = null;
    public static final StandardOpenOption READ = null;
    public static final StandardOpenOption SPARSE = null;
    public static final StandardOpenOption SYNC = null;
    public static final StandardOpenOption TRUNCATE_EXISTING = null;
    public static final StandardOpenOption WRITE = null;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ StandardOpenOption[] f184a = null;

    static {
        StandardOpenOption r0 = new StandardOpenOption("READ", 0);
        READ = r0;
        StandardOpenOption r1 = new StandardOpenOption("WRITE", 1);
        WRITE = r1;
        StandardOpenOption r2 = new StandardOpenOption("APPEND", 2);
        APPEND = r2;
        StandardOpenOption r3 = new StandardOpenOption("TRUNCATE_EXISTING", 3);
        TRUNCATE_EXISTING = r3;
        StandardOpenOption r4 = new StandardOpenOption("CREATE", 4);
        CREATE = r4;
        StandardOpenOption r5 = new StandardOpenOption("CREATE_NEW", 5);
        CREATE_NEW = r5;
        StandardOpenOption r6 = new StandardOpenOption("DELETE_ON_CLOSE", 6);
        DELETE_ON_CLOSE = r6;
        StandardOpenOption r7 = new StandardOpenOption("SPARSE", 7);
        SPARSE = r7;
        StandardOpenOption r8 = new StandardOpenOption("SYNC", 8);
        SYNC = r8;
        StandardOpenOption r9 = new StandardOpenOption("DSYNC", 9);
        DSYNC = r9;
        f184a = new StandardOpenOption[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9};
    }

    public static StandardOpenOption valueOf(String r1) {
        return (StandardOpenOption) Enum.valueOf(StandardOpenOption.class, r1);
    }

    public static StandardOpenOption[] values() {
        return (StandardOpenOption[]) f184a.clone();
    }
}
