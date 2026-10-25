package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public abstract class zzj extends zzb implements zzk {
    public static zzk zzb(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
        if ((r0 instanceof zzk) == false) goto L10;
        return (zzk) r0;
    L10:
        return new zzi(r2);
    }
}
