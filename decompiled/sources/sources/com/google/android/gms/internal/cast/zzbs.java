package com.google.android.gms.internal.cast;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.mediarouter.media.MediaRouter;

/* loaded from: classes4.dex */
final /* synthetic */ class zzbs implements CallbackToFutureAdapter.Resolver {
    private final /* synthetic */ zzbt zza;
    private final /* synthetic */ MediaRouter.RouteInfo zzb;
    private final /* synthetic */ MediaRouter.RouteInfo zzc;

    public /* synthetic */ zzbs(zzbt r1, MediaRouter.RouteInfo r2, MediaRouter.RouteInfo r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final /* synthetic */ Object attachCompleter(CallbackToFutureAdapter.Completer r3) {
        return this.zza.zza(this.zzb, this.zzc, r3);
    }
}
