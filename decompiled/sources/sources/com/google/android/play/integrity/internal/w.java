package com.google.android.play.integrity.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes5.dex */
final class w extends t {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f134a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ t f135b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ ae f136c;

    public w(ae r1, TaskCompletionSource r2, TaskCompletionSource r3, t r4) {
        this.f134a = r3;
        this.f135b = r4;
        this.f136c = r1;
        super(r2);
    }

    @Override // com.google.android.play.integrity.internal.t
    public final void b() {
        Object r0 = ae.h(this.f136c);
        monitor-enter(r0);
        ae.o(this.f136c, this.f134a);     // Catch: Throwable -> L7
        if (ae.j(this.f136c).getAndIncrement() <= 0) goto L9;
        ae.f(this.f136c).d("Already connected to the service.", new Object[0]);     // Catch: Throwable -> L7
    L9:
        ae.q(this.f136c, this.f135b);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
