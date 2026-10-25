package j$.nio.file.attribute;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class d implements java.nio.file.attribute.BasicFileAttributeView {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ BasicFileAttributeView f194a;

    public /* synthetic */ d(BasicFileAttributeView r1) {
        this.f194a = r1;
    }

    public final /* synthetic */ boolean equals(Object r2) {
        BasicFileAttributeView r1 = this.f194a;
        if ((r2 instanceof d) == false) goto L6;
        r2 = ((d) r2).f194a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f194a.hashCode();
    }

    @Override // java.nio.file.attribute.BasicFileAttributeView, java.nio.file.attribute.AttributeView
    public final /* synthetic */ String name() {
        return this.f194a.name();
    }

    @Override // java.nio.file.attribute.BasicFileAttributeView
    public final /* synthetic */ java.nio.file.attribute.BasicFileAttributes readAttributes() {
        return f.a(this.f194a.readAttributes());
    }

    @Override // java.nio.file.attribute.BasicFileAttributeView
    public final /* synthetic */ void setTimes(java.nio.file.attribute.FileTime r1, java.nio.file.attribute.FileTime r2, java.nio.file.attribute.FileTime r3) {
        this.f194a.a(j$.desugar.sun.nio.fs.g.c(r1), j$.desugar.sun.nio.fs.g.c(r2), j$.desugar.sun.nio.fs.g.c(r3));
    }
}
