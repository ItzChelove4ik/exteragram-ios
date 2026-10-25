package com.google.android.gms.internal.play_billing;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public abstract class zzcu extends zzcw {
    public static zzcz zza(Object r1) {
        return new zzcx(r1);
    }

    public static zzcz zzb(zzcz r0, long r1, TimeUnit r3, ScheduledExecutorService r4) {
        if (r0.isDone() == false) goto L6;
        return r0;
    L6:
        return zzde.zzs(r0, 28500, r3, r4);
    }

    public static void zzc(zzcz r1, zzcs r2, Executor r3) {
        r1.zzb(new zzct(r1, r2), r3);
    }
}
