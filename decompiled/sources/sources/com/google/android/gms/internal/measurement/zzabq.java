package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class zzabq {
    private static final String[] zza = null;
    private static final zzabu zzb = null;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.google.android.gms.internal.measurement.zzabu] */
    static {
        zza = new String[]{"com.google.common.flogger.util.StackWalkerStackGetter", "com.google.common.flogger.util.JavaLangAccessStackGetter"};
        int r0 = 0;
    L4:
        if (r0 >= 2) goto L11;
        zzabv r2 = null;
        r2 = (zzabu) Class.forName(zza[r0]).asSubclass(zzabu.class).getDeclaredConstructor(null).newInstance(null);     // Catch: Throwable -> L14
    L8:
        if (r2 != null) goto L12;
        r0 = r0 + 1;
    L12:
        zzb = r2;
        return;
    L11:
        r2 = new zzabv();
        goto L12
    }

    public static StackTraceElement[] zzb(Class r1, int r2, int r3) {
        if (r2 > 0) goto L9;
        if (r2 == (-1)) goto L9;
        f$$ExternalSyntheticBUOutline1.m("invalid maximum depth: 0");
        return null;
    L9:
        return zzb.zzb(r1, r2, 2);
    }
}
