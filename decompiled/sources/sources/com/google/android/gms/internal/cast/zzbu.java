package com.google.android.gms.internal.cast;

import androidx.mediarouter.media.MediaRouteSelector;

/* loaded from: classes4.dex */
final /* synthetic */ class zzbu implements Runnable {
    private final /* synthetic */ zzbx zza;
    private final /* synthetic */ MediaRouteSelector zzb;
    private final /* synthetic */ int zzc;

    public /* synthetic */ zzbu(zzbx r1, MediaRouteSelector r2, int r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzx(this.zzb, this.zzc);
    }
}
