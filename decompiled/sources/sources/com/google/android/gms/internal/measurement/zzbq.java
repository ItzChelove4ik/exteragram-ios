package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes4.dex */
public final class zzbq extends zzbl implements zzbs {
    public zzbq(IBinder r2) {
        super(r2, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
    }

    @Override // com.google.android.gms.internal.measurement.zzbs
    public final Bundle zze(Bundle r2) {
        Parcel r0 = zza();
        zzbn.zzc(r0, r2);
        Parcel r1 = zzP(1, r0);
        Bundle r22 = (Bundle) zzbn.zzb(r1, Bundle.CREATOR);
        r1.recycle();
        return r22;
    }
}
