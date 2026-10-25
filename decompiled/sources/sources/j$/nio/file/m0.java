package j$.nio.file;

import java.nio.file.WatchEvent;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class m0 implements WatchEvent.Kind {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ n0 f246a;

    public /* synthetic */ m0(n0 r1) {
        this.f246a = r1;
    }

    public final /* synthetic */ boolean equals(Object r2) {
        n0 r1 = this.f246a;
        if ((r2 instanceof m0) == false) goto L6;
        r2 = ((m0) r2).f246a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f246a.hashCode();
    }

    @Override // java.nio.file.WatchEvent.Kind
    public final /* synthetic */ String name() {
        return this.f246a.name();
    }

    @Override // java.nio.file.WatchEvent.Kind
    public final /* synthetic */ Class type() {
        return this.f246a.type();
    }
}
