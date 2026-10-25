package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public abstract class GmsClientSupervisor {
    static HandlerThread zza = null;
    private static final Object zzb = null;
    private static int zzc = 9;
    private static zzq zzd = null;
    private static Executor zze = null;
    private static boolean zzf = false;

    static {
        zzb = new Object();
    }

    public GmsClientSupervisor() {
    }

    public static GmsClientSupervisor getInstance(Context r4) {
        Object r0 = zzb;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (zzd != null) goto L13;
        Context r2 = r4.getApplicationContext();     // Catch: Throwable -> L9
        if (zzf == false) goto L11;
        Looper r42 = getOrStartHandlerThread().getLooper();     // Catch: Throwable -> L9
    L12:
        zzd = new zzq(r2, r42, zze);     // Catch: Throwable -> L9
        goto L13
    L11:
        r42 = r4.getMainLooper();     // Catch: Throwable -> L9
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return zzd;
    }

    public static HandlerThread getOrStartHandlerThread() {
        Object r0 = zzb;
        monitor-enter(r0);
        HandlerThread r1 = zza;     // Catch: Throwable -> L8
        if (r1 == null) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r1;
    L10:
        HandlerThread r12 = new HandlerThread("GoogleApiHandler", zzc);     // Catch: Throwable -> L8
        zza = r12;     // Catch: Throwable -> L8
        r12.start();     // Catch: Throwable -> L8
        HandlerThread r13 = zza;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r13;
    L8:
        th = move-exception;
        throw th;
    }

    public abstract ConnectionResult zza(zzn r1, ServiceConnection r2, String r3, Executor r4);

    public final void zzb(String r2, String r3, int r4, ServiceConnection r5, String r6, boolean r7) {
        zzc(new zzn(r2, r3, 4225, r7), r5, r6);
    }

    public abstract void zzc(zzn r1, ServiceConnection r2, String r3);
}
