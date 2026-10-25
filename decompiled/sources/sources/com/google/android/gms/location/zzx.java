package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.internal.location.zzb;

/* loaded from: classes5.dex */
public abstract class zzx extends zzb implements zzy {
    public static zzy zzb(IBinder r2) {
        IInterface r0 = r2.queryLocalInterface("com.google.android.gms.location.ILocationListener");
        if ((r0 instanceof zzy) == false) goto L7;
        return (zzy) r0;
    L7:
        return new zzw(r2);
    }
}
