package com.google.android.gms.tasks;

import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: classes.dex */
final class zzr {
    private final Object zza;
    private Queue zzb;
    private boolean zzc;

    public zzr() {
        this.zza = new Object();
    }

    public final void zza(zzq r3) {
        Object r0 = this.zza;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.zzb != null) goto L9;
        this.zzb = new ArrayDeque();     // Catch: Throwable -> L7
    L9:
        this.zzb.add(r3);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    public final void zzb(Task r3) {
        Object r0 = this.zza;
        monitor-enter(r0);
    L24:
        th = move-exception;
        throw th;
    L5:
        if (this.zzb != null) goto L7;
    L26:
        monitor-exit(r0);     // Catch: Throwable -> L24
        return;
    L7:
        if (this.zzc == true) goto L26;
        this.zzc = true;     // Catch: Throwable -> L24
        monitor-exit(r0);     // Catch: Throwable -> L24
    L11:
        Object r1 = this.zza;
        monitor-enter(r1);
        zzq r02 = (zzq) this.zzb.poll();     // Catch: Throwable -> L18
        if (r02 == null) goto L15;
        monitor-exit(r1);     // Catch: Throwable -> L18
        r02.zza(r3);
        goto L11
    L15:
        this.zzc = false;     // Catch: Throwable -> L18
        monitor-exit(r1);     // Catch: Throwable -> L18
        return;
    L18:
        th = move-exception;
        throw th;
    }
}
