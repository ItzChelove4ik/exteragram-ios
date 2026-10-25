package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.internal.maps.zzai;
import com.google.android.gms.internal.maps.zzaj;

/* loaded from: classes5.dex */
public abstract class zzau extends com.google.android.gms.internal.maps.zzb implements zzav {
    public zzau() {
        super("com.google.android.gms.maps.internal.IOnMarkerClickListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L6;
        zzaj r12 = zzai.zzb(r2.readStrongBinder());
        com.google.android.gms.internal.maps.zzc.zzd(r2);
        boolean r0 = zzb(r12);
        r3.writeNoException();
        r3.writeInt(r0 ? 1 : 0);
        return true;
    L6:
        return false;
    }
}
