package com.google.android.gms.cloudmessaging;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.List;

/* loaded from: classes.dex */
public final class zzw {
    private final Context zza;
    private int zzb;
    private int zzc;

    public zzw(Context r2) {
        this.zzc = 0;
        this.zza = r2;
    }

    public final synchronized int zza() {
        monitor-enter(this);
        int r0 = this.zzc;     // Catch: Throwable -> L12
        if (r0 == 0) goto L7;
        monitor-exit(this);
        return r0;
    L7:
        Context r02 = this.zza;     // Catch: Throwable -> L12
        PackageManager r1 = r02.getPackageManager();     // Catch: Throwable -> L12
        if (Wrappers.packageManager(r02).checkPermission("com.google.android.c2dm.permission.SEND", "com.google.android.gms") != (-1)) goto L14;
        Log.e("Metadata", "Google Play services missing or without correct permission.");     // Catch: Throwable -> L12
        monitor-exit(this);
        return 0;
    L14:
        int r2 = 1;
        if (PlatformVersion.isAtLeastO() == true) goto L21;
        Intent r03 = new Intent("com.google.android.c2dm.intent.REGISTER");     // Catch: Throwable -> L12
        r03.setPackage("com.google.android.gms");     // Catch: Throwable -> L12
        List<ResolveInfo> r04 = r1.queryIntentServices(r03, 0);     // Catch: Throwable -> L12
        if (r04 == null) goto L21;
        if (r04.isEmpty() == true) goto L21;
    L26:
        this.zzc = r2;     // Catch: Throwable -> L12
        monitor-exit(this);
        return r2;
    L21:
        Intent r05 = new Intent("com.google.iid.TOKEN_REQUEST");     // Catch: Throwable -> L12
        r05.setPackage("com.google.android.gms");     // Catch: Throwable -> L12
        List<ResolveInfo> r06 = r1.queryBroadcastReceivers(r05, 0);     // Catch: Throwable -> L12
        if (r06 != null) goto L24;
    L29:
        Log.w("Metadata", "Failed to resolve IID implementation package, falling back");     // Catch: Throwable -> L12
        if (true != PlatformVersion.isAtLeastO()) goto L33;
        r2 = 2;
    L33:
        this.zzc = r2;     // Catch: Throwable -> L12
        monitor-exit(this);
        return r2;
    L24:
        if (r06.isEmpty() == true) goto L29;
        r2 = 2;
    L12:
        th = move-exception;
        throw th;
    }

    public final synchronized int zzb() {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (this.zzb == 0) goto L21;
    L14:
        int r0 = this.zzb;     // Catch: Throwable -> L8
        monitor-exit(this);
        return r0;
    L21:
        PackageInfo r02 = Wrappers.packageManager(this.zza).getPackageInfo("com.google.android.gms", 0);     // Catch: Throwable -> L8 PackageManager.NameNotFoundException -> L10
    L12:
        if (r02 == null) goto L14;
        this.zzb = r02.versionCode;     // Catch: Throwable -> L8
        goto L14
    L10:
        e = move-exception;
        Log.w("Metadata", "Failed to find package ".concat(e.toString()));     // Catch: Throwable -> L8
        r02 = null;
        goto L12
    }
}
