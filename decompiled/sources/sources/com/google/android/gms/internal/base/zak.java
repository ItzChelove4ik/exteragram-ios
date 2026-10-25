package com.google.android.gms.internal.base;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/* loaded from: classes4.dex */
public abstract class zak {
    public static final int zaa = 0;

    static {
        if (Build.VERSION.SDK_INT < 31) goto L5;
        int r0 = 33554432;
    L6:
        zaa = r0;
        return;
    L5:
        r0 = 0;
        goto L6
    }

    public static PendingIntent zaa(Context r0, int r1, Intent r2, int r3) {
        return PendingIntent.getActivity(r0, r1, r2, r3);
    }
}
