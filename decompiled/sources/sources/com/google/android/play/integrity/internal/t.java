package com.google.android.play.integrity.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes5.dex */
public abstract class t implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private final TaskCompletionSource f130a;

    public t() {
        this.f130a = null;
    }

    public t(TaskCompletionSource r1) {
        this.f130a = r1;
    }

    public void a(Exception r1) {
        TaskCompletionSource r0 = this.f130a;
        if (r0 == null) goto L6;
        r0.trySetException(r1);
        return;
    }

    public abstract void b();

    public final TaskCompletionSource c() {
        return this.f130a;
    }

    @Override // java.lang.Runnable
    public final void run() {
        b();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        a(e);
    }
}
