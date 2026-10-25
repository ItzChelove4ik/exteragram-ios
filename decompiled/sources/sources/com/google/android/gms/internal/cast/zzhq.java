package com.google.android.gms.internal.cast;

import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class zzhq {
    public zzhq() {
    }

    public static int zza(int r1, int r2) {
        if (r2 < 0) goto L11;
        if (r2 > r1) goto L5;
        return r1;
    L5:
        int r12 = (r1 + (r1 >> 1)) + 1;
        if (r12 >= r2) goto L8;
        int r13 = Integer.highestOneBit(r2 - 1);
        r12 = r13 + r13;
    L8:
        if (r12 >= 0) goto L13;
        return Integer.MAX_VALUE;
    L13:
        return r12;
    L11:
        f$$ExternalSyntheticBUOutline1.m("cannot store more than Integer.MAX_VALUE elements");
        return 0;
    }
}
