package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes4.dex */
public abstract class zzfg {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
    }

    public static PendingIntent zza(Context r0, int r1, Intent r2, int r3) {
        return PendingIntent.getActivity(r0, 0, r2, 201326592);
    }

    public static PendingIntent zzb(Context r0, int r1, Intent r2, int r3) {
        return PendingIntent.getBroadcast(r0, 0, r2, r3);
    }
}
