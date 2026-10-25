package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableForwarder1;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
final class zzph extends zzpd implements ScheduledExecutorService, zzpc, AutoCloseable {
    final ScheduledExecutorService zza;

    public zzph(ScheduledExecutorService r1) {
        super(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzof, java.lang.AutoCloseable
    public /* synthetic */ void close() {
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableForwarder1.m(this);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture schedule(Runnable r2, long r3, TimeUnit r5) {
        ScheduledExecutorService r1 = this.zza;
        zzpm r22 = zzpm.zza(r2, null);
        return new zzpf(r22, r1.schedule(r22, r3, r5));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture schedule(Callable r2, long r3, TimeUnit r5) {
        zzpm r0 = new zzpm(r2);
        return new zzpf(r0, this.zza.schedule(r0, r3, r5));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture scheduleAtFixedRate(Runnable r2, long r3, long r5, TimeUnit r7) {
        zzpg r22 = new zzpg(r2);
        return new zzpf(r22, this.zza.scheduleAtFixedRate(r22, r3, r5, r7));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture scheduleWithFixedDelay(Runnable r2, long r3, long r5, TimeUnit r7) {
        zzpg r22 = new zzpg(r2);
        return new zzpf(r22, this.zza.scheduleWithFixedDelay(r22, r3, r5, r7));
    }
}
