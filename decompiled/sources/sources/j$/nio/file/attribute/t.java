package j$.nio.file.attribute;

import java.nio.file.attribute.PosixFileAttributeView;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class t implements BasicFileAttributeView, p {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PosixFileAttributeView f209a;

    public /* synthetic */ t(PosixFileAttributeView r1) {
        this.f209a = r1;
    }

    @Override // j$.nio.file.attribute.BasicFileAttributeView
    public final /* synthetic */ void a(FileTime r1, FileTime r2, FileTime r3) {
        this.f209a.setTimes(j$.desugar.sun.nio.fs.g.e(r1), j$.desugar.sun.nio.fs.g.e(r2), j$.desugar.sun.nio.fs.g.e(r3));
    }

    @Override // j$.nio.file.attribute.p
    public final /* synthetic */ void b(UserPrincipal r1) {
        this.f209a.setOwner(x.a(r1));
    }

    public final /* synthetic */ boolean equals(Object r2) {
        PosixFileAttributeView r1 = this.f209a;
        if ((r2 instanceof t) == false) goto L6;
        r2 = ((t) r2).f209a;
    L6:
        return r1.equals(r2);
    }

    @Override // j$.nio.file.attribute.p
    public final /* synthetic */ UserPrincipal getOwner() {
        return w.a(this.f209a.getOwner());
    }

    public final /* synthetic */ int hashCode() {
        return this.f209a.hashCode();
    }

    @Override // j$.nio.file.attribute.b
    public final /* synthetic */ String name() {
        return this.f209a.name();
    }

    @Override // j$.nio.file.attribute.BasicFileAttributeView
    public final /* synthetic */ BasicFileAttributes readAttributes() {
        return e.a(this.f209a.readAttributes());
    }
}
