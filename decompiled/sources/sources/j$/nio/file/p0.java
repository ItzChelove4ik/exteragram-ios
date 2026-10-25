package j$.nio.file;

import java.nio.file.WatchKey;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class p0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ WatchKey f255a;

    public /* synthetic */ p0(WatchKey r1) {
        this.f255a = r1;
    }

    public static /* synthetic */ p0 a(WatchKey r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return new p0(r1);
    }

    public final /* synthetic */ boolean equals(Object r2) {
        WatchKey r1 = this.f255a;
        if ((r2 instanceof p0) == false) goto L6;
        r2 = ((p0) r2).f255a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f255a.hashCode();
    }
}
