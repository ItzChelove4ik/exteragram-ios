package com.google.android.gms.measurement.internal;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes5.dex */
public final class zzgf extends com.google.android.gms.internal.measurement.zzbl implements zzgh {
    public zzgf(IBinder r2) {
        super(r2, "com.google.android.gms.measurement.internal.IUploadBatchesCallback");
    }

    @Override // com.google.android.gms.measurement.internal.zzgh
    public final void zze(zzoq r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(r0, r2);
        zzd(2, r0);
    }
}
