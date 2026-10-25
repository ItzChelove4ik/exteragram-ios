package com.google.android.gms.internal.vision;

/* loaded from: classes5.dex */
abstract class zzhi {
    private static final Class<?> zza = null;
    private static final boolean zzb = false;

    static {
        zza = zza("libcore.io.Memory");
        if (zza("org.robolectric.Robolectric") == null) goto L5;
        boolean r0 = true;
    L6:
        zzb = r0;
        return;
    L5:
        r0 = false;
        goto L6
    }

    private static <T> Class<T> zza(String r0) {
        return (Class<T>) Class.forName(r0);
    L4:
        return null;
    }

    public static boolean zza() {
        if (zza != null) goto L5;
        return false;
    L5:
        if (zzb == true) goto L10;
        return true;
    L10:
        return false;
    }

    public static Class<?> zzb() {
        return zza;
    }
}
