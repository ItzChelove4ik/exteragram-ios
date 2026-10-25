package com.google.android.gms.internal.p000authapi;

import android.os.BadParcelableException;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public abstract class zbc {
    static {
        zbc.class.getClassLoader();
    }

    public static Parcelable zba(Parcel r1, Parcelable.Creator r2) {
        if (r1.readInt() != 0) goto L7;
        return null;
    L7:
        return (Parcelable) r2.createFromParcel(r1);
    }

    public static void zbb(Parcel r2, Parcelable r3) {
        if (r3 != null) goto L6;
        r2.writeInt(0);
        return;
    L6:
        r2.writeInt(1);
        r3.writeToParcel(r2, 0);
    }

    public static void zbc(Parcel r0, IInterface r1) {
        r0.writeStrongBinder(r1.asBinder());
    }

    public static void zbd(Parcel r3) {
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
