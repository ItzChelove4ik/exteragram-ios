package j$.nio.file;

import java.util.Iterator;
import java.util.function.Consumer;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class c0 implements Iterable {

    /* renamed from: a, reason: collision with root package name */
    public final Iterable f218a;

    public c0(Iterable r1) {
        this.f218a = r1;
    }

    @Override // java.lang.Iterable
    public final void forEach(Consumer r3) {
        this.f218a.forEach(new a0(r3, 1));
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new d0(this.f218a.iterator());
    }
}
