package j$.nio.file;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class x implements Iterator {

    /* renamed from: a, reason: collision with root package name */
    public int f269a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Path f270b;

    public x(Path r1) {
        this.f270b = r1;
        this.f269a = 0;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f269a >= this.f270b.getNameCount()) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.f269a >= this.f270b.getNameCount()) goto L7;
        Path r0 = this.f270b.getName(this.f269a);
        this.f269a++;
        return r0;
    L7:
        throw new NoSuchElementException();
    }
}
