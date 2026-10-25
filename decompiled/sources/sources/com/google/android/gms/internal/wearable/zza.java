package com.google.android.gms.internal.wearable;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes5.dex */
public abstract class zza implements IInterface {
    private final IBinder zza;
    private final String zzb;

    public zza(IBinder r1, String r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.zza;
    }

    public final void zzJ(int r3, Parcel r4) {
        Parcel r0 = Parcel.obtain();
        this.zza.transact(r3, r4, r0, 0);     // Catch: Throwable -> L6
        r0.readException();     // Catch: Throwable -> L6
        r4.recycle();
        r0.recycle();
        return;
    L6:
        th = move-exception;
        r4.recycle();
        r0.recycle();
        throw th;
    }

    public final void zzK(int r2, Parcel r3) {
        this.zza.transact(1, r3, null, 1);     // Catch: Throwable -> L5
        r3.recycle();
        return;
    L5:
        th = move-exception;
        r3.recycle();
        throw th;
    }

    public final Parcel zza() {
        Parcel r0 = Parcel.obtain();
        r0.writeInterfaceToken(this.zzb);
        return r0;
    }
}
