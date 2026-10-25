package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzig implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ zzjd zze;

    public zzig(zzjd r1, String r2, String r3, String r4, long r5) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        Objects.requireNonNull(r1);
        this.zze = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String r0 = this.zza;
        if (r0 != null) goto L6;
        zzjd r02 = this.zze;
        String r5 = this.zzb;
        r02.zzL().zzau(r5, null);
        return;
    L6:
        zzlu r4 = new zzlu(this.zzc, r0, this.zzd);
        zzjd r03 = this.zze;
        String r52 = this.zzb;
        r03.zzL().zzau(r52, r4);
    }
}
