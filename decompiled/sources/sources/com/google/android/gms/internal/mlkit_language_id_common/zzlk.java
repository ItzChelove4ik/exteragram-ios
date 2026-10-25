package com.google.android.gms.internal.mlkit_language_id_common;

import android.content.Context;
import android.os.SystemClock;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLogging;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes4.dex */
public final class zzlk {
    private final TelemetryLoggingClient zza;
    private final AtomicLong zzb;

    public zzlk(Context r3, String r4) {
        this.zzb = new AtomicLong(-1);
        this.zza = TelemetryLogging.getClient(r3, TelemetryLoggingOptions.builder().setApi("mlkit:natural_language").build());
    }

    public static zzlk zza(Context r2) {
        return new zzlk(r2, "mlkit:natural_language");
    }

    public final /* synthetic */ void zzb(long r1, Exception r3) {
        this.zzb.set(r1);
    }

    public final synchronized void zzc(int r17, int r18, long r19, long r21) {
        monitor-enter(this);
        long r2 = SystemClock.elapsedRealtime();     // Catch: Throwable -> L15
        if (this.zzb.get() != (-1)) goto L9;
    L12:
        this.zza.log(new TelemetryData(0, Arrays.asList(new MethodInvocation[]{new MethodInvocation(r17, r18, 0, r19, r21, null, null, 0)}))).addOnFailureListener(new zzlj(this, r2));     // Catch: Throwable -> L15
        monitor-exit(this);
        return;
    L9:
        if ((r2 - this.zzb.get()) > 1800000) goto L12;
        monitor-exit(this);
        return;
    L15:
        th = move-exception;
        throw th;
    }
}
