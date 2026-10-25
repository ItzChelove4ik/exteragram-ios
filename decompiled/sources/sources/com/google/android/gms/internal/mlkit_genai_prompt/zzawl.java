package com.google.android.gms.internal.mlkit_genai_prompt;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzawl {
    static final zzawl zza = null;
    private static volatile zzawl zzd;
    private final Map zze;

    static {
        zza = new zzawl(true);
    }

    public zzawl(boolean r1) {
        this.zze = Collections.EMPTY_MAP;
    }

    public static zzawl zzb() {
        int r0 = zzavq.$r8$clinit;
        return zza;
    }

    public static zzawl zzc() {
        zzawl r0 = zzd;
        if (r0 == null) goto L6;
        return r0;
    L6:
        monitor-enter(zzawl.class);
        zzawl r1 = zzd;     // Catch: Throwable -> L11
        if (r1 == null) goto L13;
        monitor-exit(zzawl.class);     // Catch: Throwable -> L11
        return r1;
    L13:
        int r12 = zzavq.$r8$clinit;     // Catch: Throwable -> L11
        zzawl r13 = zzaws.zzb(zzawl.class);     // Catch: Throwable -> L11
        zzd = r13;     // Catch: Throwable -> L11
        monitor-exit(zzawl.class);     // Catch: Throwable -> L11
        return r13;
    L11:
        th = move-exception;
        throw th;
    }

    public final zzawx zzd(zzaxw r2, int r3) {
        zzawk r0 = new zzawk(r2, r3);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.zze.get(r0));
        return null;
    }
}
