package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLogging;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes5.dex */
public final class zzgq {
    private static zzgq zza;
    private final zzic zzb;
    private final TelemetryLoggingClient zzc;
    private final AtomicLong zzd;

    private zzgq(Context r4, zzic r5) {
        this.zzd = new AtomicLong(-1);
        this.zzc = TelemetryLogging.getClient(r4, TelemetryLoggingOptions.builder().setApi("measurement:api").build());
        this.zzb = r5;
    }

    public static zzgq zza(zzic r2) {
        if (zza != null) goto L6;
        zza = new zzgq(r2.zzaZ(), r2);
    L6:
        return zza;
    }

    public final synchronized void zzb(int r18, int r19, long r20, long r22, int r24) {
        monitor-enter(this);
        long r2 = this.zzb.zzba().elapsedRealtime();     // Catch: Throwable -> L15
        AtomicLong r0 = this.zzd;     // Catch: Throwable -> L15
        if (r0.get() != (-1)) goto L9;
    L12:
        this.zzc.log(new TelemetryData(0, Arrays.asList(new MethodInvocation[]{new MethodInvocation(36301, r19, 0, r20, r22, null, null, 0, r24)}))).addOnFailureListener(new zzgp(this, r2));     // Catch: Throwable -> L15
        monitor-exit(this);
        return;
    L9:
        if ((r2 - r0.get()) > 1800000) goto L12;
        monitor-exit(this);
        return;
    L15:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ void zzc(long r1, Exception r3) {
        this.zzd.set(r1);
    }
}
