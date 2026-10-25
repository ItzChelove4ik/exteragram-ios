package com.google.android.gms.internal.fido;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public abstract class zzc {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final ClassLoader zzb = null;

    static {
        zzb = zzc.class.getClassLoader();
    }

    public static Parcelable zza(Parcel r1, Parcelable.Creator r2) {
        if (r1.readInt() != 0) goto L7;
        return null;
    L7:
        return (Parcelable) r2.createFromParcel(r1);
    }

    public static void zzc(Parcel r0) {
        int r02 = r0.dataAvail();
        if (r02 > 0) goto L5;
        return;
    L5:
        zzc$$ExternalSyntheticBUOutline0.m(r02);
    }

    public static void zzd(Parcel r2, Parcelable r3) {
        if (r3 != null) goto L6;
        r2.writeInt(0);
        return;
    L6:
        r2.writeInt(1);
        r3.writeToParcel(r2, 0);
    }
}
