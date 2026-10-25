package j$.adapter;

import android.os.StrictMode;
import j$.nio.file.spi.c;
import j$.nio.file.spi.d;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.spi.FileSystemProvider;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract class b {

    /* renamed from: a, reason: collision with root package name */
    public static final d f143a = null;

    static {
        if (a.f141a == false) goto L12;
        FileSystemProvider r0 = FileSystems.getDefault().provider();
        int r1 = j$.nio.file.spi.b.f;
        if (r0 != null) goto L8;
        d r02 = null;
    L15:
        f143a = r02;
        r02.j(URI.create("file:///"));
        return;
    L8:
        if ((r0 instanceof c) == false) goto L10;
        r02 = ((c) r0).f262a;
        goto L15
    L10:
        r02 = new j$.nio.file.spi.b(r0);
        goto L15
    L12:
        if (a.f142b == false) goto L14;
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(StrictMode.getThreadPolicy()).permitDiskReads().build());
    L14:
        r02 = j$.desugar.sun.nio.fs.d.f152a;
        goto L15
    }
}
