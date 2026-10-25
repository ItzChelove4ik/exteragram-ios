package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zzwi implements Runnable, zzwt {
    private zzws zza;
    private final boolean zzb;
    private boolean zzc;
    private boolean zzd;
    private boolean zze;

    public zzwi(zzws r2, boolean r3) {
        this.zze = false;
        this.zza = r2;
        this.zzb = zzrn.zza(Thread.currentThread());
        this.zze = r3;
    }

    private final void zzb() {
        this.zzc = true;
        if (this.zzb == true) goto L5;
        return;
    L5:
        if (this.zzd == true) goto L9;
        zzrn.zza(Thread.currentThread());
        return;
    }

    @Override // com.google.android.gms.internal.measurement.zzwt, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        zzws r0 = this.zza;
        this.zza = null;     // Catch: Throwable -> L15
        if (this.zzd == false) goto L7;
    L9:
        if (r0 == null) goto L12;
        r0.close();
    L12:
        if (this.zze == false) goto L28;
        zzwg r2 = zzwg.zza;
        zzvy.zzc(zzvy.zzd(), r2);
        return;
    L28:
        return;
    L7:
        if (this.zzc == true) goto L18;
        zzb();     // Catch: Throwable -> L15
        goto L9
    L18:
        throw new IllegalStateException("Span was already closed!");     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        if (r0 != null) goto L25;
    L24:
        throw th;
    L25:
        r0.close();     // Catch: Throwable -> L22
    L22:
        th = move-exception;
        th.addSuppressed(th);
        goto L24
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzc == false) goto L5;
    L9:
        zzwh r1 = zzwh.zza;
        zzrn.zzb().post(r1);
        return;
    L5:
        if (this.zzd == false) goto L9;
        zzb();
    }

    public final ListenableFuture zza(ListenableFuture r3) {
        if (this.zzc == false) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Span was already closed. Did you attach it to a future after calling Tracer.endSpan()?");
        return null;
    L5:
        if (this.zzd == true) goto L8;
        this.zzd = true;
        r3.addListener(this, MoreExecutors.directExecutor());
        return r3;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("Signal is already attached to future");
        return null;
    }
}
