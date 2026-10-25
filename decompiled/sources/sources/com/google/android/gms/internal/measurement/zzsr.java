package com.google.android.gms.internal.measurement;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public final class zzsr implements zzrt {
    private boolean zza;

    static {
        new AtomicInteger();
    }

    private zzsr() {
        this.zza = false;
    }

    public static zzsr zzb() {
        return new zzsr();
    }

    @Override // com.google.android.gms.internal.measurement.zzrt
    public final /* bridge */ /* synthetic */ Object zza(zzrs r2) {
        if (this.zza == true) goto L5;
        zzsj r1 = zzsj.zza(zzst.zzc(r2));
    L16:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L22
    L24:
        throw th;
    L22:
        th = move-exception;
        th.addSuppressed(th);
        goto L24
    L12:
        if ((r1.zzb() instanceof zzsf) == false) goto L19;
        File r22 = ((zzsf) r1.zzb()).zza();     // Catch: Throwable -> L16
        r1.close();
        return r22;
    L19:
        throw new IOException("Not convertible and fallback to pipe is disabled.");     // Catch: Throwable -> L16
    L5:
        if (r2.zze() == true) goto L9;
        return r2.zza().zzg(r2.zzb());
    L9:
        throw new zzsk("Short circuit would skip transforms.");
    }

    public final zzsr zzc() {
        this.zza = true;
        return this;
    }
}
