package j$.nio.file.attribute;

import java.nio.file.attribute.GroupPrincipal;
import javax.security.auth.Subject;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class w implements UserPrincipal {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ java.nio.file.attribute.UserPrincipal f212a;

    public /* synthetic */ w(java.nio.file.attribute.UserPrincipal r1) {
        this.f212a = r1;
    }

    public static /* synthetic */ UserPrincipal a(java.nio.file.attribute.UserPrincipal r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        if ((r1 instanceof x) == false) goto L10;
        return ((x) r1).f213a;
    L10:
        if ((r1 instanceof GroupPrincipal) == false) goto L14;
        return new s((GroupPrincipal) r1);
    L14:
        return new w(r1);
    }

    @Override // java.security.Principal
    public final /* synthetic */ boolean equals(Object r2) {
        java.nio.file.attribute.UserPrincipal r1 = this.f212a;
        if ((r2 instanceof w) == false) goto L6;
        r2 = ((w) r2).f212a;
    L6:
        return r1.equals(r2);
    }

    @Override // java.security.Principal
    public final /* synthetic */ String getName() {
        return this.f212a.getName();
    }

    @Override // java.security.Principal
    public final /* synthetic */ int hashCode() {
        return this.f212a.hashCode();
    }

    @Override // java.security.Principal
    public final /* synthetic */ boolean implies(Subject r1) {
        return this.f212a.implies(r1);
    }

    @Override // java.security.Principal
    public final /* synthetic */ String toString() {
        return this.f212a.toString();
    }
}
