package c;

/* loaded from: classes.dex */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    public final String f15a;

    /* renamed from: b, reason: collision with root package name */
    public volatile boolean f16b;

    public g(String r1, boolean r2) {
        this.f15a = r1;
        this.f16b = r2;
    }

    public final synchronized void a() {
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L4:
        if (this.f16b == false) goto L7;
        monitor-exit(this);
        return;
    L7:
        String r0 = this.f15a;     // Catch: Throwable -> L12
        if (r0 != null) goto L14;
        this.f16b = true;     // Catch: Throwable -> L12
        monitor-exit(this);
        return;
    L14:
        a.a(r0);     // Catch: Throwable -> L12
        this.f16b = true;     // Catch: Throwable -> L12
        monitor-exit(this);
    }
}
