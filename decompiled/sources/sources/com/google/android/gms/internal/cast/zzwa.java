package com.google.android.gms.internal.cast;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes4.dex */
public abstract class zzwa<V> extends zzwb<V> {

    final class zza {
        static final zza zza = null;
        static final zza zzb = null;
        final boolean zzc;
        final Throwable zzd;

        static {
            if (zzwb.zzc == false) goto L6;
            zzb = null;
            zza = null;
            return;
        L6:
            zzb = new zza(false, null);
            zza = new zza(true, null);
        }

        public zza(boolean r1, Throwable r2) {
            this.zzc = r1;
            this.zzd = r2;
        }
    }

    final class zzc {
        static final zzc zza = null;
        final Throwable zzb;

        /* renamed from: com.google.android.gms.internal.cast.zzwa$zzc$1, reason: invalid class name */
        public class AnonymousClass1 extends Throwable {
            public AnonymousClass1(String r1) {
                super("Failure occurred while trying to finish a future.");
            }

            @Override // java.lang.Throwable
            public final Throwable fillInStackTrace() {
                return this;
            }
        }

        static {
            zza = new zzc(new AnonymousClass1("Failure occurred while trying to finish a future."));
        }

        public zzc(Throwable r1) {
            r1.getClass();
            this.zzb = r1;
        }
    }

    final class zzd {
        static final zzd zza = null;
        zzd next;
        final Runnable zzb;
        final Executor zzc;

        static {
            zza = new zzd();
        }

        public zzd() {
            this.zzb = null;
            this.zzc = null;
        }

        public zzd(Runnable r1, Executor r2) {
            this.zzb = r1;
            this.zzc = r2;
        }
    }

    abstract class zzf<V> extends zzwa<V> implements ListenableFuture {
        public zzf() {
        }
    }

    public zzwa() {
    }

    public static Object zza(Object r2) {
        if ((r2 instanceof zza) == false) goto L5;
        Throwable r22 = ((zza) r2).zzd;
        CancellationException r0 = new CancellationException("Task was cancelled.");
        r0.initCause(r22);
        throw r0;
    L5:
        if ((r2 instanceof zzc) == true) goto L11;
        if (r2 != zzwb.zza) goto L14;
        return null;
    L14:
        return r2;
    L11:
        throw new ExecutionException(((zzc) r2).zzb);
    }

    public static boolean zzb(Object r0) {
        return true;
    }

    private static Object zzp(Future r1) {
        boolean r0 = false;
    L13:
        Object r12 = r1.get();     // Catch: Throwable -> L7 InterruptedException -> L12
    L4:
        if (r0 == false) goto L6;
        Thread.currentThread().interrupt();
    L6:
        return r12;
    L12:
        r0 = true;
    L7:
        th = move-exception;
        if (r0 == false) goto L11;
        Thread.currentThread().interrupt();
    L11:
        throw th;
    }

    private static void zzq(zzwa r1, boolean r2) {
        r1.zzk();
        r1.zze();
        zzd r12 = r1.zzi(zzd.zza);
        zzd r22 = null;
    L3:
        if (r12 == null) goto L5;
        zzd r0 = r12.next;
        r12.next = r22;
        r22 = r12;
        r12 = r0;
    L5:
        if (r22 == null) goto L7;
        Runnable r13 = r22.zzb;
        zzd r02 = r22.next;
        Objects.requireNonNull(r13);
        Executor r23 = r22.zzc;
        Objects.requireNonNull(r23);
        zzs(r13, r23);
        r22 = r02;
        goto L5
    }

    private final void zzr(StringBuilder r4) {
        Object r1 = zzp(this);     // Catch: Exception -> L6 ExecutionException -> L8 CancellationException -> L17
        r4.append("SUCCESS, result=[");     // Catch: Exception -> L6 ExecutionException -> L8 CancellationException -> L17
        if (r1 != null) goto L10;
        r4.append("null");     // Catch: Exception -> L6 ExecutionException -> L8 CancellationException -> L17
    L13:
        r4.append("]");     // Catch: Exception -> L6 ExecutionException -> L8 CancellationException -> L17
        return;
    L10:
        if (r1 != this) goto L12;
        r4.append("this future");     // Catch: Exception -> L6 ExecutionException -> L8 CancellationException -> L17
        goto L13
    L12:
        r4.append(r1.getClass().getName());     // Catch: Exception -> L6 ExecutionException -> L8 CancellationException -> L17
        r4.append("@");     // Catch: Exception -> L6 ExecutionException -> L8 CancellationException -> L17
        r4.append(Integer.toHexString(System.identityHashCode(r1)));     // Catch: Exception -> L6 ExecutionException -> L8 CancellationException -> L17
    L17:
        r4.append("CANCELLED");
        return;
    L8:
        e = move-exception;
        r4.append("FAILURE, cause=[");
        r4.append(e.getCause());
        r4.append("]");
        return;
    L6:
        e = move-exception;
        r4.append("UNKNOWN, cause=[");
        r4.append(e.getClass());
        r4.append(" thrown from get()]");
    }

    private static void zzs(Runnable r6, Executor r7) {
        r7.execute(r6);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        Logger r0 = zzwb.zzb.zza();
        Level r1 = Level.SEVERE;
        String r62 = String.valueOf(r6);
        String r72 = String.valueOf(r7);
        StringBuilder r4 = new StringBuilder((r62.length() + 57) + r72.length());
        r4.append("RuntimeException while executing runnable ");
        r4.append(r62);
        r4.append(" with executor ");
        r4.append(r72);
        r0.logp(r1, "com.google.common.util.concurrent.AbstractFuture", "executeListener", r4.toString(), e);
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable r4, Executor r5) {
        zzhd.zza(r4, "Runnable was null.");
        zzhd.zza(r5, "Executor was null.");
        if (isDone() == true) goto L13;
        zzd r0 = this.listenersField;
        if (r0 == zzd.zza) goto L13;
        zzd r1 = new zzd(r4, r5);
    L7:
        r1.next = r0;
        if (zzh(r0, r1) == true) goto L12;
        r0 = this.listenersField;
        if (r0 != zzd.zza) goto L7;
    L12:
        return;
    L13:
        zzs(r4, r5);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean r7) {
        Object r0 = this.valueField;
        if (r0 != null) goto L5;
        boolean r3 = true;
    L6:
        if (r3 == true) goto L8;
    L20:
        return false;
    L8:
        if (zzwb.zzc == false) goto L10;
        zza r32 = new zza(r7, new CancellationException("Future.cancel() was called."));
    L15:
        if (zzwb.zzj(this, r0, r32) == true) goto L16;
        r0 = this.valueField;
        if (zzb(r0) == false) goto L15;
    L16:
        zzq(this, r7);
        return true;
    L10:
        if (r7 == false) goto L12;
        r32 = zza.zza;
    L13:
        Objects.requireNonNull(r32);
        goto L15
    L12:
        r32 = zza.zzb;
        goto L13
    L5:
        r3 = false;
        goto L6
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return zzm();
    }

    @Override // java.util.concurrent.Future
    public final Object get(long r1, TimeUnit r3) {
        return zzl(r1, r3);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.valueField instanceof zza;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        Object r1 = this.valueField;
        boolean r0 = zzb(r1);
        if (r1 == null) goto L5;
        boolean r12 = true;
    L7:
        return r12 & r0;
    L5:
        r12 = false;
        goto L7
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder();
        if (getClass().getName().startsWith("com.google.common.util.concurrent.") == false) goto L5;
        r0.append(getClass().getSimpleName());
    L6:
        r0.append('@');
        r0.append(Integer.toHexString(System.identityHashCode(this)));
        r0.append("[status=");
        if ((this.valueField instanceof zza) == false) goto L10;
        r0.append("CANCELLED");
    L25:
        r0.append("]");
        return r0.toString();
    L10:
        if (isDone() == false) goto L12;
        zzr(r0);
        goto L25
    L12:
        int r1 = r0.length();
        r0.append("PENDING");
        String r3 = zzg();     // Catch: Throwable -> L18
        if (r3 == null) goto L17;
        if (r3.isEmpty() == true) goto L17;
    L20:
        if (r3 == null) goto L23;
        r0.append(", info=[");
        r0.append(r3);
        r0.append("]");
    L23:
        if (isDone() == false) goto L25;
        r0.delete(r1, r0.length());
        zzr(r0);
    L17:
        r3 = null;
    L18:
        th = move-exception;
        zzwu.zza(th);
        r3 = "Exception thrown from implementation: ".concat(String.valueOf(th.getClass()));
        goto L20
    L5:
        r0.append(getClass().getName());
        goto L6
    }

    public final boolean zzc(Object r2) {
        if (r2 != null) goto L5;
        r2 = zzwb.zza;
    L5:
        if (zzwb.zzj(this, null, r2) == false) goto L8;
        zzq(this, false);
        return true;
    L8:
        return false;
    }

    public final boolean zzd(Throwable r2) {
        if (zzwb.zzj(this, null, new zzc(r2)) == false) goto L6;
        zzq(this, false);
        return true;
    L6:
        return false;
    }

    public void zze() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String zzg() {
        if ((this instanceof ScheduledFuture) == false) goto L6;
        long r0 = ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS);
        StringBuilder r2 = new StringBuilder(String.valueOf(r0).length() + 21);
        r2.append("remaining delay=[");
        r2.append(r0);
        r2.append(" ms]");
        return r2.toString();
    L6:
        return null;
    }
}
