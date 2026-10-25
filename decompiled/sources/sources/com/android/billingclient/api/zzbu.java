package com.android.billingclient.api;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzbu implements Runnable {
    public final /* synthetic */ zzce zza;
    public final /* synthetic */ ConsumeParams zzb;
    public final /* synthetic */ ConsumeResponseListener zzc;

    public /* synthetic */ zzbu(zzce r1, ConsumeParams r2, ConsumeResponseListener r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzce.zzan(this.zza, this.zzb, this.zzc);
    }
}
