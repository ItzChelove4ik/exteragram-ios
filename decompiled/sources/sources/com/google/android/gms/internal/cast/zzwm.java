package com.google.android.gms.internal.cast;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/* loaded from: classes4.dex */
abstract class zzwm extends AtomicReference implements Runnable {
    private static final Runnable zza = null;
    private static final Runnable zzb = null;

    static {
        zza = new zzwl(null);
        zzb = new zzwl(null);
    }

    public zzwm() {
    }

    private final void zzg(Thread r8) {
        Runnable r0 = (Runnable) get();
        zzwk r1 = null;
        boolean r3 = false;
        int r4 = 0;
    L4:
        if ((r0 instanceof zzwk) == false) goto L6;
        r1 = (zzwk) r0;
    L12:
        r4 = r4 + 1;
        if (r4 <= 1000) goto L24;
        Runnable r6 = zzb;
        if (r0 == r6) goto L19;
        if (compareAndSet(r0, r6) == true) goto L19;
    L25:
        r0 = (Runnable) get();
    L19:
        if (Thread.interrupted() == true) goto L21;
        if (r3 == true) goto L21;
        r3 = false;
    L23:
        LockSupport.park(r1);
    L21:
        r3 = true;
        goto L23
    L24:
        Thread.yield();
        goto L25
    L6:
        if (r0 == zzb) goto L12;
        if (r3 == false) goto L30;
        r8.interrupt();
        return;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Thread r0 = Thread.currentThread();
        Object r1 = null;
        if (compareAndSet(null, r0) == false) goto L38;
        boolean r2 = zza();
        if (r2 == true) goto L28;
        r1 = zzb();     // Catch: Throwable -> L9
    L9:
        th = move-exception;
    L14:
        th = move-exception;
        if (compareAndSet(r0, zza) == true) goto L25;
        zzg(r0);
    L25:
        zzc(null);
        throw th;
    L11:
        if ((th instanceof InterruptedException) == false) goto L17;
        Thread.currentThread().interrupt();     // Catch: Throwable -> L14
    L17:
        if (compareAndSet(r0, zza) == true) goto L19;
        zzg(r0);
    L19:
        zzd(th);
        return;
    L28:
        if (compareAndSet(r0, zza) == true) goto L30;
        zzg(r0);
    L30:
        if (r2 == true) goto L37;
        zzc(r1);
        return;
    L37:
        return;
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        Runnable r0 = (Runnable) get();
        if (r0 != zza) goto L6;
        String r02 = "running=[DONE]";
    L12:
        String r4 = zzf();
        String r1 = String.valueOf(r4);
        StringBuilder r3 = new StringBuilder((r02.length() + 2) + r1.length());
        r3.append(r02);
        r3.append(", ");
        r3.append(r4);
        return r3.toString();
    L6:
        if ((r0 instanceof zzwk) == false) goto L9;
        r02 = "running=[INTERRUPTED]";
        goto L12
    L9:
        if ((r0 instanceof Thread) == false) goto L11;
        String r03 = ((Thread) r0).getName();
        StringBuilder r2 = new StringBuilder(String.valueOf(r03).length() + 21);
        r2.append("running=[RUNNING ON ");
        r2.append(r03);
        r2.append("]");
        r02 = r2.toString();
        goto L12
    L11:
        r02 = "running=[NOT STARTED YET]";
        goto L12
    }

    public abstract boolean zza();

    public abstract Object zzb();

    public abstract void zzc(Object r1);

    public abstract void zzd(Throwable r1);

    public final void zze() {
        Runnable r0 = (Runnable) get();
        if ((r0 instanceof Thread) == false) goto L17;
        zzwk r1 = new zzwk(this, null);
        r1.zza(Thread.currentThread());
        if (compareAndSet(r0, r1) == false) goto L20;
        Thread r12 = (Thread) r0;     // Catch: Throwable -> L11
        r12.interrupt();     // Catch: Throwable -> L11
        if (((Runnable) getAndSet(zza)) != zzb) goto L21;
        LockSupport.unpark(r12);
        return;
    L21:
        return;
    L11:
        th = move-exception;
        if (((Runnable) getAndSet(zza)) != zzb) goto L16;
        LockSupport.unpark((Thread) r0);
    L16:
        throw th;
    L20:
        return;
    }

    public abstract String zzf();
}
