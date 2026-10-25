package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;

/* loaded from: classes.dex */
public abstract class zzcx extends zzbm implements zzcy {
    public zzcx() {
        super("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
    }

    @Override // com.google.android.gms.internal.measurement.zzbm
    public final boolean zza(int r7, Parcel r8, Parcel r9, int r10) {
        if (r7 != 1) goto L5;
        String r1 = r8.readString();
        String r2 = r8.readString();
        Bundle r3 = (Bundle) zzbn.zzb(r8, Bundle.CREATOR);
        long r4 = r8.readLong();
        zzbn.zzf(r8);
        zze(r1, r2, r3, r4);
        r9.writeNoException();
    L10:
        return true;
    L5:
        if (r7 == 2) goto L8;
        return false;
    L8:
        int r6 = zzf();
        r9.writeNoException();
        r9.writeInt(r6);
        goto L10
    }
}
