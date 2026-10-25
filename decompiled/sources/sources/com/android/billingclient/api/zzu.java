package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzp;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzu implements com.google.android.gms.internal.play_billing.zzr {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ int zzb;

    public /* synthetic */ zzu(BillingClientImpl r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    public final Object zza(zzp r2) {
        return BillingClientImpl.zzq(this.zza, this.zzb, r2);
    }
}
