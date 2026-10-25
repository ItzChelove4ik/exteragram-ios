package j$.nio.file;

import java.nio.file.attribute.FileStoreAttributeView;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class f extends java.nio.file.FileStore {

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f223b = 0;

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ FileStore f224a;

    public /* synthetic */ f(FileStore r1) {
        this.f224a = r1;
    }

    public final /* synthetic */ boolean equals(Object r2) {
        FileStore r1 = this.f224a;
        if ((r2 instanceof f) == false) goto L6;
        r2 = ((f) r2).f224a;
    L6:
        return r1.equals(r2);
    }

    @Override // java.nio.file.FileStore
    public final /* synthetic */ Object getAttribute(String r1) {
        return this.f224a.a(r1);
    }

    @Override // java.nio.file.FileStore
    public final /* synthetic */ long getBlockSize() {
        return this.f224a.b();
    }

    @Override // java.nio.file.FileStore
    public final /* synthetic */ FileStoreAttributeView getFileStoreAttributeView(Class r1) {
        j$.nio.file.attribute.q r0 = this.f224a.c(r1);
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.f206a;
    }

    @Override // java.nio.file.FileStore
    public final /* synthetic */ long getTotalSpace() {
        return this.f224a.d();
    }

    @Override // java.nio.file.FileStore
    public final /* synthetic */ long getUnallocatedSpace() {
        return this.f224a.e();
    }

    @Override // java.nio.file.FileStore
    public final /* synthetic */ long getUsableSpace() {
        return this.f224a.f();
    }

    public final /* synthetic */ int hashCode() {
        return this.f224a.hashCode();
    }

    @Override // java.nio.file.FileStore
    public final /* synthetic */ boolean isReadOnly() {
        return this.f224a.g();
    }

    @Override // java.nio.file.FileStore
    public final /* synthetic */ String name() {
        return this.f224a.h();
    }

    @Override // java.nio.file.FileStore
    public final /* synthetic */ boolean supportsFileAttributeView(Class r1) {
        return this.f224a.i(j$.desugar.sun.nio.fs.g.h(r1));
    }

    @Override // java.nio.file.FileStore
    public final /* synthetic */ boolean supportsFileAttributeView(String r1) {
        return this.f224a.j(r1);
    }

    @Override // java.nio.file.FileStore
    public final /* synthetic */ String type() {
        return this.f224a.k();
    }
}
