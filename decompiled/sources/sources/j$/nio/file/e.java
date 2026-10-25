package j$.nio.file;

import java.nio.file.attribute.FileStoreAttributeView;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class e extends FileStore {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ java.nio.file.FileStore f221a;

    public e(java.nio.file.FileStore r1) {
        this.f221a = r1;
    }

    @Override // j$.nio.file.FileStore
    public final /* synthetic */ Object a(String r1) {
        return this.f221a.getAttribute(r1);
    }

    @Override // j$.nio.file.FileStore
    public final /* synthetic */ long b() {
        return this.f221a.getBlockSize();
    }

    @Override // j$.nio.file.FileStore
    public final /* synthetic */ j$.nio.file.attribute.q c(Class r1) {
        FileStoreAttributeView r0 = this.f221a.getFileStoreAttributeView(r1);
        if (r0 != null) goto L7;
        return null;
    L7:
        return new j$.nio.file.attribute.q(r0);
    }

    @Override // j$.nio.file.FileStore
    public final /* synthetic */ long d() {
        return this.f221a.getTotalSpace();
    }

    @Override // j$.nio.file.FileStore
    public final /* synthetic */ long e() {
        return this.f221a.getUnallocatedSpace();
    }

    public final /* synthetic */ boolean equals(Object r2) {
        java.nio.file.FileStore r1 = this.f221a;
        if ((r2 instanceof e) == false) goto L6;
        r2 = ((e) r2).f221a;
    L6:
        return r1.equals(r2);
    }

    @Override // j$.nio.file.FileStore
    public final /* synthetic */ long f() {
        return this.f221a.getUsableSpace();
    }

    @Override // j$.nio.file.FileStore
    public final /* synthetic */ boolean g() {
        return this.f221a.isReadOnly();
    }

    @Override // j$.nio.file.FileStore
    public final /* synthetic */ String h() {
        return this.f221a.name();
    }

    public final /* synthetic */ int hashCode() {
        return this.f221a.hashCode();
    }

    @Override // j$.nio.file.FileStore
    public final /* synthetic */ boolean i(Class r1) {
        return this.f221a.supportsFileAttributeView(j$.desugar.sun.nio.fs.g.h(r1));
    }

    @Override // j$.nio.file.FileStore
    public final /* synthetic */ boolean j(String r1) {
        return this.f221a.supportsFileAttributeView(r1);
    }

    @Override // j$.nio.file.FileStore
    public final /* synthetic */ String k() {
        return this.f221a.type();
    }
}
