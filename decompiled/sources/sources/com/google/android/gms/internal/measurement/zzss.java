package com.google.android.gms.internal.measurement;

import java.io.InputStream;

/* loaded from: classes4.dex */
public final class zzss implements zzrt {
    private final zzafj zza;
    private final zzadf zzb;

    private zzss(zzafj r2) {
        this.zzb = zzadf.zza();
        this.zza = r2;
    }

    public static zzss zzb(zzafc r1) {
        return new zzss(r1.zzcj());
    }

    @Override // com.google.android.gms.internal.measurement.zzrt
    public final /* bridge */ /* synthetic */ Object zza(zzrs r2) {
        InputStream r22 = zzst.zzc(r2);
        zzafc r1 = (zzafc) this.zza.zza(r22, this.zzb);     // Catch: Throwable -> L7
        if (r22 == null) goto L6;
        r22.close();
    L6:
        return r1;
    L7:
        th = move-exception;
        if (r22 != null) goto L16;
    L13:
        throw th;
    L16:
        r22.close();     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        th.addSuppressed(th);
        goto L13
    }
}
