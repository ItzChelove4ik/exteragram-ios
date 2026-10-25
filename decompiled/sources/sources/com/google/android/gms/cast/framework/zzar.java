package com.google.android.gms.cast.framework;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public abstract class zzar extends com.google.android.gms.internal.cast.zzb implements zzas {
    public static zzas zzb(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.android.gms.cast.framework.IReconnectionService");
        if ((r0 instanceof zzas) == false) goto L10;
        return (zzas) r0;
    L10:
        return new zzaq(r2);
    }
}
