package com.google.android.gms.cast.framework;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public abstract class zzah extends com.google.android.gms.internal.cast.zzb implements zzai {
    public static zzai zzb(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.android.gms.cast.framework.ICastContext");
        if ((r0 instanceof zzai) == false) goto L10;
        return (zzai) r0;
    L10:
        return new zzag(r2);
    }
}
