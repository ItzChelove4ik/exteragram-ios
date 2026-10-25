package com.google.android.gms.internal.mlkit_common;

import com.android.dx.util.LabeledList$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public abstract class zzak {
    public static Object[] zza(Object[] r2, int r3) {
        int r0 = 0;
    L3:
        if (r0 >= r3) goto L11;
        if (r2[r0] == null) goto L7;
        r0 = r0 + 1;
        goto L3
    L7:
        LabeledList$$ExternalSyntheticBUOutline0.m("at index ", r0);
        return null;
    L11:
        return r2;
    }
}
