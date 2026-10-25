package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes.dex */
public abstract class zzw extends com.google.android.gms.internal.common.zzb implements zzx {
    public zzw() {
        super("com.google.android.gms.common.internal.ICertData");
    }

    public static zzx zzg(IBinder r2) {
        IInterface r0 = r2.queryLocalInterface("com.google.android.gms.common.internal.ICertData");
        if ((r0 instanceof zzx) == false) goto L7;
        return (zzx) r0;
    L7:
        return new zzv(r2);
    }

    @Override // com.google.android.gms.internal.common.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L5;
        IObjectWrapper r0 = zzd();
        r3.writeNoException();
        com.google.android.gms.internal.common.zzc.zze(r3, r0);
    L10:
        return true;
    L5:
        if (r1 == 2) goto L8;
        return false;
    L8:
        int r02 = zze();
        r3.writeNoException();
        r3.writeInt(r02);
        goto L10
    }
}
