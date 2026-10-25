package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes5.dex */
public final class zzb extends com.google.android.gms.internal.maps.zza implements ICameraUpdateFactoryDelegate {
    public zzb(IBinder r2) {
        super(r2, "com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
    }

    @Override // com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate
    public final IObjectWrapper newLatLng(LatLng r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zze(r0, r2);
        Parcel r1 = zzJ(8, r0);
        IObjectWrapper r22 = IObjectWrapper.Stub.asInterface(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate
    public final IObjectWrapper newLatLngBounds(LatLngBounds r2, int r3) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zze(r0, r2);
        r0.writeInt(r3);
        Parcel r1 = zzJ(10, r0);
        IObjectWrapper r22 = IObjectWrapper.Stub.asInterface(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate
    public final IObjectWrapper newLatLngZoom(LatLng r2, float r3) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zze(r0, r2);
        r0.writeFloat(r3);
        Parcel r1 = zzJ(9, r0);
        IObjectWrapper r22 = IObjectWrapper.Stub.asInterface(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }
}
