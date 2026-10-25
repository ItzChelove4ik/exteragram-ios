package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.genai.common.DownloadCallback;

/* loaded from: classes4.dex */
final /* synthetic */ class zzaud implements zzoj {
    private final /* synthetic */ zzaun zza;
    private final /* synthetic */ DownloadCallback zzb;

    public /* synthetic */ zzaud(zzaun r1, DownloadCallback r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoj
    public final /* synthetic */ ListenableFuture zza(Object r2) {
        return this.zza.zzr(this.zzb, (zzdq) r2);
    }
}
