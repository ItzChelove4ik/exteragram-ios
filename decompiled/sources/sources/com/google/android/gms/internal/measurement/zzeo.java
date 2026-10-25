package com.google.android.gms.internal.measurement;

import java.util.Objects;

/* loaded from: classes.dex */
abstract class zzeo implements Runnable {
    final long zzi;
    final long zzj;
    final boolean zzk;
    final /* synthetic */ zzez zzl;

    public zzeo(zzez r3, boolean r4) {
        Objects.requireNonNull(r3);
        this.zzl = r3;
        this.zzi = r3.zza.currentTimeMillis();
        this.zzj = r3.zza.elapsedRealtime();
        this.zzk = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzl.zzR() == false) goto L11;
        zzb();
        return;
    L11:
        zza();     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        this.zzl.zzN(e, false, this.zzk);
        zzb();
    }

    public abstract void zza();

    public void zzb() {
    }
}
