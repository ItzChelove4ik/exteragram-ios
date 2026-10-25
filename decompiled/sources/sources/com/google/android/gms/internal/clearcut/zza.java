package com.google.android.gms.internal.clearcut;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zza implements IInterface {
    private final IBinder zza;
    private final String zzb;

    public zza(IBinder r1, String r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.zza;
    }

    public final Parcel obtainAndWriteInterfaceToken() {
        Parcel r0 = Parcel.obtain();
        r0.writeInterfaceToken(this.zzb);
        return r0;
    }

    public final void transactOneway(int r2, Parcel r3) {
        this.zza.transact(1, r3, null, 1);     // Catch: Throwable -> L5
        r3.recycle();
        return;
    L5:
        th = move-exception;
        r3.recycle();
        throw th;
    }
}
