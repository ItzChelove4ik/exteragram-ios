package j$.nio.file;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class v implements OpenOption {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ java.nio.file.OpenOption f267a;

    public /* synthetic */ v(java.nio.file.OpenOption r1) {
        this.f267a = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [j$.nio.file.OpenOption, java.nio.file.StandardOpenOption] */
    /* JADX WARN: Type inference failed for: r1v4, types: [j$.nio.file.OpenOption, java.nio.file.LinkOption] */
    public static /* synthetic */ OpenOption a(java.nio.file.OpenOption r1) {
        if (r1 != 0) goto L6;
        return null;
    L6:
        if ((r1 instanceof w) == false) goto L10;
        return ((w) r1).f268a;
    L10:
        if ((r1 instanceof LinkOption) == false) goto L14;
        LinkOption r12 = (LinkOption) r1;
        return java.nio.file.LinkOption.NOFOLLOW_LINKS;
    L14:
        if ((r1 instanceof StandardOpenOption) == false) goto L18;
        return j$.desugar.sun.nio.fs.g.d((StandardOpenOption) r1);
    L18:
        return new v(r1);
    }

    public final /* synthetic */ boolean equals(Object r2) {
        java.nio.file.OpenOption r1 = this.f267a;
        if ((r2 instanceof v) == false) goto L6;
        r2 = ((v) r2).f267a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f267a.hashCode();
    }
}
