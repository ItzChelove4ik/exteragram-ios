package com.google.android.recaptcha.internal;

import android.app.Application;
import com.google.android.gms.tasks.Task;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;

/* loaded from: classes5.dex */
public final class zzjm {
    public static final zzjm zza = null;
    private static zzjt zzb;

    static {
        zza = new zzjm();
    }

    private zzjm() {
    }

    public static final Task zze(Application r8, String r9, long r10) {
        return zzhz.zza(BuildersKt.async$default(zzik.zza(zzik.zza, r8, null, 2, null).zze().zzc(), null, null, new zzjl(r8, r9, r10, null), 3, null));
    }

    public final zzjt zza(Application r2, zzko r3) {
        monitor-enter(this);
        zzjt r0 = zzb;     // Catch: Throwable -> L6
        if (r0 != null) goto L9;
        r0 = new zzjt(r2, r3);     // Catch: Throwable -> L6
    L9:
        if (zzb != null) goto L11;
        zzb = r0;     // Catch: Throwable -> L6
    L11:
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final Object zzc(Application r10, String r11, long r12, Continuation r14) {
        return zzjt.zza(zza(r10, zzik.zza(zzik.zza, r10, null, 2, null)), r11, r12, null, r14, 4, null);
    }
}
