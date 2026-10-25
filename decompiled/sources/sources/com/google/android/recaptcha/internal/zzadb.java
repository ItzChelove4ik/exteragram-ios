package com.google.android.recaptcha.internal;

import java.security.Provider;
import java.util.Iterator;

/* loaded from: classes5.dex */
final class zzadb implements zzade {
    private final zzadn zza;

    public /* synthetic */ zzadb(zzadn r1, byte[] r2) {
        this.zza = r1;
    }

    @Override // com.google.android.recaptcha.internal.zzade
    public final Object zza(String r4) {
        Iterator r0 = zzadf.zza(new String[]{"GmsCore_OpenSSL", "AndroidOpenSSL"}).iterator();
    L4:
        if (r0.hasNext() == false) goto L9;
        Provider r1 = (Provider) r0.next();
        return this.zza.zza(r4, r1);
    L9:
        return this.zza.zza(r4, null);
    }
}
