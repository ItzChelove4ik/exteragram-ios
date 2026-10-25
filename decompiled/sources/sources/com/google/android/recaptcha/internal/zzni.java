package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;

/* loaded from: classes5.dex */
public final class zzni extends zzoa {
    private final zzne zza;

    public zzni(zzne r12, zznf r13) {
        super(r12, r13, zzoc.zza.zza(), 59, 57, 61, 72, zzaki.zze(), null);
        this.zza = r12;
    }

    public final zzne zza() {
        return this.zza;
    }

    public final Object zzb(String r2, Continuation r3) {
        return new zzmc(new zznh(this, r2, null));
    }
}
