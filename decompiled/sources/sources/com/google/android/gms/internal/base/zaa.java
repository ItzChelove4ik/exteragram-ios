package com.google.android.gms.internal.base;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zaa implements IInterface {
    private final IBinder zaa;
    private final String zab;

    public zaa(IBinder r1, String r2) {
        this.zaa = r1;
        this.zab = r2;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.zaa;
    }

    public final Parcel zaa() {
        Parcel r0 = Parcel.obtain();
        r0.writeInterfaceToken(this.zab);
        return r0;
    }

    public final void zac(int r3, Parcel r4) {
        Parcel r0 = Parcel.obtain();
        this.zaa.transact(r3, r4, r0, 0);     // Catch: Throwable -> L6
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

    public final void zad(int r2, Parcel r3) {
        this.zaa.transact(1, r3, null, 1);     // Catch: Throwable -> L5
        r3.recycle();
        return;
    L5:
        th = move-exception;
        r3.recycle();
        throw th;
    }
}
