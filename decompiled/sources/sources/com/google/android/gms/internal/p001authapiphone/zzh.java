package com.google.android.gms.internal.p001authapiphone;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public final class zzh extends zza implements IInterface {
    public zzh(IBinder r2) {
        super(r2, "com.google.android.gms.auth.api.phone.internal.ISmsRetrieverApiService");
    }

    public final void zzg(zzj r2) {
        Parcel r0 = zza();
        zzc.zzc(r0, r2);
        zzb(1, r0);
    }
}
