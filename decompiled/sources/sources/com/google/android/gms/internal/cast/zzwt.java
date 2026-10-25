package com.google.android.gms.internal.cast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes4.dex */
public abstract class zzwt {
    public static zzwo zza(ExecutorService r1) {
        if ((r1 instanceof zzwo) == false) goto L7;
        return (zzwo) r1;
    L7:
        if ((r1 instanceof ScheduledExecutorService) == false) goto L11;
        return new zzws((ScheduledExecutorService) r1);
    L11:
        return new zzwp(r1);
    }
}
