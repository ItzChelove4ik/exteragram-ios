package com.google.android.gms.internal.identity_credentials;

import android.os.BadParcelableException;
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

    private boolean routeToSuperOrEnforceInterface(int r2, Parcel r3, Parcel r4, int r5) {
        if (r2 > 16777215) goto L5;
        r3.enforceInterface(getInterfaceDescriptor());
        return false;
    L5:
        return super.onTransact(r2, r3, r4, r5);
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    public abstract boolean dispatchTransaction(int r1, Parcel r2, Parcel r3, int r4);

    public void enforceNoDataAvail(Parcel r3) {
        int r2 = zzc.$r8$clinit;
        int r22 = r3.dataAvail();
        if (r22 > 0) goto L5;
        return;
    L5:
        StringBuilder r1 = new StringBuilder(String.valueOf(r22).length() + 45);
        r1.append("Parcel data not fully consumed, unread size: ");
        r1.append(r22);
        throw new BadParcelableException(r1.toString());
    }

    @Override // android.os.Binder
    public boolean onTransact(int r2, Parcel r3, Parcel r4, int r5) {
        if (routeToSuperOrEnforceInterface(r2, r3, r4, r5) == false) goto L7;
        return true;
    L7:
        return dispatchTransaction(r2, r3, r4, r5);
    }
}
