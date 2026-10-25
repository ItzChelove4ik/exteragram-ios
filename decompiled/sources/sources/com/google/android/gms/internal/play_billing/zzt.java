package com.google.android.gms.internal.play_billing;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
final class zzt implements zzcz {
    final WeakReference zza;
    private final zzo zzb;

    public zzt(zzp r2) {
        this.zzb = new zzs(this);
        this.zza = new WeakReference(r2);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean r2) {
        zzp r0 = (zzp) this.zza.get();
        boolean r1 = this.zzb.cancel(r2);
        if (r1 == false) goto L7;
        if (r0 == null) goto L8;
        r0.zza();
        return true;
    L8:
        return r1;
    L7:
        return r1;
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return this.zzb.get();
    }

    @Override // java.util.concurrent.Future
    public final Object get(long r1, TimeUnit r3) {
        return this.zzb.get(r1, r3);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.zzb.zzc instanceof zze;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.zzb.isDone();
    }

    public final String toString() {
        return this.zzb.toString();
    }

    public final boolean zza(Object r1) {
        return this.zzb.zzd(r1);
    }

    @Override // com.google.android.gms.internal.play_billing.zzcz
    public final void zzb(Runnable r1, Executor r2) {
        this.zzb.zzb(r1, r2);
    }

    public final boolean zzc(Throwable r3) {
        zzg r0 = new zzg(r3);
        zzd r32 = zzo.zzb;
        zzo r2 = this.zzb;
        if (r32.zzd(r2, null, r0) == false) goto L6;
        zzo.zzc(r2);
        return true;
    L6:
        return false;
    }
}
