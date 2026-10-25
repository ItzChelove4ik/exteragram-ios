package j$.nio.file;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum FileVisitResult extends Enum<FileVisitResult> {
    public static final FileVisitResult CONTINUE = null;
    public static final FileVisitResult SKIP_SIBLINGS = null;
    public static final FileVisitResult SKIP_SUBTREE = null;
    public static final FileVisitResult TERMINATE = null;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ FileVisitResult[] f180a = null;

    static {
        FileVisitResult r0 = new FileVisitResult("CONTINUE", 0);
        CONTINUE = r0;
        FileVisitResult r1 = new FileVisitResult("TERMINATE", 1);
        TERMINATE = r1;
        FileVisitResult r2 = new FileVisitResult("SKIP_SUBTREE", 2);
        SKIP_SUBTREE = r2;
        FileVisitResult r3 = new FileVisitResult("SKIP_SIBLINGS", 3);
        SKIP_SIBLINGS = r3;
        f180a = new FileVisitResult[]{r0, r1, r2, r3};
    }

    public static FileVisitResult valueOf(String r1) {
        return (FileVisitResult) Enum.valueOf(FileVisitResult.class, r1);
    }

    public static FileVisitResult[] values() {
        return (FileVisitResult[]) f180a.clone();
    }
}
