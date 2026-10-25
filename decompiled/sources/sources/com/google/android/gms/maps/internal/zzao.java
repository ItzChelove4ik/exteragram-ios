package com.google.android.gms.maps.internal;

import android.os.Parcel;

/* loaded from: classes5.dex */
public abstract class zzao extends com.google.android.gms.internal.maps.zzb implements zzap {
    public zzao() {
        super("com.google.android.gms.maps.internal.IOnMapLoadedCallback");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L6;
        zzb();
        r3.writeNoException();
        return true;
    L6:
        return false;
    }
}
