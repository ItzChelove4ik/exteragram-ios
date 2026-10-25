package com.google.android.gms.internal.measurement;

import android.os.BadParcelableException;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;

/* loaded from: classes.dex */
public abstract class zzbn {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final ClassLoader zzb = null;

    static {
        zzb = zzbn.class.getClassLoader();
    }

    public static boolean zza(Parcel r0) {
        if (r0.readInt() == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static Parcelable zzb(Parcel r1, Parcelable.Creator r2) {
        if (r1.readInt() != 0) goto L7;
        return null;
    L7:
        return (Parcelable) r2.createFromParcel(r1);
    }

    public static void zzc(Parcel r2, Parcelable r3) {
        if (r3 != null) goto L6;
        r2.writeInt(0);
        return;
    L6:
        r2.writeInt(1);
        r3.writeToParcel(r2, 0);
    }

    public static void zzd(Parcel r0, IInterface r1) {
        if (r1 != null) goto L5;
        r0.writeStrongBinder(null);
        return;
    L5:
        r0.writeStrongBinder(r1.asBinder());
    }

    public static HashMap zze(Parcel r1) {
        return r1.readHashMap(zzb);
    }

    public static void zzf(Parcel r3) {
        int r32 = r3.dataAvail();
        if (r32 > 0) goto L5;
        return;
    L5:
        StringBuilder r2 = new StringBuilder(String.valueOf(r32).length() + 45);
        r2.append("Parcel data not fully consumed, unread size: ");
        r2.append(r32);
        throw new BadParcelableException(r2.toString());
    }
}
