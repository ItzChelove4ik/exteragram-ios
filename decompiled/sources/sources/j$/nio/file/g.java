package j$.nio.file;

import java.nio.file.PathMatcher;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.util.Set;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class g extends FileSystem {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ java.nio.file.FileSystem f226a;

    public g(java.nio.file.FileSystem r1) {
        this.f226a = r1;
    }

    public static /* synthetic */ FileSystem i(java.nio.file.FileSystem r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        if ((r1 instanceof h) == false) goto L10;
        return ((h) r1).f227a;
    L10:
        return new g(r1);
    }

    @Override // j$.nio.file.FileSystem
    public final /* synthetic */ Iterable a() {
        return this.f226a.getFileStores();
    }

    @Override // j$.nio.file.FileSystem
    public final /* synthetic */ g0 b(String r1) {
        PathMatcher r0 = this.f226a.getPathMatcher(r1);
        if (r0 != null) goto L7;
        return null;
    L7:
        if ((r0 instanceof f0) == false) goto L11;
        return ((f0) r0).f225a;
    L11:
        return new e0(r0);
    }

    @Override // j$.nio.file.FileSystem
    public final Iterable c() {
        return new c0(this.f226a.getRootDirectories());
    }

    @Override // j$.nio.file.FileSystem, java.io.Closeable, java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        this.f226a.close();
    }

    @Override // j$.nio.file.FileSystem
    public final /* synthetic */ j$.nio.file.attribute.y d() {
        UserPrincipalLookupService r1 = this.f226a.getUserPrincipalLookupService();
        if (r1 != null) goto L7;
        return null;
    L7:
        return new j$.nio.file.attribute.y(r1);
    }

    @Override // j$.nio.file.FileSystem
    public final /* synthetic */ boolean e() {
        return this.f226a.isReadOnly();
    }

    public final /* synthetic */ boolean equals(Object r2) {
        java.nio.file.FileSystem r1 = this.f226a;
        if ((r2 instanceof g) == false) goto L6;
        r2 = ((g) r2).f226a;
    L6:
        return r1.equals(r2);
    }

    @Override // j$.nio.file.FileSystem
    public final /* synthetic */ q0 f() {
        return q0.a(this.f226a.newWatchService());
    }

    @Override // j$.nio.file.FileSystem
    public final /* synthetic */ j$.nio.file.spi.d g() {
        FileSystemProvider r1 = this.f226a.provider();
        int r0 = j$.nio.file.spi.b.f;
        if (r1 != null) goto L7;
        return null;
    L7:
        if ((r1 instanceof j$.nio.file.spi.c) == false) goto L11;
        return ((j$.nio.file.spi.c) r1).f262a;
    L11:
        return new j$.nio.file.spi.b(r1);
    }

    @Override // j$.nio.file.FileSystem
    public final /* synthetic */ Path getPath(String r1, String[] r2) {
        return y.s(this.f226a.getPath(r1, r2));
    }

    @Override // j$.nio.file.FileSystem
    public final /* synthetic */ String getSeparator() {
        return this.f226a.getSeparator();
    }

    @Override // j$.nio.file.FileSystem
    public final /* synthetic */ Set h() {
        return this.f226a.supportedFileAttributeViews();
    }

    public final /* synthetic */ int hashCode() {
        return this.f226a.hashCode();
    }

    @Override // j$.nio.file.FileSystem
    public final /* synthetic */ boolean isOpen() {
        return this.f226a.isOpen();
    }
}
