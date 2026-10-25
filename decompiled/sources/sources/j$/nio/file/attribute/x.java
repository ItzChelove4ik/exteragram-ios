package j$.nio.file.attribute;

import javax.security.auth.Subject;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class x implements java.nio.file.attribute.UserPrincipal {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ UserPrincipal f213a;

    public /* synthetic */ x(UserPrincipal r1) {
        this.f213a = r1;
    }

    public static /* synthetic */ java.nio.file.attribute.UserPrincipal a(UserPrincipal r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        if ((r1 instanceof w) == false) goto L10;
        return ((w) r1).f212a;
    L10:
        if ((r1 instanceof s) == false) goto L14;
        return ((s) r1).f208a;
    L14:
        return new x(r1);
    }

    @Override // java.security.Principal
    public final /* synthetic */ boolean equals(Object r2) {
        UserPrincipal r1 = this.f213a;
        if ((r2 instanceof x) == false) goto L6;
        r2 = ((x) r2).f213a;
    L6:
        return r1.equals(r2);
    }

    @Override // java.security.Principal
    public final /* synthetic */ String getName() {
        return this.f213a.getName();
    }

    @Override // java.security.Principal
    public final /* synthetic */ int hashCode() {
        return this.f213a.hashCode();
    }

    @Override // java.security.Principal
    public final /* synthetic */ boolean implies(Subject r1) {
        return this.f213a.implies(r1);
    }

    @Override // java.security.Principal
    public final /* synthetic */ String toString() {
        return this.f213a.toString();
    }
}
