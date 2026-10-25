package com.google.android.recaptcha.internal;

import java.security.Provider;
import javax.crypto.KeyAgreement;

/* loaded from: classes5.dex */
public final class zzadh implements zzadn {
    public zzadh() {
    }

    @Override // com.google.android.recaptcha.internal.zzadn
    public final /* bridge */ /* synthetic */ Object zza(String r1, Provider r2) {
        if (r2 != null) goto L6;
        return KeyAgreement.getInstance(r1);
    L6:
        return KeyAgreement.getInstance(r1, r2);
    }
}
