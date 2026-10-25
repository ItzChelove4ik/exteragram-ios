package com.google.android.recaptcha.internal;

import kotlin.jvm.functions.Function1;

/* loaded from: classes5.dex */
final /* synthetic */ class zzde implements Function1 {
    private final /* synthetic */ zzcz zza;
    private final /* synthetic */ zzali zzb;

    public /* synthetic */ zzde(zzcz r1, zzali r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* synthetic */ Object invoke(Object r3) {
        zzaka r32 = (zzaka) r3;
        String r0 = r32.zza();
        if (this.zza.zza(r0) == false) goto L6;
        zzali r2 = this.zzb;
        zzajz r33 = (zzajz) r32.zzG();
        r33.zza(r2.zza());
        r33.zzb(r2.zzb());
        return (zzaka) r33.zzp();
    L6:
        return null;
    }
}
