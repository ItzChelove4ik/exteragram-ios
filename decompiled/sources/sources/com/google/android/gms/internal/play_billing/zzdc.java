package com.google.android.gms.internal.play_billing;

import java.util.concurrent.TimeoutException;

/* loaded from: classes5.dex */
final class zzdc extends TimeoutException {
    public /* synthetic */ zzdc(String r1, zzdd r2) {
        super(r1);
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable fillInStackTrace() {
        monitor-enter(this);
        setStackTrace(new StackTraceElement[0]);     // Catch: Throwable -> L7
        monitor-exit(this);
        return this;
    L7:
        th = move-exception;
        throw th;
    }
}
