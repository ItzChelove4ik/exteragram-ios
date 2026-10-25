package j$.nio.file.attribute;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class i implements FileAttribute {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ java.nio.file.attribute.FileAttribute f199a;

    public /* synthetic */ i(java.nio.file.attribute.FileAttribute r1) {
        this.f199a = r1;
    }

    public final /* synthetic */ boolean equals(Object r2) {
        java.nio.file.attribute.FileAttribute r1 = this.f199a;
        if ((r2 instanceof i) == false) goto L6;
        r2 = ((i) r2).f199a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f199a.hashCode();
    }

    @Override // j$.nio.file.attribute.FileAttribute
    public final /* synthetic */ String name() {
        return this.f199a.name();
    }

    @Override // j$.nio.file.attribute.FileAttribute
    public final /* synthetic */ Object value() {
        return this.f199a.value();
    }
}
