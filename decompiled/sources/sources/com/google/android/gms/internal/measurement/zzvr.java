package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzvr extends zzvq {
    public zzvr() {
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
