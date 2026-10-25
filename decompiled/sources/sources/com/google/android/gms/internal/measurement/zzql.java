package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class zzql extends BroadcastReceiver {
    static volatile zzqj zza;
    private static volatile zzqk zzb;

    public zzql() {
    }

    @SuppressLint({"UnprotectedReceiver"})
    @TargetApi(33)
    public static void zza(Context r4, zzqk r5, zzqj r6) {
        if (zzb == null) goto L5;
        return;
    L5:
        monitor-enter(zzql.class);
    L13:
        th = move-exception;
        throw th;
    L7:
        if (zzb == null) goto L9;
    L17:
        monitor-exit(zzql.class);     // Catch: Throwable -> L13
        return;
    L9:
        if (Objects.equals(r4.getPackageName(), "com.google.android.gms") == false) goto L11;
    L16:
        zzb = r5;     // Catch: Throwable -> L13
        zza = r6;     // Catch: Throwable -> L13
        goto L17
    L11:
        if (PlatformVersion.isAtLeastT() == false) goto L15;
        r4.registerReceiver(new zzql(), new IntentFilter("com.google.android.gms.phenotype.UPDATE"), 2);     // Catch: Throwable -> L13
        goto L16
    L15:
        r4.registerReceiver(new zzql(), new IntentFilter("com.google.android.gms.phenotype.UPDATE"));     // Catch: Throwable -> L13
        goto L16
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context r2, Intent r3) {
        String r1 = r3.getStringExtra("com.google.android.gms.phenotype.PACKAGE_NAME");
        if (r1 != null) goto L6;
        return;
    L6:
        if (r1.contains("../") == false) goto L8;
    L16:
        StringBuilder r0 = new StringBuilder(r1.length() + 68);
        r0.append("Got an invalid config package for P/H that includes '..': ");
        r0.append(r1);
        r0.append(". Exiting.");
        Log.w("PhUpdateBroadcastRecv", r0.toString());
        return;
    L8:
        if (r1.contains("/..") == true) goto L16;
        zzqk r22 = zzb;
        if (r22 != null) goto L14;
        Log.w("PhUpdateBroadcastRecv", "No callback registered for P/H UPDATE broadcast. Exiting.");
        return;
    L14:
        r22.zza(r1);
    }
}
