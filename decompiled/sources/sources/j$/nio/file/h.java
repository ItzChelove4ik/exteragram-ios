package j$.nio.file;

import java.nio.file.PathMatcher;
import java.nio.file.WatchService;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.util.Set;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class h extends java.nio.file.FileSystem {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ FileSystem f227a;

    public /* synthetic */ h(FileSystem r1) {
        this.f227a = r1;
    }

    public static /* synthetic */ java.nio.file.FileSystem a(FileSystem r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        if ((r1 instanceof g) == false) goto L10;
        return ((g) r1).f226a;
    L10:
        return new h(r1);
    }

    @Override // java.nio.file.FileSystem, java.io.Closeable, java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        this.f227a.close();
    }

    public final /* synthetic */ boolean equals(Object r2) {
        FileSystem r1 = this.f227a;
        if ((r2 instanceof h) == false) goto L6;
        r2 = ((h) r2).f227a;
    L6:
        return r1.equals(r2);
    }

    @Override // java.nio.file.FileSystem
    public final /* synthetic */ Iterable getFileStores() {
        return this.f227a.a();
    }

    @Override // java.nio.file.FileSystem
    public final /* synthetic */ java.nio.file.Path getPath(String r1, String[] r2) {
        return z.s(this.f227a.getPath(r1, r2));
    }

    @Override // java.nio.file.FileSystem
    public final /* synthetic */ PathMatcher getPathMatcher(String r1) {
        g0 r0 = this.f227a.b(r1);
        if (r0 != null) goto L7;
        return null;
    L7:
        if ((r0 instanceof e0) == false) goto L11;
        return ((e0) r0).f222a;
    L11:
        return new f0(r0);
    }

    @Override // java.nio.file.FileSystem
    public final Iterable getRootDirectories() {
        return new c0(this.f227a.c());
    }

    @Override // java.nio.file.FileSystem
    public final /* synthetic */ String getSeparator() {
        return this.f227a.getSeparator();
    }

    @Override // java.nio.file.FileSystem
    public final /* synthetic */ UserPrincipalLookupService getUserPrincipalLookupService() {
        j$.nio.file.attribute.y r1 = this.f227a.d();
        int r0 = j$.nio.file.attribute.z.f215a;
        if (r1 != null) goto L7;
        return null;
    L7:
        return r1.f214a;
    }

    public final /* synthetic */ int hashCode() {
        return this.f227a.hashCode();
    }

    @Override // java.nio.file.FileSystem
    public final /* synthetic */ boolean isOpen() {
        return this.f227a.isOpen();
    }

    @Override // java.nio.file.FileSystem
    public final /* synthetic */ boolean isReadOnly() {
        return this.f227a.e();
    }

    @Override // java.nio.file.FileSystem
    public final /* synthetic */ WatchService newWatchService() {
        q0 r0 = this.f227a.f();
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.f257a;
    }

    @Override // java.nio.file.FileSystem
    public final /* synthetic */ FileSystemProvider provider() {
        j$.nio.file.spi.d r1 = this.f227a.g();
        int r0 = j$.nio.file.spi.c.f261b;
        if (r1 != null) goto L7;
        return null;
    L7:
        if ((r1 instanceof j$.nio.file.spi.b) == false) goto L11;
        return ((j$.nio.file.spi.b) r1).e;
    L11:
        return new j$.nio.file.spi.c(r1);
    }

    @Override // java.nio.file.FileSystem
    public final /* synthetic */ Set supportedFileAttributeViews() {
        return this.f227a.h();
    }
}
