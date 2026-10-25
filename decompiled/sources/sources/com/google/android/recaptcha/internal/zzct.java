package com.google.android.recaptcha.internal;

import java.util.List;
import kotlin.coroutines.Continuation;

/* loaded from: classes5.dex */
public final class zzct {
    private final List zza;

    public zzct(List r1) {
        this.zza = r1;
    }

    public final List zza() {
        return this.zza;
    }

    public final Object zzb(long r7, zzakg r9, Continuation r10) {
        return new zzmc(new zzcs(this, r7, r9, null));
    }

    public final Object zzc(String r8, zzaka r9, long r10, Continuation r12) {
        return new zzmc(new zzcp(this, r8, r9, r10, null));
    }
}
