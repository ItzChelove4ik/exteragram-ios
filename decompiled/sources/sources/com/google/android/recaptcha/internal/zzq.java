package com.google.android.recaptcha.internal;

import java.util.Optional;

/* loaded from: classes5.dex */
final /* synthetic */ class zzq implements zzaj {
    private final /* synthetic */ long zza;

    public /* synthetic */ zzq(long r1) {
        this.zza = r1;
    }

    @Override // java.util.function.Function
    public final /* synthetic */ Object apply(Object r3) {
        zzam r32 = (zzam) r3;
        long r0 = this.zza;
        r32.zzb.zzb(zzar.zzb(r0));     // Catch: zzah -> L6
        return Optional.empty();
    L7:
        return Optional.of(zzc.zza);
    }
}
