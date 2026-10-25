package com.google.android.gms.internal.cloudmessaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/* loaded from: classes4.dex */
public abstract class zzr {
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

    public static PendingIntent zza(Context r0, int r1, Intent r2, int r3) {
        return PendingIntent.getBroadcast(r0, 0, r2, r3);
    }
}
