package com.google.android.gms.internal.auth_blockstore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.fido.zzc$$ExternalSyntheticBUOutline0;

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

    public void enforceNoDataAvail(Parcel r1) {
        int r0 = zzc.$r8$clinit;
        int r02 = r1.dataAvail();
        if (r02 > 0) goto L5;
        return;
    L5:
        zzc$$ExternalSyntheticBUOutline0.m(r02);
    }

    @Override // android.os.Binder
    public boolean onTransact(int r2, Parcel r3, Parcel r4, int r5) {
        if (routeToSuperOrEnforceInterface(r2, r3, r4, r5) == false) goto L7;
        return true;
    L7:
        return dispatchTransaction(r2, r3, r4, r5);
    }
}
