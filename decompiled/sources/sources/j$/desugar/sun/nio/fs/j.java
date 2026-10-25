package j$.desugar.sun.nio.fs;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract /* synthetic */ class j {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f162a = null;

    static {
        int[] r0 = new int[j$.nio.file.a.values().length];
        f162a = r0;
        r0[j$.nio.file.a.READ.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
    L10:
        f162a[j$.nio.file.a.WRITE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
    L12:
        f162a[j$.nio.file.a.EXECUTE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L9
        return;
    }
}
