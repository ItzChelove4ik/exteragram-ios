package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzai;
import com.google.android.gms.internal.maps.zzaj;
import com.google.android.gms.internal.maps.zzm;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

/* loaded from: classes5.dex */
public final class zzg extends com.google.android.gms.internal.maps.zza implements IGoogleMapDelegate {
    public zzg(IBinder r2) {
        super(r2, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final zzn addCircle(CircleOptions r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zze(r0, r2);
        Parcel r1 = zzJ(35, r0);
        zzn r22 = zzm.zzb(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final zzaj addMarker(MarkerOptions r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zze(r0, r2);
        Parcel r1 = zzJ(11, r0);
        zzaj r22 = zzai.zzb(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void animateCamera(IObjectWrapper r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zzg(r0, r2);
        zzc(5, r0);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void animateCameraWithCallback(IObjectWrapper r2, zzd r3) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zzg(r0, r2);
        com.google.android.gms.internal.maps.zzc.zzg(r0, r3);
        zzc(6, r0);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void animateCameraWithDurationAndCallback(IObjectWrapper r2, int r3, zzd r4) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zzg(r0, r2);
        r0.writeInt(r3);
        com.google.android.gms.internal.maps.zzc.zzg(r0, r4);
        zzc(7, r0);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final CameraPosition getCameraPosition() {
        Parcel r2 = zzJ(1, zza());
        CameraPosition r0 = (CameraPosition) com.google.android.gms.internal.maps.zzc.zza(r2, CameraPosition.CREATOR);
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final float getMaxZoomLevel() {
        Parcel r2 = zzJ(2, zza());
        float r0 = r2.readFloat();
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final float getMinZoomLevel() {
        Parcel r2 = zzJ(3, zza());
        float r0 = r2.readFloat();
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final IProjectionDelegate getProjection() {
        Parcel r3 = zzJ(26, zza());
        IBinder r0 = r3.readStrongBinder();
        if (r0 != null) goto L5;
        IProjectionDelegate r02 = null;
    L9:
        r3.recycle();
        return r02;
    L5:
        IInterface r1 = r0.queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
        if ((r1 instanceof IProjectionDelegate) == false) goto L8;
        r02 = (IProjectionDelegate) r1;
        goto L9
    L8:
        r02 = new zzbu(r0);
        goto L9
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final IUiSettingsDelegate getUiSettings() {
        Parcel r3 = zzJ(25, zza());
        IBinder r0 = r3.readStrongBinder();
        if (r0 != null) goto L5;
        IUiSettingsDelegate r02 = null;
    L9:
        r3.recycle();
        return r02;
    L5:
        IInterface r1 = r0.queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
        if ((r1 instanceof IUiSettingsDelegate) == false) goto L8;
        r02 = (IUiSettingsDelegate) r1;
        goto L9
    L8:
        r02 = new zzca(r0);
        goto L9
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void moveCamera(IObjectWrapper r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zzg(r0, r2);
        zzc(4, r0);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean setMapStyle(MapStyleOptions r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zze(r0, r2);
        Parcel r1 = zzJ(91, r0);
        boolean r22 = com.google.android.gms.internal.maps.zzc.zzh(r1);
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMapType(int r2) {
        Parcel r0 = zza();
        r0.writeInt(r2);
        zzc(16, r0);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMyLocationEnabled(boolean r3) {
        Parcel r0 = zza();
        int r1 = com.google.android.gms.internal.maps.zzc.$r8$clinit;
        r0.writeInt(r3 ? 1 : 0);
        zzc(22, r0);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraIdleListener(zzp r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zzg(r0, r2);
        zzc(99, r0);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraMoveListener(zzt r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zzg(r0, r2);
        zzc(97, r0);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraMoveStartedListener(zzv r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zzg(r0, r2);
        zzc(96, r0);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMapLoadedCallback(zzap r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zzg(r0, r2);
        zzc(42, r0);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMarkerClickListener(zzav r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zzg(r0, r2);
        zzc(30, r0);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMyLocationChangeListener(zzbb r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zzg(r0, r2);
        zzc(36, r0);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setPadding(int r2, int r3, int r4, int r5) {
        Parcel r0 = zza();
        r0.writeInt(r2);
        r0.writeInt(r3);
        r0.writeInt(r4);
        r0.writeInt(r5);
        zzc(39, r0);
    }
}
