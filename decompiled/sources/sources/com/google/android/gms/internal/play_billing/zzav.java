package com.google.android.gms.internal.play_billing;

import android.os.Parcel;

/* loaded from: classes5.dex */
public abstract class zzav extends zzaq implements zzaw {
    public zzav() {
        super("com.google.android.apps.play.billingtestcompanion.aidl.IBillingOverrideServiceCallback");
    }

    @Override // com.google.android.gms.internal.play_billing.zzaq
    public final boolean zzb(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L6;
        int r12 = r2.readInt();
        zzar.zzb(r2);
        zza(r12);
        return true;
    L6:
        return false;
    }
}
