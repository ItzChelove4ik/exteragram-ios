package com.google.android.recaptcha.internal;

import java.security.Provider;
import java.security.Signature;

/* loaded from: classes5.dex */
public final class zzadm implements zzadn {
    public zzadm() {
    }

    @Override // com.google.android.recaptcha.internal.zzadn
    public final /* bridge */ /* synthetic */ Object zza(String r1, Provider r2) {
        if (r2 != null) goto L6;
        return Signature.getInstance(r1);
    L6:
        return Signature.getInstance(r1, r2);
    }
}
