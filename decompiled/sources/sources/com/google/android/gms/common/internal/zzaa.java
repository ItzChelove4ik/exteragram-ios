package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes4.dex */
final class zzaa implements IGmsServiceBroker {
    private final IBinder zza;

    public zzaa(IBinder r1) {
        this.zza = r1;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.zza;
    }

    @Override // com.google.android.gms.common.internal.IGmsServiceBroker
    public final void getService(IGmsCallbacks r4, GetServiceRequest r5) {
        Parcel r0 = Parcel.obtain();
        Parcel r1 = Parcel.obtain();
        r0.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");     // Catch: Throwable -> L6
        if (r4 == null) goto L8;
        IBinder r42 = r4.asBinder();     // Catch: Throwable -> L6
    L9:
        r0.writeStrongBinder(r42);     // Catch: Throwable -> L6
        if (r5 == null) goto L12;
        r0.writeInt(1);     // Catch: Throwable -> L6
        zzm.zza(r5, r0, 0);     // Catch: Throwable -> L6
    L13:
        this.zza.transact(46, r0, r1, 0);     // Catch: Throwable -> L6
        r1.readException();     // Catch: Throwable -> L6
        r1.recycle();
        r0.recycle();
        return;
    L12:
        r0.writeInt(0);     // Catch: Throwable -> L6
        goto L13
    L8:
        r42 = null;
    L6:
        th = move-exception;
        r1.recycle();
        r0.recycle();
        throw th;
    }
}
