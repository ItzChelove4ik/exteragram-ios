package com.google.android.recaptcha.internal;

import java.security.Provider;
import javax.crypto.Mac;

/* loaded from: classes5.dex */
public final class zzadk implements zzadn {
    public zzadk() {
    }

    @Override // com.google.android.recaptcha.internal.zzadn
    public final /* bridge */ /* synthetic */ Object zza(String r1, Provider r2) {
        if (r2 != null) goto L6;
        return Mac.getInstance(r1);
    L6:
        return Mac.getInstance(r1, r2);
    }
}
