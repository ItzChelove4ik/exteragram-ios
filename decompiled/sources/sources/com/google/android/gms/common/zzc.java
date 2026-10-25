package com.google.android.gms.common;

/* loaded from: classes4.dex */
public abstract class zzc {
    public static int zza(int r6) {
        int[] r1 = {1, 2, 3};
        int r3 = 0;
    L3:
        if (r3 >= 3) goto L11;
        int r4 = r1[r3];
        int r5 = r4 - 1;
        if (r4 == 0) goto L10;
        if (r5 == r6) goto L7;
        r3 = r3 + 1;
        goto L3
    L7:
        return r4;
    L10:
        throw null;
    L11:
        return 1;
    }
}
