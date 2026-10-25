package j$.nio.file;

import java.util.Iterator;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class d0 implements Iterator {

    /* renamed from: a, reason: collision with root package name */
    public final Iterator f220a;

    public d0(Iterator r1) {
        this.f220a = r1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f220a.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return j$.desugar.sun.nio.fs.g.f(this.f220a.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.f220a.remove();
    }
}
