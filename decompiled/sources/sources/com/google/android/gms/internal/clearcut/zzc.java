package com.google.android.gms.internal.clearcut;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public abstract class zzc {
    private static final ClassLoader zzd = null;

    static {
        zzd = zzc.class.getClassLoader();
    }

    public static <T extends Parcelable> T zza(Parcel r1, Parcelable.Creator<T> r2) {
        if (r1.readInt() != 0) goto L7;
        return null;
    L7:
        return r2.createFromParcel(r1);
    }

    public static void zza(Parcel r0, IInterface r1) {
        if (r1 != null) goto L6;
        IBinder r12 = null;
    L4:
        r0.writeStrongBinder(r12);
        return;
    L6:
        r12 = r1.asBinder();
        goto L4
    }

    public static void zza(Parcel r2, Parcelable r3) {
        if (r3 != null) goto L6;
        r2.writeInt(0);
        return;
    L6:
        r2.writeInt(1);
        r3.writeToParcel(r2, 0);
    }
}
