package com.google.android.gms.internal.fido;

import com.android.dx.util.LabeledList$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class zzcr {
    public static Object zza(Object r0, int r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        LabeledList$$ExternalSyntheticBUOutline0.m("at index ", r1);
        return null;
    }

    public static Object[] zzb(Object[] r2, int r3) {
        int r0 = 0;
    L3:
        if (r0 >= r3) goto L5;
        zza(r2[r0], r0);
        r0 = r0 + 1;
        goto L3
    L5:
        return r2;
    }
}
