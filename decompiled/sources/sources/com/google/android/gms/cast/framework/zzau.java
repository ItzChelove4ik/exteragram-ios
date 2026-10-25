package com.google.android.gms.cast.framework;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public abstract class zzau extends com.google.android.gms.internal.cast.zzb implements zzav {
    public static zzav zzb(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.android.gms.cast.framework.ISession");
        if ((r0 instanceof zzav) == false) goto L10;
        return (zzav) r0;
    L10:
        return new zzat(r2);
    }
}
