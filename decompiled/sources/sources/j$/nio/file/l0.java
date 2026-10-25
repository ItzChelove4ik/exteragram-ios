package j$.nio.file;

import java.nio.file.WatchEvent;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class l0 implements n0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ WatchEvent.Kind f242a;

    public /* synthetic */ l0(WatchEvent.Kind r1) {
        this.f242a = r1;
    }

    public final /* synthetic */ boolean equals(Object r2) {
        WatchEvent.Kind r1 = this.f242a;
        if ((r2 instanceof l0) == false) goto L6;
        r2 = ((l0) r2).f242a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f242a.hashCode();
    }

    @Override // j$.nio.file.n0
    public final /* synthetic */ String name() {
        return this.f242a.name();
    }

    @Override // j$.nio.file.n0
    public final /* synthetic */ Class type() {
        return this.f242a.type();
    }
}
