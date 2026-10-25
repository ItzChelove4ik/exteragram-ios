package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.android.gms.internal.cast.zzib$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class zzna {
    public static Object[] zza(Object[] r2, int r3) {
        int r0 = 0;
    L3:
        if (r0 >= r3) goto L5;
        zzb(r2[r0], r0);
        r0 = r0 + 1;
        goto L3
    L5:
        return r2;
    }

    public static Object zzb(Object r0, int r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        zzib$$ExternalSyntheticBUOutline0.m(String.valueOf(r1).length() + 9, r1);
        return null;
    }
}
