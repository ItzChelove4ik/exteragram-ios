package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes4.dex */
public final class zzcq extends zzbl implements zzcs {
    public zzcq(IBinder r2) {
        super(r2, "com.google.android.gms.measurement.api.internal.IBundleReceiver");
    }

    @Override // com.google.android.gms.internal.measurement.zzcs
    public final void zzb(Bundle r2) {
        Parcel r0 = zza();
        zzbn.zzc(r0, r2);
        zzc(1, r0);
    }
}
