package com.google.android.gms.internal.p000authapi;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zba implements IInterface {
    private final IBinder zba;
    private final String zbb;

    public zba(IBinder r1, String r2) {
        this.zba = r1;
        this.zbb = r2;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.zba;
    }

    public final Parcel zba() {
        Parcel r0 = Parcel.obtain();
        r0.writeInterfaceToken(this.zbb);
        return r0;
    }

    public final void zbb(int r3, Parcel r4) {
        Parcel r0 = Parcel.obtain();
        this.zba.transact(r3, r4, r0, 0);     // Catch: Throwable -> L6
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
}
