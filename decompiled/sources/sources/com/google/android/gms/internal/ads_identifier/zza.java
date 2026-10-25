package com.google.android.gms.internal.ads_identifier;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zza implements IInterface {
    private final IBinder zza;
    private final String zzb;

    public zza(IBinder r1, String r2) {
        this.zza = r1;
        this.zzb = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";
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

    public final Parcel zzb(int r3, Parcel r4) {
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
}
