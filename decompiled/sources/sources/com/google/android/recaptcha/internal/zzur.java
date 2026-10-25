package com.google.android.recaptcha.internal;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzur {
    private static final zztx zzb = null;
    private static final zzur zzc = null;
    private final Map zza;

    static {
        zzb = zzuq.zza;
        zzc = zzc();
    }

    public zzur() {
        this.zza = new HashMap();
    }

    public static zzur zza() {
        return zzc;
    }

    private static zzur zzc() {
        zzur r0 = new zzur();
        r0.zzb(zzb, zzuj.class);     // Catch: GeneralSecurityException -> L5
        return r0;
    L5:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("unexpected error.", e);
        return null;
    }

    public final synchronized void zzb(zztx r3, Class r4) {
        monitor-enter(this);
        Map r0 = this.zza;     // Catch: Throwable -> L10
        zztx r1 = (zztx) r0.get(r4);     // Catch: Throwable -> L10
        if (r1 != null) goto L6;
    L12:
        r0.put(r4, r3);     // Catch: Throwable -> L10
        monitor-exit(this);
        return;
    L6:
        if (r1.equals(r3) == true) goto L12;
        String r42 = r4.toString();     // Catch: Throwable -> L10
        StringBuilder r12 = new StringBuilder(r42.length() + 60);     // Catch: Throwable -> L10
        r12.append("Different key creator for parameters class ");     // Catch: Throwable -> L10
        r12.append(r42);     // Catch: Throwable -> L10
        r12.append(" already inserted");     // Catch: Throwable -> L10
        throw new GeneralSecurityException(r12.toString());     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }
}
