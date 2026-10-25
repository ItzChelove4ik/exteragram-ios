package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
public final class zzcn {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Logger zzb = null;
    private final zzgb zzc;
    private final long zzd;
    private final Handler zze;

    static {
        zzb = new Logger("AnalyticsConsent");
    }

    public zzcn(Context r3, long r4) {
        Api r0 = zzga.zza;
        this.zzc = new zzfu(r3, new zzfz());
        this.zzd = r4;
        this.zze = new zzfk(Looper.getMainLooper());
    }

    public static /* synthetic */ void zzb(TaskCompletionSource r3, Exception r4) {
        zzb.d(r4, "get checkbox consent failed", new Object[0]);
        r3.trySetResult(Boolean.FALSE);
    }

    public static /* synthetic */ void zzc(TaskCompletionSource r3) {
        zzb.d("get checkbox consent timed out", new Object[0]);
        r3.trySetResult(Boolean.FALSE);
    }

    public final synchronized Task zza() {
        monitor-enter(this);
        TaskCompletionSource r0 = new TaskCompletionSource();     // Catch: Throwable -> L6
        this.zzc.zza().addOnSuccessListener(new zzcm(r0)).addOnFailureListener(new zzck(r0));     // Catch: Throwable -> L6
        zzcl r1 = new zzcl(r0);     // Catch: Throwable -> L6
        long r2 = this.zzd * 1000;     // Catch: Throwable -> L6
        this.zze.postDelayed(r1, r2);     // Catch: Throwable -> L6
        Task r02 = r0.getTask();     // Catch: Throwable -> L6
        monitor-exit(this);
        return r02;
    L6:
        th = move-exception;
        throw th;
    }
}
