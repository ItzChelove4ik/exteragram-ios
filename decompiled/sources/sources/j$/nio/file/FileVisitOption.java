package j$.nio.file;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum FileVisitOption extends Enum<FileVisitOption> {
    public static final FileVisitOption FOLLOW_LINKS = null;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ FileVisitOption[] f179a = null;

    static {
        FileVisitOption r0 = new FileVisitOption("FOLLOW_LINKS", 0);
        FOLLOW_LINKS = r0;
        f179a = new FileVisitOption[]{r0};
    }

    public static FileVisitOption valueOf(String r1) {
        return (FileVisitOption) Enum.valueOf(FileVisitOption.class, r1);
    }

    public static FileVisitOption[] values() {
        return (FileVisitOption[]) f179a.clone();
    }
}
