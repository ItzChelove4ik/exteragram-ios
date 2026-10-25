package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;

/* loaded from: classes5.dex */
public final class zzeh implements zzev {
    private boolean zza;

    public zzeh() {
        this.zza = true;
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final int zza() {
        return 25;
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final boolean zzb() {
        return this.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final Object zzc(String r1, zzaka r2, Continuation r3) {
        return new zzmc(new zzef(r2, this, null));
    }

    public final void zze(boolean r1) {
        this.zza = false;
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final Object zzk(zzakk r2, Continuation r3) {
        return new zzmc(new zzeg(r2, this, null));
    }
}
