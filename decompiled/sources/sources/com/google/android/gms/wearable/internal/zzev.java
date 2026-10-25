package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.wearable.zzc;

/* loaded from: classes5.dex */
public final class zzev extends com.google.android.gms.internal.wearable.zza implements IInterface {
    public zzev(IBinder r2) {
        super(r2, "com.google.android.gms.wearable.internal.IRpcResponseCallback");
    }

    public final void zzd(boolean r2, byte[] r3) {
        Parcel r0 = zza();
        zzc.zzc(r0, r2);
        r0.writeByteArray(r3);
        zzK(1, r0);
    }
}
