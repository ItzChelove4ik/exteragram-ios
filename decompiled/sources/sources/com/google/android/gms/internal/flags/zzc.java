package com.google.android.gms.internal.flags;

import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zzc {
    private static final ClassLoader zza = null;

    static {
        zza = zzc.class.getClassLoader();
    }

    public static void zza(Parcel r0, boolean r1) {
        r0.writeInt(r1 ? 1 : 0);
    }

    public static boolean zzc(Parcel r0) {
        if (r0.readInt() == 0) goto L6;
        return true;
    L6:
        return false;
    }
}
