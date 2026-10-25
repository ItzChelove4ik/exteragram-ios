package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.wearable.zzc;

/* loaded from: classes5.dex */
public final class zzfb extends com.google.android.gms.internal.wearable.zza implements IInterface {
    public zzfb(IBinder r2) {
        super(r2, "com.google.android.gms.wearable.internal.IWearableService");
    }

    public final void zzd(zzex r2, zzd r3) {
        Parcel r0 = zza();
        zzc.zze(r0, r2);
        zzc.zzd(r0, r3);
        zzJ(16, r0);
    }

    public final void zzz(zzex r2, String r3, String r4, byte[] r5) {
        Parcel r0 = zza();
        zzc.zze(r0, r2);
        r0.writeString(r3);
        r0.writeString(r4);
        r0.writeByteArray(r5);
        zzJ(12, r0);
    }
}
