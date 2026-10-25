package com.google.android.gms.internal.mlkit_vision_label;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzof extends zza implements IInterface {
    public zzof(IBinder r2) {
        super(r2, "com.google.mlkit.vision.label.aidls.IImageLabeler");
    }

    public final List zzd(IObjectWrapper r2, zzod r3) {
        Parcel r0 = zza();
        zzc.zzb(r0, r2);
        zzc.zza(r0, r3);
        Parcel r1 = zzb(3, r0);
        ArrayList r22 = r1.createTypedArrayList(zzoj.CREATOR);
        r1.recycle();
        return r22;
    }

    public final void zze() {
        zzc(1, zza());
    }

    public final void zzf() {
        zzc(2, zza());
    }
}
