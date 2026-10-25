package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes4.dex */
public final class zzl extends zza implements zzn {
    public zzl(IBinder r2) {
        super(r2, "com.google.android.gms.maps.model.internal.ICircleDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final double zzd() {
        Parcel r2 = zzJ(6, zza());
        double r0 = r2.readDouble();
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final int zzi() {
        Parcel r2 = zzJ(18, zza());
        int r0 = r2.readInt();
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final void zzn() {
        zzc(1, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final void zzo(LatLng r2) {
        Parcel r0 = zza();
        zzc.zze(r0, r2);
        zzc(3, r0);
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final void zzq(int r2) {
        Parcel r0 = zza();
        r0.writeInt(r2);
        zzc(11, r0);
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final void zzr(double r2) {
        Parcel r0 = zza();
        r0.writeDouble(r2);
        zzc(5, r0);
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final void zzs(int r2) {
        Parcel r0 = zza();
        r0.writeInt(r2);
        zzc(9, r0);
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final boolean zzy(zzn r2) {
        Parcel r0 = zza();
        zzc.zzg(r0, r2);
        Parcel r1 = zzJ(17, r0);
        boolean r22 = zzc.zzh(r1);
        r1.recycle();
        return r22;
    }
}
