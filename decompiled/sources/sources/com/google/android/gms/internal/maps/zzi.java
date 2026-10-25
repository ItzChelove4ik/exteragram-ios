package com.google.android.gms.internal.maps;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public final class zzi extends zza implements zzk {
    public zzi(IBinder r2) {
        super(r2, "com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final IObjectWrapper zzg(Bitmap r2) {
        Parcel r0 = zza();
        zzc.zze(r0, r2);
        Parcel r1 = zzJ(6, r0);
        IObjectWrapper r22 = IObjectWrapper.Stub.asInterface(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final IObjectWrapper zzk(int r2) {
        Parcel r0 = zza();
        r0.writeInt(r2);
        Parcel r1 = zzJ(1, r0);
        IObjectWrapper r22 = IObjectWrapper.Stub.asInterface(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }
}
