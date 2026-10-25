package com.google.android.recaptcha.internal;

import java.security.KeyPairGenerator;
import java.security.Provider;

/* loaded from: classes5.dex */
public final class zzadj implements zzadn {
    public zzadj() {
    }

    @Override // com.google.android.recaptcha.internal.zzadn
    public final /* bridge */ /* synthetic */ Object zza(String r1, Provider r2) {
        if (r2 != null) goto L6;
        return KeyPairGenerator.getInstance(r1);
    L6:
        return KeyPairGenerator.getInstance(r1, r2);
    }
}
