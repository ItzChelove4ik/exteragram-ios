package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* loaded from: classes4.dex */
public final class zzadq extends zzacd {
    private final zzadu zza;

    public zzadq(zzadu r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.measurement.zzafj
    public final /* synthetic */ Object zzb(zzacv r3, zzadf r4) {
        int r0 = zzadu.zzd;
        zzadu r2 = this.zza.zzck();
        zzafp r02 = zzafl.zza().zzb(r2.getClass());     // Catch: RuntimeException -> L5 IOException -> L11 zzafy -> L18 zzaeh -> L21
        r02.zzg(r2, zzacw.zza(r3), r4);     // Catch: RuntimeException -> L5 IOException -> L11 zzafy -> L18 zzaeh -> L21
        r02.zzk(r2);     // Catch: RuntimeException -> L5 IOException -> L11 zzafy -> L18 zzaeh -> L21
        return r2;
    L21:
        e = move-exception;
        if (e.zzb() == true) goto L25;
        throw e;
    L25:
        throw new zzaeh(e);
    L18:
        e = move-exception;
        throw e.zza();
    L11:
        e = move-exception;
        if ((e.getCause() instanceof zzaeh) == false) goto L17;
        throw ((zzaeh) e.getCause());
    L17:
        throw new zzaeh(e);
    L5:
        e = move-exception;
        if ((e.getCause() instanceof zzaeh) == true) goto L9;
        throw e;
    L9:
        throw ((zzaeh) e.getCause());
    }
}
