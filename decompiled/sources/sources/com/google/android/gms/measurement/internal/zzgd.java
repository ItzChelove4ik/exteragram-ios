package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public abstract class zzgd extends com.google.android.gms.internal.measurement.zzbm implements zzge {
    public zzgd() {
        super("com.google.android.gms.measurement.internal.ITriggerUrisCallback");
    }

    @Override // com.google.android.gms.internal.measurement.zzbm
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 2) goto L6;
        ArrayList r12 = r2.createTypedArrayList(zzoh.CREATOR);
        com.google.android.gms.internal.measurement.zzbn.zzf(r2);
        zze(r12);
        return true;
    L6:
        return false;
    }
}
