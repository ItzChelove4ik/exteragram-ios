package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzhz extends zzjf {
    private static final AtomicLong zzj = null;
    private zzhy zza;
    private zzhy zzb;
    private final PriorityBlockingQueue zzc;
    private final BlockingQueue zzd;
    private final Thread.UncaughtExceptionHandler zze;
    private final Thread.UncaughtExceptionHandler zzf;
    private final Object zzg;
    private final Semaphore zzh;

    static {
        zzj = new AtomicLong(Long.MIN_VALUE);
    }

    public zzhz(zzic r2) {
        super(r2);
        this.zzg = new Object();
        this.zzh = new Semaphore(2);
        this.zzc = new PriorityBlockingQueue();
        this.zzd = new LinkedBlockingQueue();
        this.zze = new zzhw(this, "Thread death: Uncaught exception on worker thread");
        this.zzf = new zzhw(this, "Thread death: Uncaught exception on network thread");
    }

    public static /* synthetic */ AtomicLong zzu() {
        return zzj;
    }

    private final void zzz(zzhx r4) {
        Object r0 = this.zzg;
        monitor-enter(r0);
        PriorityBlockingQueue r1 = this.zzc;     // Catch: Throwable -> L7
        r1.add(r4);     // Catch: Throwable -> L7
        zzhy r42 = this.zza;     // Catch: Throwable -> L7
        if (r42 != null) goto L9;
        zzhy r43 = new zzhy(this, "Measurement Worker", r1);     // Catch: Throwable -> L7
        this.zza = r43;     // Catch: Throwable -> L7
        r43.setUncaughtExceptionHandler(this.zze);     // Catch: Throwable -> L7
        this.zza.start();     // Catch: Throwable -> L7
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L9:
        r42.zza();     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.measurement.internal.zzjf
    public final boolean zza() {
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzje
    public final void zzaY() {
        if (Thread.currentThread() != this.zzb) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Call expected from network thread");
    }

    public final void zzd() {
        if (Thread.currentThread() == this.zza) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Call not expected from worker thread");
    }

    public final boolean zze() {
        if (Thread.currentThread() != this.zza) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzf() {
        if (Thread.currentThread() != this.zzb) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzje
    public final void zzg() {
        if (Thread.currentThread() != this.zza) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Call expected from worker thread");
    }

    public final Future zzh(Callable r4) {
        zzw();
        Preconditions.checkNotNull(r4);
        zzhx r0 = new zzhx(this, r4, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zza) goto L5;
        zzz(r0);
        return r0;
    L5:
        if (this.zzc.isEmpty() == true) goto L7;
        this.zzu.zzaW().zze().zza("Callable skipped the worker queue.");
    L7:
        r0.run();
        return r0;
    }

    public final Future zzi(Callable r4) {
        zzw();
        Preconditions.checkNotNull(r4);
        zzhx r0 = new zzhx(this, r4, true, "Task exception on worker thread");
        if (Thread.currentThread() != this.zza) goto L6;
        r0.run();
        return r0;
    L6:
        zzz(r0);
        return r0;
    }

    public final void zzj(Runnable r4) {
        zzw();
        Preconditions.checkNotNull(r4);
        zzz(new zzhx(this, r4, false, "Task exception on worker thread"));
    }

    public final Object zzk(AtomicReference r3, long r4, String r6, Runnable r7) {
        monitor-enter(r3);
        this.zzu.zzaX().zzj(r7);     // Catch: Throwable -> L11
        r3.wait(r4);     // Catch: Throwable -> L11 InterruptedException -> L13
        monitor-exit(r3);     // Catch: Throwable -> L11
        Object r32 = r3.get();
        if (r32 != null) goto L10;
        this.zzu.zzaW().zze().zza("Timed out waiting for ".concat(r6));
    L10:
        return r32;
    L13:
        zzgs r2 = this.zzu.zzaW().zze();     // Catch: Throwable -> L11
        StringBuilder r5 = new StringBuilder(r6.length() + 24);     // Catch: Throwable -> L11
        r5.append("Interrupted waiting for ");     // Catch: Throwable -> L11
        r5.append(r6);     // Catch: Throwable -> L11
        r2.zza(r5.toString());     // Catch: Throwable -> L11
        return null;
    L11:
        th = move-exception;
        throw th;
    }

    public final void zzl(Runnable r4) {
        zzw();
        Preconditions.checkNotNull(r4);
        zzz(new zzhx(this, r4, true, "Task exception on worker thread"));
    }

    public final void zzm(Runnable r4) {
        zzw();
        Preconditions.checkNotNull(r4);
        zzhx r1 = new zzhx(this, r4, false, "Task exception on network thread");
        Object r42 = this.zzg;
        monitor-enter(r42);
        BlockingQueue r0 = this.zzd;     // Catch: Throwable -> L7
        r0.add(r1);     // Catch: Throwable -> L7
        zzhy r12 = this.zzb;     // Catch: Throwable -> L7
        if (r12 != null) goto L9;
        zzhy r13 = new zzhy(this, "Measurement Network", r0);     // Catch: Throwable -> L7
        this.zzb = r13;     // Catch: Throwable -> L7
        r13.setUncaughtExceptionHandler(this.zzf);     // Catch: Throwable -> L7
        this.zzb.start();     // Catch: Throwable -> L7
    L10:
        monitor-exit(r42);     // Catch: Throwable -> L7
        return;
    L9:
        r12.zza();     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ zzhy zzn() {
        return this.zza;
    }

    public final /* synthetic */ void zzo(zzhy r1) {
        this.zza = null;
    }

    public final /* synthetic */ zzhy zzp() {
        return this.zzb;
    }

    public final /* synthetic */ void zzq(zzhy r1) {
        this.zzb = null;
    }

    public final /* synthetic */ Object zzr() {
        return this.zzg;
    }

    public final /* synthetic */ Semaphore zzs() {
        return this.zzh;
    }

    public final /* synthetic */ boolean zzt() {
        return false;
    }
}
