package com.google.android.gms.internal.measurement;

import java.util.concurrent.Executor;
import java.util.logging.Level;

/* loaded from: classes4.dex */
public abstract class zzlz {
    public static final void zza(Level r1, Executor r2, String r3, Object... r4) {
        zzc(r1, r2, null, r3, r4);
    }

    public static final void zzb(Level r0, Executor r1, Throwable r2, String r3, Object... r4) {
        zzc(r0, r1, r2, r3, r4);
    }

    private static final void zzc(Level r1, Executor r2, Throwable r3, String r4, Object... r5) {
        r2.execute(zzxa.zza(new zzly(r1, r3, r4, r5)));
    }
}
