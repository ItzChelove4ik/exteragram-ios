package com.google.android.gms.internal.clearcut;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zzb extends Binder implements IInterface {
    static {
    }

    public zzb(String r1) {
        attachInterface(this, r1);
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    public abstract boolean dispatchTransaction(int r1, Parcel r2, Parcel r3, int r4);

    @Override // android.os.Binder
    public boolean onTransact(int r2, Parcel r3, Parcel r4, int r5) {
        if (r2 <= 16777215) goto L5;
        boolean r0 = super.onTransact(r2, r3, r4, r5);
    L6:
        if (r0 == false) goto L10;
        return true;
    L10:
        return dispatchTransaction(r2, r3, r4, r5);
    L5:
        r3.enforceInterface(getInterfaceDescriptor());
        r0 = false;
        goto L6
    }
}
