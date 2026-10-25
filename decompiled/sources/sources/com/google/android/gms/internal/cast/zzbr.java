package com.google.android.gms.internal.cast;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.mediarouter.media.MediaRouter;

/* loaded from: classes4.dex */
final /* synthetic */ class zzbr implements Runnable {
    private final /* synthetic */ zzbt zza;
    private final /* synthetic */ MediaRouter.RouteInfo zzb;
    private final /* synthetic */ MediaRouter.RouteInfo zzc;
    private final /* synthetic */ CallbackToFutureAdapter.Completer zzd;

    public /* synthetic */ zzbr(zzbt r1, MediaRouter.RouteInfo r2, MediaRouter.RouteInfo r3, CallbackToFutureAdapter.Completer r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzb(this.zzb, this.zzc, this.zzd);
    }
}
