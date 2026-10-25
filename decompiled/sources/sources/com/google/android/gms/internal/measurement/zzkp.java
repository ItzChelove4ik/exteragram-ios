package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;

/* loaded from: classes4.dex */
public abstract class zzkp extends zzbm implements zzkq {
    public zzkp() {
        super("com.google.android.gms.phenotype.internal.IGetStorageInfoCallbacks");
    }

    @Override // com.google.android.gms.internal.measurement.zzbm
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 2) goto L6;
        Status r12 = (Status) zzbn.zzb(r2, Status.CREATOR);
        byte[] r32 = r2.createByteArray();
        zzbn.zzf(r2);
        zzb(r12, r32);
        return true;
    L6:
        return false;
    }
}
