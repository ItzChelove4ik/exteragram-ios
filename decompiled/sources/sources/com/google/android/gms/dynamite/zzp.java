package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public final class zzp extends com.google.android.gms.internal.common.zza implements IInterface {
    public zzp(IBinder r2) {
        super(r2, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    public final IObjectWrapper zze(IObjectWrapper r2, String r3, int r4) {
        Parcel r0 = zza();
        com.google.android.gms.internal.common.zzc.zze(r0, r2);
        r0.writeString(r3);
        r0.writeInt(r4);
        Parcel r1 = zzB(2, r0);
        IObjectWrapper r22 = IObjectWrapper.Stub.asInterface(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }

    public final int zzf(IObjectWrapper r2, String r3, boolean r4) {
        Parcel r0 = zza();
        com.google.android.gms.internal.common.zzc.zze(r0, r2);
        r0.writeString(r3);
        r0.writeInt(r4 ? 1 : 0);
        Parcel r1 = zzB(3, r0);
        int r22 = r1.readInt();
        r1.recycle();
        return r22;
    }

    public final IObjectWrapper zzg(IObjectWrapper r2, String r3, int r4) {
        Parcel r0 = zza();
        com.google.android.gms.internal.common.zzc.zze(r0, r2);
        r0.writeString(r3);
        r0.writeInt(r4);
        Parcel r1 = zzB(4, r0);
        IObjectWrapper r22 = IObjectWrapper.Stub.asInterface(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }

    public final int zzh(IObjectWrapper r2, String r3, boolean r4) {
        Parcel r0 = zza();
        com.google.android.gms.internal.common.zzc.zze(r0, r2);
        r0.writeString(r3);
        r0.writeInt(r4 ? 1 : 0);
        Parcel r1 = zzB(5, r0);
        int r22 = r1.readInt();
        r1.recycle();
        return r22;
    }

    public final int zzi() {
        Parcel r2 = zzB(6, zza());
        int r0 = r2.readInt();
        r2.recycle();
        return r0;
    }

    public final IObjectWrapper zzj(IObjectWrapper r2, String r3, boolean r4, long r5) {
        Parcel r0 = zza();
        com.google.android.gms.internal.common.zzc.zze(r0, r2);
        r0.writeString(r3);
        r0.writeInt(r4 ? 1 : 0);
        r0.writeLong(r5);
        Parcel r1 = zzB(7, r0);
        IObjectWrapper r22 = IObjectWrapper.Stub.asInterface(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }

    public final IObjectWrapper zzk(IObjectWrapper r2, String r3, int r4, IObjectWrapper r5) {
        Parcel r0 = zza();
        com.google.android.gms.internal.common.zzc.zze(r0, r2);
        r0.writeString(r3);
        r0.writeInt(r4);
        com.google.android.gms.internal.common.zzc.zze(r0, r5);
        Parcel r1 = zzB(8, r0);
        IObjectWrapper r22 = IObjectWrapper.Stub.asInterface(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }
}
