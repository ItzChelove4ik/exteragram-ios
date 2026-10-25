package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.genai.common.StreamingCallback;

/* loaded from: classes4.dex */
final /* synthetic */ class zzauh implements zzoj {
    private final /* synthetic */ zzaun zza;
    private final /* synthetic */ Object zzb;
    private final /* synthetic */ StreamingCallback zzc;

    public /* synthetic */ zzauh(zzaun r1, Object r2, StreamingCallback r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoj
    public final /* synthetic */ ListenableFuture zza(Object r3) {
        return ((zzdp) r3).zzb(this.zza.zze(this.zzb, this.zzc));
    }
}
