package j$.nio.file;

import java.nio.file.PathMatcher;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class f0 implements PathMatcher {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ g0 f225a;

    public /* synthetic */ f0(g0 r1) {
        this.f225a = r1;
    }

    public final /* synthetic */ boolean equals(Object r2) {
        g0 r1 = this.f225a;
        if ((r2 instanceof f0) == false) goto L6;
        r2 = ((f0) r2).f225a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f225a.hashCode();
    }

    @Override // java.nio.file.PathMatcher
    public final /* synthetic */ boolean matches(java.nio.file.Path r1) {
        return this.f225a.p(y.s(r1));
    }
}
