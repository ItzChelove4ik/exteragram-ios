package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzgj extends zzar {
    final /* synthetic */ zzgk zza;

    public zzgj(zzgk r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzas
    public final void zzb(zzan r1) {
        this.zza.zze(r1);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzas
    public final void zzc(int r2, String r3) {
        if (r2 != 1) goto L5;
        int r22 = 601;
    L12:
        this.zza.zzf(zzdo.zze(r22, "AiCore service is not connected. Service provider failure: ".concat(String.valueOf(r3)), null));
        return;
    L5:
        if (r2 != 2) goto L7;
        r22 = 604;
        goto L12
    L7:
        if (r2 == 3) goto L9;
        r22 = 0;
        goto L12
    L9:
        r22 = 607;
        goto L12
    }
}
