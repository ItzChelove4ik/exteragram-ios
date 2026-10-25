package com.google.android.gms.internal.play_billing;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* loaded from: classes5.dex */
final class zzcx implements zzcz {
    private static final zzcy zza = null;
    private final Object zzb;

    static {
        zza = new zzcy(zzcx.class);
    }

    public zzcx(Object r1) {
        this.zzb = r1;
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean r1) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return this.zzb;
    }

    @Override // java.util.concurrent.Future
    public final Object get(long r1, TimeUnit r3) {
        r3.getClass();
        return this.zzb;
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return true;
    }

    public final String toString() {
        Object r0 = this.zzb;
        return super.toString() + "[status=SUCCESS, result=[" + r0.toString() + "]]";
    }

    @Override // com.google.android.gms.internal.play_billing.zzcz
    public final void zzb(Runnable r7, Executor r8) {
        zzbg.zzc(r8, "Executor was null.");
        r8.execute(r7);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        zza.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.ImmediateFuture", "addListener", "RuntimeException while executing runnable " + r7.toString() + " with executor " + String.valueOf(r8), e);
    }
}
