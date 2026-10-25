package j$.nio.file;

import java.io.Closeable;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class k implements Iterator, Closeable {

    /* renamed from: a, reason: collision with root package name */
    public final p f236a;

    /* renamed from: b, reason: collision with root package name */
    public n f237b;

    static {
    }

    public k(Path r3, FileVisitOption... r4) {
        p r0 = new p(Arrays.asList(r4), Integer.MAX_VALUE);
        this.f236a = r0;
        if (r0.e == true) goto L9;
        n r32 = r0.c(r3, false);
        this.f237b = r32;
        IOException r2 = r32.d;
        if (r2 != null) goto L7;
        return;
    L7:
        throw r2;
    L9:
        throw new IllegalStateException("Closed");
    }

    public final void a() {
        if (this.f237b != null) goto L15;
        n r0 = this.f236a.b();
    L5:
        if (r0 == null) goto L19;
        IOException r1 = r0.d;
        if (r1 != null) goto L14;
        if (r0.f247a != o.END_DIRECTORY) goto L10;
        r0 = this.f236a.b();
        goto L5
    L10:
        this.f237b = r0;
        return;
    L14:
        throw new UncheckedIOException(r1);
    L19:
        return;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f236a.close();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f236a.e == true) goto L11;
        a();
        if (this.f237b == null) goto L8;
        return true;
    L8:
        return false;
    L11:
        throw new IllegalStateException();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.f236a.e == true) goto L11;
        a();
        n r0 = this.f237b;
        if (r0 == null) goto L9;
        this.f237b = null;
        return r0;
    L9:
        throw new NoSuchElementException();
    L11:
        throw new IllegalStateException();
    }
}
