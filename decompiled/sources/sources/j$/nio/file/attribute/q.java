package j$.nio.file.attribute;

import java.nio.file.attribute.FileStoreAttributeView;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class q implements b {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ FileStoreAttributeView f206a;

    public /* synthetic */ q(FileStoreAttributeView r1) {
        this.f206a = r1;
    }

    public final /* synthetic */ boolean equals(Object r2) {
        FileStoreAttributeView r1 = this.f206a;
        if ((r2 instanceof q) == false) goto L6;
        r2 = ((q) r2).f206a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f206a.hashCode();
    }

    @Override // j$.nio.file.attribute.b
    public final /* synthetic */ String name() {
        throw null;
    }
}
