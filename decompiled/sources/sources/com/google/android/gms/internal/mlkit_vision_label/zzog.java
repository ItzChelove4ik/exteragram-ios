package com.google.android.gms.internal.mlkit_vision_label;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public final class zzog extends zza implements zzoi {
    public zzog(IBinder r2) {
        super(r2, "com.google.mlkit.vision.label.aidls.IImageLabelerCreator");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzoi
    public final zzof zzd(IObjectWrapper r2, zzol r3) {
        Parcel r0 = zza();
        zzc.zzb(r0, r2);
        zzc.zza(r0, r3);
        Parcel r1 = zzb(1, r0);
        IBinder r22 = r1.readStrongBinder();
        if (r22 != null) goto L5;
        zzof r23 = null;
    L9:
        r1.recycle();
        return r23;
    L5:
        IInterface r32 = r22.queryLocalInterface("com.google.mlkit.vision.label.aidls.IImageLabeler");
        if ((r32 instanceof zzof) == false) goto L8;
        r23 = (zzof) r32;
        goto L9
    L8:
        r23 = new zzof(r22);
        goto L9
    }
}
