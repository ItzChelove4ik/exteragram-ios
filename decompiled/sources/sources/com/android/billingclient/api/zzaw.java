package com.android.billingclient.api;

import android.text.TextUtils;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.internal.play_billing.zzc;
import com.google.android.gms.internal.play_billing.zzie;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
final class zzaw implements Callable {
    final /* synthetic */ PurchasesResponseListener zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ BillingClientImpl zzc;

    public zzaw(BillingClientImpl r1, PurchasesResponseListener r2, String r3, boolean r4) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        BillingClientImpl r0 = this.zzc;
        if (BillingClientImpl.zzah(r0, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) == true) goto L5;
        zzie r1 = zzie.zzb;
        BillingResult r3 = zzcj.zzj;
        BillingClientImpl.zzak(r0, r1, 9, r3);
        this.zza.onQueryPurchasesResponse(r3, com.google.android.gms.internal.play_billing.zzbt.zzk());
        return null;
    L5:
        String r12 = this.zzb;
        if (TextUtils.isEmpty(r12) == false) goto L8;
        zzc.zzn("BillingClient", "Please provide a valid product type.");
        zzie r13 = zzie.zzX;
        BillingResult r32 = zzcj.zze;
        BillingClientImpl.zzak(r0, r13, 9, r32);
        this.zza.onQueryPurchasesResponse(r32, com.google.android.gms.internal.play_billing.zzbt.zzk());
        return null;
    L8:
        zzcw r02 = BillingClientImpl.zzaj(r0, r12, false, 9);
        List r14 = r02.zzb();
        PurchasesResponseListener r4 = this.zza;
        if (r14 == null) goto L11;
        r4.onQueryPurchasesResponse(r02.zza(), r02.zzb());
        return null;
    L11:
        r4.onQueryPurchasesResponse(r02.zza(), com.google.android.gms.internal.play_billing.zzbt.zzk());
        return null;
    }
}
