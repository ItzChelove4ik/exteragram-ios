package com.google.android.gms.internal.auth_blockstore;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zza implements IInterface {
    private final String mDescriptor;
    private final IBinder mRemote;

    public zza(IBinder r1, String r2) {
        this.mRemote = r1;
        this.mDescriptor = r2;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.mRemote;
    }

    public Parcel obtainAndWriteInterfaceToken() {
        Parcel r0 = Parcel.obtain();
        r0.writeInterfaceToken(this.mDescriptor);
        return r0;
    }

    public void transactAndReadExceptionReturnVoid(int r3, Parcel r4) {
        Parcel r0 = Parcel.obtain();
        this.mRemote.transact(r3, r4, r0, 0);     // Catch: Throwable -> L6
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
