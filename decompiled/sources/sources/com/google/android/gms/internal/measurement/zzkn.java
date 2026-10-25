package com.google.android.gms.internal.measurement;

import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zzkn extends zzbm implements zzko {
    public zzkn() {
        super("com.google.android.gms.phenotype.internal.IFlagUpdateListener");
    }

    @Override // com.google.android.gms.internal.measurement.zzbm
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 2) goto L6;
        byte[] r12 = r2.createByteArray();
        zzbn.zzf(r2);
        zzb(r12);
        return true;
    L6:
        return false;
    }
}
