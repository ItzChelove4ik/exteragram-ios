package com.google.android.gms.maps.internal;

import android.os.Parcel;

/* loaded from: classes5.dex */
public abstract class zzu extends com.google.android.gms.internal.maps.zzb implements zzv {
    public zzu() {
        super("com.google.android.gms.maps.internal.IOnCameraMoveStartedListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L6;
        int r12 = r2.readInt();
        com.google.android.gms.internal.maps.zzc.zzd(r2);
        zzb(r12);
        r3.writeNoException();
        return true;
    L6:
        return false;
    }
}
