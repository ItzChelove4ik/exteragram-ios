package com.google.android.gms.internal.measurement;

import android.util.Log;
import java.lang.reflect.Method;

/* loaded from: classes4.dex */
public abstract class zzrm {
    private static final Method zza = null;

    static {
        Method r1 = null;
        Class<?> r2 = Class.forName("android.os.SystemProperties");     // Catch: Exception -> L5 Throwable -> L9
        r1 = r2.getMethod("get", new Class[]{String.class, String.class});     // Catch: Exception -> L5 Throwable -> L9
        r2.getMethod("getInt", new Class[]{String.class, Integer.TYPE});     // Catch: Exception -> L5 Throwable -> L9
        r2.getMethod("getLong", new Class[]{String.class, Long.TYPE});     // Catch: Exception -> L5 Throwable -> L9
        r2.getMethod("getBoolean", new Class[]{String.class, Boolean.TYPE});     // Catch: Exception -> L5 Throwable -> L9
    L7:
        zza = r1;
        return;
    L5:
        e = move-exception;
        e.printStackTrace();     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        zza = r1;
        throw th;
    }

    public static String zza(String r2, String r3) {
        return (String) zza.invoke(null, new Object[]{"tiktok_systrace", "false"});
    L5:
        e = move-exception;
        Log.e("SystemProperties", "get error", e);
        return "false";
    }
}
