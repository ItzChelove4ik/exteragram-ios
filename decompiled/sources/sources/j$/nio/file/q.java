package j$.nio.file;

import java.io.UncheckedIOException;
import java.util.Iterator;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class q implements Iterator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Iterator f256a;

    public q(Iterator r1) {
        this.f256a = r1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f256a.hasNext();
    L4:
        e = move-exception;
        throw new UncheckedIOException(e.getCause());
    }

    @Override // java.util.Iterator
    public final Object next() {
        return (Path) this.f256a.next();
    L4:
        e = move-exception;
        throw new UncheckedIOException(e.getCause());
    }
}
