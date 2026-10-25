package com.google.android.gms.internal.p000authapi;

import android.util.Base64;
import java.security.SecureRandom;

/* loaded from: classes4.dex */
public abstract class zbaw {
    private static final SecureRandom zba = null;

    static {
        zba = new SecureRandom();
    }

    public static String zba() {
        byte[] r0 = new byte[16];
        zba.nextBytes(r0);
        return Base64.encodeToString(r0, 11);
    }
}
