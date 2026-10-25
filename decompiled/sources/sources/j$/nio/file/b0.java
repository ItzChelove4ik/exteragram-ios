package j$.nio.file;

import java.nio.file.DirectoryStream;
import java.util.Iterator;
import java.util.function.Consumer;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class b0 implements DirectoryStream {

    /* renamed from: a, reason: collision with root package name */
    public final DirectoryStream f216a;

    public b0(DirectoryStream r1) {
        this.f216a = r1;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f216a.close();
    }

    @Override // java.lang.Iterable
    public final void forEach(Consumer r3) {
        this.f216a.forEach(new a0(r3, 0));
    }

    @Override // java.nio.file.DirectoryStream, java.lang.Iterable
    public final Iterator iterator() {
        return new d0(this.f216a.iterator());
    }
}
