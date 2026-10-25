package com.google.android.gms.internal.cloudmessaging;

import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public abstract class zzc {
    static {
        zzc.class.getClassLoader();
    }

    public static Parcelable zza(Parcel r1, Parcelable.Creator r2) {
        if (r1.readInt() != 0) goto L7;
        return null;
    L7:
        return (Parcelable) r2.createFromParcel(r1);
    }

    public static void zzb(Parcel r2, Parcelable r3) {
        if (r3 != null) goto L6;
        r2.writeInt(0);
        return;
    L6:
        r2.writeInt(1);
        r3.writeToParcel(r2, 0);
    }

    public static void zzc(Parcel r0, IInterface r1) {
        r0.writeStrongBinder(r1.asBinder());
    }
}
