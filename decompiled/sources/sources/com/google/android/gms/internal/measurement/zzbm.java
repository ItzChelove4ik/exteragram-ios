package com.google.android.gms.internal.measurement;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public abstract class zzbm extends Binder implements IInterface {
    public zzbm(String r1) {
        attachInterface(this, r1);
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int r2, Parcel r3, Parcel r4, int r5) {
        if (r2 > 16777215) goto L5;
        r3.enforceInterface(getInterfaceDescriptor());
    L10:
        return zza(r2, r3, r4, r5);
    L5:
        if (super.onTransact(r2, r3, r4, r5) == false) goto L10;
        return true;
    }

    public abstract boolean zza(int r1, Parcel r2, Parcel r3, int r4);
}
