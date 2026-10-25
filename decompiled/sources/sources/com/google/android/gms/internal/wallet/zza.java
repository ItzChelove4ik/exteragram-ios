package com.google.android.gms.internal.wallet;

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

    public final Parcel zza() {
        Parcel r0 = Parcel.obtain();
        r0.writeInterfaceToken(this.zzb);
        return r0;
    }

    public final void zzc(int r3, Parcel r4) {
        this.zza.transact(r3, r4, null, 1);     // Catch: Throwable -> L5
        r4.recycle();
        return;
    L5:
        th = move-exception;
        r4.recycle();
        throw th;
    }
}
