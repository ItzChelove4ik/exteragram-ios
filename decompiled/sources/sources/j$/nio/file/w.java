package j$.nio.file;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class w implements java.nio.file.OpenOption {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ OpenOption f268a;

    public /* synthetic */ w(OpenOption r1) {
        this.f268a = r1;
    }

    public static /* synthetic */ java.nio.file.OpenOption a(OpenOption r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        if ((r1 instanceof v) == false) goto L10;
        return ((v) r1).f267a;
    L10:
        if ((r1 instanceof LinkOption) == false) goto L14;
        return java.nio.file.LinkOption.NOFOLLOW_LINKS;
    L14:
        if ((r1 instanceof StandardOpenOption) == false) goto L18;
        return j$.desugar.sun.nio.fs.g.d((StandardOpenOption) r1);
    L18:
        return new w(r1);
    }

    public final /* synthetic */ boolean equals(Object r2) {
        OpenOption r1 = this.f268a;
        if ((r2 instanceof w) == false) goto L6;
        r2 = ((w) r2).f268a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f268a.hashCode();
    }
}
