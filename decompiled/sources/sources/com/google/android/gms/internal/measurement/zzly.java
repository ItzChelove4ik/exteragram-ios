package com.google.android.gms.internal.measurement;

import java.util.logging.Level;

/* loaded from: classes4.dex */
final /* synthetic */ class zzly implements Runnable {
    private final /* synthetic */ Level zza;
    private final /* synthetic */ Throwable zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ Object[] zzd;

    public /* synthetic */ zzly(Level r1, Throwable r2, String r3, Object[] r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        ((zzxp) ((zzxp) zzlx.zza.zze(this.zza).zzo(this.zzb)).zzn("com/google/android/libraries/phenotype/client/Phlogger", "logInternal", 44, "Phlogger.java")).zzp(this.zzc, this.zzd);
    }
}
