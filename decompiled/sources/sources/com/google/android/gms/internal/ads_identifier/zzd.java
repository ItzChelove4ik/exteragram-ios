package com.google.android.gms.internal.ads_identifier;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes4.dex */
public final class zzd extends zza implements zzf {
    public zzd(IBinder r2) {
        super(r2, "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
    }

    @Override // com.google.android.gms.internal.ads_identifier.zzf
    public final String zzc() {
        Parcel r2 = zzb(1, zza());
        String r0 = r2.readString();
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.internal.ads_identifier.zzf
    public final boolean zze(boolean r2) {
        Parcel r22 = zza();
        zzc.zza(r22, true);
        Parcel r1 = zzb(2, r22);
        boolean r23 = zzc.zzb(r1);
        r1.recycle();
        return r23;
    }
}
