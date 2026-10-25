package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzot implements Runnable {
    private final /* synthetic */ zzpg zza;
    private final /* synthetic */ ListenableFuture zzb;

    public /* synthetic */ zzot(zzpg r1, ListenableFuture r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzk(this.zzb);
    }
}
