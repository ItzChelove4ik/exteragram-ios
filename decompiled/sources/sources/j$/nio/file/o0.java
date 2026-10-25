package j$.nio.file;

import java.nio.file.WatchEvent;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class o0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ WatchEvent.Modifier f251a;

    public /* synthetic */ o0(WatchEvent.Modifier r1) {
        this.f251a = r1;
    }

    public final /* synthetic */ boolean equals(Object r2) {
        WatchEvent.Modifier r1 = this.f251a;
        if ((r2 instanceof o0) == false) goto L6;
        r2 = ((o0) r2).f251a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f251a.hashCode();
    }
}
