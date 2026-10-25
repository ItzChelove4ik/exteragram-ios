package com.google.android.gms.internal.play_billing;

import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* loaded from: classes5.dex */
public abstract class zzcj<V> extends zzck<V> {

    final class zza {
        static final zza zza = null;
        static final zza zzb = null;
        final boolean zzc;
        final Throwable zzd;

        static {
            if (zzck.zzc == false) goto L6;
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

    final class zzb<V> implements Runnable {
        final zzcj<V> zza;
        final zzcz<? extends V> zzb;

        public zzb(zzcj r1, zzcz r2) {
            this.zza = r1;
            this.zzb = r2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.zza.valueField != this) goto L10;
            zzcz<? extends V> r0 = this.zzb;
            if (zzck.zzq(this.zza, this, zzcj.zza(r0)) == false) goto L9;
            zzcj.zzf(this.zza, false);
            return;
        L9:
            return;
        }
    }

    final class zzc {
        static final zzc zza = null;
        static final zzc zzb = null;
        final Throwable zzc;

        /* renamed from: com.google.android.gms.internal.play_billing.zzcj$zzc$1, reason: invalid class name */
        public class AnonymousClass1 extends Throwable {
            public AnonymousClass1(String r1) {
                super("Failure occurred while trying to finish a future.");
            }

            @Override // java.lang.Throwable
            public final Throwable fillInStackTrace() {
                return this;
            }
        }

        /* renamed from: com.google.android.gms.internal.play_billing.zzcj$zzc$2, reason: invalid class name */
        public class AnonymousClass2 extends Throwable {
            public AnonymousClass2(String r1) {
                super("Failure.exception is unexpectedly null.");
            }

            @Override // java.lang.Throwable
            public final Throwable fillInStackTrace() {
                return this;
            }
        }

        static {
            zza = new zzc(new AnonymousClass1("Failure occurred while trying to finish a future."));
            zzb = new zzc(new AnonymousClass2("Failure.exception is unexpectedly null."));
        }

        public zzc(Throwable r1) {
            r1.getClass();
            this.zzc = r1;
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

    interface zze<V> extends zzcz<V> {
    }

    public zzcj() {
    }

    public static /* bridge */ /* synthetic */ Object zza(zzcz r0) {
        return zzr(r0);
    }

    public static Object zzc(Object r4) {
        if ((r4 instanceof zza) == false) goto L5;
        Throwable r42 = ((zza) r4).zzd;
        CancellationException r0 = new CancellationException("Task was cancelled.");
        r0.initCause(r42);
        throw r0;
    L5:
        if ((r4 instanceof zzc) == false) goto L13;
        Throwable r43 = ((zzc) r4).zzc;
        if (r43 != null) goto L11;
        zzck.zzb.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "getDoneValue", "Failure.exception is unexpectedly null.");
        throw new ExecutionException(zzc.zzb.zzc);
    L11:
        throw new ExecutionException(r43);
    L13:
        if (r4 != zzck.zza) goto L18;
        return null;
    L18:
        return r4;
    }

    public static /* bridge */ /* synthetic */ void zzf(zzcj r0, boolean r1) {
        zzu(r0, false);
    }

    public static boolean zzh(Object r0) {
        if ((r0 instanceof zzb) == true) goto L6;
        return true;
    L6:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Object zzr(zzcz r6) {
        if ((r6 instanceof zze) == false) goto L15;
        Object r62 = ((zzcj) r6).valueField;
        if ((r62 instanceof zza) == false) goto L12;
        zza r0 = (zza) r62;
        if (r0.zzc == false) goto L12;
        Throwable r63 = r0.zzd;
        if (r63 == null) goto L11;
        r62 = new zza(false, r63);
        goto L12
    L11:
        r62 = zza.zzb;
    L12:
        Objects.requireNonNull(r62);
        return r62;
    L15:
        if ((r6 instanceof zzdf) == false) goto L21;
        Throwable r1 = ((zzdf) r6).zze();
        if (r1 == null) goto L21;
        return new zzc(r1);
    L21:
        boolean r12 = r6.isCancelled();
        if (((!zzck.zzc) & r12) == false) goto L50;
        zza r64 = zza.zzb;
        Objects.requireNonNull(r64);
        return r64;
    L50:
        Object r3 = zzs(r6);     // Catch: CancellationException -> L29 ExecutionException -> L31 Throwable -> L37
        if (r12 == true) goto L28;
        if (r3 == null) goto L35;
        return r3;
    L35:
        return zzck.zza;
    L28:
        return new zza(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: ".concat(String.valueOf(r6))));
    L37:
        e = move-exception;
        return new zzc(e);
    L29:
        e = move-exception;
        if (r12 == true) goto L44;
        return new zzc(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: ".concat(String.valueOf(r6)), e));
    L44:
        return new zza(false, e);
    L31:
        e = move-exception;
        if (r12 == false) goto L49;
        return new zza(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: ".concat(String.valueOf(r6)), e));
    L49:
        return new zzc(e.getCause());
    }

    private static Object zzs(Future r1) {
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

    private final void zzt(StringBuilder r4) {
        Object r1 = zzs(this);     // Catch: Exception -> L6 ExecutionException -> L8 CancellationException -> L17
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

    private static void zzu(zzcj r3, boolean r4) {
        zzd r42 = null;
    L3:
        r3.zzo();
        r3.zzg();
        zzd r2 = r42;
        zzd r43 = r3.zzk(zzd.zza);
        zzd r32 = r2;
    L4:
        if (r43 == null) goto L6;
        zzd r0 = r43.next;
        r43.next = r32;
        r32 = r43;
        r43 = r0;
    L6:
        if (r32 == null) goto L16;
        Runnable r44 = r32.zzb;
        zzd r02 = r32.next;
        Objects.requireNonNull(r44);
        Runnable r45 = r44;
        if ((r45 instanceof zzb) == false) goto L14;
        zzb r46 = (zzb) r45;
        r3 = r46.zza;
        if (r3.valueField != r46) goto L15;
        if (zzck.zzq(r3, r46, zzr(r46.zzb)) == false) goto L15;
        r42 = r02;
    L15:
        r32 = r02;
        goto L6
    L14:
        Executor r33 = r32.zzc;
        Objects.requireNonNull(r33);
        zzv(r45, r33);
        goto L15
    }

    private static void zzv(Runnable r6, Executor r7) {
        r7.execute(r6);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        zzck.zzb.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "executeListener", "RuntimeException while executing runnable " + String.valueOf(r6) + " with executor " + String.valueOf(r7), e);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean r7) {
        Object r0 = this.valueField;
        boolean r1 = r0 instanceof zzb;
        if (r0 != null) goto L5;
        boolean r4 = true;
    L7:
        if ((r1 | r4) == true) goto L9;
        return false;
    L9:
        if (zzck.zzc == false) goto L11;
        zza r12 = new zza(r7, new CancellationException("Future.cancel() was called."));
    L15:
        boolean r42 = false;
    L17:
        if (zzck.zzq(this, r0, r12) == true) goto L18;
        r0 = this.valueField;
        if (zzh(r0) == false) goto L17;
        return r42;
    L18:
        zzu(this, r7);
        if ((r0 instanceof zzb) == false) goto L31;
        zzcz<? extends V> r6 = ((zzb) r0).zzb;
        if ((r6 instanceof zze) == false) goto L30;
        zzcj<V> this = (zzcj) r6;
        r0 = this.valueField;
        if (r0 != null) goto L25;
        boolean r43 = true;
    L27:
        if ((r43 | (r0 instanceof zzb)) == false) goto L29;
        r42 = true;
        goto L17
    L29:
        return true;
    L25:
        r43 = false;
        goto L27
    L30:
        r6.cancel(r7);
    L31:
        return true;
    L11:
        if (r7 == false) goto L13;
        r12 = zza.zza;
    L14:
        Objects.requireNonNull(r12);
        goto L15
    L13:
        r12 = zza.zzb;
        goto L14
    L5:
        r4 = false;
        goto L7
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return zzl();
    }

    @Override // java.util.concurrent.Future
    public final Object get(long r1, TimeUnit r3) {
        return zzm(r1, r3);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.valueField instanceof zza;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        Object r1 = this.valueField;
        boolean r0 = zzh(r1);
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
    L35:
        r0.append("]");
        return r0.toString();
    L10:
        if (isDone() == false) goto L12;
        zzt(r0);
        goto L35
    L12:
        int r1 = r0.length();
        r0.append("PENDING");
        Object r3 = this.valueField;
        if ((r3 instanceof zzb) == false) goto L37;
        r0.append(", setFuture=[");
        zzcz<? extends V> r32 = ((zzb) r3).zzb;
        if (r32 != this) goto L19;
        r0.append("this future");     // Catch: Throwable -> L17
    L39:
    L22:
        r0.append("]");
    L33:
        if (isDone() == false) goto L35;
        r0.delete(r1, r0.length());
        zzt(r0);
        goto L35
    L19:
        r0.append(r32);     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        zzda.zza(th);
        r0.append("Exception thrown from implementation: ");
        r0.append(th.getClass());
        goto L22
    L37:
        String r33 = zzd();     // Catch: Throwable -> L28
        if (r33 == null) goto L27;
        if (r33.isEmpty() == true) goto L27;
    L30:
        if (r33 == null) goto L33;
        r0.append(", info=[");
        r0.append(r33);
        r0.append("]");
    L27:
        r33 = null;
    L28:
        th = move-exception;
        zzda.zza(th);
        r33 = "Exception thrown from implementation: ".concat(String.valueOf(th.getClass()));
        goto L30
    L5:
        r0.append(getClass().getName());
        goto L6
    }

    @Override // com.google.android.gms.internal.play_billing.zzcz
    public final void zzb(Runnable r4, Executor r5) {
        zzbg.zzc(r5, "Executor was null.");
        if (isDone() == true) goto L13;
        zzd r0 = this.listenersField;
        if (r0 == zzd.zza) goto L13;
        zzd r1 = new zzd(r4, r5);
    L7:
        r1.next = r0;
        if (zzp(r0, r1) == true) goto L12;
        r0 = this.listenersField;
        if (r0 != zzd.zza) goto L7;
    L12:
        return;
    L13:
        zzv(r4, r5);
    }

    public abstract String zzd();

    @Override // com.google.android.gms.internal.play_billing.zzdf
    public final Throwable zze() {
        if ((this instanceof zze) == false) goto L8;
        Object r1 = this.valueField;
        if ((r1 instanceof zzc) == true) goto L7;
        return null;
    L7:
        return ((zzc) r1).zzc;
    L8:
        return null;
    }

    public abstract void zzg();

    public final boolean zzi(Throwable r2) {
        if (zzck.zzq(this, null, new zzc(r2)) == false) goto L6;
        zzu(this, false);
        return true;
    L6:
        return false;
    }

    public final boolean zzj(zzcz r5) {
        Object r0 = this.valueField;
        if (r0 != null) goto L23;
        if (r5.isDone() == true) goto L7;
        zzb r02 = new zzb(this, r5);
        if (zzck.zzq(this, null, r02) == true) goto L28;
        r0 = this.valueField;
        goto L23
    L28:
        r5.zzb(r02, zzcp.zza);     // Catch: Throwable -> L15
    L20:
        return true;
    L15:
        th = move-exception;
        zzc r1 = new zzc(th);     // Catch: Throwable -> L18
    L19:
        zzck.zzq(this, r02, r1);
    L18:
        r1 = zzc.zza;
        goto L19
    L7:
        if (zzck.zzq(this, null, zzr(r5)) == false) goto L10;
        zzu(this, false);
        return true;
    L10:
        return false;
    L23:
        if ((r0 instanceof zza) == false) goto L25;
        r5.cancel(((zza) r0).zzc);
    L25:
        return false;
    }
}
