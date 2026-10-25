package com.google.android.gms.internal.play_billing;

import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes5.dex */
public class zzo implements zzcz {
    static final boolean zza = false;
    static final zzd zzb = null;
    private static final Logger zzg = null;
    private static final Object zzh = null;
    volatile Object zzc;
    volatile zzh zzd;
    volatile zzm zze;

    static {
        zza = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        zzg = Logger.getLogger(zzo.class.getName());
        zzd r4 = new zzj(AtomicReferenceFieldUpdater.newUpdater(zzm.class, Thread.class, "zzb"), AtomicReferenceFieldUpdater.newUpdater(zzm.class, zzm.class, "zzc"), AtomicReferenceFieldUpdater.newUpdater(zzo.class, zzm.class, "zze"), AtomicReferenceFieldUpdater.newUpdater(zzo.class, zzh.class, "zzd"), AtomicReferenceFieldUpdater.newUpdater(zzo.class, Object.class, "zzc"));     // Catch: Throwable -> L6
        th = null;
    L5:
        Throwable r10 = th;
        zzb = r4;
        if (r10 == null) goto L11;
        zzg.logp(Level.SEVERE, "com.android.billingclient.util.concurrent.AbstractResolvableFuture", "<clinit>", "SafeAtomicHelper is broken!", r10);
    L11:
        zzh = new Object();
        return;
    L6:
        th = th;
        r4 = new zzl();
        goto L5
    }

    public zzo() {
    }

    public static void zzc(zzo r4) {
    L2:
        zzm r0 = r4.zze;
        zzd r1 = zzb;
        if (r1.zze(r4, r0, zzm.zza) == false) goto L2;
    L4:
        zzh r2 = null;
        if (r0 == null) goto L6;
        Thread r3 = r0.zzb;
        if (r3 == null) goto L17;
        r0.zzb = null;
        LockSupport.unpark(r3);
    L17:
        r0 = r0.zzc;
    L6:
        zzh r02 = r4.zzd;
        if (r1.zzc(r4, r02, zzh.zza) == false) goto L6;
    L8:
        zzh r42 = r2;
        r2 = r02;
        if (r2 == null) goto L11;
        r02 = r2.zzd;
        r2.zzd = r42;
    L11:
        if (r42 == null) goto L13;
        Runnable r03 = r42.zzb;
        zzh r12 = r42.zzd;
        zzf(r03, r42.zzc);
        r42 = r12;
        goto L11
    }

    private final void zze(StringBuilder r4) {
        boolean r1 = false;
    L27:
        V r2 = get();     // Catch: Throwable -> L16 InterruptedException -> L26
    L4:
        if (r1 == false) goto L10;
        Thread.currentThread().interrupt();     // Catch: RuntimeException -> L6 ExecutionException -> L8 CancellationException -> L22
    L10:
        r4.append("SUCCESS, result=[");     // Catch: RuntimeException -> L6 ExecutionException -> L8 CancellationException -> L22
        if (r2 != this) goto L13;
        String r3 = "this future";
    L14:
        r4.append(r3);     // Catch: RuntimeException -> L6 ExecutionException -> L8 CancellationException -> L22
        r4.append("]");     // Catch: RuntimeException -> L6 ExecutionException -> L8 CancellationException -> L22
        return;
    L13:
        r3 = String.valueOf(r2);     // Catch: RuntimeException -> L6 ExecutionException -> L8 CancellationException -> L22
    L26:
        r1 = true;
    L16:
        th = move-exception;
        if (r1 == false) goto L19;
        Thread.currentThread().interrupt();     // Catch: RuntimeException -> L6 ExecutionException -> L8 CancellationException -> L22
    L19:
        throw th;     // Catch: RuntimeException -> L6 ExecutionException -> L8 CancellationException -> L22
    L22:
        r4.append("CANCELLED");
        return;
    L6:
        e = move-exception;
        r4.append("UNKNOWN, cause=[");
        r4.append(e.getClass());
        r4.append(" thrown from get()]");
        return;
    L8:
        e = move-exception;
        r4.append("FAILURE, cause=[");
        r4.append(e.getCause());
        r4.append("]");
    }

    private static void zzf(Runnable r6, Executor r7) {
        r7.execute(r6);     // Catch: RuntimeException -> L4
        return;
    L4:
        e = move-exception;
        zzg.logp(Level.SEVERE, "com.android.billingclient.util.concurrent.AbstractResolvableFuture", "executeListener", "RuntimeException while executing runnable " + String.valueOf(r6) + " with executor " + String.valueOf(r7), e);
    }

    private final void zzg(zzm r5) {
        r5.zzb = null;
    L3:
        zzm r52 = this.zze;
        if (r52 == zzm.zza) goto L18;
        zzm r1 = null;
    L6:
        if (r52 == null) goto L27;
        zzm r2 = r52.zzc;
        if (r52.zzb == null) goto L10;
        r1 = r52;
    L17:
        r52 = r2;
        goto L6
    L10:
        if (r1 == null) goto L15;
        r1.zzc = r2;
        if (r1.zzb != null) goto L17;
    L15:
        if (zzb.zze(this, r52, r2) == true) goto L17;
    L27:
        return;
    }

    private static final Object zzh(Object r2) {
        if ((r2 instanceof zze) == false) goto L5;
        Throwable r22 = ((zze) r2).zzc;
        CancellationException r0 = new CancellationException("Task was cancelled.");
        r0.initCause(r22);
        throw r0;
    L5:
        if ((r2 instanceof zzg) == true) goto L11;
        if (r2 != zzh) goto L14;
        return null;
    L14:
        return r2;
    L11:
        throw new ExecutionException(((zzg) r2).zza);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean r7) {
        Object r0 = this.zzc;
        if (r0 != null) goto L5;
        boolean r3 = true;
    L6:
        if (r3 == true) goto L8;
    L17:
        return false;
    L8:
        if (zza == false) goto L10;
        zze r32 = new zze(r7, new CancellationException("Future.cancel() was called."));
    L14:
        if (zzb.zzd(this, r0, r32) == false) goto L17;
        zzc(this);
        return true;
    L10:
        if (r7 == false) goto L12;
        r32 = zze.zza;
        goto L14
    L12:
        r32 = zze.zzb;
        goto L14
    L5:
        r3 = false;
        goto L6
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        if (Thread.interrupted() == true) goto L32;
        Object r0 = this.zzc;
        if (r0 == null) goto L7;
        boolean r3 = true;
    L8:
        if (r3 == true) goto L10;
        zzm r02 = this.zze;
        zzm r32 = zzm.zza;
        if (r02 == r32) goto L30;
        zzm r4 = new zzm();
    L14:
        zzd r5 = zzb;
        r5.zza(r4, r02);
        if (r5.zze(this, r02, r4) == true) goto L16;
        r02 = this.zze;
        if (r02 != r32) goto L14;
    L16:
        LockSupport.park(this);
        if (Thread.interrupted() == true) goto L25;
        Object r03 = this.zzc;
        if (r03 == null) goto L21;
        boolean r33 = true;
    L22:
        if (r33 == false) goto L16;
        return zzh(r03);
    L21:
        r33 = false;
        goto L22
    L25:
        zzg(r4);
        throw new InterruptedException();
    L30:
        return zzh(this.zzc);
    L10:
        return zzh(r0);
    L7:
        r3 = false;
        goto L8
    L32:
        throw new InterruptedException();
    }

    @Override // java.util.concurrent.Future
    public final Object get(long r20, TimeUnit r22) {
        long r4 = r22.toNanos(r20);
        if (Thread.interrupted() == true) goto L77;
        Object r6 = this.zzc;
        if (r6 == null) goto L7;
        boolean r9 = true;
    L8:
        if (r9 == false) goto L12;
        return zzh(r6);
    L12:
        if (r4 <= 0) goto L14;
        long r11 = System.nanoTime() + r4;
    L16:
        if (r4 < 1000) goto L41;
        zzm r62 = this.zze;
        zzm r15 = zzm.zza;
        if (r62 == r15) goto L39;
        zzm r7 = new zzm();
    L20:
        zzd r8 = zzb;
        r8.zza(r7, r62);
        if (r8.zze(this, r62, r7) == true) goto L22;
        r62 = this.zze;
        if (r62 != r15) goto L20;
    L22:
        LockSupport.parkNanos(this, r4);
        if (Thread.interrupted() == true) goto L34;
        Object r42 = this.zzc;
        if (r42 == null) goto L27;
        boolean r5 = true;
    L28:
        if (r5 == true) goto L30;
        r4 = r11 - System.nanoTime();
        if (r4 >= 1000) goto L22;
        zzg(r7);
        goto L41
    L30:
        return zzh(r42);
    L27:
        r5 = false;
        goto L28
    L34:
        zzg(r7);
        throw new InterruptedException();
    L39:
        return zzh(this.zzc);
    L41:
        if (r4 <= 0) goto L54;
        Object r43 = this.zzc;
        if (r43 == null) goto L45;
        boolean r52 = true;
    L46:
        if (r52 == true) goto L48;
        if (Thread.interrupted() == true) goto L53;
        r4 = r11 - System.nanoTime();
        goto L41
    L53:
        throw new InterruptedException();
    L48:
        return zzh(r43);
    L45:
        r52 = false;
        goto L46
    L54:
        String r63 = toString();
        String r72 = r22.toString();
        Locale r82 = Locale.ROOT;
        String r73 = r72.toLowerCase(r82);
        String r2 = "Waited " + r20 + " " + r22.toString().toLowerCase(r82);
        if ((r4 + 1000) >= 0) goto L71;
        String r23 = r2.concat(" (plus ");
        long r44 = -r4;
        long r112 = r22.convert(r44, TimeUnit.NANOSECONDS);
        long r45 = r44 - r22.toNanos(r112);
        if (r112 != 0) goto L59;
    L60:
        boolean r16 = true;
    L62:
        if (r112 <= 0) goto L67;
        String r24 = r23 + r112 + " " + r73;
        if (r16 == false) goto L66;
        r24 = r24.concat(",");
    L66:
        r23 = r24.concat(" ");
    L67:
        if (r16 == false) goto L69;
        r23 = r23 + r45 + " nanoseconds ";
    L69:
        r2 = r23.concat("delay)");
        goto L71
    L59:
        if (r45 > 1000) goto L60;
        r16 = false;
    L71:
        if (isDone() == true) goto L75;
        zzo$$ExternalSyntheticBUOutline0.m(r2, r63);
        return null;
    L75:
        throw new TimeoutException(r2.concat(" but future completed as timeout expired"));
    L14:
        r11 = 0;
        goto L16
    L7:
        r9 = false;
        goto L8
    L77:
        throw new InterruptedException();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.zzc instanceof zze;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        if (this.zzc == null) goto L5;
        return true;
    L5:
        return false;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder();
        r0.append(super.toString());
        r0.append("[status=");
        if ((this.zzc instanceof zze) == false) goto L6;
        r0.append("CANCELLED");
    L20:
        r0.append("]");
        return r0.toString();
    L6:
        if (isDone() == false) goto L22;
        zze(r0);
        goto L20
    L22:
        String r1 = zza();     // Catch: RuntimeException -> L10
    L12:
        if (r1 == null) goto L17;
        if (r1.isEmpty() == true) goto L17;
        r0.append("PENDING, info=[");
        r0.append(r1);
        r0.append("]");
    L17:
        if (isDone() == false) goto L19;
        zze(r0);
        goto L20
    L19:
        r0.append("PENDING");
    L10:
        e = move-exception;
        r1 = "Exception thrown from implementation: ".concat(String.valueOf(e.getClass()));
        goto L12
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String zza() {
        if ((this instanceof ScheduledFuture) == true) goto L5;
        return null;
    L5:
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    @Override // com.google.android.gms.internal.play_billing.zzcz
    public final void zzb(Runnable r5, Executor r6) {
        r6.getClass();
        zzh r0 = this.zzd;
        zzh r1 = zzh.zza;
        if (r0 == r1) goto L10;
        zzh r2 = new zzh(r5, r6);
    L5:
        r2.zzd = r0;
        if (zzb.zzc(this, r0, r2) == true) goto L7;
        r0 = this.zzd;
        if (r0 != r1) goto L5;
    L7:
        return;
    L10:
        zzf(r5, r6);
    }

    public boolean zzd(Object r3) {
        if (r3 != null) goto L5;
        r3 = zzh;
    L5:
        if (zzb.zzd(this, null, r3) == false) goto L8;
        zzc(this);
        return true;
    L8:
        return false;
    }
}
