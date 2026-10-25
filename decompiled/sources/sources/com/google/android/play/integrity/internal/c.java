package com.google.android.play.integrity.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.fido.zzc$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class c {
    static {
        c.class.getClassLoader();
    }

    public static Parcelable a(Parcel r1, Parcelable.Creator r2) {
        if (r1.readInt() != 0) goto L7;
        return null;
    L7:
        return (Parcelable) r2.createFromParcel(r1);
    }

    public static void b(Parcel r0) {
        int r02 = r0.dataAvail();
        if (r02 > 0) goto L5;
        return;
    L5:
        zzc$$ExternalSyntheticBUOutline0.m(r02);
    }

    public static void c(Parcel r1, Parcelable r2) {
        r1.writeInt(1);
        r2.writeToParcel(r1, 0);
    }
}
