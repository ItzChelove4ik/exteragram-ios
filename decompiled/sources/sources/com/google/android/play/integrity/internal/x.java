package com.google.android.play.integrity.internal;

/* loaded from: classes5.dex */
final class x extends t {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ ae f137a;

    public x(ae r1) {
        this.f137a = r1;
    }

    @Override // com.google.android.play.integrity.internal.t
    public final void b() {
        Object r0 = ae.h(this.f137a);
        monitor-enter(r0);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (ae.j(this.f137a).get() > 0) goto L7;
    L13:
        ae r1 = this.f137a;     // Catch: Throwable -> L11
        if (ae.d(r1) == null) goto L16;
        ae.f(r1).d("Unbind from service.", new Object[0]);     // Catch: Throwable -> L11
        ae r12 = this.f137a;     // Catch: Throwable -> L11
        ae.a(r12).unbindService(ae.b(r12));     // Catch: Throwable -> L11
        ae.m(this.f137a, false);     // Catch: Throwable -> L11
        ae.n(this.f137a, null);     // Catch: Throwable -> L11
        ae.l(this.f137a, null);     // Catch: Throwable -> L11
    L16:
        ae.p(this.f137a);     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return;
    L7:
        if (ae.j(this.f137a).decrementAndGet() <= 0) goto L13;
        ae.f(this.f137a).d("Leaving the connection open for other ongoing calls.", new Object[0]);     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
    }
}
