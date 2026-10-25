package com.google.android.gms.common.internal;

import android.util.Log;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public abstract class zzc {
    private Object zza;
    private boolean zzb;
    final /* synthetic */ BaseGmsClient zzd;

    public zzc(BaseGmsClient r1, Object r2) {
        java.util.Objects.requireNonNull(r1);
        this.zzd = r1;
        this.zza = r2;
        this.zzb = false;
    }

    public abstract void zzc(Object r1);

    public final void zzd() {
        monitor-enter(this);
        Object r2 = this.zza;     // Catch: Throwable -> L7
        if (this.zzb == false) goto L9;
        String r4 = toString();     // Catch: Throwable -> L7
        StringBuilder r6 = new StringBuilder(r4.length() + 47);     // Catch: Throwable -> L7
        r6.append("Callback proxy ");     // Catch: Throwable -> L7
        r6.append(r4);     // Catch: Throwable -> L7
        r6.append(" being reused. This is not safe.");     // Catch: Throwable -> L7
        Log.w("GmsClient", r6.toString());     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);     // Catch: Throwable -> L7
        if (r2 == null) goto L12;
        zzc(r2);
    L12:
        monitor-enter(this);
        this.zzb = true;     // Catch: Throwable -> L18
        monitor-exit(this);     // Catch: Throwable -> L18
        zze();
        return;
    L18:
        th = move-exception;
        throw th;
    L7:
        th = move-exception;
        throw th;
    }

    public final void zze() {
        zzf();
        BaseGmsClient r0 = this.zzd;
        ArrayList r1 = r0.zzj();
        monitor-enter(r1);
        r0.zzj().remove(this);     // Catch: Throwable -> L7
        monitor-exit(r1);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void zzf() {
        monitor-enter(this);
        this.zza = null;     // Catch: Throwable -> L7
        monitor-exit(this);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
