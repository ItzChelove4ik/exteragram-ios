package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;

/* loaded from: classes5.dex */
public final class zzee implements zzev {
    public zzee() {
    }

    public static final /* synthetic */ zzama zze(zzee r0, String r1) {
        zzalz r02 = zzama.zza();
        r02.zzc(r1);
        return (zzama) r02.zzp();
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final int zza() {
        return 11;
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final boolean zzb() {
        return true;
    }

    @Override // com.google.android.recaptcha.internal.zzev
    public final Object zzc(String r1, zzaka r2, Continuation r3) {
        return new zzmc(new zzed(this, null));
    }
}
