package com.google.android.gms.cast.framework;

import android.content.Context;
import com.google.android.gms.internal.cast.zzbx;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzg implements Runnable {
    private final /* synthetic */ Context zza;
    private final /* synthetic */ CastOptions zzb;
    private final /* synthetic */ OptionsProvider zzc;
    private final /* synthetic */ zzbx zzd;
    private final /* synthetic */ com.google.android.gms.cast.internal.zzn zze;
    private final /* synthetic */ TaskCompletionSource zzf;

    public /* synthetic */ zzg(Context r1, CastOptions r2, OptionsProvider r3, zzbx r4, com.google.android.gms.cast.internal.zzn r5, TaskCompletionSource r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        CastContext.zze(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf);
    }
}
