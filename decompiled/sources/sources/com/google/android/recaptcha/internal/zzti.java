package com.google.android.recaptcha.internal;

import java.security.Provider;
import java.security.Security;

/* loaded from: classes5.dex */
public abstract class zzti {
    private static final String[] zza = null;

    static {
        zza = new String[]{"GmsCore_OpenSSL", "AndroidOpenSSL", "Conscrypt"};
    }

    public static Provider zza() {
        String[] r0 = zza;
        int r1 = 0;
    L4:
        if (r1 >= 3) goto L9;
        Provider r2 = Security.getProvider(r0[r1]);
        if (r2 != null) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return r2;
    L9:
        return null;
    }
}
