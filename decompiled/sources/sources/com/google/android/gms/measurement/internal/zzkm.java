package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzkm implements Runnable {
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcs zza;
    final /* synthetic */ zzlj zzb;

    public zzkm(zzlj r1, com.google.android.gms.internal.measurement.zzcs r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzlj r0 = this.zzb;
        zzic r1 = r0.zzu.zzh().zzu;
        if (r1.zzd().zzl().zzo(zzjk.zzb) == true) goto L7;
        r1.zzaW().zzh().zza("Analytics storage consent denied; will not get session id");
    L5:
        Long r12 = null;
    L12:
        if (r12 == null) goto L20;
        zzic r02 = r0.zzu;
        r02.zzk().zzap(this.zza, r12.longValue());
        return;
    L20:
        this.zza.zzb(null);     // Catch: RemoteException -> L17
        return;
    L17:
        e = move-exception;
        this.zzb.zzu.zzaW().zzb().zzb("getSessionId failed with exception", e);
        return;
    L7:
        if (r1.zzd().zzo(r1.zzba().currentTimeMillis()) == true) goto L5;
        if (r1.zzd().zzl.zza() == 0) goto L5;
        r12 = Long.valueOf(r1.zzd().zzl.zza());
        goto L12
    }
}
