package com.google.android.gms.internal.vision;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.vision.L;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes5.dex */
public abstract class zzt<T> {
    private final Context zza;
    private final Object zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private boolean zzf;
    private boolean zzg;
    private T zzh;

    public zzt(Context r2, String r3, String r4) {
        this.zzb = new Object();
        this.zzf = false;
        this.zzg = false;
        this.zza = r2;
        this.zzc = r3;
        String r22 = String.valueOf(r4);
        if (r22.length() == 0) goto L5;
        String r23 = "com.google.android.gms.vision.dynamite.".concat(r22);
    L6:
        this.zzd = r23;
        this.zze = r4;
        return;
    L5:
        r23 = new String("com.google.android.gms.vision.dynamite.");
        goto L6
    }

    public abstract T zza(DynamiteModule r1, Context r2);

    public abstract void zza();

    public final boolean zzb() {
        if (zzd() == null) goto L6;
        return true;
    L6:
        return false;
    }

    public final void zzc() {
        Object r0 = this.zzb;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.zzh != null) goto L18;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L18:
        zza();     // Catch: Throwable -> L8 RemoteException -> L12
    L14:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L12:
        e = move-exception;
        Log.e(this.zzc, "Could not finalize native handle", e);     // Catch: Throwable -> L8
        goto L14
    }

    @RequiresNonNull({"context", "thickFeatureName", "featureName"})
    public final T zzd() {
        Object r0 = this.zzb;
        monitor-enter(r0);
        T r1 = this.zzh;     // Catch: Throwable -> L8
        if (r1 == null) goto L45;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r1;
    L45:
        DynamiteModule r2 = DynamiteModule.load(this.zza, DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION, this.zzd);     // Catch: Throwable -> L8 DynamiteModule.LoadingException -> L13
    L21:
        if (r2 != null) goto L47;
    L28:
        boolean r22 = this.zzg;     // Catch: Throwable -> L8
        if (r22 == false) goto L31;
    L33:
        if (r22 == true) goto L35;
    L37:
        T r6 = this.zzh;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r6;
    L35:
        if (this.zzh == null) goto L37;
        Log.w(this.zzc, "Native handle is now available.");     // Catch: Throwable -> L8
        goto L37
    L31:
        if (this.zzh != null) goto L33;
        Log.w(this.zzc, "Native handle not yet available. Reverting to no-op handle.");     // Catch: Throwable -> L8
        this.zzg = true;     // Catch: Throwable -> L8
        goto L37
    L47:
        this.zzh = zza(r2, this.zza);     // Catch: Throwable -> L8 RemoteException -> L24 Throwable -> L26
    L26:
        e = move-exception;
        Log.e(this.zzc, "Error creating remote native handle", e);     // Catch: Throwable -> L8
        goto L28
    L13:
        String r23 = String.format("%s.%s", new Object[]{"com.google.android.gms.vision", this.zze});     // Catch: Throwable -> L8
        L.d("Cannot load thick client module, fall back to load optional module %s", new Object[]{r23});     // Catch: Throwable -> L8
        r2 = DynamiteModule.load(this.zza, DynamiteModule.PREFER_REMOTE, r23);     // Catch: Throwable -> L8 DynamiteModule.LoadingException -> L16
    L16:
        e = move-exception;
        L.e(e, "Error loading optional module %s", new Object[]{r23});     // Catch: Throwable -> L8
        if (this.zzf == true) goto L20;
        L.d("Broadcasting download intent for dependency %s", new Object[]{this.zze});     // Catch: Throwable -> L8
        String r24 = this.zze;     // Catch: Throwable -> L8
        Intent r3 = new Intent();     // Catch: Throwable -> L8
        r3.setClassName("com.google.android.gms", "com.google.android.gms.vision.DependencyBroadcastReceiverProxy");     // Catch: Throwable -> L8
        r3.putExtra("com.google.android.gms.vision.DEPENDENCIES", r24);     // Catch: Throwable -> L8
        r3.setAction("com.google.android.gms.vision.DEPENDENCY");     // Catch: Throwable -> L8
        this.zza.sendBroadcast(r3);     // Catch: Throwable -> L8
        this.zzf = true;     // Catch: Throwable -> L8
    L20:
        r2 = null;
    L8:
        th = move-exception;
        throw th;
    }
}
