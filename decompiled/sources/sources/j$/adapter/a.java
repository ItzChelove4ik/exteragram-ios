package j$.adapter;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public static final boolean f141a = false;

    /* renamed from: b, reason: collision with root package name */
    public static final boolean f142b = false;

    static {
        Class.forName("java.util.StringJoiner");     // Catch: ClassNotFoundException -> L13
    L4:
        boolean r1 = false;
        Class.forName("java.nio.file.FileSystems");     // Catch: ClassNotFoundException -> L7
        boolean r0 = true;
    L8:
        f141a = r0;
        Class.forName("android.os.Build");     // Catch: ClassNotFoundException -> L14
        r1 = true;
    L11:
        f142b = r1;
        return;
    L7:
        r0 = false;
        goto L8
    }
}
