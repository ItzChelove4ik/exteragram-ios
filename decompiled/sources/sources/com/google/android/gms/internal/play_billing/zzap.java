package com.google.android.gms.internal.play_billing;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes5.dex */
public abstract class zzap implements IInterface {
    private final IBinder zza;
    private final String zzb;

    public zzap(IBinder r1, String r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.zza;
    }

    public final Parcel zzs() {
        Parcel r0 = Parcel.obtain();
        r0.writeInterfaceToken(this.zzb);
        return r0;
    }

    public final Parcel zzt(int r3, Parcel r4) {
        Parcel r0 = Parcel.obtain();
        this.zza.transact(r3, r4, r0, 0);     // Catch: Throwable -> L6 RuntimeException -> L8
        r0.readException();     // Catch: Throwable -> L6 RuntimeException -> L8
        r4.recycle();
        return r0;
    L6:
        th = move-exception;
        r4.recycle();
        throw th;
    L8:
        e = move-exception;
        r0.recycle();     // Catch: Throwable -> L6
        throw e;     // Catch: Throwable -> L6
    }

    public final void zzv(int r3, Parcel r4) {
        this.zza.transact(r3, r4, null, 1);     // Catch: Throwable -> L5
        r4.recycle();
        return;
    L5:
        th = move-exception;
        r4.recycle();
        throw th;
    }
}
