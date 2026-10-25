package j$.nio.file.attribute;

import java.nio.file.attribute.GroupPrincipal;
import javax.security.auth.Subject;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class s implements UserPrincipal {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ GroupPrincipal f208a;

    public /* synthetic */ s(GroupPrincipal r1) {
        this.f208a = r1;
    }

    @Override // java.security.Principal
    public final /* synthetic */ boolean equals(Object r2) {
        GroupPrincipal r1 = this.f208a;
        if ((r2 instanceof s) == false) goto L6;
        r2 = ((s) r2).f208a;
    L6:
        return r1.equals(r2);
    }

    @Override // java.security.Principal
    public final /* synthetic */ String getName() {
        return this.f208a.getName();
    }

    @Override // java.security.Principal
    public final /* synthetic */ int hashCode() {
        return this.f208a.hashCode();
    }

    @Override // java.security.Principal
    public final /* synthetic */ boolean implies(Subject r1) {
        return this.f208a.implies(r1);
    }

    @Override // java.security.Principal
    public final /* synthetic */ String toString() {
        return this.f208a.toString();
    }
}
