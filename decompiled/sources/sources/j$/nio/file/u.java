package j$.nio.file;

import java.nio.file.DirectoryStream;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class u implements DirectoryStream.Filter {

    /* renamed from: a, reason: collision with root package name */
    public static final u f266a = null;

    static {
        f266a = new u();
    }

    @Override // java.nio.file.DirectoryStream.Filter
    public final /* bridge */ /* synthetic */ boolean accept(Object r1) {
        Path r12 = (Path) r1;
        return true;
    }
}
