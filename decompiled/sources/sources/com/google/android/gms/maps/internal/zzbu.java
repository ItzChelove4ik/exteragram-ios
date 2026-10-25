package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes5.dex */
public final class zzbu extends com.google.android.gms.internal.maps.zza implements IProjectionDelegate {
    public zzbu(IBinder r2) {
        super(r2, "com.google.android.gms.maps.internal.IProjectionDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IProjectionDelegate
    public final IObjectWrapper toScreenLocation(LatLng r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zze(r0, r2);
        Parcel r1 = zzJ(2, r0);
        IObjectWrapper r22 = IObjectWrapper.Stub.asInterface(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }
}
