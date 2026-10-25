package com.google.android.recaptcha.internal;

import java.security.GeneralSecurityException;
import java.security.Provider;
import java.util.Iterator;

/* loaded from: classes5.dex */
final class zzadd implements zzade {
    private final zzadn zza;

    public /* synthetic */ zzadd(zzadn r1, byte[] r2) {
        this.zza = r1;
    }

    @Override // com.google.android.recaptcha.internal.zzade
    public final Object zza(String r5) {
        Iterator r0 = zzadf.zza(new String[]{"GmsCore_OpenSSL", "AndroidOpenSSL", "Conscrypt"}).iterator();
        Exception r1 = null;
    L4:
        if (r0.hasNext() == false) goto L12;
        Provider r2 = (Provider) r0.next();
        return this.zza.zza(r5, r2);
    L8:
        e = move-exception;
        if (r1 != null) goto L4;
        r1 = e;
        goto L4
    L12:
        throw new GeneralSecurityException("No good Provider found.", r1);
    }
}
