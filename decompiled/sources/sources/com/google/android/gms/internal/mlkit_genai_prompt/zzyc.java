package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.genai.common.DownloadCallback;
import com.google.mlkit.genai.common.GenAiException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CompletableDeferred;

/* loaded from: classes4.dex */
public final class zzyc implements DownloadCallback {
    final /* synthetic */ boolean zza;
    final /* synthetic */ DownloadCallback zzb;
    final /* synthetic */ CompletableDeferred zzc;
    final /* synthetic */ zzyd zzd;
    final /* synthetic */ zzaur zze;
    final /* synthetic */ Function0 zzf;

    public zzyc(boolean r1, DownloadCallback r2, CompletableDeferred r3, zzyd r4, zzaur r5, Function0 r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
    }

    @Override // com.google.mlkit.genai.common.DownloadCallback
    public final void onDownloadCompleted() {
        this.zzf.invoke();
    }

    @Override // com.google.mlkit.genai.common.DownloadCallback
    public final void onDownloadFailed(GenAiException r4) {
        CompletableDeferred r0 = this.zzc;
        if (r0 == null) goto L5;
        r0.completeExceptionally(r4);
    L5:
        zzyd.zzi(this.zzd, this.zze, r4, this.zzb);
        this.zzf.invoke();
    }

    @Override // com.google.mlkit.genai.common.DownloadCallback
    public final void onDownloadProgress(long r2) {
        if (this.zza == false) goto L6;
        this.zzb.onDownloadProgress(r2);
        return;
    }

    @Override // com.google.mlkit.genai.common.DownloadCallback
    public final void onDownloadStarted(long r2) {
        if (this.zza == false) goto L5;
        this.zzb.onDownloadStarted(r2);
    L5:
        CompletableDeferred r1 = this.zzc;
        if (r1 == null) goto L9;
        r1.complete(Unit.INSTANCE);
        return;
    }
}
