package com.google.android.gms.internal.mlkit_common;

import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
public abstract class zzaa {
    public static int zza(int r1, int r2) {
        if (r2 < 0) goto L9;
        int r12 = (r1 + (r1 >> 1)) + 1;
        if (r12 >= r2) goto L6;
        int r13 = Integer.highestOneBit(r2 - 1);
        r12 = r13 + r13;
    L6:
        if (r12 >= 0) goto L11;
        return Integer.MAX_VALUE;
    L11:
        return r12;
    L9:
        Buffer$$ExternalSyntheticBUOutline2.m("cannot store more than MAX_VALUE elements");
        return 0;
    }
}
