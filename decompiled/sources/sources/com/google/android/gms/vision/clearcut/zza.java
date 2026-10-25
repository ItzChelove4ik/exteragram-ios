package com.google.android.gms.vision.clearcut;

import com.google.android.gms.internal.vision.zzfi$zzo;

/* loaded from: classes5.dex */
final class zza implements Runnable {
    private final /* synthetic */ int zza;
    private final /* synthetic */ zzfi$zzo zzb;
    private final /* synthetic */ DynamiteClearcutLogger zzc;

    public zza(DynamiteClearcutLogger r1, int r2, zzfi$zzo r3) {
        this.zzc = r1;
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DynamiteClearcutLogger.zza(this.zzc).zza(this.zza, this.zzb);
    }
}
