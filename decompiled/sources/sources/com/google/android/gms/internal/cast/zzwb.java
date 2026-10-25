package com.google.android.gms.internal.cast;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import com.google.android.gms.internal.cast.zzwa;
import com.google.common.util.concurrent.ListenableFuture;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import sun.misc.Unsafe;

/* loaded from: classes4.dex */
abstract class zzwb<V> extends zzwx implements ListenableFuture<V> {
    static final Object zza = null;
    static final zzwn zzb = null;
    static final boolean zzc = false;
    private static final zza zzd = null;
    volatile zzwa.zzd listenersField;
    volatile Object valueField;
    volatile zze waitersField;

    abstract class zza {
        public /* synthetic */ zza(byte[] r1) {
        }

        public abstract void zza(zze r1, Thread r2);

        public abstract void zzb(zze r1, zze r2);

        public abstract boolean zzc(zzwb r1, zze r2, zze r3);

        public abstract boolean zzd(zzwb r1, zzwa.zzd r2, zzwa.zzd r3);

        public abstract zze zze(zzwb r1, zze r2);

        public abstract zzwa.zzd zzf(zzwb r1, zzwa.zzd r2);

        public abstract boolean zzg(zzwb r1, Object r2, Object r3);
    }

    final class zzb extends zza {
        private static final AtomicReferenceFieldUpdater<zze, Thread> zza = null;
        private static final AtomicReferenceFieldUpdater<zze, zze> zzb = null;
        private static final AtomicReferenceFieldUpdater<? super zzwb<?>, zze> zzc = null;
        private static final AtomicReferenceFieldUpdater<? super zzwb<?>, zzwa.zzd> zzd = null;
        private static final AtomicReferenceFieldUpdater<? super zzwb<?>, Object> zze = null;

        static {
            zza = AtomicReferenceFieldUpdater.newUpdater(zze.class, Thread.class, "thread");
            zzb = AtomicReferenceFieldUpdater.newUpdater(zze.class, zze.class, "next");
            zzc = AtomicReferenceFieldUpdater.newUpdater(zzwb.class, zze.class, "waitersField");
            zzd = AtomicReferenceFieldUpdater.newUpdater(zzwb.class, zzwa.zzd.class, "listenersField");
            zze = AtomicReferenceFieldUpdater.newUpdater(zzwb.class, Object.class, "valueField");
        }

        public /* synthetic */ zzb(byte[] r1) {
            super(null);
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final void zza(zze r1, Thread r2) {
            zza.lazySet(r1, r2);
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final void zzb(zze r1, zze r2) {
            zzb.lazySet(r1, r2);
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final boolean zzc(zzwb r1, zze r2, zze r3) {
            return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(zzc, r1, r2, r3);
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final boolean zzd(zzwb r1, zzwa.zzd r2, zzwa.zzd r3) {
            return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(zzd, r1, r2, r3);
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final zze zze(zzwb r1, zze r2) {
            return zzc.getAndSet(r1, r2);
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final zzwa.zzd zzf(zzwb r1, zzwa.zzd r2) {
            return zzd.getAndSet(r1, r2);
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final boolean zzg(zzwb r1, Object r2, Object r3) {
            return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(zze, r1, r2, r3);
        }
    }

    final class zzc extends zza {
        public /* synthetic */ zzc(byte[] r1) {
            super(null);
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final void zza(zze r1, Thread r2) {
            r1.thread = r2;
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final void zzb(zze r1, zze r2) {
            r1.next = r2;
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final boolean zzc(zzwb r1, zze r2, zze r3) {
            monitor-enter(r1);
        L9:
            th = move-exception;
            throw th;
        L4:
            if (r1.waitersField != r2) goto L11;
            r1.waitersField = r3;     // Catch: Throwable -> L9
            monitor-exit(r1);     // Catch: Throwable -> L9
            return true;
        L11:
            monitor-exit(r1);     // Catch: Throwable -> L9
            return false;
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final boolean zzd(zzwb r1, zzwa.zzd r2, zzwa.zzd r3) {
            monitor-enter(r1);
        L9:
            th = move-exception;
            throw th;
        L4:
            if (r1.listenersField != r2) goto L11;
            r1.listenersField = r3;     // Catch: Throwable -> L9
            monitor-exit(r1);     // Catch: Throwable -> L9
            return true;
        L11:
            monitor-exit(r1);     // Catch: Throwable -> L9
            return false;
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final zze zze(zzwb r1, zze r2) {
            monitor-enter(r1);
            zze r0 = r1.waitersField;     // Catch: Throwable -> L6
            if (r0 == r2) goto L8;
            r1.waitersField = r2;     // Catch: Throwable -> L6
        L8:
            monitor-exit(r1);     // Catch: Throwable -> L6
            return r0;
        L6:
            th = move-exception;
            throw th;
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final zzwa.zzd zzf(zzwb r1, zzwa.zzd r2) {
            monitor-enter(r1);
            zzwa.zzd r0 = r1.listenersField;     // Catch: Throwable -> L6
            if (r0 == r2) goto L8;
            r1.listenersField = r2;     // Catch: Throwable -> L6
        L8:
            monitor-exit(r1);     // Catch: Throwable -> L6
            return r0;
        L6:
            th = move-exception;
            throw th;
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final boolean zzg(zzwb r1, Object r2, Object r3) {
            monitor-enter(r1);
        L9:
            th = move-exception;
            throw th;
        L4:
            if (r1.valueField != r2) goto L11;
            r1.valueField = r3;     // Catch: Throwable -> L9
            monitor-exit(r1);     // Catch: Throwable -> L9
            return true;
        L11:
            monitor-exit(r1);     // Catch: Throwable -> L9
            return false;
        }
    }

    final class zzd extends zza {
        static final Unsafe zza = null;
        static final long zzb = 0;
        static final long zzc = 0;
        static final long zzd = 0;
        static final long zze = 0;
        static final long zzf = 0;

        static {
            Unsafe r1 = Unsafe.getUnsafe();     // Catch: SecurityException -> L5
        L16:
            zzc = r1.objectFieldOffset(zzwb.class.getDeclaredField("waitersField"));     // Catch: NoSuchFieldException -> L8
            zzb = r1.objectFieldOffset(zzwb.class.getDeclaredField("listenersField"));     // Catch: NoSuchFieldException -> L8
            zzd = r1.objectFieldOffset(zzwb.class.getDeclaredField("valueField"));     // Catch: NoSuchFieldException -> L8
            zze = r1.objectFieldOffset(zze.class.getDeclaredField("thread"));     // Catch: NoSuchFieldException -> L8
            zzf = r1.objectFieldOffset(zze.class.getDeclaredField("next"));     // Catch: NoSuchFieldException -> L8
            zza = r1;     // Catch: NoSuchFieldException -> L8
            return;
        L8:
            e = move-exception;
            HttpUrl$$ExternalSyntheticBUOutline0.m(e);
            return;
        L5:
            r1 = (Unsafe) AccessController.doPrivileged(zzwc.zza);     // Catch: PrivilegedActionException -> L11
        L11:
            e = move-exception;
            Make$Map$$ExternalSyntheticBUOutline0.m("Could not initialize intrinsics", e.getCause());
        }

        public /* synthetic */ zzd(byte[] r1) {
            super(null);
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final void zza(zze r3, Thread r4) {
            zza.putObject(r3, zze, r4);
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final void zzb(zze r3, zze r4) {
            zza.putObject(r3, zzf, r4);
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final boolean zzc(zzwb r7, zze r8, zze r9) {
            return zzwb$zzd$$ExternalSyntheticBackportWithForwarding0.m(zza, r7, zzc, r8, r9);
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final boolean zzd(zzwb r7, zzwa.zzd r8, zzwa.zzd r9) {
            return zzwb$zzd$$ExternalSyntheticBackportWithForwarding0.m(zza, r7, zzb, r8, r9);
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final zze zze(zzwb r3, zze r4) {
        L2:
            zze r0 = r3.waitersField;
            if (r4 == r0) goto L7;
            if (zzc(r3, r0, r4) == false) goto L2;
        L7:
            return r0;
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final zzwa.zzd zzf(zzwb r3, zzwa.zzd r4) {
        L2:
            zzwa.zzd r0 = r3.listenersField;
            if (r4 == r0) goto L7;
            if (zzd(r3, r0, r4) == false) goto L2;
        L7:
            return r0;
        }

        @Override // com.google.android.gms.internal.cast.zzwb.zza
        public final boolean zzg(zzwb r7, Object r8, Object r9) {
            return zzwb$zzd$$ExternalSyntheticBackportWithForwarding0.m(zza, r7, zzd, r8, r9);
        }
    }

    final class zze {
        static final zze zza = null;
        volatile zze next;
        volatile Thread thread;

        static {
            zza = new zze(false);
        }

        public zze() {
            zzwb.zzn(this, Thread.currentThread());
        }

        public zze(boolean r1) {
        }
    }

    static {
        zza = new Object();
        zzb = new zzwn(zzwa.class);
        boolean r0 = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));     // Catch: SecurityException -> L5
    L6:
        zzc = r0;
        String r02 = System.getProperty("java.runtime.name", _UrlKt.FRAGMENT_ENCODE_SET);
        if (r02 != null) goto L9;
    L34:
        zza r03 = new zzd(null);     // Catch: Error -> L16 Throwable -> L18
    L12:
        Throwable r6 = null;
        Throwable r12 = null;
    L26:
        zzd = r03;
        if (r6 == null) goto L38;
        zzwn r04 = zzb;
        Logger r7 = r04.zza();
        Level r2 = Level.SEVERE;
        r7.logp(r2, "com.google.common.util.concurrent.AbstractFutureState", "<clinit>", "UnsafeAtomicHelper is broken!", r12);
        r04.zza().logp(r2, "com.google.common.util.concurrent.AbstractFutureState", "<clinit>", "AtomicReferenceFieldUpdaterAtomicHelper is broken!", r6);
        return;
    L38:
        return;
    L18:
        e = move-exception;
        r03 = new zzb(null);     // Catch: Error -> L22 Throwable -> L24
        r6 = null;
        r12 = e;
    L24:
        e = move-exception;
        r6 = e;
        r12 = e;
        r03 = new zzc(null);
        goto L26
    L9:
        if (r02.contains("Android") == true) goto L34;
        r03 = new zzb(null);     // Catch: NoClassDefFoundError -> L13
    L13:
        r03 = new zzc(null);
    L5:
        r0 = false;
        goto L6
    }

    public zzwb() {
    }

    private final void zza(zze r5) {
        r5.thread = null;
    L3:
        zze r52 = this.waitersField;
        if (r52 == zze.zza) goto L18;
        zze r1 = null;
    L6:
        if (r52 == null) goto L27;
        zze r2 = r52.next;
        if (r52.thread == null) goto L10;
        r1 = r52;
    L17:
        r52 = r2;
        goto L6
    L10:
        if (r1 == null) goto L15;
        r1.next = r2;
        if (r1.thread != null) goto L17;
    L15:
        if (zzd.zzc(this, r52, r2) == true) goto L17;
    L27:
        return;
    }

    public static boolean zzj(zzwb r1, Object r2, Object r3) {
        return zzd.zzg(r1, r2, r3);
    }

    public static /* synthetic */ void zzn(zze r1, Thread r2) {
        zzd.zza(r1, r2);
    }

    public final boolean zzh(zzwa.zzd r2, zzwa.zzd r3) {
        return zzd.zzd(this, r2, r3);
    }

    public final zzwa.zzd zzi(zzwa.zzd r2) {
        return zzd.zzf(this, r2);
    }

    public final void zzk() {
        zze r0 = zze.zza;
        zze r2 = zzd.zze(this, r0);
    L3:
        if (r2 == null) goto L8;
        Thread r02 = r2.thread;
        if (r02 == null) goto L7;
        r2.thread = null;
        LockSupport.unpark(r02);
    L7:
        r2 = r2.next;
        goto L3
    }

    public final Object zzl(long r21, TimeUnit r23) {
        long r4 = r23.toNanos(r21);
        if (Thread.interrupted() == true) goto L80;
        Object r6 = this.valueField;
        if (r6 == null) goto L7;
        boolean r9 = true;
    L9:
        if ((r9 & zzwa.zzb(r6)) == false) goto L13;
        return zzwa.zza(r6);
    L13:
        if (r4 <= 0) goto L15;
        long r11 = System.nanoTime() + r4;
    L17:
        if (r4 < 1000) goto L43;
        zze r62 = this.waitersField;
        if (r62 == zze.zza) goto L40;
        zze r15 = new zze();
    L21:
        zza r7 = zzd;
        r7.zzb(r15, r62);
        if (r7.zzc(this, r62, r15) == true) goto L23;
        r62 = this.waitersField;
        if (r62 != zze.zza) goto L21;
    L23:
        LockSupport.parkNanos(this, Math.min(r4, 2147483647999999999L));
        if (Thread.interrupted() == true) goto L36;
        Object r42 = this.valueField;
        if (r42 == null) goto L28;
        boolean r5 = true;
    L30:
        if ((r5 & zzwa.zzb(r42)) == true) goto L32;
        r4 = r11 - System.nanoTime();
        if (r4 >= 1000) goto L23;
        zza(r15);
        goto L43
    L32:
        return zzwa.zza(r42);
    L28:
        r5 = false;
        goto L30
    L36:
        zza(r15);
        throw new InterruptedException();
    L40:
        Object r0 = this.valueField;
        Objects.requireNonNull(r0);
        return zzwa.zza(r0);
    L43:
        if (r4 <= 0) goto L57;
        Object r43 = this.valueField;
        if (r43 == null) goto L47;
        boolean r52 = true;
    L49:
        if ((r52 & zzwa.zzb(r43)) == true) goto L51;
        if (Thread.interrupted() == true) goto L56;
        r4 = r11 - System.nanoTime();
        goto L43
    L56:
        throw new InterruptedException();
    L51:
        return zzwa.zza(r43);
    L47:
        r52 = false;
        goto L49
    L57:
        String r63 = toString();
        String r72 = r23.toString();
        Locale r112 = Locale.ROOT;
        String r73 = r72.toLowerCase(r112);
        String r113 = r23.toString().toLowerCase(r112);
        StringBuilder r8 = new StringBuilder((String.valueOf(r21).length() + 8) + String.valueOf(r113).length());
        r8.append("Waited ");
        r8.append(r21);
        r8.append(" ");
        r8.append(r113);
        String r2 = r8.toString();
        if ((r4 + 1000) >= 0) goto L74;
        String r22 = r2.concat(" (plus ");
        long r44 = -r4;
        long r114 = r23.convert(r44, TimeUnit.NANOSECONDS);
        long r45 = r44 - r23.toNanos(r114);
        if (r114 != 0) goto L62;
    L63:
        boolean r16 = true;
    L65:
        if (r114 <= 0) goto L70;
        StringBuilder r92 = new StringBuilder(((r22.length() + String.valueOf(r114).length()) + 1) + String.valueOf(r73).length());
        r92.append(r22);
        r92.append(r114);
        r92.append(" ");
        r92.append(r73);
        String r24 = r92.toString();
        if (r16 == false) goto L69;
        r24 = r24.concat(",");
    L69:
        r22 = r24.concat(" ");
    L70:
        if (r16 == false) goto L72;
        StringBuilder r3 = new StringBuilder((r22.length() + String.valueOf(r45).length()) + 13);
        r3.append(r22);
        r3.append(r45);
        r3.append(" nanoseconds ");
        r22 = r3.toString();
    L72:
        r2 = r22.concat("delay)");
        goto L74
    L62:
        if (r45 > 1000) goto L63;
        r16 = false;
    L74:
        if (isDone() == true) goto L76;
        StringBuilder r32 = new StringBuilder((r2.length() + 5) + String.valueOf(r63).length());
        r32.append(r2);
        r32.append(" for ");
        r32.append(r63);
        throw new TimeoutException(r32.toString());
    L76:
        throw new TimeoutException(r2.concat(" but future completed as timeout expired"));
    L15:
        r11 = 0;
        goto L17
    L7:
        r9 = false;
        goto L9
    L80:
        throw new InterruptedException();
    }

    public final Object zzm() {
        if (Thread.interrupted() == true) goto L34;
        Object r0 = this.valueField;
        if (r0 == null) goto L7;
        boolean r3 = true;
    L9:
        if ((r3 & zzwa.zzb(r0)) == true) goto L11;
        zze r02 = this.waitersField;
        if (r02 == zze.zza) goto L31;
        zze r32 = new zze();
    L15:
        zza r4 = zzd;
        r4.zzb(r32, r02);
        if (r4.zzc(this, r02, r32) == true) goto L17;
        r02 = this.waitersField;
        if (r02 != zze.zza) goto L15;
    L17:
        LockSupport.park(this);
        if (Thread.interrupted() == true) goto L27;
        Object r03 = this.valueField;
        if (r03 == null) goto L22;
        boolean r42 = true;
    L24:
        if ((r42 & zzwa.zzb(r03)) == false) goto L17;
        return zzwa.zza(r03);
    L22:
        r42 = false;
        goto L24
    L27:
        zza(r32);
        throw new InterruptedException();
    L31:
        Object r6 = this.valueField;
        Objects.requireNonNull(r6);
        return zzwa.zza(r6);
    L11:
        return zzwa.zza(r0);
    L7:
        r3 = false;
        goto L9
    L34:
        throw new InterruptedException();
    }
}
