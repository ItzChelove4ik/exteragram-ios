package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public abstract class zzc {
    private static final ClassLoader zza = null;

    static {
        zza = zzc.class.getClassLoader();
    }

    public static void zza(Parcel r1, Parcelable r2) {
        r1.writeInt(1);
        r2.writeToParcel(r1, 0);
    }

    public static void zzb(Parcel r0, IInterface r1) {
        if (r1 != null) goto L5;
        r0.writeStrongBinder(null);
        return;
    L5:
        r0.writeStrongBinder(r1.asBinder());
    }
}
