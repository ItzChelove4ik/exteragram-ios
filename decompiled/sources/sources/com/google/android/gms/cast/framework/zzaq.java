package com.google.android.gms.cast.framework;

import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes4.dex */
public final class zzaq extends com.google.android.gms.internal.cast.zza implements zzas {
    public zzaq(IBinder r2) {
        super(r2, "com.google.android.gms.cast.framework.IReconnectionService");
    }

    @Override // com.google.android.gms.cast.framework.zzas
    public final void zze() {
        zzc(1, zza());
    }

    @Override // com.google.android.gms.cast.framework.zzas
    public final int zzf(Intent r2, int r3, int r4) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zzc(r0, r2);
        r0.writeInt(r3);
        r0.writeInt(r4);
        Parcel r1 = zzb(2, r0);
        int r22 = r1.readInt();
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.cast.framework.zzas
    public final IBinder zzg(Intent r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zzc(r0, r2);
        Parcel r1 = zzb(3, r0);
        IBinder r22 = r1.readStrongBinder();
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.cast.framework.zzas
    public final void zzh() {
        zzc(4, zza());
    }
}
