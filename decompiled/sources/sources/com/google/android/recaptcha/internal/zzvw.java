package com.google.android.recaptcha.internal;

import java.security.Provider;
import java.security.SecureRandom;

/* loaded from: classes5.dex */
public abstract class zzvw {
    private static final ThreadLocal zza = null;

    static {
        zza = new zzvv();
    }

    public static byte[] zza(int r1) {
        byte[] r12 = new byte[r1];
        ((SecureRandom) zza.get()).nextBytes(r12);
        return r12;
    }

    public static /* synthetic */ SecureRandom zzb() {
        SecureRandom r0 = zzc();
        r0.nextLong();
        return r0;
    }

    private static SecureRandom zzc() {
        Provider r0 = zzti.zza();
        if (r0 != null) goto L19;
    L6:
        Provider r02 = null;
        r02 = (Provider) Class.forName("org.conscrypt.Conscrypt").getMethod("newProvider", null).invoke(null, null);     // Catch: Throwable -> L15
    L9:
        if (r02 == null) goto L13;
        return SecureRandom.getInstance("SHA1PRNG", r02);
    L13:
        return new SecureRandom();
    L19:
        return SecureRandom.getInstance("SHA1PRNG", r0);
    }
}
