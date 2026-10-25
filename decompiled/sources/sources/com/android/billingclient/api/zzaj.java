package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzc;
import java.util.concurrent.Future;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzaj implements Runnable {
    public final /* synthetic */ Future zza;
    public final /* synthetic */ Runnable zzb;

    public /* synthetic */ zzaj(Future r1, Runnable r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Future r0 = this.zza;
        if (r0.isDone() == false) goto L5;
        return;
    L5:
        if (r0.isCancelled() == true) goto L11;
        Runnable r2 = this.zzb;
        r0.cancel(true);
        zzc.zzn("BillingClient", "Async task is taking too long, cancel it!");
        if (r2 == null) goto L12;
        r2.run();
        return;
    L12:
        return;
    }
}
