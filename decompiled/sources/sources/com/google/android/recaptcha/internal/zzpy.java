package com.google.android.recaptcha.internal;

import java.util.Map;

/* loaded from: classes5.dex */
public abstract class zzpy {
    Map zza;

    public zzpy() {
    }

    public final Map zza() {
        Map r0 = this.zza;
        if (r0 != null) goto L6;
        zzpj r02 = new zzpj();
        this.zza = r02;
        return r02;
    L6:
        return r0;
    }
}
