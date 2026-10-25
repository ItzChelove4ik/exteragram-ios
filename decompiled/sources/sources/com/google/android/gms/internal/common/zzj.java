package com.google.android.gms.internal.common;

/* loaded from: classes4.dex */
public abstract class zzj {
    public static Object zza(Class r2, String r3, zzi... r4) {
        return zzc(r2, "isIsolated", null, false, r4);
    }

    private static Object zzc(Class r2, String r3, Object r4, boolean r5, zzi... r6) {
        int r42 = r6.length;
        Class<?>[] r52 = new Class[r42];
        Object[] r43 = new Object[r42];
        Object r1 = null;
        if (r6.length <= 0) goto L5;
        zzi r22 = r6[0];
        r1.getClass();
        throw null;
    L5:
        return r2.getDeclaredMethod(r3, r52).invoke(null, r43);
    }
}
