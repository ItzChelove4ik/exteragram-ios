package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public abstract class zzm extends zzb implements zzn {
    public static zzn zzb(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
        if ((r0 instanceof zzn) == false) goto L10;
        return (zzn) r0;
    L10:
        return new zzl(r2);
    }
}
