package com.google.android.recaptcha.internal;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes5.dex */
public abstract class zztf {
    private static final Logger zza = null;
    private static final AtomicBoolean zzb = null;

    static {
        zza = Logger.getLogger(zztf.class.getName());
        zzb = new AtomicBoolean(false);
    }

    public static boolean zza() {
        if (zzb.get() == false) goto L6;
        return true;
    L6:
        return false;
    }

    public static Boolean zzb() {
        return (Boolean) Class.forName("org.conscrypt.Conscrypt").getMethod("isBoringSslFIPSBuild", null).invoke(null, null);
    L4:
        zza.logp(Level.INFO, "com.google.crypto.tink.config.internal.TinkFipsUtil", "checkConscryptIsAvailableAndUsesFipsBoringSsl", "Conscrypt is not available or does not support checking for FIPS build.");
        return Boolean.FALSE;
    }
}
