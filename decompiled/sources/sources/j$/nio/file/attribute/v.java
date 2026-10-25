package j$.nio.file.attribute;

import java.nio.file.attribute.UserDefinedFileAttributeView;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class v implements FileAttributeView {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ UserDefinedFileAttributeView f211a;

    public /* synthetic */ v(UserDefinedFileAttributeView r1) {
        this.f211a = r1;
    }

    public final /* synthetic */ boolean equals(Object r2) {
        UserDefinedFileAttributeView r1 = this.f211a;
        if ((r2 instanceof v) == false) goto L6;
        r2 = ((v) r2).f211a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f211a.hashCode();
    }

    @Override // j$.nio.file.attribute.b
    public final /* synthetic */ String name() {
        return this.f211a.name();
    }
}
