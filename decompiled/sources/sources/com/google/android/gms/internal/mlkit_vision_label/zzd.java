package com.google.android.gms.internal.mlkit_vision_label;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public final class zzd extends zza implements IInterface {
    public zzd(IBinder r2) {
        super(r2, "com.google.android.gms.vision.label.internal.client.INativeImageLabeler");
    }

    public final void zzd() {
        zzc(2, zza());
    }

    public final zzh[] zze(IObjectWrapper r2, zzl r3) {
        Parcel r0 = zza();
        zzc.zzb(r0, r2);
        zzc.zza(r0, r3);
        Parcel r1 = zzb(1, r0);
        zzh[] r22 = (zzh[]) r1.createTypedArray(zzh.CREATOR);
        r1.recycle();
        return r22;
    }
}
