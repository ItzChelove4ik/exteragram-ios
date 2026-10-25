package com.google.android.gms.cast.framework;

import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public abstract class zzam extends com.google.android.gms.internal.cast.zzb implements zzan {
    public zzam() {
        super("com.google.android.gms.cast.framework.ICastStateListener");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    public final boolean zza(int r2, Parcel r3, Parcel r4, int r5) {
        if (r2 != 1) goto L5;
        IObjectWrapper r1 = zzb();
        r4.writeNoException();
        com.google.android.gms.internal.cast.zzc.zze(r4, r1);
    L13:
        return true;
    L5:
        if (r2 != 2) goto L7;
        int r22 = r3.readInt();
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzc(r22);
        r4.writeNoException();
        goto L13
    L7:
        if (r2 == 3) goto L10;
        return false;
    L10:
        r4.writeNoException();
        r4.writeInt(12451000);
        goto L13
    }
}
