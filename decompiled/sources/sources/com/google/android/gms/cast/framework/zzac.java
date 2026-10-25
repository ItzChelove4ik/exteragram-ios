package com.google.android.gms.cast.framework;

import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public abstract class zzac extends com.google.android.gms.internal.cast.zzb implements zzad {
    public zzac() {
        super("com.google.android.gms.cast.framework.IAppVisibilityListener");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L5;
        IObjectWrapper r0 = zzb();
        r3.writeNoException();
        com.google.android.gms.internal.cast.zzc.zze(r3, r0);
    L16:
        return true;
    L5:
        if (r1 != 2) goto L7;
        zzc();
        r3.writeNoException();
        goto L16
    L7:
        if (r1 != 3) goto L9;
        zzd();
        r3.writeNoException();
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
