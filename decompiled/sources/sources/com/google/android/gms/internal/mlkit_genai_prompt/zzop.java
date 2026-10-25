package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Future;

/* loaded from: classes4.dex */
public abstract class zzop extends zzoq {
    private final ListenableFuture zza;

    public zzop(ListenableFuture r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmc
    public final /* synthetic */ Object zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoo
    public final /* synthetic */ Future zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoq
    public final ListenableFuture zzc() {
        return this.zza;
    }
}
