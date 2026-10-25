package com.google.android.gms.measurement.internal;

import android.os.Process;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/* loaded from: classes5.dex */
final class zzhy extends Thread {
    final /* synthetic */ zzhz zza;
    private final Object zzb;
    private final BlockingQueue zzc;
    private boolean zzd;

    public zzhy(zzhz r1, String r2, BlockingQueue r3) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzd = false;
        Preconditions.checkNotNull(r2);
        Preconditions.checkNotNull(r3);
        this.zzb = new Object();
        this.zzc = r3;
        setName(r2);
    }

    private final void zzb() {
        zzhz r0 = this.zza;
        Object r1 = r0.zzr();
        monitor-enter(r1);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.zzd == true) goto L16;
        r0.zzs().release();     // Catch: Throwable -> L9
        r0.zzr().notifyAll();     // Catch: Throwable -> L9
        if (this != r0.zzn()) goto L12;
        r0.zzo(null);     // Catch: Throwable -> L9
    L15:
        this.zzd = true;     // Catch: Throwable -> L9
        goto L16
    L12:
        if (this != r0.zzp()) goto L14;
        r0.zzq(null);     // Catch: Throwable -> L9
        goto L15
    L14:
        r0.zzu.zzaW().zzb().zza("Current scheduler thread is neither worker nor network");     // Catch: Throwable -> L9
    L16:
        monitor-exit(r1);     // Catch: Throwable -> L9
    }

    private final void zzc(InterruptedException r3) {
        this.zza.zzu.zzaW().zze().zzb(String.valueOf(getName()).concat(" was interrupted"), r3);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        boolean r0 = false;
    L4:
        if (r0 == false) goto L51;
        int r02 = Process.getThreadPriority(Process.myTid());     // Catch: Throwable -> L17
    L10:
        BlockingQueue r2 = this.zzc;     // Catch: Throwable -> L17
        zzhx r3 = (zzhx) r2.poll();     // Catch: Throwable -> L17
        if (r3 != null) goto L13;
        Object r32 = this.zzb;     // Catch: Throwable -> L17
        monitor-enter(r32);     // Catch: Throwable -> L17
        if (r2.peek() != null) goto L31;
        this.zza.zzt();     // Catch: Throwable -> L27
        r32.wait(NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);     // Catch: Throwable -> L27 InterruptedException -> L29
    L29:
        e = move-exception;
        zzc(e);     // Catch: Throwable -> L27
    L31:
        monitor-exit(r32);     // Catch: Throwable -> L27
        Object r22 = this.zza.zzr();     // Catch: Throwable -> L17
        monitor-enter(r22);     // Catch: Throwable -> L17
    L40:
        th = move-exception;
        throw th;     // Catch: Throwable -> L17
    L35:
        if (this.zzc.peek() == null) goto L36;
        monitor-exit(r22);     // Catch: Throwable -> L40
        goto L10
    L36:
        zzb();     // Catch: Throwable -> L40
        monitor-exit(r22);     // Catch: Throwable -> L40
        zzb();
        return;
    L27:
        th = move-exception;
        throw th;     // Catch: Throwable -> L17
    L13:
        if (true == r3.zza) goto L15;
        int r23 = 10;
    L16:
        Process.setThreadPriority(r23);     // Catch: Throwable -> L17
        r3.run();     // Catch: Throwable -> L17
        goto L10
    L15:
        r23 = r02;
    L17:
        th = move-exception;
        zzb();
        throw th;
    L51:
        this.zza.zzs().acquire();     // Catch: InterruptedException -> L7
        r0 = true;
    L7:
        e = move-exception;
        zzc(e);
        goto L4
    }

    public final void zza() {
        Object r1 = this.zzb;
        monitor-enter(r1);
        r1.notifyAll();     // Catch: Throwable -> L7
        monitor-exit(r1);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
