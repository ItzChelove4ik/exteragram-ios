package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zzz extends com.google.android.gms.internal.common.zzb implements IGmsCallbacks {
    public zzz() {
        super("com.google.android.gms.common.internal.IGmsCallbacks");
    }

    @Override // com.google.android.gms.internal.common.zzb
    public final boolean zza(int r3, Parcel r4, Parcel r5, int r6) {
        if (r3 != 1) goto L5;
        int r32 = r4.readInt();
        IBinder r0 = r4.readStrongBinder();
        Bundle r1 = (Bundle) com.google.android.gms.internal.common.zzc.zzb(r4, Bundle.CREATOR);
        com.google.android.gms.internal.common.zzc.zzf(r4);
        onPostInitComplete(r32, r0, r1);
    L13:
        r5.writeNoException();
        return true;
    L5:
        if (r3 != 2) goto L7;
        int r33 = r4.readInt();
        Bundle r02 = (Bundle) com.google.android.gms.internal.common.zzc.zzb(r4, Bundle.CREATOR);
        com.google.android.gms.internal.common.zzc.zzf(r4);
        zzb(r33, r02);
        goto L13
    L7:
        if (r3 == 3) goto L10;
        return false;
    L10:
        int r34 = r4.readInt();
        IBinder r03 = r4.readStrongBinder();
        zzj r12 = (zzj) com.google.android.gms.internal.common.zzc.zzb(r4, zzj.CREATOR);
        com.google.android.gms.internal.common.zzc.zzf(r4);
        zzc(r34, r03, r12);
        goto L13
    }
}
