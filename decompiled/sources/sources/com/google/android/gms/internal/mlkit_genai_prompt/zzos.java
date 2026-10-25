package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;

/* loaded from: classes4.dex */
final class zzos implements Runnable {
    final ListenableFuture zza;
    final zzor zzb;

    public zzos(ListenableFuture r1, zzor r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        ListenableFuture r0 = this.zza;
        if ((r0 instanceof zzpn) == false) goto L18;
        Throwable r1 = zzpo.zza((zzpn) r0);
        if (r1 == null) goto L18;
        this.zzb.zza(r1);
        return;
    L18:
        Object r02 = zzot.zzi(r0);     // Catch: Throwable -> L12 ExecutionException -> L15
        this.zzb.zzb(r02);
        return;
    L15:
        e = move-exception;
        this.zzb.zza(e.getCause());
        return;
    L12:
        th = move-exception;
        this.zzb.zza(th);
    }

    public final String toString() {
        zzkx r0 = zzky.zza(this);
        r0.zzc(this.zzb);
        return r0.toString();
    }
}
