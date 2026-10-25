package j$.nio.file.attribute;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class j implements java.nio.file.attribute.FileAttribute {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ FileAttribute f200a;

    public /* synthetic */ j(FileAttribute r1) {
        this.f200a = r1;
    }

    public final /* synthetic */ boolean equals(Object r2) {
        FileAttribute r1 = this.f200a;
        if ((r2 instanceof j) == false) goto L6;
        r2 = ((j) r2).f200a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f200a.hashCode();
    }

    @Override // java.nio.file.attribute.FileAttribute
    public final /* synthetic */ String name() {
        return this.f200a.name();
    }

    @Override // java.nio.file.attribute.FileAttribute
    public final /* synthetic */ Object value() {
        return this.f200a.value();
    }
}
