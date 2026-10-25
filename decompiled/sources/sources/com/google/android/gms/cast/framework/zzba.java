package com.google.android.gms.cast.framework;

import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public abstract class zzba extends com.google.android.gms.internal.cast.zzb implements zzbb {
    public zzba() {
        super("com.google.android.gms.cast.framework.ISessionProvider");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L5;
        String r12 = r2.readString();
        com.google.android.gms.internal.cast.zzc.zzf(r2);
        IObjectWrapper r0 = zzb(r12);
        r3.writeNoException();
        com.google.android.gms.internal.cast.zzc.zze(r3, r0);
    L16:
        return true;
    L5:
        if (r1 != 2) goto L7;
        boolean r02 = zzc();
        r3.writeNoException();
        int r13 = com.google.android.gms.internal.cast.zzc.$r8$clinit;
        r3.writeInt(r02 ? 1 : 0);
        goto L16
    L7:
        if (r1 != 3) goto L9;
        String r03 = zzd();
        r3.writeNoException();
        r3.writeString(r03);
        goto L16
    L9:
        if (r1 == 4) goto L12;
        return false;
    L12:
        r3.writeNoException();
        r3.writeInt(12451000);
        goto L16
    }
}
