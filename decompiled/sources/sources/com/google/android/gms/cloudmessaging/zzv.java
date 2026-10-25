package com.google.android.gms.cloudmessaging;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes.dex */
public final class zzv {
    private static zzv zza;
    private final Context zzb;
    private final ScheduledExecutorService zzc;
    private zzp zzd;
    private int zze;

    public zzv(Context r3, ScheduledExecutorService r4) {
        this.zzd = new zzp(this, null);
        this.zze = 1;
        this.zzc = r4;
        this.zzb = r3.getApplicationContext();
    }

    public static synchronized zzv zza(Context r4) {
        monitor-enter(zzv.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L9;
        com.google.android.gms.internal.cloudmessaging.zzu.zza();     // Catch: Throwable -> L7
        zza = new zzv(r4, Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1, new NamedThreadFactory("MessengerIpcClient"))));     // Catch: Throwable -> L7
    L9:
        zzv r42 = zza;     // Catch: Throwable -> L7
        monitor-exit(zzv.class);
        return r42;
    }

    private final synchronized Task zzf(zzs r3) {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (Log.isLoggable("MessengerIpcClient", 3) == false) goto L9;
        Log.d("MessengerIpcClient", "Queueing ".concat(r3.toString()));     // Catch: Throwable -> L6
    L9:
        if (this.zzd.zza(r3) == true) goto L11;
        zzp r0 = new zzp(this, null);     // Catch: Throwable -> L6
        this.zzd = r0;     // Catch: Throwable -> L6
        r0.zza(r3);     // Catch: Throwable -> L6
    L11:
        Task r32 = r3.zzb.getTask();     // Catch: Throwable -> L6
        monitor-exit(this);
        return r32;
    }

    private final synchronized int zzg() {
        monitor-enter(this);
        int r0 = this.zze;     // Catch: Throwable -> L6
        this.zze = r0 + 1;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final Task zzb(int r3, Bundle r4) {
        return zzf(new zzr(zzg(), r3, r4));
    }

    public final Task zzc(int r3, Bundle r4) {
        return zzf(new zzu(zzg(), r3, r4));
    }

    public final /* synthetic */ Context zzd() {
        return this.zzb;
    }

    public final /* synthetic */ ScheduledExecutorService zze() {
        return this.zzc;
    }
}
