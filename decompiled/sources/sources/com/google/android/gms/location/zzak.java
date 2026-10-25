package com.google.android.gms.location;

import com.google.android.gms.common.internal.Preconditions;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class zzak {
    public static int zza(int r3) {
        boolean r1 = true;
        if (r3 != 100) goto L5;
    L12:
        Preconditions.checkArgument(r1, "priority %d must be a Priority.PRIORITY_* constant", new Object[]{Integer.valueOf(r3)});
        return r3;
    L5:
        if (r3 == 102) goto L12;
        if (r3 == 104) goto L12;
        if (r3 != 105) goto L11;
        r3 = 105;
        goto L12
    L11:
        r1 = false;
        goto L12
    }

    public static String zzb(int r1) {
        if (r1 != 100) goto L5;
        return "HIGH_ACCURACY";
    L5:
        if (r1 != 102) goto L7;
        return "BALANCED_POWER_ACCURACY";
    L7:
        if (r1 != 104) goto L9;
        return "LOW_POWER";
    L9:
        if (r1 != 105) goto L12;
        return "PASSIVE";
    L12:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }
}
