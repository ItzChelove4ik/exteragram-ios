package com.google.android.gms.location;

import com.google.android.gms.common.internal.Preconditions;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class zzao {
    public static int zza(int r3) {
        boolean r0 = true;
        if (r3 == 0) goto L9;
        if (r3 == 1) goto L9;
        if (r3 != 2) goto L8;
        r3 = 2;
        goto L9
    L8:
        r0 = false;
    L9:
        Preconditions.checkArgument(r0, "throttle behavior %d must be a ThrottleBehavior.THROTTLE_* constant", new Object[]{Integer.valueOf(r3)});
        return r3;
    }

    public static String zzb(int r1) {
        if (r1 != 0) goto L4;
        return "THROTTLE_BACKGROUND";
    L4:
        if (r1 != 1) goto L6;
        return "THROTTLE_ALWAYS";
    L6:
        if (r1 != 2) goto L9;
        return "THROTTLE_NEVER";
    L9:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }
}
