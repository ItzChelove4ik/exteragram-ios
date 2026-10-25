package com.google.android.gms.internal.icing;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public final class zzaa extends zza implements IInterface {
    public zzaa(IBinder r2) {
        super(r2, "com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
    }

    public final void zze(zzac r2, com.google.firebase.appindexing.internal.zzc[] r3) {
        Parcel r0 = zza();
        zzc.zzc(r0, r2);
        r0.writeTypedArray(r3, 0);
        zzc(7, r0);
    }
}
