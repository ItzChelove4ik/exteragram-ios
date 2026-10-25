package j$.nio.file;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class d implements java.nio.file.CopyOption {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ CopyOption f219a;

    public /* synthetic */ d(CopyOption r1) {
        this.f219a = r1;
    }

    public final /* synthetic */ boolean equals(Object r2) {
        CopyOption r1 = this.f219a;
        if ((r2 instanceof d) == false) goto L6;
        r2 = ((d) r2).f219a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f219a.hashCode();
    }
}
