package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzc;
import com.google.android.gms.internal.play_billing.zzp;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzbc implements BillingClientStateListener {
    final /* synthetic */ zzp zza;
    final /* synthetic */ BillingClientImpl zzb;

    public zzbc(BillingClientImpl r1, zzp r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // com.android.billingclient.api.BillingClientStateListener
    public final void onBillingServiceDisconnected() {
        zzc.zzm("BillingClient", "Reconnection attempt failed.");
        this.zza.zzb(zzcj.zzj);     // Catch: Throwable -> L5
    L7:
        BillingClientImpl r0 = this.zzb;
        if (BillingClientImpl.zzh(r0) == null) goto L13;
        r0.zzag(new zzba(this));
        return;
    L13:
        return;
    L5:
        th = move-exception;
        zzc.zzo("BillingClient", "Exception setting completer.", th);
        goto L7
    }

    @Override // com.android.billingclient.api.BillingClientStateListener
    public final void onBillingSetupFinished(BillingResult r4) {
        zzc.zzm("BillingClient", "Reconnection finished with result: " + r4.getResponseCode());
        this.zza.zzb(r4);     // Catch: Throwable -> L5
    L7:
        BillingClientImpl r0 = this.zzb;
        if (BillingClientImpl.zzh(r0) == null) goto L13;
        r0.zzag(new zzbb(this, r4));
        return;
    L13:
        return;
    L5:
        th = move-exception;
        zzc.zzo("BillingClient", "Exception setting completer.", th);
        goto L7
    }
}
