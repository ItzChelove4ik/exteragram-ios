package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public abstract class zzac extends com.google.android.gms.internal.common.zzb implements zzad {
    public static zzad zzb(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.android.gms.common.internal.IGoogleCertificatesApi");
        if ((r0 instanceof zzad) == false) goto L10;
        return (zzad) r0;
    L10:
        return new zzab(r2);
    }
}
