package com.google.android.gms.vision;

/* loaded from: classes5.dex */
public abstract class Detector<T> {
    private final Object zza;

    public Detector() {
        this.zza = new Object();
    }

    public void release() {
        Object r1 = this.zza;
        monitor-enter(r1);
        monitor-exit(r1);     // Catch: Throwable -> L6
        return;
    L6:
        th = move-exception;
        throw th;
    }
}
