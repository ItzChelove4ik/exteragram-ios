package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes5.dex */
public abstract class zzas extends com.google.android.gms.internal.maps.zzb implements zzat {
    public zzas() {
        super("com.google.android.gms.maps.internal.IOnMapReadyCallback");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean zza(int r3, Parcel r4, Parcel r5, int r6) {
        if (r3 != 1) goto L13;
        IBinder r32 = r4.readStrongBinder();
        if (r32 != null) goto L7;
        IGoogleMapDelegate r33 = null;
    L11:
        com.google.android.gms.internal.maps.zzc.zzd(r4);
        zzb(r33);
        r5.writeNoException();
        return true;
    L7:
        IInterface r0 = r32.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        if ((r0 instanceof IGoogleMapDelegate) == false) goto L10;
        r33 = (IGoogleMapDelegate) r0;
        goto L11
    L10:
        r33 = new zzg(r32);
        goto L11
    L13:
        return false;
    }
}
