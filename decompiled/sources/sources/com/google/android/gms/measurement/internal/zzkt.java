package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzkt implements Runnable {
    final /* synthetic */ zzba zza;
    final /* synthetic */ zzlj zzb;

    public zzkt(zzlj r1, zzba r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzlj r0 = this.zzb;
        zzic r1 = r0.zzu;
        zzhh r2 = r1.zzd();
        zzic r3 = r2.zzu;
        r2.zzg();
        zzba r32 = r2.zzj();
        zzba r5 = this.zza;
        if (zzjl.zzu(r5.zzb(), r32.zzb()) == false) goto L10;
        SharedPreferences.Editor r22 = r2.zzd().edit();
        r22.putString("dma_consent_settings", r5.zze());
        r22.apply();
        r1.zzaW().zzk().zzb("Setting DMA consent(FE)", r5);
        zzic r52 = r0.zzu;
        if (r52.zzt().zzP() == false) goto L8;
        r52.zzt().zzl();
        return;
    L8:
        r52.zzt().zzj(false);
        return;
    L10:
        r1.zzaW().zzi().zzb("Lower precedence consent source ignored, proposed source", Integer.valueOf(r5.zzb()));
    }
}
