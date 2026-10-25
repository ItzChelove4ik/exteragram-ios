package j$.desugar.sun.nio.fs;

import j$.nio.file.Path;
import java.io.File;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.util.Iterator;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class l implements Iterator {

    /* renamed from: a, reason: collision with root package name */
    public final DirectoryStream.Filter f164a;

    /* renamed from: b, reason: collision with root package name */
    public final File[] f165b;

    /* renamed from: c, reason: collision with root package name */
    public int f166c;
    public final /* synthetic */ m d;

    public l(m r1, Path r2, DirectoryStream.Filter r3) {
        this.d = r1;
        this.f166c = 0;
        File[] r22 = r2.toFile().listFiles();
        if (r22 != null) goto L5;
        r22 = new File[0];
    L5:
        this.f165b = r22;
        this.f164a = r3;
    }

    public final o a() {
    L2:
        int r0 = this.f166c;
        File[] r1 = this.f165b;
        if (r0 >= r1.length) goto L4;
        this.f166c = r0 + 1;
        File r02 = r1[r0];
        i r2 = this.d.g;
        String r03 = r02.getPath();
        m r3 = this.d;
        o r12 = new o(r2, r03, r3.e, r3.f);
        if (this.f164a.accept(r12) == false) goto L2;
        return r12;
    L10:
        e = move-exception;
        throw new DirectoryIteratorException(e);
    L4:
        return null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (a() == null) goto L6;
        this.f166c--;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return a();
    }
}
