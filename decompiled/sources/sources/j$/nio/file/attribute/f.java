package j$.nio.file.attribute;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class f implements java.nio.file.attribute.BasicFileAttributes {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ BasicFileAttributes f196a;

    public /* synthetic */ f(BasicFileAttributes r1) {
        this.f196a = r1;
    }

    public static /* synthetic */ java.nio.file.attribute.BasicFileAttributes a(BasicFileAttributes r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        if ((r1 instanceof e) == false) goto L10;
        return ((e) r1).f195a;
    L10:
        if ((r1 instanceof h) == false) goto L14;
        return ((h) r1).f198a;
    L14:
        if ((r1 instanceof u) == false) goto L18;
        return ((u) r1).f210a;
    L18:
        return new f(r1);
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ java.nio.file.attribute.FileTime creationTime() {
        return j$.desugar.sun.nio.fs.g.e(this.f196a.creationTime());
    }

    public final /* synthetic */ boolean equals(Object r2) {
        BasicFileAttributes r1 = this.f196a;
        if ((r2 instanceof f) == false) goto L6;
        r2 = ((f) r2).f196a;
    L6:
        return r1.equals(r2);
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ Object fileKey() {
        return this.f196a.fileKey();
    }

    public final /* synthetic */ int hashCode() {
        return this.f196a.hashCode();
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ boolean isDirectory() {
        return this.f196a.isDirectory();
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ boolean isOther() {
        return this.f196a.isOther();
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ boolean isRegularFile() {
        return this.f196a.isRegularFile();
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ boolean isSymbolicLink() {
        return this.f196a.isSymbolicLink();
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ java.nio.file.attribute.FileTime lastAccessTime() {
        return j$.desugar.sun.nio.fs.g.e(this.f196a.lastAccessTime());
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ java.nio.file.attribute.FileTime lastModifiedTime() {
        return j$.desugar.sun.nio.fs.g.e(this.f196a.lastModifiedTime());
    }

    @Override // java.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ long size() {
        return this.f196a.size();
    }
}
