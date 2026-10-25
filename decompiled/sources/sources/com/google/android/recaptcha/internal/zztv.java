package com.google.android.recaptcha.internal;

import java.security.GeneralSecurityException;
import java.util.Map;

/* loaded from: classes5.dex */
public final class zztv {
    private final Map zza;
    private final Map zzb;

    public /* synthetic */ zztv(Map r1, Map r2, byte[] r3) {
        this.zza = r1;
        this.zzb = r2;
    }

    public static zztu zza() {
        return new zztu(null);
    }

    public final Enum zzb(Object r2) {
        Enum r1 = (Enum) this.zzb.get(r2);
        if (r1 == null) goto L6;
        return r1;
    L6:
        throw new GeneralSecurityException("Unable to convert object enum: ".concat(String.valueOf(r2)));
    }

    public final Object zzc(Enum r2) {
        Object r1 = this.zza.get(r2);
        if (r1 == null) goto L6;
        return r1;
    L6:
        throw new GeneralSecurityException("Unable to convert proto enum: ".concat(String.valueOf(r2)));
    }
}
