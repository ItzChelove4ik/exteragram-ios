package com.android.billingclient.api;

import android.os.Bundle;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzw implements Callable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ int zzb;
    public final /* synthetic */ String zzc;
    public final /* synthetic */ String zzd;
    public final /* synthetic */ BillingFlowParams zze;
    public final /* synthetic */ Bundle zzf;

    public /* synthetic */ zzw(BillingClientImpl r1, int r2, String r3, String r4, BillingFlowParams r5, Bundle r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return BillingClientImpl.zze(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf);
    }
}
