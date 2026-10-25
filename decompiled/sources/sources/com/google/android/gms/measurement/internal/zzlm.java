package com.google.android.gms.measurement.internal;

import java.util.Map;

/* loaded from: classes5.dex */
final /* synthetic */ class zzlm implements Runnable {
    private final /* synthetic */ zzln zza;
    private final /* synthetic */ int zzb;
    private final /* synthetic */ Exception zzc;
    private final /* synthetic */ byte[] zzd;
    private final /* synthetic */ Map zze;

    public /* synthetic */ zzlm(zzln r1, int r2, Exception r3, byte[] r4, Map r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze);
    }
}
