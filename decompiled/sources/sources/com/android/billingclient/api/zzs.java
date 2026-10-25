package com.android.billingclient.api;

import android.content.Context;
import android.content.IntentFilter;

/* loaded from: classes4.dex */
final class zzs {
    private final Context zza;
    private final PurchasesUpdatedListener zzb;
    private final zzch zze;
    private final zzr zzf;
    private final zzr zzg;
    private boolean zzh;

    public zzs(Context r1, PurchasesUpdatedListener r2, zzco r3, zzb r4, UserChoiceBillingListener r5, zzch r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zze = r6;
        this.zzf = new zzr(this, true);
        this.zzg = new zzr(this, false);
    }

    public static /* bridge */ /* synthetic */ zzb zza(zzs r0) {
        r0.getClass();
        return null;
    }

    public static /* bridge */ /* synthetic */ zzch zzb(zzs r0) {
        return r0.zze;
    }

    public static /* bridge */ /* synthetic */ PurchasesUpdatedListener zzc(zzs r0) {
        return r0.zzb;
    }

    public static /* bridge */ /* synthetic */ UserChoiceBillingListener zze(zzs r0) {
        r0.getClass();
        return null;
    }

    public final PurchasesUpdatedListener zzd() {
        return this.zzb;
    }

    public final void zzg(boolean r4) {
        IntentFilter r0 = new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED");
        IntentFilter r1 = new IntentFilter("com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED");
        r1.addAction("com.android.vending.billing.ALTERNATIVE_BILLING");
        this.zzh = r4;
        zzr r42 = this.zzg;
        Context r2 = this.zza;
        r42.zza(r2, r1);
        boolean r43 = this.zzh;
        zzr r3 = this.zzf;
        if (r43 == false) goto L6;
        r3.zzb(r2, r0, "com.google.android.finsky.permission.PLAY_BILLING_LIBRARY_BROADCAST");
        return;
    L6:
        r3.zza(r2, r0);
    }
}
