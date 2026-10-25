package com.google.android.gms.internal.mlkit_genai_prompt;

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
public abstract class zzoc<V> extends zzod<V> {

    final class zza {
        static final zza zza = null;
        static final zza zzb = null;
        final boolean zzc;
        final Throwable zzd;

        static {
            if (zzod.zzg == false) goto L6;
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
        final zzoc<V> zza;
        final ListenableFuture<? extends V> zzb;

        public zzb(zzoc r1, ListenableFuture r2) {
            this.zza = r1;
            this.zzb = r2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.zza.valueField != this) goto L10;
            ListenableFuture<? extends V> r0 = this.zzb;
            if (zzod.zzq(this.zza, this, zzoc.zzm(r0)) == false) goto L9;
            zzoc.zzn(this.zza, false);
            return;
        L9:
            return;
        }
    }

    final class zzc {
        static final zzc zza = null;
        final Throwable zzb;

        /* renamed from: com.google.android.gms.internal.mlkit_genai_prompt.zzoc$zzc$1, reason: invalid class name */
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

    interface zze<V> extends ListenableFuture<V> {
    }

    abstract class zzf<V> extends zzoc<V> implements zze<V> {
        public zzf() {
        }
    }

    public zzoc() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Object zza(ListenableFuture r8) {
        if ((r8 instanceof zze) == false) goto L15;
        Object r82 = ((zzoc) r8).valueField;
        if ((r82 instanceof zza) == false) goto L12;
        zza r0 = (zza) r82;
        if (r0.zzc == false) goto L12;
        Throwable r83 = r0.zzd;
        if (r83 == null) goto L11;
        r82 = new zza(false, r83);
        goto L12
    L11:
        r82 = zza.zzb;
    L12:
        Objects.requireNonNull(r82);
        return r82;
    L15:
        if ((r8 instanceof zzpn) == false) goto L21;
        Throwable r02 = ((zzpn) r8).zzk();
        if (r02 == null) goto L21;
        return new zzc(r02);
    L21:
        boolean r03 = r8.isCancelled();
        if (((!zzod.zzg) & r03) == false) goto L48;
        zza r84 = zza.zzb;
        Objects.requireNonNull(r84);
        return r84;
    L48:
        Object r3 = zzb(r8);     // Catch: CancellationException -> L29 ExecutionException -> L31 Throwable -> L35
        if (r03 == false) goto L34;
        String r5 = String.valueOf(r8);     // Catch: CancellationException -> L29 ExecutionException -> L31 Throwable -> L35
        StringBuilder r7 = new StringBuilder(r5.length() + 84);     // Catch: CancellationException -> L29 ExecutionException -> L31 Throwable -> L35
        r7.append("get() did not throw CancellationException, despite reporting isCancelled() == true: ");     // Catch: CancellationException -> L29 ExecutionException -> L31 Throwable -> L35
        r7.append(r5);     // Catch: CancellationException -> L29 ExecutionException -> L31 Throwable -> L35
        return new zza(false, new IllegalArgumentException(r7.toString()));
    L34:
        return zzy(r3);
    L35:
        e = move-exception;
        return new zzc(e);
    L29:
        e = move-exception;
        if (r03 == true) goto L42;
        return new zzc(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: ".concat(String.valueOf(r8)), e));
    L42:
        return new zza(false, e);
    L31:
        e = move-exception;
        if (r03 == false) goto L47;
        return new zza(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: ".concat(String.valueOf(r8)), e));
    L47:
        return new zzc(e.getCause());
    }

    private static Object zzb(Future r1) {
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

    public static Object zze(Object r2) {
        if ((r2 instanceof zza) == false) goto L5;
        CancellationException r0 = new CancellationException("Task was cancelled.");
        r0.initCause(((zza) r2).zzd);
        throw r0;
    L5:
        if ((r2 instanceof zzc) == true) goto L11;
        if (r2 != zzod.zze) goto L14;
        return null;
    L14:
        return r2;
    L11:
        throw new ExecutionException(((zzc) r2).zzb);
    }

    public static boolean zzf(Object r0) {
        if ((r0 instanceof zzb) == true) goto L6;
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ Object zzm(ListenableFuture r0) {
        return zza(r0);
    }

    public static /* synthetic */ void zzn(zzoc r0, boolean r1) {
        zzv(r0, false);
    }

    private static void zzv(zzoc r3, boolean r4) {
        zzd r42 = null;
    L3:
        r3.zzr();
        r3.zzc();
        zzd r2 = r42;
        zzd r43 = r3.zzp(zzd.zza);
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
        if (zzod.zzq(r3, r46, zza(r46.zzb)) == false) goto L15;
        r42 = r02;
    L15:
        r32 = r02;
        goto L6
    L14:
        Executor r33 = r32.zzc;
        Objects.requireNonNull(r33);
        zzx(r45, r33);
        goto L15
    }

    private final void zzw(StringBuilder r4) {
        Object r1 = zzb(this);     // Catch: Exception -> L6 ExecutionException -> L8 CancellationException -> L17
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

    private static void zzx(Runnable r6, Executor r7) {
        r7.execute(r6);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        Logger r0 = zzod.zzf.zza();
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

    private static Object zzy(Object r0) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        return zzod.zze;
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable r4, Executor r5) {
        zzla.zzd(r4, "Runnable was null.");
        zzla.zzd(r5, "Executor was null.");
        if (isDone() == true) goto L13;
        zzd r0 = this.listenersField;
        if (r0 == zzd.zza) goto L13;
        zzd r1 = new zzd(r4, r5);
    L7:
        r1.next = r0;
        if (zzo(r0, r1) == true) goto L12;
        r0 = this.listenersField;
        if (r0 != zzd.zza) goto L7;
    L12:
        return;
    L13:
        zzx(r4, r5);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean r7) {
        Object r0 = this.valueField;
        boolean r1 = r0 instanceof zzb;
        if (r0 != null) goto L5;
        boolean r4 = true;
    L7:
        if ((r1 | r4) == true) goto L9;
        return false;
    L9:
        if (zzod.zzg == false) goto L11;
        zza r12 = new zza(r7, new CancellationException("Future.cancel() was called."));
    L15:
        boolean r42 = false;
    L17:
        if (zzod.zzq(this, r0, r12) == true) goto L18;
        r0 = this.valueField;
        if (zzf(r0) == false) goto L17;
        return r42;
    L18:
        zzv(this, r7);
        if ((r0 instanceof zzb) == false) goto L31;
        ListenableFuture<? extends V> r6 = ((zzb) r0).zzb;
        if ((r6 instanceof zze) == false) goto L30;
        zzoc<V> this = (zzoc) r6;
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
    public Object get() {
        return zzt();
    }

    @Override // java.util.concurrent.Future
    public Object get(long r1, TimeUnit r3) {
        return zzs(r1, r3);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.valueField instanceof zza;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        Object r1 = this.valueField;
        boolean r0 = zzf(r1);
        if (r1 == null) goto L5;
        boolean r12 = true;
    L7:
        return r12 & r0;
    L5:
        r12 = false;
        goto L7
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        if (getClass().getName().startsWith("com.google.common.util.concurrent.") == false) goto L5;
        r0.append(getClass().getSimpleName());
    L6:
        r0.append('@');
        r0.append(Integer.toHexString(System.identityHashCode(this)));
        r0.append("[status=");
        if (isCancelled() == false) goto L10;
        r0.append("CANCELLED");
    L32:
        r0.append("]");
        return r0.toString();
    L10:
        if (isDone() == false) goto L12;
        zzw(r0);
        goto L32
    L12:
        int r1 = r0.length();
        r0.append("PENDING");
        Object r3 = this.valueField;
        if ((r3 instanceof zzb) == false) goto L34;
        r0.append(", setFuture=[");
        ListenableFuture<? extends V> r32 = ((zzb) r3).zzb;
        if (r32 != this) goto L19;
        r0.append("this future");     // Catch: Throwable -> L17
    L36:
    L22:
        r0.append("]");
    L30:
        if (isDone() == false) goto L32;
        r0.delete(r1, r0.length());
        zzw(r0);
        goto L32
    L19:
        r0.append(r32);     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        zzpj.zzb(th);
        r0.append("Exception thrown from implementation: ");
        r0.append(th.getClass());
        goto L22
    L34:
        String r33 = zzlb.zzb(zzd());     // Catch: Throwable -> L25
    L27:
        if (r33 == null) goto L30;
        r0.append(", info=[");
        r0.append(r33);
        r0.append("]");
    L25:
        th = move-exception;
        zzpj.zzb(th);
        r33 = "Exception thrown from implementation: ".concat(String.valueOf(th.getClass()));
        goto L27
    L5:
        r0.append(getClass().getName());
        goto L6
    }

    public void zzc() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String zzd() {
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

    public final boolean zzg() {
        Object r1 = this.valueField;
        if ((r1 instanceof zza) == true) goto L5;
        return false;
    L5:
        if (((zza) r1).zzc == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean zzh(Object r2) {
        if (zzod.zzq(this, null, zzy(r2)) == false) goto L6;
        zzv(this, false);
        return true;
    L6:
        return false;
    }

    public boolean zzi(Throwable r2) {
        if (zzod.zzq(this, null, new zzc(r2)) == false) goto L6;
        zzv(this, false);
        return true;
    L6:
        return false;
    }

    public final boolean zzj(ListenableFuture r5) {
        r5.getClass();
        Object r0 = this.valueField;
        if (r0 != null) goto L23;
        if (r5.isDone() == true) goto L7;
        zzb r02 = new zzb(this, r5);
        if (zzod.zzq(this, null, r02) == true) goto L26;
        r0 = this.valueField;
        goto L23
    L26:
        r5.addListener(r02, zzok.zza);     // Catch: Throwable -> L15
    L20:
        return true;
    L15:
        th = move-exception;
        zzc r1 = new zzc(th);     // Catch: Throwable -> L18
    L19:
        zzod.zzq(this, r02, r1);
    L18:
        r1 = zzc.zza;
        goto L19
    L7:
        if (zzod.zzq(this, null, zza(r5)) == false) goto L10;
        zzv(this, false);
        return true;
    L10:
        return false;
    L23:
        if ((r0 instanceof zza) == false) goto L25;
        r5.cancel(((zza) r0).zzc);
    L25:
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzpn
    public final Throwable zzk() {
        if ((this instanceof zze) == false) goto L8;
        Object r1 = this.valueField;
        if ((r1 instanceof zzc) == true) goto L7;
        return null;
    L7:
        return ((zzc) r1).zzb;
    L8:
        return null;
    }

    public final void zzl(Future r3) {
        if (r3 == null) goto L4;
        boolean r0 = true;
    L6:
        if ((r0 & isCancelled()) == false) goto L9;
        r3.cancel(zzg());
        return;
    L9:
        return;
    L4:
        r0 = false;
        goto L6
    }
}
