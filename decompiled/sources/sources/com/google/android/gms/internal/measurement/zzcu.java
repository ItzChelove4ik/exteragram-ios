package com.google.android.gms.internal.measurement;

import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zzcu extends zzbm implements zzcv {
    public zzcu() {
        super("com.google.android.gms.measurement.api.internal.IDynamiteUploadBatchesCallback");
    }

    @Override // com.google.android.gms.internal.measurement.zzbm
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 2) goto L6;
        zze();
        return true;
    L6:
        return false;
    }
}
