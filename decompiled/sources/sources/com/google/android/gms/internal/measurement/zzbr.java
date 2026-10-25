package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public abstract class zzbr extends zzbm implements zzbs {
    public static zzbs zzb(IBinder r2) {
        IInterface r0 = r2.queryLocalInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
        if ((r0 instanceof zzbs) == false) goto L7;
        return (zzbs) r0;
    L7:
        return new zzbq(r2);
    }
}
