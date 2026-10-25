package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public final class zzv extends com.google.android.gms.internal.common.zza implements zzx {
    public zzv(IBinder r2) {
        super(r2, "com.google.android.gms.common.internal.ICertData");
    }

    @Override // com.google.android.gms.common.internal.zzx
    public final IObjectWrapper zzd() {
        Parcel r2 = zzB(1, zza());
        IObjectWrapper r0 = IObjectWrapper.Stub.asInterface(r2.readStrongBinder());
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.common.internal.zzx
    public final int zze() {
        Parcel r2 = zzB(2, zza());
        int r0 = r2.readInt();
        r2.recycle();
        return r0;
    }
}
