package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzkc implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ Bundle zze;
    final /* synthetic */ boolean zzf;
    final /* synthetic */ boolean zzg;
    final /* synthetic */ boolean zzh;
    final /* synthetic */ String zzi;
    final /* synthetic */ zzlj zzj;

    public zzkc(zzlj r1, String r2, String r3, long r4, long r6, Bundle r8, boolean r9, boolean r10, boolean r11, String r12) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r6;
        this.zze = r8;
        this.zzf = r9;
        this.zzg = r10;
        this.zzh = r11;
        this.zzi = r12;
        Objects.requireNonNull(r1);
        this.zzj = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzj.zzG(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi);
    }
}
