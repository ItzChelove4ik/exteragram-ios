package com.google.android.gms.internal.play_billing;

import com.google.android.gms.internal.play_billing.zzcj;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
final class zzde extends zzcq {
    private zzcz zzd;
    private ScheduledFuture zze;

    private zzde(zzcz r1) {
        this.zzd = r1;
    }

    public static /* bridge */ /* synthetic */ zzcz zzr(zzde r0) {
        return r0.zzd;
    }

    public static zzcz zzs(zzcz r2, long r3, TimeUnit r5, ScheduledExecutorService r6) {
        zzde r32 = new zzde(r2);
        zzdb r4 = new zzdb(r32);
        r32.zze = r6.schedule(r4, 28500, r5);
        r2.zzb(r4, zzcp.zza);
        return r32;
    }

    public static /* bridge */ /* synthetic */ ScheduledFuture zzt(zzde r0) {
        return r0.zze;
    }

    public static /* bridge */ /* synthetic */ void zzu(zzde r0, ScheduledFuture r1) {
        r0.zze = null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcj
    public final String zzd() {
        zzcz r0 = this.zzd;
        ScheduledFuture r5 = this.zze;
        if (r0 == null) goto L11;
        String r02 = "inputFuture=[" + r0.toString() + "]";
        if (r5 == null) goto L10;
        long r1 = r5.getDelay(TimeUnit.MILLISECONDS);
        if (r1 <= 0) goto L10;
        return r02 + ", remaining delay=[" + r1 + " ms]";
    L10:
        return r02;
    L11:
        return null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcj
    public final void zzg() {
        zzcz r0 = this.zzd;
        boolean r1 = this.valueField instanceof zzcj.zza;
        boolean r2 = true;
        if (r0 == null) goto L5;
        boolean r4 = true;
    L7:
        if ((r1 & r4) == false) goto L15;
        Object r12 = this.valueField;
        if ((r12 instanceof zzcj.zza) == true) goto L11;
    L13:
        r2 = false;
    L14:
        r0.cancel(r2);
        goto L15
    L11:
        if (((zzcj.zza) r12).zzc == false) goto L13;
    L15:
        ScheduledFuture r02 = this.zze;
        if (r02 == null) goto L18;
        r02.cancel(false);
    L18:
        this.zzd = null;
        this.zze = null;
        return;
    L5:
        r4 = false;
        goto L7
    }
}
