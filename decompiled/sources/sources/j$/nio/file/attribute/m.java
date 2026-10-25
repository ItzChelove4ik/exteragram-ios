package j$.nio.file.attribute;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class m implements java.nio.file.attribute.FileAttributeView {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ FileAttributeView f203a;

    public /* synthetic */ m(FileAttributeView r1) {
        this.f203a = r1;
    }

    public final /* synthetic */ boolean equals(Object r2) {
        FileAttributeView r1 = this.f203a;
        if ((r2 instanceof m) == false) goto L6;
        r2 = ((m) r2).f203a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f203a.hashCode();
    }

    @Override // java.nio.file.attribute.AttributeView
    public final /* synthetic */ String name() {
        return this.f203a.name();
    }
}
