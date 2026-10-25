package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.genai.common.StreamingCallback;
import java.util.concurrent.ExecutorService;

/* loaded from: classes4.dex */
public final class zzaah extends zzaur implements AutoCloseable {
    public zzaah(int r2, ExecutorService r3, zzaty r4) {
        super(r3, r2, -1, r4);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaun
    public final zzdp zzd(zzdi r1, zzdq r2) {
        zzii r0 = zzij.zzd(r1);
        r0.zzc(r2);
        return zzig.zzd(r0.zzd());
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaun
    public final /* bridge */ /* synthetic */ Object zze(Object r1, StreamingCallback r2) {
        return zzys.zze((zzaan) r1, r2, null);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaun
    public final /* bridge */ /* synthetic */ Object zzf(Object r1) {
        return zzys.zzd((zzic) r1);
    }
}
