package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public final class zzab extends com.google.android.gms.internal.common.zza implements zzad {
    public zzab(IBinder r2) {
        super(r2, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }

    @Override // com.google.android.gms.common.internal.zzad
    public final boolean zze(com.google.android.gms.common.zzt r2, IObjectWrapper r3) {
        Parcel r0 = zza();
        com.google.android.gms.internal.common.zzc.zzc(r0, r2);
        com.google.android.gms.internal.common.zzc.zze(r0, r3);
        Parcel r1 = zzB(5, r0);
        boolean r22 = com.google.android.gms.internal.common.zzc.zza(r1);
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.common.internal.zzad
    public final com.google.android.gms.common.zzr zzf(com.google.android.gms.common.zzp r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.common.zzc.zzc(r0, r2);
        Parcel r1 = zzB(6, r0);
        com.google.android.gms.common.zzr r22 = (com.google.android.gms.common.zzr) com.google.android.gms.internal.common.zzc.zzb(r1, com.google.android.gms.common.zzr.CREATOR);
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.common.internal.zzad
    public final boolean zzg() {
        Parcel r2 = zzB(7, zza());
        boolean r0 = com.google.android.gms.internal.common.zzc.zza(r2);
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.common.internal.zzad
    public final com.google.android.gms.common.zzr zzh(com.google.android.gms.common.zzp r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.common.zzc.zzc(r0, r2);
        Parcel r1 = zzB(8, r0);
        com.google.android.gms.common.zzr r22 = (com.google.android.gms.common.zzr) com.google.android.gms.internal.common.zzc.zzb(r1, com.google.android.gms.common.zzr.CREATOR);
        r1.recycle();
        return r22;
    }
}
