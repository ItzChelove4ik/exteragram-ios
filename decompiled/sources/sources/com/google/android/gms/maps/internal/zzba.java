package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes5.dex */
public abstract class zzba extends com.google.android.gms.internal.maps.zzb implements zzbb {
    public zzba() {
        super("com.google.android.gms.maps.internal.IOnMyLocationChangeListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L6;
        IObjectWrapper r12 = IObjectWrapper.Stub.asInterface(r2.readStrongBinder());
        com.google.android.gms.internal.maps.zzc.zzd(r2);
        zzb(r12);
        r3.writeNoException();
        return true;
    L6:
        return false;
    }
}
