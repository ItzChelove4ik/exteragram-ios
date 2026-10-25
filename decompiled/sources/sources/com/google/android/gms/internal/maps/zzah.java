package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes4.dex */
public final class zzah extends zza implements zzaj {
    public zzah(IBinder r2) {
        super(r2, "com.google.android.gms.maps.model.internal.IMarkerDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final boolean zzE(zzaj r2) {
        Parcel r0 = zza();
        zzc.zzg(r0, r2);
        Parcel r1 = zzJ(16, r0);
        boolean r22 = zzc.zzh(r1);
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final int zzg() {
        Parcel r2 = zzJ(17, zza());
        int r0 = r2.readInt();
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final IObjectWrapper zzi() {
        Parcel r2 = zzJ(30, zza());
        IObjectWrapper r0 = IObjectWrapper.Stub.asInterface(r2.readStrongBinder());
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final LatLng zzj() {
        Parcel r2 = zzJ(4, zza());
        LatLng r0 = (LatLng) zzc.zza(r2, LatLng.CREATOR);
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final void zzo() {
        zzc(1, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final void zzt(IObjectWrapper r2) {
        Parcel r0 = zza();
        zzc.zzg(r0, r2);
        zzc(18, r0);
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final void zzw(LatLng r2) {
        Parcel r0 = zza();
        zzc.zze(r0, r2);
        zzc(3, r0);
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final void zzx(float r2) {
        Parcel r0 = zza();
        r0.writeFloat(r2);
        zzc(22, r0);
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final void zzz(IObjectWrapper r2) {
        Parcel r0 = zza();
        zzc.zzg(r0, r2);
        zzc(29, r0);
    }
}
