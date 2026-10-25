package com.google.android.gms.dynamite;

import android.os.Looper;
import android.util.Log;

/* loaded from: classes.dex */
public abstract class zzb {
    private static ClassLoader zza;
    private static Thread zzb;

    static {
    }

    public static synchronized ClassLoader zza() {
        monitor-enter(zzb.class);
    L48:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L64;
        Thread r2 = zzb;     // Catch: Throwable -> L48
        ClassLoader r3 = null;
        if (r2 != null) goto L52;
        ThreadGroup r22 = Looper.getMainLooper().getThread().getThreadGroup();     // Catch: Throwable -> L48
        if (r22 != null) goto L12;
        r2 = null;
    L45:
        zzb = r2;     // Catch: Throwable -> L48
        if (r2 != null) goto L52;
    L60:
        zza = r3;     // Catch: Throwable -> L48
        goto L64
    L12:
        monitor-enter(Void.class);     // Catch: Throwable -> L48
        int r6 = r22.activeGroupCount();     // Catch: Throwable -> L19 SecurityException -> L21
        ThreadGroup[] r7 = new ThreadGroup[r6];     // Catch: Throwable -> L19 SecurityException -> L21
        r22.enumerate(r7);     // Catch: Throwable -> L19 SecurityException -> L21
        int r8 = 0;
        int r9 = 0;
    L14:
        if (r9 >= r6) goto L23;
        ThreadGroup r10 = r7[r9];     // Catch: Throwable -> L19 SecurityException -> L21
        if ("dynamiteLoader".equals(r10.getName()) == true) goto L24;
        r9 = r9 + 1;     // Catch: Throwable -> L19 SecurityException -> L21
    L24:
        if (r10 != null) goto L26;
        r10 = new ThreadGroup(r22, "dynamiteLoader");     // Catch: Throwable -> L19 SecurityException -> L21
    L26:
        int r23 = r10.activeCount();     // Catch: Throwable -> L19 SecurityException -> L21
        Thread[] r62 = new Thread[r23];     // Catch: Throwable -> L19 SecurityException -> L21
        r10.enumerate(r62);     // Catch: Throwable -> L19 SecurityException -> L21
    L27:
        if (r8 >= r23) goto L32;
        Thread r72 = r62[r8];     // Catch: Throwable -> L19 SecurityException -> L21
        if ("GmsDynamite".equals(r72.getName()) == true) goto L33;
        r8 = r8 + 1;
    L33:
        if (r72 == null) goto L72;
    L43:
        monitor-exit(Void.class);     // Catch: Throwable -> L19
        r2 = r72;
        goto L45
    L72:
        zza r24 = new zza(r10, "GmsDynamite");     // Catch: Throwable -> L19 SecurityException -> L39
        r24.setContextClassLoader(null);     // Catch: Throwable -> L19 SecurityException -> L37
        r24.start();     // Catch: Throwable -> L19 SecurityException -> L37
        r72 = r24;
    L37:
        SecurityException e = e;
        r72 = r24;
    L42:
        String r63 = e.getMessage();     // Catch: Throwable -> L19
        StringBuilder r92 = new StringBuilder(String.valueOf(r63).length() + 39);     // Catch: Throwable -> L19
        r92.append("Failed to enumerate thread/threadgroup ");     // Catch: Throwable -> L19
        r92.append(r63);     // Catch: Throwable -> L19
        Log.w("DynamiteLoaderV2CL", r92.toString());     // Catch: Throwable -> L19
    L39:
        e = move-exception;
        e = e;
        goto L42
    L32:
        r72 = null;
        goto L33
    L23:
        r10 = null;
    L19:
        th = move-exception;
        throw th;     // Catch: Throwable -> L48
    L21:
        e = move-exception;
        e = e;
        r72 = null;
    L52:
        monitor-enter(r2);     // Catch: Throwable -> L48
        r3 = zzb.getContextClassLoader();     // Catch: Throwable -> L55 SecurityException -> L57
    L59:
        monitor-exit(r2);     // Catch: Throwable -> L55
    L55:
        th = move-exception;
        throw th;     // Catch: Throwable -> L48
    L57:
        e = move-exception;
        String r4 = e.getMessage();     // Catch: Throwable -> L55
        StringBuilder r73 = new StringBuilder(String.valueOf(r4).length() + 41);     // Catch: Throwable -> L55
        r73.append("Failed to get thread context classloader ");     // Catch: Throwable -> L55
        r73.append(r4);     // Catch: Throwable -> L55
        Log.w("DynamiteLoaderV2CL", r73.toString());     // Catch: Throwable -> L55
    L64:
        ClassLoader r1 = zza;     // Catch: Throwable -> L48
        monitor-exit(zzb.class);
        return r1;
    }
}
