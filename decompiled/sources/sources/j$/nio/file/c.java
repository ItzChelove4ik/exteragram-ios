package j$.nio.file;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class c implements CopyOption {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ java.nio.file.CopyOption f217a;

    public /* synthetic */ c(java.nio.file.CopyOption r1) {
        this.f217a = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [j$.nio.file.CopyOption, java.nio.file.StandardCopyOption] */
    /* JADX WARN: Type inference failed for: r1v3, types: [j$.nio.file.CopyOption, java.nio.file.StandardCopyOption] */
    /* JADX WARN: Type inference failed for: r1v4, types: [j$.nio.file.CopyOption, java.nio.file.StandardCopyOption] */
    /* JADX WARN: Type inference failed for: r1v6, types: [j$.nio.file.CopyOption, java.nio.file.LinkOption] */
    public static /* synthetic */ CopyOption a(java.nio.file.CopyOption r1) {
        if (r1 != 0) goto L6;
        return null;
    L6:
        if ((r1 instanceof d) == false) goto L10;
        return ((d) r1).f219a;
    L10:
        if ((r1 instanceof LinkOption) == false) goto L14;
        LinkOption r12 = (LinkOption) r1;
        return java.nio.file.LinkOption.NOFOLLOW_LINKS;
    L14:
        if ((r1 instanceof StandardCopyOption) == false) goto L26;
        StandardCopyOption r13 = (StandardCopyOption) r1;
        if (r13 != StandardCopyOption.REPLACE_EXISTING) goto L20;
        return java.nio.file.StandardCopyOption.REPLACE_EXISTING;
    L20:
        if (r13 != StandardCopyOption.COPY_ATTRIBUTES) goto L24;
        return java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
    L24:
        return java.nio.file.StandardCopyOption.ATOMIC_MOVE;
    L26:
        return new c(r1);
    }

    public final /* synthetic */ boolean equals(Object r2) {
        java.nio.file.CopyOption r1 = this.f217a;
        if ((r2 instanceof c) == false) goto L6;
        r2 = ((c) r2).f217a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f217a.hashCode();
    }
}
