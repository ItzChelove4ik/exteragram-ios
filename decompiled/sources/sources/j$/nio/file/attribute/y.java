package j$.nio.file.attribute;

import java.nio.file.attribute.UserPrincipalLookupService;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class y {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ UserPrincipalLookupService f214a;

    public y(UserPrincipalLookupService r1) {
        this.f214a = r1;
    }

    public final /* synthetic */ boolean equals(Object r2) {
        UserPrincipalLookupService r1 = this.f214a;
        if ((r2 instanceof y) == false) goto L6;
        r2 = ((y) r2).f214a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f214a.hashCode();
    }
}
