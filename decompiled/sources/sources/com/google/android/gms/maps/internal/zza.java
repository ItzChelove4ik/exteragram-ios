package com.google.android.gms.maps.internal;

/* loaded from: classes5.dex */
public abstract class zza {
    public static byte zza(Boolean r0) {
        if (r0 != null) goto L4;
        return -1;
    L4:
        if (r0.booleanValue() == true) goto L7;
        return 0;
    L7:
        return 1;
    }

    public static Boolean zzb(byte r1) {
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L8;
        return null;
    L8:
        return Boolean.TRUE;
    L10:
        return Boolean.FALSE;
    }
}
