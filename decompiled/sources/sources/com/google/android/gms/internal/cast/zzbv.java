package com.google.android.gms.internal.cast;

import androidx.mediarouter.media.MediaRouteSelector;

/* loaded from: classes4.dex */
final /* synthetic */ class zzbv implements Runnable {
    private final /* synthetic */ zzbx zza;
    private final /* synthetic */ MediaRouteSelector zzb;

    public /* synthetic */ zzbv(zzbx r1, MediaRouteSelector r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzy(this.zzb);
    }
}
