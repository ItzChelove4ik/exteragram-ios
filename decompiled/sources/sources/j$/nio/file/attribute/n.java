package j$.nio.file.attribute;

import java.nio.file.attribute.FileOwnerAttributeView;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class n implements p {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ FileOwnerAttributeView f204a;

    public /* synthetic */ n(FileOwnerAttributeView r1) {
        this.f204a = r1;
    }

    @Override // j$.nio.file.attribute.p
    public final /* synthetic */ void b(UserPrincipal r1) {
        this.f204a.setOwner(x.a(r1));
    }

    public final /* synthetic */ boolean equals(Object r2) {
        FileOwnerAttributeView r1 = this.f204a;
        if ((r2 instanceof n) == false) goto L6;
        r2 = ((n) r2).f204a;
    L6:
        return r1.equals(r2);
    }

    @Override // j$.nio.file.attribute.p
    public final /* synthetic */ UserPrincipal getOwner() {
        return w.a(this.f204a.getOwner());
    }

    public final /* synthetic */ int hashCode() {
        return this.f204a.hashCode();
    }

    @Override // j$.nio.file.attribute.b
    public final /* synthetic */ String name() {
        return this.f204a.name();
    }
}
