package com.google.android.gms.internal.wearable;

import android.os.Build;

/* loaded from: classes5.dex */
public abstract class zzd {
    public static final int zza = 0;

    static {
        if (Build.VERSION.SDK_INT < 31) goto L5;
        int r0 = 33554432;
    L6:
        zza = r0;
        return;
    L5:
        r0 = 0;
        goto L6
    }
}
