package com.android.billingclient.api;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzam implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ ConsumeResponseListener zzb;
    public final /* synthetic */ ConsumeParams zzc;

    public /* synthetic */ zzam(BillingClientImpl r1, ConsumeResponseListener r2, ConsumeParams r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BillingClientImpl.zzH(this.zza, this.zzb, this.zzc);
    }
}
