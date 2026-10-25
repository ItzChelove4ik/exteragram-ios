package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes4.dex */
final class zzox implements ListenableFuture {
    static final ListenableFuture zza = null;
    private static final zzpb zzb = null;
    private final Object zzc;

    static {
        zza = new zzox(null);
        zzb = new zzpb(zzox.class);
    }

    public zzox(Object r1) {
        this.zzc = r1;
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable r7, Executor r8) {
        zzla.zzd(r7, "Runnable was null.");
        zzla.zzd(r8, "Executor was null.");
        r8.execute(r7);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        Logger r0 = zzb.zza();
        Level r1 = Level.SEVERE;
        String r6 = String.valueOf(r7);
        String r72 = String.valueOf(r8);
        StringBuilder r3 = new StringBuilder((r6.length() + 57) + r72.length());
        r3.append("RuntimeException while executing runnable ");
        r3.append(r6);
        r3.append(" with executor ");
        r3.append(r72);
        r0.logp(r1, "com.google.common.util.concurrent.ImmediateFuture", "addListener", r3.toString(), e);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean r1) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return this.zzc;
    }

    @Override // java.util.concurrent.Future
    public final Object get(long r1, TimeUnit r3) {
        r3.getClass();
        return this.zzc;
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
        Object r0 = this.zzc;
        String r3 = super.toString();
        String r02 = String.valueOf(r0);
        int r1 = String.valueOf(r3).length();
        int r12 = r1 + 25;
        StringBuilder r2 = new StringBuilder((r12 + r02.length()) + 2);
        r2.append(r3);
        r2.append("[status=SUCCESS, result=[");
        r2.append(r02);
        r2.append("]]");
        return r2.toString();
    }
}
