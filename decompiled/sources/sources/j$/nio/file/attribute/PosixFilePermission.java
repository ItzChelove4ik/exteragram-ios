package j$.nio.file.attribute;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum PosixFilePermission extends Enum<PosixFilePermission> {
    public static final PosixFilePermission GROUP_EXECUTE = null;
    public static final PosixFilePermission GROUP_READ = null;
    public static final PosixFilePermission GROUP_WRITE = null;
    public static final PosixFilePermission OTHERS_EXECUTE = null;
    public static final PosixFilePermission OTHERS_READ = null;
    public static final PosixFilePermission OTHERS_WRITE = null;
    public static final PosixFilePermission OWNER_EXECUTE = null;
    public static final PosixFilePermission OWNER_READ = null;
    public static final PosixFilePermission OWNER_WRITE = null;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ PosixFilePermission[] f191a = null;

    static {
        PosixFilePermission r0 = new PosixFilePermission("OWNER_READ", 0);
        OWNER_READ = r0;
        PosixFilePermission r1 = new PosixFilePermission("OWNER_WRITE", 1);
        OWNER_WRITE = r1;
        PosixFilePermission r2 = new PosixFilePermission("OWNER_EXECUTE", 2);
        OWNER_EXECUTE = r2;
        PosixFilePermission r3 = new PosixFilePermission("GROUP_READ", 3);
        GROUP_READ = r3;
        PosixFilePermission r4 = new PosixFilePermission("GROUP_WRITE", 4);
        GROUP_WRITE = r4;
        PosixFilePermission r5 = new PosixFilePermission("GROUP_EXECUTE", 5);
        GROUP_EXECUTE = r5;
        PosixFilePermission r6 = new PosixFilePermission("OTHERS_READ", 6);
        OTHERS_READ = r6;
        PosixFilePermission r7 = new PosixFilePermission("OTHERS_WRITE", 7);
        OTHERS_WRITE = r7;
        PosixFilePermission r8 = new PosixFilePermission("OTHERS_EXECUTE", 8);
        OTHERS_EXECUTE = r8;
        f191a = new PosixFilePermission[]{r0, r1, r2, r3, r4, r5, r6, r7, r8};
    }

    public static PosixFilePermission valueOf(String r1) {
        return (PosixFilePermission) Enum.valueOf(PosixFilePermission.class, r1);
    }

    public static PosixFilePermission[] values() {
        return (PosixFilePermission[]) f191a.clone();
    }
}
