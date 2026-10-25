package com.google.android.gms.internal.play_billing;

/* loaded from: classes5.dex */
final class zzf extends Throwable {
    public zzf(String r1) {
        super("Failure occurred while trying to finish a future.");
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable fillInStackTrace() {
        monitor-enter(this);
        monitor-exit(this);
        return this;
    }
}
