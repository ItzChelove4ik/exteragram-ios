package com.google.android.gms.cast.framework;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public abstract class zzak extends com.google.android.gms.internal.cast.zzb implements zzal {
    public static zzal zzb(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.android.gms.cast.framework.ICastSession");
        if ((r0 instanceof zzal) == false) goto L10;
        return (zzal) r0;
    L10:
        return new zzaj(r2);
    }
}
