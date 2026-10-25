package com.google.android.gms.cast.framework;

import android.content.Context;
import com.google.android.gms.internal.cast.zzbx;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
final /* synthetic */ class zzj implements Callable {
    private final /* synthetic */ Context zza;
    private final /* synthetic */ CastOptions zzb;
    private final /* synthetic */ OptionsProvider zzc;
    private final /* synthetic */ zzbx zzd;
    private final /* synthetic */ com.google.android.gms.cast.internal.zzn zze;

    public /* synthetic */ zzj(Context r1, CastOptions r2, OptionsProvider r3, zzbx r4, com.google.android.gms.cast.internal.zzn r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        return CastContext.zzd(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
    }
}
