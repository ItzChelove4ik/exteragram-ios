package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zzcr extends zzbm implements zzcs {
    public zzcr() {
        super("com.google.android.gms.measurement.api.internal.IBundleReceiver");
    }

    @Override // com.google.android.gms.internal.measurement.zzbm
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L6;
        Bundle r12 = (Bundle) zzbn.zzb(r2, Bundle.CREATOR);
        zzbn.zzf(r2);
        zzb(r12);
        r3.writeNoException();
        return true;
    L6:
        return false;
    }
}
