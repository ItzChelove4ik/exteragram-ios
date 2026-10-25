package com.google.android.recaptcha.internal;

import android.app.Application;
import kotlin.coroutines.Continuation;

/* loaded from: classes5.dex */
public final class zzec implements zzev {
    private final Application zza;
    private final zzhr zzb;

    public zzec(Application r1, zzgp r2, zzhr r3) {
        this.zza = r1;
        this.zzb = r3;
    }

    public static final /* synthetic */ Application zze(zzec r0) {
        return r0.zza;
    }

    public static final /* synthetic */ zzhr zzf(zzec r0) {
        return r0.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final int zza() {
        return 38;
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final boolean zzb() {
        return true;
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final Object zzc(String r1, zzaka r2, Continuation r3) {
        return new zzmc(new zzeb(this, null));
    }
}
