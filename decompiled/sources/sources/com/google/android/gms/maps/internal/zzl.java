package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes5.dex */
public final class zzl extends com.google.android.gms.internal.maps.zza implements IMapViewDelegate {
    public zzl(IBinder r2) {
        super(r2, "com.google.android.gms.maps.internal.IMapViewDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void getMapAsync(zzat r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zzg(r0, r2);
        zzc(9, r0);
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final IObjectWrapper getView() {
        Parcel r2 = zzJ(8, zza());
        IObjectWrapper r0 = IObjectWrapper.Stub.asInterface(r2.readStrongBinder());
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onCreate(Bundle r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zze(r0, r2);
        zzc(2, r0);
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onDestroy() {
        zzc(5, zza());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onEnterAmbient(Bundle r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zze(r0, r2);
        zzc(10, r0);
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onExitAmbient() {
        zzc(11, zza());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onLowMemory() {
        zzc(6, zza());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onPause() {
        zzc(4, zza());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onResume() {
        zzc(3, zza());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onSaveInstanceState(Bundle r3) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zze(r0, r3);
        Parcel r2 = zzJ(7, r0);
        if (r2.readInt() == 0) goto L5;
        r3.readFromParcel(r2);
    L5:
        r2.recycle();
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onStart() {
        zzc(12, zza());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onStop() {
        zzc(13, zza());
    }
}
