package j$.nio.file;

import java.nio.file.PathMatcher;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class e0 implements g0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PathMatcher f222a;

    public /* synthetic */ e0(PathMatcher r1) {
        this.f222a = r1;
    }

    public final /* synthetic */ boolean equals(Object r2) {
        PathMatcher r1 = this.f222a;
        if ((r2 instanceof e0) == false) goto L6;
        r2 = ((e0) r2).f222a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f222a.hashCode();
    }

    @Override // j$.nio.file.g0
    public final /* synthetic */ boolean p(Path r1) {
        return this.f222a.matches(z.s(r1));
    }
}
