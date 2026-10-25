package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public final class zzaw extends com.google.android.gms.internal.cast.zza implements zzax {
    public zzaw(IBinder r2) {
        super(r2, "com.google.android.gms.cast.framework.ISessionManager");
    }

    @Override // com.google.android.gms.cast.framework.zzax
    public final IObjectWrapper zze() {
        Parcel r2 = zzb(1, zza());
        IObjectWrapper r0 = IObjectWrapper.Stub.asInterface(r2.readStrongBinder());
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.cast.framework.zzax
    public final void zzf(zzaz r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zze(r0, r2);
        zzc(2, r0);
    }

    @Override // com.google.android.gms.cast.framework.zzax
    public final void zzg(zzaz r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zze(r0, r2);
        zzc(3, r0);
    }

    @Override // com.google.android.gms.cast.framework.zzax
    public final void zzh(zzan r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zze(r0, r2);
        zzc(4, r0);
    }

    @Override // com.google.android.gms.cast.framework.zzax
    public final void zzi(zzan r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zze(r0, r2);
        zzc(5, r0);
    }

    @Override // com.google.android.gms.cast.framework.zzax
    public final void zzj(boolean r2, boolean r3) {
        Parcel r22 = zza();
        int r0 = com.google.android.gms.internal.cast.zzc.$r8$clinit;
        r22.writeInt(1);
        r22.writeInt(r3 ? 1 : 0);
        zzc(6, r22);
    }

    @Override // com.google.android.gms.cast.framework.zzax
    public final IObjectWrapper zzk() {
        Parcel r2 = zzb(7, zza());
        IObjectWrapper r0 = IObjectWrapper.Stub.asInterface(r2.readStrongBinder());
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.cast.framework.zzax
    public final int zzl() {
        Parcel r2 = zzb(8, zza());
        int r0 = r2.readInt();
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.cast.framework.zzax
    public final void zzm(Bundle r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zzc(r0, r2);
        zzc(9, r0);
    }
}
