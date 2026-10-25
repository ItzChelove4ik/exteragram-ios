package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzadf {
    static final zzadf zza = null;
    private static volatile zzadf zzd;
    private final Map zze;

    static {
        zza = new zzadf(true);
    }

    public zzadf(boolean r1) {
        this.zze = Collections.EMPTY_MAP;
    }

    public static zzadf zza() {
        int r0 = zzacf.$r8$clinit;
        return zza;
    }

    public static zzadf zzb() {
        zzadf r0 = zzd;
        if (r0 == null) goto L6;
        return r0;
    L6:
        monitor-enter(zzadf.class);
        zzadf r1 = zzd;     // Catch: Throwable -> L11
        if (r1 == null) goto L13;
        monitor-exit(zzadf.class);     // Catch: Throwable -> L11
        return r1;
    L13:
        int r12 = zzacf.$r8$clinit;     // Catch: Throwable -> L11
        zzadf r13 = zzadn.zzb(zzadf.class);     // Catch: Throwable -> L11
        zzd = r13;     // Catch: Throwable -> L11
        monitor-exit(zzadf.class);     // Catch: Throwable -> L11
        return r13;
    L11:
        th = move-exception;
        throw th;
    }

    public final zzadt zzc(zzafc r2, int r3) {
        zzade r0 = new zzade(r2, r3);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.zze.get(r0));
        return null;
    }
}
