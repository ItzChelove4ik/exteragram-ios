package com.google.android.gms.internal.play_billing;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes5.dex */
public final class zzeu {
    static final zzeu zza = null;
    private static volatile zzeu zzd;
    private final Map zze;

    static {
        zza = new zzeu(true);
    }

    public zzeu(boolean r1) {
        this.zze = Collections.EMPTY_MAP;
    }

    public static zzeu zza() {
        zzeu r0 = zzd;
        if (r0 == null) goto L6;
        return r0;
    L6:
        monitor-enter(zzeu.class);
        zzeu r1 = zzd;     // Catch: Throwable -> L11
        if (r1 == null) goto L13;
        monitor-exit(zzeu.class);     // Catch: Throwable -> L11
        return r1;
    L13:
        int r12 = zzgs.$r8$clinit;     // Catch: Throwable -> L11
        zzeu r13 = zzfc.zzb(zzeu.class);     // Catch: Throwable -> L11
        zzd = r13;     // Catch: Throwable -> L11
        monitor-exit(zzeu.class);     // Catch: Throwable -> L11
        return r13;
    L11:
        th = move-exception;
        throw th;
    }

    public final zzfh zzb(zzgl r2, int r3) {
        zzet r0 = new zzet(r2, r3);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.zze.get(r0));
        return null;
    }
}
