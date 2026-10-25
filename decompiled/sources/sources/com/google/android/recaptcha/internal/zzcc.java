package com.google.android.recaptcha.internal;

import kotlinx.coroutines.CompletableDeferred;

/* loaded from: classes5.dex */
public final class zzcc implements zzcf {
    private final CompletableDeferred zza;

    public zzcc(CompletableDeferred r1) {
        this.zza = r1;
    }

    public final CompletableDeferred zza() {
        return this.zza;
    }
}
