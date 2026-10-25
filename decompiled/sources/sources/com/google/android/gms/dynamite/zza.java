package com.google.android.gms.dynamite;

import android.os.Process;

/* loaded from: classes.dex */
final class zza extends Thread {
    public zza(ThreadGroup r1, String r2) {
        super(r1, "GmsDynamite");
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Process.setThreadPriority(19);
        monitor-enter(this);
    L12:
        wait();     // Catch: Throwable -> L6 InterruptedException -> L8
    L8:
        return;
    L6:
        th = move-exception;
        throw th;
    }
}
