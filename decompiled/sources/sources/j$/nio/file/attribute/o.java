package j$.nio.file.attribute;

import java.nio.file.attribute.FileOwnerAttributeView;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class o implements FileOwnerAttributeView {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ p f205a;

    public /* synthetic */ o(p r1) {
        this.f205a = r1;
    }

    public final /* synthetic */ boolean equals(Object r2) {
        p r1 = this.f205a;
        if ((r2 instanceof o) == false) goto L6;
        r2 = ((o) r2).f205a;
    L6:
        return r1.equals(r2);
    }

    @Override // java.nio.file.attribute.FileOwnerAttributeView
    public final /* synthetic */ java.nio.file.attribute.UserPrincipal getOwner() {
        return x.a(this.f205a.getOwner());
    }

    public final /* synthetic */ int hashCode() {
        return this.f205a.hashCode();
    }

    @Override // java.nio.file.attribute.FileOwnerAttributeView, java.nio.file.attribute.AttributeView
    public final /* synthetic */ String name() {
        return this.f205a.name();
    }

    @Override // java.nio.file.attribute.FileOwnerAttributeView
    public final /* synthetic */ void setOwner(java.nio.file.attribute.UserPrincipal r1) {
        this.f205a.b(w.a(r1));
    }
}
