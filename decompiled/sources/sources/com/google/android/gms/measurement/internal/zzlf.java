package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* loaded from: classes5.dex */
final /* synthetic */ class zzlf implements Runnable {
    private final /* synthetic */ zzlj zza;
    private final /* synthetic */ Bundle zzb;

    public /* synthetic */ zzlf(zzlj r1, Bundle r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzag(this.zzb);
    }
}
