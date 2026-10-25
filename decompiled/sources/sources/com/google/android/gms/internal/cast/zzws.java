package com.google.android.gms.internal.cast;

import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableForwarder1;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
final class zzws extends zzwp implements ScheduledExecutorService, zzwo, AutoCloseable {
    final ScheduledExecutorService zza;

    public zzws(ScheduledExecutorService r1) {
        super(r1);
        r1.getClass();
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.cast.zzwd, java.lang.AutoCloseable
    public /* synthetic */ void close() {
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableForwarder1.m(this);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture schedule(Runnable r2, long r3, TimeUnit r5) {
        ScheduledExecutorService r1 = this.zza;
        zzww r22 = zzww.zzo(r2, null);
        return new zzwq(r22, r1.schedule(r22, r3, r5));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture schedule(Callable r2, long r3, TimeUnit r5) {
        zzww r0 = new zzww(r2);
        return new zzwq(r0, this.zza.schedule(r0, r3, r5));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture scheduleAtFixedRate(Runnable r2, long r3, long r5, TimeUnit r7) {
        zzwr r22 = new zzwr(r2);
        return new zzwq(r22, this.zza.scheduleAtFixedRate(r22, r3, r5, r7));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture scheduleWithFixedDelay(Runnable r2, long r3, long r5, TimeUnit r7) {
        zzwr r22 = new zzwr(r2);
        return new zzwq(r22, this.zza.scheduleWithFixedDelay(r22, r3, r5, r7));
    }
}
