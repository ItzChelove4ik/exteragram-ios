package com.google.android.gms.maps.internal;

import android.os.Parcel;

/* loaded from: classes5.dex */
public abstract class zzc extends com.google.android.gms.internal.maps.zzb implements zzd {
    public zzc() {
        super("com.google.android.gms.maps.internal.ICancelableCallback");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L5;
        zzc();
    L10:
        r3.writeNoException();
        return true;
    L5:
        if (r1 == 2) goto L8;
        return false;
    L8:
        zzb();
        goto L10
    }
}
