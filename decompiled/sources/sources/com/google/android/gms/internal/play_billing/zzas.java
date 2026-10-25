package com.google.android.gms.internal.play_billing;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes5.dex */
public final class zzas extends zzap implements zzau {
    public zzas(IBinder r2) {
        super(r2, "com.google.android.apps.play.billingtestcompanion.aidl.IBillingOverrideService");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.play_billing.zzau
    public final void zza(String r2, String r3, zzaw r4) {
        Parcel r0 = zzs();
        r0.writeString(r2);
        r0.writeString(r3);
        int r22 = zzar.$r8$clinit;
        r0.writeStrongBinder(r4);
        zzv(1, r0);
    }
}
