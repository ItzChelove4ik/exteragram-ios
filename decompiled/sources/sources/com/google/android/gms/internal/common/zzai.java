package com.google.android.gms.internal.common;

import com.google.android.gms.internal.cast.zzib$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public abstract class zzai {
    public static Object[] zza(Object[] r2, int r3) {
        int r0 = 0;
    L3:
        if (r0 >= r3) goto L11;
        if (r2[r0] == null) goto L7;
        r0 = r0 + 1;
        goto L3
    L7:
        zzib$$ExternalSyntheticBUOutline0.m(String.valueOf(r0).length() + 9, r0);
        return null;
    L11:
        return r2;
    }
}
