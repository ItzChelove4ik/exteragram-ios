package com.google.android.gms.common;

/* loaded from: classes4.dex */
abstract class zzz {
    public static int zza(int r5) {
        int[] r1 = {1, 2, 3, 4, 5, 6};
        int r2 = 0;
    L3:
        if (r2 >= 6) goto L11;
        int r3 = r1[r2];
        int r4 = r3 - 1;
        if (r3 == 0) goto L10;
        if (r4 == r5) goto L7;
        r2 = r2 + 1;
        goto L3
    L7:
        return r3;
    L10:
        throw null;
    L11:
        return 1;
    }
}
