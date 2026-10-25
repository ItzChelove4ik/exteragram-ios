package com.google.android.gms.internal.play_billing;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* loaded from: classes5.dex */
final class zzct implements Runnable {
    final Future zza;
    final zzcs zzb;

    public zzct(Future r1, zzcs r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        Future r0 = this.zza;
        if ((r0 instanceof zzdf) == false) goto L36;
        Throwable r1 = zzdg.zza((zzdf) r0);
        if (r1 == null) goto L36;
        this.zzb.zza(r1);
        return;
    L36:
    L18:
        e = move-exception;
        this.zzb.zza(e.getCause());
        return;
    L16:
        th = move-exception;
        this.zzb.zza(th);
        return;
    L10:
        if (r0.isDone() == false) goto L29;
        boolean r12 = false;
        Future r02 = r0;
    L34:
        Object r03 = r02.get();     // Catch: Throwable -> L22 InterruptedException -> L27
    L13:
        if (r12 == false) goto L20;
        Thread.currentThread().interrupt();     // Catch: Throwable -> L16 ExecutionException -> L18
    L20:
        this.zzb.zzb(r03);
        return;
    L27:
        r12 = true;
        r02 = r02;
    L22:
        th = move-exception;
        if (r12 == false) goto L26;
        Thread.currentThread().interrupt();     // Catch: Throwable -> L16 ExecutionException -> L18
    L26:
        throw th;     // Catch: Throwable -> L16 ExecutionException -> L18
    L29:
        throw new IllegalStateException(zzbj.zza("Future was expected to be done: %s", new Object[]{r0}));     // Catch: Throwable -> L16 ExecutionException -> L18
    }

    public final String toString() {
        zzbc r0 = zzbe.zza(this);
        r0.zza(this.zzb);
        return r0.toString();
    }
}
