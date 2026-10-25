package j$.nio.file.attribute;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class c implements BasicFileAttributeView {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ java.nio.file.attribute.BasicFileAttributeView f193a;

    public /* synthetic */ c(java.nio.file.attribute.BasicFileAttributeView r1) {
        this.f193a = r1;
    }

    @Override // j$.nio.file.attribute.BasicFileAttributeView
    public final /* synthetic */ void a(FileTime r1, FileTime r2, FileTime r3) {
        this.f193a.setTimes(j$.desugar.sun.nio.fs.g.e(r1), j$.desugar.sun.nio.fs.g.e(r2), j$.desugar.sun.nio.fs.g.e(r3));
    }

    public final /* synthetic */ boolean equals(Object r2) {
        java.nio.file.attribute.BasicFileAttributeView r1 = this.f193a;
        if ((r2 instanceof c) == false) goto L6;
        r2 = ((c) r2).f193a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f193a.hashCode();
    }

    @Override // j$.nio.file.attribute.b
    public final /* synthetic */ String name() {
        return this.f193a.name();
    }

    @Override // j$.nio.file.attribute.BasicFileAttributeView
    public final /* synthetic */ BasicFileAttributes readAttributes() {
        return e.a(this.f193a.readAttributes());
    }
}
