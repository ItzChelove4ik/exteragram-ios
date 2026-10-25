package j$.nio.file;

import java.nio.file.DirectoryStream;
import java.util.Iterator;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class m {

    /* renamed from: a, reason: collision with root package name */
    public final Path f243a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f244b;

    /* renamed from: c, reason: collision with root package name */
    public final DirectoryStream f245c;
    public final Iterator d;
    public boolean e;

    public m(Path r1, Object r2, DirectoryStream r3) {
        this.f243a = r1;
        this.f244b = r2;
        this.f245c = r3;
        this.d = r3.iterator();
    }
}
