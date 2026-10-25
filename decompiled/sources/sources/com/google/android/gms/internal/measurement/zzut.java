package com.google.android.gms.internal.measurement;

import com.google.common.base.Function;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ExecutionSequencer;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
abstract class zzut {
    private final String zza;
    private final ListenableFuture zzb;
    private final zzuv zzc;
    private final ExecutionSequencer zzd;
    private final zzvm zze;
    private final zzvm zzf;
    private final Object zzg;
    private final zzwb zzh;
    private List zzi;

    public zzut(zzuv r1, zzvc r2, ListenableFuture r3, boolean r4, String r5) {
        this.zzf = new zzvm(new zzul(this, null), MoreExecutors.directExecutor());
        this.zzg = new Object();
        this.zzi = new ArrayList();
        this.zzc = r1;
        this.zzb = r3;
        this.zza = r1.zzc();
        this.zze = new zzvm(new zzuh((zzui) r1), MoreExecutors.directExecutor());
        this.zzd = ExecutionSequencer.create();
        this.zzh = zzwb.zzb();
        zza(new zzuq(this));
    }

    public final void zza(AsyncFunction r2) {
        Object r0 = this.zzg;
        monitor-enter(r0);
        this.zzi.add(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final ListenableFuture zzb(Function r6, Executor r7) {
        AsyncFunction r62 = zzxa.zzc(new zzuo(r6));
        Stopwatch.createStarted(zzxh.zza());
        String r0 = String.valueOf(this.zza);
        zzwi r02 = this.zzh.zza("Update ".concat(r0), zzxd.zza);
        ListenableFuture r1 = this.zzf.zza();     // Catch: Throwable -> L6
        ExecutionSequencer r2 = this.zzd;     // Catch: Throwable -> L6
        r2.submitAsync(new zzum(r1), MoreExecutors.directExecutor());     // Catch: Throwable -> L6
        ListenableFuture r63 = r2.submitAsync(zzxa.zzb(new zzun(this, r1, r62, r7)), MoreExecutors.directExecutor());     // Catch: Throwable -> L6
        Futures.propagateCancellation(r63, r1);     // Catch: Throwable -> L6
        Futures.nonCancellationPropagating(this.zzb);     // Catch: Throwable -> L6
        ListenableFuture r5 = zzuy.zza(r63);     // Catch: Throwable -> L6
        r02.zza(r5);     // Catch: Throwable -> L6
        r02.close();
        return r5;
    L6:
        th = move-exception;
        r02.close();     // Catch: Throwable -> L9
    L11:
        throw th;
    L9:
        th = move-exception;
        th.addSuppressed(th);
        goto L11
    }

    public final /* synthetic */ ListenableFuture zzc(zzth r1) {
        return this.zze.zza();
    }

    public final /* synthetic */ ListenableFuture zzd(AsyncFunction r1, Executor r2, Object r3) {
        return this.zzc.zzb(r1, r2, null);
    }

    public final /* synthetic */ String zze() {
        return this.zza;
    }

    public final /* synthetic */ zzuv zzf() {
        return this.zzc;
    }

    public final /* synthetic */ zzvm zzg() {
        return this.zze;
    }

    public final /* synthetic */ Object zzh() {
        return this.zzg;
    }

    public final /* synthetic */ zzwb zzi() {
        return this.zzh;
    }

    public final /* synthetic */ List zzj() {
        return this.zzi;
    }

    public final /* synthetic */ void zzk(List r1) {
        this.zzi = r1;
    }
}
