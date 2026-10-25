package com.google.android.gms.common.wrappers;

import android.content.Context;

/* loaded from: classes.dex */
public class Wrappers {
    private static final Wrappers zzb = null;
    private PackageManagerWrapper zza;

    static {
        zzb = new Wrappers();
    }

    public Wrappers() {
        this.zza = null;
    }

    public static PackageManagerWrapper packageManager(Context r1) {
        return zzb.zza(r1);
    }

    public final synchronized PackageManagerWrapper zza(Context r2) {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (this.zza == null) goto L6;
    L11:
        PackageManagerWrapper r22 = this.zza;     // Catch: Throwable -> L8
        monitor-exit(this);
        return r22;
    L6:
        if (r2.getApplicationContext() == null) goto L10;
        r2 = r2.getApplicationContext();     // Catch: Throwable -> L8
    L10:
        this.zza = new PackageManagerWrapper(r2);     // Catch: Throwable -> L8
        goto L11
    }
}
