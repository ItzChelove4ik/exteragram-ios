package com.android.billingclient.api;

import androidx.core.util.Consumer;
import com.google.android.gms.internal.play_billing.zzc;
import com.google.android.gms.internal.play_billing.zzie;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

/* loaded from: classes4.dex */
final class zzca implements com.google.android.gms.internal.play_billing.zzcs {
    final /* synthetic */ Consumer zza;
    final /* synthetic */ Runnable zzb;
    final /* synthetic */ zzce zzc;
    final /* synthetic */ int zzd;

    public zzca(zzce r1, int r2, Consumer r3, Runnable r4) {
        this.zzd = r2;
        this.zza = r3;
        this.zzb = r4;
        Objects.requireNonNull(r1);
        this.zzc = r1;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcs
    public final void zza(Throwable r6) {
        boolean r0 = r6 instanceof TimeoutException;
        zzce r1 = this.zzc;
        if (r0 == false) goto L5;
        zzce.zzaw(r1, zzie.zzaX, 28, zzcj.zzF);
        zzc.zzo("BillingClientTesting", "Asynchronous call to Billing Override Service timed out.", r6);
    L6:
        this.zzb.run();
        return;
    L5:
        zzce.zzaw(r1, zzie.zzaQ, 28, zzcj.zzF);
        zzc.zzo("BillingClientTesting", "An error occurred while retrieving billing override.", r6);
        goto L6
    }

    @Override // com.google.android.gms.internal.play_billing.zzcs
    public final /* bridge */ /* synthetic */ void zzb(Object r3) {
        Integer r32 = (Integer) r3;
        int r0 = r32.intValue();
        zzce r1 = this.zzc;
        if (zzce.zzas(r1, r0) == false) goto L6;
        BillingResult r33 = zzce.zzau(r1, this.zzd, r32.intValue());
        this.zza.accept(r33);
        return;
    L6:
        this.zzb.run();
    }
}
