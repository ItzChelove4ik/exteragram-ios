package com.google.android.gms.internal.mlkit_genai_prompt;

import android.annotation.SuppressLint;
import com.google.mlkit.genai.common.DownloadCallback;
import com.google.mlkit.genai.common.GenAiException;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzatv implements zzdy {
    final /* synthetic */ DownloadCallback zza;

    public zzatv(zzaun r1, DownloadCallback r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdy
    public final void zza(zzdq r1, long r2) {
        this.zza.onDownloadStarted(r2);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdy
    @SuppressLint({"WrongConstant"})
    public final void zzb(zzdq r2, int r3, zzdo r4) {
        GenAiException r22 = new GenAiException(r4, r4.zzg(), r4.zzh());
        this.zza.onDownloadFailed(r22);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdy
    public final void zzc(zzdq r1, long r2) {
        this.zza.onDownloadProgress(r2);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdy
    public final void zzd(zzdq r1) {
        this.zza.onDownloadCompleted();
    }
}
