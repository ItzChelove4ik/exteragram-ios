package com.google.android.play.integrity.internal;

/* loaded from: classes5.dex */
public final class ax implements bb {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f123a = null;

    /* renamed from: b, reason: collision with root package name */
    private volatile bb f124b;

    /* renamed from: c, reason: collision with root package name */
    private volatile Object f125c;

    static {
        f123a = new Object();
    }

    private ax(bb r2) {
        this.f125c = f123a;
        this.f124b = r2;
    }

    public static bb b(bb r1) {
        if ((r1 instanceof ax) == false) goto L6;
        return r1;
    L6:
        return new ax(r1);
    }

    @Override // com.google.android.play.integrity.internal.bd
    public final Object a() {
        Object r1 = this.f125c;
        Object r2 = f123a;
        if (r1 != r2) goto L20;
        monitor-enter(this);
        Object r12 = this.f125c;     // Catch: Throwable -> L13
        if (r12 != r2) goto L16;
        r12 = this.f124b.a();     // Catch: Throwable -> L13
        Object r3 = this.f125c;     // Catch: Throwable -> L13
        if (r3 == r2) goto L15;
        if (r3 == r12) goto L15;
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + r3 + " & " + r12 + ". This is likely due to a circular dependency.");     // Catch: Throwable -> L13
    L15:
        this.f125c = r12;     // Catch: Throwable -> L13
        this.f124b = null;     // Catch: Throwable -> L13
    L16:
        monitor-exit(this);     // Catch: Throwable -> L13
        return r12;
    L13:
        th = move-exception;
        throw th;
    L20:
        return r1;
    }
}
