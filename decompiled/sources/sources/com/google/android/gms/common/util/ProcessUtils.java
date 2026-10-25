package com.google.android.gms.common.util;

import android.app.Application;
import android.os.Build;
import android.os.Process;
import android.os.StrictMode;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.common.zzi;
import com.google.android.gms.internal.common.zzj;
import com.google.android.gms.internal.common.zzx;
import com.google.android.gms.internal.common.zzy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* loaded from: classes4.dex */
public abstract class ProcessUtils {
    private static String zza;
    private static int zzb;
    private static Boolean zzc;

    static {
    }

    public static String getMyProcessName() {
        if (zza != null) goto L30;
        if (Build.VERSION.SDK_INT < 28) goto L7;
        zza = Application.getProcessName();
        goto L30
    L7:
        int r0 = zzb;
        if (r0 != 0) goto L10;
        r0 = Process.myPid();
        zzb = r0;
    L10:
        String r3 = null;
        r3 = null;
        r3 = null;
        BufferedReader r32 = null;
        if (r0 > 0) goto L34;
    L28:
        zza = r3;
        goto L30
    L34:
        StringBuilder r5 = new StringBuilder(String.valueOf(r0).length() + 14);     // Catch: Throwable -> L20 IOException -> L27
        r5.append("/proc/");     // Catch: Throwable -> L20 IOException -> L27
        r5.append(r0);     // Catch: Throwable -> L20 IOException -> L27
        r5.append("/cmdline");     // Catch: Throwable -> L20 IOException -> L27
        String r02 = r5.toString();     // Catch: Throwable -> L20 IOException -> L27
        StrictMode.ThreadPolicy r1 = StrictMode.allowThreadDiskReads();     // Catch: Throwable -> L20 IOException -> L27
        BufferedReader r2 = new BufferedReader(new FileReader(r02));     // Catch: Throwable -> L22
        StrictMode.setThreadPolicy(r1);     // Catch: Throwable -> L20 IOException -> L27
        String r03 = r2.readLine();     // Catch: IOException -> L31 Throwable -> L18
        Preconditions.checkNotNull(r03);     // Catch: IOException -> L31 Throwable -> L18
        r3 = r03.trim();     // Catch: IOException -> L31 Throwable -> L18
    L18:
        th = th;
        r32 = r2;
    L25:
        IOUtils.closeQuietly(r32);
        throw th;
    L17:
        IOUtils.closeQuietly(r2);
        goto L28
    L22:
        th = move-exception;
        StrictMode.setThreadPolicy(r1);     // Catch: Throwable -> L20 IOException -> L27
        throw th;     // Catch: Throwable -> L20 IOException -> L27
    L27:
        r2 = null;
    L20:
        th = th;
    L30:
        return zza;
    }

    public static boolean zza() {
        Boolean r0 = zzc;
        if (r0 != null) goto L15;
        if (PlatformVersion.isAtLeastP() == false) goto L16;
        r0 = Boolean.valueOf(Process.isIsolated());
    L13:
        zzc = r0;
        goto L15
    L16:
        Object r02 = zzj.zza(Process.class, "isIsolated", new zzi[0]);     // Catch: ReflectiveOperationException -> L12
        Object[] r1 = new Object[0];     // Catch: ReflectiveOperationException -> L12
        if (r02 == null) goto L11;
        r0 = (Boolean) r02;     // Catch: ReflectiveOperationException -> L12
        goto L13
    L11:
        throw new zzy(zzx.zza("expected a non-null reference", r1));     // Catch: ReflectiveOperationException -> L12
    L12:
        r0 = Boolean.FALSE;
    L15:
        return r0.booleanValue();
    }
}
