package com.google.android.gms.measurement.internal;

import android.os.Parcel;

/* loaded from: classes5.dex */
public abstract class zzgg extends com.google.android.gms.internal.measurement.zzbm implements zzgh {
    public zzgg() {
        super("com.google.android.gms.measurement.internal.IUploadBatchesCallback");
    }

    @Override // com.google.android.gms.internal.measurement.zzbm
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 2) goto L6;
        zzoq r12 = (zzoq) com.google.android.gms.internal.measurement.zzbn.zzb(r2, zzoq.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r2);
        zze(r12);
        return true;
    L6:
        return false;
    }
}
