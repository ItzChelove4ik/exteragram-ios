package j$.nio.file.attribute;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class l implements FileAttributeView {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ java.nio.file.attribute.FileAttributeView f202a;

    public /* synthetic */ l(java.nio.file.attribute.FileAttributeView r1) {
        this.f202a = r1;
    }

    public final /* synthetic */ boolean equals(Object r2) {
        java.nio.file.attribute.FileAttributeView r1 = this.f202a;
        if ((r2 instanceof l) == false) goto L6;
        r2 = ((l) r2).f202a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f202a.hashCode();
    }

    @Override // j$.nio.file.attribute.b
    public final /* synthetic */ String name() {
        return this.f202a.name();
    }
}
