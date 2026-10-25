package com.google.android.gms.cast.framework.media.internal;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public abstract class zzf extends com.google.android.gms.internal.cast.zzb implements zzg {
    public static zzg zzb(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.android.gms.cast.framework.media.internal.IFetchBitmapTask");
        if ((r0 instanceof zzg) == false) goto L10;
        return (zzg) r0;
    L10:
        return new zze(r2);
    }
}
