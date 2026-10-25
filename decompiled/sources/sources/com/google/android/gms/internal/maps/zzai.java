package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public abstract class zzai extends zzb implements zzaj {
    public static zzaj zzb(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
        if ((r0 instanceof zzaj) == false) goto L10;
        return (zzaj) r0;
    L10:
        return new zzah(r2);
    }
}
