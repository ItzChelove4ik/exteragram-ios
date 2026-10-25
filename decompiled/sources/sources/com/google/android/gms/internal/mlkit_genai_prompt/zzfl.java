package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzfl implements zzoj {
    private final /* synthetic */ zzfv zza;

    public /* synthetic */ zzfl(zzfv r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoj
    public final /* synthetic */ ListenableFuture zza(Object r2) {
        zzdh r22 = (zzdh) r2;
        zzfv r1 = this.zza;
        return zzot.zza(Integer.valueOf(r22.zza().zzh(r1.zza.zzj())));
    L5:
        e = move-exception;
        return zzot.zzc(zzdo.zze(6, "Failed to connect to AICore service.", e));
    }
}
