package com.google.android.gms.internal.clearcut;

/* loaded from: classes4.dex */
abstract class zzaw {
    private static final Class<?> zzfb = null;
    private static final boolean zzfc = false;

    static {
        zzfb = zze("libcore.io.Memory");
        if (zze("org.robolectric.Robolectric") == null) goto L5;
        boolean r0 = true;
    L6:
        zzfc = r0;
        return;
    L5:
        r0 = false;
        goto L6
    }

    private static <T> Class<T> zze(String r0) {
        return (Class<T>) Class.forName(r0);
    L4:
        return null;
    }

    public static boolean zzx() {
        if (zzfb != null) goto L5;
        return false;
    L5:
        if (zzfc == true) goto L10;
        return true;
    L10:
        return false;
    }

    public static Class<?> zzy() {
        return zzfb;
    }
}
