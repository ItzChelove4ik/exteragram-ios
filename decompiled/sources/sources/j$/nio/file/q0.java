package j$.nio.file;

import java.io.Closeable;
import java.nio.file.WatchService;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class q0 implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ WatchService f257a;

    public /* synthetic */ q0(WatchService r1) {
        this.f257a = r1;
    }

    public static /* synthetic */ q0 a(WatchService r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return new q0(r1);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        this.f257a.close();
    }

    public final /* synthetic */ boolean equals(Object r2) {
        WatchService r1 = this.f257a;
        if ((r2 instanceof q0) == false) goto L6;
        r2 = ((q0) r2).f257a;
    L6:
        return r1.equals(r2);
    }

    public final /* synthetic */ int hashCode() {
        return this.f257a.hashCode();
    }
}
