package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes.dex */
public final class zzq extends com.google.android.gms.internal.common.zza implements IInterface {
    public zzq(IBinder r2) {
        super(r2, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
    }

    public final IObjectWrapper zze(IObjectWrapper r2, String r3, int r4, IObjectWrapper r5) {
        Parcel r0 = zza();
        com.google.android.gms.internal.common.zzc.zze(r0, r2);
        r0.writeString(r3);
        r0.writeInt(r4);
        com.google.android.gms.internal.common.zzc.zze(r0, r5);
        Parcel r1 = zzB(2, r0);
        IObjectWrapper r22 = IObjectWrapper.Stub.asInterface(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }

    public final IObjectWrapper zzf(IObjectWrapper r2, String r3, int r4, IObjectWrapper r5) {
        Parcel r0 = zza();
        com.google.android.gms.internal.common.zzc.zze(r0, r2);
        r0.writeString(r3);
        r0.writeInt(r4);
        com.google.android.gms.internal.common.zzc.zze(r0, r5);
        Parcel r1 = zzB(3, r0);
        IObjectWrapper r22 = IObjectWrapper.Stub.asInterface(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }
}
